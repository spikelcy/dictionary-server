package shared;

public class Message implements java.io.Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5773032079402931345L;
	public String name;
	 public String meaning;
	 public String function;
	 
	 public void meaningCheck() {
	      System.out.println("Meaning of " + name + " is " + meaning);
	   }
}
