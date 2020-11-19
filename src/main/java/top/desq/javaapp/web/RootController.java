package top.desq.javaapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.desq.javaapp.service.AutoService;
import top.desq.javaapp.service.UserService;
import top.desq.javaapp.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private AutoService autoService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:auto";
    }

    @GetMapping("/auto")
    public String getAuto(Model model) {
        model.addAttribute("autoList",
                autoService.getAll(SecurityUtil.authUserId()));
        return "auto";
    }
}
