package chngblndrev2a;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.*;

public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922313714245091658L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/* The following two lines of code open a frame containing
					 * the resources.html file, but the links will not open 
					 * Uncomment the following two lines to open "about.html"
					 * in a Java frame instead of a browser. */
//					About frame = new About();
//					frame.setVisible(true);
					/* End of frame with dead links*/
					
					/* The following opens the resources.html file in a browser, but the 
					 * links only work in Eclipse, not a runnable jar file.
					 */
//					Desktop desktopBrowser = Desktop.getDesktop();
//					java.net.URL aboutURL = About.class.getResource("resources.html");
//					desktopBrowser.browse(aboutURL.toURI());
					/* End of code with links which only work in Eclipse, not a 
					 * runnable jar file.
					 */
					
					/* The following is from:
					 * https://www.appsloveworld.com/eclipse/100/152/html-file-not-opening-in-executable-jar
					 * Also works in Eclipse but not runnable jar file. 
					 */
//					File resFile = new File(About.class.getResource("resources.html").toURI());
//					File htmlFile = new File("resources.html");
//					if(!htmlFile.exists()) {
//					    Files.copy(resFile.toPath(), htmlFile.toPath());
//					}
//					Desktop.getDesktop().browse(htmlFile.toURI());
					/* End of code that does not work in runnable jar.*/
					
					/* FOLLOWING CODE WILL OPEN "about.html" IN A BROWSER
					 * FROM A RUNNABLE JAR.
					 */
					
					/* Here is code that WILL open the about.html browser with 
					 * active links. The key is the resources files etc are "zipped" 
					 * into the runnable jar file. For the computer's operating system
					 * to access them with Desktop methods they must be opened as 
					 * input streams that write to files used by the Desktop methods.
					 * */
					
					/* 1. Open resource files embedded in runnable jar as InputStreams. */
					
			        InputStream aboutFileIS = About.class.getResourceAsStream("/resources/about.html");
			        InputStream cssIS = About.class.getResourceAsStream("/resources/mystyle.css");
			        
			        /* 2. Create files in c:\temp folder to be filled with the content of 
			         * the InputStreams. NOTE the "\\" for setting path and filename in the 
			         * Windows style. The first "\" is a necessary escape character.
			         */
			        File aboutTMP = new File("c:\\temp\\about.html");
			        File cssTMP = new File("c:\\temp\\mystyle.css");
			        
			        try {
			        	/* 3. Copy Inputstreams to files.
			        	 * NOTE: FileUtil is from an external archive and must be 
			        	 * added by: Right click on Project --> Build Path -->
			        	 * Add External Archives --> C:\Program Files\commons-io-2.13.0 
			        	 * --> add the jars to the project. 
			        	 * */
						FileUtils.copyInputStreamToFile(aboutFileIS, aboutTMP);
						FileUtils.copyInputStreamToFile(cssIS, cssTMP);
						/* 4. Open files with system's default browser. 
						 * In this case the browser opens the HTML file,
						 * which in turn references the CSS file. */
						Desktop.getDesktop().open(aboutTMP);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*END OF CODE BLOCK THAT OPENS "about.html" IN BROWSER. */
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public About() {
		setTitle("About Change Blindness Program");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(295, 316, 89, 23);
		contentPane.add(btnNewButton);
		
		JEditorPane editorPaneHTML = createEditorPane();
		JScrollPane scrollPane = new JScrollPane(editorPaneHTML);
		scrollPane.setBounds(10, 11, 653, 294);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);	       

	}
	
	/* Create JEditorPane and load with resources.html file. */
    private JEditorPane createEditorPane() {
    	
    	/* The following loaded "about.html" in the runnable jar, but the links
    	 * did not work. 
    	 * */
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL aboutURL = About.class.getResource("/resources/about.html");
        if (aboutURL != null) {
            try {
                editorPane.setPage(aboutURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + aboutURL);
            }
        } 
    	/* End of code block. */
 	
        return editorPane;
    }
}
