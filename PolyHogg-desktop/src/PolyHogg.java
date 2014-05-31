
 
import com.PolyHogg.view.GameScreen;
import com.PolyHogg.view.MenuScreen;
import com.badlogic.gdx.Game;
 
public class PolyHogg extends Game {
 
	/*
	 * Charge les differents ressources utile a l'application
	 */
    @Override
    public void create() {
    	setScreen(new GameScreen());//Implemente interface ecran de jeu (show() et hide())
    	//setScreen(new MenuScreen());
    }
 
}