package ru.javastudy.mvcHtml5Angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvcHtml5Angular.service.*;
import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "userJSP")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody ModelAndView main(HttpServletRequest req) {
        String []users=UserService.GetTop();
        req.getSession().setAttribute("users",users);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about() { return new ModelAndView("about");}

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {return new ModelAndView("edit");}

    @RequestMapping(value = "/AllStatistica", method = RequestMethod.GET)
    public ModelAndView AllStatictica() {
        ModelAndView model =  new ModelAndView("AllStatistica");

        model.addObject("TotalCategories", StatisticaService.Count("сategory"));
        model.addObject("TotalTests", StatisticaService.Count("test"));
        model.addObject("TotalUsers", StatisticaService.Count("user"));
        return model;
    }
}