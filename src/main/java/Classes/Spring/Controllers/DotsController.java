package Classes.Spring.Controllers;

import Classes.Beans.Dot;
import Classes.Beans.DotsService;
import Classes.Beans.UsersService;
import Classes.Spring.Security.HeaderEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

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
    List<Dot> getDotes(@RequestHeader(value = "auth") String credentials, @RequestBody Dot dot){
        if (usersService.isCredentialsValid(headerEncryption.decodeLoginFromHeaderBasic64(credentials), headerEncryption.decodePasswordFromHeaderBasic64(credentials))) {
            return dotsService.getDotsByUsername(headerEncryption.decodeLoginFromHeaderBasic64(credentials));
        }else {
            return new ArrayList<>();
        }
    }
    @PostMapping
    Dot saveDot(@RequestHeader(value = "auth") String credentials ,@RequestBody Dot dot){
        if (usersService.isCredentialsValid(headerEncryption.decodeLoginFromHeaderBasic64(credentials),headerEncryption.decodePasswordFromHeaderBasic64(credentials))) {
            dot.setOwner(headerEncryption.decodeLoginFromHeaderBasic64(credentials));
            dotsService.saveDot(dot);
            return dot;
        }else {
            return new Dot();
        }

    }
}
//finished