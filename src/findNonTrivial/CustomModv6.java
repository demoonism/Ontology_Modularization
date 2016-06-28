package findNonTrivial;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


class CustomModv6 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File folder = new File("F:\\assignment");
		File[] listOfFiles = folder.listFiles();
		Retrive(listOfFiles);

	}

	
	public static void Retrive (File[] listOfFiles) {
	      
		BufferedReader br = null;
		String  sCurrentLine;

		int counter  = 1;
	
	
		for (File ontoFile : listOfFiles) {
		    if (ontoFile.isFile()) {   //OuterIF
	

				System.out.println(ontoFile.getName());
				

		    }
		}
	
		
		return;
    }
	

}
