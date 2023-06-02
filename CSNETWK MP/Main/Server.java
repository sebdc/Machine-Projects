/*|************************************************************************
      
        CSNETWK - S13
        Machine Project
        April 12, 2023 
      
        Group 18
            - DELA CRUZ, Sebastien Michael V.
            - PANGILINAN, Wilhelm Vincent S.	

**************************************************************************/
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

	/// Class Attributes
	private String host;
	private int port;
	private HashMap<String,User> userList;
	private ServerSocket server;
	private JsonMessageHandler json;

    /*|*******************************************************
      
                              Executable

    *********************************************************/  
	public static void main( String[] args ) throws IOException {
        new Server( "localhost", 4000 ).start();
	}

    /*|*******************************************************

                           Constructor Method

    *********************************************************/  
    public Server( String host, int port ) throws IOException, SocketException {
        this.host = host;
        this.port = port;
        this.server = new ServerSocket(port);
        this.userList = new HashMap<String,User>();
		this.json = new JsonMessageHandler();
    }

    /*|*******************************************************

                             Thread Method

    *********************************************************/  
	/**
	 *    ` Starts the server and listens for incoming connections from clients.
	 *    Each client is added to the user list as a new user with a pre-generated
	 *    username. A new thread is created to handle the communication between
	 *    the server and each client.
	 * 
	 *    @throws IOException     if there is an error writing to the output stream
	 *    @throws SocketException if there is an error with the socket used to listen for connections
	 */
	public void start() throws IOException, SocketException {

		System.out.printf( "Server %d is now open.\n", this.port );
		Boolean isOpen = true;
		int joinCount = 0;

		while( isOpen ) {
			// - Accept a client
			Socket client = server.accept();

			// - Add the new user to the hashmap
			User user = new User(client);
			String username = "User" + String.valueOf(++joinCount);
			user.setUsername(username);
			this.userList.put( username, user );

			// - Create a new thread for the user
			new Thread( new UserHandler(this, user) ).start();
		}
	}
	
	/*|*******************************************************
	
						Command Methods    

	*********************************************************/ 
	/**
	 *    ` Processes a command sent by a user and executes the corresponding action.
	 *    An unregistered user can only use the commands /?, /leave, and /register. 
	 * 
	 *    @param sender           the user sending the message
	 *    @param jsonString       the command in jsonString to be processed
	 *    @throws IOException     if there is an error writing to the output stream
	 *    @throws SocketException if there is an error with the socket used to listen for connections
	 */
	public void processCommand( User sender, String jsonString ) throws IOException, SocketException {
		
		String message = json.deserialize(jsonString);
		String[] parts = message.split( " " );
		String command = parts[0];

		switch( command ) { 
			case "/all" : {
				broadcastMessage( sender, message );
			} 	break;
			case "/msg" : {
				directMessage( sender, message );
			}   break;
			case "/multicast" : {
				multicastMessage( sender, message );
			}   break;
			case "/register" : {
				registerUser( sender, message );
			}   break;
			case "/leave" : {
				leaveServer( sender, message );
			}   break;
			case "/?" : {
				listCommands( sender, message );
			}   break;
			default: {
			}   break;
		}
	}

	/**
	 *    ` List all the available commands while the client is connected to the server.
	 *    It provides a brief description and the correct format for each command. This
	 *    function checks if the command prompt and parameters are valid. If not, it 
	 *    displays the appropriate error message.
	 * 
	 * 	  @param sender           the user sending the message
	 *    @param input            expected format: /?
	 */
	private void listCommands( User sender, String input ) throws IOException {

		// - Valid: Display all commands
		if( input.equals("/?") ) {
			String commandList = "\nCommand List:\n" +
				"/all <message> \t\t- \tbroadcasts a message to everyone\n" +
				"/msg <handle> <message>\t- \tmessage a specific user\n" +
				"/multicast <handle,...,handle> <message> - message multiple specific users\n" +
				"/register <handle> \t- \tregister a unique handle\n" +
				"/leave \t\t\t- \tdisconnects client from the server\n";

			String output = "/msg " + sender.getUsername() + commandList;
			
			// - Send feedback message
			sender.sendJsonString( json.serialize(output) );
		}

		// - Error: Invalid use of /?
		else {
			String error = "/system Error: Invalid use of /?\n";
			sender.sendJsonString( json.serialize(error) );
		}
	}

	/**
	 *    ` Sends a message to all connected clients on the server. This function
	 *    checks if the command prompt and parameters are valid. If not, it displays
	 *    the appropriate error messages. 
	 * 
	 *    @param sender           the registered user sending the message
	 *    @param input            expected format: /all <message>
	 *    @throws IOException     if there is an error writing to the output stream
	 */
	private void broadcastMessage( User sender, String input ) throws IOException {

		String[] parts = null;
		String command = null;
		String message = null;

		// - Split message into two parts = { "/all", <message> }
		try {
			parts = input.split( "\\s+", 2 );
			command = parts[0]; 
			message = parts[1];
		} 

		// - Error: Missing parameters from /all
		catch( ArrayIndexOutOfBoundsException e ) {
			String error = "/system Error: Invalid use of /all <message>\n";
			sender.sendJsonString( json.serialize(error) );
		} 

		// - Error: User is not registered 
		if( sender.getIsRegistered() == false ) {
			String error = "/system Error: The user must first /register <handle> before using /all <message>\n";
			sender.sendJsonString( json.serialize(error) );
		}

		// - Valid: Send message to all connected clients
		else if( command.equals("/all") && parts.length == 2 ) {
			for( Map.Entry<String,User> entry : this.userList.entrySet() ) {
				User receiver = entry.getValue();
				if( receiver.getIsRegistered() ) {
					String output = command + " " + sender.getUsername() + ": " + message;
					receiver.sendJsonString( json.serialize(output) );
				}
			} 
		} 

		// - Error: Invalid use of /all
		else {
			String error = "/system Error: Invalid use of /all <message>\n";
			sender.sendJsonString( json.serialize(error) );
		}
	}

	/**
	 *    ` Sends a message to a specified connected client on the server. This function
	 *    checks if the command prompt and parameters are valid. If not, it displays
	 *    the appropriate error messages. 
	 * 
	 *    @param sender           the registered user sending the message
	 *    @param input            expected format: /msg <handle> <message>
	 *    @throws IOException     if there is an error writing to the output stream
	 */
	private void directMessage( User sender, String input ) throws IOException {

		String[] parts = null;
		String command = null, handle = null, message = null;

		// - Split message into three parts = { "/msg", <handle>, <message> }
		try {
			parts = input.split( "\\s+", 3 );
			command = parts[0];
			handle = parts[1];
			message = parts[2];
		} 
		
		// - Error: Missing parameters from using /msg
		catch( ArrayIndexOutOfBoundsException e ) {
			String error = "/system Error: Invalid use of /msg <handle> <message>\n";
			sender.sendJsonString( json.serialize(error) );
		} 
		
		// - Error: User is not registered
		if( sender.getIsRegistered() == false ) {
			String error = "/system Error: The user must first /register <handle> before using /all <message>\n";
			sender.sendJsonString( json.serialize(error) );		
		}

		// - Error: Handle not found
		else if( handle != null && this.userList.containsKey(handle) == false ) {
			String error = "/system Error: The user \"" + handle + "\" does not exist\n";
			sender.sendJsonString( json.serialize(error) );		
		}

		// - Valid: Sends message to receiver and feedback message to sender 
		else if( command.equals("/msg") && this.userList.containsKey(handle) && parts.length == 3 ) {

			// - Sends message to receiver 
			User receiver = this.userList.get(handle);
			String sent = command + " " + handle + " " + "[From " + sender.getUsername() + "]: " + message;
			receiver.sendJsonString( json.serialize(sent) );

			// - Sends feedback to sender 
			String feedback = command + " " + sender.getUsername() + " " + "[To " + handle + "]: " + message; 
			sender.sendJsonString( json.serialize(feedback) );
		}

		// - Error: Invalid use of /all
		else {
			String error = "/system Error: Invalid use of /msg <handle> <message>\n";
			sender.sendJsonString( json.serialize(error) );		
		}
	}

	/**
	 *    ` Sends a message to multiple specified connected clients on the server. This 
	 *    function checks if the command prompt and parameters are valid. If not, then it 
	 *    displays the appropriate error messages. 
	 * 
	 *    @param sender           the registered user sending the message
	 *    @param input            expected format: /multicast <handle,...,handle> <message>
	 *    @throws IOException     if there is an error writing to the output stream
	 */
	private void multicastMessage( User sender, String input ) throws IOException {
	
		String parts[] = null, namesArr[] = null;
		String command = null, names = null, message = null;

		// - Split message into three parts = { "/mc", <handle,...,handle> <message> }
		try {
			parts = input.split( "\\s+", 3 );
			command = parts[0];             
			names = parts[1];     
			namesArr = names.split( "," );  	// - { handle, handle, handle }          
			message = parts[2];             	
		} 
		
		// - Error: Missing parameters from using /mc
		catch( ArrayIndexOutOfBoundsException e ) {
			String error = "/system Error: Invalid use of /mc <handle,...,handle> <message>\n";
			sender.sendJsonString( json.serialize(error) );
		} 

		// - Error: Missing parameters from using /mc
		catch( NullPointerException e ) {
			String error = "/system Error: Invalid use of /mc <handle,...,handle> <message>\n";
			sender.sendJsonString( json.serialize(error) );		
		}
		
		// - Error: User not registered
		if( sender.getIsRegistered() == false ) {
			String error = "/system Error: The user must first /register <handle> before using /mc <handle,...,handle> <message>\n";
			sender.sendJsonString( json.serialize(error) );		
		}

		// - Valid: Send message to receivers and feedback message to sender
		else if( command.equals("/multicast") && parts.length == 3 ) {

			// - Keep track of the message's status for each handle
			String[] success = new String[namesArr.length]; 
			String[] failure = new String[namesArr.length];
			int sentCount = 0, failCount = 0;

			// - Sends DIRECT MESSAGE to each receiver if found
			for( int i = 0; i < namesArr.length; i++ ) {
				String handle = namesArr[i];
				if( this.userList.containsKey(handle) == true ) {
					String newInput = "/msg" + " " + handle + " " + message; 
					processCommand( sender, json.jsonToJsonString(json.serialize(newInput)) );
					success[sentCount] = handle; 
					sentCount++;
				} else {
					failure[failCount] = handle;
					failCount++;
				}
			}

			// - Sends success feedback through DIRECT MESSAGE to sender 
			if( sentCount > 0 ) {
				String feedback = "/msg" + " " + sender.getUsername() + " " + "[Server] The message was successfully sent to user(s)";
				if( sentCount == 1 ) {
					feedback += " " + success[0];
				} else {
					for( int handle = 0; handle < sentCount-1; handle++ ) {
						feedback += " " + success[handle] + ",";
					} feedback += " and " + success[sentCount-1];
				} 

				// - Send feedback message
				sender.sendJsonString( json.serialize(feedback) );			
			}

			// - Sends failure feedback through DIRECT MESSAGE to sender 
			if( failCount > 0 ) {
				String feedback = "/msg" + " " + sender.getUsername() + " " + "[Server] The user(s)";
				if( failCount == 1 ) {
					feedback += " " + failure[0];
				} else {
					for( int handle = 0; handle < failCount-1; handle++ ) {
						feedback += " " + failure[handle] + ",";
					} feedback += " and " + failure[failCount-1];
				} feedback += " were not found";

				// - Send feedback message
				sender.sendJsonString( json.serialize(feedback) );
			}
		} 

		// - Error: Invalid use of /mc
		else {
			String error = "/system Error: Invalid use of /multicast <handle,...,handle> <message>\n";
			sender.sendJsonString( json.serialize(error) );
		}
		
	}

	/*|*******************************************************
	
					  Alias Handling Methods    

	*********************************************************/ 
	/**
	 *    ` This method registers a user by adding their username and user object
	 *    to the user list. If the user is already registered, or if the username
	 *    is already taken, the method will return the appropriate error message.
	 *    Otherwise, the user will be registered and their username will be updated.
	 * 
	 *    @param sender              the user who sent the registration request
	 *    @param input            	 expected format: /register <handle>
	 *    @throws IOException        if there's an error with the output stream
	 */
	private void registerUser( User sender, String input ) throws IOException {

		String[] parts = null, handleParts = null;
		String command = null, handle = null;

		// - Split input into two parts = { "/register", <handle> }
		try {
			parts = input.split( "\\s+", 2 );
			command = parts[0]; 
			handle = parts[1];
			handleParts = handle.split(" ");
		}

		// - Error: Missing parameters from using /register
		catch( ArrayIndexOutOfBoundsException e ) {
			String error = "/system Error: Invalid use of /register <handle>\n";
			sender.sendJsonString( json.serialize(error) );
		} 

		// - Error: User is already registered
		if( sender.getIsRegistered() == true ) {
			String error = "/system Error: The user is already registered\n";
			sender.sendJsonString( json.serialize(error) );	
		}

		// - Error: Handle is already taken
		else if( this.userList.containsKey(handle) == true ) {
			String error = "/system Error: The handle " + handle + " is already taken\n";
			sender.sendJsonString( json.serialize(error) );	
		}

		// - Error: Handle contains space
		else if( handleParts.length != 1 || handle.trim().isEmpty() ) {
			String error = "/system Error: The handle must not contain spaces\n";
			sender.sendJsonString( json.serialize(error) );
		}
		
		// - Valid: Register user and send feedback message through DIRECT MESSAGE
		else if( command.equals("/register") && parts.length == 2 && this.userList.containsKey(handle) == false ) {

			// - Find the entry of the sender
			this.userList.remove( sender.getUsername() );   // - Remove sender from the user list
			this.userList.put( handle, sender );            // - Add sender back to the user list with new handle
			sender.setUsername(handle);                     // - Replace sender's username
			sender.setIsRegistered(true);                   // - Register user

			// - Send feedback message
			String feedback = "/msg" + " " + sender.getUsername() + " " + "[Server] User has successfully registered. Welcome " + sender.getUsername() + "!\n";
			sender.sendJsonString( json.serialize(feedback) );
		}

		// - Error: Invalid use of /register 
		else {
			String error = "/system Error: Invalid use of /register <handle>\n";
			sender.sendJsonString( json.serialize(error) );		
		}
	}


	/**
	 *    ` This method removes the user from the user list when they disconnect the
	 * 	  server. This function checks if the command prompt and parameters are valid. 
	 * 	  If not, it displays the appropriate error messages. 
	 * 
	 *    @param sender              the user who will disconnect from the server
	 *    @param input            	 expected format: /leave 
	 *    @throws IOException        if there's an error with the output stream
	 */
	private void leaveServer( User sender, String input ) throws IOException {
		
		// - Valid : Leave server
		if( input.equals("/leave") ) {

			// - Sends feedback 
			String feedback = "/system" + " " + "System: " + sender.getUsername() + " has left the server\n";
			sender.sendJsonString( json.serialize(feedback) );

			// - Disconnect client from server
			this.userList.remove( sender.getUsername() );
			sender.getClient().shutdownOutput();
		}

		// - Error: Invalid use of /leave
		else {
			String error = "/system Error: Invalid use of /leave\n";
			sender.sendJsonString( json.serialize(error) );
		}
	}

	/*|*******************************************************
	
						Getters and Setters

	*********************************************************/   
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public ServerSocket getServer() {
		return server;
	}
} 