package Classes.Controllers;

import Classes.Beans.User;
import Classes.Beans.UsersService;
import Classes.Controllers.Exceptions.UserAlreadyExsistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping(value = "reg")
public class RegController {
    private UsersService usersService;

    @Autowired
    public RegController(UsersService usersService) {
        this.usersService = usersService;
    }

    private void registration(@RequestBody User user) throws UserAlreadyExsistsException {
        if (usersService.isUserExists(user.getUsername())){
            throw new UserAlreadyExsistsException();
        }else {
            usersService.saveUser(user);
        }
    }
}
//finished
