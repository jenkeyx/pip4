package Classes.Spring.Controllers;

import Classes.Beans.*;
import Classes.Spring.Security.HeaderEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dots")
public class DotsController {
    DotsService dotsService;
    UsersService usersService;
    HeaderEncryption headerEncryption;
    ClickerCounter clickerCounter = new ClickerCounter();
    RatioCounter ratioCounter = new RatioCounter();
    MBeanServer server = ManagementFactory.getPlatformMBeanServer();
    ObjectName clickerName,ratioName;

    {
        try {
            clickerName = new ObjectName("Classes.Beans:type=ClickerCounter");
            ratioName = new ObjectName("Classes.Beans:type=RatioCounter");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public DotsController(DotsService dotsService, UsersService usersService, HeaderEncryption headerEncryption) {
        this.dotsService = dotsService;
        this.usersService = usersService;
        this.headerEncryption = headerEncryption;
        try {
            server.registerMBean(clickerCounter, clickerName);
            server.registerMBean(ratioCounter,ratioName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    List<Dot> getDotes(@RequestHeader(value = "Authorization") String credentials) {
        if (usersService.isCredentialsValid(headerEncryption.decodeLoginFromHeaderBasic64(credentials), headerEncryption.decodePasswordFromHeaderBasic64(credentials))) {
            return dotsService.getDotsByUsername(headerEncryption.decodeLoginFromHeaderBasic64(credentials));
        } else {
            return new ArrayList<>();
        }
    }

    @PostMapping
    Dot saveDot(@RequestHeader(value = "Authorization") String credentials, @RequestBody Dot dot) {
        if (usersService.isCredentialsValid(headerEncryption.decodeLoginFromHeaderBasic64(credentials), headerEncryption.decodePasswordFromHeaderBasic64(credentials))) {
            dot.setOwner(headerEncryption.decodeLoginFromHeaderBasic64(credentials));
            dotsService.saveDot(dot);
            clickerCounter.count(dot.isHit());
            ratioCounter.calcRatio(clickerCounter.getHitCount(),clickerCounter.getClickCount());
            return dot;
        } else {
            System.out.println("achtung!");
            return new Dot();
        }

    }
}
//finished