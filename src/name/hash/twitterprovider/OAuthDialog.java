package name.hash.twitterprovider;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class OAuthDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6966469750214592368L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtPin;
	private JEditorPane hyperLinkText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OAuthDialog dialog = new OAuthDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		gridBagLayout.columnWidths = new int[] {442};
		gridBagLayout.rowHeights = new int[] {66, 66, 66, 66};
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
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
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 1;
		getContentPane().add(contentPanel, gbc_contentPanel);
		{
			String SAMPLE_URL = "http://example.com";
			hyperLinkText = new JEditorPane();
			hyperLinkText.setContentType("text/html");
			hyperLinkText.setText(convertHyperLink(SAMPLE_URL));
			hyperLinkText.setPreferredSize(new Dimension(150, 19));
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
				JButton okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private String convertHyperLink(String url) {
		return "<html><a href='" + url + "'>" + url + "</a>";
	}
}
