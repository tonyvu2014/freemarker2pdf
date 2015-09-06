package com.tonyvu.freemarker2pdf;

import java.io.File;

public final class App {

	public static final String HTML_EXTENSION = "html";
	public static final String FTL_EXTENSION = "ftl";
	public static final String PDF_EXTENSION = "pdf";
	public static final String FILE_EXTENSION = "html";
	
	public static String getFullPath(String filePath, String fileName, String fileExtension) {
		return filePath +  File.separator + fileName + "." + fileExtension;
	}
}
