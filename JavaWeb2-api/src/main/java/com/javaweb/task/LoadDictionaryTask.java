 package com.javaweb.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseSystemMemory;

@Component
public class LoadDictionaryTask extends BaseController implements CommandLineRunner {

    public void run(String... args) throws Exception {
        BaseSystemMemory.dictionaryList = dictionaryService.selectAll();
    }

}
