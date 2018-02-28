package changeJar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*****************************************************************
 * Class that set ups a panel of instructions for GUI
 *****************************************************************/
public class MyChangeJarPanel extends JPanel {
	private ChangeJar jar;
	private JLabel messageLb;
	private JButton button0;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton enter;
	private JButton reset;

	private String s;

	public MyChangeJarPanel() {

		s = "";
		enter = new JButton("enter");
		reset = new JButton("reset");

		button0 = new JButton("0");
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");

		messageLb = new JLabel("Jar 1");

		ButtonListener listener = new ButtonListener();

		button0.addActionListener(listener);
		button1.addActionListener(listener);
		button2.addActionListener(listener);
		button3.addActionListener(listener);
		button4.addActionListener(listener);
		button5.addActionListener(listener);
		button6.addActionListener(listener);
		button7.addActionListener(listener);
		button8.addActionListener(listener);
		button9.addActionListener(listener);
		enter.addActionListener(listener);
		reset.addActionListener(listener);

		add(button0);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		add(button7);
		add(button8);
		add(button9);
		add(enter);
		add(reset);
		add(messageLb);
	}

	private class ButtonListener implements ActionListener {
		// --------------------------------------------------------------
		// Determines which button was pressed and sets the label
		// text accordingly.
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == button0)
				s = s + "0";
			if (event.getSource() == button1)
				s = s + "1";
			if (event.getSource() == button2)
				s = s + "2";
			if (event.getSource() == button3)
				s = s + "3";
			if (event.getSource() == button4)
				s = s + "4";
			if (event.getSource() == button5)
				s = s + "5";
			if (event.getSource() == button6)
				s = s + "6";
			if (event.getSource() == button7)
				s = s + "7";
			if (event.getSource() == button8)
				s = s + "8";
			if (event.getSource() == button9)
				s = s + "9";

			if (event.getSource() == enter) {
				jar.getAmount();
				if (jar.getAmount() > 0)
					messageLb.setText("Open");
				s = "";
				jar.save("Open");
				;
			}

			if (event.getSource() == reset) {
				jar.getAmount();
				s = "";
				messageLb.setText("Closed");
			}

		}
	}

}
