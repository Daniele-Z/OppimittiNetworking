package online.corsojava;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PDDocument document = new PDDocument();
    	PDPage newPage = new PDPage();
    	
    	try {
    		PDPageContentStream contentStream = new PDPageContentStream(document, newPage);
    		
    		contentStream.beginText();
    		contentStream.setFont(PDType1Font.COURIER, 15);
    		contentStream.newLineAtOffset(20, 700);
    		contentStream.showText("Lebro john");
    		contentStream.endText();
    		contentStream.close();
    		document.addPage(newPage);
    		document.save(new File("C:\\Users\\User\\OneDrive\\Desktop\\Hello.pdf"));
    		}catch(IOException e) {
    		e.printStackTrace();
    	}
	}

}
