package ru.javastudy.mvcHtml5Angular.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvcHtml5Angular.model.Transaction;
import ru.javastudy.mvcHtml5Angular.service.EditService;
import ru.javastudy.mvcHtml5Angular.service.TransactionService;
import ru.javastudy.mvcHtml5Angular.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "userJSP")
public class UserController {
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView transaction(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("transaction");
        Transaction[]tr;
        tr = TransactionService.GetTransactions(req.getSession().getAttribute("login").toString());
        model.addObject("transactions", tr);
        return model;
    }

    @RequestMapping(value = "/edit/dataEdit", method = RequestMethod.POST)
    public @ResponseBody
    String dataEdit(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            String login = req.getSession().getAttribute("login").toString();
            if(EditService.CheckPassword(login, req.getParameter("lastpassword"))){
                if(req.getParameter("firstname")!="" )
                    EditService.edit(login, "firstname", req.getParameter("firstname"));
                if(req.getParameter("secondname")!="" )
                    EditService.edit(login, "secondname", req.getParameter("secondname"));
                if(req.getParameter("email")!="" )
                    EditService.edit(login, "email", req.getParameter("email"));
                if(req.getParameter("newpassword")!="" )//если новый пароль не введен то нет смысла изменять
                    EditService.editPassword(login, req.getParameter("lastpassword"), req.getParameter("newpassword"));
                json.put("flag", true);
            }else {
                json.put("flag", false);
            }
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/statistica", method = RequestMethod.GET)
    public @ResponseBody ModelAndView statictica(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("statistica");

        model.addObject("bestAnswers", UserService.GetBestAnswer(req.getSession().getAttribute("login").toString()));
        model.addObject("badAnswers", UserService.GetBadAnswer(req.getSession().getAttribute("login").toString()));

        return model;
    }


}
