package name.hash.viewcontrol;

import java.util.List;

import name.hash.TweetModel;

public interface TwitterListManager<T extends TweetModel> {

	// �p�[�X���ꂽ�f�[�^����TweetList���쐬����

	List<T> getHomeTimeline();

	List<T> getUserTimeline();

	List<T> getMoreTimeline();
}
