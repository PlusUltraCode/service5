package com.in28minutes.learnspringframework.c1;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CalcuLator {

    private DataService dataService;

    public CalcuLator(DataService dataService){
        this.dataService=dataService;
    }

    public int calcuLatorMax(){
        return Arrays.stream(dataService.retriveData())
                .max().orElse(0);

    }

}
