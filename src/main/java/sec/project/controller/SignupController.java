package sec.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;
import java.util.List;

@Controller
@Slf4j
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        log.info("in loadForm.GET.");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {

        log.info("New Form Submission" + name + " " + address); //who is signupping?
        signupRepository.save(new Signup(name, address));
        return "done";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listThemAll(Model model) {
        log.info("in list.");
        List<Signup> list =  signupRepository.findAll();
        model.addAttribute("cnt", list.size());
        model.addAttribute("signups", list);
        return "list";
    }

}
