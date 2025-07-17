package sea.scplus.consumer.common.mail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.LinkProvider;

import sea.scplus.consumer.common.util.TimezoneDateTimeUtil;

@Service
public class PDFSave {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PDFSave.class);
 
    @SuppressWarnings("resource")
	protected byte[] buildPdfDocument(String strHTMLContent, String cpa_ticket_id, String PDF_PATH) throws Exception {
        
    	// IE workaround: write into byte array first.
    	FileOutputStream fos = null;
     
    	String fileName = cpa_ticket_id + TimezoneDateTimeUtil.getCurrentDateString() + ".pdf";
    	
    	LOGGER.info("Absolute file name : " + fileName);
    	
    	
    	String pdfFilePath = "";
    	
    	/**** Get The Absolute Path Of The File ****/
    	pdfFilePath = PDF_PATH + File.separator + fileName;    
    	
    	LOGGER.info("Absolute full Path : " + pdfFilePath);
		
		File pdfFile = new File(pdfFilePath);
		fos = new FileOutputStream(pdfFile); 
		
		generatePDFDesdeDoc(strHTMLContent, cpa_ticket_id, PDF_PATH, fos);
 
		Path path = Paths.get(pdfFilePath);
		byte[] bArray = Files.readAllBytes(path);
		
        return bArray;
    }
    
    public String IMG_PATH 		= "";
    // width : 8.5 inch
 	// height : 11 inch
 	// 1 inch : 72 points
 	//http://www.java2s.com/Code/Java/PDF-RTF/CustomPDFDocumentPageSize.htm
    //https://packers.tistory.com/224
    public String IMG_CIRCLE    = "";
    public void generatePDFDesdeDoc(String strHTMLContent, String cpa_ticket_id, String PDF_PATH, FileOutputStream fos) throws DocumentException, IOException
    {
    	Resource resource1 = new ClassPathResource("/fonts/SamsungSharpSansRg.ttf");
		Resource resource2 = new ClassPathResource("/fonts/SamsungSharpSansMd.ttf");
		Resource resource3 = new ClassPathResource("/fonts/SamsungSharpSansBd.ttf");
		Resource resource4 = new ClassPathResource("/fonts/SAMSUNGONE-400V10.ttf");
		Resource resource5 = new ClassPathResource("/fonts/SAMSUNGONE-500V10.ttf");
		Resource resource6 = new ClassPathResource("/fonts/SAMSUNGONE-600V10.ttf");
		Resource resource7 = new ClassPathResource("/fonts/SAMSUNGONE-700V10.ttf");
		
		// Images
		Resource resource0 = new ClassPathResource("/image/addressIco.png");
		IMG_PATH = resource0.getURI().getPath().substring( 0 , resource0.getURI().getPath().toString().indexOf("image/addressIco.png") );
    	Resource resource9 = new ClassPathResource("/image/circle.png");
    	IMG_CIRCLE = resource9.getURI().getPath().substring( 0 , resource9.getURI().getPath().toString().indexOf("image/circle.png") );
		// Document
    	/**
    	 * Constructs a new <CODE>Document</CODE> -object.
    	 *
    	 * @param pageSize     the pageSize
    	 * @param marginLeft   the margin on the left
    	 * @param marginRight  the margin on the right
    	 * @param marginTop    the margin on the top
    	 * @param marginBottom the margin on the bottom
    	 */
		Document document = new Document(PageSize.LETTER, -50f , -50f , 20f, 5f);
        
    	PdfWriter.getInstance(document, fos);
        
        document.open();
        
        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();
//        CssFile cssFile = XMLWorkerHelper.getInstance().getCSS(new FileInputStream(getClass().getResource("/styles/privacy.css").getPath()));
//        cssResolver.addCss(cssFile);
        
        // HTML, Font
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(resource1.getURI().getPath(), "SamsungSharpSans");
        fontProvider.register(resource2.getURI().getPath(), "SamsungSharpSans");
        fontProvider.register(resource3.getURI().getPath(), "SamsungSharpSans");
        fontProvider.register(resource4.getURI().getPath(), "SamsungOne-400");
        fontProvider.register(resource5.getURI().getPath(), "SamsungOne-500");
        fontProvider.register(resource6.getURI().getPath(), "SamsungOne-600");
        fontProvider.register(resource7.getURI().getPath(), "SamsungOne-700");
        
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
         
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        
        htmlContext.setImageProvider(new AbstractImageProvider() {
      	  public String getImageRootPath() {
      	    return IMG_PATH;
      	  }
      	});
        
        htmlContext.setLinkProvider(new LinkProvider() {
            public String getLinkRoot() {
                return IMG_PATH;
            }
        });
        
        // Pipelines
        ElementList elements = new ElementList();
        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
        HtmlPipeline html = new HtmlPipeline(htmlContext, end);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        
        String htmlStr0 = strHTMLContent;
        
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
        
        StringReader strReader;
      
        strReader = new StringReader(htmlStr0);
        xmlParser.parse(strReader);
        
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setPaddingLeft(0f);
        cell.setPaddingRight(0f);
        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        
        for (Element element : elements) {
        	if (!ColumnText.isAllowedElement(element))
                continue;
        	cell.addElement(element);
        }
        
        table.addCell(cell);
        
        document.add(table);
        
        document.close();
    }
    
}