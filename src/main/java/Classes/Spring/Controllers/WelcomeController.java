package Classes.Spring.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/signIn", "/home"})
public class WelcomeController {
    @GetMapping
    String index(){
        return "index.html";
    }
}