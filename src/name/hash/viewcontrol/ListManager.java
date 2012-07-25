package name.hash.viewcontrol;

import java.util.Collections;
import java.util.List;

import name.hash.QueryStringBuilder;
import name.hash.TweetModel;
import name.hash.TwitterApiConectionService;
import name.hash.TwitterXmlParser;

public class ListManager implements TwitterListManager {
	private long id = -1;
	private String userName;
	private int page = 1;
	private int count = 10;

	enum State {
		Home, User
	}

	public ListManager(String name) {
		userName = name;
	}

	@Override
	public List<TweetModel> getList() {
		boolean success = conection();

		if (success)
			return parse();
		else
			return Collections.emptyList();
	}

	public List<TweetModel> nextPage() {
		++page;
		return getList();
	}

	public void changeUser(String name) {
		userName = name;
		page = 1;
	}

	public void chamgeCount(int num) {
		count = num;
	}

	private boolean conection() {
		QueryStringBuilder builder = QueryStringBuilder.getBuilder(userName);
		builder.queryId(id);
		builder.queryPage(page);
		builder.queryCount(count);

		TwitterApiConectionService service = new TwitterApiConectionService();
		return service.connect(builder.buildQuery());
	}

	private List<TweetModel> parse() {
		TwitterXmlParser parser = new TwitterXmlParser();
		parser.parse(TwitterApiConectionService.XML_OUTPUT);
		return parser.getList();
	}

	@Override
	public List<TweetModel> getHomeTimeline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetModel> getUserTimeline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetModel> getMoreTimeline() {
		// TODO Auto-generated method stub
		return null;
	}
}
