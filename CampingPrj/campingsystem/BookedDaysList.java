package campingsystem;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

/**********************************************************************
 * Table model for list of fully booked days.
 
 * @version November 1 2017
 *********************************************************************/

@SuppressWarnings("serial")
public class BookedDaysList extends AbstractTableModel {

	private ArrayList<String> dateList;
	
	public BookedDaysList(SiteModel listSites) {
		dateList = new ArrayList<String>();
		dateList = listSites.getBookedDays();
	}
	
	public int getRowCount() {
		return dateList.size();
	}


	public int getColumnCount() {
		return 1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return dateList.get(rowIndex);
	}
	
	public String getColumnName(int col) {
		return "Fully Booked Dates";
	}
	
}