import org.jibble.pircbot.*;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jibble.pircbot.*;

// TEST GITHUBZ
 // ------------ATOMIKA BOT------------ \\
//                                       \\

public class AtomikaBot extends PircBot{
    // INITIATE BOT
	static AtomikaBot bot = new AtomikaBot();

	// CHANNEL SETTINGS
    static String MY_CHANNEL = "#rithek";
    static final String MY_NAME = "Rithek";
    static String BOT_NAME = "";
    static final String TWITCH_IRC = "irc.twitch.tv";
    static String TWITCH_OAUTH = "";
    static final int    TWITCH_PORT = 6667;

    // OTHER SETTINGS
    static final String FILE_LOCATION = "D:\\\\Stream\\AtomikaBot\\ScenesFromAHat.txt";
    static ArrayList<String> questionArray = new ArrayList<String>();
    static String currentQuestion = "";
    static double currentQuestionPosition = -1;
    static ArrayList<String> Usernames = new ArrayList<String>();

    static int chatterCount = 0;
	JFrame jframe = new JFrame();
	static ArrayList<String> weatherArray = new ArrayList<String>();
	static String currentWeather;
	static double currentWeatherPosition = -1;
	static long commandDelay = 3;
	
	static long lastCommandTime = new Date().getTime();
	
	
	
	// FOR FLAKES RANDOM
	
	
	// BOT SETUP
	public static void main(String[] args) throws Exception{
		
		}
	public AtomikaBot(JFrame frame) {
		jframe = frame;
		this.setName(BOT_NAME);
		this.setMessageDelay(500);
	}
	public AtomikaBot() {
		this.setName(BOT_NAME);
		this.setMessageDelay(500);
	}
	
	
	public boolean userExists (ArrayList<String> arr, String targetValue){
		for(String user: arr){
			if (user.equals(targetValue)){
				return true;
			} 
		}
		return false;
	}
	
	// ON MESSAGE STUFF
	
	@Override
	 public void onMessage(String channel, String sender,
			 		String login, String hostname, String message) {
		String[] messageArray = message.split(" ");
		String command = messageArray[0];
		
		
		if (command.startsWith("!")){
			
			long date = new Date().getTime();
			long currentCommandTime = date;
			
			if (isWorthy(currentCommandTime)){

				// COMMANDS TOGGLE
				if (AtomikaInterface.commandsToggle == true){
					// COMMANDS ON	
					switch(command){
					default:break;
					
						case "!frames":
						    sendMessage(channel, sender + " just called in, the man wants some frames, and here they are!");
							return;	
					
						case "!5fpsrace":
						    sendMessage(channel, "City Council is again issuing a request to locals to please stop asking for 5fps races. It's just never going to happen!");
							return;	
							
						case "!weather":
						    sendMessage(channel, _getWeather());
							return;
							
						case "!bonk":
						    sendMessage(channel, "Here's an update to some earlier news. Initial reports of competitors bonking rails, trees, and everything else have crowds wondering 'is it on purpose?' I can assure you these riders know exactly what they are doing Kappa");
							return;
							
						case "!yt":
						    sendMessage(channel, "When I'm carving it up, I watch Rithek's YouTube. And now so are you:  https://goo.gl/pT7JQd");
							return;
							
						case "!commands":
						    sendMessage(channel, "!yt, !weather, !bonk, !frames, !5fpsrace");
							return;
					}

				} else {
				// COMMANDS OFF
					
				}
			}
		} // Closes ! Check
		
		// Chatter Count
		if(userExists(Usernames, sender)){
			//System.out.println("Chatter Already Exists");
		} else {	
			// ****FIX EXCEPTIONS****
			if(!sender.equalsIgnoreCase ("nightbot")){
				if(!sender.equalsIgnoreCase("rithek"))
				{
					Usernames.add(sender);
					chatterCount++;
					//System.out.println(sender + " Added");
					//System.out.println(chatterCount);
					AtomikaInterface.UniqueCLabel.setText(chatterCount + ""); 
				}
			}
		}

		
	// MY COMMANDS
        if (sender.equalsIgnoreCase(MY_NAME)){
            switch(command){
            
                case "!scenes":
                    sendMessage(channel, _getNextQuestion());
                    return;
                case "!repeatquestion":
                	sendMessage(channel, _getCurrentQuestion());
                	 return;
                default:break;
	        }
	    }
	}
	// ***********************ON LAUNCH CODE***********************
	public void Testerthing() throws IOException{
		String username = AtomikaInterface.userNameText.getText();
		String password = AtomikaInterface.userPassword.getText();
		
		// Test if fields are empty
		if (username.equals("") || password.equals("")){
			JOptionPane.showMessageDialog(AtomikaInterface.frmAtomikabot, "Enter bot credentials in the connnect tab.");
		} else {
			TWITCH_OAUTH = password;
			BOT_NAME = username;
			
			try {
				bot.setName(username);
	            bot.connect(TWITCH_IRC, TWITCH_PORT, TWITCH_OAUTH);
	            bot.joinChannel(MY_CHANNEL);
	            
	            _getQuestions(FILE_LOCATION);
		        loadWeather();
				Usernames.add(BOT_NAME);
				_saveAuth(username, password);
				
	        } catch (NickAlreadyInUseException e) {
	            System.err.println("Nickname is currently in use");
	        } catch (IrcException e) {
	            System.err.println("Server did not accept connection");
	        }
			
		}
	}
	
	
	private void _saveAuth(String username, String password) throws UnsupportedEncodingException {	
		try{
			PrintWriter writer = new PrintWriter("D:\\\\Stream\\AtomikaBot\\Auth.txt", "UTF-8");
			writer.println(username);
			writer.println(password);
			writer.close();
		} catch(FileNotFoundException e){
			
		}
	}
	
	
	@Override
	 public void onUserList(String channel, User[] users) 
	{ for (int i = 0; i < users.length; i++) 
	{ User user = users[i]; String nick = user.getNick();
	System.out.println(nick); 
	} 
	}
	
	public void _getUsers(){

		 User[] whatever = bot.getUsers(MY_CHANNEL);
		for( User user : whatever){
			System.out.println(user.getNick());
		}
	}
	
	// QUESTION LOADER
    public static void _getQuestions(String file) throws FileNotFoundException{
        // Avoid appending
        questionArray.clear();
        // Try to read the file.
        // Cycle through each line, adding it to the array
        try (Scanner inFile = new Scanner(new File(file))) {
            while (inFile.hasNextLine()){
                questionArray.add(inFile.nextLine());
            }
        } catch (FileNotFoundException e){
            System.err.println("Can't find the file: " + file);
        }
    }
    
    // NEW QUESTION
	public static String _getNextQuestion(){
	        
        // remove the last question used from array
		//System.out.println(currentQuestionPosition);
        if (currentQuestionPosition >= 0 ) {
            questionArray.remove((int)currentQuestionPosition);
        }
        int randomNumber;
        try{
        	// get a new random position
            randomNumber = (int)(Math.floor(Math.random() * (questionArray.size() -1 )));
            // grab question with random number ^
        	
        	
            String randomQuestion = questionArray.get(randomNumber);
            currentQuestion = randomQuestion;
            currentQuestionPosition = randomNumber;
        } catch(ArrayIndexOutOfBoundsException e){
            currentQuestion = "No more questions to display!";
            currentQuestionPosition = -1;
        }
        return currentQuestion;
		}
	
	// GET CURRENT QUESTION
    private String _getCurrentQuestion(){
		return currentQuestion;
    	}
    
    // GET RANDOM WEATHER
    private String _getWeather(){
        int randomNumber;
        try{
        	// get a new random position
            randomNumber = (int)(Math.floor(Math.random() * (weatherArray.size() )));
            // grab question with random number ^
        	
        	
            String randomWeather = weatherArray.get(randomNumber);
           // System.out.println(randomNumber);
            currentWeather = randomWeather;
            currentWeatherPosition = randomNumber;
        } catch(ArrayIndexOutOfBoundsException e){
        	currentWeather = "404 Weather not found Kappa";
        	currentWeatherPosition = -1;
        }
        return currentWeather;
		}
    // WEATHER LOADER
    public static void loadWeather(){
    	weatherArray.clear();
		weatherArray.add("Reports are coming in from peak one's high alpine stating that avalanche conditions have moved to the extreme. Riders are advised to use caution, when venturing out into the back country.");
		weatherArray.add("Alright here's what's happenin' on the mountain. Recent heavy snowfalls have left all three peaks in great shape today,"
				+ " but avalanche warnings are still in the red so ski patrol is advising caution. ");
		weatherArray.add("In the latest weather update, all three peaks today will be experiencing clear skies, some mild winds, and mid range temperatures - perfect conditions for those of us lucky enough to be heading out riding.");
		weatherArray.add("Time for a weather update, I'm happy to report yesterday's conditions will be carrying through most of today - actually, all of today. Riders can expect excellent visibility, mild winds, and low range in the high alpines.");
    }
    
    public static boolean isWorthy(long currentCommandTime){
    	long date = new Date().getTime();
    	long diffInSeconds = (currentCommandTime - lastCommandTime);
    	if (diffInSeconds >= commandDelay * 1000){
    		lastCommandTime = date;
    		return true;
    	}
    	
		return false;	
    }    
}