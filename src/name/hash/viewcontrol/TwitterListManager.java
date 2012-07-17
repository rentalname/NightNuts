package name.hash.viewcontrol;

import java.util.List;

import name.hash.TweetModel;

public interface TwitterListManager {

	// パースされたデータからTweetListを作成する
	List<TweetModel> getList();
}
