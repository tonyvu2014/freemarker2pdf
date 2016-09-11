# freemarker2pdf
Java utilities for converting freemarker template to pdf file.

You can use this library to pass parameters to freemarker template and convert it to html or pdf. There is also helper method to insert image into an existing pdf file. The output pdf can be written to disk or as InputStream (for downloading). Please see javadoc for more details: https://rawgit.com/tonyvu2014/freemarker2pdf/master/src/main/java/com/tonyvu/freemarker2pdf/doc/index.html

There are 2 ways of converting: using iText library or flying saucer library. Generally, it is much better to use the flying saucer way since it supports broader css styles while iText support for css is limited.

Steps to create the jar:

   1. Go to your git repo, clone the project with git clone https://github.com/tonyvu2014/freemarker2pdf.git
   2. Run mvn clean package -Dmaven.test.skip=true

   The jar file will be generated under target folder
   
To build and run the unit test: 

   1. Go to the file /src/test/java/com/tonyvu/freemarker2pdf/FreeMarker2PdfTest.java and update the templatePath and pdfPath to reflect setup in your environment.
   2. Run mvn clean package. If the tests fail, errors will occur. (Or you can run mvn test)

You can copy the generated freemarker2pdf-&lt;version&gt;.jar to your project to use directly or you can install it to local repository. To install the jar your local repository, issue this command from the project root folder:

   mvn install:install-file -Dfile=target/freemarker2pdf-&lt;version&gt;.jar -DpomFile=pom.xml
   
After that, to use the library in your project, just add the dependency to pom.xml like below:
   
   ```xml
   <dependency>
      <groupId>com.tonyvu</groupId>
      <artifactid>freemarker2pdf</artifactId>
      <version>....</version>
   </dependency>
   ```
   
The HtmlUtils class from the library provides static method to convert a freemarker template to html.
The PdfUtils class provides various methods to convert html or freemarker template to pdf file.
   
Please see the javadoc to find out how to use the library:
https://rawgit.com/tonyvu2014/freemarker2pdf/master/src/main/java/com/tonyvu/freemarker2pdf/doc/index.html
   

