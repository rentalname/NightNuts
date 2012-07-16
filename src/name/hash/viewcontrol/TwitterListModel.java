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
 * �c�C�[�g���e��\�����邽�߂̃��X�g�̃��f�����r���[�ɒ񋟂���
 * @author Hi
 *
 */
public class TwitterListModel implements ListModel<TweetModel> {
	Set<ListDataListener> set = new HashSet<>();
	List<TweetModel> list = new ArrayList<>();

	/**
	 * �^����ꂽ,String�z���,TweetModel�I�u�W�F�N�g�ɕϊ����ă��X�g�ɓo�^����
	 * ���X�g�̐��������삷�邱�Ƃ̊m�F�̂��߂ɍ쐬�����X�^�u�R���X�g���N�^
	 * 
	 * @param sArray
	 *            TweetModel�I�u�W�F�N�g�ɕϊ������String�z��
	 */
	public TwitterListModel(String[] sArray) {
		for (String s : sArray) {
			list.add(new TweetModel(0, s, s, "999999"));
		}
	}

	/**
	 * ���X�g�̗v�f�ɕύX���������Ƃ���,�ύX��ʒm���郊�X�i�[��o�^����
	 * 
	 * @param listener
	 *            ���̃��X�i�[��ύX�ʒm��̃��X�i�[�Ƃ��ēo�^����
	 */
	@Override
	public void addListDataListener(ListDataListener listener) {
		set.add(listener);
	}

	/**
	 * �w�肳�ꂽ���X�i�[�̓o�^����������
	 * 
	 * @param listener
	 *            ���̃��X�i�[�̓o�^����������
	 */
	@Override
	public void removeListDataListener(ListDataListener listener) {
		set.remove(listener);
	}

	/**
	 * ���X�g�����������Ƃ���,�����I�ɌĂяo����郁�\�b�h.
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
	 * ���X�g�ɐV���ȗv�f��ǉ�����
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
	 * ���X�g����w�肳�ꂽ�v�f����菜��
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
