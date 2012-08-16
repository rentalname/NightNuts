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
 * ダイアログを表示して,OAuth認証に必要なPINコードをWebから取得することをユーザに促す
 * ユーザに,PINコードを入力させて,入力結果を呼び出し元のリスナー実装クラスへ返す
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
	 * ダイアログにPINコード発行するために必要なサイトへのURLを表示する
	 * 
	 * @param url
	 *            PINコード取得サイトへのURL
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
				txtpnoauthpin.setText("このアプリケーションを利用するためにはOAuth認証を行う必要があります." + "\n"
						+ "以下のサイトで手続きを行なって,PINコードをを取得してください.");
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
			 * JEitorPaneに表示したハイパーリンクに対して,リスナーを登録する
			 * リスナーに渡されるイベントの種類
			 * @EventType.ENTERED ハイパーリンクをマウスオーバー
			 * @EventType.ACTIVATE ハイパーリンクをクリックした時
			 * @EventType.EXIT ハイパーリンクの上からマウスアウト
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
						// ダイアログを不可視状態にすることでダイアログを閉じる
						OAuthDialog.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * 渡された{@link java.net.URL}を{@link java.net.URI}に変換して,
	 * その{@link URI}を使用してデスクトップ標準のブラウザを起動する
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
