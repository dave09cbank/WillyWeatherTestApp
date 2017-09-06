/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willyweather.testproject.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 *
 * @author vms
 */
public class FileParser {
    
	@Autowired
	private FileUnZipper unzip;

	@Value("#{systemProperties['field']}")
	private String field;

	public InputStream getInputFile(String fileName) throws IOException {
		Resource resource = new ClassPathResource(fileName);
		return resource.getInputStream();
	}

	public String readLine(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getInputFile(fileName)));
		return bufferedReader.readLine();
	}

	public List<String> tokenizeByWhiteSpace(String input) {
		return Arrays.asList(input.split("\\s+"));
	}

	public List<String> readLinesAsListFromFile(String fileName) {
		BufferedReader bufferedReader;
		List<String> fileLines = new ArrayList<>();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(getInputFile(fileName)));
			String line = null;
			do {
				line = bufferedReader.readLine();
				if (StringUtils.isNotEmpty(line)) {
					fileLines.add(line);
				}
			} while (line != null);
		} catch (IOException e) {
			throw new RuntimeException("error reading lines from file : " + Arrays.toString(e.getStackTrace()));
		}
		return fileLines;
	}

	public List<String> readLinesAsListFromBufferReader(BufferedReader bufferedReader) {

		List<String> fileLines = new ArrayList<>();
		try {
			String line = "";
			do {
				line = bufferedReader.readLine();
				if (StringUtils.isNotEmpty(line)) {
					fileLines.add(line);
				}
			} while (line != null);
		} catch (IOException e) {
			throw new RuntimeException("error reading lines from file : " + Arrays.toString(e.getStackTrace()));
		}
		return fileLines;
	}

	public int getIndexPostionForStringInLine(String line, String searchString) {
		return StringUtils.indexOfIgnoreCase(line, searchString);
	}

	public String getRowValueForHeader(String header, String headerLine, String rowLine) {
		int headerStartIndex = StringUtils.indexOfIgnoreCase(headerLine, header);
		String headerValue = getWordAtIndex(headerStartIndex, headerLine);
		String rowValue = null;
		if (StringUtils.equals(header, headerValue)) {
			rowValue = getWordAtIndex(headerStartIndex, rowLine);
			if (StringUtils.isEmpty(rowValue)) {
				int headerLastIndex = headerStartIndex + (headerValue.length() - 1);
				rowValue = getWordAtIndex(headerLastIndex, rowLine);
			}
		}
		return rowValue;
	}

	public String getWordAtIndex(int index, String string) {
		String word = null;
		if (index > -1) {
			int lowerLimit = index;
			int upperLimit = index;
			while (string.charAt(lowerLimit) != ' ' && lowerLimit > 0) {
				lowerLimit--;
			}
			while (string.charAt(upperLimit) != ' ' && upperLimit < string.length()) {
				upperLimit++;
			}
			word = StringUtils.strip(string.substring(lowerLimit, upperLimit));
		}
		return word;
	}

	public List<String> buildColumnForHeaderFromRowValuesList(String header, List<String> fileLines) {
		String headerLine = fileLines.get(0);
		if (StringUtils.isNotEmpty(header) && StringUtils.contains(headerLine, header)) {
			Map<String, List<String>> rowHeaderValuesMap = new HashMap<>();
			List<String> rowValues = new ArrayList<>();
			for (String line : fileLines.subList(1, fileLines.size())) {
				String rowValue = getRowValueForHeader(header, headerLine, line);
				rowValues.add(rowValue);
			}
			rowHeaderValuesMap.put(header, rowValues);
			return rowHeaderValuesMap.get(header);
		}
		return null;
	}

	public List<String> getColumnValuesForHeaderFromFile() {

		List<String> rowValues = null;
		try {
			rowValues = readLinesAsListFromBufferReader(unzip.unzipAndReadFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buildColumnForHeaderFromRowValuesList(field, rowValues);
	}
}
