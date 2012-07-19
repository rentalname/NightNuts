package name.hash.viewcontrol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import name.hash.TweetModel;

@SuppressWarnings("serial")
public class ViewControler extends JFrame {

	private static final String CONFIGURATION_DIR = "./.config/";
	private static final String CONFIGURATION_FILE = CONFIGURATION_DIR + "window_config";
	private JPanel contentPane;
	private JTextField nameField;
	private final Action changeUserAction = new ChangeUserAction();
	private final Action moreTweetAction = new MoreTweetAction();
	private ListManager manager;
	private TwitterListModel twitterListModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ViewControler frame = new ViewControler();
				frame.setSize(new Dimension(800, 800));
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewControler() {
		
		loadWindowLocation(this);

		setTitle("Night Nuts");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// ウィンドウが閉じられた際の位置を記録して,アプリケーション起動時にロード
				Point location = getLocation();
				saveWindowLocation(location);
			}
		});

		JPanel controlPane = new JPanel();
		contentPane.add(controlPane, BorderLayout.SOUTH);
		GridBagLayout gbl_controlPane = new GridBagLayout();
		gbl_controlPane.columnWidths = new int[] { 50, 179, 90, 50 };
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
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 0, 5);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		controlPane.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		// タイムライン取得対象のユーザーを変更する
		JButton btnCangeUser = new JButton(changeUserAction);
		GridBagConstraints gbc_btnChangeUser = new GridBagConstraints();
		gbc_btnChangeUser.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnChangeUser.insets = new Insets(0, 0, 0, 5);
		gbc_btnChangeUser.gridx = 2;
		gbc_btnChangeUser.gridy = 0;
		controlPane.add(btnCangeUser, gbc_btnChangeUser);

		// 次のページのツイートを取得する
		JButton btnMoreTweet = new JButton(moreTweetAction);
		GridBagConstraints gbc_btnMoreTweet = new GridBagConstraints();
		gbc_btnMoreTweet.insets = new Insets(0, 0, 0, 5);
		gbc_btnMoreTweet.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnMoreTweet.gridx = 3;
		gbc_btnMoreTweet.gridy = 0;
		controlPane.add(btnMoreTweet, gbc_btnMoreTweet);

		manager = new ListManager("jihou");
		twitterListModel = new TwitterListModel(manager.getList());
		JList<TweetModel> list = new JList<>(twitterListModel);
		list.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list.setAlignmentY(Component.TOP_ALIGNMENT);
		list.setCellRenderer(new TweetListCellRenderer());

		// JListをJScrollPaneに追加
		JScrollPane tweetViewPane = new JScrollPane(list);
		tweetViewPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(tweetViewPane, BorderLayout.CENTER);
	}

	private void loadWindowLocation(JFrame frame) {
		File file = new File(CONFIGURATION_FILE);
		if (!file.exists()) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String str = br.readLine();
			String[] split = str.split(",");
			frame.setBounds(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveWindowLocation(Point location) {
		File file = new File(CONFIGURATION_DIR);
		if (!file.exists()) {
			file.mkdirs();
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIGURATION_FILE));) {
			writer.write(location.x + "," + location.y);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ChangeUserAction extends AbstractAction {
		public ChangeUserAction() {
			putValue(NAME, "Change");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("*** CHANGE USER ***");

			String text = nameField.getText();
			if (text.length() > 0) {
				manager.changeUser(text);
				twitterListModel.allRemoveTweetModel();
				twitterListModel.addTweetModel(manager.getList());
			} else {
				System.out.println("User name field is empty");
			}
		}
	}

	private class MoreTweetAction extends AbstractAction {
		public MoreTweetAction() {
			putValue(NAME, "More...");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("MORE");
			twitterListModel.addTweetModel(manager.nextPage());
		}
	}
}
