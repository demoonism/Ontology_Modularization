package renamingScript;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileRenaming {



	
	public static void main(String[] args) throws IOException{
		
		File folder = new File("F:\\renaming");
		File[] listOfFiles = folder.listFiles();
		
		ObjectProperty(listOfFiles);
		
	}
	


private static void ObjectProperty(File[] listOfFiles) {

	try {

		
		for (File file : listOfFiles) {
			ArrayList<String> FileName = new ArrayList<String>();
			

			if (file.isFile()) {

				File fXmlFile = file;
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				NodeList ObjectPropertyList = doc.getElementsByTagName("owl:ObjectProperty");
				for (int temp = 0; temp < ObjectPropertyList.getLength(); temp++) {
					Node nNode = ObjectPropertyList.item(temp);
				
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						
						String HeadNode = eElement.getAttribute("rdf:about");
						if (HeadNode == "") {
							HeadNode = eElement.getAttribute("rdf:ID");
						}

						HeadNode = HeadNode.substring(HeadNode.indexOf("#") + 1);
						
						FileName.add(HeadNode);
						
					} // if ends--

				}
				
				  File oldName = file;
				  File newName =  null;
				  
				  if(FileName.size()<2)
				  newName = new File("F:\\renaming\\"+FileName.get(0)+".owl");
				  else
				  newName = new File("F:\\renaming\\"+FileName.get(0)+"_"+FileName.get(2)+".owl");
			      
				  if(oldName.renameTo(newName)) {
			         System.out.println(oldName.getName()+" renamed as "+newName.getName());
			      } else {
			         System.out.println(oldName.getName()+" Error");
			      }
				
				

			}
		}
		
	
		
		

	} catch (Exception e) {
		e.printStackTrace();
	}

} // -Equi Function end

}

