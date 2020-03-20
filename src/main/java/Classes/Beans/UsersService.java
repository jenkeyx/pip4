package Classes.Beans;

import Classes.Spring.Security.PasswordEncryption;
import Classes.Spring.Data.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    UserRepo userRepo;
    PasswordEncryption passwordEncryption;

    @Autowired
    public UsersService(UserRepo userRepo, PasswordEncryption passwordEncryption) {
        this.userRepo = userRepo;
        this.passwordEncryption = passwordEncryption;
    }

    public void createUser(User user){
        user.setPassword(passwordEncryption.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean isCredentialsValid(String username,String password){
        User user = loadUser(username);
        if (user != null){
            return passwordEncryption.decode(user.getPassword()).equals(password);
        }else {
            return false;
        }
    }

    public User loadUser(String username){
            return userRepo.findUserByUsername(username);
    }

    public void saveUser(User user){
        user.setPassword(passwordEncryption.encode(user.getPassword()));
        userRepo.save(user);
    }
}
//finished