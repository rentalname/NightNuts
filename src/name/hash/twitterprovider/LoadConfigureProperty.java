package name.hash.twitterprovider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadConfigureProperty {
	private String consumerKey;
	private String consumerKeySecret;
	private String accessToken;
	private String accessTokenSecret;

	public LoadConfigureProperty() {
		try (InputStream oAuthFile = getClass().getClassLoader().getResourceAsStream("oauthfile");
				BufferedReader br = new BufferedReader(new InputStreamReader(oAuthFile))) {
			if (oAuthFile == null) {
				System.err.println("resouce is null");
			}
			consumerKey = br.readLine();
			consumerKeySecret = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File acceseTokenFile = new File(InitializeProperty.SECURE_ACCESS_TOKEN);
		try (BufferedReader br = new BufferedReader(new FileReader(acceseTokenFile))) {
			accessToken = br.readLine();
			accessTokenSecret = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerKeySecret() {
		return consumerKeySecret;
	}
}
