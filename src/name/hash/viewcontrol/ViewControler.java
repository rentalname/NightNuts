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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import name.hash.model.TweetModel;
import name.hash.model.TwitterListModel;

@SuppressWarnings("serial")
public class ViewControler extends JFrame {

	private static final ImageIcon iconHome2User = new ImageIcon(ViewControler.class.getResource("/Home2User.png"));
	private static final ImageIcon iconUser2Home = new ImageIcon(ViewControler.class.getResource("/User2Home.png"));
	private static final String CONFIGURATION_DIR = "./.config/";
	private static final String CONFIGURATION_FILE = CONFIGURATION_DIR + "window_config";
	private JPanel contentPane;
	private JTextField nameField;
	private final Action changeUserAction = new ChangeUserAction();
	private final Action moreTweetAction = new MoreTweetAction();
	private ListManager manager;
	private TwitterListModel twitterListModel;
	private JToggleButton userHomeChange;
	private JButton btnMoreTweet;
	private JButton btnCangeUser;

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
		gbl_controlPane.columnWidths = new int[] { 0, 50, 179, 75, 32 };
		gbl_controlPane.rowHeights = new int[] { 32 };
		gbl_controlPane.columnWeights = new double[] { 0.0, 0.0, 2.0, 1.5, 0.0 };
		gbl_controlPane.rowWeights = new double[] { 0.0 };
		controlPane.setLayout(gbl_controlPane);

		userHomeChange = new JToggleButton();
		userHomeChange.setMargin(new Insets(2, 4, 2, 4));
		userHomeChange.setMaximumSize(new Dimension(64, 32));
		userHomeChange.setMinimumSize(new Dimension(64, 32));
		userHomeChange.setPreferredSize(new Dimension(64, 32));
		userHomeChange.setIcon(iconHome2User);
		userHomeChange.setBorder(null);
		userHomeChange.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				// ホームとユーザーの切り替えボタンが押されたら,表示するタイムラインを切り替える
				if (userHomeChange.isSelected()) {
					twitterListModel.allRemoveTweetModel();
					twitterListModel.addTweetModel(manager.getUserTimeline());
					userHomeChange.setIcon(iconUser2Home);
					nameField.setEnabled(true);
					btnCangeUser.setEnabled(true);
				} else {
					twitterListModel.allRemoveTweetModel();
					twitterListModel.addTweetModel(manager.getHomeTimeline());
					userHomeChange.setIcon(iconHome2User);
					nameField.setEnabled(false);
					btnCangeUser.setEnabled(false);
				}
			}
		});
		GridBagConstraints gbc_tglbtnHu = new GridBagConstraints();
		gbc_tglbtnHu.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnHu.gridx = 0;
		gbc_tglbtnHu.gridy = 0;
		controlPane.add(userHomeChange, gbc_tglbtnHu);

		JTextPane txtpnName = new JTextPane();
		txtpnName.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtpnName.setBackground(SystemColor.control);
		txtpnName.setEditable(false);
		txtpnName.setText("name :");
		GridBagConstraints gbc_txtpnName = new GridBagConstraints();
		gbc_txtpnName.anchor = GridBagConstraints.EAST;
		gbc_txtpnName.gridx = 1;
		gbc_txtpnName.gridy = 0;
		controlPane.add(txtpnName, gbc_txtpnName);

		nameField = new JTextField();
		nameField.setEnabled(false);
		nameField.setText("DruckerBOT");
		nameField.setBackground(SystemColor.text);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 0, 5);
		gbc_nameField.gridx = 2;
		gbc_nameField.gridy = 0;
		controlPane.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		btnCangeUser = new JButton(changeUserAction);
		btnCangeUser.setEnabled(false);
		btnCangeUser.setMaximumSize(new Dimension(32, 32));
		btnCangeUser.setMinimumSize(new Dimension(32, 32));
		btnCangeUser.setPreferredSize(new Dimension(32, 32));
		btnCangeUser.setIcon(new ImageIcon(ViewControler.class.getResource("/changeUser.png")));
		GridBagConstraints gbc_btnChangeUser = new GridBagConstraints();
		gbc_btnChangeUser.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnChangeUser.insets = new Insets(0, 0, 0, 0);
		gbc_btnChangeUser.gridx = 3;
		gbc_btnChangeUser.gridy = 0;
		controlPane.add(btnCangeUser, gbc_btnChangeUser);

		btnMoreTweet = new JButton(moreTweetAction);
		btnMoreTweet.setBorderPainted(false);
		btnMoreTweet.setMargin(new Insets(1, 1, 1, 1));
		btnMoreTweet.setToolTipText("Get more tweet");
		btnMoreTweet.setMaximumSize(new Dimension(64, 64));
		btnMoreTweet.setMinimumSize(new Dimension(32, 32));
		btnMoreTweet.setPreferredSize(new Dimension(32, 32));
		btnMoreTweet.setIcon(new ImageIcon(ViewControler.class.getResource("/new-content.png")));
		GridBagConstraints gbc_btnMoreTweet = new GridBagConstraints();
		gbc_btnMoreTweet.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnMoreTweet.gridx = 4;
		gbc_btnMoreTweet.gridy = 0;
		controlPane.add(btnMoreTweet, gbc_btnMoreTweet);

		manager = new ListManager("jihou");
		twitterListModel = new TwitterListModel(manager.getHomeTimeline());
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
			String[] windowLocate = str.split(",");
			frame.setBounds(Integer.parseInt(windowLocate[0]), Integer.parseInt(windowLocate[1]), 0, 0);
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
			putValue(SHORT_DESCRIPTION, "Change user");
		}

		public void actionPerformed(ActionEvent e) {
			String text = nameField.getText();
			if (text.length() > 0) {
				manager.changeUser(text);
				twitterListModel.allRemoveTweetModel();
				twitterListModel.addTweetModel(manager.getUserTimeline());
			} else {
				System.out.println("User name field is empty");
			}
		}
	}

	private class MoreTweetAction extends AbstractAction {
		public MoreTweetAction() {
			putValue(SHORT_DESCRIPTION, "Add Tweet For List View");
		}

		public void actionPerformed(ActionEvent e) {
			twitterListModel.addTweetModel(manager.getMoreTimeline());
		}
	}
}
