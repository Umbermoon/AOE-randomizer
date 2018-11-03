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
	
	JPanel mainPanel, leftPanel, dlcMenuPanel;
	JButton banButton, randomizeButton;
	JCheckBox repeatBox, baseGameBox, forgottenBox, africanBox, rajaBox;
	JCheckBox[] dlcBoxList;
	JTextArea outputArea;
	
	public AOERUI() {
		control = new Core();
		initUI();
	}
	
	private void initUI() {
		this.setMinimumSize(new Dimension(800,600));
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
		
		leftPanel.add(dlcMenuPanel, BorderLayout.CENTER);
		banButton = new JButton("Ban\nCivs");
		
		
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		
	
		this.setContentPane(mainPanel);
		this.setVisible(true);
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
