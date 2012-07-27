package name.hash.viewcontrol;

import java.util.List;

import name.hash.TweetModel;

public interface TwitterListManager<T extends TweetModel> {

	// パースされたデータからTweetListを作成する

	List<T> getHomeTimeline();

	List<T> getUserTimeline();

	List<T> getMoreTimeline();
}
