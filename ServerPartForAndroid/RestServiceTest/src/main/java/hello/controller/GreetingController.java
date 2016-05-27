package hello.controller;

import hello.entity.User;
import hello.service.LogicService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tatiana on 14.05.2016.
 */
@RestController
public class GreetingController {

    User UserInstance;

    //int Authorisation = 0;
    // 0 - значение по умолчанию
    // 1 - успешная авторизация
    // 2 - ошибка авторизации (пользователь не существует)
    // 3 - ошибка в пароле

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*@RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }*/

    /*@RequestMapping(value="/greeting", method= RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="Quiz") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    } // правильный Get-запрос*/

    /*@RequestMapping(value="/greeting", method= RequestMethod.POST)
    public Greeting writePassLogin(Greeting greeting) {
        System.out.println((greeting.getId()));
        System.out.println((greeting.getContent()));
        return new Greeting(counter.incrementAndGet(),
                String.format(template, "POST"));
    }*/

   /* @ModelAttribute("greeting")
    public Greeting newGreeting() {
        return new Greeting(counter.incrementAndGet(), "POST");
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String addPayment(@ModelAttribute("greeting") Greeting greeting) {
        System.out.println("Data:\n");
        System.out.println((greeting.getId()));
        System.out.println((greeting.getContent()));
        return "redirect:greeting";
    }*/

    //MultiValueMap<String, Greeting> mapForData;
    //String UserLogin;
    //MultiValueMap<String, Object> mapForData;



    //@RequestMapping(value = "/greeting", method = RequestMethod.POST)
    //@ResponseBody
    /*public MultiValueMap<String, String> updateGr(@PathVariable() MultiValueMap<String, String> mapForData) {

        UserLogin = mapForData.get("login").toString();
        System.out.println("Login: "+UserLogin);
        System.out.println("\nData:\n");
        return mapForData;
    }*/

    /*public ResponseEntity<MultiValueMap<String, Object>> createPerson(@RequestBody Greeting greeting) {

        Object v = 5;
        mapForData.add("login", v);

        return ResponseEntity.ok(mapForData);

    }*/

    @RequestMapping(value = "/UserPost", method = RequestMethod.POST)
    public String GetUserData(@RequestBody Map<String,String> body) {
        System.out.println("\nReceived POST request:" + body.get("login").toString() );
        System.out.print(" " + body.get("password").toString() );
        //Authorisation = new UserService().SelectUser(body.get("login").toString()); // авторизуем пользователя
        UserInstance = new LogicService().Authorize(body.get("login").toString(), body.get("password").toString());
        System.out.print("\nAuthorisation: " + UserInstance.Authorisation);
        return null;
        //return null;
    }

    @RequestMapping(value="/UserGet", method= RequestMethod.GET)
    /*public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="Quiz") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));*/
        public User AuthResult () {
                return UserInstance;
    }

   /* @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String logs(@RequestParam("json") String json) {
        System.out.println("\nData:\n");
        System.out.println(("\nReceived POST request:" + json));

        return null;
    }
    */

}
