
import java.util.ArrayDeque;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * This thread act as a delayed mechanism. We want to avoid sending too many replies in a very short period of time.
 * This also reduces the amount of simultaneous API calls.
 * @author Simon Huang
 *
 */
public class TweetBuffer extends Thread {

	private static ArrayDeque<String> tweetQueue = new ArrayDeque<String>();
	private static ArrayDeque<String[]> APIBuffer = new ArrayDeque<String[]>();
	
	private final int API_DELAY = 2000; //API Buffer delay
	private final int REPLY_DELAY = 5000; 

	
	public void run() {
		System.out.println("TweetBuffer initialized!");
		int totalDelay = 0;
		//Constantly loop for new tweets being fed.
		while (true) {
			//===============
			// START REPLY BUFFER
			//===============
			if((totalDelay >= REPLY_DELAY) && !tweetQueue.isEmpty()){ //If the queue is not empty, pop elem so it can be replied.
				
				String toReply = tweetQueue.pop();
				
				
				//Send tweet
				Twitter twitter = TwitterFactory.getSingleton();
			    Status status;
				try {
					status = twitter.updateStatus(toReply);
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				System.out.println(toReply); //mock tweet reply
			
				
				totalDelay = 0; //Reset total delay
			}
			//===============
			// END REPLY BUFFER
			//===============
			
			//===============
			// START API BUFFER
			//===============
			/*
			 * [0]: tweet
			 * [1]: location
			 * [2]: username
			 */
			if(!APIBuffer.isEmpty()){
				String[] toSend = APIBuffer.pop();
				//Send to keyword extractor. API has limit of 1 call per sec
				NlpApi process = new NlpApi(toSend[0],toSend[1],toSend[2]);
				
				//Process_Dummy.processTweet(toSend[0], toSend[1], toSend[2]);
				System.out.println("API POP! POP!");
			} 
			
			//===============
			// END REPLY BUFFER
			//===============
			try {
				totalDelay += API_DELAY;
				Thread.sleep(API_DELAY); //Delay mech
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * Add a reply to the buffer queue. Method used by the YP class.
	 * Twitter automatically shortens URL if overall character count is >140
	 * @param URL URL to the website
	 * @param screenName Username for @mention
	 */
	public static synchronized void addToBuffer(String URL, String screenName){ 
		String mention = "@" + screenName;
		
		//Randomize content of tweets to avoid bot like behaviours. Try to keep them short.
		String content[] = {
				"I have found some stores nearby:",
				"Here's what I found:",
				"There you go:",
				"Some results for you:"
		};
		
		//URL
		String resultURL = "http://localhost/pinit/Search/index.html?userid=" + URL;
		
		String toReply = mention + " " + content[(int) (Math.random() * content.length)] + " " + resultURL;
		tweetQueue.add(toReply);
		//System.out.println("Tweet added to reply queue");
	}
	
	/**
	 * Adds tweet info to the buffer so that the next API callsvvvrever
	 * @param args
	 */
	public static synchronized void addToAPIBuffer(String[] args){
		APIBuffer.add(args);
	}
	
}
