package name.hash.viewcontrol;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class NewTwitterListRow extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField userName;
	private JTextField tweetText;

	void setUserName(String name) {
		userName.setText(name);
	}

	void setTweetText(String text) {
		tweetText.setText(text);
	}

	public NewTwitterListRow() {
		setBorder(null);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel userNameColumn = new JPanel();
		userNameColumn.setMaximumSize(new Dimension(300, 3000));
		userNameColumn.setBorder(null);
		add(userNameColumn);
		userNameColumn.setLayout(new GridLayout(0, 1, 0, 0));

		userName = new JTextField();
		userName.setMargin(new Insets(0, 3, 0, 0));
		userName.setBackground(SystemColor.info);
		userName.setEditable(false);
		userName.setColumns(15);
		userNameColumn.add(userName);

		JPanel tweetTextColumn = new JPanel();
		tweetTextColumn.setBorder(null);
		add(tweetTextColumn);
		tweetTextColumn.setLayout(new GridLayout(0, 1, 0, 0));

		tweetText = new JTextField();
		tweetText.setMargin(new Insets(0, 3, 0, 0));
		tweetText.setBackground(SystemColor.info);
		tweetText.setEditable(false);
		tweetText.setColumns(35);
		tweetTextColumn.add(tweetText);
	}
}
