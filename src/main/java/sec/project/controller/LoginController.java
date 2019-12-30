package sec.project.controller;

import jdk.jfr.events.ThrowablesEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.config.CustomUserDetailsService;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadRoot() {
        return "homepage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/perform_logout", method = RequestMethod.POST)
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = "/perform_login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password) throws AuthenticationException {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        
        log.info("login: {} with {}", username, password); //Does not work for some reason??
        
        
        //if (userDetails.getPassword().equals(password)) {
            return "homepage";  // TODO Build any login check, please!!!!!!
        //}
        /*}else {  //BROKEN :no check
           log.info("login FAILED: {} with {}", username, password); //Add a reason + source
           throw new MyAuthFail();
        } 
        */
    }

    public class MyAuthFail extends AuthenticationException {
        public MyAuthFail() {
            super("Bad password");
        }

    }
}
