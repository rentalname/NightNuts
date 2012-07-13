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

@SuppressWarnings("serial")
public class ViewControler extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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

		textField = new JTextField();
		textField.setBackground(SystemColor.text);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		controlPane.add(textField, gbc_textField);
		textField.setColumns(10);

		// �^�C�����C���擾�Ώۂ̃��[�U�[��ύX����
		JButton btnUpdate = new JButton("Change User");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 0;
		controlPane.add(btnUpdate, gbc_btnUpdate);

		// ���̃y�[�W�̃c�C�[�g���擾����
		JButton btnMore = new JButton("More...");
		GridBagConstraints gbc_btnMore = new GridBagConstraints();
		gbc_btnMore.insets = new Insets(0, 0, 0, 5);
		gbc_btnMore.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnMore.gridx = 3;
		gbc_btnMore.gridy = 0;
		controlPane.add(btnMore, gbc_btnMore);

		JPanel tweetViewPane = new JPanel();
		contentPane.add(tweetViewPane, BorderLayout.CENTER);

		// ���X�g�̗l�q��\�����邽�߂̃X�^�u�f�[�^
		String[] initData = { "Blue ", "Green", "Red  ", "White", "Black" };

		// �c�C�[�g��\�����郊�X�g
		JList<TweetModel> list = new JList<>(new TwitterListModel(initData));
		list.setCellRenderer(new TweetListCellRenderer());
		tweetViewPane.add(list);
	}
}