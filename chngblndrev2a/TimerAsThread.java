/* 
 * This class implements a timer as a thread, allowing the parent class
 * to process user input while it executes.
 * 
 * Parent class must have an Atomic Boolean to pass to this 
 * class's constructor in the form of: 
 *	
 *		final AtomicBoolean stopWatchRunning = new AtomicBoolean(false);
 *
 * The parent class instantiates and starts this time class similar to this:
 *
 *		Runnable timer = new TimerAsThread(textFieldOutput, stopWatchRunning);
 *		stopWatchRunning.set(true);
 *		new Thread(timer).start();
 *	
 * The parent class stops the timer by:
 *
 *		stopWatchRunning.set(false);
 * 
*/

package chngblndrev2a;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTextField;

public class TimerAsThread implements Runnable {
	
	JTextField textFieldToUpate;
	AtomicBoolean runWatch = new AtomicBoolean(false);
	
	// Constructor to instantiate class.
	public TimerAsThread(JTextField textFieldPassed, AtomicBoolean stopWatchRunning) {
        this.textFieldToUpate = textFieldPassed;
        this.runWatch = stopWatchRunning; 
        
//        System.out.println("Constructor Completed.");
    }
	
	public void run() {
//		System.out.println("Run Method Started.");		
		
		textFieldToUpate.setText("0:0:0:000");
		textFieldToUpate.repaint();
		long milliseconds = 0;
		String millisecondsStr;
		long secondsThisLoop = 0;
		long secondsLastLoop = 0;
		long secondsDisplay = 0;
		String secondsDisplayStr;
		String minutesDisplayStr;
		String hoursDisplayStr;
		long minutes = 0;
		long hours = 0;
//		long whileLoopPasses = 0;
//		String whileLoopPassesStr;
		long startTime = Instant.now().toEpochMilli();
	    
	    while(runWatch.get()){
		    long endTime = Instant.now().toEpochMilli();			    
		    long timeElapsed = endTime - startTime;
		    milliseconds = (timeElapsed % 1000);
		    millisecondsStr = Long.toString(milliseconds);
		    secondsThisLoop = (timeElapsed/1000);
		    if(secondsThisLoop > secondsLastLoop ){
		    	secondsDisplay++;			    	
		    }
		    if(secondsDisplay == 60){
		    	minutes++;
		    	secondsDisplay = 0;
		    }
		    if(minutes == 60){
		    	hours++;
		    	minutes = 0;
		    }
//		    System.out.println("Execution time in milliseconds: " + millisecondsStr);
		    hoursDisplayStr = Long.toString(hours);
		    minutesDisplayStr = Long.toString(minutes);			    
		    secondsDisplayStr = Long.toString(secondsDisplay);
		    textFieldToUpate.setText(hoursDisplayStr + ":" + minutesDisplayStr + ":" + secondsDisplayStr + ":" + millisecondsStr);
		    textFieldToUpate.repaint();
		    secondsLastLoop = secondsThisLoop;
		    
//		    whileLoopPasses++;
//		    System.out.println("Trial Time Loop running.");
		    
	    }		    
//	    whileLoopPassesStr = Long.toString(whileLoopPasses);
//	    System.out.println("Passes thru while loop: " + whileLoopPassesStr);
	    
//	    System.out.println("Run Method Ending.");
	}

}
