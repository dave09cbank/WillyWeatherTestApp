/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willyweather.testproject.processor;

import com.willyweather.testproject.file.FileParser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vms
 */
public abstract class Processor {

    @Autowired
    public FileParser fileParser;

    public abstract List<String> process();

    public List<Long> roundUp() {
        List<String> valueList = process();
        List<Long> valueListDouble = new ArrayList<>();
        if (valueList != null) {
            for (String value : valueList) {
                valueListDouble.add(Math.round(Double.parseDouble(value)));
            }
        }
        return valueListDouble;

    }
}
