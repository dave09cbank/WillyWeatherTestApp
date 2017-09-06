/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willyweather.testproject.service.impl;

import com.willyweather.testproject.processor.Processor;
import com.willyweather.testproject.service.WeatherDataService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author vms
 */
@Component
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired(required = false)
    private Processor processor;

    @Autowired
    ApplicationContext applicationContext;

    public void printBeans() {
        List<String> beans = Arrays.asList(applicationContext.getBeanDefinitionNames());
        for (int i = 0; i < beans.size() - 1; i++) {
            System.out.println(beans.get(i));
        }

        System.out.println("");
        System.out.println("" + System.getProperty("field"));
        System.out.println("");
    }

    /**
     * Processes file and returns results
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    @Override
    public void processFile() throws FileNotFoundException, IOException {

        System.out.println("Testing 1.1");
        printBeans();

        if (processor != null) {
            System.out.println(this.processor.getClass());
            List<Long> values = processor.roundUp();

            String strDisplay = "";
            for (int i = 0; i < values.size() - 1; i++) {
                if (StringUtils.isEmpty(strDisplay)) {
                    strDisplay = "" + values.get(i);
                } else {
                    strDisplay = strDisplay + "," + values.get(i);
                }
            }

            if (!StringUtils.isEmpty(strDisplay)) {
                System.out.println(strDisplay);
            } else {
                System.out.println("Testing");
            }
        }
    }

}
