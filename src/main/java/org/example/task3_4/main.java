package org.example.task3_4;

import org.example.task3_4.configs.myConfig;
import org.example.task3_4.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(myConfig.class);
        Communication communication = context.getBean("communication",Communication.class);

        System.out.println(communication.showAllUser());
        System.out.print(communication.saveUser().getBody());
        System.out.print(communication.apdateUser().getBody());
        System.out.println(communication.deleteUser().getBody());

    }
}
