package view.subView;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Message extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel messageLabel;
	public Message(String message) {
		this.messageLabel = new JLabel(message, SwingConstants.CENTER);
	    this.setLayout(new BorderLayout());
	    this.add(this.messageLabel, BorderLayout.CENTER);
	    this.setSize(200, 100);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setResizable(false);
	}
	
	public void setMessage(String message) {
		this.messageLabel.setText(message);
	}
}
