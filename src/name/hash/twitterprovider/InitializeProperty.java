package name.hash.twitterprovider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * twitter4jが利用するプロパティファイルがまだ存在していなかった時に
 * プロパティファイルの生成を行う
 * 
 * @author User
 */
public class InitializeProperty implements DialogInputListener {
	Twitter twitter;
	State state = State.start;
	String uri;
	private RequestToken requestToken;

	enum State {
		start, end
	}

	public InitializeProperty(String toURI) {
		uri = toURI;
		twitter = new TwitterFactory().getInstance();
		initializeSetting();
	}

	private void initializeSetting() {
		OAuthDialog.createDialog(getRequestURL(), this);
	}

	boolean isSettingEnd() {
		if (state == State.end) {
			return true;
		}
		return false;
	}

	/**
	 * Get request token URL
	 * 
	 * @return success
	 *         request token URL
	 * @return failed
	 *         "http://example.com/error"
	 */
	private String getRequestURL() {
		// TODO：秘密キーを公開しているので,リリース時には取り替える必要がある
		try {
			twitter.setOAuthConsumer("dAIeKD8aBEWjwdyyRkz1g", "S0I6BtqfJo3EkbZWi5EDyRA5u3HhVVePj2ltBJiW6UI");
			requestToken = twitter.getOAuthRequestToken();
			return requestToken.getAuthenticationURL();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		// Failed to get URL
		return "http://example.com/error";
	}

	@Override
	public void update(String pinCode) {
		doOAuth(pinCode);
	}

	/**
	 * OAuth認証によってオースアクセストークンを取得する
	 * 
	 * @param pinCode
	 */
	private void doOAuth(String pinCode) {
		try {
			AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(requestToken, pinCode);
			saveAccessToken(oAuthAccessToken);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	/**
	 * アクセストークンを永続化する
	 * 
	 * @param oAuthAccessToken
	 */
	private void saveAccessToken(AccessToken oAuthAccessToken) {
		String token = oAuthAccessToken.getToken();
		String tokenSecret = oAuthAccessToken.getTokenSecret();
		System.out.println(token + ":" + tokenSecret);

		File secureDirectory = new File(LoadConfigureProperty.SECURE_ACCESS_TOKEN_DIR);
		File accessTokenFile = new File(LoadConfigureProperty.SECURE_ACCESS_TOKEN);
		if (!secureDirectory.exists()) {
			secureDirectory.mkdirs();
		}
		try (FileWriter fileWriter = new FileWriter(accessTokenFile);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			bufferedWriter.write(token + "\n");
			bufferedWriter.write(tokenSecret + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
