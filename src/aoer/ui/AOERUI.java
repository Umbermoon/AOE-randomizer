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
	
	JPanel mainPanel, leftPanel;
	JButton dlcButton, banButton, randomizeButton;
	JCheckBox repeatBox;
	JTextArea outputArea;
	
	public AOERUI() {
		initUI();
	}
	
	private void initUI() {
		control = new Core();
		this.setMinimumSize(new Dimension(800,600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		dlcButton = new JButton("Select\nDLCs");
		
		
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
