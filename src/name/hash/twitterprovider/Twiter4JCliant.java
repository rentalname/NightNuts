package name.hash.twitterprovider;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.hash.model.TweetModel;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twiter4JCliant {
	private Twitter tw;
	private Paging page = new Paging(1);
	private String userName;
	private static Twiter4JCliant instance;
	private boolean isSettingComplete;
	private boolean isReadFirstPage;

	/**
	 * �v���p�e�B�t�@�C��{@link InitializeProperty.SECURE_ACCESS_TOKEN}�̑��݂��m�F����,
	 * �t�@�C�������݂��Ȃ��ꍇ{@link InitializeProperty}�N���X����{@link OAuthDialog}
	 * �𐶐�����,�v���p�e�B�t�@�C���̐������s��
	 */
	private Twiter4JCliant() {
		// �ݒ�t�@�C�������݂��Ă��邱�Ƃ��m�F����,���݂��Ȃ���,�����ݒ�N���X���Ăяo��
		if (!checkExistProperty()) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					InitializeProperty property = new InitializeProperty(LoadConfigureProperty.SECURE_ACCESS_TOKEN);
					try {
						while (!property.isSettingEnd()) {
							Thread.sleep(60);
						}
						notifyAll();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (!checkExistProperty()) {
							Thread.sleep(60);
						}
						clientInitialize();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} else {
			clientInitialize();
		}
	}

	/**
	 * �ݒ�t�@�C����ǂݍ����,Twitter4J�C���X�^���X�̏��������s��
	 */
	private void clientInitialize() {
		isSettingComplete = true;
		LoadConfigureProperty prop = new LoadConfigureProperty();
		ConfigurationBuilder cBuilder = new ConfigurationBuilder();
		cBuilder.setDebugEnabled(true).setOAuthConsumerKey(prop.getConsumerKey())
				.setOAuthConsumerSecret(prop.getConsumerKeySecret()).setOAuthAccessToken(prop.getAccessToken())
				.setOAuthAccessTokenSecret(prop.getAccessTokenSecret());
		TwitterFactory tf = new TwitterFactory(cBuilder.build());
		tw = tf.getInstance();
	}

	/**
	 * twitter4j.properties�t�@�C���̑��݂��m�F����
	 */
	private boolean checkExistProperty() {
		File file = new File(LoadConfigureProperty.SECURE_ACCESS_TOKEN);
		return file.exists();
	}

	public static Twiter4JCliant getInstance() {
		if (instance == null) {
			instance = new Twiter4JCliant();
		}
		return instance;
	}

	/**
	 * �����̃^�C�����C����Ԃ�. �����ݒ肪�I�����Ă��Ȃ������ꍇ,��̃^�C�����C�����X�g��Ԃ�
	 * 
	 * @return �����̃^�C�����C��
	 */
	public List<TweetModel> getHomeTimeLine() {
		if (!isSettingComplete) {
			return Collections.emptyList();
		} else {
			isReadFirstPage = true;
		}
		return getHomeTimeLine(resetPage());
	}

	/**
	 * �w�肵�����[�U�̃^�C�����C����Ԃ� �����ݒ肪�I�����Ă��Ȃ������ꍇ,��̃^�C�����C�����X�g��Ԃ�
	 * 
	 * @param name
	 *            �Ώۃ��[�U�̃X�N���[���l�[��
	 * @return ���[�U�̃^�C�����C��
	 */
	public List<TweetModel> getUserTimeLine(String name) {
		userName = name;
		if (!isSettingComplete) {
			return Collections.emptyList();
		} else {
			isReadFirstPage = true;
		}
		try {
			ResponseList<Status> list = tw.getUserTimeline(name, resetPage());
			return convertList(list);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<TweetModel> getNextHomeTimeLine() {
		if (!isReadFirstPage) {
			getHomeTimeLine();
		}
		return getHomeTimeLine(followingPage());
	}

	public List<TweetModel> getNextUserTimeline() {
		if (!isReadFirstPage) {
			getUserTimeLine(userName);
		}
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
