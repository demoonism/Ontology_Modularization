package HierarchyAnalysis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModelExtractionv2 {

	static ArrayList<String> ChecktList = new ArrayList<String>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\SweetAll");
		File[] listOfFiles = folder.listFiles();

		SubClassOf(listOfFiles);

	}



	private static void SubClassOf(File[] listOfFiles ) {

	    try {
	    	File SubClassOf = new File("NewSubClass.txt");
	    	if (!SubClassOf.exists()) {
				SubClassOf.createNewFile();
			}

			FileWriter fw = new FileWriter(SubClassOf.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
		
		for (File file : listOfFiles) {

			if (file.isFile()) {   //OuterIF
				
		    	
		
		

	    	File fXmlFile = new File("F:\\SweetAll\\"+file.getName());
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	
			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	     
	    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	     
	    	NodeList nList = doc.getElementsByTagName("owl:Class");
	    	boolean flager = false;
	    	
	    	for (int temp1 = 0; temp1 < nList.getLength(); temp1++) {
	    	
		    	
	    	Node nNode1 = nList.item(temp1);

	    		
	    	if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
	     
	    		Element eElement1 = (Element) nNode1;   
	    		NodeList equivalentList = eElement1.getElementsByTagName("rdfs:subClassOf");
	    	    if(equivalentList.getLength()>0)
	    	    	flager = true;
	    		}
	    	}
	    	if(flager)
	    		bw.write("/------------Extracted from: "+file.getName()+"------------/\n\n");

	    	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    	
		    	
	    		Node nNode = nList.item(temp);

	    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    			
	    			
	    			String HeadNode =  eElement.getAttribute("rdf:about").replaceAll("#", "");
	    			//String HeadNode =  eElement.getAttribute("rdf:about");
	    			
	    			 if(HeadNode.contains(".owl"))
 		            	
	    	            {
	    	            	
	    				 HeadNode = 	HeadNode.substring(HeadNode.indexOf("owl")+3);
	    	            }
	    			 
	    			 
	    			if(HeadNode == "")
	    			HeadNode =  eElement.getAttribute("rdf:ID");

	    	        
	    			NodeList equivalentList = eElement.getElementsByTagName("rdfs:subClassOf");
	    	        for (int k = 0; k < equivalentList.getLength(); ++k)
	    	        {
	    	        	

	    	            Element equivalent = (Element) equivalentList.item(k);
	    	            
	    	            //String equivalentText = equivalent.getAttribute("rdf:resource").replaceAll("#", "");
	    	            String equivalentText = equivalent.getAttribute("rdf:resource");
	  	    	      
	    	            
//	    		         if(equivalentText.contains(".owl"))
//	    		            	
//	    	            {
//	    	            	
//	    		            	equivalentText = 	equivalentText.substring(equivalentText.indexOf("owl")+3);
//	    	            }
	    	      
	    		    		if(!equivalentText.isEmpty())
	    		    			
	    		    		{		   
	    		    			try {

	    		    		

	    		    				

	    		    				// if file doesnt exists, then create it
	    		    				
	    		    				bw.write("<owl:Class rdf:about=\"" + HeadNode+ "\">\n");
	    		    				bw.write(" <rdfs:subClassOf rdf:resource=\"" + equivalentText + "\"\n");
	    		    				bw.write("</owl:Class>\n\n");
	    		    			

	    		    				System.out.println("Done");

	    		    			} catch (IOException e) {
	    		    				e.printStackTrace();
	    		    			}
	    		    			
	    		
	    		    			
	    		    		}
	    	    		
	    	    		
	    	    		
	    	    		 String   DescriptionText="";

	    	    		
	    	    	}
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			}
		bw.close();
		
		
	    } catch (Exception e) {
			e.printStackTrace();
		    }
		
	}   //-Equi Function end

}
