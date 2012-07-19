package name.hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TwitterApiConectionService {
	public static final String XML_OUTPUT = "user_timeline.xml";

	private final String uri = "api.twitter.com";
	private final int port = 80;

	private final String screenName = "rentalname_dev";
	private final String path = "/1/statuses/user_timeline.xml";
	private String query = "?screen_name=" + screenName;
	
	public boolean connect() {
		try (Socket socket = new Socket(uri, port);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF8"));
				BufferedWriter sWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedWriter fWriter = new BufferedWriter(new FileWriter(XML_OUTPUT))) {
			sWriter.write("GET " + path + query + " HTTP/1.1\n");
			sWriter.write("Host:" + uri + ":" + port + "\n");
			sWriter.write("\n");
			sWriter.flush();
			String bodyString = removeHeaderString(reader);
			fWriter.write(bodyString);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (UserNotFoundExeption e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	/*
	 * api.twitterへ接続する際のクエリーを受け取って,接続する
	 */
	public boolean connect(String query){
		this.query = query;
		return connect();
	}

	private String removeHeaderString(BufferedReader reader) throws IOException, UserNotFoundExeption {
		final int HEADER_LINE = 17;
		StringBuilder builder = new StringBuilder();
		String str;
		int line = 0;
		while ((str = reader.readLine()) != null) {
			if (line > HEADER_LINE) {
				builder.append(str).append("\n");
				System.out.println(str);
			} else {
				line++;
				if(str.equals("Status: 404 Not Found")){
					//ユーザが見つからなかったときの処理
					//新たなユーザの指定を促す
					throw new UserNotFoundExeption("User with the specified name does not exist");
				}
				System.out.println("SKIP:" + str);
			}
			if (str.equals("</statuses>")) {
				break;
			}
		}
		return builder.toString();
	}
}
