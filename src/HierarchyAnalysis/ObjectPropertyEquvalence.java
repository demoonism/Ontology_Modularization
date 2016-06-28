package HierarchyAnalysis;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ObjectPropertyEquvalence {

	static ArrayList<String> ChecktList = new ArrayList<String>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\sweet");
		File[] listOfFiles = folder.listFiles();

		SubClassOf(listOfFiles);

	
	}

	
	private static void SubClassOf(File[] listOfFiles ) {

	    try {
	    
	    	
		ArrayList<String> RepeatList = new ArrayList<String>();



				
	    	File fXmlFile = new File("F:\\Object\\ObjectEquivalent.owl");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	
			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	     
	    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	     
	    	NodeList innerList = doc.getElementsByTagName("owl:ObjectProperty");
	    	
	    	for (int i = 0; i < innerList.getLength(); i++) {
	    	
		    	
	    		Node innerNode = innerList.item(i);

	    		
	    		if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element innerElement = (Element) innerNode;

	    			NodeList InnersubPropertyList = innerElement.getElementsByTagName("owl:equivalentProperty");
	    	        for (int o = 0; o < InnersubPropertyList.getLength(); ++o)
	    	        {

	    	            Element sub = (Element) InnersubPropertyList.item(o);
	    	            
	    	            String Parent = "";
	    	            
	    	            Parent = sub.getAttribute("rdf:resource");
    	
		    		 	Parent =  Parent.substring(Parent.indexOf("#")+1);
		    		 	
		    		 	    
	    	            	
	    	        	String HeadNode =  innerElement.getAttribute("rdf:about");
		    			if(HeadNode == "")
		    			HeadNode =  innerElement.getAttribute("rdf:ID");
		    			
		    			//HeadNode = HeadNode.replace("#", "");
		    			
		    	 	    HeadNode = 	HeadNode.substring(HeadNode.indexOf("#")+1);
	  	    	         
		    		    
		    		    
		    		 
		    		    
		    		    
	    		    		if(!Parent.isEmpty())
	    		    			
	    		    		{		   
	    		    		//	try {
	    		    				//writer.write("--sub property:000000000 \n");
	    		    				//writer.write(HeadNode + "  ("+fileloop.getName()+") \n");
	    		    				System.out.println("begining");
	    		    			
	    		    				//System.out.printf("%s %d%s%s\n","--sub property level",counter, ":",HeadNode);
	    		    				System.out.println("A:"+HeadNode);
	    		    				System.out.println("B:"+Parent);
	    		    			
	    		    		
	    		    				System.out.println("ending\n");
	    		    				//System.out.printf(HeadNode + "  ("+fileloop.getName()+") \n");

	    		    			

	    		    			
	    		    		}
	    	  
	    	    	}
	    	        
	    		}
	    	}
		
	    	
			
		

	    } catch (Exception e) {
			e.printStackTrace();
		    }
		
	}   //-Equi Function end
	
	

	private static ArrayList<String> recursion(String parent, ArrayList<String> RepeatList, File[] listOfFiles,int counter){
		
		counter+=1;
	    try {
	    	File Hierachy = new File("Hierachy.txt");
	    	if (!Hierachy.exists()) {
	    		Hierachy.createNewFile();
			}

			FileWriter fw = new FileWriter(Hierachy.getName(),true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			  		    				
		
    for (File fileloop : listOfFiles) {

		if (fileloop.isFile()) {   //OuterIF
			
    	File innerXML = new File("F:\\sweet\\"+fileloop.getName());
    	DocumentBuilderFactory innerdbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder = innerdbFactory.newDocumentBuilder();
    	Document InnerDoc = dBuilder.parse(innerXML);
    	
		
    	//optional, but recommended
    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
    	InnerDoc.getDocumentElement().normalize();
     
    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
     
    	NodeList innerList = InnerDoc.getElementsByTagName("owl:ObjectProperty");
    	
    	for (int i = 0; i < innerList.getLength(); i++) {
    	
	    	
    		Node innerNode = innerList.item(i);

    		
    		if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
     
    			Element innerElement = (Element) innerNode;

    			NodeList InnersubPropertyList = innerElement.getElementsByTagName("rdfs:subPropertyOf");
    	        for (int o = 0; o < InnersubPropertyList.getLength(); ++o)
    	        {

    	            Element sub = (Element) InnersubPropertyList.item(o);
    	            
    	            String subText = "";
    	            
    	            subText = sub.getAttribute("rdf:resource");

	    		 	if( subText.contains(".owl"))	
	    		 	{    	
	    		 	    subText = 	 subText.substring(subText.indexOf("#")+1);
	    		 	}
	    		 	    
	    	
    	            if(subText.equals(parent))
    	            {
    	            	
    	        	String HeadNode =  innerElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			HeadNode =  innerElement.getAttribute("rdf:ID");
	    			
	    			//HeadNode = HeadNode.replace("#", "");
	    			
	    		    if(HeadNode.contains(".owl"))	
  	    	         {
  	    	            	
	    	 	    	HeadNode = 	HeadNode.substring(HeadNode.indexOf("#")+1);
  	    	         }
	    		    
	    		    
	    		 
	    		    
	    		    
    		    		if(!subText.isEmpty())
    		    			
    		    		{		   
    		    		//	try {
    		    				//writer.write("--sub property:000000000 \n");
    		    				//writer.write(HeadNode + "  ("+fileloop.getName()+") \n");
    		    				System.out.println("begining");
    		    			
    		    				//System.out.printf("%s %d%s%s\n","--sub property level",counter, ":",HeadNode);
    		    				System.out.println("--sub property:"+HeadNode);
    		    				System.out.println("Super class:"+parent);
    		    			
    		    		
    		    				System.out.println("ending\n");
    		    				//System.out.printf(HeadNode + "  ("+fileloop.getName()+") \n");

    		    			

    		    			
    		    		}
    	    		
    	        }

    	    	}
    	        
    		}
    	}
	
    	

	
	    	}
		
		RepeatList.add(parent);
		
		}   // end of inner for loop
	
    writer.close();
    
    } catch (Exception e) {
			e.printStackTrace();
		    }
	    
	    return RepeatList;
		
	}   //-Equi Function end
	
	
}

