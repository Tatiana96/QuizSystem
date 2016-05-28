package ru.javastudy.mvcHtml5Angular.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvcHtml5Angular.model.*;
import ru.javastudy.mvcHtml5Angular.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "userJSP")
public class AdminController {

    @RequestMapping(value = "/NewCategory", method = RequestMethod.GET)
    public ModelAndView NewCategory() {return new ModelAndView("NewCategory");}

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public @ResponseBody
    String addCategory(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            if(TestService.addCategory(req.getParameter("new")))   json.put("flag", true);
            else json.put("flag", false);
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/AllQuestion", method = RequestMethod.GET)
    public  @ResponseBody
    ModelAndView AllQuestions(HttpServletRequest req)throws java.io.IOException {
        ModelAndView model = new ModelAndView("AllQuestion");
        Question []q;
        q = QuestionAnswerService.GetQuestions(null);
        model.addObject("questions",q);

        return model;
    }

    @RequestMapping(value = "/NewTest", method = RequestMethod.GET)
    public  @ResponseBody
    ModelAndView NewTest(HttpServletRequest req)throws java.io.IOException {
        ModelAndView model = new ModelAndView("NewTest");
        model.addObject("category",req.getParameter("category").toString());
        model.addObject("questions",QuestionAnswerService.GetQuestionsCategory(req.getParameter("category").toString()));
        return model;
    }

    @RequestMapping(value = "/AddTest", method = RequestMethod.POST)
    public @ResponseBody
    String addTest(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            String questions[] = new String[Integer.parseInt(req.getParameter("size"))];
            for(int i=0; i< Integer.parseInt(req.getParameter("size")); i++){
                questions[i] =  req.getParameter("questions["+i+"][content]").toString();
            }
            TestService.AddTest(req.getParameter("name"), req.getParameter("category"), req.getParameter("about"), req.getParameter("cost"), questions);
            json.put("flag", true);
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/AllTransactions", method = RequestMethod.GET)
    public ModelAndView AllTransactions() {
        ModelAndView model = new ModelAndView("AllTransactions");
        Transaction []tr;
        tr = TransactionService.GetTransactionsProcessing();
        model.addObject("transactions", tr);
        return model;
    }

    @RequestMapping(value = "/AddTransactions", method = RequestMethod.POST)
    public @ResponseBody
    String AddTransactions(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            TransactionService.AddTransactions(req.getParameter("id"));
            json.put("flag", true);
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/NewQuestion", method = RequestMethod.GET)
    public ModelAndView singup() {
        ModelAndView model = new ModelAndView("NewQuestion");
        UserQuestion []q;
        q = proposeQuestionService.getQuestions();
        model.addObject("questions",q );
        return model;
    }

    @RequestMapping(value = "/DeleteQuestion", method = RequestMethod.POST)
    public @ResponseBody
    String DeleteQuestion(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            proposeQuestionService.DeleteQuestion(req.getParameter("id"));
            json.put("flag", true);
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/AddQuestion", method = RequestMethod.POST)
    public @ResponseBody
    String AddQuestion(HttpServletRequest req){
        try {
            JSONObject json = new JSONObject();
            proposeQuestionService.AddQuestion(req.getParameter("id"));
            json.put("flag", true);
            return json.toString();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

}
