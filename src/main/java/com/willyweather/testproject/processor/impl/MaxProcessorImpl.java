package com.willyweather.testproject.processor.impl;

import com.willyweather.testproject.processor.Processor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("#{systemProperties['field']!=null && systemProperties['field'].equals('MAX')}")
public class MaxProcessorImpl extends Processor {

	@Override
	public List<String> process() {
		List<String> valueList = new ArrayList<String>();
		for (String value : fileParser.getColumnValuesForHeaderFromFile()) {
			valueList.add(value.replaceAll("\\*", ""));
		}
		return valueList;
	}

}