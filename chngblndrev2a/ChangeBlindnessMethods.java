/*
 * This class is a copy of the first main class version of the Change Blindness application.
 * It mixed the setup of the GUI with methods to execute the program's basic functions.
 * 
 * This class is intended to provide the core functionality of the program separate from the 
 * main class which executes the GUI. This should help make it easier to modify the GUI, while
 * maintaining functionality.
 * 
 * As much as possible this class will execute the operations of the program. 
 * 	1. Variables are passed via set and get methods.
 * 	2. Methods are called by the GUI when needed.
 * 	3. The original code is pruned as much as practical.  
 * 
 */

package chngblndrev2a;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChangeBlindnessMethods {

	String maskTimeStr = null;
	Integer maskTimeInt = 500;
	String alterSpeedStr = null;
	Integer alterSpeedInt = 2000;	
	private JTextField textTrialTimer;
	private JTextField textIncorrectClicks;
	Integer incorrectClickCount = 0;
	String currentImageSet;
	String changeFound = "NO";
	boolean trialLogCreated = false;
	
	// For trial timer control.
	AtomicBoolean trialTimerRunning = new AtomicBoolean(false);
	
	// Header String for CSV Log.
	String headerStr = "Image Set, Mask Time (ms), Alteration Speed (ms), Trial Time (hh:mm:ss.ms), Incorrect Clicks, Solved";
	CreateCSVLog changeBlindLog = new CreateCSVLog("Change Blindness", headerStr);

	protected void setTrialLog(boolean trialLogYesNo) {		
		trialLogCreated = trialLogYesNo;
	}
	
	protected boolean getTrialLog() {		
		return trialLogCreated;
	}
	
	protected void setTrialTimerRunning(boolean runningYesNo) {
		trialTimerRunning.set(runningYesNo);
	}
	
	protected boolean getTrialTimerRunning() {
		return trialTimerRunning.get();
	}
	
	protected void setTimerOutput(JTextField textTimerOutput) {
		textTrialTimer = textTimerOutput;
	}
	
	protected void setInncorrectClickOutput(JTextField textIncorrectClickOutput) {
		textIncorrectClicks = textIncorrectClickOutput;
	}
	
	protected void trialTimerControl(Runnable threadName) {
		if(trialTimerRunning.get() == false){
			changeFound = "NO";
			Runnable trialTimer = new TimerAsThread(textTrialTimer, trialTimerRunning);
			trialTimerRunning.set(true);
			new Thread(trialTimer).start();
			new Thread(threadName).start();
			// Reset incorrect click count.
			incorrectClickCount = 0;
			textIncorrectClicks.setText(Integer.toString(incorrectClickCount));
		}else if(trialTimerRunning.get() == true){
			trialTimerRunning.set(false);
			writeTrialRecord();
		}		
	}

	protected void setAlterSpeed(String alterSpeedStr2) {
		// alterSpeed will be evaluated with respect milliseconds.
		if(alterSpeedStr2 == "250ms"){
			alterSpeedInt = 250;
		}else if(alterSpeedStr2 == "500ms"){
			alterSpeedInt = 500;
		}else if(alterSpeedStr2 == "1s"){
			alterSpeedInt = 1000;
		}else if(alterSpeedStr2 == "2s"){
			alterSpeedInt = 2000;
		}		
		//System.out.println("Alteration Speed: " + Integer.toString(alterSpeedInt) + "ms.");		
	}

	protected void setMaskTime(String maskTimeStr2) {
		if(maskTimeStr2 == "0ms"){
			maskTimeInt = 0;
		}else if(maskTimeStr2 == "50ms"){
			maskTimeInt = 50;
		}else if(maskTimeStr2 == "100ms"){
			maskTimeInt = 100;
		}else if(maskTimeStr2 == "200ms"){
			maskTimeInt = 200;
		}else if(maskTimeStr2 == "500ms"){
			maskTimeInt
			= 500;
		}		
		//System.out.println("Mask Time: " + Integer.toString(maskTimeInt) + "ms.");
	}
	
	protected void incrementIncorrect() {
		if(trialTimerRunning.get()) {
			++incorrectClickCount;
			textIncorrectClicks.setText(Integer.toString(incorrectClickCount));
		}		
	}
	
	protected void writeTrialRecord(){
		// add boolean if to only write record if a log has been created.
		
		if(trialLogCreated){
			// String headerStr = "Image Set, Mask Time, Alteration Speed, Trial Time, Incorrect Clicks, Solved";
			String maskTimeRecStr = Integer.toString(maskTimeInt);
			String alterSpeedRecStr = Integer.toString(alterSpeedInt);
			String recordStr = currentImageSet + ", " + maskTimeRecStr + ", " + alterSpeedRecStr + ", " + textTrialTimer.getText() + ", " +  textIncorrectClicks.getText() + ", " + changeFound + ", ";
			changeBlindLog.writeRecord(recordStr);
		}
	}
	
	protected void changeFound() {		
		if(trialTimerRunning.get()) {
			// Stop timers.
			trialTimerRunning.set(false);
			changeFound = "Yes";
			JOptionPane.showMessageDialog(null, "Change Found - Congratulations!", currentImageSet, JOptionPane.PLAIN_MESSAGE);
			writeTrialRecord();
		}
	}
	
	protected void selectImgSetStartStopTrial(JTabbedPane tabbedPane, JPanel panelSetSelected, JLabel lblImgSet, String imgSetSelected, String imgPathMask, String imgPathA, String imgPathB, String setThreadName) {
		// Check if there is a log and currently running trial.
		if(trialLogCreated == false && trialTimerRunning.get() == false) {			
			int input = JOptionPane.showConfirmDialog(null, "Proceed without Trial log?");
			// 0=yes, 1=no, 2=cancel
			//System.out.println(input);			
			switch(input) {
				case 0: 
					// Yes - Run without log.
					tabbedPane.setSelectedComponent(panelSetSelected);
					Runnable setOneThread = new ImageTimer(trialTimerRunning, maskTimeInt, alterSpeedInt, lblImgSet, imgPathMask, imgPathA, imgPathB, setThreadName);
					trialTimerControl(setOneThread);				
					if(trialTimerRunning.get()) {
						currentImageSet = imgSetSelected;
					}
					//System.out.println("Run trail without a log.");
					break;
				case 1:
					// No - Create/select a log.
					//trialLogCreated = changeBlindLog.openFileDialog();
					trialLogCreated = createLogFile();
					//System.out.println("Create or select a log.");
					break;	
				case 3:
					// Do nothing.
					//System.out.println("Do Nothing.");
			}
		}else if(trialLogCreated == false && trialTimerRunning.get() ) {
			// Stop trial without a log.
			trialTimerRunning.set(false);
		}else {	
			// This will automatically execute if there is a log.
			tabbedPane.setSelectedComponent(panelSetSelected);
			Runnable setOneThread = new ImageTimer(trialTimerRunning, maskTimeInt, alterSpeedInt, lblImgSet, imgPathMask, imgPathA, imgPathB, setThreadName);
			trialTimerControl(setOneThread);				
			if(trialTimerRunning.get()) {
				currentImageSet = imgSetSelected;
			}
		}
	}
	
	// Method callable from within package.
	protected boolean createLogFile() {		
		return changeBlindLog.openFileDialog();
	}
}
