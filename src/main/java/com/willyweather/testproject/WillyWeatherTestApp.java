package com.willyweather.testproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vms
 */
import com.willyweather.testproject.service.WeatherDataService;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Slf4j
@SpringBootApplication
@Controller
public class WillyWeatherTestApp implements CommandLineRunner {

    @Autowired
    private WeatherDataService dataService;

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        SpringApplication.run(WillyWeatherTestApp.class, args);
        System.out.println("STOPPING ******");
    }

    @Override
    public void run(String... arg0) throws Exception {

        if (arg0 != null && arg0[0] != null && arg0[0].startsWith("-Dfield=")) {
            String[] strSplit = arg0[0].split("=");
            System.setProperty("field", strSplit[1]);
        }

        this.dataService.processFile();
    }
}
