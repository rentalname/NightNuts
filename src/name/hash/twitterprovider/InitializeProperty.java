package name.hash.twitterprovider;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * twitter4j�����p����v���p�e�B�t�@�C�����܂����݂��Ă��Ȃ���������
 * �v���p�e�B�t�@�C���̐������s��
 * 
 * @author User
 */
public class InitializeProperty {
	Twitter twitter;
	State state = State.start;
	String uri;

	enum State {
		start, end
	}

	public InitializeProperty(String toURI) {
		uri = toURI;
		twitter = new TwitterFactory().getInstance();
	}

	private void initializeSetting() {
		OAuthDialog dialog = new OAuthDialog();
		dialog.setURL(getRequestURL());
		dialog.setVisible(true);
	}
	boolean isSettingEnd(){
		if(state == State.end){
			return true;
		}
		return false;
	}

	/**
	 * �f�o�b�O�pAPI
	 * OAuth�_�C�A���O�������I�ɋN������
	 */
	public void startOAuthDialog() {
		OAuthDialog dialog = new OAuthDialog();
		try {
			dialog.setURL(twitter.getOAuthRequestToken().getAuthenticationURL());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		dialog.setVisible(true);
	}

	/**
	 * Get request token URL
	 * 
	 * @return
	 *         request token URL
	 */
	private String getRequestURL() {
		// TODO�F�閧�L�[�����J���Ă���̂�,�����[�X���ɂ͎��ւ���K�v������
		try {
			twitter.setOAuthConsumer("dAIeKD8aBEWjwdyyRkz1g", "S0I6BtqfJo3EkbZWi5EDyRA5u3HhVVePj2ltBJiW6UI");
			RequestToken requestToken = twitter.getOAuthRequestToken();
			return requestToken.getAuthenticationURL();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return "http://example.com/error";
		// return "https://api.twitter.com/oauth/request_token";
	}
}
