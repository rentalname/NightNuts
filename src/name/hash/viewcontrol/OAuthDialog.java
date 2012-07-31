package name.hash.viewcontrol;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class OAuthDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

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
	 * Create the dialog.
	 */
	public OAuthDialog() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{442, 0};
		gridBagLayout.rowHeights = new int[]{66, 66, 66, 66, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
				txtpnoauthpin.setText("\u3053\u306E\u30A2\u30D7\u30EA\u30B1\u30FC\u30B7\u30E7\u30F3\u3092\u5229\u7528\u3059\u308B\u305F\u3081\u306B\u306FOAuth\u8A8D\u8A3C\u3092\u884C\u3046\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059.\r\n\u4EE5\u4E0B\u306E\u30B5\u30A4\u30C8\u3067\u624B\u7D9A\u304D\u3092\u884C\u306A\u3063\u3066,PIN\u30B3\u30FC\u30C9\u3092\u3092\u53D6\u5F97\u3057\u3066\u304F\u3060\u3055\u3044.");
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
			JPanel inputPINPane = new JPanel();
			GridBagConstraints gbc_inputPINPane = new GridBagConstraints();
			gbc_inputPINPane.weighty = 1.0;
			gbc_inputPINPane.fill = GridBagConstraints.BOTH;
			gbc_inputPINPane.gridx = 0;
			gbc_inputPINPane.gridy = 2;
			getContentPane().add(inputPINPane, gbc_inputPINPane);
			{
				textField = new JTextField();
				inputPINPane.add(textField);
				textField.setColumns(10);
			}
			{
				JEditorPane editorPane = new JEditorPane();
				inputPINPane.add(editorPane);
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

}
