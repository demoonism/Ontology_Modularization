package CombineMod;

public class triple {
	private String a;
	private String b;
	private String c;
	
	public triple(String subject, String relation, String object){
		
		a = subject;
		b = relation;
		c = object;
	}
	
	public String getSubject(){		
		return a;
	}
	
	public String getRelation(){		
		return b;
	}
	
	public String getObject(){		
		return c;
	}
	
	public String getTriple(){		
		return a+b+c;
	}
	
	
	

}
