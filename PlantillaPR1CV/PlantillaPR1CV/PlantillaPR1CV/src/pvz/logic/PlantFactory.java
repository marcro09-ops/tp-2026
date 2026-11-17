package pvz.logic;

import pvz.logic.gameobjects.Plant;




import pvz.logic.gameobjects.*;
import utils.Position; 
import pvz.logic.*;
import pvz.view.Messages;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;


public class PlantFactory {
	
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
			 new Sunflower(),
			 new Peashooter(),
			 new WallNut(),
			 new CherryBomb()
			 );
			 
	 
	 public static Plant spawnPlant(String plantName, GameWorld game, Position position) {
				 if (plantName.equalsIgnoreCase("p") || plantName.equalsIgnoreCase("peashooter")) {
			         Peashooter p = new Peashooter(game, position);
			         return p;
			     } 
			     else if (plantName.equalsIgnoreCase("s") || plantName.equalsIgnoreCase("sunflower")) {
			    	 Sunflower s=new Sunflower(game, position);
			        	return s;
			     }
			     else if (plantName.equalsIgnoreCase("c") || plantName.equalsIgnoreCase("cherry-bomb")) {
			    	 CherryBomb c=new CherryBomb(game, position);
			        	return c;
			     }
			     else if (plantName.equalsIgnoreCase("wn") || plantName.equalsIgnoreCase("wall-nut")) {
			    	 WallNut w=new WallNut(game, position);
			        	return w;
			     }
				 
				 return null;
			 }
	public static List<Plant> getAvailablePlants() {
			 return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}
		
}
