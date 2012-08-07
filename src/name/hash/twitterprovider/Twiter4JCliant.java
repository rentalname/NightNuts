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

public class Twiter4JCliant{
	static final String TWITTER_PROPERTY_FILE = "twitter4j.properties";
	private Twitter tw;
	private Paging page = new Paging(1);
	private String userName;
	private static Twiter4JCliant instance;

	/**
	 * プロパティファイルの存在を確認して,ファイルが存在しない場合{@link InitializeProperty}クラスからプロパティファイルの生成を行う
	 */
	private Twiter4JCliant() {
		// 設定ファイルが存在していることを確認して,存在しない時,初期設定クラスを呼び出す
		if (!checkExistProperty()) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					InitializeProperty property = new InitializeProperty(TWITTER_PROPERTY_FILE);
					try {
						while (!property.isSettingEnd()) {
							Thread.sleep(200);
						}
						notifyAll();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} else {
			tw = TwitterFactory.getSingleton();
			return;
		}
		new Thread() {
			@Override
			public void run() {
				try {
					while (!checkExistProperty()) {
						Thread.sleep(100);
					}
					tw = TwitterFactory.getSingleton();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

		// 初期設定を待ち受けるためのポーリング
		try {
			while (tw == null) {
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * twitter4j.propertiesファイルの存在を確認する
	 */
	private boolean checkExistProperty() {
		File file = new File(Twiter4JCliant.TWITTER_PROPERTY_FILE);
		return file.exists();
	}

	public static Twiter4JCliant getInstance() {
		if (instance == null) {
			instance = new Twiter4JCliant();
		}
		return instance;
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
