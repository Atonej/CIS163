package campingsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**********************************************************************
 * GUI frame for Camping Sites lists
 * 
 * @author Atone Joryman
 * @version Fall 2017
 *********************************************************************/

@SuppressWarnings("serial")
public class GUICampingReg extends JFrame implements ActionListener {

	/* Holds all JMenus */
	private JMenuBar menus;

	/* JMenu for file operations */
	private JMenu fileMenu;
	/* JMenu for checking in */
	private JMenu checkingInMenu;
	/* JMenu for edit methods */
	private JMenu editMenu;

	private JMenu checkOut;

	private JMenu streamMenu;

	private JMenuItem openSerItem;
	private JMenuItem exitItem;
	private JMenuItem saveSerItem;
	private JMenuItem openTextItem;
	private JMenuItem saveTextItem;
	private JMenuItem tentCheckInItem;
	private JMenuItem rvCheckInItem;
	private JMenuItem visualOfSiteItem;
	private JMenuItem bookedDaysItem;
	private JMenuItem checkingOut;

	private JMenu viewMenu;

	private JMenuItem removeItem;
	private JMenuItem undoItem;

	private JTable jListTable;
	private SiteModel siteList;

	private JPanel datePanel;

	private JMenu sortMenu;

	private JMenuItem sortNameItem;
	private JMenuItem sortDaysStayingItem;
	private JMenuItem sortDateItem;
	private JMenuItem streamItem;

	private Site siteCarry;

	public GUICampingReg() {

		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		checkingInMenu = new JMenu("Checking In");
		viewMenu = new JMenu("View");
		checkOut = new JMenu("Checking Out");
		sortMenu = new JMenu("Sort");
		streamMenu = new JMenu("Stream");

		visualOfSiteItem = new JMenuItem("Visual of Site");
		bookedDaysItem = new JMenuItem("Fully booked days");

		tentCheckInItem = new JMenuItem("Check-in Tent site");
		rvCheckInItem = new JMenuItem("Check-in RV site");

		checkingOut = new JMenuItem("Checking Out");

		sortDateItem = new JMenuItem("Date");
		sortNameItem = new JMenuItem("Name");
		sortDaysStayingItem = new JMenuItem("Days Staying");
		streamItem = new JMenuItem("Stream");

		checkingInMenu.add(tentCheckInItem);
		checkingInMenu.add(rvCheckInItem);

		checkOut.add(checkingOut);

		sortMenu.add(sortNameItem);
		sortMenu.add(sortDaysStayingItem);
		sortMenu.add(sortDateItem);

		streamMenu.add(streamItem);

		removeItem = new JMenuItem("Remove");
		undoItem = new JMenuItem("Undo");

		openSerItem = new JMenuItem("Open Serial");
		saveSerItem = new JMenuItem("Save Serial");
		exitItem = new JMenuItem("Exit");
		openTextItem = new JMenuItem("Open Text");
		saveTextItem = new JMenuItem("Save Text");

		fileMenu.add(openSerItem);
		fileMenu.add(saveSerItem);
		fileMenu.addSeparator();
		fileMenu.add(openTextItem);
		fileMenu.add(saveTextItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		openSerItem.addActionListener(this);
		exitItem.addActionListener(this);
		saveSerItem.addActionListener(this);
		openTextItem.addActionListener(this);
		saveTextItem.addActionListener(this);

		visualOfSiteItem.addActionListener(this);
		bookedDaysItem.addActionListener(this);

		tentCheckInItem.addActionListener(this);
		rvCheckInItem.addActionListener(this);

		removeItem.addActionListener(this);
		undoItem.addActionListener(this);

		checkingOut.addActionListener(this);

		sortNameItem.addActionListener(this);
		sortDaysStayingItem.addActionListener(this);
		sortDateItem.addActionListener(this);

		streamItem.addActionListener(this);

		editMenu.add(removeItem);
		editMenu.add(undoItem);

		viewMenu.add(visualOfSiteItem);
		viewMenu.add(bookedDaysItem);

		menus = new JMenuBar();

		menus.add(fileMenu);
		menus.add(editMenu);
		menus.add(checkingInMenu);
		menus.add(checkOut);
		menus.add(sortMenu);
		menus.add(streamMenu);
		menus.add(viewMenu);
		setJMenuBar(menus);

		siteList = new SiteModel();
		jListTable = new JTable(siteList);

		// Add jListTable as a JScrollPane
		// Allows usage of getColumnName in SiteModel
		add(new JScrollPane(jListTable));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800, 500);

	}

	private void visualOfCampsite() {
		datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(2, 1));

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatCal = new SimpleDateFormat("M/d/yyyy");

		JLabel dateRequest = new JLabel("Enter desired date:");
		JTextField userDate = new JTextField();
		userDate.setText(formatCal.format(cal.getTime()));
		datePanel.add(dateRequest);
		datePanel.add(userDate);

		boolean validDate = false;

		int result = JOptionPane.showConfirmDialog(null, datePanel, "",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			try {
				java.util.Date date = null;
				date = formatCal.parse(userDate.getText());
				GregorianCalendar cal1 = new GregorianCalendar();
				cal1.setTime(date);
				validDate = true;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Incorrect date format.");
			}
		}

		if (validDate) {
			try {
				SiteVisual v = new SiteVisual(this, siteList,
						userDate.getText());
				v.setModal(true);
				v.setSize(600, 400);
				v.setVisible(true);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Date out of range.");
			}
		}
	}

	private void daysBookedFrame() {
		JFrame bookedFrame = new JFrame();
		BookedDaysList bookedDays = new BookedDaysList(siteList);
		JTable bookedTable = new JTable(bookedDays);
		bookedFrame.add(new JScrollPane(bookedTable));
		bookedFrame.setSize(300, 600);
		bookedFrame.setVisible(true);
	}

	public void getList() {
		siteList.getList();
	}

	// -----------------------------------------------------------------
	// Sorts the specified array of objects using an insertion
	// sort algorithm.
	// -----------------------------------------------------------------
	public static void insertionSort(Site site,
			Comparable<Site>[] data) {
		for (int index = 1; index < data.length; index++) {
			Comparable<Site> key = data[index];
			int position = index;

			// Shift larger values to the right
			while (position > 0
					&& data[position - 1].compareTo((Site) key) > 0) {
				data[position] = data[position - 1];
				position--;
			}

			data[position] = key;
		}
	}

	/******************************************************************
	 * Displays the cost of the requested reservation using polymorphic 
	 * cost methods.
	 * 
	 * @param s
	 *            Site to display cost for site 0(1)
	 *****************************************************************/
	public void displayCost(Site site) {
		if (siteList.getIsAdded() == true) {
			JOptionPane.showMessageDialog(null,
					"You owe: " + site.cost() + " Dollars");

		}

		if (siteList.getCheckOut() == true) {
			JOptionPane.showMessageDialog(null,
					"Late fee added: " + site.getLateFee()
							+ " Dollars\n" + "You owe: " + site.cost()
							+ " Dollars");

		}
		siteList.setIsAdded(false);
	}

	public void actionPerformed(ActionEvent e) {

		// Exit button
		if (e.getSource() == exitItem) {
			System.exit(0);
		}

		if (e.getSource() == openSerItem) {
			try {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					String filename = chooser.getSelectedFile()
							.getAbsolutePath();
					siteList.loadFromSerialized(filename);
				}
			}

			catch (Exception error) {
				JOptionPane.showMessageDialog(null,
						siteList.getReturnStat());

			}

			siteList.update();
		}

		if (e.getSource() == saveSerItem) {
			try {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					String filename = chooser.getSelectedFile()
							.getAbsolutePath();
					siteList.saveAsSerialized(filename);
				}
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null,
						siteList.getReturnStat());
			}
			siteList.update();

		}

		if (e.getSource() == openTextItem) {
			try {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					String filename = chooser.getSelectedFile()
							.getAbsolutePath();
					siteList.loadText(filename);
				}
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null,
						siteList.getReturnStat());

			}
			siteList.update();

		}

		if (e.getSource() == saveTextItem) {
			try {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					String filename = chooser.getSelectedFile()
							.getAbsolutePath();
					siteList.saveText(filename);
				}
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null,
						siteList.getReturnStat());

			}
			siteList.update();

		}

		if (e.getSource() == rvCheckInItem) {
			RV RVRental = new RV();

			// Creates a dialog box for tent check in
			DialogCheckInRv checkIn = new DialogCheckInRv(this,
					RVRental, siteList);

			siteCarry = RVRental;
			// Waits for dialogue box to close before continuing
			checkIn.setModal(true);
			// Displays dialog box
			checkIn.setSize(350, 250);
			checkIn.setVisible(true);

			if (siteList.getReturnStat() == "added")
				displayCost(siteCarry);

			else if (siteList.getReturnStat() != null) {

				JOptionPane.showMessageDialog(null,
						siteList.getReturnStat());
			}

			siteList.update();
			// jListTable.revalidate();
			// jListTable.repaint();
		}

		if (e.getSource() == tentCheckInItem) {

			Tent tentRental = new Tent();

			// Creates a dialog box for tent check in
			DialogCheckInTent t = new DialogCheckInTent(this,
					tentRental, siteList);

			// Waits for dialogue box to close before continuing
			t.setModal(true);
			// Displays dialog box
			t.setSize(350, 250);
			t.setVisible(true);

			siteCarry = tentRental;

			if (siteList.getReturnStat() == "added")
				displayCost(siteCarry);

			else if (siteList.getReturnStat() != null) {

				JOptionPane.showMessageDialog(null,
						siteList.getReturnStat());
			}

			siteList.update();

			// jListTable.revalidate();
			// jListTable.repaint();
		}

		if (e.getSource() == visualOfSiteItem) {
			visualOfCampsite();
			jListTable.revalidate();
			jListTable.repaint();
		}

		if (e.getSource() == removeItem) {
			siteList.deleteReservation(jListTable.getSelectedRows());
		}

		if (e.getSource() == undoItem) {
			siteList.undo();
			if (siteList.getReturnStat() == "Nothing to Undo") {
				JOptionPane.showMessageDialog(null, "Nothing to Undo");
			}

			else if (siteList
					.getReturnStat() == "ERROR Parse Exception") {
				JOptionPane.showMessageDialog(null,
						"ERROR Parse Exception");
			}
		}

		if (e.getSource() == bookedDaysItem) {
			daysBookedFrame();
		}

		if (e.getSource() == sortNameItem) {
			Collections.sort(siteList.getList(),
					SortByName.COMPARE_BY_NAME);
			siteList.update();
		}

		if (e.getSource() == sortDateItem) {
			// this function is the same method as the sortName class except
			// simplified
			// first lambda function
			// Collections.sort(siteList.getList(), (Site one, Site other) -> {
			// return one.getCheckIn().compareTo(other.getCheckIn());
			// });
			// lambda function even more simplified
			// doesn't need return, class or brackets
			Collections.sort(siteList.getList(), (one, other) -> one
					.getCheckIn().compareTo(other.getCheckIn()));

			siteList.update();
		}

		if (e.getSource() == sortDaysStayingItem) {

			Comparator<Site> daysStaying = new Comparator<Site>() {
				// Anonymous Class
				@Override
				// can't sort more than one digit because of string
				public int compare(Site site, Site siteOther) {
					return Integer.toString(site.getDaysStaying())
							.compareTo(Integer.toString(
									siteOther.getDaysStaying()));

				}
			};

			Collections.sort(siteList.getList(), daysStaying);

			siteList.update();
		}

		if (e.getSource() == streamItem) {

			if(siteList.streaming().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "There is no one "
						+ "staying over 3 days");
			}
			else {
			JOptionPane.showMessageDialog(null,
					"The People who are staying 3 "
							+ "days or more are below:\n"
							+ siteList.streaming());
			}
	// Stream.of(sum).forEach(element -> System.out.println(element));

		}

		if (e.getSource() == checkingOut) {
			int spot = jListTable.getSelectedRow();
			// GregorianCalendar newDate =
			// siteList.getList().get(spot).getCheckIn();
			try {
				// Creates a dialog box for check out of reservation
				DialogCheckOut checkOut = new DialogCheckOut(this,
						siteCarry, siteList, spot);

				// siteList.deleteReservation(jListTable.getSelectedRows());

				// Waits for dialogue box to close before continuing
				checkOut.setModal(true);
				// Displays dialog box
				checkOut.setSize(550, 250);
				checkOut.setVisible(true);
//				GregorianCalendar temp = siteList.getList().get(spot).getCheckIn();
//				siteCarry.setCheckIn(temp);
//				//if the site is checking out with a reserved site 
//				//in front of it
//				  if(siteList.getListDate().isReserved(siteCarry)) {
//					 JOptionPane.showMessageDialog(null, 
//							 "Cannot check out, already taken");
//					 return;
//				  }
				  
				   if (siteList.getCheckOut() == true) {
					siteList.dayLeaving(spot);
				}
				displayCost(siteCarry);
				siteList.setCheckOut(false);

				// if(siteList.getCheckOut() == false) {
				// checkOut.setVisible(true);
				// }

			}

			catch (ArrayIndexOutOfBoundsException error) {
				JOptionPane.showMessageDialog(null,
						"Choose a reservation");
			} 
		}
	}

	public static void main(String[] args) {
		new GUICampingReg();
	}

}