package com.projectmanagement.saasovation.security.application;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class  LoginController {


//    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"/login", "/logout-success"}, method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        return "login";
    }

}