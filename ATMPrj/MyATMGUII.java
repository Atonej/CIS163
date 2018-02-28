package atmPack;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyATMGUII extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyATMGUII frame = new MyATMGUII();
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
	public MyATMGUII() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JButton button = new JButton("0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblAtm = new JLabel("Atm 1");
		contentPane.add(lblAtm);
		contentPane.add(button);

		JButton button_1 = new JButton("1");
		contentPane.add(button_1);

		JButton button_2 = new JButton("2");
		contentPane.add(button_2);

		JButton button_3 = new JButton("3");
		contentPane.add(button_3);

		JButton button_4 = new JButton("4");
		contentPane.add(button_4);

		JButton button_5 = new JButton("5");
		contentPane.add(button_5);

		JButton button_6 = new JButton("6");
		contentPane.add(button_6);

		JButton button_7 = new JButton("7");
		contentPane.add(button_7);

		JButton button_8 = new JButton("8");
		contentPane.add(button_8);

		JButton button_9 = new JButton("9");
		contentPane.add(button_9);
	}

}
