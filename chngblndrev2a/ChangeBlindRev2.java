package chngblndrev2a;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeBlindRev2 {

	private JFrame frmChngBlndRevTwo;
	private JTextField textFieldTimer;
	private JTextField textFieldIncorrectClicks;
	
	// Instantiate ChangeBlindnessMethods
	ChangeBlindnessMethods chngBlndMthd = new ChangeBlindnessMethods();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeBlindRev2 window = new ChangeBlindRev2();
					window.frmChngBlndRevTwo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangeBlindRev2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChngBlndRevTwo = new JFrame();
		frmChngBlndRevTwo.setTitle("Change Blindness Rev. 2");
		frmChngBlndRevTwo.setBounds(100, 100, 1100, 723);// Original values: (100, 100, 871, 723)
		frmChngBlndRevTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChngBlndRevTwo.getContentPane().setLayout(new MigLayout("", "[140px][4px][650px, grow]", "[490.00px][12.00px][135.00px,grow][]"));
		
		JPanel panel = new JPanel();
		frmChngBlndRevTwo.getContentPane().add(panel, "cell 0 0,width 130!");
		panel.setLayout(new MigLayout("", "[]", "[][][][][][][][][][][][][]"));
		
		JLabel lblSpacer_1 = new JLabel("");
		panel.add(lblSpacer_1, "cell 0 0, height 50!");
		
		// Mask Time Group - Start
		JLabel lblMaskTime = new JLabel("Mask Time");
		panel.add(lblMaskTime, "cell 0 1");
		
		JComboBox<String> comboBoxMaskTime = new JComboBox<String>();
		comboBoxMaskTime.setModel(new DefaultComboBoxModel<String>(new String[] {"500ms", "250ms", "100ms", "50ms", "0ms"}));
		panel.add(comboBoxMaskTime, "cell 0 2,width 90!");
		
		JButton btnNewButton = new JButton("SET");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String maskTimeStr = (String) comboBoxMaskTime.getSelectedItem();
				chngBlndMthd.setMaskTime(maskTimeStr);
			}
		});
		panel.add(btnNewButton, "cell 0 3");
		// Mask Time Group - End
		
		// Alter Speed Group - Start
		JLabel lblAlterSpeed = new JLabel("Alteration Speed");
		panel.add(lblAlterSpeed, "cell 0 4");
		
		JComboBox<String> comboBoxAlterSpeed = new JComboBox<String>();
		comboBoxAlterSpeed.setModel(new DefaultComboBoxModel<String>(new String[] {"2s", "1s", "500ms", "250ms"}));
		panel.add(comboBoxAlterSpeed, "cell 0 5,width 90!");
		
		JButton btnAlterSpeed = new JButton("SET");
		btnAlterSpeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String alterSpeedStr = (String) comboBoxAlterSpeed.getSelectedItem();
				chngBlndMthd.setAlterSpeed(alterSpeedStr);
			}
		});
		panel.add(btnAlterSpeed, "cell 0 6");
		// Alter Speed Group - End
		
		JLabel lblSpacer_2 = new JLabel("");
		panel.add(lblSpacer_2, "cell 0 7, height 20!");

		// Trial Timer - Start
		JLabel lblTrialTimer = new JLabel("Trial Timer");
		panel.add(lblTrialTimer, "cell 0 8");
		
		textFieldTimer = new JTextField();
		textFieldTimer.setText("0:0:0:000");
		panel.add(textFieldTimer, "cell 0 9,width 90!");
		// Set as output for timer when running.
		chngBlndMthd.setTimerOutput(textFieldTimer);
		// Trial Timer - End
		
		// Incorrect Click Count - Start		
		JLabel lblIncorrectClicks = new JLabel("Incorrect Clicks");
		panel.add(lblIncorrectClicks, "cell 0 10");
		
		textFieldIncorrectClicks = new JTextField();
		textFieldIncorrectClicks.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldIncorrectClicks.setText("0");
		panel.add(textFieldIncorrectClicks, "cell 0 11,width 90!");
		textFieldIncorrectClicks.setColumns(10);
		// Set as output for incorrect click count.
		chngBlndMthd.setInncorrectClickOutput(textFieldIncorrectClicks);
		// Incorrect Click Count - End
			
		JTabbedPane tabbedPaneImg = new JTabbedPane(JTabbedPane.TOP);
		frmChngBlndRevTwo.getContentPane().add(tabbedPaneImg, "cell 2 0, grow");
		
		// Image set 1 Panel.
		JPanel panelImgSet1 = new JPanel();
		tabbedPaneImg.addTab("Image Set 1", null, panelImgSet1, null);
		panelImgSet1.setLayout(null);
		
		JLabel lblImgSet1Target = new JLabel("");
		lblImgSet1Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet1Target.setBounds(228, 296, 131, 27);
		panelImgSet1.add(lblImgSet1Target);
	
		JLabel lblImgSet1 = new JLabel("");
		lblImgSet1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet1.setBounds(0, 0, 700, 456);
		lblImgSet1.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_1/trunkPresent.png")));
		lblImgSet1.setHorizontalAlignment(SwingConstants.CENTER);
		panelImgSet1.add(lblImgSet1);
		// End of Image Set 1 panel.
		
		// Image set 2 Panel.
		JPanel panelImgSet2 = new JPanel();
		tabbedPaneImg.addTab("Image Set 2", null, panelImgSet2, null);
		panelImgSet2.setLayout(null);
		
		JLabel lblImgSet2Target = new JLabel("");
		lblImgSet2Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet2Target.setBounds(250, 190, 27, 163);
		panelImgSet2.add(lblImgSet2Target);
		
		JLabel lblImgSet2 = new JLabel("");
		lblImgSet2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet2.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet2.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_2/postWood.jpg")));
		lblImgSet2.setBounds(0, 0, 700, 456);
		panelImgSet2.add(lblImgSet2);		
		//End of Image Set 2 panel.
		
		// Image set 3 Panel.
		JPanel panelImgSet3 = new JPanel();
		tabbedPaneImg.addTab("Image Set 3", null, panelImgSet3, null);
		panelImgSet3.setLayout(null);
		
		JLabel lblImgSet3Target = new JLabel("");
		lblImgSet3Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet3Target.setBounds(448, 24, 46, 42);
		panelImgSet3.add(lblImgSet3Target);
		
		JLabel lblImgSet3 = new JLabel("");
		lblImgSet3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet3.setBounds(0, 0, 700, 456);
		lblImgSet3.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_3/moonPhaseB2.png")));
		lblImgSet3.setHorizontalAlignment(SwingConstants.CENTER);
		panelImgSet3.add(lblImgSet3);		
		
		JPanel panelImgSet4 = new JPanel();
		tabbedPaneImg.addTab("Image Set 4", null, panelImgSet4, null);
		panelImgSet4.setLayout(null);
		
		JLabel lblImgSet4Target1 = new JLabel("");
		lblImgSet4Target1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet4Target1.setBounds(163, 72, 16, 248);
		panelImgSet4.add(lblImgSet4Target1);
		
		JLabel lblImgSet4Target2 = new JLabel("");
		lblImgSet4Target2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet4Target2.setBounds(508, 93, 16, 248);
		panelImgSet4.add(lblImgSet4Target2);
		
		JLabel lblImgSet4 = new JLabel("");
		lblImgSet4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet4.setBounds(10, 5, 690, 440);
		lblImgSet4.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_4/bolts.png")));
		lblImgSet4.setHorizontalAlignment(SwingConstants.CENTER);
		panelImgSet4.add(lblImgSet4);
		
		JPanel panelImgSet5 = new JPanel();
		tabbedPaneImg.addTab("Image Set 5", null, panelImgSet5, null);
		panelImgSet5.setLayout(null);
		
		JLabel lblImgSet5Target = new JLabel("");
		lblImgSet5Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet5Target.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet5Target.setBounds(294, 287, 344, 39);
		panelImgSet5.add(lblImgSet5Target);
		
		JLabel lblImgSet5 = new JLabel("");
		lblImgSet5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet5.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet5.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_5/fewerOranges.png")));
		lblImgSet5.setBounds(0, 0, 700, 445);
		panelImgSet5.add(lblImgSet5);
		
		JPanel panelImgSet6 = new JPanel();
		tabbedPaneImg.addTab("Image Set 6", null, panelImgSet6, null);
		panelImgSet6.setLayout(null);
		
		JLabel lblImgSet6Target = new JLabel("");
		lblImgSet6Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet6Target.setBounds(277, 104, 46, 14);
		panelImgSet6.add(lblImgSet6Target);
		
		JLabel lblImgSet6 = new JLabel("");
		lblImgSet6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet6.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet6.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_6/antennaRight.png")));
		lblImgSet6.setBounds(0, 0, 690, 445);
		panelImgSet6.add(lblImgSet6);
		
		JPanel panelImgSet7 = new JPanel();
		tabbedPaneImg.addTab("Image Set 7", null, panelImgSet7, null);
		panelImgSet7.setLayout(null);
		
		JLabel lblImgSet7Target_1 = new JLabel("");
		lblImgSet7Target_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet7Target_1.setBounds(452, 263, 81, 33);
		panelImgSet7.add(lblImgSet7Target_1);
		
		JLabel lblImgSet7Target_2 = new JLabel("");
		lblImgSet7Target_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet7Target_2.setBounds(547, 299, 28, 79);
		panelImgSet7.add(lblImgSet7Target_2);
		
		JLabel lblImgSet7Target_3 = new JLabel("");
		lblImgSet7Target_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet7Target_3.setBounds(475, 377, 81, 50);
		panelImgSet7.add(lblImgSet7Target_3);
		
		JLabel lblImgSet7 = new JLabel("");
		lblImgSet7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet7.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet7.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_7/yellowFlower.png")));
		lblImgSet7.setBounds(0, 0, 700, 456);
		panelImgSet7.add(lblImgSet7);
		
		JPanel panelImgSet8 = new JPanel();
		tabbedPaneImg.addTab("Image Set 8", null, panelImgSet8, null);
		panelImgSet8.setLayout(null);
		
		JLabel lblImgSet8Target_1 = new JLabel("");
		lblImgSet8Target_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet8Target_1.setBounds(49, 178, 25, 26);
		panelImgSet8.add(lblImgSet8Target_1);
		
		JLabel lblmgSet8Target_2 = new JLabel("");
		lblmgSet8Target_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblmgSet8Target_2.setBounds(221, 0, 207, 72);
		panelImgSet8.add(lblmgSet8Target_2);
		
		JLabel lblImgSet8 = new JLabel("");
		lblImgSet8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet8.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet8.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_8/people.png")));
		lblImgSet8.setBounds(0, 0, 700, 456);
		panelImgSet8.add(lblImgSet8);
		
		JPanel panelImgSet9 = new JPanel();
		tabbedPaneImg.addTab("Image Set 9", null, panelImgSet9, null);
		panelImgSet9.setLayout(null);
		
		JLabel lblImgSet9Target = new JLabel("");
		lblImgSet9Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet9Target.setBounds(387, 169, 55, 44);
		panelImgSet9.add(lblImgSet9Target);
		
		JLabel lblImgSet9 = new JLabel("");
		lblImgSet9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet9.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet9.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_9/engine.png")));
		lblImgSet9.setBounds(0, 0, 700, 456);
		panelImgSet9.add(lblImgSet9);
		
		JPanel panelImgSet10 = new JPanel();
		tabbedPaneImg.addTab("Image Set 10", null, panelImgSet10, null);
		panelImgSet10.setLayout(null);
		
		JLabel lblImgSet10Target = new JLabel("");
		lblImgSet10Target.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				chngBlndMthd.changeFound();
			}
		});
		lblImgSet10Target.setBounds(203, 98, 73, 69);
		panelImgSet10.add(lblImgSet10Target);
		
		JLabel lblImgSet10 = new JLabel("");
		lblImgSet10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.incrementIncorrect();
			}
		});
		lblImgSet10.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSet10.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_10/statue_NoTrees.png")));
		lblImgSet10.setBounds(0, 0, 700, 456);
		panelImgSet10.add(lblImgSet10);
		//End of Image Set 3 panel.
		
		JLabel lblNewLabel = new JLabel("Click on images to start or stop a trial");
		frmChngBlndRevTwo.getContentPane().add(lblNewLabel, "cell 0 1 3 1,alignx left,aligny top");
		
		// Image set selection labels.
		JScrollPane scrollPaneSelImg = new JScrollPane();
		frmChngBlndRevTwo.getContentPane().add(scrollPaneSelImg, "cell 0 2,span,grow");
		
		JPanel panelImgSets = new JPanel();
		scrollPaneSelImg.setViewportView(panelImgSets);
		panelImgSets.setLayout(new MigLayout("", "[][][][][][][][][][]", "[]"));
		
		JLabel lblImgSet_1 = new JLabel("");
		lblImgSet_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet1, lblImgSet1, "Image Set 1", "/imgset_1/mask.jpg", "/imgset_1/trunkPresent.png", "/imgset_1/trunkGone.png", "setOneThread");
			}
		});
		lblImgSet_1.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_1/trunkPresentLbl.png")));
		panelImgSets.add(lblImgSet_1, "cell 0 0");
		
		JLabel lblImgSet_2 = new JLabel("");
		lblImgSet_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet2, lblImgSet2, "Image Set 2", "/imgset_2/postMask.jpg", "/imgset_2/postWood.jpg", "/imgset_2/postMetal.jpg", "setTwoThread");
			}
		});
		lblImgSet_2.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_2/postWoodLbl.jpg")));
		panelImgSets.add(lblImgSet_2, "cell 1 0");
		
		JLabel lblImgSet_3 = new JLabel("");
		lblImgSet_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet3, lblImgSet3, "Image Set 3", "/imgset_3/maskMoon.png", "/imgset_3/moonPhaseA2.png", "/imgset_3/moonPhaseB2.png", "setThreeThread");
			}
		});
		lblImgSet_3.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_3/moonPhaseA2Lbl.png")));
		panelImgSets.add(lblImgSet_3, "cell 2 0");
		
		JLabel lblImgSet_4 = new JLabel("");
		lblImgSet_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet4, lblImgSet4, "Image Set 4", "/imgset_4/mask581x436.png", "/imgset_4/bolts.png", "/imgset_4/no_bolts.png", "setFourThread");
			}
		});
		lblImgSet_4.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_4/boltsLbl.png")));
		panelImgSets.add(lblImgSet_4, "cell 3 0");
		
		JLabel lblImgSet_5 = new JLabel("");
		lblImgSet_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet5, lblImgSet5, "Image Set 5", "/imgset_5/mask581x436.png", "/imgset_5/fewerOranges.png", "/imgset_5/moreOranges.png", "setFiveThread");
			}
		});
		lblImgSet_5.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_5/fewerOrangesLbl.png")));
		panelImgSets.add(lblImgSet_5, "cell 4 0");
		
		JLabel lblImgSet_6 = new JLabel("");
		lblImgSet_6.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_6/antennaRightLbl.png")));
		lblImgSet_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet6, lblImgSet6, "Image Set 6", "/imgset_6/mask600x370.png", "/imgset_6/antennaRight.png", "/imgset_6/antennaLeft.png", "setSixThread");
			}
		});
		panelImgSets.add(lblImgSet_6, "cell 5 0");
		
		JLabel lblImgSet_7 = new JLabel("");
		lblImgSet_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet7, lblImgSet7, "Image Set 7", "/imgset_7/mask600x370.png", "/imgset_7/yellowFlower.png", "/imgset_7/greenFlower.png", "setSevenThread");
			}
		});
		lblImgSet_7.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_7/yellowFlowerLbl.png")));
		panelImgSets.add(lblImgSet_7, "cell 6 0");
		
		JLabel lblImgSet_8 = new JLabel("");
		lblImgSet_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet8, lblImgSet8, "Image Set 8", "/imgset_8/mask600x450.png", "/imgset_8/noPeople.png", "/imgset_8/people.png", "setEightThread");
			}
		});
		lblImgSet_8.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_8/noPeopleLbl.png")));
		panelImgSets.add(lblImgSet_8, "cell 7 0");
		
		JLabel lblImgSet_9 = new JLabel("");
		lblImgSet_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet9, lblImgSet9, "Image Set 9", "/imgset_9/mask600x420.png", "/imgset_9/engine.png", "/imgset_9/noEngine.png", "setNineThread");
			}
		});
		lblImgSet_9.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_9/engineLbl.png")));
		panelImgSets.add(lblImgSet_9, "cell 8 0");
		
		JLabel lblImgSet_10 = new JLabel("");
		lblImgSet_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chngBlndMthd.selectImgSetStartStopTrial(tabbedPaneImg, panelImgSet10, lblImgSet10, "Image Set 10", "/imgset_10/mask.png", "/imgset_10/statue_NoTrees.png", "/imgset_10/statue_Trees.png", "setTenThread");
			}
		});
		lblImgSet_10.setIcon(new ImageIcon(ChangeBlindRev2.class.getResource("/imgset_10/statue_NoTreesLbl.png")));
		panelImgSets.add(lblImgSet_10, "cell 9 0");
		
		JMenuBar menuBar = new JMenuBar();
		frmChngBlndRevTwo.setJMenuBar(menuBar);
		
		JMenu mnMainMenu = new JMenu("Menu");
		menuBar.add(mnMainMenu);
		
		JMenuItem mntmMenuItemLog = new JMenuItem("Create Log File");
		mntmMenuItemLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// Create CSV log in directory of users choice.
				//System.out.println(chngBlndMthd.getTrialLog());
				chngBlndMthd.setTrialLog(chngBlndMthd.createLogFile());				
				//System.out.println(chngBlndMthd.getTrialLog());
			}
		});
		
		JMenuItem mntmNewMenuItemInstructions = new JMenuItem("Instructions");
		mntmNewMenuItemInstructions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Instructions.main(null);
			}
		});
		mnMainMenu.add(mntmNewMenuItemInstructions);
		mnMainMenu.add(mntmMenuItemLog);
		
		JMenuItem mntmMenuItemClose = new JMenuItem("Close");
		mntmMenuItemClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println(chngBlndMthd.getTrialTimerRunning());
				if(chngBlndMthd.getTrialTimerRunning()){
					chngBlndMthd.setTrialTimerRunning(false);
				}
				// Following will return false if trail is NOT running.
				//System.out.println(chngBlndMthd.getTrialTimerRunning());
				frmChngBlndRevTwo.dispose();
			}
		});
		
		JMenuItem mntmNewMenuItemAbout = new JMenuItem("About Change Blindness Program");
		mntmNewMenuItemAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				About.main(null);
			}
		});
		mnMainMenu.add(mntmNewMenuItemAbout);
		mnMainMenu.add(mntmMenuItemClose);
	}
}
