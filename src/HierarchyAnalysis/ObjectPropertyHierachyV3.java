package HierarchyAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ObjectPropertyHierachyV3 {

	static ArrayList<String> ChecktList = new ArrayList<String>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\sweet");
		File[] listOfFiles = folder.listFiles();

		SubClassOf(listOfFiles);

	
	}

	
	private static void SubClassOf(File[] listOfFiles ) {

	    try {
	    

	    	File fXmlFile = new File("F:\\Object\\ObjectHierarchy.owl");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	
			
	    	doc.getDocumentElement().normalize();
	     
	    	NodeList innerList = doc.getElementsByTagName("owl:ObjectProperty");
	    	
	    	for (int i = 0; i < innerList.getLength(); i++) {
	    	
		    	
	    		Node innerNode = innerList.item(i);

	    		
	    		if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element innerElement = (Element) innerNode;

	    			NodeList InnersubPropertyList = innerElement.getElementsByTagName("rdfs:subPropertyOf");
	    	        for (int o = 0; o < InnersubPropertyList.getLength(); ++o)
	    	        {

	    	            Element sub = (Element) InnersubPropertyList.item(o);
	    	            
	    	            String Parent = "";
	    	            
	    	            Parent = sub.getAttribute("rdf:resource");
    	
		    		 	//Parent =  Parent.substring(Parent.indexOf("#")+1);
		    		 	
		    		 	    
	    	            	
	    	        	String HeadNode =  innerElement.getAttribute("rdf:about");
		    			if(HeadNode == "")
		    			HeadNode =  innerElement.getAttribute("rdf:ID");
		    			
		    	 	    //HeadNode = 	HeadNode.substring(HeadNode.indexOf("#")+1);
	  	    	         
		    		    
		    		    
		    		 
		    		    
		    		    
	    		    		if(!Parent.isEmpty())
	    		    		{		   

	    		    				System.out.println("begining");
	    		    			
	    		    				System.out.println("--sub property:"+HeadNode);
	    		    				System.out.println("Super class:"+Parent);
	    		    			
	    		    		
	    		    				System.out.println("ending\n");

	    		    		}
	    	  
	    	    	}
	    	        
	    		}
	    	}

	    } catch (Exception e) {
			e.printStackTrace();
		    }
		
	} 
	
	
}

