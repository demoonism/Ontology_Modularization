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

class CuModV2beta3 {

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
		
		File filefolder = new File("F:\\sweet");
		File[] SweetList = filefolder.listFiles();


   
    	
    
		
		
		
		
		for (File ontoFile : listOfFiles) {
		    if (ontoFile.isFile()) {   //OuterIF
	
				try {

					br = new BufferedReader(new FileReader(ontoFile.getPath()));

					int index = 1;
					
					while ((sCurrentLine = br.readLine()) != null) {
						
						
				
						
						if(sCurrentLine.contains("begining"))
						{

				        	  String newsuper = "";
				        	  String newsub = "";
				        		
				        
				        	  
					          while (!(NextLine = br.readLine()).contains("ending")) {
					        	  	String next = NextLine;
					        	  	String adjcent = null;
					        	  
					        	 	FileWriter fw = null;
					            	BufferedWriter bw = null;
								
									
									File module = null;
							    	
							    	module = new File("module"+index+".owl");

							    	if (!module.exists()) {
							    		module.createNewFile();
									}
							    	
							    	fw = new FileWriter(module.getName(),true);
							    	bw = new BufferedWriter(fw);
							    	
									System.out.println("<?xml version=\"1.0\"?>\n\n\n\n");
									System.out.println("<!DOCTYPE rdf:RDF [\n");
									System.out.println("				<!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >\n");
									System.out.println("				<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >\n");
									System.out.println("				<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >\n");
									System.out.println("				<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\n");
									System.out.println("]>\n\n\n\n\n");

									System.out.println("<rdf:RDF xmlns=\"http://www.semanticweb.org/xians/ontologies/2016/custMod#\"\n");
									System.out.println("         xml:base=\"http://www.semanticweb.org/xians/ontologies/2016/custMod\"\n");
									System.out.println("         xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n");
									System.out.println("         xmlns:owl=\"http://www.w3.org/2002/07/owl#\"\n");
									System.out.println("         xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\n");	
									System.out.println("         xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n");
									System.out.println("         <owl:Ontology rdf:about=\"http://www.semanticweb.org/xians/ontologies/2016/custMod\">\n");
									System.out.println("         <rdfs:label>SWEET Ontology Remodularization</rdfs:label>\n");
									System.out.println("         <owl:versionInfo>2.3</owl:versionInfo>\n");
									System.out.println("         </owl:Ontology>\n\n\n\n\n");
							     	
					        	
		        		       	  if(next.contains("--sub property:"))
					        	  {
		        		       	
		        		       		
		        		       		newsub = next.substring(15);
		        		       		System.out.println("newsub------------"+newsub);
					      
					        		ObjectProperty(SweetList,newsub,bw);
					        		AllValueFrom(SweetList,newsub,bw);
					        		SomeValue(SweetList,newsub,bw);
					        		HasValue(SweetList,newsub,bw);

					        	  }
		        		       	  fw.close();
		        		       	  bw.close();
		        		       	  
		        		       	  fw = new FileWriter(module.getName(),true);
		        		       	  bw = new BufferedWriter(fw);
						    	
					        	  if((adjcent = br.readLine()).contains("Super class:"))
					        	  {
					        		  		   	newsuper =  adjcent.substring(12);
					        		  		   	System.out.println("-------super-----"+newsuper);
							        		  	
 					        		  			ObjectProperty(SweetList,newsuper,bw);
 					        		  			AllValueFrom(SweetList,newsuper,bw);
 					        		  			SomeValue(SweetList,newsuper,bw);
 					        		  			HasValue(SweetList,newsuper,bw);

					        	  }

					        	  
					        	  bw.close();
					        	  index++;

					        	  
					          }

						}

					  }

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
						} catch (IOException ex) {
						ex.printStackTrace();
						}
					}	
			
		    }
		}
	

		return;
    }
	
	private static void ObjectProperty(File[] listOfFiles, String axiom, BufferedWriter br) {

	    try {
	    	
	    	
	    	

		for (File file : listOfFiles) {
			
		 	File fXmlFile = new File("F:\\sweet\\"+file.getName());
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
			NodeList ObjectPropertyList = doc.getElementsByTagName("owl:ObjectProperty");
			
			
			if (file.isFile()) {   //OuterIF

			
	    	doc.getDocumentElement().normalize();
	     

	    	for (int temp = 0; temp < ObjectPropertyList.getLength(); temp++) {
	    		Node nNode = ObjectPropertyList.item(temp);
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {        // if start--
	 
	    			Element eElement = (Element) nNode;
	     
	    			String HeadNode =  eElement.getAttribute("rdf:about");
	    			//String HeadNode =  eElement.getAttribute("rdf:about");
	    			
	    			if(HeadNode == "")
	    			HeadNode =  eElement.getAttribute("rdf:ID").replace("#", "");

	    			String HeadNode_original =  eElement.getAttribute("rdf:about");
	    			//String HeadNode =  eElement.getAttribute("rdf:about");
	    			
	    			if(HeadNode_original == "")
	    				HeadNode_original =  eElement.getAttribute("rdf:ID");

	    		
	    			
	    			
	    		    if(HeadNode.contains(".owl"))	
  	    	         {
  	    	            	
	    		    	HeadNode = 	HeadNode.substring(HeadNode.indexOf("owl")+3);
  	    	         }
	    		    
	    			if(HeadNode.equals(axiom))
	    			{
	    			

	    			System.out.println(" <!-- Object Properties -->\n\n\n\n");	
	    	
	    			
	    			System.out.println(" <!-- Extracted from: "+file.getName()+" -->\n\n");
	    			
	    			NodeList RangeList = eElement.getElementsByTagName("rdfs:range");

	    			//if(RangeList.getLength() ==0)
		    		System.out.println("	<owl:ObjectProperty rdf:about=\"" + HeadNode_original+ "\">\n");
    		
		    
	    			
	    			String RangeText = "";
	    			String RangeText_original = "";
	    			
	    			
	    			for (int j = 0; j < RangeList.getLength(); j++)
	    	        {
	    	         Element Range = (Element) RangeList.item(j);
	    	             RangeText = Range.getAttribute("rdf:resource").replace("#", "");
	    	             RangeText_original = Range.getAttribute("rdf:resource");
	    	               
	    		        if(!RangeText.isEmpty())
	    					System.out.println("		<rdfs:range rdf:resource=\""+RangeText_original+"\"/>\n");
	    	        }
	    			
	    			
	    			NodeList DomainList = eElement.getElementsByTagName("rdfs:domain");
	    			String DomainText = "";
	    			String DomainText_original = "";
	    			for (int j = 0; j < DomainList.getLength(); j++)
	    	        {
	    	         Element Domain = (Element) DomainList.item(j);
	    	            // RangeText = Range.getAttribute("rdf:resource").replace("#", "");
	    	        	DomainText = Domain.getAttribute("rdf:resource");
	    
	    		        if(!DomainText.isEmpty())
	    					System.out.println("		<rdfs:domain rdf:resource=\""+DomainText+"\"/>\n");
	    	        }
		    		
	    		     
	    			NodeList EquivList = eElement.getElementsByTagName("owl:equivalentProperty");
	    			String EquivText = "";
	    			for (int j = 0; j < EquivList.getLength(); j++)
	    	        {
	    	         Element Equiv = (Element) EquivList.item(j);
	    	          // EquivText = Equiv.getAttribute("rdf:resource").replace("#", "");
	    	             EquivText = Equiv.getAttribute("rdf:resource");
	    	               
	    		        if(!EquivText.isEmpty())
	    					System.out.println("		<owl:equivalentProperty rdf:resource=\""+EquivText+"\">\n");
	    	        }
	    			
	    			
	    	        NodeList TypeList = eElement.getElementsByTagName("rdf:type");    			
	    	        for (int i = 0; i < TypeList.getLength(); i++)
	    	        {

	    	        	String TypeText ="";
	    	            Element Type = (Element) TypeList.item(i);
	    	            		//TypeText = Type.getAttribute("rdf:resource").replace("#", "");
	    	            		TypeText = Type.getAttribute("rdf:resource");
	    		        
	    	      /*  if(TypeText.contains(".owl"))	
	    	         {
	    	            	
	    		        	TypeText = 	TypeText.substring(TypeText.indexOf("owl")+3);
	    	         }
	    	      */
	    		        if(!TypeText.isEmpty())
	    	    		{
	    	 		   		//System.out.println("	<owl:ObjectProperty rdf:about=\"" + HeadNode+ "\">\n");
	    					System.out.println("		<rdf:type rdf:resource=\""+TypeText+"\">\n");
		    				//bw.write("	</owl:ObjectProperty>\n\n");

	    	    		}
	
	    	    		
	    	        }
	    	        
	    	        System.out.println("	</owl:ObjectProperty>\n\n\n\n\n\n");	
	    	       
	    			} // end of if #axiom
	    	
	    
	    	        
	    						}   // if ends--

	    							}

		    	}
			}
		
		
	    } catch (Exception e) {
			e.printStackTrace();
		    }
		
	}   //-Equi Function end
	
	private static void AllValueFrom(File[] listOfFiles,  String axiom, BufferedWriter br) {

		
		String Search = "";
		String key = "about";
	    try {

	    
		
		for (File file : listOfFiles) {

			if (file.isFile()) {   //OuterIF
				

	    	File fXmlFile = new File("F:\\sweet\\"+file.getName());
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	
			
	    	
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
	    			{HeadNode =  eElement.getAttribute("rdf:ID"); }

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
	    		    			String   allValueText="";
	    		    			
	    		    			String onPropertyText_original ="";
	    		    			String   allValueText_original ="";
	    		    			
	    		    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
	    		    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
	    		    		        {
	    		    		            Element Restriction = (Element) RestrictionList.item(l);
	    		    	    			
	    		    		            NodeList onPropertyList = Restriction.getElementsByTagName("owl:onProperty");
	    		    		            
	    		   	    			    for (int m = 0; m < onPropertyList.getLength(); ++m)
	    		   	    		        {
	    		   	    		        	

	    		   	    		            Element onProperty = (Element) onPropertyList.item(m);
	    		   	    		           onPropertyText_original = onProperty.getAttribute("rdf:resource");
	    		   	    		           onPropertyText =  onProperty.getAttribute("rdf:resource").replace("#", "");
		    		   	    		 	    if( onPropertyText.contains(".owl"))	
			    		   	    	         {
			    		   	    	            	
		    		   	    		 	    onPropertyText = 	 onPropertyText.substring( onPropertyText.indexOf("owl")+3);
			    		   	    	         }
		    		   	  
	    		   	    		    	}
	    		   	    			    
	    		   	    		
	    		   	    				if( onPropertyText.equals(axiom))
	    		   		    			{    

	    		    		         NodeList allValueList = subClass.getElementsByTagName("owl:allValuesFrom");
	    		   	    			    

	    		   	    			    for (int m = 0; m < allValueList.getLength(); ++m)
	    		   	    		        {

	    	   	    		           Element allValue = (Element) allValueList.item(m);
	    	   	    		           
	    	   	    		           
	    	   	    		        allValueText_original = allValue.getAttribute("rdf:resource");
	    	   	    		        	allValueText = allValue.getAttribute("rdf:resource").replace("#", "");
	    	   	    		        	if( allValueText.contains(".owl"))	
		    		   	    	         {
		    		   	    	            	
	    	   	    		        		allValueText = 	 allValueText.substring( allValueText.indexOf("owl")+3);
		    		   	    	         }
	    	   	    		        	
	    	   	    		        	
	    		   	    		    	}
	    		   	    			    

	    		    	    		      if(!allValueText.isEmpty()){
	    		    	    		    
	    		    	    					
	    		    	    					System.out.println(" <!-- All values from-->\n\n\n\n");	
	    		    	  	    			
	    		    	    					System.out.println(" <!-- Extracted from: "+file.getName()+" -->\n\n");
	    		    	    		    	   
	    		    	    		      		System.out.println("	<owl:Class rdf:"+key+"=\"" + HeadNode_original+ "\">\n");
		        		    					System.out.println("		<rdfs:subClassOf>\n");
		        		    					System.out.println("				<owl:Restriction>\n");
		    	    		    				System.out.println("					<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
		    	    		    				System.out.println("					<owl:allValuesFrom rdf:resource=\"" + allValueText_original + "\"/>\n");
		    	    		    				System.out.println("				</owl:Restriction>\n");
		    	    		    				System.out.println("		</rdfs:subClassOf>\n");
		    	    		    				System.out.println("	</owl:Class>\n\n\n\n\n\n\n");
		    	    		    			
		    	    		    				
		 
	    		    	   	    		   }
	    		    	    		      
	  
	    		    		   	
	    		   	    		        
	    		    		        } //-- end of new if

	    		    		    		
	    		    		    	}
	    		    		        
	    		    			
	    		    			
	    		    			
	    		    			
	    		    			
	    		    		}
	    	    		

	    	      
	    	    		
	    	    	}
	    	        
	   
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			}
	
		
		
	    } catch (Exception e) {
			e.printStackTrace();
		    }

	}   //-Equi Function end
	
	private static void SomeValue(File[] listOfFiles,  String axiom, BufferedWriter br) {
		
		
		String Search = "";
		String key ="about";
	    try {
	  
	  	    	
		
		for (File file : listOfFiles) {

			if (file.isFile()) {   //OuterIF

	    	File fXmlFile = new File("F:\\sweet\\"+file.getName());
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	doc.getDocumentElement().normalize();
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
	    		    			String   someValueText="";

	    		    			String onPropertyText_original ="";
	    		    			String someValueText_original ="";
	    		    			
	    		    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
	    		    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
	    		    		        {
	    		    		            Element Restriction = (Element) RestrictionList.item(l);
	    		    	    			
	    		    		            NodeList onPropertyList = Restriction.getElementsByTagName("owl:onProperty");
	    		    		            
	    		   	    			    for (int m = 0; m < onPropertyList.getLength(); ++m)
	    		   	    		        {
	    		   	    		        	
	    		   	    		            Element onProperty = (Element) onPropertyList.item(m);
	    		   	    		            onPropertyText = onProperty.getAttribute("rdf:resource").replace("#", "");
	    		   	    		            
	    		   	    		         onPropertyText_original = onProperty.getAttribute("rdf:resource");
	    		   	    		            
		    		   	    		 	    if(onPropertyText.contains(".owl"))	
			    		   	    	         {
			    		   	    	            	
		    		   	    		 	    onPropertyText = 	onPropertyText.substring(onPropertyText.indexOf("owl")+3);
			    		   	    	         }
		    		   	    		 	    
	    		   	    		    	}
	    		   	    			    
	    		   	    				if(onPropertyText.equals(axiom))
	    		   		    			{     
	    		   	    					
	    		   	    			    
	    		   	    			    NodeList someValueList = subClass.getElementsByTagName("owl:someValuesFrom");
  
	    		   	    			    for (int m = 0; m < someValueList.getLength(); ++m)
	    		   	    		        {

	    	   	    		            Element someValue = (Element) someValueList.item(m);
	    	   	    		        	someValueText = someValue.getAttribute("rdf:resource").replace("#", "");
	    	   	    		        	someValueText_original = someValue.getAttribute("rdf:resource");
		    	   	    		         
	   	    		   	    		 
	    		   	    		 	    if(someValueText.contains(".owl"))	
		    		   	    	         {
		    		   	    	            	
	    		   	    		 	    someValueText = 	someValueText.substring(someValueText.indexOf("owl")+3);
		    		   	    	         }
	    		   	    		    	}
	    		   	    			    

	    		   
	    		    	    		        if(!someValueText.isEmpty()){
	    		    	    		        	
	    		    	    				System.out.println(" <!-- Some values from-->\n\n\n\n");	
	 	    		    	  	    			
	 	    		    	  	    		System.out.println(" <!-- Extracted from: "+file.getName()+" -->\n\n");
	 	    		    	    		    	   	
	    		    	    		   		System.out.println("	<owl:Class rdf:"+key+"=\"" + HeadNode_original+ "\">\n");
	        		    					System.out.println("		<rdfs:subClassOf>\n");
	        		    					System.out.println("			<owl:Restriction>\n");
	    	    		    				System.out.println("				<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
	    	    		    				System.out.println("				<owl:someValuesFrom rdf:resource=\"" + someValueText_original + "\"/>\n");
	    	    		    				System.out.println("			</owl:Restriction>\n");
	    	    		    				System.out.println("		</rdfs:subClassOf>\n");
	    	    		    				System.out.println("	</owl:Class>\n\n\n");
	    	    		    	
	    	    		    				
	    	    		    				
	
	    	    		    			    
	    		    	   	    		   }
	
	    		    	    		        
	    		    		        }//--new if end
	    		    		    		
	    		    		    	}
	    		    		        
	    		    			
	    		    			
	    		    			
	    		    			
	    		    			
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

	private static void HasValue(File[] listOfFiles,  String axiom, BufferedWriter br) {
		
		
		String Search = "";
		String key = "about";
		
		boolean flag= false;
  
	    try {
	       
	  	    	
	  	    	
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
	     
	    	NodeList nList = doc.getElementsByTagName("owl:Class");

	    	
	    	
	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    	
		    	
	    		Node nNode = nList.item(temp);

	    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    			
	    			
	    			String HeadNode_original =  eElement.getAttribute("rdf:about");
	    			if(HeadNode_original == "")
	    	//		{HeadNode_original =  eElement.getAttribute("rdf:ID");key = "ID";}
	    			{HeadNode_original =  eElement.getAttribute("rdf:ID");}
	    			
	    			
	    			String HeadNode =  eElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    	//		{HeadNode =  eElement.getAttribute("rdf:ID");key = "ID";}
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
	    		    			String   hasValueText="";
	    		    			String   onPropertyText_original ="";
	    		    			String   hasValueText_original ="";
	    		    			
		    		    			
	    		    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
	    		    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
	    		    		        {
	    		    		            Element Restriction = (Element) RestrictionList.item(l);
	    		    	    			
	    		    		            NodeList onPropertyList = Restriction.getElementsByTagName("owl:onProperty");
	    		    		            
	    		   	    			    for (int m = 0; m < onPropertyList.getLength(); ++m)
	    		   	    		        {
	    		   	    		        	

	    		   	    		            Element onProperty = (Element) onPropertyList.item(m);
	    		   	    		            onPropertyText = onProperty.getAttribute("rdf:resource").replace("#", "");
	    		   	    		         onPropertyText_original = onProperty.getAttribute("rdf:resource").replace("#", "");
	    		   	    		            
		    		   	    		 	    if(onPropertyText.contains(".owl"))	
			    		   	    	         {
			    		   	    	            	
		    		   	    		 	    onPropertyText = 	onPropertyText.substring(onPropertyText.indexOf("owl")+3);
			    		   	    	         }
	    		   	    		            
	    		   	    		    	}
	    		   	    			    
	    		   	    			    
	    		   	    				if(onPropertyText.equals(axiom))
	    		   		    			{      
	    		   	    			    
	    		   	    			 NodeList hasValueList = subClass.getElementsByTagName("owl:hasValue");
	    		    		      
	    		   	    			    
	    		    		         	for (int m = 0; m < hasValueList.getLength(); ++m)
	    		   	    		        {
	    		   	    		        	
	    	   	    		            Element hasValue = (Element) hasValueList.item(m);
	    	   	    		            hasValueText = hasValue.getAttribute("rdf:resource").replace("#", "");
	    	   	    		            
	    	   	    		         hasValueText_original = hasValue.getAttribute("rdf:resource").replace("#", "");
	    	   	    		         
	    	   	    		         if(hasValueText.contains(".owl"))	
	    		   	    	         {
	    		   	    	            	
	    	   	    		        	hasValueText = 	hasValueText.substring(hasValueText.indexOf("owl")+3);
	    		   	    	         }
	    	   	    		         
	    	   	    		         
	    	   	    		         
	    		   	    		    	}
	    		   	    			    
	    		    		         	
	    		    		         	
	    		   	    			    

	    		    	    		       if(!hasValueText.isEmpty()){


	    		    	    					
	    		    	    				System.out.println(" <!-- Has values-->\n\n\n\n");		    	
	 	    		    	  	    		System.out.println(" <!-- Extracted from: "+file.getName()+" -->\n\n");
	    		    	    		   		System.out.println("	<owl:Class rdf:"+key+"=\"" + HeadNode_original+ "\">\n");
	        		    					System.out.println("		<rdfs:subClassOf>\n");
	        		    					System.out.println("			<owl:Restriction>\n");
	    	    		    				System.out.println("				<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
	    	    		    				System.out.println("				<owl:hasValue rdf:datatype=\"" + hasValueText_original + "\"/>\n");
	    	    		    				System.out.println("			</owl:Restriction>\n");
	    	    		    				System.out.println("		</rdfs:subClassOf>\n");
	    	    		    				System.out.println("	</owl:Class>\n\n");
	    	    		    				
	   
	    	    		    				
	    		    	    		       }
	    		   
	    		    	   

	    		    		        }//---new if end
	    		   	    			 
	    		    		    	}
	    		    		        
	    		    			
	    		    			
	    		    			
	    		    			
	    		    			
	    		    		}
	    	    		
	    	    		
	    	    	
	    	    	
	    	    		
	    	    		//===
	    	    		
	 
	    	    		
	    	    		//====
	    	    		
	    	    	}
	    	        
	    		}
	    	}
		
	    	
	
		
		    	}
			}
	
		if(flag)
			System.out.println("</rdf:RDF>");
	    } catch (Exception e) {
			e.printStackTrace();
		    }

	}   
/*
	private static void disjoint(File[] listOfFiles,  String axiom, BufferedWriter br) {
		
		
		
		String Search = "";
		String key = "about";
		
		 try {
		    	axiom = axiom.replace("http://www.w3.org/2006/time#", "");
		    	
		    	File SubClassOf = null;
		    	
		    	if(Naming.isEmpty()){
		    		SubClassOf = new File(axiom+".owl");
		    	if (!SubClassOf.exists()) {
					SubClassOf.createNewFile();
				}
		     
		    	Search = axiom;
		    	}
		    	else{
		    		SubClassOf = new File(Naming+".owl");
			    	if (!SubClassOf.exists()) {
						SubClassOf.createNewFile();
					}
			        
			    	Search = Naming;
			    
		    	}
			
			for (File file : listOfFiles) {

				if (file.isFile()) {   //OuterIF
					

		    	File fXmlFile = new File("F:\\semi\\"+file.getName());
		    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    	Document doc = dBuilder.parse(fXmlFile);
		    	
				
		    	
		    	doc.getDocumentElement().normalize();
		    	
		    	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		     
		    	NodeList nList = doc.getElementsByTagName("owl:Class");

		    	for (int temp = 0; temp < nList.getLength(); temp++) {
		    	
			    	
		    		Node nNode = nList.item(temp);

		    		
		    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		     
		    			Element eElement = (Element) nNode;
		    			
		    			
		    			String HeadNode_original =  eElement.getAttribute("rdf:about");
		    			if(HeadNode_original == "")
		    				{HeadNode_original =  eElement.getAttribute("rdf:ID");key = "ID";}
		    			
		    		
		    			String HeadNode =  eElement.getAttribute("rdf:about");
		    			if(HeadNode == "")
		    			{HeadNode =  eElement.getAttribute("rdf:ID"); key = "ID";}

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
		    		    			String   allValueText="";
		    		    			
		    		    			String onPropertyText_original ="";
		    		    			String   allValueText_original ="";
		    		    			
		    		    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
		    		    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
		    		    		        {
		    		    		            Element Restriction = (Element) RestrictionList.item(l);
		    		    	    			
		    		    		            NodeList onPropertyList = Restriction.getElementsByTagName("owl:onProperty");
		    		    		            
		    		   	    			    for (int m = 0; m < onPropertyList.getLength(); ++m)
		    		   	    		        {
		    		   	    		        	

		    		   	    		            Element onProperty = (Element) onPropertyList.item(m);
		    		   	    		           onPropertyText_original = onProperty.getAttribute("rdf:resource");
		    		   	    		           onPropertyText =  onProperty.getAttribute("rdf:resource").replace("#", "");
			    		   	    		 	    if( onPropertyText.contains(".owl"))	
				    		   	    	         {
				    		   	    	            	
			    		   	    		 	    onPropertyText = 	 onPropertyText.substring( onPropertyText.indexOf("owl")+3);
				    		   	    	         }
			    		   	  
		    		   	    		    	}
		    		   	    			    
		    		   	    		
		    		   	    				if( onPropertyText.equals(axiom))
		    		   		    			{    

		    		    		         NodeList allValueList = subClass.getElementsByTagName("owl:allValuesFrom");
		    		   	    			    

		    		   	    			    for (int m = 0; m < allValueList.getLength(); ++m)
		    		   	    		        {

		    	   	    		           Element allValue = (Element) allValueList.item(m);
		    	   	    		           
		    	   	    		           
		    	   	    		        allValueText_original = allValue.getAttribute("rdf:resource");
		    	   	    		        	allValueText = allValue.getAttribute("rdf:resource").replace("#", "");
		    	   	    		        	if( allValueText.contains(".owl"))	
			    		   	    	         {
			    		   	    	            	
		    	   	    		        		allValueText = 	 allValueText.substring( allValueText.indexOf("owl")+3);
			    		   	    	         }
		    	   	    		        	
		    	   	    		        	
		    		   	    		    	}
		    		   	    			    

		    		    	    		      if(!allValueText.isEmpty()){
		    		    	    		    
		    		    	  	    			
		    		    	    
		    		    	    			    	FileWriter fw = new FileWriter(SubClassOf.getName(),true);
		    		    	    					BufferedWriter bw = new BufferedWriter(fw);
		    		    	    					
		    		    	    					bw.write(" <!-- All values from-->\n\n\n\n");	
		    		    	  	    			
		    		    	    					bw.write(" <!-- Extracted from: "+file.getName()+" -->\n\n");
		    		    	    		    	   
		    		    	    		      		bw.write("	<owl:Class rdf:"+key+"=\"" + HeadNode_original+ "\">\n");
			        		    					bw.write("		<rdfs:subClassOf>\n");
			        		    					bw.write("				<owl:Restriction>\n");
			    	    		    				bw.write("					<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
			    	    		    				bw.write("					<owl:allValuesFrom rdf:resource=\"" + allValueText_original + "\"/>\n");
			    	    		    				bw.write("				</owl:Restriction>\n");
			    	    		    				bw.write("		</rdfs:subClassOf>\n");
			    	    		    				bw.write("	</owl:Class>\n\n\n\n\n\n\n");
			    	    		    				bw.close();
			    	    		    				
			 
		    		    	   	    		   }
		    		    	    		      
		  
		    		    		   	
		    		   	    		        
		    		    		        } //-- end of new if

		    		    		    		
		    		    		    	}
		    		    		        
		    		    			
		    		    			
		    		    			
		    		    			
		    		    			
		    		    		}
		    	    		

		    	      
		    	    		
		    	    	}
		    	        
		   
		    	        
		    		}
		    	}
			
		    	
		
			
			    	}
				}
		
			
			
		    } catch (Exception e) {
				e.printStackTrace();
			    }
		
		
		

	}   

	
	
	*/
	
	
}
