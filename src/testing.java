import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		File folder = new File("F:\\hierarchy");
		File[] listOfFiles = folder.listFiles();
		Retrive(listOfFiles);
		
		
		
		//File filefolder = new File("F:\\sweet");
		//File[] SweetList = filefolder.listFiles();

		//String axiom = "Storm";
		//FindAllSubclass(SweetList, axiom,axiom);
	}

	public static void Retrive (File[] listOfFiles) {
	      
		BufferedReader br = null;
		String  sCurrentLine;
		String  NextLine;
		
		File file = new File("F:\\SweetAll\\RoarSweet.owl");

	
		    	try {
        	 	FileWriter fw = null;
            	BufferedWriter bw = null;
			
				
				File module = null;
		    	
		    	module = new File("module.owl");

		    	if (!module.exists()) {
		    	
						module.createNewFile();
					}

		    	
		    	fw = new FileWriter(module.getName(),true);
		    	bw = new BufferedWriter(fw);	
		    	
		    	Cardinality(file, bw);
		    	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
	
		

		return;
    }
	
	private static void Cardinality(File listOfFiles, BufferedWriter br) {
		
		
		System.out.println("passing");
		
  
	    try {
	       
	

			if (listOfFiles.isFile()) {   //OuterIF
				

	    	File fXmlFile = listOfFiles;
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	
			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	     
	    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	     
	    	NodeList nList = doc.getElementsByTagName("owl:Class");

	    	
	    	
	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    	
		    	
	    		Node nNode = nList.item(temp);

	    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    			
	    			
	    			String HeadNode_original =  eElement.getAttribute("rdf:about");
	    			if(HeadNode_original == "")
	    			{HeadNode_original =  eElement.getAttribute("rdf:ID");}
	    			
	    			
	    			
	    			String HeadNode =  eElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			{HeadNode =  eElement.getAttribute("rdf:ID");}
	    			
	    			HeadNode = HeadNode.replace("#", "");

	    	 	    if(HeadNode.contains(".owl"))	
  	    	         {
  	    	            	
	    	 	    	HeadNode = 	HeadNode.substring(HeadNode.indexOf("owl")+3);
  	    	         }
	    	 	    
	    	        
	    			NodeList SubClassList = eElement.getElementsByTagName("rdfs:subClassOf");
	    	        for (int k = 0; k < SubClassList.getLength(); ++k)
	    	        {
	    	        	

	    	            Element subClass = (Element) SubClassList.item(k);
	    	            String  subClassText = subClass.getAttribute("rdf:resource");

	    		    	if(subClassText.isEmpty())
	    		    			
	    		    	{		   
    	
	    		    			String   onPropertyText="";
	    		    			String   CardAttribute="";
	    		    			String   onPropertyText_original ="";
	    		    			String   CardContent ="";
	    		    			
		    		    			
	    		    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
	    		    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
	    		    		        {
	    		    		            Element Restriction = (Element) RestrictionList.item(l);
	    		    	    			
	    		    		            NodeList onPropertyList = Restriction.getElementsByTagName("owl:onProperty");
	    		    		            
	    		   	    			    for (int m = 0; m < onPropertyList.getLength(); ++m)
	    		   	    		        {
	    		   	    		        	

	    		   	    		            Element onProperty = (Element) onPropertyList.item(m);
	    		   	    		            onPropertyText = onProperty.getAttribute("rdf:resource");
		    		   	    		 	    //onPropertyText = 	onPropertyText.substring(onPropertyText.indexOf("#")+1);
			    		   	    	         
	    		   	    		            
	    		   	    		            onPropertyText_original = onProperty.getAttribute("rdf:resource");

	    		   	    		    	}
	    		   	    			    
	    		   	    			    
	    		   	    			 NodeList cardinalityList = subClass.getElementsByTagName("owl:minCardinality");

	    		   	    			    
	    		   	    			for (int m = 0; m < cardinalityList.getLength(); ++m)
    		   	    		        {
    	   	    		            Element cardinality = (Element) cardinalityList.item(m);
    	   	    		            CardAttribute = cardinality.getAttribute("rdf:datatype");
    	   	    		            CardContent = cardinality.getTextContent();
    		   	    		    	}
    		   	    			    

    		    	    		       if(!CardAttribute.isEmpty()){

    		    	    				System.out.println(" <!-- Cardinality-->\n\n\n\n");		    	
    		    	    		   		System.out.println("	<owl:Class rdf:about =\"" + HeadNode_original+ "\">\n");
        		    					System.out.println("		<rdfs:subClassOf>\n");
        		    					System.out.println("			<owl:Restriction>\n");
    	    		    				System.out.println("				<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
    	    		    				System.out.println("				<owl:cardinality rdf:datatype=\"" + CardAttribute + "\">"+CardContent+"</owl:cardinality>\n");
    	    		    				System.out.println("			</owl:Restriction>\n");
    	    		    				System.out.println("		</rdfs:subClassOf>\n");
    	    		    				System.out.println("	</owl:Class>\n\n");
	    		   
    		    	    		       }

	    		   	    			 
	    		    		    	}
	    		    		        
	    		    			
	    		    			
	    		    			
	    		    			
	    		    			
	    		    		}
	    	    		
	    	    		
	    	    	
	    	    	
	    	    		
	    	    		//===
	    	    		
	 
	    	    		
	    	    		//====
	    	    		
	    	    	}
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			
	
	    } catch (Exception e) {
			e.printStackTrace();
		    }

	}   

	
	
}
