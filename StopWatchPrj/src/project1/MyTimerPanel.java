package project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;




/**********************************************************************
 * This MyTimerPanel inherits properties of JPanel and focuses on 
 * designing a application that will show a StopWatch of all functions
 * 
 * @author Atone Joryman
 * @version Fall 2017
 **********************************************************************/

public class MyTimerPanel extends JPanel {
	
	private StopWatch stopWatchTimer;
	/** for loading file*/
	private static final long serialVersionUID = 1L;

	/** for the timer repeat of element*/
	private boolean flag = true;

	/** call for a virtual timer */
	private Timer javaTimer;
	
	/** give it actions*/
    private TimerListener timer;

	/** on screen buttons for stopwatch */
	private JButton start, stop, reset, add , sub;
	
	/** text to put into each instance*/
	private JTextField min, sec, milli;
	
	/** labels for each instance*/
	private JLabel minL,secL,milliL, time;
		
	/** menu items for the file */
	private JMenuItem suspend;
	
	/**drop down menu under file*/
	private JMenu file;
	
	/**store everything to a panel*/
	private JPanel panel;
	
	
/***********************************************************************
 * This default constructor is the basis of what is sent to the JPanel
 * and then the JFrame of GUI
 * @param suspend2 
 * @param quitItem 
 * 
 **********************************************************************/
	public MyTimerPanel(){
		//set up the body
		stopWatchTimer = new StopWatch (0, 0, 0);
        timer = new TimerListener();
        javaTimer = new Timer(100, timer);
        //javaTimer.start();
        javaTimer.setRepeats(flag);

        //set the layout
        setLayout(new GridLayout(4,3));
		setBackground(Color.BLUE);
		
		//initialize JButttons
		start = new JButton("START");
		stop = new JButton("STOP");
		reset = new JButton("RESET");
		add = new JButton("ADD");
		sub = new JButton("SUBTRACT");
		
		//make text fields initially 0
		min = new JTextField("0");
		sec = new JTextField("0");
		milli = new JTextField("0");
		
		//initialize JLabels
		time = new JLabel("Time: " + stopWatchTimer.toString());
		minL = new JLabel("Minute: ");
		secL = new JLabel("Second: ");
		milliL = new JLabel("Millisecond: ");
		
		//add to menu bar
//				JMenuBar menuBar = new JMenuBar();
//				menuBar.add(file);
		
		
	    //timer = new TimerListener();
		min.addActionListener(timer);
		sec.addActionListener(timer);
		milli.addActionListener(timer);
		start.addActionListener(timer);
		stop.addActionListener(timer);
		reset.addActionListener(timer);
		add.addActionListener(timer);
		sub.addActionListener(timer);
		
		//add everything to panel
		add(time);
		
		add(reset);
		add(add);
		add(minL);
		add(min);
		add(start);
		add(secL);
		add(sec);
		add(stop);
		add(milliL);
		add(milli);
		add(sub);
		
		
		
		
		
	}
		
		
		private class TimerListener implements ActionListener{
			@Override
			public void actionPerformed (ActionEvent event){
			if(event.getSource() == start){				
				javaTimer.start();

			}
				
				else {
					//while(event.getSource()!= stop){
//					for(long milli=0; milli<System.nanoTime();milli++){
//						stopWatchTimer.inc();
//						time.setText("Time " + stopWatchTimer.toString());
//					}
					
					
//					for(int i=0; i<100; i++){
//						
//					Timer milliCount = new Timer(1000, timer);
//						milliCount.start();
//						//milliCount.setRepeats(true);
//						time.setText("Time " + stopWatchTimer.toString());
//					}
					
					
//					for(int milliCount =0; milliCount< javaTimer.getDelay()/1000; milliCount++){
//						stopWatchTimer.add(milliCount);
//						time.setText("Time " + stopWatchTimer.toString());
//					}
//					long tStart = System.nanoTime();
					
					
				//	}
					//increment the time according to Timer
					stopWatchTimer.add(100);
					
					//stopWatchTimer.inc();
					//reset the text in toString 
					time.setText("Time " + stopWatchTimer.toString());
					}
				
			//when stop button is pressed
			if(event.getSource()==stop){
				javaTimer.stop();
				flag=false;
			}
			
			//when reset button is pressed
			if(event.getSource()==reset){
				stopWatchTimer= new StopWatch();
				javaTimer.stop();
				time.setText("Time " + stopWatchTimer.toString());
			}
			
			//when add button is selected 
			if(event.getSource()== add){
				int minTmp, secTmp, milliTmp;
				//change from String to Integer
				minTmp = Integer.parseInt(min.getText());
				secTmp = Integer.parseInt(sec.getText());
				milliTmp = Integer.parseInt(milli.getText());
				StopWatch newstopWatchTimer = new StopWatch(minTmp,
						secTmp, milliTmp);
				stopWatchTimer.add(newstopWatchTimer);
				javaTimer.stop();
				time.setText("Time added: " + 
				stopWatchTimer.toString());

			}
			
			//when subtraction button is selected
			if(event.getSource()== sub){
				int minTmp, secTmp, milliTmp;
				
				//change from string to integer
				minTmp = Integer.parseInt(min.getText());
				secTmp = Integer.parseInt(sec.getText());
				milliTmp = Integer.parseInt(milli.getText());
				StopWatch newstopWatchTimer = new StopWatch(minTmp,
						secTmp, milliTmp);
				stopWatchTimer.sub(newstopWatchTimer);
				javaTimer.stop();
				time.setText("Time subtracted: " + 
				stopWatchTimer.toString());

			}
			
			}
			
			
			
		}

		
	
}
