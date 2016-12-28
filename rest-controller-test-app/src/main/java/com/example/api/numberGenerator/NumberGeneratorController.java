package com.example.api.numberGenerator;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.JdkIdGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by msosl on 12/28/16.
 */

@RestController
public class NumberGeneratorController {
    @Autowired
    private JdkIdGenerator jdkIdGenerator;

    @Autowired
    DefaultNumberGeneratorService defaultNumberGeneratorService;

    @RequestMapping(value="/numbers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, String> getNumbers() {
        HashMap<String, String> map = new HashMap<>();
        map.put("numberOne", jdkIdGenerator.generateId().toString());

        String numberTwo = defaultNumberGeneratorService.generateNumber();
        map.put("numberTwo", numberTwo);

        return map;
    }

    @RequestMapping(value="/numbers/{number}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getNumberByNumber(@PathVariable String number) {
        return("Your number is " + number);
    }

    @RequestMapping(value="/numbers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String postNumberReturnsNumber(@RequestBody HashMap<String, String> numberMap) {
        System.out.println(numberMap.toString());
        return("Your number is " + numberMap.get("number"));
    }
}
