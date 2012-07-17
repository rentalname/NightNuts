package name.hash.viewcontrol;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TwitterListRow extends JPanel {
	private JTextField userName;
	private JTextField tweetText;
	private JTextField userId;
	private JTextField tweetDate;

	/**
	 * Create the panel.
	 */
	public TwitterListRow() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 20 };
		gridBagLayout.columnWeights = new double[] { 1.0, 3.0, 0.5, 0.5 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		JPanel userNameColumn = new JPanel();
		GridBagConstraints gbc_0 = new GridBagConstraints();
		gbc_0.insets = new Insets(0, 0, 0, 0);
		gbc_0.fill = GridBagConstraints.BOTH;
		gbc_0.gridx = 0;
		gbc_0.gridy = 0;
		add(userNameColumn, gbc_0);

		userName = new JTextField();
		userName.setBackground(SystemColor.info);
		userName.setEditable(false);
		userNameColumn.add(userName);
		userName.setColumns(10);

		JPanel tweetTextColumn = new JPanel();
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		add(tweetTextColumn, gbc_1);

		tweetText = new JTextField();
		tweetText.setBackground(SystemColor.info);
		tweetText.setEditable(false);
		tweetTextColumn.add(tweetText);
		tweetText.setColumns(10);

		JPanel userIdColumn = new JPanel();
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.insets = new Insets(0, 0, 0, 0);
		gbc_2.fill = GridBagConstraints.BOTH;
		gbc_2.gridx = 2;
		gbc_2.gridy = 0;
		add(userIdColumn, gbc_2);

		userId = new JTextField();
		userId.setBackground(SystemColor.info);
		userId.setEditable(false);
		userIdColumn.add(userId);
		userId.setColumns(10);

		JPanel tweetDateColumn = new JPanel();
		GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.fill = GridBagConstraints.BOTH;
		gbc_3.gridx = 3;
		gbc_3.gridy = 0;
		add(tweetDateColumn, gbc_3);

		tweetDate = new JTextField();
		tweetDate.setBackground(SystemColor.info);
		tweetDate.setEditable(false);
		tweetDateColumn.add(tweetDate);
		tweetDate.setColumns(10);
	}

	void setUserName(String name) {
		userName.setText(name);
	}

	void setTweetText(String text) {
		tweetText.setText(text);
	}

	void setUserId(String Id) {
		userId.setText(Id);
	}

	void setTweetDate(String date) {
		tweetDate.setText(date);
	}
}
