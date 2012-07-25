package name.hash.twitterprovider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.hash.TweetModel;

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
	public List<TweetModel> getNextUserTimeline(){
		
	}
	public List<TweetModel> getUserTimeLine(String name) {
		try {
			ResponseList<Status> list = tw.getUserTimeline(name);
			return convertList(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<TweetModel> getNextHomeTimeLine() {
		page.setPage(page.getPage() + 1);
		Assert.assertTrue(page.getPage() > 0);
		return getHomeTimeLine(page);
	}

	public List<TweetModel> getHomeTimeLine(Paging p) {
		try {
			ResponseList<Status> list = tw.getHomeTimeline(p);
			return convertList(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<TweetModel> getHomeTimeLine() {
		return getHomeTimeLine(new Paging(1));
	}

	private List<TweetModel> convertList(ResponseList<Status> list) {
		List<TweetModel> tmList = new ArrayList<>();
		for (Status s : list) {
			tmList.add(new TweetModel(s.getId(), s.getUser().getName(), s.getText(), s.getCreatedAt().toString()));
		}
		return tmList;
	}

	@SuppressWarnings("unused")
	private void showStatus(ResponseList<Status> list) {
		for (Status s : list) {
			System.out.println(s.getText());
		}
	}

}
