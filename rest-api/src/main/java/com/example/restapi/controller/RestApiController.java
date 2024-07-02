package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import com.example.restapi.model.CalculatorQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class RestApiController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello World!";
    }

    //http://localhost:8080/echo/dongho/age/25/is-man/true

    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable(name = "message") String msg,
            @PathVariable int age,
            @PathVariable boolean isMan
    ) {
        System.out.println(msg);
        System.out.println(age);
        System.out.println(isMan);


        return msg.toUpperCase();

    }

//http://localhost:8080/book?category=IT&issuedYear=2023&issued-month=01&issued_day=31

    @GetMapping(path = "/book")
    public String book(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam(name = "issued-month") String issuedMonth,
            @RequestParam(name = "issued_day") String issuedDay

    ) {
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issuedDay);

        return "book";
    }

    @GetMapping(path = "/book2")
    public String book2(
            BookQueryParam bookQueryParam
    ) {
        System.out.println(bookQueryParam);
        return bookQueryParam.toString();
    }
    //http://localhost:8080/api/complex?num1=12&num2=13

    @GetMapping(path = "complex")
    public int calculate(
            CalculatorQueryParam calculatorQueryParam
    ) {

        return calculatorQueryParam.getNum1() * calculatorQueryParam.getNum2();
    }

    @GetMapping(path = "sum")
    public int sum(
            CalculatorQueryParam calculatorQueryParam
    ) {

        return calculatorQueryParam.getNum1() + calculatorQueryParam.getNum2();
    }


    @DeleteMapping(path = {"/user/{userName}/delete",
            "/user/{userName}/del"})
    public void delete(@PathVariable String userName) {
        log.info(userName);
    }
}