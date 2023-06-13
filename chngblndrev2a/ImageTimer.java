package chngblndrev2a;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageTimer implements Runnable {
	
	// Trial Timer control.
	AtomicBoolean runWatch;
	long maskDuration = 0;
	long imgDuration = 0;
	// Label to update.
	JLabel lblToUpdate;
	// Resources for label update.
	String maskStr = null;
	String imgAStr = null;
	String imgBStr = null;
	String threadName = null;
	
	// Constructor to instantiate class.
	public ImageTimer(AtomicBoolean stopWatchRunning, long maskDuration, long imgDuration, JLabel label, String imgMaskStr, String imgAStr, String imgBStr, String threadName ) {
		this.runWatch = stopWatchRunning;
        this.maskDuration = maskDuration;
        this.imgDuration = imgDuration;
        this.lblToUpdate = label;
        this.maskStr = imgMaskStr;
        this.imgAStr = imgAStr;
        this.imgBStr = imgBStr;
        this.threadName = threadName;
        
//        System.out.println("Constructor Completed.");
    }
	
	public void run() {		

		//System.out.println("Run Method Started.");
		Thread.currentThread().setName(threadName);
		String currentImgStr = imgAStr;

		// Outer loop only runs when trial timer is running.
		// while(runWatch.get()){}
		while(runWatch.get()){	
				    
		    if(maskDuration > 0) {
		    	// Display mask.
		    	//System.out.println("Mask image: " + maskStr);
				lblToUpdate.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource(maskStr)));
				lblToUpdate.repaint();
				
				long timeElapsedMask = 0;
				long startTimeMask = Instant.now().toEpochMilli();	
				
				// Start mask timer.
				while(timeElapsedMask < maskDuration){
			    
				    timeElapsedMask = (Instant.now().toEpochMilli()) - startTimeMask;		    
			    
		//		    whileLoopPasses++;
				    //System.out.println("Mask Duration: " + Float.toString(maskDuration));
				    //System.out.println("Time Elapsed: " + Float.toString(timeElapsedMask));	
				    //System.out.println("Mask Duration loop running.");
				    
				    //System.out.println("Mask Timer running.");
			    }	
		    
			// Reset displayed image after mask
			//System.out.println("Before Timer Test image: " + currentImgStr);
			lblToUpdate.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource(currentImgStr)));
			lblToUpdate.repaint();			
		    }
		    
			long timeElapsedImg = 0;
			long startTimeImg = Instant.now().toEpochMilli();
			
			lblToUpdate.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource(currentImgStr)));
			lblToUpdate.repaint();
			
			//System.out.println("Image Duration: " + imgDuration);
			while(timeElapsedImg < imgDuration){			
				timeElapsedImg = (Instant.now().toEpochMilli()) - startTimeImg;			
			}
		    
			// Reset test image to display.
			if(currentImgStr == imgAStr) {
				currentImgStr = imgBStr;
			}else{
				currentImgStr = imgAStr;
			}
			//System.out.println("After Timer Test image: " + currentImgStr);
		}
//	    whileLoopPassesStr = Long.toString(whileLoopPasses);
//	    System.out.println("Passes thru while loop: " + whileLoopPassesStr);
	    
//	    System.out.println("Run Method Ending.");

	    //System.out.println("Duration loop exiting.");
	    //System.out.println("Thread name: " + Thread.currentThread().getName());
	    
	}

}

