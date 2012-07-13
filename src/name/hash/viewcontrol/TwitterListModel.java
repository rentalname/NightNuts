package name.hash.viewcontrol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import name.hash.TweetModel;

public class TwitterListModel implements ListModel<TweetModel> {
	Set<ListDataListener> set = new HashSet<>();
	List<TweetModel> list = new ArrayList<>();

	public TwitterListModel(String[] sArray) {
		for (String s : sArray) {
			list.add(new TweetModel(0, s, s, "999999"));
		}
	}

	@Override
	public void addListDataListener(ListDataListener listener) {
		set.add(listener);
	}

	@Override
	public TweetModel getElementAt(int index) {
		if (index + 1 > list.size()) {
			throw new ArrayIndexOutOfBoundsException("a index is larger than list size");
		}
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public void removeListDataListener(ListDataListener listener) {
		set.remove(listener);
	}

	public void addTweetModel(TweetModel model) {
		list.add(model);
		for (ListDataListener l : set) {
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, list.size(), list.size()));
		}
	}

}
