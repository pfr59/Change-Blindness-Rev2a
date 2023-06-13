package chngblndrev2a;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DurationTimer implements Runnable {
	
	// Trial Timer control.
	AtomicBoolean runWatch = new AtomicBoolean(false);
	// Flag correct answer.
//	AtomicBoolean answerCorrect = new AtomicBoolean(false);
	// Duration for timer event.
	long duration = 0;
	// Label to update.
	JLabel lblToUpdate;
	// Resource for label update.
	String resourceStr = null;
	String threadName = null;
	Runnable nextThread;
	
	// Constructor to instantiate class.
	public DurationTimer(AtomicBoolean stopWatchRunning, long eventDuration, JLabel label, String imgResource, String threadName, Runnable nextThread ) {
		this.runWatch = stopWatchRunning;
//        this.answerCorrect = correctAnswer;
        this.duration = eventDuration;
        this.lblToUpdate = label;
        this.resourceStr = imgResource;
        this.threadName = threadName;
        this.nextThread = nextThread;
        
//        System.out.println("Constructor Completed.");
    }
	
	public void run() {		

//		System.out.println("Run Method Started.");
		Thread.currentThread().setName(threadName);
		
		lblToUpdate.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource(resourceStr)));;
		lblToUpdate.repaint();

		long timeElapsed = 0;
		long startTime = Instant.now().toEpochMilli();
			    
	    while(timeElapsed < duration){
//		    long endTime = Instant.now().toEpochMilli();			    
//		    timeElapsed = endTime - startTime;
		    
		    timeElapsed = (Instant.now().toEpochMilli()) - startTime;		    
	    
//		    whileLoopPasses++;
		    //System.out.println("Duration: " + Float.toString(duration));
		    //System.out.println("Time Elapsed: " + Float.toString(timeElapsed));	
		    //System.out.println("Duration loop running.");		    
	    }		    
//	    whileLoopPassesStr = Long.toString(whileLoopPasses);
//	    System.out.println("Passes thru while loop: " + whileLoopPassesStr);
	    
//	    System.out.println("Run Method Ending.");

	    //System.out.println("Duration loop exiting.");
	    //System.out.println("Thread name: " + Thread.currentThread().getName());

	    new Thread(nextThread).start();
	    
	}

}

