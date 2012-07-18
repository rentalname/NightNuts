package name.hash.viewcontrol;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import name.hash.TweetModel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class ViewControler extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private final Action changeUserAction = new ChangeUserAction();
	private final Action moreTweetAction = new MoreTweetAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ViewControler frame = new ViewControler();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewControler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel controlPane = new JPanel();
		contentPane.add(controlPane, BorderLayout.SOUTH);
		GridBagLayout gbl_controlPane = new GridBagLayout();
		gbl_controlPane.columnWidths = new int[] { 50, 120, 90, 50 };
		gbl_controlPane.rowHeights = new int[] { 21 };
		gbl_controlPane.columnWeights = new double[] { 0.0, 2.0, 1.5, 1.5 };
		gbl_controlPane.rowWeights = new double[] { 0.0 };
		controlPane.setLayout(gbl_controlPane);

		JTextPane txtpnName = new JTextPane();
		txtpnName.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtpnName.setBackground(SystemColor.control);
		txtpnName.setEditable(false);
		txtpnName.setText("name :");
		GridBagConstraints gbc_txtpnName = new GridBagConstraints();
		gbc_txtpnName.anchor = GridBagConstraints.EAST;
		gbc_txtpnName.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnName.gridx = 0;
		gbc_txtpnName.gridy = 0;
		controlPane.add(txtpnName, gbc_txtpnName);

		nameField = new JTextField();
		nameField.setBackground(SystemColor.text);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		controlPane.add(nameField, gbc_textField);
		nameField.setColumns(10);

		// タイムライン取得対象のユーザーを変更する
		JButton btnCangeUser = new JButton(changeUserAction);
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 0;
		controlPane.add(btnCangeUser, gbc_btnUpdate);

		// 次のページのツイートを取得する
		JButton btnMoreTweet = new JButton(moreTweetAction);
		GridBagConstraints gbc_btnMore = new GridBagConstraints();
		gbc_btnMore.insets = new Insets(0, 0, 0, 5);
		gbc_btnMore.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnMore.gridx = 3;
		gbc_btnMore.gridy = 0;
		controlPane.add(btnMoreTweet, gbc_btnMore);

		JPanel tweetViewPane = new JPanel();
		contentPane.add(tweetViewPane, BorderLayout.CENTER);

		// リストの様子を表示するためのスタブデータ
		// String[] initData = { "Blue ", "Green", "Red  ", "White", "Black" };

		// ツイートを表示するリスト
		ListManager manager = new ListManager("jihou");
		TwitterListModel twitterListModel = new TwitterListModel(manager.getList());
		JList<TweetModel> list = new JList<>(twitterListModel);
		list.setCellRenderer(new TweetListCellRenderer());
		tweetViewPane.add(list);
	}

	private class ChangeUserAction extends AbstractAction {
		public ChangeUserAction() {
			putValue(NAME, "Change");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(nameField.getText());
		}
	}

	private class MoreTweetAction extends AbstractAction {
		public MoreTweetAction() {
			putValue(NAME, "More...");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("MORE");
		}
	}
}