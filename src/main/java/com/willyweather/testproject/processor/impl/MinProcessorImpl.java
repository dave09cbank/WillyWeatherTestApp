package com.willyweather.testproject.processor.impl;

import com.willyweather.testproject.processor.Processor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("#{systemProperties['field']!=null && systemProperties['field'].equals('MIN')}")
public class MinProcessorImpl extends Processor {

	@Override
	public List<String> process() {
		List<String> valueList = new ArrayList<>();
		for (String value : fileParser.getColumnValuesForHeaderFromFile()) {
			valueList.add(value.replaceAll("\\*", ""));
		}
		return valueList;
	}

}