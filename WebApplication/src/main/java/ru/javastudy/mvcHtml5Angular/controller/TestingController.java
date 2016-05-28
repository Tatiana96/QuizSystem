package ru.javastudy.mvcHtml5Angular.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvcHtml5Angular.model.Question;
import ru.javastudy.mvcHtml5Angular.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "userJSP")
public class TestingController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody ModelAndView test(HttpServletRequest req)throws java.io.IOException {

        ModelAndView model = new ModelAndView("test");

        String name = OtherService.decodeGetParameter(req.getParameter("nameTest").toString());
        model.addObject("nameTest", name);
        model.addObject("category", OtherService.decodeGetParameter(req.getParameter("category").toString()));
        model.addObject("cost", TestService.GetAboutTest(name, "cost"));
        model.addObject("about", TestService.GetAboutTest(name, "about"));
        model.addObject("total", TestService.GetAboutTest(name, "total"));
        model.addObject("best", TestService.GetAboutTest(name, "best"));

        String comment[] = TestService.GetComments(name);
        model.addObject("comments", comment);
        req.getSession().setAttribute("costTest",TestService.GetAboutTest(name, "cost"));

        return model;
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public @ResponseBody ModelAndView tests(HttpServletRequest req) throws java.io.IOException {
        ModelAndView model = new ModelAndView("tests");
        String category = OtherService.decodeGetParameter(req.getParameter("category").toString());
        model.addObject("nameCat",  category);
        model.addObject("tests", TestService.GetTests(category));
        return model;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody ModelAndView categories(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("categories");
        model.addObject("categories", TestService.GetCategories());
        return model;
    }

    @RequestMapping(value = "/solve", method = RequestMethod.GET)
    public @ResponseBody ModelAndView solve(HttpServletRequest req) throws java.io.IOException {
        ModelAndView model = new ModelAndView("solve");

        String nameTest = OtherService.decodeGetParameter(req.getParameter("nameTest").toString());
        Question []q;
        q = QuestionAnswerService.GetQuestions(nameTest);

        model.addObject("questions", q);
        model.addObject("nameTest", nameTest);
        String idTesting = TestService.SetTesting(req.getSession().getAttribute("login").toString(), nameTest);
        req.getSession().setAttribute("idTesting", idTesting);

        return model;
    }

    @RequestMapping(value = "/Answer/chekAnswer", method = RequestMethod.POST)
    public @ResponseBody String questionAns(HttpServletRequest req) throws java.io.IOException {

        for(int i=0; i< Integer.parseInt(req.getParameter("size")); i++){
            String id = OtherService.decodeGetParameter(req.getParameter("question["+i+"][id]").toString());
            String answer =  req.getParameter("question["+i+"][answer]").toString();
            String nameTest = req.getParameter("nameTest").toString();
            QuestionAnswerService.SetUserResponse(req.getSession().getAttribute("idTesting").toString(),id ,answer, req.getSession().getAttribute("login").toString());
        }

        JSONObject json = new JSONObject();
        json.put("flag", true);
        return json.toString();
    }

    @RequestMapping(value = "/testFinish", method = RequestMethod.GET)
    public ModelAndView testFinish(HttpServletRequest req) throws java.io.IOException{
        ModelAndView model = new ModelAndView("testFinish");
        String nameTest = OtherService.decodeGetParameter(req.getParameter("nameTest").toString());
        String idTesting = req.getSession().getAttribute("idTesting").toString();
        String UserResult = UserService.GetUserResult(idTesting, req.getSession().getAttribute("login").toString());
        String totalResult =  UserService.GetTotalResult(idTesting, req.getSession().getAttribute("login").toString());
        model.addObject("nameTest", nameTest);
        model.addObject("UserResult", UserResult);
        model.addObject("total",totalResult);
        model.addObject("Msg",OtherService.GetMessageResult(Integer.parseInt(UserResult), Integer.parseInt(totalResult)));

        TestService.SetFinishTesting(idTesting, UserResult);
        req.getSession().setAttribute("idTesting", null);
        return model;
    }

    @RequestMapping(value = "/addFunds", method = RequestMethod.GET)
    public @ResponseBody ModelAndView Funds(HttpServletRequest req) throws java.io.IOException {
        ModelAndView model = new ModelAndView("addFunds");
        return model;
    }

    @RequestMapping(value = "/addFunds/add", method = RequestMethod.POST)
    public @ResponseBody String FundsPOST(HttpServletRequest req) throws java.io.IOException {
        TransactionService.add(req.getSession().getAttribute("login").toString(), OtherService.decodeGetParameter(req.getParameter("summa")));

        JSONObject json = new JSONObject();
        json.put("flag", true);

        return json.toString();
    }

    @RequestMapping(value = "/CommentAdd", method = RequestMethod.POST)
    public @ResponseBody String CommentAdd(HttpServletRequest req) throws java.io.IOException {
        TestService.CommentAdd(req.getParameter("test"), req.getParameter("content"));

        JSONObject json = new JSONObject();
        json.put("flag", true);

        return json.toString();
    }

    @RequestMapping(value = "/MultyTest", method = RequestMethod.GET)
    public ModelAndView MultyTest() {
        ModelAndView model = new ModelAndView("MultyTest");
        model.addObject("categories", TestService.GetCategories());
        return model;
    }

    @RequestMapping(value = "/GetKod", method = RequestMethod.POST)
    public @ResponseBody
    String GetKod(HttpServletRequest req) throws java.io.IOException {
        JSONObject json = new JSONObject();
        json.put("msg", TestService.GetKode(req.getParameter("name").toString()));
        json.put("flag", true);

        return json.toString();
    }

    @RequestMapping(value = "/solveMulty", method = RequestMethod.GET)
    public @ResponseBody ModelAndView solveMulty(HttpServletRequest req) throws java.io.IOException {
        ModelAndView model = new ModelAndView("solve");

        String questions = OtherService.decodeGetParameter(req.getParameter("category"));
        String[] tmp = questions.split(",");
        Question []q;
        q = QuestionAnswerService.GetMultyQuestions(tmp, tmp.length);

        model.addObject("nameTest", "Мультитест");
        model.addObject("questions",q);
        String idTesting = TestService.SetMultyTesting(req.getSession().getAttribute("login").toString());
        req.getSession().setAttribute("idTesting", idTesting);

        return model;
    }



}