package Classes.Beans;

import Spring.Security.PasswordEncryption;
import Spring.Data.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

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
        if (isUserExists(username)){
            User user = loadUser(username);
            return passwordEncryption.decode(user.getPassword()).equals(password);
        }else {
            return false;
        }
    }

    public User loadUser(String username){
        if (isUserExists(username)){
            return userRepo.findUserByUsername(username);
        }else {
            return null;
        }
    }

    public boolean isUserExists(String username){
        return userRepo.findUserByUsername(username)!=null;
    }

    public void saveUser(User user){
        user.setPassword(passwordEncryption.encode(user.getPassword()));
        userRepo.save(user);
    }
}
//finished