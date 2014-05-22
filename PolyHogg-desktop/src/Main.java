

import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
	public static void main(String[] args) {
		
		int hauteur = Constants.WINDOWS_HEIGHT*Constants.COTE_BLOCK;
		int largeur = Constants.WINDOWS_WIDTH*Constants.COTE_BLOCK;
		
		new LwjglApplication(new PolyHogg(),"PolyHogg",largeur,hauteur, true);
	}
}
