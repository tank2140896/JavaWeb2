 package com.javaweb.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javaweb.base.BaseController;

@Component
public class LoadTask extends BaseController implements CommandLineRunner {

    public void run(String... args) throws Exception {
        System.out.println("我被加载了，我可以调用service层的方法");
    }

}
