package source.framework;
import source.framework.gamestate.*;
import source.game.states.*;

public class Engine {

	private static GameManager gameManager;
	
	public static void init() {
		gameManager = new GameManager();
	}
	
	public static void start() {
		gameManager.stackState( new TitleScreen(gameManager) );
		gameManager.getWindow().changePanel( gameManager.peekStack() );
		gameManager.getWindow().createWindow();
	}
}
