package trabalhofinal.sistema;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class UserFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		setBackground(new Color(240, 240, 240));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 507);
		contentPane = new JPanel();

		setContentPane(contentPane);
		
		JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tb);
		
		JPanel panel = new JPanel();
		tb.addTab("New tab", null, panel, null);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
