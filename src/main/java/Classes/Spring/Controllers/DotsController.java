package Classes.Spring.Controllers;

import Classes.Beans.Dot;
import Classes.Beans.DotsService;
import Classes.Beans.UsersService;
import Classes.Spring.Security.HeaderEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "dots")
public class DotsController {
    DotsService dotsService;
    UsersService usersService;
    HeaderEncryption headerEncryption;

    @Autowired
    public DotsController(DotsService dotsService, UsersService usersService, HeaderEncryption headerEncryption) {
        this.dotsService = dotsService;
        this.usersService = usersService;
        this.headerEncryption = headerEncryption;
    }
    @GetMapping
    List<Dot> getDotes(@RequestHeader(value = "Authorization") String credentials){
        if (usersService.isCredentialsValid(headerEncryption.decodeLoginFromHeaderBasic64(credentials), headerEncryption.decodePasswordFromHeaderBasic64(credentials))) {
            return dotsService.getDotsByUsername(headerEncryption.decodeLoginFromHeaderBasic64(credentials));
        }else {
            return new ArrayList<>();
        }
    }
    @PostMapping
    Dot saveDot(@RequestHeader(value = "Authorization") String credentials ,@RequestBody Dot dot){
        if (usersService.isCredentialsValid(headerEncryption.decodeLoginFromHeaderBasic64(credentials),headerEncryption.decodePasswordFromHeaderBasic64(credentials))) {
            dot.setOwner(headerEncryption.decodeLoginFromHeaderBasic64(credentials));
            dotsService.saveDot(dot);
            return dot;
        }else {
            System.out.println("achtung!");
            return new Dot();
        }

    }
}
//finished