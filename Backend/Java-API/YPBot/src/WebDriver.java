
public class WebDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NlpApi go = new NlpApi("I want a hamburger so badly");
		System.out.print(go.KeyPhrase());
	}

}
