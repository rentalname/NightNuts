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
		// TODO Auto-generated method stub
		JLabel tweet = new JLabel(value.getName() + " | " + value.getText());
		return tweet;
	}

}
