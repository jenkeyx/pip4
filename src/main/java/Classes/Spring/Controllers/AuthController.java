package Classes.Spring.Controllers;

import Classes.Beans.UsersService;
import Classes.Spring.Controllers.Exceptions.WrongCredentialsException;
import Classes.Spring.Security.HeaderEncryption;
import Classes.Spring.Security.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logging", method = RequestMethod.POST)
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
    void authorize(@RequestHeader(name = "Authorization") String header) throws WrongCredentialsException {
        String username = headerEncryption.decodeLoginFromHeaderBasic64(header);
        String password = headerEncryption.decodePasswordFromHeaderBasic64(header);
        if (!usersService.isCredentialsValid(username,password)){
            System.out.println(header);
            throw new WrongCredentialsException();
        }
    }

}
//finished