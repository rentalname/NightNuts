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

	void setUserName(String name) {
		userName.setText(name);
	}

	void setTweetText(String text) {
		tweetText.setText(text);
	}

	/**
	 * Create the panel.
	 */
	public TwitterListRow() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 60, 100 };
		gridBagLayout.rowHeights = new int[] { 10 };
		gridBagLayout.columnWeights = new double[] { 1.0, 5.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		JPanel userNameColumn = new JPanel();
		userNameColumn.setMaximumSize(new Dimension(2000, 2000));
		userNameColumn.setBackground(SystemColor.inactiveCaptionText);
		userNameColumn.setAlignmentY(0.0f);
		userNameColumn.setAlignmentX(0.0f);
		FlowLayout flowLayout = (FlowLayout) userNameColumn.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		userNameColumn.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_0 = new GridBagConstraints();
		gbc_0.fill = GridBagConstraints.BOTH;
		gbc_0.insets = new Insets(0, 0, 0, 0);
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
		tweetTextColumn.setMaximumSize(new Dimension(2000, 2000));
		tweetTextColumn.setBackground(SystemColor.inactiveCaptionText);
		tweetTextColumn.setAlignmentY(0.0f);
		tweetTextColumn.setAlignmentX(0.0f);
		FlowLayout flowLayout_1 = (FlowLayout) tweetTextColumn.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		tweetTextColumn.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		add(tweetTextColumn, gbc_1);

		tweetText = new JTextField();
		tweetTextColumn.add(tweetText);
		tweetText.setBorder(null);
		tweetText.setMargin(new Insets(0, 0, 0, 0));
		tweetText.setBackground(SystemColor.info);
		tweetText.setEditable(false);
		tweetText.setColumns(35);

	}
}
