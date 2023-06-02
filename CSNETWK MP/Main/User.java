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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class User {

    /// Class Attributes
    private Socket client;
    private DataInputStream inReader;
    private DataOutputStream outWriter;
    private String username;
    private Boolean isRegistered = false;
    private ObjectMapper mapper;

    /*|*******************************************************

                        Constructor Method

    *********************************************************/  
    public User( Socket client ) throws IOException {
        this.client = client;
        this.inReader = new DataInputStream( this.client.getInputStream() );
        this.outWriter = new DataOutputStream( this.client.getOutputStream() );
        this.mapper = new ObjectMapper();
    }

    /*|*******************************************************

                         Behaviour Methods

    *********************************************************/  
    public void sendString( String input ) throws IOException {
        this.outWriter.writeUTF( input );
    }

    public void sendJsonString( ObjectNode input ) throws IOException {
        String jsonString = mapper.writeValueAsString(input);
        this.outWriter.writeUTF( jsonString );
    }

    public String receiveString() throws IOException {
        String jsonString = this.inReader.readUTF();
        return jsonString;
    }

    public ObjectNode receiveJson() throws IOException {
        String jsonString = this.inReader.readUTF();
        return (ObjectNode) mapper.readTree( jsonString );
    }

    /*|*******************************************************
    
                        Getters and Setters

    *********************************************************/  
    public Socket getClient() {
        return client;
    }

    public DataInputStream getInReader() {
        return inReader;
    }

    public DataOutputStream getOutWriter() {
        return outWriter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

        public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}

/******************************************************************
 *     
 *      The UserHandler class
 * 
******************************************************************/
class UserHandler implements Runnable {
    
    /// Class Attributes
    private Server server;
    private User user;
    private DataInputStream inReader;
    private JsonMessageHandler json;


    /*|*******************************************************

                        Constructor Method

    *********************************************************/  
    public UserHandler( Server server, User user ) throws IOException {
        this.server = server;
        this.user = user;
        this.inReader = new DataInputStream( user.getClient().getInputStream() );
        this.json = new JsonMessageHandler();
    }

    /*|*******************************************************
    
                          Thread Methods

    *********************************************************/  
      /**
       *    ` This method is used to run the thread for handling messages sent by the 
       *    user to the server. It continuously listens for incoming messages and passes
       *    them to the server's processCommand() method for further handling. It also
       *    prints the message to the server's console. If an IOException or SocketException 
       *    is thrown, the thread will terminate.
       */
    public void run() throws ArrayIndexOutOfBoundsException {
        try {
            while(true) {

                // - Server receives a jsonString from client
                String jsonString = inReader.readUTF();

                // - Process jsonString to raw message (e.g. /all <message> )
                String message = this.json.deserialize(jsonString);
                System.out.printf( "[Server] %s : %s\n", this.user.getUsername(), message );

                // - Sends jsonString to server
                server.processCommand( this.user, jsonString );
            }
        } catch( SocketException e ) {
        } catch( IOException e ) {
        }
    }
}