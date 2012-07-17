package name.hash.viewcontrol;

import java.util.List;

import name.hash.QueryStringBuilder;
import name.hash.TweetModel;
import name.hash.TwitterApiConectionService;
import name.hash.TwitterXmlParser;

public class Manager implements TwitterListManager {
	private long id = -1;
	private String userName;
	private int page;
	private int count = 10;

	@Override
	public List<TweetModel> getList() {
		conection();
		return parse();
	}

	public List<TweetModel> nextPage() {
		page++;
		return getList();
	}

	public List<TweetModel> changeUser(String name) {
		userName = name;
		page = 0;
		return getList();
	}

	public void chamgeCount(int num) {
		count = num;
	}

	private void conection() {
		QueryStringBuilder builder = QueryStringBuilder.getBuilder(userName);
		builder.queryId(id);
		builder.queryPage(page);
		builder.queryCount(count);

		TwitterApiConectionService service = new TwitterApiConectionService();
		service.connect(builder.buildQuery());
	}

	private List<TweetModel> parse() {
		TwitterXmlParser parser = new TwitterXmlParser();
		parser.parse(TwitterApiConectionService.XML_OUTPUT);
		return parser.getList();
	}
}
