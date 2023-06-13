package chngblndrev2a;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class Instructions extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6901156548418717106L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructions frame = new Instructions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Instructions() {
		setTitle("Instructions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 730, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(328, 366, 78, 35);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
					
		JEditorPane editorPaneInstructions = createEditorPane();
		JScrollPane scrollPaneInstructions = new JScrollPane(editorPaneInstructions);
		scrollPaneInstructions.setBounds(10, 11, 694, 344);
		contentPane.add(scrollPaneInstructions);		

		contentPane.add(btnClose);
	}
	
    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL instructURL = About.class.getResource("/resources/instructions.html");
        if (instructURL != null) {
            try {
                editorPane.setPage(instructURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + instructURL);
            }
        } else {
            System.err.println("Couldn't find file: /resources/instructions.html");
        }
        
        return editorPane;
    }
}
