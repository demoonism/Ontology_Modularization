package classhierarchy;

import java.util.ArrayList;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pair p1 = new Pair("hehe","hahah");
		ArrayList<Pair> checker = new ArrayList<Pair>();
		checker.add(p1);
		
		Pair p2 = new Pair("hehe","hahah");
		
		
		for(Pair temp : checker)
		if (temp.getX().equals(p2.getX()) &&temp.getY().equals(p2.getY()))
		System.out.println("test true");
	}

}
