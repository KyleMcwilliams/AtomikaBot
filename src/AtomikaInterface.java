import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import org.jibble.pircbot.*;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JPasswordField;
public class AtomikaInterface extends PircBot{

	// Static Variables
	public static JFrame frmAtomikabot;
	public static AtomikaBot bot;
	public static JLabel UniqueCLabel = new JLabel();
	private JTextField ChannelText;
	public static boolean isConnected = false;
	public static boolean commandsToggle = true;
	private JTextField textFieldChat;
	public static JTextField userPassword;
	public static JTextField userNameText;
	public static JPanel Connection;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				// TAB COLOR SETTINGS
				   UIManager.put("TabbedPane.contentAreaColor ",ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.selected",ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.background",ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.shadow",ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.borderColor", Color.BLACK);
			       UIManager.put("TabbedPane.darkShadow", ColorUIResource.BLACK);
			       UIManager.put("TabbedPane.light", ColorUIResource.BLACK);
			       UIManager.put("TabbedPane.highlight", ColorUIResource.BLACK);
			       UIManager.put("TabbedPane.focus", ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.unselectedBackground", ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.selectHighlight", ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.tabAreaBackground", ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.borderHightlightColor", ColorUIResource.DARK_GRAY);
			       UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
			       
			       //BUTTON COLOR SETTINGS
			       UIManager.put("Button.border", ColorUIResource.BLACK);
			       UIManager.put("Button.select", ColorUIResource.WHITE);
			       UIManager.put("Button.focus", new Color(0, 0, 0, 0));
			       
			       //TEXT FIELD SETTINGS
			       UIManager.put("TextField.border", ColorUIResource.BLACK);
			       UIManager.put("TextField.background", ColorUIResource.DARK_GRAY);
			       UIManager.put("TextField.foreground", ColorUIResource.WHITE);
			       
			       
			       
			       
				try {
					AtomikaInterface window = new AtomikaInterface();
					window.frmAtomikabot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
// * Create the application.
	public AtomikaInterface() {
		initialize();
		bot = new AtomikaBot(frmAtomikabot);
	}
	
	 //\_____________________/\
	// [FRAME] All the stuff! \\
   //\________________________/\\
	
	private void initialize() {
		frmAtomikabot = new JFrame();
			frmAtomikabot.setResizable(false);
			frmAtomikabot.getContentPane().setFont(new Font("Open Sans", Font.BOLD, 12));
			frmAtomikabot.setTitle("AtomikaBOT");
			frmAtomikabot.getContentPane().setBackground(SystemColor.desktop);
			frmAtomikabot.setBounds(100, 100, 384, 542);
			frmAtomikabot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmAtomikabot.getContentPane().setLayout(null);
			
			// [IMAGE] Logo
			JLabel Logo = DefaultComponentFactory.getInstance().createLabel("");
				Image img = new ImageIcon(this.getClass().getResource("/logo4.png")).getImage();
				Logo.setIcon(new ImageIcon(img));
				Logo.setBounds(0, 0, 378, 74);
				frmAtomikabot.getContentPane().add(Logo);
			
			// [TABBED PANE] The Tabs
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBorder(null);
				tabbedPane.setFont(new Font("Open Sans", Font.BOLD, 14));
				tabbedPane.setBackground(SystemColor.desktop);
				tabbedPane.setBounds(0, 73, 378, 394);
				frmAtomikabot.getContentPane().add(tabbedPane);
			
			// [PANEL] Main
			JPanel Main = new JPanel();
				Main.setBorder(null);
				Main.setForeground(SystemColor.desktop);
				Main.setBackground(Color.DARK_GRAY);
				tabbedPane.addTab("Main", null, Main, null);
				Main.setLayout(null);
				Main.setLayout(null);
			
			// [LABEL] Unique Chatters 
				UniqueCLabel.setHorizontalAlignment(SwingConstants.LEFT);
				UniqueCLabel.setForeground(Color.WHITE);
				UniqueCLabel.setText(bot.chatterCount + "");
				UniqueCLabel.setFont(new Font("Open Sans", Font.BOLD, 18));
				UniqueCLabel.setBounds(31, 11, 81, 35);
				Main.add(UniqueCLabel);
			
			// [IMAGE] Unique Chatters Image
			JLabel ChatterIcon = DefaultComponentFactory.getInstance().createLabel("");
				Image chatimg = new ImageIcon(this.getClass().getResource("/Chat Icon.png")).getImage();
				ChatterIcon.setIcon(new ImageIcon(chatimg));
				ChatterIcon.setBounds(10, 11, 23, 35);
				Main.add(ChatterIcon);
				
			// [BUTTON] Commands Toggle
			JButton btnCommandsOn = new JButton("Commands: On");
				btnCommandsOn.setFont(new Font("Open Sans", Font.BOLD, 12));
				btnCommandsOn.setBounds(255, 15, 107, 23);
				Main.add(btnCommandsOn);
				btnCommandsOn.setBackground(ColorUIResource.DARK_GRAY);
				btnCommandsOn.setForeground(new Color(51, 153, 0));
				
				JButton btnNextScene = new JButton("Next Scene");
				btnNextScene.setEnabled(false);
				btnNextScene.setForeground(Color.WHITE);
				btnNextScene.setFont(new Font("Open Sans", Font.BOLD, 12));
				btnNextScene.setBackground(Color.DARK_GRAY);
				btnNextScene.setBounds(255, 49, 107, 23);
				Main.add(btnNextScene);
				tabbedPane.setBackgroundAt(0, Color.BLACK);
				tabbedPane.setForegroundAt(0, SystemColor.controlHighlight);
		
			// [PANEL] Extras
			JPanel Extras = new JPanel();
				Extras.setBackground(Color.DARK_GRAY);
				Extras.setBorder(null);
				tabbedPane.addTab("Extras", null, Extras, null);
				Extras.setLayout(null);
				
			// [TEXT FIELD] bot > Chat
			textFieldChat = new JTextField();
			textFieldChat.setFont(new Font("Open Sans", Font.PLAIN, 12));
				textFieldChat.setBounds(10, 326, 269, 23);
				Extras.add(textFieldChat);
				textFieldChat.setColumns(10);
				textFieldChat.setBackground(ColorUIResource.GRAY);


			// SEND BUTTON *****
			JButton btnSend = new JButton("Send");
			btnSend.setFont(new Font("Open Sans", Font.PLAIN, 12));
				btnSend.setBounds(289, 326, 79, 23);
				Extras.add(btnSend);
				btnSend.setBackground(ColorUIResource.GRAY);
				tabbedPane.setBackgroundAt(1, Color.BLACK);
				tabbedPane.setForegroundAt(1, SystemColor.controlHighlight);
				frmAtomikabot.getRootPane().setDefaultButton(btnSend);
			//------------------------------------
			
			
			// [PANEL] Connections
				Connection = new JPanel();
				Connection.setBackground(Color.DARK_GRAY);
				tabbedPane.addTab("Connection", null, Connection, null);
				Connection.setLayout(null);
				Connection.setLayout(null);
				
				userPassword = new JTextField();
				userPassword.setBounds(10, 56, 142, 20);
				userPassword.setText(getAuth(1));
				Connection.add(userPassword);
				
				userNameText = new JTextField();
				userNameText.setBounds(10, 11, 142, 20);
				userNameText.setText(getAuth(0));
				Connection.add(userNameText);
				userNameText.setColumns(10);
				tabbedPane.setBackgroundAt(2, Color.BLACK);
				tabbedPane.setForegroundAt(2, SystemColor.controlHighlight);
			
			JPanel Settings = new JPanel();
			Settings.setForeground(SystemColor.desktop);
			Settings.setBackground(Color.DARK_GRAY);
			tabbedPane.addTab("Settings", null, Settings, null);
			tabbedPane.setBackgroundAt(3, Color.BLACK);
			tabbedPane.setForegroundAt(3, SystemColor.controlHighlight);
			
			// [PANEL] About
			Panel About = new Panel();
			About.setBackground(Color.DARK_GRAY);
				tabbedPane.addTab("About", null, About, null);
				About.setLayout(null);
				
				JLabel lblAtomikabotVersion = new JLabel("AtomikaBOT - Version 0.2");
				lblAtomikabotVersion.setForeground(Color.WHITE);
				lblAtomikabotVersion.setBounds(215, 324, 148, 25);
				About.add(lblAtomikabotVersion);
				tabbedPane.setForegroundAt(4, SystemColor.controlHighlight);
				tabbedPane.setBackgroundAt(4, Color.BLACK);
			
			// [BUTTON] Connect / Disconnect
			JButton btnConnect = new JButton("Connect");
			btnConnect.setFont(new Font("Open Sans", Font.BOLD, 12));
				btnConnect.setForeground(new Color(51, 153, 0));
				btnConnect.setBackground(Color.DARK_GRAY);
				btnConnect.setBounds(10, 477, 93, 26);
				frmAtomikabot.getContentPane().add(btnConnect);
			
			// [PANEL] Logo
			JPanel LogoPanel = new JPanel();
				LogoPanel.setBackground(Color.DARK_GRAY);
				LogoPanel.setBorder(null);
				LogoPanel.setBounds(0, 0, 378, 74);
				frmAtomikabot.getContentPane().add(LogoPanel);
			
			// USER LIST STUFF ( FIX OR DELETE ) 		
			JButton btnUserList = new JButton("User List");
				LogoPanel.add(btnUserList);
				btnUserList.setEnabled(false);
				frmAtomikabot.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnConnect, btnUserList}));
				btnUserList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						}
					});
				
			// [BUTTON] Exit
			JButton btnExit = new JButton("Exit");
			btnExit.setFont(new Font("Open Sans", Font.BOLD, 12));
				btnExit.setBackground(Color.DARK_GRAY);
				btnExit.setForeground(SystemColor.WHITE);
				btnExit.setBounds(304, 478, 64, 25);
				frmAtomikabot.getContentPane().add(btnExit);
			
			// [TEXT FIELD] Channel Select
			ChannelText = new JTextField();
			ChannelText.setFont(new Font("Open Sans", Font.PLAIN, 11));
				ChannelText.setBounds(113, 480, 116, 20);
				frmAtomikabot.getContentPane().add(ChannelText);
				ChannelText.setColumns(10);
				


		// ---CONNECTION BUTTON--- \\
		
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UniqueCLabel.setText("0");
				String textcheck = ChannelText.getText();
				
				// What the button does when NOT CONNECTED to a channel
				if (isConnected == true){
					btnConnect.setText("Connect");
					isConnected = false;
					btnConnect.setForeground(new Color(51, 153, 0));
					System.out.println(AtomikaBot.MY_CHANNEL);
					AtomikaBot.chatterCount = 0;
					ChannelText.setEditable(true);
					ChannelText.setBackground(ColorUIResource.DARK_GRAY);
					bot.disconnect();
					AtomikaBot.bot.disconnect();
					
				} 
				// What the button does when CONNECTED to a channel
				else if(isConnected == false){
					
					if (textcheck.isEmpty()){
					} else {
					// Gets Textbox Channel
						AtomikaBot.MY_CHANNEL = "#" + ChannelText.getText();
					}
					
					btnConnect.setText("Disconnect");
					isConnected = true;
					btnConnect.setForeground(SystemColor.RED);
					ChannelText.setEditable(false);
					ChannelText.setBackground(ColorUIResource.BLACK);
//					
//				if(AtomikaBot.MY_CHANNEL == "#rithek"){
//						AtomikaBot.bot.sendMessage(AtomikaBot.MY_CHANNEL, "/me has powered on!");
//						}else
//						}
				try {
					bot.Testerthing();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
				
				// THE BUTTON (MOVE ALL THIS STUFF INTO THE IF STATEMENT)
//				try {
//					bot.disconnect();
//					AtomikaBot.bot.disconnect();
//					UniqueCLabel.setText("0");
//					
//					String textcheck = ChannelText.getText();
					
//					if (textcheck.isEmpty()){
//					} else {
//					// Gets Textbox Channel
//						AtomikaBot.MY_CHANNEL = "#" + ChannelText.getText();
//					}
					
					//bot.Testerthing();
					//System.out.println(AtomikaBot.MY_CHANNEL);
					
//				} catch (IOException e) {
//				}
			}
		});
		
		
		btnCommandsOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Commands = TURN OFF
				if (commandsToggle == true){
					commandsToggle = false;
					btnCommandsOn.setText("Commands: Off");
					btnCommandsOn.setForeground(SystemColor.RED);
					textFieldChat.setEditable(false);
					textFieldChat.setBackground(ColorUIResource.DARK_GRAY);
					
				// Commands = TURN ON	
				} else{
					commandsToggle = true;
					btnCommandsOn.setText("Commands: On");
					btnCommandsOn.setForeground(new Color(51, 153, 0));
					textFieldChat.setEditable(true);
					textFieldChat.setBackground(ColorUIResource.GRAY);
					
				}
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bot.disconnect();
				System.exit(0);
			}
		});
		// SEND BUTTON
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (commandsToggle == true){
				AtomikaBot.bot.sendMessage(AtomikaBot.MY_CHANNEL, textFieldChat.getText());
				textFieldChat.setText("");
				} else{}
			}
		});
		
		
		// Next Scene BUTTON
				btnNextScene.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					AtomikaBot.bot.sendMessage(AtomikaBot.MY_CHANNEL, AtomikaBot._getNextQuestion());
					}
				});
		
	}
	private static String getAuth(int x){
		String file = "D:\\\\Stream\\AtomikaBot\\Auth.txt";
		   try (Scanner inFile = new Scanner(new File(file))) {
			   String username = inFile.nextLine();
			   String password = inFile.nextLine();
			   
				switch (x){
					case 0: 
						return username;
					case 1:
						return password;
					
					default: return "";
					
				}
	        } catch (FileNotFoundException e){
	            return "";
	        }
	
	}
}
