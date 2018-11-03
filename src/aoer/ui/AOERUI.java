package aoer.ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aoer.information.*;
import aoer.backend.*;

@SuppressWarnings("serial")
public class AOERUI extends JFrame{

	Core control;
	
	JPanel mainPanel, leftPanel, dlcMenuPanel, buttonPanel;
	JButton banButton, randomizeButton;
	JCheckBox repeatBox, baseGameBox, forgottenBox, africanBox, rajaBox;
	JCheckBox[] dlcBoxList;
	JTextArea outputArea;
	
	public AOERUI() {
		control = new Core();
		initUI();
		openPlayerDialog();
	}
	
	private void initUI() {
		this.setMinimumSize(new Dimension(800,600));
		this.setTitle("AOE Faction Randomizer");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		dlcMenuPanel = new JPanel();
		dlcMenuPanel.setLayout(new GridLayout(4,1,0,10));
		
		dlcBoxList = new JCheckBox[4];
		
		baseGameBox = new JCheckBox("Base Game");
		baseGameBox.setSelected(control.hasAllowedDLC(DLC.BASE));
		dlcBoxList[0] = baseGameBox;
		
		forgottenBox = new JCheckBox("The Forgotten");
		forgottenBox.setSelected(control.hasAllowedDLC(DLC.FORGOTTEN));
		dlcBoxList[1] = forgottenBox;
		
		africanBox = new JCheckBox("The African Kingdoms");
		africanBox.setSelected(control.hasAllowedDLC(DLC.AFRICANS));
		dlcBoxList[2] = africanBox;
		
		rajaBox = new JCheckBox("Rise of the Rajas");
		rajaBox.setSelected(control.hasAllowedDLC(DLC.RAJAS));
		dlcBoxList[3] = rajaBox;
		
		ActionListener dlcCheckBoxListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DLC[] enabledDLC = new DLC[4];
				int numEnabled = 0;
				if(dlcBoxList[0].isSelected()) {
					enabledDLC[0] = DLC.BASE;
					numEnabled++;
				}
				if(dlcBoxList[1].isSelected()) {
					enabledDLC[1] = DLC.FORGOTTEN;
					numEnabled++;
				}
				if(dlcBoxList[2].isSelected()) {
					enabledDLC[2] = DLC.AFRICANS;
					numEnabled++;
				}
				if(dlcBoxList[3].isSelected()) {
					enabledDLC[3] = DLC.RAJAS;
					numEnabled++;
				}
				
				int numPlaced = 0;
				DLC[] newEnabled = new DLC[numEnabled];
				for(int i = 0; i < enabledDLC.length; i++) {
					if(enabledDLC[i] != null) {
						newEnabled[numPlaced] = enabledDLC[i];
						numPlaced++;
					}
				}
				
				control.setAllowedDLC(newEnabled);
				
			}
			
		};
		
		for(int i = 0; i < dlcBoxList.length; i++) {
			dlcBoxList[i].addActionListener(dlcCheckBoxListener);
			dlcMenuPanel.add(dlcBoxList[i]);
		}
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1,0,10));
		
		leftPanel.add(dlcMenuPanel, BorderLayout.CENTER);
		banButton = new JButton("Ban\nCivs");
		buttonPanel.add(banButton);
		
		randomizeButton = new JButton("Randomize\nCivs");
		buttonPanel.add(randomizeButton);
		
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		
		mainPanel.add(outputArea, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	
	private void openPlayerDialog() {
		JDialog dia = new JDialog(this, "");
		dia.setSize(new Dimension(400,400));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		JPanel blankPanel = new JPanel();
		
		dia.getContentPane().setLayout(new BorderLayout());
		
		JTextField numberEntry;
		JLabel numberLabel;
		
		numberEntry = new JTextField();
		numberLabel = new JLabel("Enter number of players");
		
		numberEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Updating Dialog");
					String text = numberEntry.getText();
					int number = Integer.parseInt(text);
					if(number > 8)
						number = 8;
					else if (number < 1)
						number = 1;
					updatePlayerDialog(dia, topPanel, number);
				}
				catch(NullPointerException npe) {
					//do nothing
				}
				catch(NumberFormatException nfe) {
					//do nothing
				}
			}
		
		});
		
		topPanel.add(numberLabel);
		topPanel.add(numberEntry);
		
		dia.getContentPane().add(topPanel, BorderLayout.NORTH);
		dia.getContentPane().add(blankPanel);
		dia.setVisible(true);
		
	}
	
	private void updatePlayerDialog(JDialog dia, JPanel topPanel, int num) {
		dia.getContentPane().removeAll();
		dia.getContentPane().add(topPanel, BorderLayout.NORTH);
		JLabel[] playerLabels = new JLabel[num];
		JTextField[] playerFields = new JTextField[num];
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(8,2,1,8));
		
		for(int i = 0; i < num; i++) {
			playerLabels[i] = new JLabel("Player " + (i + 1));
			playerFields[i] = new JTextField();
			playerPanel.add(playerLabels[i]);
			playerPanel.add(playerFields[i]);
		}
		
		dia.getContentPane().add(playerPanel, BorderLayout.CENTER);
		dia.revalidate();
		dia.repaint();
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AOERUI();
			}
			
		});
	}
}
