package name.hash.viewcontrol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import name.hash.TweetModel;
/**
 * ツイート内容を表示するためのリストのモデルをビューに提供する
 * @author Hi
 *
 */
public class TwitterListModel implements ListModel<TweetModel> {
	Set<ListDataListener> set = new HashSet<>();
	List<TweetModel> list = new ArrayList<>();

	/**
	 * 与えられた,String配列を,TweetModelオブジェクトに変換してリストに登録する
	 * リストの生成が動作することの確認のために作成したスタブコンストラクタ
	 * 
	 * @param sArray
	 *            TweetModelオブジェクトに変換されるString配列
	 */
	public TwitterListModel(String[] sArray) {
		for (String s : sArray) {
			list.add(new TweetModel(0, s, s, "999999"));
		}
	}

	/**
	 * リストの要素に変更があったときに,変更を通知するリスナーを登録する
	 * 
	 * @param listener
	 *            このリスナーを変更通知先のリスナーとして登録する
	 */
	@Override
	public void addListDataListener(ListDataListener listener) {
		set.add(listener);
	}

	/**
	 * 指定されたリスナーの登録を解除する
	 * 
	 * @param listener
	 *            このリスナーの登録を解除する
	 */
	@Override
	public void removeListDataListener(ListDataListener listener) {
		set.remove(listener);
	}

	/**
	 * リストが生成されるときに,自動的に呼び出されるメソッド.
	 */
	@Override
	public TweetModel getElementAt(int index) {
		if (index + 1 > list.size()) {
			throw new ArrayIndexOutOfBoundsException("A index is larger than list size");
		} else if (index < 0) {
			throw new ArrayIndexOutOfBoundsException("Index is pointing to a value less than zero");
		}
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	/**
	 * リストに新たな要素を追加する
	 * 
	 * @param model
	 */
	public void addTweetModel(TweetModel model) {
		list.add(model);
		for (ListDataListener l : set) {
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, list.size(), list.size()));
		}
	}

	/**
	 * リストから指定された要素を取り除く
	 * 
	 * @param model
	 */
	public void removeTweetModel(TweetModel model) {
		int modelIndex = list.indexOf(model);
		list.remove(model);
		for (ListDataListener l : set) {
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, modelIndex, list.size()));
		}

	}
}
