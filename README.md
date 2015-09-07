# freemarker2pdf
Java utilities for converting freemarker template to pdf file

Steps to create the jar:

   1. Go to your git repo, clone the project with git clone https://github.com/tonyvu2014/freemarker2pdf.git
   2. Run mvn clean package -Dmaven.test.skip=true

   The jar file will be generated under target folder
   
To build and run the unit test: 

   1. Go to the file /src/test/java/com/tonyvu/freemarker2pdf/FreeMarker2PdfTest.java and update the templatePath and pdfPath to reflect setup in your environment.
   Similarly, make the change to /src/test/java/com/tonyvu/freemarker2pdf/FreeMarkerToPdfTest.java
   2. Run mvn clean package. If the tests fail, errors will occur.

You can copy the generated freemarker2pdf-<version>.jar to your project to use directly or you can install its to local repository. To install the jar your local repository, issue this command from the project root folder:

   mvn install:install-file -Dfile=target/freemarker2pdf-<version>.jar -DpomFile=pom.xml
   
After that, to use the library in your project, just the the dependency to pom.xml like below:

   <dependency>
      <groupId>com.tonyvu</groupId>
      <artifactid>freemarker2pdf</artifactId>
      <version>....</version>
   </dependency>


