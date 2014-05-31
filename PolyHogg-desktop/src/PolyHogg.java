
 
import com.PolyHogg.view.MenuScreen;
import com.badlogic.gdx.Game;
 
public class PolyHogg extends Game {
 
	/*
	 * Charge les differents ressources utile a l'application
	 */
    @Override
    public void create() {
    	setScreen(new MenuScreen());
    }
 
}
