package com.xiaomi;

import com.xiaomi.util.PdfToWordUtil;

import java.util.Properties;

public class Main {
	public static void main(String[] args) {
//		String res = new PdfToWordUtil().pdftoword("/Users/lizhenghang/Desktop/test1.pdf");
//		System.out.println(res);
		int win = System.getProperty("os.name").toLowerCase().indexOf("win");
		System.out.println(win);
		String property = System.getProperty("os.name");
		System.out.println(property);
	}
}