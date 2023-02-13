package com.study.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
//        model.addAttribute("signUpForm", new SignUpForm());
        //언제 생략가능?
        //모델에 담은 객체를 camel case로 사용할 때 생략가능하다.
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }

}
