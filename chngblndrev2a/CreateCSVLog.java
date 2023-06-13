package chngblndrev2a;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateCSVLog {
	
	String logFileAbsolutePath = null;
	String recordName;
	String recordHeader;
	File resultsFile;
	boolean logFileCreated = false;
	
	public CreateCSVLog(String recNameStr, String recHeadStr){
		//System.out.println("Header from Main Init:" +recHeadStr);
		this.recordName = recNameStr;
		this.recordHeader = recHeadStr;	
		//System.out.println("Header from Main Init:" +recordHeader );
	}
	
	protected boolean openFileDialog(){
		JFrame parentFrame = new JFrame();				 
		JFileChooser fileChooser = new JFileChooser();
		//Set an extension filter, so the user sees other csv files
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setDialogTitle("Specify a file to save.");
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    logFileAbsolutePath = fileToSave.getAbsolutePath();				    
		    // Check for .csv file extension.
		    if(logFileAbsolutePath.endsWith(".csv")) {
		    	createLog(logFileAbsolutePath);
		    }else {
		    	// Add .csv file extension if missing.
		    	logFileAbsolutePath = fileToSave.getAbsolutePath()+".csv";
		    	createLog(logFileAbsolutePath);
		    }				    
		    //System.out.println("Save as file: " + logFileAbsolutePath);
		    logFileCreated = true;
		}
		return logFileCreated;
	}

	protected void createLog(String createLogFile){
		// TODO Auto-generated method stub
		LocalDateTime fileCreateDateTime = LocalDateTime.now(); 
		DateTimeFormatter myFormatDateTime = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String fileCreationDateTimeStr = myFormatDateTime.format(fileCreateDateTime);
		//System.out.println("DateTime: " + fileCreationDateTimeStr);
		
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(createLogFile, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileWriter.write(recordName + " \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileWriter.write( "Log Created: " + fileCreationDateTimeStr + " \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileWriter.write(recordHeader + " \n");
			//System.out.println("Record Header:" + recordHeader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	protected void writeRecord(String resultsRecordStr) {
		// TODO Auto-generated method stub
		
		Writer fileWriter = null;
		
		// Open file to APPEND.
		try {
			fileWriter = new FileWriter(logFileAbsolutePath, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileWriter.write(resultsRecordStr +",\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
