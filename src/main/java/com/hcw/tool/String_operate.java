package com.hcw.tool;

public class String_operate {

	public String splitByR(String string) {
		
		String result = null;
		String[] a = string.split("\\/");
		result = a[a.length-1];
		return result;
	}
	public String splitByF(String string) {
		
		String result = null;
		String[] a = string.split("\\\\");
		result = a[a.length-1];
		return result;
	}
}
