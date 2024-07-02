package com.in28minutes.learnspringframework.c1;

import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class MySQL implements DataService {

    @Override
    public int[] retriveData() {
        return new int[]{1, 2, 3, 4, 5};
    }
}
