package classhierarchy;

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

public class subclasshierarchy {




	public static void main(String[] args) throws IOException {
		
		
		
		File folder = new File("F:\\SubclassAll");
		File[] listOfFiles = folder.listFiles();


		
		SubClassOf(listOfFiles);

	
	}

	
	private static void SubClassOf(File[] listOfFiles ) {
		
		
		Pair p1 = new Pair("hehe","hahah");
		
		ArrayList<Pair> ChecktList = new ArrayList<Pair>();

		ChecktList.add(p1);
		
	    try {
	    

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

	    			NodeList subPropertyList = eElement.getElementsByTagName("rdfs:subClassOf");
	    	        for (int k = 0; k < subPropertyList.getLength(); ++k)
	    	        {
	    	        	
	    	        	File Hierachy = new File("NewSubclassHierarchy.txt");
	    		    	if (!Hierachy.exists()) {
	    		    		Hierachy.createNewFile();
	    				}

	    				FileWriter fw = new FileWriter(Hierachy.getName(),true);
	    				BufferedWriter wr = new BufferedWriter(fw);
	    				

	    	            Element equivalent = (Element) subPropertyList.item(k);

	    	            String parent = equivalent.getAttribute("rdf:resource");
	    	            String child =  eElement.getAttribute("rdf:about");
	    	            
	    	            boolean flag = true;
	    	            
	    	    		for(Pair tp : ChecktList)		
	    	    			if (tp.getX().equals(parent) &&tp.getY().equals(child))
	    	    				flag = false;
		    			
	    	    		
	    	    		
		    					
	    	    			if(flag){
		    			    	NodeList innerList = doc.getElementsByTagName("owl:Class");
		    			    		
		    					for (int i = 0; i < innerList.getLength(); i++) {
		    				    	
		    				    	
		    			    		Node innerNode = innerList.item(i);

		    			    		
		    			    		if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
		    			     
		    			    			Element innerElement = (Element) innerNode;

		    			    			NodeList InnersubPropertyList = innerElement.getElementsByTagName("rdfs:subClassOf");
		    			    	        for (int o = 0; o < InnersubPropertyList.getLength(); ++o)
		    			    	        {

		    			    	            Element sub = (Element) InnersubPropertyList.item(o);
		    			    	            
		    			    	            String ancester = "";
		    			    	            
		    			    	            ancester = sub.getAttribute("rdf:resource");
		    				    		 	    
		    				    	
		    			    	            if(ancester.equals(parent))
		    			    	            {
		    			    	            	
		    			    	            
		    			    	        	String HeadNode =  innerElement.getAttribute("rdf:about");
		    				    			if(HeadNode == "")
		    				    			HeadNode =  innerElement.getAttribute("rdf:ID");

		    			    		    		if(!ancester.isEmpty())	
		    			    		    		{		   
		    			    		    		//	try {
		    			    		    				//writer.write("--sub property:000000000 \n");
		    			    		    				//writer.write(HeadNode + "  ("+fileloop.getName()+") \n");
		    			    		    				//System.out.println("begining");
		    			    		    				//System.out.println("Super class:"+parent);
		    			    		    				
		    			    		    			wr.write("<owl:Class rdf:about=\"" + HeadNode+ "\">\n");
		    			    		    			wr.write(" <rdfs:subClassOf rdf:resource=\"" + ancester + "\"/>\n");
		    			    		    			wr.write("</owl:Class>\n\n");
		    			    		    			
		    			    		    			
		    			    		    			
		    			    		    			Pair p =  new Pair(ancester, HeadNode);
		    				    					ChecktList.add(p);

		    			    		    			
		    			    		    		}
		    			    	    		
		    			    	        }

		    			    	    	}
		    			    	        
		    			    		}
		    			    	}
		    				
		    			    	


	    	    			}// if flag
	    				wr.close();;

	    	            
	    	    	}
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			
			}   // end of out for loop
		


	    } catch (Exception e) {
			e.printStackTrace();
		    }
		
	}   //-Equi Function end
	
	
	
	
	
}
