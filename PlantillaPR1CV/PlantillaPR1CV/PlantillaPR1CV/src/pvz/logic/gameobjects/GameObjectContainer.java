package pvz.logic.gameobjects;

import java.util.List;



import utils.Position;

import java.util.ArrayList;

public class GameObjectContainer {
	 private List<GameObject> gameObjects;
	 
	 public GameObjectContainer() {
		 
	 gameObjects = new ArrayList<>();
	 
	 }
	 
	 public void removeFromIndex(int index) {
	    this.gameObjects.remove(index);
	  }
	 
	 public String iconInPosition(Position position) {
	        for (int i = 0; i < gameObjects.size(); i++) {
	            if (gameObjects.get(i).isInPosition(position)) {
	                return gameObjects.get(i).getIcon();
	            }
	        }
	        return null;
	   }
	 
	 public boolean isEmpty(Position position) {
	        for (int i = 0; i < gameObjects.size(); i++) {
	            if (gameObjects.get(i).isInPosition(position)) {
	                return false;
	            }
	        }
	        return true; 
	    }
	 
	 public boolean removeDead() {
	        boolean removed = false;
	        for(int i = gameObjects.size() - 1; i >= 0; i--) {
	            if(!gameObjects.get(i).isAlive()) {
	                gameObjects.get(i).onExit();
	                removeFromIndex(i);
	                removed = true;
	            }
	        }
	        return removed;
	    }
	    
	 public void update() {
	    	for(int i=0; i<gameObjects.size(); i++) {
	    		gameObjects.get(i).update();
	        }
	    }
	 
	 public void add(GameObject object) { 
	     gameObjects.add(object);
	     
	 }
	
	 public int getIndex(Position p) {
		 for (int i = 0; i < gameObjects.size(); i++) {
	            if (gameObjects.get(i).isInPosition(p)) {
	            	return i;
	            }
	        }
		return -1;
	 }
	 
	 public int size() {
		 return gameObjects.size();
	 }
	 
	 public GameObject getObject(int i){
		 if(i==-1) {
			 return null;
		 }
		 return gameObjects.get(i);
	 }
	 public boolean catchObjectInPosition(Position p) {
	        for (int i = 0; i < gameObjects.size(); i++) {
	            if (gameObjects.get(i).isInPosition(p)) {
	                if (gameObjects.get(i).catchSun()) {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }
	 
}
