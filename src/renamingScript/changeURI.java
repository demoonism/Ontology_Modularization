package renamingScript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class changeURI {
	
	public static void replaceSelected(File[] listOfFiles) throws IOException {
		
		int counter  = 1;
		
		for (File file : listOfFiles) {
	
			Path path = Paths.get(file.getPath());
			Charset charset = StandardCharsets.UTF_8;
			System.out.println(file.getName()+"  "+ counter);
			String content = new String(Files.readAllBytes(path), charset);
			//String content = new String(Files.readAllBytes(path));
			
			//content = content.replaceAll("rdf:about=\"http://www.semanticweb.org/xians/ontologies/2016/custMod\">", "rdf:about=\"http://www.semanticweb.org/xians/ontologies/2016/module"+counter+"\">");
			//content = content.replaceAll("xml:base=\"http://www.semanticweb.org/xians/ontologies/2016/custMod", "xml:base=\"http://www.semanticweb.org/xians/ontologies/2016/module"+counter);
			content = content.replaceAll("xmlns=\"http://www.semanticweb.org/xians/ontologies/2016/custMod", "xmlns=\"http://www.semanticweb.org/xians/ontologies/2016/module"+counter);
			
			
			
			Files.write(path, content.getBytes(charset));
			//Files.write(path, content.getBytes(charset));
			
			counter ++;
			
		
		}
	    
	    
	    
	    
	}

	public static void main(String[] args) {
		
		File folder = new File("F:\\RemodSWEET");
		File[] listOfFiles = folder.listFiles();
		try {
			replaceSelected(listOfFiles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}

}
