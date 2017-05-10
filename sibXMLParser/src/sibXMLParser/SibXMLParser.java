package sibXMLParser;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class SibXMLParser {
	
	private static SibXMLParser XMLPars;
	private static String tPath;
	private static String tRootElement;
	private static Document xmldoc;
	
	private SibXMLParser() {
		
		try {
			
			SAXReader reader = new SAXReader();
			try {
				xmldoc = reader.read(tPath);
				
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static SibXMLParser getInstance(String iPath,String rootElements){
		
		if (tPath == null) {
			tPath = iPath;
		}
		if (tRootElement == null) {
			tRootElement = rootElements;
		}
		
		if (XMLPars == null) {
			XMLPars = new SibXMLParser();
		}
		return XMLPars;
	}
	
	public String getValue(String iKey) {

		String retStr = "";
		
		List nodes = xmldoc.selectNodes(tRootElement + "/" + iKey);
		
		if (nodes.size() > 0 ) {
			retStr =  ((Node) nodes.get(0)).getText();
		} else {
			retStr = "";
		}
		
		return retStr;
		
	}
}
