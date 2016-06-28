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
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SubclassHierachy {

	static ArrayList<String> ChecktList = new ArrayList<String>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\SubclassAll");
		File[] listOfFiles = folder.listFiles();

		SubClassOf(listOfFiles);

	
	}

	
	private static void SubClassOf(File[] listOfFiles ) {

	    try {
	    
	    	
		ArrayList<String> RepeatList = new ArrayList<String>();
		ArrayList<String> HierarchyList = new ArrayList<String>();
		
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
	    	        	
	    	        	File Hierachy = new File("SubclassHierarchy.txt");
	    		    	if (!Hierachy.exists()) {
	    		    		Hierachy.createNewFile();
	    				}

	    				FileWriter fw = new FileWriter(Hierachy.getName(),true);
	    				BufferedWriter wr = new BufferedWriter(fw);
	    				

	    	            Element equivalent = (Element) subPropertyList.item(k);

	    	            String equivalentText = equivalent.getAttribute("rdf:resource");

	    	           
	    				if(!RepeatList.contains(equivalentText))
	    				{
	    					//wr.write("------------------------------------------------------\n");  
	    					//wr.write("Super class: " + equivalentText+ "\n");
	    					//wr.close();
	    					
	    					//bw.write("<owl:Class rdf:about=\"" + HeadNode+ "\">\n");
		    				//bw.write(" <rdfs:subClassOf rdf:resource=\"" + equivalentText + "\"\n");
		    				//bw.write("</owl:Class>\n\n");
	    					
	    					//System.out.println("------------------------------------------------------");  
	    					//System.out.println("<owl:Class rdf:about=\"" + equivalentText+ "\">\n");
	    					//wr.close();
	    					
	    					
	    					
	    					RepeatList = recursion(equivalentText,RepeatList,doc,wr);
	    				} // end of out if
	    				
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
	
	

	private static ArrayList<String> recursion(String parent, ArrayList<String> RepeatList, Document file,BufferedWriter br ) throws ParserConfigurationException, SAXException, IOException{
		

    	//optional, but recommended
    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
    	file.getDocumentElement().normalize();
     
    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
     
    	NodeList innerList = file.getElementsByTagName("owl:Class");
    	
    	for (int i = 0; i < innerList.getLength(); i++) {
    	
	    	
    		Node innerNode = innerList.item(i);

    		
    		if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
     
    			Element innerElement = (Element) innerNode;

    			NodeList InnersubPropertyList = innerElement.getElementsByTagName("rdfs:subClassOf");
    	        for (int o = 0; o < InnersubPropertyList.getLength(); ++o)
    	        {

    	            Element sub = (Element) InnersubPropertyList.item(o);
    	            
    	            String subText = "";
    	            
    	            subText = sub.getAttribute("rdf:resource");
	    		 	    
	    	
    	            if(subText.equals(parent))
    	            {
    	            	
    	            
    	        	String HeadNode =  innerElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			HeadNode =  innerElement.getAttribute("rdf:ID");

    		    		if(!subText.isEmpty())	
    		    		{		   
    		    		//	try {
    		    				//writer.write("--sub property:000000000 \n");
    		    				//writer.write(HeadNode + "  ("+fileloop.getName()+") \n");
    		    				//System.out.println("begining");
    		    				//System.out.println("Super class:"+parent);
    		    				
    		    			br.write("<owl:Class rdf:about=\"" + parent+ "\">\n");
    		    			br.write(" <rdfs:subClassOf rdf:resource=\"" + HeadNode + "\"\n");
    		    			br.write("</owl:Class>\n\n");
    		    			
    		    				//System.out.printf("%"+counter*10+"s %d%s%s\n","--sub class level",counter, ":",HeadNode);
    		    				//System.out.println("--sub class:"+HeadNode);
    		    				//System.out.println("Super class:"+parent);
    		    				//System.out.println("ending\n");
    		    				//System.out.printf(HeadNode + "  ("+fileloop.getName()+") \n");

    		    				

    		    		//	} catch (IOException e) {
    		    		//		e.printStackTrace();
    		    		//	}
    		    			
    		    			   recursion(HeadNode,RepeatList,file,br);
   		    				//System.out.println("recursion start\n");

    		    			
    		    		}
    	    		
    	        }

    	    	}
    	        
    		}
    	}
	
    	

		RepeatList.add(parent);
		

	    return RepeatList;
		
	}   //-Equi Function end
	
	
}

