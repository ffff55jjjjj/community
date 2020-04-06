package lifee.majiang.community.community.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AutoLoginController {

    @Value("${auto.login.default.token}")
    private String defaultToken;

    @GetMapping("/autoLogin")
    public String autoLogin(HttpServletResponse response, Model model){
        response.addCookie(new Cookie("token",defaultToken));
        return "redirect:/";
    }
}
