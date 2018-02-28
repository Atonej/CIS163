package changeJar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MyChangeJarGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My3ChangeJarGUI frame = new My3ChangeJarGUI();
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
	public MyChangeJarGUI() {   
		

    
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(26, 13, 28, 14);
		contentPane.add(lblTotal);

		JLabel lblQuarters = new JLabel("Quarters:");
		lblQuarters.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuarters.setBounds(26, 42, 65, 17);
		contentPane.add(lblQuarters);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q;
				String Quarters = textField.getText();
				q = Integer.parseInt(Quarters);

			}
		});
		textField.setBounds(101, 39, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblDimes = new JLabel("Dimes:");
		lblDimes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDimes.setBounds(26, 67, 47, 14);
		contentPane.add(lblDimes);

		textField_1 = new JTextField();
		textField_1.setBounds(101, 127, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNickels = new JLabel("Nickels");
		lblNickels.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNickels.setBounds(26, 92, 65, 20);
		contentPane.add(lblNickels);

		textField_2 = new JTextField();
		textField_2.setBounds(101, 64, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblPennies = new JLabel("Pennies:");
		lblPennies.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPennies.setBounds(26, 123, 65, 26);
		contentPane.add(lblPennies);

		textField_3 = new JTextField();
		textField_3.setBounds(101, 96, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JButton btnPutIn = new JButton("Put In");
		btnPutIn.setBounds(61, 215, 141, 35);
		contentPane.add(btnPutIn);

		JButton btnTakeOut = new JButton("Take Out");
		btnTakeOut.setBounds(268, 215, 141, 35);
		contentPane.add(btnTakeOut);
	}
}

