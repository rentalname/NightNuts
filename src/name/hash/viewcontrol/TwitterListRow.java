package name.hash.viewcontrol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class TwitterListRow extends JPanel {
	private JTextField userName;
	private JTextField tweetText;
	private JTextField userId;
	private JTextField tweetDate;

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

	/**
	 * Create the panel.
	 */
	public TwitterListRow() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 5.0, 0.5, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		JPanel userNameColumn = new JPanel();
		userNameColumn.setBackground(SystemColor.inactiveCaptionText);
		userNameColumn.setAlignmentY(0.0f);
		userNameColumn.setAlignmentX(0.0f);
		FlowLayout flowLayout = (FlowLayout) userNameColumn.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		userNameColumn.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_0 = new GridBagConstraints();
		gbc_0.insets = new Insets(0, 0, 0, 0);
		gbc_0.fill = GridBagConstraints.BOTH;
		gbc_0.gridx = 0;
		gbc_0.gridy = 0;
		add(userNameColumn, gbc_0);

		userName = new JTextField();
		userName.setBorder(null);
		userName.setMinimumSize(new Dimension(50, 50));
		userName.setMargin(new Insets(0, 0, 0, 0));
		userName.setBackground(SystemColor.info);
		userName.setEditable(false);
		userNameColumn.add(userName);
		userName.setColumns(12);

		JPanel tweetTextColumn = new JPanel();
		tweetTextColumn.setBackground(SystemColor.inactiveCaptionText);
		tweetTextColumn.setAlignmentY(0.0f);
		tweetTextColumn.setAlignmentX(0.0f);
		FlowLayout flowLayout_1 = (FlowLayout) tweetTextColumn.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		tweetTextColumn.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.insets = new Insets(0, 0, 0, 0);
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		add(tweetTextColumn, gbc_1);

		tweetText = new JTextField();
		tweetText.setBorder(null);
		tweetText.setMargin(new Insets(0, 0, 0, 0));
		tweetText.setBackground(SystemColor.info);
		tweetText.setEditable(false);
		tweetTextColumn.add(tweetText);
		tweetText.setColumns(40);

		JPanel userIdColumn = new JPanel();
		userIdColumn.setBackground(SystemColor.inactiveCaptionText);
		userIdColumn.setAlignmentY(0.0f);
		userIdColumn.setAlignmentX(0.0f);
		FlowLayout flowLayout_2 = (FlowLayout) userIdColumn.getLayout();
		flowLayout_2.setHgap(0);
		flowLayout_2.setVgap(0);
		userIdColumn.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.insets = new Insets(0, 0, 0, 0);
		gbc_2.fill = GridBagConstraints.BOTH;
		gbc_2.gridx = 2;
		gbc_2.gridy = 0;
		add(userIdColumn, gbc_2);

		userId = new JTextField();
		userId.setBorder(null);
		userId.setMargin(new Insets(0, 0, 0, 0));
		userId.setBackground(SystemColor.info);
		userId.setEditable(false);
		userIdColumn.add(userId);
		userId.setColumns(12);

		JPanel tweetDateColumn = new JPanel();
		tweetDateColumn.setBackground(SystemColor.inactiveCaptionText);
		tweetDateColumn.setAlignmentY(0.0f);
		tweetDateColumn.setAlignmentX(0.0f);
		FlowLayout flowLayout_3 = (FlowLayout) tweetDateColumn.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		tweetDateColumn.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.fill = GridBagConstraints.BOTH;
		gbc_3.gridx = 3;
		gbc_3.gridy = 0;
		add(tweetDateColumn, gbc_3);

		tweetDate = new JTextField();
		tweetDate.setBorder(null);
		tweetDate.setMargin(new Insets(0, 0, 0, 0));
		tweetDate.setBackground(SystemColor.info);
		tweetDate.setEditable(false);
		tweetDateColumn.add(tweetDate);
		tweetDate.setColumns(17);
	}
}
