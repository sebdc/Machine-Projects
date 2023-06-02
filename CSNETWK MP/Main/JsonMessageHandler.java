/*|************************************************************************
      
        CSNETWK - S13
        Machine Project
        April 12, 2023 
      
        Group 18
            - DELA CRUZ, Sebastien Michael V.
            - PANGILINAN, Wilhelm Vincent S.

**************************************************************************/
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMessageHandler {

    private ObjectMapper mapper;

    /*|*******************************************************

                        Constructor Method

    *********************************************************/  
    public JsonMessageHandler() {
        this.mapper = new ObjectMapper();
    }

    /*|*******************************************************

                         Behaviour Methods

    *********************************************************/  
    public String jsonToJsonString( ObjectNode json ) throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(json);
        return jsonString;
    }

    public ObjectNode jsonStringToJson( String jsonString ) throws JsonProcessingException {
        ObjectNode json = (ObjectNode)mapper.readTree(jsonString);
        return json;
    }

    /*|*******************************************************

                         Serialize Methods
        - these methods take raw inputs as its parameter
        e.g. /all, /msg, /?, etc.

    *********************************************************/  
    /**
     *    ` Serializes the input string into a JSON object based on the specified 
     *    command. Assumes that the input string is valid and contains a valid command.
     * 
     *    @param input      user input with valid commands 
     */
    public ObjectNode serialize( String input ) throws JsonProcessingException, IOException {

        String[] parts = input.split( " " );
		String command = parts[0];

        // - Run commands
		switch( command ) {
            case "/?"        : return serializeDisplay(input);
            case "/register" : return serializeRegister(input);
            case "/all"      : return serializeBroadcast(input);
            case "/msg"      : return serializeUnicast(input);
            case "/multicast": return serializeMulticast(input);
            case "/leave"    : return serializeLeave(input);
            case "/system"   : return serializeSystem(input);
		}
        
        // - Return nothing; method assumes input are valid
        return null;
    }

    /**
     *    ` Serializes the input string based on the format specified for /?
     * 
     *    @param input      expected format: /?
     */
    public ObjectNode serializeDisplay( String input ) throws JsonProcessingException {

        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command", "?" );

        // - Returns input into JSON Format as string
        return json;
    }

    /**
     *    ` Serializes the input string based on the format specified for /register
     * 
     *    @param input      expected format: /register <handle>
     */
    public ObjectNode serializeRegister( String input ) throws JsonProcessingException {

		// - Split input into two parts = { "/register", <handle> }
		String[] parts = input.split( "\\s+", 2 );
		String handle = parts[1];

        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command", "register" );
        json.put( "handle", handle );
        return json;
    }

    /**
     *    ` Serializes the input string based on the format specified for /all
     * 
     *    @param input      expected format: /all <message>
     */
    public ObjectNode serializeBroadcast( String input ) throws JsonProcessingException {

		// - Split input into two parts = { "/all", <message> }
		String[] parts = input.split( "\\s+", 2 );
		String message = parts[1];

        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command", "all" );
        json.put( "message", message );
        return json;
    }

    /**
     *    ` Serializes the input string based on the format specified for /msg 
     * 
     *    @param input      expected format: /msg <handle> <message>
     */
    public ObjectNode serializeUnicast( String input )  throws JsonProcessingException {
    	
        // - Split message into three parts = { "/msg", <handle>, <message> }
		String[] parts = input.split( "\\s+", 3 );
		String handle = parts[1], message = parts[2];
        
        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command","msg" );
        json.put( "handle", handle );
        json.put( "message", message );
        return json;
    }

    /**
     *    ` Serializes the input string based on the format specified for /multicast 
     * 
     *    @param input      expected format: /multicast <handle,...,handle> <message>
     */
    public ObjectNode serializeMulticast( String input ) throws JsonProcessingException {
    	
        // - Split message into three parts = { "/multicast", <handles>, <message> }
		String[] parts = input.split( "\\s+", 3 );
		String handles = parts[1], message = parts[2];

        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command", "multicast" );
        json.put( "handles", handles );
        json.put( "message", message );
        return json;
    }

    /**
     *    ` Serializes the input string based on the format specified for /leave
     * 
     *    @param input      expected format: /leave
     */
    public ObjectNode serializeLeave( String input ) throws JsonProcessingException {
        
        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command", "leave" );

        // - Returns input into JSON Format as string
        return json;
    }

    /**
     *    ` Serializes the input string based on the format specified for the /system,
     *    a command used to send system messages to users.
     * 
     *    @param input      expected format: /leave
     */   
    public ObjectNode serializeSystem( String input ) throws JsonProcessingException {
        
		// - Split input into two parts = { "/system", <error_message> }
		String[] parts = input.split( "\\s+", 2 );
		String message = parts[1];

        // - Create JSON Object
        ObjectNode json = mapper.createObjectNode();
        json.put( "command", "system" );
        json.put( "message", message );
        return json;
    }
    

    /*|*******************************************************

                        Deserialize Methods
        - server and client can only receive/send string inputs
        - therefore, the parameters for these functions are jsonString
        - it returns the raw messages : /all, /msg, etc.

    *********************************************************/ 
    /**
     *    ` Deserializes a JSON string back to the unprocessed input as string 
     *    based on the specified command. It does this by converting the JSON
     *    string back to a JSON object, then retrieving the appropriate field
     *    values based on the specified command. It assumes that the JSON string 
     *    is a valid input.
     * 
     *    @param jsonString     JSON string with valid commands and parameters
     */   
    public String deserialize( String jsonString ) throws JsonProcessingException, IOException {

        ObjectNode json = (ObjectNode) mapper.readTree(jsonString);
        String command = json.get("command").asText();

        // - Run commands
		switch( command ) {
            case "?"        : return deserializeDisplay(json);
            case "register" : return deserializeRegister(json);
            case "all"      : return deserializeBroadcast(json);
            case "msg"      : return deserializeUnicast(json);
            case "multicast": return deserializeMulticast(json);
            case "leave"    : return deserializeLeave(json);
            case "error"    : return deserializeSystem(json);
		}
        
        // - Return nothing; method assumes input are valid
        return null;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /? 
     * 
     *    @param json       JSON Object for the command /?
     */ 
    public String deserializeDisplay( ObjectNode json ) throws JsonProcessingException {
        String command = "/" + json.get("command").asText();
        return command;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /register
     * 
     *    @param json       JSON Object for the command /register
     */ 
    public String deserializeRegister( ObjectNode json ) throws JsonProcessingException {
        String command = "/" + json.get("command").asText();
        String handle = json.get("handle").asText();
        return command + " " + handle;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /all
     * 
     *    @param json       JSON Object for the command /all
     */ 
    public String deserializeBroadcast( ObjectNode json ) throws JsonProcessingException {
        String command = "/" + json.get("command").asText();
        String message = json.get("message").asText();
        return command + " " + message;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /msg
     * 
     *    @param json       JSON Object for the command /msg
     */ 
    public String deserializeUnicast( ObjectNode json ) throws JsonProcessingException {
        String command = "/" + json.get("command").asText();
        String handle = json.get("handle").asText();
        String message = json.get("message").asText();
        return command + " " + handle + " " + message;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /multicast
     * 
     *    @param json       JSON Object for the command /multicast
     */ 
    public String deserializeMulticast( ObjectNode json ) throws JsonProcessingException {
        
        // - Split message into three parts
        String command = "/" + json.get("command").asText();
        String handles = json.get("handles").asText();
        String message = json.get("message").asText();
        String[] namesArr = handles.split( "," );

        String names = "";
        for( int i = 0; i < namesArr.length-1; i++ ) {
            names += namesArr[i] + ",";
        } names += namesArr[namesArr.length-1];
            
        return command + " " + names + " " + message;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /leave
     * 
     *    @param json       JSON Object for the command /leave
     */ 
    public String deserializeLeave( ObjectNode json ) throws JsonProcessingException {
        String command = "/" + json.get("command").asText();
        return command;
    }

    /**
     *    ` Deserializes the JSON object back to the unprocessed message for /system 
     * 
     *    @param json       JSON Object for the command /system
     */ 
    public String deserializeSystem( ObjectNode json ) throws JsonProcessingException {
        String command = "/" + json.get("command").asText();
        String message = json.get("message").asText();
        return command + " " + message;
    }
}