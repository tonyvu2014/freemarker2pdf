package com.tonyvu.freemarker2pdf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.lowagie.text.DocumentException;

import freemarker.template.TemplateException;

/**
 * Unit test for simple App.
 */
public class FreeMarker2PdfTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FreeMarker2PdfTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FreeMarker2PdfTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testFreeMarker2Pdf()
    {
    	String templatePath = ".";
    	String templateName = "helloworld";
    	String pdfPath =".";
    	String pdfName = "helloworld";
    	Map<String, Object> map =  new HashMap<String, Object>();
    	map.put("name", "world");
    	try {
		PdfUtils.convert2Pdf(templatePath, templateName, map, pdfPath, pdfName);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (TemplateException e) {
		e.printStackTrace();
	} catch (DocumentException e) {
		e.printStackTrace();
	}
    	
    	File pdfFile = new File(App.getFullPath(pdfPath, pdfName, App.PDF_EXTENSION));
        assertTrue(pdfFile.exists());
    }
}
