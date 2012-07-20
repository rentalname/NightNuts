package name.hash.twitterprovider;

import org.junit.Assert;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Twiter4JCliant {
	private Twitter tw = TwitterFactory.getSingleton();
	private Paging page = new Paging(1);

	public void getUserTimeLine(String name) {
		try {
			ResponseList<Status> list = tw.getUserTimeline(name);
			showStatus(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	private void showStatus(ResponseList<Status> list) {
		for (Status s : list) {
			System.out.println(s.getText());
		}
	}

	public void getHomeTimeLine() {
		try {
			ResponseList<Status> list = tw.getHomeTimeline();
			showStatus(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void getHomeTimeLine(Paging p) {
		try {
			ResponseList<Status> list = tw.getHomeTimeline(p);
			showStatus(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void getNextHomeTimeLine() {
		page.setPage(page.getPage() + 1);
		Assert.assertTrue(page.getPage() > 0);
		getHomeTimeLine(page);
	}

}
