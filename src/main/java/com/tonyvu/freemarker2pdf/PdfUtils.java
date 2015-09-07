package com.tonyvu.freemarker2pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import freemarker.template.TemplateException;

public class PdfUtils {
	
	/**
	 * Generate pdf file from template
	 * 
	 * @param templatePath path to the freemarker template
	 * @param templateName freemarker template name
	 * @param map the parameter map of key to value
	 * @param filePath path to the generated pdf file
	 * @param fileName generated pdf file name 
	 * @return the generated pdf file
	 * @throws Exception
	 */
	public static File writeToFile(String templatePath, String templateName, Map<String, String> map, String filePath, String fileName) throws Exception {
		InputStream pdfTemplateInputStream = new FileInputStream(App.getFullPath(templatePath, templateName, App.FTL_EXTENSION));
		
		PdfReader pdfTemplate = new PdfReader(pdfTemplateInputStream);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, out);
		stamper.setFormFlattening(true);
		
		for (String key: map.keySet()) {
			stamper.getAcroFields().setField(key, map.get(key));
		}
		
		stamper.close();
		pdfTemplate.close();
		
		File file = new File(App.getFullPath(filePath, fileName, App.PDF_EXTENSION));
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(out.toByteArray());
		fos.close();
		
		return file;
	}
	
	
	/**
	 * Generate input stream based on freemarker template
	 *
	 * @param templatePath path to freemarker template
	 * @param templateName freemarker template name
	 * @param map parameter map of key to value
	 * @return the generated pdf as stream
	 * @throws Exception
	 */
	public static InputStream writeInputStream(String templatePath, String templateName, Map<String, String> map) throws Exception {
		InputStream pdfTemplateInputStream = new FileInputStream(App.getFullPath(templatePath, templateName, App.FTL_EXTENSION));

		PdfReader pdfTemplate = new PdfReader(pdfTemplateInputStream);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, out);
		stamper.setFormFlattening(true);
		
		for (String key: map.keySet()) {
			stamper.getAcroFields().setField(key, map.get(key));
		}
		
		stamper.close();
		pdfTemplate.close();
				
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	/**
	 * Insert image to a pdf file at a coordinate
	 * 
	 * @param pdfFile full path to pdf file
	 * @param imageFile full path to image file
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public static void insertImage(String pdfFile, String imageFile, float x, float y) {
		Document document = new Document();

	        try {
	            PdfWriter.getInstance(document,
	                    new FileOutputStream(pdfFile));
	            document.open();
	
	            Image image = Image.getInstance(imageFile);
	            image.setAbsolutePosition(x, y);
	            document.add(image);
	
	            document.close();
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	}
	
	/**
	 * Convert html document to pdf document using iText
	 * 
	 * @param htmlPath path to the html file
	 * @param htmlName html file name
	 * @param pdfPath path to the generated pdf file
	 * @param pdfName geherated pdf file name
	 * @throws FileNotFoundException 
	 * @throws DocumentException 
	 * @throws com.itextpdf.text.DocumentException 
	 */
	public static void convertHtmlToPdf(String htmlPath, String htmlName, String pdfPath, String pdfName) throws FileNotFoundException, DocumentException, com.itextpdf.text.DocumentException {
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
	        com.itextpdf.text.pdf.PdfWriter writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(App.getFullPath(pdfPath, pdfName, App.PDF_EXTENSION)));
	        document.open();
	        try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				        new FileInputStream(App.getFullPath(htmlPath, htmlName, App.HTML_EXTENSION)));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        document.close();
	}
	
	/**
	 * Convert html document to pdf document using flying saucer
	 * 
	 * @param htmlPath path to the html file
	 * @param htmlName html file name
	 * @param pdfPath path to the generated pdf file
	 * @param pdfName generated pdf file name
	 * @throws FileNotFoundException 
	 * @throws DocumentException 
	 * @throws com.lowagie.text.DocumentException 
	 */
	public static void convertHtml2Pdf(String htmlPath, String htmlName, String pdfPath, String pdfName) throws FileNotFoundException, IOException, com.lowagie.text.DocumentException {
		/** Creating an instance of iText renderer
	         *  which will be used to generate the pdf from the
	         *  html document.
	         */
	        final ITextRenderer iTextRenderer = new ITextRenderer();
	
	        /**
	         * Setting the document as the url value passed.
	         * This means that the iText renderer
	         * has to parse this html document to generate the pdf.
	         */
	        String htmlFile = App.getFullPath(htmlPath, htmlName, App.HTML_EXTENSION);
	        iTextRenderer.setDocument(new File(App.getFullPath(htmlPath, htmlName, App.HTML_EXTENSION)));
	        iTextRenderer.layout();
	
	        /** The generated pdf will be written to the file. */
	        final FileOutputStream fileOutputStream =
	                    new FileOutputStream(new File(App.getFullPath(pdfPath, pdfName, App.PDF_EXTENSION)));
	
	        /** Creating the pdf */
	        iTextRenderer.createPDF(fileOutputStream);
	        fileOutputStream.close();
	}
	
	/**
	 * Generate pdf from freemarker template using iText
	 * 
	 * @param templatePath freemarker template path
	 * @param templateName freemarker template name
	 * @param map parameter map
	 * @param pdfPath generated pdf file path
	 * @param pdfName generated pdf file name
	 * @throws IOException
	 * @throws TemplateException
	 * @throws DocumentException
	 * @throws com.itextpdf.text.DocumentException
	 */
	public static void convertToPdf(String templatePath, String templateName, Map map, String pdfPath, String pdfName) throws IOException, TemplateException, DocumentException, com.itextpdf.text.DocumentException {
		String htmlFullPath = HtmlUtils.generateHtmlFromTemplate(templatePath, templateName, map, pdfPath, pdfName);
		
	        convertHtmlToPdf(pdfPath, pdfName, pdfPath, pdfName);	
	        
	        File htmlFile =  new File(htmlFullPath);
	        if (htmlFile.exists()) {
	        	htmlFile.delete();
	        }
	}
	
	/**
	 * Generate pdf from freemarker template using flying saucer
	 * 
	 * @param templatePath freemarker template path
	 * @param templateName freemarker template name
	 * @param map parameter map
	 * @param pdfPath generated pdf file path
	 * @param pdfName generated pdf file name
	 * @throws IOException
	 * @throws TemplateException
	 * @throws DocumentException
	 */
	public static void convert2Pdf(String templatePath, String templateName, Map map, String pdfPath, String pdfName) throws IOException, TemplateException, DocumentException  {
		String htmlFullPath = HtmlUtils.generateHtmlFromTemplate(templatePath, templateName, map, pdfPath, pdfName);
		
        	convertHtml2Pdf(pdfPath, pdfName, pdfPath, pdfName);	
        
	        File htmlFile =  new File(htmlFullPath);
	        if (htmlFile.exists()) {
	        	htmlFile.delete();
	        }
	}
}
