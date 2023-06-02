package source.framework.gamestate;
import java.util.Stack;

import source.framework.gui.Window;

public class GameManager {

    private GameData data;
    private Stack<GameState> state;
    private Window window;

    public GameManager() {
        state = new Stack<GameState>();
        window = new Window();
    }

    /*|**********************************************

                      Stack Methods

    ************************************************/
    public void stackState( GameState state ) {
        this.state.push( state );
    }

    public void popStack() {
        this.state.pop();
    }

    public GameState peekStack() {
        return state.peek();
    }

    public void clearStack() {
        this.state.clear();
    }

    public Boolean isStackEmpty() {
        if( state.empty() ) 
            return true; 
        else 
            return false; 
    }

    public Window getWindow() {
        return window;
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/ 
    public void setData( GameData data ) {
        this.data = data;
    }

    public GameData getData() {
        return data;
    }
}