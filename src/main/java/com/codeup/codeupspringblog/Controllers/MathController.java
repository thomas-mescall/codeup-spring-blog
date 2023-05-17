package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int num1, @PathVariable int num2){
        return num1 + num2;
    }
    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public int sub(@PathVariable int num1, @PathVariable int num2) {
        return num2 - num1;
    }
    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public int mul(@PathVariable int num1, @PathVariable int num2) {
        return num1 * num2;
    }
    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public int div(@PathVariable int num1, @PathVariable int num2){
        return num1 / num2;
    }
}
