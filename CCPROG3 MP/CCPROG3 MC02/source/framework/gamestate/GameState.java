package source.framework.gamestate;
import javax.swing.JPanel;

public abstract class GameState extends JPanel {

    protected GameManager manager;

    protected GameState( GameManager manager ) {
        this.manager = manager;
    }
}
