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
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Client {

    /// Class Attributes
    private Socket client;
    private Boolean isRunning;
    private Boolean isConnected;
    private JsonMessageHandler json;

    /*|*******************************************************
                            Executable
    *********************************************************/  
    public static void main( String[] args ) throws UnknownHostException, IOException {
        System.out.println( "Starting client..." ); 
        new Client().start();
    }

    /*|*******************************************************
                        Constructor Method
    *********************************************************/   
    Client() {
        this.client = null;
        this.isRunning = true;
        this.isConnected = false;
        this.json = new JsonMessageHandler();
    }

    /*|*******************************************************
                          Thread Methods
    *********************************************************/ 
    /**
     *    ` This method starts the client-side application. It prompts the user 
     *    for inputs while the client is not connected to a server. Once connected,
     *    it creates a new thread using the ClientHandler to handle the incoming 
     *    messages and prompts the to send inputs to the server. It also checks for 
     *    valid commmands as well as terminate the program if necessary.
     * 
     *    @throws UnknownHostException      if the IP address of a host could not be find
     *    @throws IOException               if there's an error with the output stream
     */
    public void start() throws UnknownHostException, IOException, SocketException {
        
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in) );

        while( this.isRunning ) {

            /*|*********************************
                  Not connected to a server
            ***********************************/ 
            while( this.isConnected == false ) {
                
                // - Parse inputs 
                System.out.print( "[>] " ); 
                String input = reader.readLine();

                // - Check for valid commands
                processCommand( input );

                // - Check if client is connected to a server
                if( this.client != null ) {
                    this.isConnected = true;
                }
            } 
                
            /*|*********************************
                    Connected to a server
            ***********************************/ 
            if( isConnected == true ) {

                // - Create a new thread for handling messages
                new Thread( new ClientHandler(this.client.getInputStream()) ).start();
                DataOutputStream outWriter = new DataOutputStream( client.getOutputStream() );

                while( isConnected == true ) {

                    // - Ask user for input
                    String message = reader.readLine();

                    try { 
                        if( isInputValid(message) ) {

                            // - Convert input to json object
                            ObjectNode jsonObj = this.json.serialize(message);
                        
                            // - Convert jsonObj to jsonString
                            String jsonString = this.json.jsonToJsonString(jsonObj);

                            // - Send input to server
                            outWriter.writeUTF( jsonString );
                        }
                    } 

                    // - Error: Server crashed
                    catch( SocketException e ) {
                        System.out.println( "Server cannot be found." ); 
                    }

                    // - Error: Server crashed
                    catch( IOException e ) {
                        System.out.println( "Server cannot be found." ); 
                    }

                    // - Disconnect client from the server
                    if( message.equals("/leave") ) {
                        // - Delay so the server can process the /leave message
                        try {
                            Thread.sleep(500); // 500 milliseconds = 0.5 seconds
                        } catch( InterruptedException e ) {
                        } leaveServer( message );
                    }

                }
            }
        }

            // - Close program
            if( isRunning == false ) {
                System.out.println( "Terminating client." ); 
                reader.close();
                client.close();
            }
    }
    
    
    /*|*******************************************************
                        Command Methods   
    *********************************************************/ 
    /**
     *    ` Checks if the user input is valid based on the available commands while the client
     *    is connected to the server. In this context, the input is considered valid if the input
     *    meets the parameters of the command, regardless of the parameter's validity
     * 
     *    @param input                  the command message to be processed
     *    @throws IOException           if there is an error writing to the output stream
     *    @throws SocketException       if there is an error with the socket used to listen for connections
     */
    public Boolean isInputValid( String input ) throws IOException {

        String parts[] = input.split(" ");
        String command = parts[0];

        String error = "Invalid command. Enter \"/?\" to see the list of available commands.\n";
        Boolean isValid = false;

        if( this.isConnected == true ) {
            switch( command ) {

                case "/?" : 
                    if( input.equals("/?") ) {
                            isValid = true;
                    } else {
                    } break;

                case "/all" :
                case "/register" :  
                    try {
                        parts = input.split( "\\s+", 2 );
                        if( parts.length == 2 ) {
                            isValid = true;
                        }
                    } catch( ArrayIndexOutOfBoundsException e ) {
                    } break;

                case "/multicast" :
                case "/msg" : 
                    try {
                        parts = input.split( "\\s+", 3 );
                        if( parts.length == 3 ) { 
                            isValid = true;
                        }
                    } catch( ArrayIndexOutOfBoundsException e ) {
                    } break;
                
                case "/leave" : 
                    if( input.equals("/leave") ) {
                        isValid = true;
                    } break;
                
                default: {
                } break;
            }
        }

        if( isValid == false ) {
            System.out.println( error );
        } return isValid;
    }

    /**
     *    ` Processes a command sent by a user and executes the corresponding action.
     * 
     *    @param input                  the command message to be processed
     *    @throws IOException           if there is an error writing to the output stream
     *    @throws SocketException       if there is an error with the socket used to listen for connections
     */
    public void processCommand( String input ) throws IOException {
        
        String parts[] = input.split(" ");
        String command = parts[0];

        if( this.isConnected == false ) {
            switch( command ) {
                case "/?": {
                    listCommands(input);
                }   break;
                case "/join": {
                    joinServer(input);
                }   break;
                case "/leave": {
                    leaveServer(input);
                }   break;
                default: {
                    System.out.println( "Invalid command. Enter \"/?\" to see the list of available commands.\n" ); 
                }   break;
            }
        }
    }

    /**
     *    ` Attempts to connect the client to a server specified by an IP address and
     *    a port number provided in the input. It checks if the command prompt and its 
     *    parameters, as well as the client's connection status, are valid. It displays
     *    the appropriate success and error messages.
     * 
     *    @param input           expected format: /join <ip address> <port number>
     */
    private void joinServer( String input ) {

        String parts[] = input.split(" ");

        // - joinServer() only works when the client is not connected to a server
        if( parts[0].equals("/join") && this.isConnected == false ) {
            // - Attempt to connect to the server
            try { 
                String host = parts[1], port = parts[2];
                this.client = new Socket( host, Integer.parseInt(port) );
                System.out.println( "Succesfully connected to server at " + this.client.getRemoteSocketAddress() + "\n" );
            } 
        
            // - Invalid port number
            catch( NumberFormatException e ) {
                System.out.println( "Invalid port number. Port number must be a valid numeric value.\n" );
            } 
            
            // - Missing parameters from using /join
            catch( ArrayIndexOutOfBoundsException e ) {
                System.out.println( "Invalid use of /join. Refer to the format: /join <ip address> <port number>\n" );
            } 
            
            // - Server not found, this.client will be set to null
            catch( IOException e ) {
                System.out.println( "Connection error. The server could not be reached.\n" );
            }
        } else {
            System.out.println( "Invalid command. Enter \"/?\" to see the list of available commands.\n" ); 
        }
    }  

    /**
     *    ` Attempts to disconnect the client from a server. It checks if the command prompt 
     *    and its parameters, as well as the client's connection status, are valid. It displays
     *    the appropriate success and error messages.
     * 
     *    @param input           expected format: /leave
     */
    private void leaveServer( String input ) {
        
        // - joinServer() only works when the client is connected to a server
        if( input.equals("/leave") && this.isConnected == true ) {
            try {
                this.client.close();
                this.client = null;
                this.isConnected = false;
                System.out.println( "\rClient disconnected from server." );
            } catch( IOException e ) {}
        } else if( input.equals("/leave") && this.isConnected == false ) {
            System.out.println( "\rClient is not connected to a server.\n" );
        } else {
            System.out.println( "Invalid command. Enter \"/?\" to see the list of available commands.\n" ); 
        }
    }
    
    /**
     *    ` List all the available commands while the client is not connected to the 
     *    server. It provides a brief description and the correct format for each command. 
     *    This function checks if the command prompt and parameters are valid. If not, it 
     *    displays the appropriate error message.
     * 
     *    @param input            expected format: /?
     */
    private void listCommands( String input ) {

        // - List the available commands while the client IS NOT CONNECTED to a server
        if( input.equals("/?") && this.isConnected == false ) {
            System.out.print( 
                "\nCommand List:\n" +
                "/join \t- \t/join [ip address] [port number]\n\n"
            );
        }
        else {
            System.out.println( "Invalid command. Enter \"/?\" to see the list of available commands.\n" ); 
        }
    }

    /*|*******************************************************
      
                          Getters and Setters

    *********************************************************/ 
    public Boolean getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(Boolean isConnected) {
        this.isConnected = isConnected;
    }
}

class ClientHandler implements Runnable {
    
    /// Class Attributes
    private DataInputStream inReader;
    private Boolean isRunning;
    private JsonMessageHandler json;

    /*|*******************************************************
                        Constructor Method
    *********************************************************/ 
    public ClientHandler( DataInputStream inReader ) {
        this.inReader = inReader;
        this.isRunning = true;
        this.json = new JsonMessageHandler();
    }

    public ClientHandler( InputStream inReader ) {
        this.inReader = new DataInputStream( inReader );
        this.isRunning = true;
        this.json = new JsonMessageHandler();
    }

    /*|*******************************************************
                          Thread Methods
    *********************************************************/ 
    /**
     *    ` The thread created using the ClientHandler will execute the run() method.
     *    It runs a continuous loop that listens for incoming messages from the server.
     *    If there's an error with the input stream, the method ignores the exception 
     *    and terminates.
     */
    public void run() {
        try {
            while( this.isRunning ) {  

                // - Receive jsonString from server
                String jsonString = inReader.readUTF();

                // - Convert jsonString to jsonObject
                ObjectNode jsonObj = json.jsonStringToJson(jsonString);

                if( jsonObj.has("message")) {
                    String message = jsonObj.get("message").asText();
                    System.out.println( message );
                }
            }
        } catch( IOException e ) {}
    }

    /*|*******************************************************
      
                          Getters and Setters

    *********************************************************/  
    public DataInputStream getInReader() {
        return inReader;
    }

    public void setInReader(DataInputStream inReader) {
        this.inReader = inReader;
    }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

}