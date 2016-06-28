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

class compareFile {

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
	      
		BufferedReader brin = null;
		BufferedReader brout = null;
		String  sCurrentLine;
		String  NextLine;
		
		File file = new File("F:\\SweetAll\\RoarSweet.owl");



   
    	
   
	
				try {

					brin = new BufferedReader(new FileReader(file.getPath()));

					int index = 1;
					
					while ((sCurrentLine = brin.readLine()) != null) {
						
						
				
						
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
							    	
									bw.write("<?xml version=\"1.0\"?>\n\n\n\n");
									bw.write("<!DOCTYPE rdf:RDF [\n");
									bw.write("				<!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >\n");
									bw.write("				<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >\n");
									bw.write("				<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >\n");
									bw.write("				<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\n");
									bw.write("]>\n\n\n\n\n");

									bw.write("<rdf:RDF xmlns=\"http://www.semanticweb.org/xians/ontologies/2016/custMod#\"\n");
									bw.write("         xml:base=\"http://www.semanticweb.org/xians/ontologies/2016/custMod\"\n");
									bw.write("         xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n");
									bw.write("         xmlns:owl=\"http://www.w3.org/2002/07/owl#\"\n");
									bw.write("         xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\n");	
									bw.write("         xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n");
									bw.write("         <owl:Ontology rdf:about=\"http://www.semanticweb.org/xians/ontologies/2016/custMod\">\n");
									bw.write("         <rdfs:label>SWEET Ontology Remodularization</rdfs:label>\n");
									bw.write("         <owl:versionInfo>2.3</owl:versionInfo>\n");
									bw.write("         </owl:Ontology>\n\n\n\n\n");
							     	
					        	
		        		       	  if(next.contains("--sub property:"))
					        	  {
		        		       	
		        		       		
		        		       		newsub = next.substring(15);
		        		       		System.out.println("newsub------------"+newsub);
					      
					        		  		ObjectProperty(file,newsub,bw);
					        		  		AllValueFrom(file,newsub,bw);
					        		  		SomeValue(file,newsub,bw);
					        		  		HasValue(file,newsub,bw);
					        		  		
					        	  }
		        		       	  
		        		       	  
		        		       	  bw.close();
		        		       	  fw.close();
		        		       	  
		        		       	  fw = new FileWriter(module.getName(),true);
		        		       	  bw = new BufferedWriter(fw);
						    	
					        	  if((adjcent = br.readLine()).contains("Super class:"))
					        	  {
					        		  		   	newsuper =  adjcent.substring(12);
					        		  		   	System.out.println("-------super-----"+newsuper);
							        		  	
 					        		  			ObjectProperty(file,newsuper,bw);
 					        		  			AllValueFrom(file,newsuper,bw);
 					        		  			SomeValue(file,newsuper,bw);
 					        		  			HasValue(file,newsuper,bw);

					        	  }

					        	  bw.write("</rdf:RDF>");
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
		
		

		return;
    }
	
	private static void ObjectProperty(File listOfFiles, String axiom, BufferedWriter br) {

	    try {
	    	
	    	
	    
			
		 	File fXmlFile = listOfFiles;
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
			NodeList ObjectPropertyList = doc.getElementsByTagName("owl:ObjectProperty");
			
			
			if (listOfFiles.isFile()) {   //OuterIF

			
	    	doc.getDocumentElement().normalize();
	     

	    	for (int temp = 0; temp < ObjectPropertyList.getLength(); temp++) {
	    		Node nNode = ObjectPropertyList.item(temp);
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {        // if start--
	 
	    			Element eElement = (Element) nNode;
	     
	    			String HeadNode =  eElement.getAttribute("rdf:about");	    			
	    			if(HeadNode == "")
	    			HeadNode =  eElement.getAttribute("rdf:ID");

	    			String HeadNode_original =  eElement.getAttribute("rdf:about");
	    			//String HeadNode =  eElement.getAttribute("rdf:about");
	    			if(HeadNode_original == "")
	    				HeadNode_original =  eElement.getAttribute("rdf:ID");

  	    	            	
	    		    HeadNode = 	HeadNode.substring(HeadNode.indexOf("#")+1);
  	    	       
	    		    
	    			if(HeadNode.equals(axiom))
	    			{ 
	    			

	    			br.write(" <!-- Object Properties -->\n\n\n\n");	
	    	
	    			
	    			br.write(" <!-- Extracted from: "+listOfFiles.getName()+" -->\n\n");
	    			
	    			NodeList RangeList = eElement.getElementsByTagName("rdfs:range");

	    			//if(RangeList.getLength() ==0)
		    		br.write("	<owl:ObjectProperty rdf:about=\"" + HeadNode_original+ "\">\n");
    		
		    
	    			
	    			String RangeText = "";
	    			String RangeText_original = "";
	    			
	    			
	    			for (int j = 0; j < RangeList.getLength(); j++)
	    	        {
	    	         Element Range = (Element) RangeList.item(j);
	    	             RangeText = Range.getAttribute("rdf:resource").replace("#", "");
	    	             RangeText_original = Range.getAttribute("rdf:resource");
	    	               
	    		        if(!RangeText.isEmpty())
	    					br.write("		<rdfs:range rdf:resource=\""+RangeText_original+"\"/>\n");
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
	    					br.write("		<rdfs:domain rdf:resource=\""+DomainText_original+"\"/>\n");
	    	        }
		    		
	    		     
	    			NodeList EquivList = eElement.getElementsByTagName("owl:equivalentProperty");
	    			String EquivText = "";
	    			for (int j = 0; j < EquivList.getLength(); j++)
	    	        {
	    	         Element Equiv = (Element) EquivList.item(j);

	    	             EquivText = Equiv.getAttribute("rdf:resource");
	    	               
	    		        if(!EquivText.isEmpty())
	    					br.write("		<owl:equivalentProperty rdf:resource=\""+EquivText+"\"/>\n");
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

	    					br.write("		<rdf:type rdf:resource=\""+TypeText+"\"/>\n");

	    	    		}
	
	    	    		
	    	        }
	    	        
	    	        br.write("	</owl:ObjectProperty>\n\n\n\n\n\n");	
	    	       
	    			} // end of if #axiom
	    	
	    
	    	        
	    						}   // if ends--

	    							}

		    	}
			
		
		
	    } catch (Exception e) {
			e.printStackTrace();
		    }
		
	}   //-Equi Function end
	
	private static void AllValueFrom(File listOfFiles,  String axiom, BufferedWriter br) {


		String key = "about";
	    try {

	    

			if (listOfFiles.isFile()) {   //OuterIF
				

	    	File fXmlFile = listOfFiles;
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

	    			HeadNode = 	HeadNode.substring(HeadNode.indexOf("#")+1);
  	    	         
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
	    		   	    		            onPropertyText =  onProperty.getAttribute("rdf:resource");

			    		   	    	            	
		    		   	    		 	    onPropertyText = 	 onPropertyText.substring( onPropertyText.indexOf("#")+1);
			    		   	    	       
		    		   	  
	    		   	    		    	}
	    		   	    			    
	    		   	    		
	    		   	    				if( onPropertyText.equals(axiom))
	    		   		    			{    

	    		    		         NodeList allValueList = subClass.getElementsByTagName("owl:allValuesFrom");
	    		   	    			    

	    		   	    			    for (int m = 0; m < allValueList.getLength(); ++m)
	    		   	    		        {

	    	   	    		           Element allValue = (Element) allValueList.item(m);
	    	   	    		           
	    	   	    		           
	    	   	    		           	allValueText_original = allValue.getAttribute("rdf:resource");
	    	   	    		        	allValueText = allValue.getAttribute("rdf:resource");
		    		   	    	            	
	    	   	    		        	allValueText = 	 allValueText.substring( allValueText.indexOf("#")+1);
		    		   	    	         
	    	   	    		        	
	    	   	    		        	
	    		   	    		    	}
	    		   	    			    

	    		    	    		      if(!allValueText.isEmpty()){
	    		    	    		    
	    		    	    					
	    		    	    					br.write(" <!-- All values from-->\n\n\n\n");	
	    		    	  	    			
	    		    	    					br.write(" <!-- Extracted from: "+listOfFiles.getName()+" -->\n\n");
	    		    	    		    	   
	    		    	    		      		br.write("	<owl:Class rdf:about=\"" + HeadNode_original+ "\">\n");
		        		    					br.write("		<rdfs:subClassOf>\n");
		        		    					br.write("				<owl:Restriction>\n");
		    	    		    				br.write("					<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
		    	    		    				br.write("					<owl:allValuesFrom rdf:resource=\"" + allValueText_original + "\"/>\n");
		    	    		    				br.write("				</owl:Restriction>\n");
		    	    		    				br.write("		</rdfs:subClassOf>\n");
		    	    		    				br.write("	</owl:Class>\n\n\n\n\n\n\n");
		    	    		    			
		    	    		    				
		 
	    		    	   	    		   }
	    		    	    		      
	  
	    		    		   	
	    		   	    		        
	    		    		        } //-- end of new if

	    		    		    		
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
	
	private static void SomeValue(File listOfFiles,  String axiom, BufferedWriter br) {
		
		
		String Search = "";
		String key ="about";
	    try {
	  
	  	    	
	

			if (listOfFiles.isFile()) {   //OuterIF

	    	File fXmlFile = listOfFiles;
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
	    			{HeadNode_original =  eElement.getAttribute("rdf:ID");key = "ID";}
	    			
	    			
	    			
	    			String HeadNode =  eElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			{HeadNode =  eElement.getAttribute("rdf:ID");key = "ID";}
	    			
	
  	    	            	
	    	 	    	HeadNode = 	HeadNode.substring(HeadNode.indexOf("#")+1);
  	    	
	    	       
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
	    		   	    		            onPropertyText = onProperty.getAttribute("rdf:resource");
	    		   	    		            
	    		   	    		            onPropertyText_original = onProperty.getAttribute("rdf:resource");
		
		    		   	    		 	    onPropertyText = 	onPropertyText.substring(onPropertyText.indexOf("#")+1);
			    		   	    	         
		    		   	    		 	    
	    		   	    		    	}
	    		   	    			    
	    		   	    				if(onPropertyText.equals(axiom))
	    		   		    			{     
	    		   	    					
	    		   	    			    
	    		   	    			    NodeList someValueList = subClass.getElementsByTagName("owl:someValuesFrom");
  
	    		   	    			    for (int m = 0; m < someValueList.getLength(); ++m)
	    		   	    		        {

	    	   	    		            Element someValue = (Element) someValueList.item(m);
	    	   	    		        	someValueText = someValue.getAttribute("rdf:resource");
	    	   	    		        	someValueText_original = someValue.getAttribute("rdf:resource");

	    		   	    		 	    someValueText = 	someValueText.substring(someValueText.indexOf("#")+1);
		    		   	    	         
	    		   	    		    	}
	    		   	    			    

	    		   
	    		    	    		        if(!someValueText.isEmpty()){
	    		    	    		        	
	    		    	    				br.write(" <!-- Some values from-->\n\n\n\n");	
	 	    		    	  	    			
	 	    		    	  	    		br.write(" <!-- Extracted from: "+listOfFiles.getName()+" -->\n\n");
	 	    		    	    		    	   	
	    		    	    		   		br.write("	<owl:Class rdf:about =\"" + HeadNode_original+ "\">\n");
	        		    					br.write("		<rdfs:subClassOf>\n");
	        		    					br.write("			<owl:Restriction>\n");
	    	    		    				br.write("				<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
	    	    		    				br.write("				<owl:someValuesFrom rdf:resource=\"" + someValueText_original + "\"/>\n");
	    	    		    				br.write("			</owl:Restriction>\n");
	    	    		    				br.write("		</rdfs:subClassOf>\n");
	    	    		    				br.write("	</owl:Class>\n\n\n");
	    	    		    	
	    	    		    				
	    	    		    				
	
	    	    		    			    
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
	
	
		
	    } catch (Exception e) {
			e.printStackTrace();
		    }

	}  

	private static void HasValue(File listOfFiles,  String axiom, BufferedWriter br) {
		
		
		String Search = "";
		String key = "about";
		
  
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
	    			{HeadNode_original =  eElement.getAttribute("rdf:ID");key = "ID";}
	    			
	    			
	    			
	    			String HeadNode =  eElement.getAttribute("rdf:about");
	    			if(HeadNode == "")
	    			{HeadNode =  eElement.getAttribute("rdf:ID");key = "ID";}
	    			
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
	    		   	    		            onPropertyText = onProperty.getAttribute("rdf:resource");
		    		   	    		 	    onPropertyText = 	onPropertyText.substring(onPropertyText.indexOf("#")+1);
			    		   	    	         
	    		   	    		            
	    		   	    		            onPropertyText_original = onProperty.getAttribute("rdf:resource");
	    		   	    		            
		    		   	    		 	
			    		   	    	         
	    		   	    		            
	    		   	    		    	}
	    		   	    			    
	    		   	    			    
	    		   	    				if(onPropertyText.equals(axiom))
	    		   		    			{      
	    		   	    			    
	    		   	    			 NodeList hasValueList = subClass.getElementsByTagName("owl:hasValue");
	    		    		      
	    		   	    			    
	    		    		         	for (int m = 0; m < hasValueList.getLength(); ++m)
	    		   	    		        {
	    		   	    		        	
	    	   	    		            Element hasValue = (Element) hasValueList.item(m);
	    	   	    		            hasValueText = hasValue.getAttribute("rdf:resource");
	    	   	    		            
	    	   	    		            hasValueText_original = hasValue.getAttribute("rdf:resource");
	    	   	    		         
	    	   	    		   
	    		   	    	            	
	    	   	    		        	hasValueText = 	hasValueText.substring(hasValueText.indexOf("#")+1);
	    		   	    	  
	    	   	    		         
	    	   	    		         
	    	   	    		         
	    		   	    		    	}
	    		   	    			    
	    		    		         	
	    		    		         	
	    		   	    			    

	    		    	    		       if(!hasValueText.isEmpty()){


	    		    	    					
	    		    	    				br.write(" <!-- Has values-->\n\n\n\n");		    	
	 	    		    	  	    		br.write(" <!-- Extracted from: "+listOfFiles.getName()+" -->\n\n");
	    		    	    		   		br.write("	<owl:Class rdf:about =\"" + HeadNode_original+ "\">\n");
	        		    					br.write("		<rdfs:subClassOf>\n");
	        		    					br.write("			<owl:Restriction>\n");
	    	    		    				br.write("				<owl:onProperty rdf:resource=\"" + onPropertyText_original + "\"/>\n");
	    	    		    				br.write("				<owl:hasValue rdf:datatype=\"" + hasValueText_original + "\"/>\n");
	    	    		    				br.write("			</owl:Restriction>\n");
	    	    		    				br.write("		</rdfs:subClassOf>\n");
	    	    		    				br.write("	</owl:Class>\n\n");
	    	    		    				
	   
	    	    		    				
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
			
	
	    } catch (Exception e) {
			e.printStackTrace();
		    }

	}   

	
	
}
