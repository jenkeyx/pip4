package Classes.Controllers;

import Classes.Beans.UsersService;
import Classes.Controllers.Exceptions.WrongCredentialsException;
import Spring.Security.HeaderEncryption;
import Spring.Security.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "logging", method = RequestMethod.POST)
public class AuthController {
    UsersService usersService;
    HeaderEncryption headerEncryption;
    PasswordEncryption passwordEncryption;

    @Autowired
    public AuthController(UsersService usersService, HeaderEncryption headerEncryption, PasswordEncryption passwordEncryption) {
        this.usersService = usersService;
        this.headerEncryption = headerEncryption;
        this.passwordEncryption = passwordEncryption;
    }
    @PostMapping
    void authorize(@RequestHeader(name = "auth") String header) throws WrongCredentialsException {
        String username = headerEncryption.decodeLoginFromHeaderBasic64(header);
        String password = passwordEncryption.decode(header);
        if (!usersService.isCredentialsValid(username,password)){
            throw new WrongCredentialsException();
        }
    }

}
//finished