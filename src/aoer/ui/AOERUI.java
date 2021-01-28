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
	JCheckBox repeatBox, baseGameBox, forgottenBox, africanBox, rajaBox, definitiveBox, westernBox;
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
		
		dlcBoxList = new JCheckBox[DLC.values().length];
		
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
		
		definitiveBox = new JCheckBox("Definitive Ed. Civs");
		definitiveBox.setSelected(control.hasAllowedDLC(DLC.DEFINITIVE));
		dlcBoxList[4] = definitiveBox;
		
		westernBox = new JCheckBox("Lords of the West");
		westernBox.setSelected(control.hasAllowedDLC(DLC.WESTERNLORDS));
		dlcBoxList[5] = westernBox;
		
		ActionListener dlcCheckBoxListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DLC[] enabledDLC = new DLC[DLC.values().length];
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
				if(dlcBoxList[4].isSelected()) {
					enabledDLC[4] = DLC.DEFINITIVE;
					numEnabled++;
				}
				if(dlcBoxList[5].isSelected()) {
					enabledDLC[5] = DLC.WESTERNLORDS;
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
		buttonPanel.setLayout(new GridLayout(3,1,0,10));
		
		leftPanel.add(dlcMenuPanel, BorderLayout.CENTER);
		banButton = new JButton("Ban Civs");
		banButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openBanDialog();
			}
			
		});
		banButton.setEnabled(false);
		buttonPanel.add(banButton);
		
		randomizeButton = new JButton("Randomize Civs");
		randomizeButton.setEnabled(true);
		randomizeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.randomizeCivs();
				Player[] pList = control.getPlayerList();
				String output = "";
				for(Player p : pList) {
					output += p.getName() + ": " + p.getAssignedCiv().getName() + "\n";
				}
				outputArea.setText(output);
			}
			
		});
		buttonPanel.add(randomizeButton);
		
		repeatBox = new JCheckBox("Allow Repeats");
		repeatBox.setSelected(true);
		repeatBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.allowRepeats(repeatBox.isSelected());
			}
			
		});
		buttonPanel.add(repeatBox);
		
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setFont(outputArea.getFont().deriveFont(22f));
		
		mainPanel.add(outputArea, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	
	private void openPlayerDialog() {
		JDialog dia = new JDialog(this, "Player Entry");
		dia.setSize(new Dimension(400,400));
		dia.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

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
		
		JButton submitButton = new JButton("Submit Names");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] names = new String[playerFields.length];
				boolean allowContinue = true;
				for(int i = 0; i < names.length; i++) {
					names[i] = playerFields[i].getText();
					if(names[i].equals(""))
						allowContinue = false;
				}
				String output = "";
				for(int i = 0; i < names.length-1; i++) {
					output += (names[i] + "\n");
				}
				output += names[names.length-1];
				
				if(allowContinue) {
					outputArea.setText(output);
					control.setPlayerList(names);
					banButton.setEnabled(true);
					dia.dispose();
				}
			}
			
		});
		
		dia.getContentPane().add(submitButton, BorderLayout.SOUTH);
		dia.revalidate();
		dia.repaint();
	}
	
	private void openBanDialog() {
		JDialog banDialog = new JDialog(this, "Ban Phase");
		banDialog.setSize(new Dimension(300,700));
		Civs[] allowedCivs = control.getAllowedCivs();
		banDialog.getContentPane().setLayout(new GridLayout(allowedCivs.length,1));
		JCheckBox[] civBoxes = new JCheckBox[allowedCivs.length];
		
		ActionListener banListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox)e.getSource();
				String output = (cb.isSelected()) ? "Enabled " : "Disabled ";  //debug info
				output += cb.getText();
				System.out.println(output);
				int index = 0;
				for(int i = 0; i < civBoxes.length; i++) {
					if(cb.equals(civBoxes[i])) {
						index = i;
						break;
					}
				}
				if(cb.isSelected()) {
					control.unbanCiv(allowedCivs[index]);
				}
				else {
					control.banCiv(allowedCivs[index]);
				}
			}
			
		};
		
		for(int i = 0; i < civBoxes.length; i++) {
			civBoxes[i] = new JCheckBox(allowedCivs[i].getName());
			civBoxes[i].setSelected(control.hasAllowedCiv(allowedCivs[i]));
			civBoxes[i].addActionListener(banListener);
			banDialog.getContentPane().add(civBoxes[i]);
		}
		banDialog.setVisible(true);
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
