package com.in28minutes.learnspringframework.c1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDv implements DataService {
    @Override
    public int[] retriveData() {
        return new int[]{11, 22, 33, 44, 55};
    }
}
