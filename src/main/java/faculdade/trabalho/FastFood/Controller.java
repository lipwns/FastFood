package faculdade.trabalho.FastFood;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/primeiramensagem")
    public String primeiraMensagem(){
        return "Primeira mensagem";
    }




}
