package com.tonyvu.freemarker2pdf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlUtils {
		
	/**
	 * Generate html from a freemarker template
	 * 
	 * @param templatePath path to the freemarker template
	 * @param templateName freemarker template name 
	 * @param root parameter of key to value
	 * @param htmlFilePath path to the generated html file
	 * @param htmlFileName generated html file name
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String generateHtmlFromTemplate(String templatePath, String templateName, Map root, String htmlFilePath, String htmlFileName) throws IOException, TemplateException {
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(templatePath));
		Template template = config.getTemplate(templateName + "." + App.FTL_EXTENSION);

		String htmlFullPath = App.getFullPath(htmlFilePath, htmlFileName, App.HTML_EXTENSION);
		Writer file = new FileWriter (new File(htmlFullPath));
		template.process(root, file);
		file.flush();
		file.close();
         
                return htmlFullPath;
	}
}
