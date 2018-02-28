 package campingsystem;

import java.text.DateFormat;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**********************************************************************
 * Dialog Box for reserving tent site
 * 
 
 * @version November 1 2017
 *********************************************************************/
@SuppressWarnings("serial")
public class DialogCheckInTent extends JDialog implements ActionListener {
	private JTextField nameTxt;
	private JTextField OccupyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JTextField numberStayingTxt;

	private JLabel nameLabel; 
	private JLabel occupyingLabel;
	private JLabel stayingLabel;
	private JLabel siteLabel;
	private JLabel numberStayingLabel;

	private JButton okButton;
	private JButton cancelButton;

	private SiteModel siteList;

	private Tent tent;  

	private ButtonListener listener;

	private JPanel panel;

	public DialogCheckInTent(JFrame paOccupy, Tent d, SiteModel listSites) { 
		super(paOccupy);
		siteList = listSites;
		tent = d;
		panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		Box nameBox = new Box(BoxLayout.X_AXIS);
		Box siteBox = new Box(BoxLayout.X_AXIS);
		Box occupyBox = new Box(BoxLayout.X_AXIS);
		Box stayBox = new Box(BoxLayout.X_AXIS);
		Box numStayBox = new Box(BoxLayout.X_AXIS);
		Box buttonBox = new Box(BoxLayout.X_AXIS);

		listener = new ButtonListener();

		okButton = new JButton("Enter");
		cancelButton = new JButton("Cancel");

		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);

		nameTxt = new JTextField();
		nameLabel = new JLabel("Name of reserver");
		nameTxt.setText("jane doe");
		nameTxt.setColumns(25);

		nameBox.add(nameLabel);
		//112
		nameBox.add(Box.createHorizontalStrut(75));
		nameBox.add(nameTxt);
		panel.add(nameBox);
		
		siteNumberTxt = new JTextField();
		siteLabel = new JLabel("Site number");
		siteNumberTxt.setText("4");
		siteNumberTxt.setColumns(30);

		siteBox.add(siteLabel);
		siteBox.add(Box.createHorizontalStrut(112));
		siteBox.add(siteNumberTxt);
		panel.add(siteBox);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatCal = new SimpleDateFormat("M/d/yyyy");
		OccupyedOnTxt = new JTextField();
		occupyingLabel = new JLabel("Date of reservation");
		OccupyedOnTxt.setText(formatCal.format(cal.getTime()));
		OccupyedOnTxt.setColumns(25);

		occupyBox.add(occupyingLabel);
		occupyBox.add(Box.createHorizontalStrut(60));
		occupyBox.add(OccupyedOnTxt);
		panel.add(occupyBox);

		stayingTxt = new JTextField();
		stayingLabel = new JLabel("Days planning on staying");
		stayingTxt.setText("5");
		stayingTxt.setColumns(25);

		stayBox.add(stayingLabel);
		stayBox.add(Box.createHorizontalStrut(19));
		stayBox.add(stayingTxt);
		panel.add(stayBox);
		
		numberStayingTxt = new JTextField();
		numberStayingLabel = new JLabel("How many are staying");
		numberStayingTxt.setText("3");
		numberStayingTxt.setColumns(25);

		numStayBox.add(numberStayingLabel);
		numStayBox.add(Box.createHorizontalStrut(40));
		numStayBox.add(numberStayingTxt);
		panel.add(numStayBox);

		buttonBox.add(okButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(cancelButton);

		panel.add(Box.createVerticalStrut(20));
		panel.add(buttonBox);
		panel.add(Box.createVerticalStrut(20));

		panel.setVisible(true);

		paOccupy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel); 

	}
	
	public void setSiteDefault(int site) {
		siteNumberTxt.setText(site + "");
	}
	
	public void setDateDefault(String date) {
		OccupyedOnTxt.setText(date);
	}


	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) throws NumberFormatException{

			if (e.getSource() == okButton) {
				try {

					tent.setNameReserving(nameTxt.getText());
					tent.setDaysStaying(Integer.parseInt(stayingTxt.getText()));
					tent.setSiteNum(Integer.parseInt(siteNumberTxt.getText()));
					tent.setNumTenters(Integer.parseInt(numberStayingTxt.getText()));

					DateFormat df = new SimpleDateFormat("M/d/yyyy");
					java.util.Date date = null;
					try {
						date = df.parse(OccupyedOnTxt.getText());
						GregorianCalendar cal = new GregorianCalendar();
						cal.setTime(date);
						tent.setCheckIn(cal);
					} catch (Exception e1) {
						throw new InvalidDate();
					}

					if(siteList.isValidInput(tent))
						siteList.addTent(tent);
					else
						throw new IllegalArgumentException();

				}
				catch(InvalidDate e1) {
					JOptionPane.showMessageDialog
					(null, "Invalid Date Format!");
				}

				catch(IllegalArgumentException e1){
					JOptionPane.showMessageDialog
					(null, "Invalid Numerical Input!");
				}

				//closeStatus = true;
			}

			dispose();

		}
	}


	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub




	}


}
