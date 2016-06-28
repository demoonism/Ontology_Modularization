package findNonTrivial;
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

public class ObjectPropertyHierachyV2 {

	static ArrayList<String> ChecktList = new ArrayList<String>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\modules");
		File[] listOfFiles = folder.listFiles();

		find(listOfFiles);

	
	}

	
	private static void find(File[] listOfFiles ) {

	    try {
	    
	    	
		ArrayList<String> RepeatList = new ArrayList<String>();
		
		for (File file : listOfFiles) {
			
		

			if (file.isFile()) {   //OuterIF
				
	    	File fXmlFile = new File("F:\\sweet\\"+file.getName());
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	
			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	     
	    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	     
	    	NodeList nList = doc.getElementsByTagName("owl:ObjectProperty");
	    	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    	
		    	
	    		Node nNode = nList.item(temp);

	    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;

	    			NodeList subPropertyList = eElement.getElementsByTagName("rdfs:subPropertyOf");
	    	        for (int k = 0; k < subPropertyList.getLength(); ++k)
	    	        {
	    	        	
	    	        	File Hierachy = new File("Hierachy.txt");
	    		    	if (!Hierachy.exists()) {
	    		    		Hierachy.createNewFile();
	    				}

	    				FileWriter fw = new FileWriter(Hierachy.getName(),true);
	    				BufferedWriter wr = new BufferedWriter(fw);
	    				

	    	            Element equivalent = (Element) subPropertyList.item(k);

	    	            String equivalentText = equivalent.getAttribute("rdf:resource").replace("#", "");
   	    		 	    if( equivalentText.contains(".owl"))	
	   	    	         {
	   	    	            	
   	    		 	    equivalentText = 	 equivalentText.substring( equivalentText.indexOf("owl")+3);
	   	    	         }
	    	            
	    				
	    				
	    				if(!RepeatList.contains(equivalentText))
	    				{
	    					//wr.write("------------------------------------------------------\n");  
	    					//wr.write("Super class: " + equivalentText+ "\n");
	    					//wr.close();
	    					
	    					//System.out.println("------------------------------------------------------");  
	    					//System.out.println("Super class: " + equivalentText);
	    					//wr.close();
	    					
	    					
	    					
	    					RepeatList = recursion(equivalentText,RepeatList,listOfFiles,0);
	    				} // end of out if

	    	            
	    	    	}
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			
			}   // end of out for loop
		


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
    	            
    	            subText = sub.getAttribute("rdf:resource").replace("#", "");
	    		 	    if( subText.contains(".owl"))	
	    		 	    {
  	    	            	
	    		 	    subText = 	 subText.substring( subText.indexOf("owl")+3);
	    		 	    }
	    		 	    
	    	
    	            if(subText.equals(parent))
    	            {
    	            	
    	            
    	        	String HeadNode =  innerElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			HeadNode =  innerElement.getAttribute("rdf:ID");
	    			
	    			HeadNode = HeadNode.replace("#", "");
	    			
	    		    if(HeadNode.contains(".owl"))	
  	    	         {
  	    	            	
	    	 	    	HeadNode = 	HeadNode.substring(HeadNode.indexOf("owl")+3);
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

    		    				

    		    		//	} catch (IOException e) {
    		    		//		e.printStackTrace();
    		    		//	}
    		    			
    		    			   recursion(HeadNode,RepeatList,listOfFiles,counter);
   		    				//System.out.println("recursion start\n");

    		    			
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

