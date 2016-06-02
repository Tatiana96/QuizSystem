package ru.javastudy.mvcHtml5Angular.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.mvcHtml5Angular.service.*;
import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "userJSP")
public class ProposeController {

    @RequestMapping(value = "/propose", method = RequestMethod.GET)
    public ModelAndView propose1(){
        ModelAndView model =  new ModelAndView("propose");
        String tmp[] = TestService.GetTests(null);

        model.addObject("tests", tmp);
        return model;
    }

    @RequestMapping(value = "/propose", method = RequestMethod.POST)
    public @ResponseBody  String propose(HttpServletRequest req) throws java.io.IOException {
        String test = req.getParameter("test");

        String idQ = proposeQuestionService.setProposeQuestion(test, req.getSession().getAttribute("login").toString(),req.getParameter("typeQuestion"),req.getParameter("contentQuestion"));
        JSONObject json = new JSONObject();

        for(int i=0; i< Integer.parseInt(req.getParameter("sizeAnswers").toString()); i++){
            proposeQuestionService.setProposeAnswer(idQ, req.getParameter("answers["+i+"][answer]").toString());
        }

        for(int i=0; i< Integer.parseInt(req.getParameter("sizeCorrectAnswers").toString()); i++){
            if(!proposeQuestionService.setCorrectProposeAnswer(idQ, req.getParameter("correctAnswers["+i+"][answer]").toString())) {
                json.put("flag", false);
                return json.toString();
            }
        }

        json.put("flag", true);
        return json.toString();
    }
}
