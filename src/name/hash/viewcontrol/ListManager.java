package name.hash.viewcontrol;

import java.util.Collections;
import java.util.List;

import name.hash.TweetModel;
import name.hash.twitterprovider.Twiter4JCliant;

public class ListManager implements TwitterListManager<TweetModel> {
	private String userName;
	Twiter4JCliant cliant = Twiter4JCliant.getInstance();
	enum State {
		Home, User,Init
	}
	private State s;

	public ListManager(String name) {
		userName = name;
	}

	public void changeUser(String name) {
		userName = name;
	}

	public void chamgeCount(int num) {
		cliant.setPagingCount(num);
	}

	@Override
	public List<TweetModel> getHomeTimeline() {
		s = State.Home;
		return cliant.getHomeTimeLine() ;
	}

	@Override
	public List<TweetModel> getUserTimeline() {
		s = State.User;
		return cliant.getUserTimeLine(userName);
	}

	@Override
	public List<TweetModel> getMoreTimeline() {
		if (s == State.Home) {
			return cliant.getNextHomeTimeLine();
		} else if (s == State.User) {
			return cliant.getNextUserTimeline();
		}
		return Collections.emptyList();
	}
}
