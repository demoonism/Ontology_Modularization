package HierarchyAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class subclassOnly {

	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\SubclassAll");
		File[] listOfFiles = folder.listFiles();

		SubClassOf(listOfFiles);

	
	}
private static void SubClassOf(File[] listOfFiles) {
		
		
		String Search = "";
		String key ="about";
	    try {
	    	
	    	
        	File Hierachy = new File("SubclassHierarchy.txt");
	    	if (!Hierachy.exists()) {
	    		Hierachy.createNewFile();
			}

			FileWriter fw = new FileWriter(Hierachy.getName(),true);
			BufferedWriter br = new BufferedWriter(fw);
			
	  	    	
		
		for (File file : listOfFiles) {

			if (file.isFile()) {   //OuterIF

			File fXmlFile = new File("F:\\SubclassAll\\"+file.getName());
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	doc.getDocumentElement().normalize();
	    	NodeList nList = doc.getElementsByTagName("owl:Class");

	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    	
		    	
	    		Node nNode = nList.item(temp);

	    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    		
	    			
	    			String HeadNode =  eElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			{HeadNode =  eElement.getAttribute("rdf:ID");}
	    			

	    			NodeList SubClassList = eElement.getElementsByTagName("rdfs:subClassOf");
	    	        for (int k = 0; k < SubClassList.getLength(); ++k)
	    	        {
	    	        	

	    	            Element subClass = (Element) SubClassList.item(k);
	    	            String  subClassText = subClass.getAttribute("rdf:resource");

	    		    		if(!subClassText.isEmpty())
	    		    			
	    		    		{
	    		    		
	    		    			
	    		    			br.write("	<owl:Class rdf:about=\"" + HeadNode+ "\">\n");
	    		    			br.write(" 		<rdfs:subClassOf rdf:resource=\"" + subClassText + "\"\n");
	    		    			br.write("	</owl:Class>\n\n");
	    		    			
	    		    		}
	    	    		
	    	    		
	    	    	
	    	    	
	    	    		
	    	    		//===
	    	    		
	 
	    	    		
	    	    		//====
	    	    		
	    	    	}
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			}
	
		
	    } catch (Exception e) {
			e.printStackTrace();
		    }

	}  
	
	
	
	
}
