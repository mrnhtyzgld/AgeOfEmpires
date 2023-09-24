
public interface MapInterface {
	default public void print() throws AgeOfEmpiresException {
		System.out.println(status());
		
	}

	default public String status() throws AgeOfEmpiresException {
		String s = "";
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<100;j++)
				s +="_";
			s += "\n";
		}
		return s;
		
	}
	
}
