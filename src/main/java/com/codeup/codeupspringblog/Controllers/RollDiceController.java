package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String intro() {
        return "roll-dice";
    }
//    public int diceRoll() {
//        Random random = new Random();
//        return random.nextInt(6);
//    }


}
