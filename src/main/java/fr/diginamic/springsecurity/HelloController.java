package fr.diginamic.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/public")
    public String getHelloPublic() throws Exception{
        return "Hello Spring Security!";
    }

    @GetMapping("/hello/private")
    public String getHelloPrivate() throws Exception {
        return "Hello Spring Security Private!";
    }

    @PostMapping("/hello/public")
    public String postHelloPublic() throws Exception{
        return "Hello Post!";
    }
}
