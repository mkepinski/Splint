package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.dao.UserDao;
import project.data.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FrontController {

    @GetMapping("/")
    public String showHomepage(HttpServletRequest request,
                               HttpSession session) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("user_logged".equals(cookie.getName())) {
                UserDao userDao = new UserDao();
                String login = cookie.getValue();
                User user = userDao.getUserByLogin(login);
                session.setAttribute("user", user);
                session.setAttribute("enable", 1);
            }
        }

        return "home";
    }
}
