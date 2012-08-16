package name.hash.twitterprovider;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * �_�C�A���O��\������,OAuth�F�؂ɕK�v��PIN�R�[�h��Web����擾���邱�Ƃ����[�U�ɑ���
 * ���[�U��,PIN�R�[�h����͂�����,���͌��ʂ��Ăяo�����̃��X�i�[�����N���X�֕Ԃ�
 * 
 * @author Hi
 */
public class OAuthDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6966469750214592368L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtPin;
	private JEditorPane hyperLinkText;
	DialogInputListener inputListener;
	private JButton okButton;

	public static void createDialog(String url, DialogInputListener listener) {
		try {
			OAuthDialog dialog = new OAuthDialog();
			dialog.setListener(listener);
			dialog.setURL(url);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setListener(DialogInputListener listener) {
		inputListener = listener;
	}

	/**
	 * �_�C�A���O��PIN�R�[�h���s���邽�߂ɕK�v�ȃT�C�g�ւ�URL��\������
	 * 
	 * @param url
	 *            PIN�R�[�h�擾�T�C�g�ւ�URL
	 */
	public void setURL(String url) {
		hyperLinkText.setText(convertHyperLink(url));
	}

	/**
	 * Create the dialog.
	 */
	public OAuthDialog() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 442 };
		gridBagLayout.rowHeights = new int[] { 66, 66, 66, 66 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel messagePane = new JPanel();
			FlowLayout flowLayout = (FlowLayout) messagePane.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_messagePane = new GridBagConstraints();
			gbc_messagePane.gridx = 0;
			gbc_messagePane.gridy = 0;
			getContentPane().add(messagePane, gbc_messagePane);
			{
				JTextPane txtpnoauthpin = new JTextPane();
				txtpnoauthpin.setText("���̃A�v���P�[�V�����𗘗p���邽�߂ɂ�OAuth�F�؂��s���K�v������܂�." + "\n"
						+ "�ȉ��̃T�C�g�Ŏ葱�����s�Ȃ���,PIN�R�[�h�����擾���Ă�������.");
				messagePane.add(txtpnoauthpin);
			}
		}
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setHgap(0);
		fl_contentPanel.setVgap(13);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 1;
		getContentPane().add(contentPanel, gbc_contentPanel);
		{
			String SAMPLE_URL = "http://example.com";
			hyperLinkText = new JEditorPane();
			/*
			 * JEitorPane�ɕ\�������n�C�p�[�����N�ɑ΂���,���X�i�[��o�^����
			 * ���X�i�[�ɓn�����C�x���g�̎��
			 * @EventType.ENTERED �n�C�p�[�����N���}�E�X�I�[�o�[
			 * @EventType.ACTIVATE �n�C�p�[�����N���N���b�N������
			 * @EventType.EXIT �n�C�p�[�����N�̏ォ��}�E�X�A�E�g
			 */
			hyperLinkText.addHyperlinkListener(new HyperlinkListener() {
				public void hyperlinkUpdate(HyperlinkEvent ev) {
					if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
						System.out.println(ev.getURL());
						startBrowser(ev.getURL());
					}
					System.out.println(ev.getEventType().toString());
				}
			});
			hyperLinkText.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 10));
			hyperLinkText.setSize(new Dimension(500, 20));
			hyperLinkText.setContentType("text/html");
			hyperLinkText.setText(convertHyperLink(SAMPLE_URL));
			hyperLinkText.setPreferredSize(new Dimension(210, 19));
			hyperLinkText.setEditable(false);
			contentPanel.add(hyperLinkText);
		}
		{
			JPanel inputPINPane = new JPanel();
			GridBagConstraints gbc_inputPINPane = new GridBagConstraints();
			gbc_inputPINPane.weighty = 1.0;
			gbc_inputPINPane.fill = GridBagConstraints.BOTH;
			gbc_inputPINPane.gridx = 0;
			gbc_inputPINPane.gridy = 2;
			getContentPane().add(inputPINPane, gbc_inputPINPane);
			{
				txtPin = new JTextField();
				txtPin.setBorder(null);
				txtPin.setEditable(false);
				txtPin.setText("PIN :");
				txtPin.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
				inputPINPane.add(txtPin);
				txtPin.setColumns(3);
			}
			{
				textField = new JTextField();
				textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						okButtonEnabled();
					}
				});
				textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
				inputPINPane.add(textField);
				textField.setColumns(16);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.TRAILING));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.SOUTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 3;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("PUSH OK");
						inputListener.update(textField.getText());
						OAuthDialog.this.setVisible(false);
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// �_�C�A���O��s����Ԃɂ��邱�ƂŃ_�C�A���O�����
						OAuthDialog.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * �n���ꂽ{@link java.net.URL}��{@link java.net.URI}�ɕϊ�����,
	 * ����{@link URI}���g�p���ăf�X�N�g�b�v�W���̃u���E�U���N������
	 * 
	 * @param url
	 */
	private void startBrowser(URL url) {
		Desktop desktop = Desktop.getDesktop();

		try {
			desktop.browse(new URI(url.toString()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private void okButtonEnabled() {
		System.out.println(textField.getText());
		if (textField.getText().length() != 0) {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}
	}

	private String convertHyperLink(String url) {
		return "<html><a href='" + url + "'>" + url + "</a>";
	}
}
