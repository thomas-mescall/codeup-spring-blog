package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String intro() {
        return "roll-dice";
    }

    public String getNum(@PathVariable int chosen, Model model) {
        int roll = diceRoll();
        model.addAttribute("roll", roll);
        model.addAttribute("chosen", chosen);
        model.addAttribute("message", getMessage(roll, chosen));
        return "result";
    }
    public int diceRoll() {
        Random random = new Random();
        return random.nextInt(6);
    }

    private String getMessage(int roll, int chosen) {
        if (roll == chosen) {
            return "Congratulations! You guessed correctly!";
        } else {
            return "Sorry! You guessed wrong. Try again!";
        }
    }


}
