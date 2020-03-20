package Classes.Spring.Controllers;

import Classes.Beans.User;
import Classes.Beans.UsersService;
import Classes.Spring.Controllers.Exceptions.UserAlreadyExsistsException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registration")
public class RegController {
    private UsersService usersService;

    @Autowired
    public RegController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public void registration(@RequestBody User user) throws UserAlreadyExsistsException {
        if (usersService.loadUser(user.getUsername()) != null){
            throw new UserAlreadyExsistsException();
        }else {
            usersService.saveUser(user);
        }
    }
}
//finished
