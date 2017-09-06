package com.willyweather.testproject.processor.impl;

import com.willyweather.testproject.processor.Processor;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("#{systemProperties['field']!=null && systemProperties['field'].equals('SNDP')}")
public class SndpProcessorImpl extends Processor {

	@Override
	public List<String> process() {
		return fileParser.getColumnValuesForHeaderFromFile();
	}

}