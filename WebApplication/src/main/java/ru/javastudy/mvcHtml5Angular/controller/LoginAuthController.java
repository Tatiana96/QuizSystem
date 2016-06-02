package ru.javastudy.mvcHtml5Angular.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvcHtml5Angular.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "userJSP")
public class LoginAuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {return new ModelAndView("login");}

    @RequestMapping(value = "/singup", method = RequestMethod.GET)
    public ModelAndView singup() { return new ModelAndView("singup");}

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody ModelAndView logout(HttpServletRequest req) {
        req.getSession().setAttribute("login", null);
        req.getSession().setAttribute("admin", null);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/LoginAuth/chekLogin", method = RequestMethod.POST)
    public @ResponseBody String LoginChek(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            if (LoginService.login(req.getParameter("login"), req.getParameter("password"))) {
                if(LoginService.IsAdmin(req.getParameter("login"))) req.getSession().setAttribute("admin", "true");
                //в регистрации не делаем проверку на админа
                req.getSession().setAttribute("login", req.getParameter("login"));
                req.getSession().setAttribute("balance", UserService.GetAboutUser(req.getSession().getAttribute("login").toString(), "balance"));
                json.put("flag", true);
            } else {
                json.put("flag", false);
            }
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/LoginAuth/chekAuth", method = RequestMethod.POST)
    public @ResponseBody String AuthChek(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            if (LoginService.singup(req.getParameter("login"), req.getParameter("password"),req.getParameter("firstname"),req.getParameter("secondname"),req.getParameter("Email"))) {
                req.getSession().setAttribute("login", req.getParameter("login"));

                req.getSession().setAttribute("balance", UserService.GetAboutUser(req.getSession().getAttribute("login").toString(), "balance"));
                json.put("flag", true);
            } else {
                json.put("flag", false);
            }
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
}
