package name.hash.viewcontrol;

import java.util.List;

import name.hash.TweetModel;

public interface TwitterListManager {

	// �p�[�X���ꂽ�f�[�^����TweetList���쐬����
	List<TweetModel> getList();

	List<TweetModel> getHomeTimeline();

	List<TweetModel> getUserTimeline();

	List<TweetModel> getMoreTimeline();
}
