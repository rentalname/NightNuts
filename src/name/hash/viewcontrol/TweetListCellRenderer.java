package name.hash.viewcontrol;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import name.hash.TweetModel;

public class TweetListCellRenderer extends JLabel implements ListCellRenderer<TweetModel> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends TweetModel> list, TweetModel value, int index,
			boolean isSelected, boolean cellHasFocus) {
		NewTwitterListRow row = new NewTwitterListRow();
		row.setUserName(value.getName());
		row.setTweetText(value.getText());
		return row;
	}
}
