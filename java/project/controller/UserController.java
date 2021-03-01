package project.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dao.UserDao;
import project.data.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

@RequestMapping("/account")
@Controller
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "new_account";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processLoginInfo(@ModelAttribute @Valid User user,
                                   BindingResult result,
                                   HttpSession session) {

        if(result.hasErrors()) {
            return "new_account";
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setEnable(1);
        session.setAttribute("enable", 1);
        userDao.saveUser(user);
        session.setAttribute("user", user);
        return "home";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        @RequestParam(required = false) boolean stayLogged,
                        HttpServletResponse response,
                        HttpSession session) {

        User user = userDao.getUserByLogin(login);
        if(BCrypt.checkpw(password, user.getPassword())) {

            user.setEnable(1);
            user.setStayLogged(stayLogged);
            userDao.updateUser(user);

            if(stayLogged) {
                Cookie cookie = new Cookie("user_logged", user.getLogin());
                cookie.setMaxAge(2_592_000);
                response.addCookie(cookie);
            }

            session.setAttribute("user", user);
            session.setAttribute("enable", 1);
        }
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogoutValidationForm() {
        return "logout_validate";
    }

    @RequestMapping(value = "/logout/confirmed", method = RequestMethod.GET)
    public String logout(HttpSession session,
                         HttpServletRequest request,
                         HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("user_logged".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        User user = (User) session.getAttribute("user");
        user.setStayLogged(false);
        user.setEnable(0);
        userDao.updateUser(user);

        session.invalidate();

        return "redirect:http://localhost:8080/";
    }
}
