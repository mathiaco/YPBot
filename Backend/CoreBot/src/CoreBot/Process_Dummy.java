//Simulates tweet being processed by other API

package CoreBot;

public class Process_Dummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Simulates tweet being extracted
	public static void processTweet(String tweet, String location, String screenName){
		System.out.println("KEYWORD EXTRACT HAS RECEIVED: " + tweet + " : " + location + " : " + screenName);
		String processed = tweet + " PROCESSED at " + location;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toURL(processed, screenName);
	}
	
	public static void toURL(String extractedWords, String screenName){
		System.out.println("URL GENERATOR HAS RECEIVED: " + extractedWords + " : " + screenName);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int uID = (int)(Math.random() * 100);
		String URL = "http://pinpoint.tech/?index=" + uID;
		TweetBuffer.addToBuffer(URL, screenName);
	}

}
