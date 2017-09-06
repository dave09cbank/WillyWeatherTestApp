/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willyweather.testproject.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vms
 */
@Getter
@Setter
@AllArgsConstructor
public class Data {
    private String STN;
	private String WBAN;
	private String YEARMODA;
	private String TEMP;
	private String DEWP;
	private String SLP;
	private String STP;
	private String VISIB;
	private String WDSP;
	private String MXSPD;
	private String GUST;
	private String MAX;
	private String MIN;
	private String PRCP;
	private String SNDP;
	private String FRSHTT;
}
