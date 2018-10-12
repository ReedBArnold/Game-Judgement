/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: June 15, 2015
 * 
 * 
 * Title: Event
 * Description: Think of every time a cutscene starts or a chest is opened or a tree collapses,
 * these actions are happening because of a trigger, this class is that trigger as well as it 
 * contains what the trigger starts.
 * 
 * Currently the Events that are available are warping and item obtaining, edit this class in the future
 * to allow for additional events.
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package
package axohEngine2.map;

//Imports
import axohEngine2.project.Item;
import axohEngine2.project.TYPE;

public class Event {
	
	//puzzle vairables
	boolean p1;
	
	//Warping variables
	private int newX, newY, newMapX, newMapY;
	private String _mapName;
	private String _overlayName;
	
	//Item getting variables
	private String _name;
	private Item _item;
	
	//Event Type
	private TYPE type;
	
	/**********************************************************
	 * Constructor
	 * 
	 * @param name - A String detailing an events name
	 * @param type - An Enum detailing the type of event
	 **********************************************************/
	public Event(String name, TYPE type) {
		_name = name;
		this.type = type;
	}
	
	public String getName(){
		return _name;
	}
	
	/************************************************************
	 * Set up a warp event 
	 * 
	 * @param mapName - A String detailing the name of the map which was set in the map Database
	 * @param overlayName - Same as mapName
	 * @param x - player x Int
	 * @param y - player y Int
	 *************************************************************/
	public void setWarp(String mapName, String overlayName, int x, int y, int xx, int yy) {
		_mapName = mapName;
		_overlayName = overlayName;
		newX = x;
		newY = y;
		newMapX = xx;
		newMapY = yy;
	}
	
	public boolean isTrue(){
		return p1;
	}
	
	/*******************************************************
	 * Set up a dialogue event
	 * 
	 * @param leading text string
	 * @param option one string
	 * @param option two string
	 * @param option three string
	 *
	 *******************************************************/
	public void setDialogue(String mo, String op1, String op2, String op3){
		
	}
	
	
	/************************************************
	 * Set an Item to this event
	 * 
	 * @param item - Of type Item
	 ************************************************/
	public void setItem(Item item) {
		_item = item;
	}
	
	/**************************************************
	 * All of these are getters, they take no paramter, but return what you ask for
	 **************************************************/
	public String getMapName() { return _mapName; }
	public String getOverlayName() { return _overlayName; }
	public int getNewX() { return newX; }
	public int getNewY() { return newY; }
	public int getNewMapX() { return newMapX;}
	public int getNewMapY() { return newMapY;}
	public TYPE getEventType() { return type; }
	public String getname() { return _name; }
	public Item getItem() { return _item; }
}
