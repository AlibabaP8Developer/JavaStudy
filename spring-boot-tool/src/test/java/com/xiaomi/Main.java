package com.xiaomi;

import com.xiaomi.util.PdfToWordUtil;

import java.util.Map;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
//		Map<String, Object> pdftoword = new PdfToWordUtil().pdftoword("/Users/lizhenghang/Desktop/test1.pdf");
//		Map<String, Object> pdftoword = new PdfToWordUtil().pdftoword("/Users/lizhenghang/Desktop/Swift 5.1 官方教程.pdf");
//		System.out.println(pdftoword);
		int win = System.getProperty("os.name").toLowerCase().indexOf("win");
		System.out.println(win);
		String property = System.getProperty("os.name");
		System.out.println(property);
	}
}