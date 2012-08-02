package name.hash.twitterprovider;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.hash.TweetModel;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class Twiter4JCliant {
	private static final String TWITTER_PROPERTY_FILE = "twitter4j.properties";
	private Twitter tw = TwitterFactory.getSingleton();
	private Paging page = new Paging(1);
	private String userName;

	private Twiter4JCliant() {
		initializeSetting();
	}
	public static Twiter4JCliant cliantFactory(){
		return new Twiter4JCliant();
	}
	private void initializeSetting() {
		if (!checkExistProperty()) {
			OAuthDialog dialog = new OAuthDialog();
			dialog.setReqestURL(getRequestURL());
			dialog.setVisible(true);
		}
	}

	/**
	 * twitter4j.propertiesファイルの存在を確認する
	 */
	private boolean checkExistProperty() {
		File file = new File(TWITTER_PROPERTY_FILE);
		return file.exists();
	}

	/**
	 * Get request token URL
	 * 
	 * @return
	 *         request token URL
	 */
	private String getRequestURL() {
		try {
			 tw.setOAuthConsumer("dAIeKD8aBEWjwdyyRkz1g", "S0I6BtqfJo3EkbZWi5EDyRA5u3HhVVePj2ltBJiW6UI");
			RequestToken requestToken = tw.getOAuthRequestToken();
			return requestToken.getAuthenticationURL();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return "https://api.twitter.com/oauth/request_token";
	}

	public List<TweetModel> getHomeTimeLine() {
		return getHomeTimeLine(resetPage());
	}

	public List<TweetModel> getUserTimeLine(String name) {
		userName = name;
		try {
			ResponseList<Status> list = tw.getUserTimeline(name, resetPage());
			return convertList(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<TweetModel> getNextHomeTimeLine() {
		return getHomeTimeLine(followingPage());
	}

	public List<TweetModel> getNextUserTimeline() {
		return getUserTimeLine(followingPage());
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

	public List<TweetModel> getUserTimeLine(Paging p) {
		try {
			ResponseList<Status> userTimeline = tw.getUserTimeline(userName, p);
			return convertList(userTimeline);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private List<TweetModel> convertList(ResponseList<Status> list) {
		List<TweetModel> tmList = new ArrayList<>();
		for (Status s : list) {
			tmList.add(new TweetModel(s.getId(), s.getUser().getName(), s.getText(), s.getCreatedAt().toString()));
		}
		return tmList;
	}

	private Paging followingPage() {
		page.setPage(page.getPage() + 1);
		if (page.getPage() < 0) {
			System.err.println(getClass().getSimpleName() + ":Paging object is less than 0");
		}
		return page;
	}

	private Paging resetPage() {
		page.setPage(1);
		return page;
	}

	public void setPagingCount(int num) {
		page.setCount(num);
	}
}
