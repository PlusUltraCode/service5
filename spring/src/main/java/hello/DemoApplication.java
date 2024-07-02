package hello;

import java.time.LocalTime;

public class DemoApplication {
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: ");



        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}
