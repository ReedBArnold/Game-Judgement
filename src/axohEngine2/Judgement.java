/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 0.53
 * Date: June 14, 2015
 * 
 * 
 * Title: Judgement(The Game)
 * Description: This class extends 'Game.java' in order to run a 2D game with specificly defined
 *  sprites, animatons, and actions.
 *  
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package name
package axohEngine2;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

import axohEngine2.entities.AnimatedSprite;
import axohEngine2.entities.DIRECTION;
import axohEngine2.entities.ImageEntity;
import axohEngine2.entities.Mob;
import axohEngine2.entities.SpriteSheet;
import axohEngine2.map.Map;
import axohEngine2.map.Tile;
import axohEngine2.project.Dialogue;
import axohEngine2.project.Help;
import axohEngine2.project.InGameMenu;
import axohEngine2.project.MapDatabase;
import axohEngine2.project.OPTION;
import axohEngine2.project.STATE;
import axohEngine2.project.TYPE;
import axohEngine2.project.TitleMenu;

//Start class by also extending the 'Game.java' engine interface
public class Judgement extends Game {
	//For serializing (The saving system)
	private static final long serialVersionUID = 1L;
	
	/****************** Variables **********************/
	//--------- Screen ---------
	//SCREENWIDTH - Game window width
	//SCREENHEIGHT - Game window height
	//CENTERX/CENTERY - Center of the game window's x/y
	static int SCREENWIDTH = 1600;
	static int SCREENHEIGHT = 900;
	static int CENTERX = SCREENWIDTH / 2;
	static int CENTERY = SCREENHEIGHT / 2;
	
	//--------- Miscelaneous ---------
	//booleans - A way of detecting a pushed key in game
	//random - Use this to generate a random number
	//state - Game states used to show specific info ie. pause/running
	//option - In game common choices at given times
	//Fonts - Variouse font sizes in the Arial style for different in game text
	boolean keyLeft, keyRight, keyUp, keyDown, keyInventory, keyAction, keyBack, keyEnter, keySpace, keyShift, keyHelp, keyEsc, keyW, keyS, keyA, keyD;
	Random random = new Random();
	STATE state; 
	OPTION option;
	private Font simple = new Font("Arial", Font.PLAIN, 72);
	private Font bold = new Font("Arial", Font.BOLD, 72);
	private Font bigBold = new Font("Arial", Font.BOLD, 96);
	private Font italics = new Font("Arial", Font.ITALIC, 72);
	private Boolean switch1=false;
	private Boolean switch2=false;
	private Boolean switch3=false;
	private Boolean switch4=false;
	//--------- Player and scale ---------
	//scale - All in game art is 16 x 16 pixels, the scale is the multiplier to enlarge the art and give it the pixelated look
	//mapX/mapY - Location of the camera on the map
	//playerX/playerY - Location of the player on the map
	//startPosX/startPosY - Starting position of the player in the map
	//playerSpeed - How many pixels the player moves in a direction each update when told to
	private int scale;
	private int mapX;
	private int mapY;
	private int playerX;
	private int playerY;
	private int s1i=1;
	private int s2i=1;
	private int s3i=1;
	private int s4i=1;
	private int n1i=1;
	private int n2i=1;
	private int n3i=1;
	private int n4i=1;
	
	//private int startPosX;
	//private int startPosY;
	private int playerSpeed;
	private DIRECTION playerDirection;
	private int boxHeight = SCREENHEIGHT/4;
	
	//----------- Map and input --------
	//currentMap - The currently displayed map the player can explore
	//currentOverlay - The current overlay which usually contains houses, trees, pots, etc.
	//mapBase - The database which contains all variables which pertain to specific maps(NPCs, monsters, chests...)
	//inputWait - How long the system waits for after an action is done on the keyboard
	//confirmUse - After some decisions are made, a second question pops up, true equals continue action from before.
	private Map currentMap;
	private Map currentOverlay;
	private MapDatabase mapBase;
	private int inputWait = 5;
	private boolean confirmUse = false;
	
	//----------- Menus ----------------
	//inX/inY - In Game Menu starting location for default choice highlight
	//inLocation - Current choice in the in game menu represented by a number, 0 is the top
	//sectionLoc - Current position the player could choose after the first choice has been made in the in game menu(Items -> potion), 0 is the top.
	//titleX, titleY, titleX2, titleY2 - Positions for specific moveable sprites at the title screen (arrow/highlight).
	//titleLocation - Current position the player is choosing in the title screen(File 1, 2, 3) 0 is top
	//currentFile - Name of the currently loaded file
	//wasSaving/wait/waitOn - Various waiting variables to give the player time to react to whats happening on screen
	private int inX = 90, inY = 90;
	private int inLocation;
	private int sectionLoc;
	private int titleX = 530, titleY = 610;
	private int titleX2 = 340, titleY2 = 310;
	private int titleLocation;
	private String currentFile;
	private boolean wasSaving = false;
	private int wait;
	private boolean waitOn = false;
	private int dArrowX = 365;
	private int dArrowY = 690;
	private boolean confirmPickUp = false;
	private boolean renderOn = false;
	
	//----------- Game  -----------------
	//SpriteSheets (To be split in to multiple smaller sprites)
	SpriteSheet extras1;
	SpriteSheet extras2;
	SpriteSheet extras3;
	SpriteSheet extras4;
	SpriteSheet mainCharacter;
	
	//ImageEntitys (Basic pictures)
	ImageEntity inGameMenu;
	ImageEntity titleMenu;
	ImageEntity titleMenu2;
	
	//Menu classes
	TitleMenu title;
	InGameMenu inMenu;
	
	//Animated sprites
	AnimatedSprite titleArrow;
	AnimatedSprite dialArrow;
	
	//Player and NPCs
	Mob playerMob;
	Mob randomNPC;
	
	//initalize the variables
	Help help;
	Dialogue dialogue;
	
	
	
	/*********************************************************************** 
	 * Constructor
	 * 
	 * Set up the super class Game and set the window to appear
	 * @throws IOException 
	 **********************************************************************/
	public Judgement() throws IOException {
		super(130, SCREENWIDTH, SCREENHEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/****************************************************************************
	 * Inherited Method
	 * This method is called only once by the 'Game.java' class, for startup
	 * Initialize all non-int variables here
	 * @throws IOException 
	 *****************************************************************************/
	void gameStartUp() throws IOException {
		/****************************************************************
		 * The "camera" is the mapX and mapY variables. These variables 
		 * can be changed in order to move the map around, simulating the
		 * camera. The player is moved around ONLY when at an edge of a map,
		 * otherwise it simply stays at the center of the screen as the "camera"
		 * is moved around.
		 ****************************************************************/
		//****Initialize Misc Variables
		state = STATE.TITLE;
		option = OPTION.NONE;
		//startPosX = 450; //TODO: Make a method that takes a tile index and spits back an x or y coordinate of that tile
		//startPosY = 375;
		playerX = 690;
		playerY = 1;
		mapX = -12;
		mapY = -340;
		scale = 4;
		playerSpeed = 5;
		
		//****Initialize spriteSheets*********************************************************************
		extras1 = new SpriteSheet("/textures/extras/extras1.png", 8, 8, 32, scale);
		extras2 = new SpriteSheet("/textures/extras/extras2.png", 8, 8, 32, scale);
		extras3 = new SpriteSheet("/textures/extras/extras3.png", 8, 8, 32, scale);
		extras4 = new SpriteSheet("/textures/extras/extras4.png", 8, 8, 32, scale);
		mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, scale);

		//****Initialize and setup AnimatedSprites*********************************************************
		titleArrow = new AnimatedSprite(this, graphics(), extras1, 0, "arrow");
		titleArrow.loadAnim(4, 10);
		sprites().add(titleArrow);
		
		//****INitalize sprites for the dialogue event****************************************************
		dialArrow = new AnimatedSprite(this, graphics(), extras4, 0, "arrow"); //Dialogue Screen
		dialArrow.loadAnim(4, 10);
		sprites().add(dialArrow);
		
		//****Initialize and setup image entities**********************************************************
		inGameMenu = new ImageEntity(this);
		titleMenu = new ImageEntity(this);
		titleMenu2 = new ImageEntity(this);
		inGameMenu.load("/menus/ingamemenu.png");
		titleMenu.load("/menus/titlemenu1.png");
		titleMenu2.load("/menus/titlemenu2.png");
		
		//*****Initialize Menus***************************************************************************
		title = new TitleMenu(titleMenu, titleMenu2, titleArrow, SCREENWIDTH, SCREENHEIGHT, simple, bold, bigBold);
		inMenu = new InGameMenu(inGameMenu, SCREENWIDTH, SCREENHEIGHT);
		dialogue = new Dialogue(dialArrow, SCREENWIDTH, SCREENHEIGHT, simple, bold, italics);
		
		//****Initialize and setup Mobs*********************************************************************
		playerMob = new Mob(this, graphics(), mainCharacter, 40, TYPE.PLAYER, "mainC", true);
		playerMob.setMultBounds(6, 50, 95, 37, 88, 62, 92, 62, 96);
		playerMob.setMoveAnim(32, 48, 40, 56, 3, 8);
		playerMob.addAttack("sword", 0, 5);
		playerMob.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
		playerMob.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
		playerMob.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
		playerMob.setCurrentAttack("sword"); //Starting attack
		playerMob.setHealth(35); //If you change the starting max health, dont forget to change it in inGameMenu.java max health also
		sprites().add(playerMob);
		
		//*****Initialize and setup first Map******************************************************************
			mapBase = new MapDatabase(this, graphics(), scale);
		//Get Map from the database
		for(int i = 0; i < mapBase.maps.length; i++){
			if(mapBase.getMap(i) == null) continue;
			if(mapBase.getMap(i).mapName() == "island1") currentMap = mapBase.getMap(i);
			if(mapBase.getMap(i).mapName() == "island1o") currentOverlay = mapBase.getMap(i);
		}
		//Add the tiles from the map to be updated each system cycle
		for(int i = 0; i < currentMap.getHeight() * currentMap.getHeight(); i++){
			addTile(currentMap.accessTile(i));
			addTile(currentOverlay.accessTile(i));
			if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
			if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
			currentMap.accessTile(i).getEntity().setX(-300);
			currentOverlay.accessTile(i).getEntity().setX(-300);
		}
		
		requestFocus(); //Make sure the game is focused on
		start(); //Start the game loop
	}
	
	/**************************************************************************** 
	 * Inherited Method
	 * Method that updates with the default 'Game.java' loop method
	 * Add game specific elements that need updating here
	 *****************************************************************************/
	void gameTimedUpdate() {
		checkInput(); //Check for user input
		//Update certain specifics based on certain game states
		if(state == STATE.TITLE) title.update(option, titleLocation); //Title Menu update
		if(state == STATE.INGAMEMENU) inMenu.update(option, sectionLoc, playerMob.health()); //In Game Menu update
		updateData(currentMap, currentOverlay, playerX, playerY); //Update the current file data for saving later
		System.out.println(frameRate()); //Print the current framerate to the console
		if(waitOn) wait--;
		System.out.println(playerX + ", " + playerY + "   " + mapX + ", " + mapY);
	}
	
	/**
	 * Inherited Method
	 * Obtain the 'graphics' passed down by the super class 'Game.java' and render objects on the screen
	 */
	void gameRefreshScreen() {		
		/*********************************************************************
		* Rendering images uses the java class Graphics2D
		* Each frame the screen needs to be cleared and an image is setup as a back buffer which is brought 
		* to the front as a full image at the time it is needed. This way the screen is NOT rendered pixel by 
		* pixel in front of the user, which would have made a strange lag effect.
		* 
		* 'graphics' objects have parameters that can be changed which effect what it renders, two are font and color
		**********************************************************************/
		Graphics2D g2d = graphics();
		g2d.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT); 
		g2d.setFont(simple);
		Color boxB = new Color(0, 0, 0, 170);
		Color boxB1 = new Color(0, 0, 0, 190);
		Color boxW = new Color(255, 255, 255, 100);
		
		//GUI rendering for when a specific state is set, only specific groups of data is drawn at specific times
		if(state == STATE.GAME) {
			//Render the map, the player, any NPCs or Monsters and the player health or status
			currentMap.render(this, g2d, mapX, mapY);
			currentOverlay.render(this, g2d, mapX, mapY);
			playerMob.renderMob(CENTERX-playerX,CENTERY-playerY);
			
			//dialogue rendering
			if(renderOn == true){
				dialogue.render(this, g2d, dArrowX, dArrowY);
			}
			
			
			//g2d.setColor(Color.GREEN);
			//g2d.drawString("Health: " + inMenu.getHealth(), CENTERX - 780, CENTERY - 350);
			//g2d.setColor(Color.BLUE);
			//g2d.drawString("Magic: " + inMenu.getMagic(), CENTERX - 280, CENTERY - 350);
			//g2d.setColor(Color.YELLOW);
			//g2d.drawString("NPC health: " + currentOverlay.accessTile(98).mob().health(), CENTERX + 200, CENTERY - 350);
		}
		if(state == STATE.INGAMEMENU){
			//Render the in game menu and specific text
			inMenu.render(this, g2d, inX, inY);
			g2d.setColor(Color.red);
			if(confirmUse) g2d.drawString("Use this?", CENTERX, CENTERY);
		}
		if(state == STATE.TITLE) {
			//Render the title screen
			title.render(this, g2d, titleX, titleY, titleX2, titleY2);
		}
		
		if(state == STATE.HELP) {
			help.render(this, g2d, titleX, titleY, titleX2, titleY2);
		}
		
		//Render save time specific writing
		if(option == OPTION.SAVE){
			drawString(g2d, "Are you sure you\n      would like to save?", 660, 400);
		}
		if(wasSaving && wait > 0) {
			g2d.drawString("Game Saved!", 700, 500);
		}
	}
	
	/*******************************************************************
	 * The next four methods are inherited
	 * Currently these methods are not being used, but they have
	 * been set up to go off at specific times in a game as events.
	 * Actions that need to be done during these times can be added here.
	 ******************************************************************/
	void gameShutDown() {		
	}

	void spriteUpdate(AnimatedSprite sprite) {		
	}

	void spriteDraw(AnimatedSprite sprite) {		
	}

	void spriteDying(AnimatedSprite sprite) {		
	}

	/*************************************************************************
	 * @param AnimatedSprite
	 * @param AnimatedSprite
	 * @param int
	 * @param int
	 * 
	 * Inherited Method
	 * Handling for when a SPRITE contacts a SPRITE
	 * 
	 * hitDir is the hit found when colliding on a specific bounding box on spr1 and hitDir2
	 * is the same thing applied to spr2
	 * hitDir is short for hit direction which can give the data needed to move the colliding sprites
	 * hitDir is a number between and including 0 and 3, these assignments are taken care of in 'Game.java'.
	 * What hitDir is actually referring to is the specific hit box that is on a multi-box sprite.
	 *****************************************************************************/
	void spriteCollision(AnimatedSprite spr1, AnimatedSprite spr2, int hitDir, int hitDir2) {
		//Get the smallest possible overlap between the two problem sprites
		double leftOverlap = (spr1.getBoundX(hitDir) + spr1.getBoundSize() - spr2.getBoundX(hitDir2));
		double rightOverlap = (spr2.getBoundX(hitDir2) + spr2.getBoundSize() - spr1.getBoundX(hitDir));
		double topOverlap = (spr1.getBoundY(hitDir) + spr1.getBoundSize() - spr2.getBoundY(hitDir2));
		double botOverlap = (spr2.getBoundY(hitDir2) + spr2.getBoundSize() - spr1.getBoundY(hitDir));
		double smallestOverlap = Double.MAX_VALUE; 
		double shiftX = 0;
		double shiftY = 0;

		if(leftOverlap < smallestOverlap) { //Left
			smallestOverlap = leftOverlap;
			shiftX -= leftOverlap; 
			shiftY = 0;
		}
		if(rightOverlap < smallestOverlap){ //right
			smallestOverlap = rightOverlap;
			shiftX = rightOverlap;
			shiftY = 0;
		}
		if(topOverlap < smallestOverlap){ //up
			smallestOverlap = topOverlap;
			shiftX = 0;
			shiftY -= topOverlap;
		}
		if(botOverlap < smallestOverlap){ //down
			smallestOverlap = botOverlap;
			shiftX = 0;
			shiftY = botOverlap;
		}

		//Handling very specific collisions
		if(spr1.spriteType() == TYPE.PLAYER && state == STATE.GAME){
			if(spr2 instanceof Mob) ((Mob) spr2).stop();
			
			//This piece of code is commented out because I still need the capability of getting a tile from an xand y position
			/*if(((Mob) spr1).attacking() && currentOverlay.getFrontTile((Mob) spr1, playerX, playerY, CENTERX, CENTERY).getBounds().intersects(spr2.getBounds())){
				((Mob) spr2).takeDamage(25);
				//TODO: inside of take damage should be a number dependant on the current weapon equipped, change later
			}*/
			
			//Handle simple push back collision
			if(playerX != 0) playerX -= shiftX;
			if(playerY != 0) playerY -= shiftY;
			if(playerX == 0) mapX -= shiftX;
			if(playerY == 0) mapY -= shiftY;
		}
	}
	
	/***********************************************************************
	* @param AnimatedSprite
	* @param Tile
	* @param int
	* @param int
	* 
	* Inherited Method
	* Set handling for when a SPRITE contacts a TILE, this is handy for
	* dealing with Tiles which contain Events. When specifying a new
	* collision method, check for the type of sprite and whether a tile is
	* solid or breakable, both, or even if it contains an event. This is
	* mandatory because the AxohEngine finds details on collision and then 
	* returns it for specific handling by the user.
	* 
	* For more details on this method, refer to the spriteCollision method above
	*************************************************************************/
	void tileCollision(AnimatedSprite spr, Tile tile, int hitDir, int hitDir2) {
		Graphics2D g2d = graphics();
		double leftOverlap = (spr.getBoundX(hitDir) + spr.getBoundSize() - tile.getBoundX(hitDir2));
		double rightOverlap = (tile.getBoundX(hitDir2) + tile.getBoundSize() - spr.getBoundX(hitDir));
		double topOverlap = (spr.getBoundY(hitDir) + spr.getBoundSize() - tile.getBoundY(hitDir2));
		double botOverlap = (tile.getBoundY(hitDir2) + tile.getBoundSize() - spr.getBoundY(hitDir));
		double smallestOverlap = Double.MAX_VALUE; 
		double shiftX = 0;
		double shiftY = 0;

		if(leftOverlap < smallestOverlap) { //Left
			smallestOverlap = leftOverlap;
			shiftX -= leftOverlap; 
			shiftY = 0;
		}
		if(rightOverlap < smallestOverlap){ //right
			smallestOverlap = rightOverlap;
			shiftX = rightOverlap;
			shiftY = 0;
		}
		if(topOverlap < smallestOverlap){ //up
			smallestOverlap = topOverlap;
			shiftX = 0;
			shiftY -= topOverlap;
		}
		if(botOverlap < smallestOverlap){ //down
			smallestOverlap = botOverlap;
			shiftX = 0;
			shiftY = botOverlap;
		}
		
		//Deal with a tiles possible event property
		if(tile.hasEvent()){
			if(spr.spriteType() == TYPE.PLAYER) {
				//Warp Events(Doors)
				if(tile.event().getEventType() == TYPE.WARP) {
					tiles().clear();
					sprites().clear();
					sprites().add(playerMob);
					//Get the new map
					for(int i = 0; i < mapBase.maps.length; i++){
						 if(mapBase.getMap(i) == null) continue;
						 if(tile.event().getMapName() == mapBase.getMap(i).mapName()) currentMap = mapBase.getMap(i);
						 if(tile.event().getOverlayName() == mapBase.getMap(i).mapName()) currentOverlay = mapBase.getMap(i);
					}
					//Load in the new maps Tiles and Mobs
					for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++){
						addTile(currentMap.accessTile(i));
						addTile(currentOverlay.accessTile(i));
						if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
						if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
					}
					//Move the player to the new position
					mapX= tile.event().getNewMapX();
					mapY= tile.event().getNewMapY();
					playerX = tile.event().getNewX();
					playerY = tile.event().getNewY();
					inputWait = 10;
				}	
			} //end warp
			
			
			
			
			//Item exchange event
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.ITEM && keyAction){
				if((tile._name).equals("chest")) tile.setFrame(tile.getSpriteNumber() + 1); //Chests should have opened and closed version next to each other
				inMenu.addItem(tile.event().getItem()); //Add item to inventory
				tile.endEvent();
			}
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("s1")) {
				inputWait = 10;
				tile.setFrame(tile.getSpriteNumber() + s1i%2);
				s1i++;
				switch1=!switch1;
				//System.out.println("1" +switch1);
				}
			}
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("s2")) {
				inputWait = 10;
				tile.setFrame(tile.getSpriteNumber() + s2i%2);
				s2i++;
				switch2=!switch2;
				//System.out.println("2"+switch2);
				}
			}
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("s3")) {
				inputWait = 10;
				tile.setFrame(tile.getSpriteNumber() + s3i%2);
				s3i++;
				switch3=!switch3;
				//System.out.println("3"+switch3);
				}
			}
			
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("s4")) {
				inputWait = 10;
				tile.setFrame(tile.getSpriteNumber() + s4i%2);
				s4i++;
				switch4=!switch4;
				//System.out.println("4"+switch4);
				}
			}
			
			// Switch puzzle
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.DOOR){
				if(switch1==true&&switch2==false&&switch3==true&&switch4==true){
					tiles().clear();
					sprites().clear();
					sprites().add(playerMob);
					//Get the new map
					for(int i = 0; i < mapBase.maps.length; i++){
						 if(mapBase.getMap(i) == null) continue;
						 if(tile.event().getMapName() == mapBase.getMap(i).mapName()) currentMap = mapBase.getMap(i);
						 if(tile.event().getOverlayName() == mapBase.getMap(i).mapName()) currentOverlay = mapBase.getMap(i);
					}
					//Load in the new maps Tiles and Mobs
					for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++){
						addTile(currentMap.accessTile(i));
						addTile(currentOverlay.accessTile(i));
						if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
						if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
					}
					//Move the player to the new position
					mapX= tile.event().getNewMapX();
					mapY= tile.event().getNewMapY();
					playerX = tile.event().getNewX();
					playerY = tile.event().getNewY();
					inputWait = 10;
						}
					}
			
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("n1")) {
				tile.setFrame(tile.getSpriteNumber() + n1i%9);
				n1i++;
				//System.out.println("4"+switch4);
				}
			}
			
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("n2")) {
				tile.setFrame(tile.getSpriteNumber() + n2i%9);
				n2i++;
				//System.out.println("4"+switch4);
				}
			}
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("n3")) {
				tile.setFrame(tile.getSpriteNumber() + n3i%9);
				n3i++;
				//System.out.println("4"+switch4);
				}
			}
			
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.PUZZLE && keyAction){
				if((tile._name).equals("n4")) {
				tile.setFrame(tile.getSpriteNumber() + n4i%9);
				n4i++;
				//System.out.println("4"+switch4);
				}
			}
			
			//Number Puzzle
			else if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.DOOR){
				if(n1i==8&&n2i==4&&n3i==4&&n4i==9){
					tiles().clear();
					sprites().clear();
					sprites().add(playerMob);
					//Get the new map
					for(int i = 0; i < mapBase.maps.length; i++){
						 if(mapBase.getMap(i) == null) continue;
						 if(tile.event().getMapName() == mapBase.getMap(i).mapName()) currentMap = mapBase.getMap(i);
						 if(tile.event().getOverlayName() == mapBase.getMap(i).mapName()) currentOverlay = mapBase.getMap(i);
					}
					//Load in the new maps Tiles and Mobs
					for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++){
						addTile(currentMap.accessTile(i));
						addTile(currentOverlay.accessTile(i));
						if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
						if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
					}
					//Move the player to the new position
					mapX= tile.event().getNewMapX();
					mapY= tile.event().getNewMapY();
					playerX = tile.event().getNewX();
					playerY = tile.event().getNewY();
					inputWait = 10;
						}
					}
			

			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.ITEM && keyAction){
				tile.endEvent();
			}
			
			//handle the dialogue rendering events
			if(spr.spriteType() == TYPE.PLAYER && tile.event().getEventType() == TYPE.DIALOGUE){
				switch(tile.event().getName()){
				case "dialogue0": dialogue.setText(0); renderOn = true; break;
				case "dialogue1": dialogue.setText(1); renderOn = true; tile.endEvent(); break;
				case "dialogue2": dialogue.setText(2); renderOn = true; break;
				case "dialogue3": dialogue.setText(3); renderOn = true;  break;
				case "dialogue4": dialogue.setText(4); renderOn = true;  break;
				case "dialogue5": dialogue.setText(5); renderOn = true;  break;
				case "dialogue6": dialogue.setText(6); renderOn = true;  break;
				case "dialogue7": dialogue.setText(7); renderOn = true;  break;
				case "dialogue8": dialogue.setText(8); renderOn = true; tile.endEvent(); break;
				case "dialogue9": dialogue.setText(9); renderOn = true;  break;
				case "dialogue10": dialogue.setText(10); renderOn = true; tile.endEvent(); break; 
				case "dialogue11": dialogue.setText(11); renderOn = true; tile.endEvent(); break; 
				case "dialogue12": dialogue.setText(12); renderOn = true; tile.endEvent(); break;	
				case "dialogue13": dialogue.setText(13); renderOn = true; tile.endEvent(); break;	
				case "dialogue14": dialogue.setText(14); renderOn = true; tile.endEvent(); break; 
				case "dialogue15": dialogue.setText(15); renderOn = true; tile.endEvent(); break; 
				case "dialogue16": dialogue.setText(16); renderOn = true; tile.endEvent(); break; 
				case "dialogue17": dialogue.setText(17); renderOn = true; tile.endEvent(); break; 
				case "dialogue18": dialogue.setText(18); renderOn = true; tile.endEvent(); break; 
				case "dialogue19": dialogue.setText(19); renderOn = true; tile.endEvent(); break; 
				case "dialogue20": dialogue.setText(20); renderOn = true; tile.endEvent(); break; 
				case "dialogue21": dialogue.setText(21); renderOn = true; tile.endEvent(); break; 
				case "dialogue22": dialogue.setText(22); renderOn = true; tile.endEvent(); break; 
				case "dialogue23": dialogue.setText(23); renderOn = true; tile.endEvent(); break; 
				case "dialogue24": dialogue.setText(24); renderOn = true; tile.endEvent(); break; 
				case "dialogue25": dialogue.setText(25); renderOn = true; tile.endEvent(); break; 
				case "dialogue26": dialogue.setText(26); renderOn = true; tile.endEvent(); break; 
				case "dialogue27": dialogue.setText(27); renderOn = true; tile.endEvent(); break; 
				case "dialogue28": dialogue.setText(28); renderOn = true; tile.endEvent(); break; 
				case "dialogue29": dialogue.setText(29); renderOn = true; tile.endEvent(); break; 
				case "dialogue30": dialogue.setText(30); renderOn = true; tile.endEvent(); break; 
				case "dialogue31": dialogue.setText(31); renderOn = true; tile.endEvent(); break; 
				case "dialogue32": dialogue.setText(32); renderOn = true; tile.endEvent(); break; 
				case "dialogue33": dialogue.setText(33); renderOn = true; tile.endEvent(); break; 
				case "dialogue34": dialogue.setText(34); renderOn = true; tile.endEvent(); break; 
				case "dialogue35": dialogue.setText(35); renderOn = true; tile.endEvent(); break; 
				case "dialogue36": dialogue.setText(36); renderOn = true; tile.endEvent(); break; 
				case "dialogue37": dialogue.setText(37); renderOn = true; tile.endEvent(); break; 
				case "dialogue38": dialogue.setText(38); renderOn = true; tile.endEvent(); break; 
				case "dialogue39": dialogue.setText(39); renderOn = true; tile.endEvent(); break; 
				case "dialogue40": dialogue.setText(40); renderOn = true; tile.endEvent(); break; 
				case "dialogue41": dialogue.setText(41); renderOn = true; tile.endEvent(); break; 
				case "dialogue42": dialogue.setText(42); renderOn = true; tile.endEvent(); break; 
				case "dialogue43": dialogue.setText(43); renderOn = true; tile.endEvent(); break; 
				case "dialogue44": dialogue.setText(44); renderOn = true; tile.endEvent(); break; 
				case "dialogue45": dialogue.setText(45); renderOn = true; tile.endEvent(); break; 
				case "dialogue46": dialogue.setText(46); renderOn = true; tile.endEvent(); break; 
				case "dialogue47": dialogue.setText(47); renderOn = true; tile.endEvent(); break; 
				case "dialogue48": dialogue.setText(48); renderOn = true; tile.endEvent(); break; 
				case "dialogue49": dialogue.setText(49); renderOn = true; tile.endEvent(); break; 
				case "dialogue50": dialogue.setText(50); renderOn = true; tile.endEvent(); break; 
				}
			}
					
			
		}//end check events
		
		//If the tile is solid, move the player off of it and exit method immediately
		if(spr.spriteType() == TYPE.PLAYER && tile.solid() && state == STATE.GAME) {
			if(playerX != 0) playerX -= shiftX;
			if(playerY != 0) playerY -= shiftY;
			if(playerX == 0) mapX -= shiftX;
			if(playerY == 0) mapY -= shiftY;
			return;
		}
		//If an npc is intersecting a solid tile, move it off
		if(spr.spriteType() != TYPE.PLAYER && tile.solid() && state == STATE.GAME){
			if(spr instanceof Mob) {
				((Mob) spr).setLoc((int)shiftX, (int)shiftY);
				((Mob) spr).resetMovement();
			}
		}
	}//end tileCollision method
	
	/*****************************************************************
	 * @param int
	 * @param int
	 * 
	 *Method to call which moves the player. The player never moves apart from the map
	 *unless the player is at an edge of the generated map. Also, to simulate the movement
	 *of the space around the player like that, the X movement is flipped. 
	 *Which means to move right, you subtract from the X position.
	 ******************************************************************/
	void movePlayer(int xa, int ya) {
		if(xa > 0) {
			if(mapX + xa < currentMap.getMinX() && playerX < playerSpeed && playerX > -playerSpeed)
				mapX += xa;
			else playerX += xa; //left +#
		}
		if(xa < 0) {
			if(mapX + xa > currentMap.getMaxX(SCREENWIDTH) && playerX < playerSpeed && playerX > -playerSpeed)
			mapX += xa;
			else playerX += xa; //right -#
		}

		if(ya > 0) {
			if(mapY + ya < currentMap.getMinY() && playerY < playerSpeed && playerY > -playerSpeed)
			mapY += ya;
			else playerY += ya; //up +#
		}
		if(ya < 0) {
			if(mapY + ya > currentMap.getMaxY(SCREENHEIGHT) && playerY < playerSpeed && playerY > -playerSpeed)
			mapY += ya;
			else playerY += ya; //down -#
		}
	}
	
	/**********************************************************
	 * Main
	 * 
	 * @param args
	 * @throws IOException 
	 ********************************************************/
	public static void main(String[] args) throws IOException { new Judgement(); }
	
	/**********************************************************
	 * The Depths of Judgement Lies Below
	 * 
	 *             Key events - Mouse events
	 *                            
	 ***********************************************************/
	
	/****************************************************************
	 * Check specifically defined key presses which do various things
	 ****************************************************************/
	public void checkInput() {
		int xa = 0;
		int ya = 0;
		
		/********************************************
		 * Special actions for In Game
		 *******************************************/
		if(state == STATE.GAME && inputWait < 0) { 
			//A or left arrow(move left)
			if(keyShift){
				playerSpeed = 12;
			} else {
				playerSpeed = 5;
			}
			if(keyA) {
				xa = xa + 1 + playerSpeed;
				playerMob.updatePlayer(keyA, keyD, keyW, keyS);
			}
			//D or right arrow(move right)
			if(keyD) {
				xa = xa - 1 - playerSpeed;
				playerMob.updatePlayer(keyA, keyD, keyW, keyS);
			}
			//W or up arrow(move up)
			if(keyW) {
				ya = ya + 1 + playerSpeed;
				playerMob.updatePlayer(keyA, keyD, keyW, keyS);
			}
			//S or down arrow(move down)
			if(keyS) {
				ya = ya - 1 - playerSpeed;
				playerMob.updatePlayer(keyA, keyD, keyW, keyS);
			}
			
			//No keys are pressed
			if(!keyA && !keyD && !keyW && !keyS) {
				playerMob.updatePlayer(keyA, keyD, keyW, keyS);
			}
			movePlayer(xa, ya);
		
			//I(Inventory)
			if(keyInventory) {
				state = STATE.INGAMEMENU;
				inputWait = 10;
			}
			
			if(keyHelp){
				state = STATE.HELP;
			}
			
			//action button for dialogue
			//Up and Down arrow key controls
			if(keyUp && dArrowY > 690){
				dArrowY -= 50;
				inputWait = 5;
			}
			if(keyDown && dArrowY < 750){
				dArrowY += 50;
				inputWait = 5;
			}
			
			if(keyEnter){
				inputWait = 10;
				renderOn = false;
			}
			
			//SpaceBar(action button)
			if(keySpace) {
				playerMob.inOutItem();
				inputWait = 10;
			}
			// I also had to add keyEsc to the boolean where all the other keys were created
			/*if(keyEsc) {
				titleX = 530; 
				titleY = 610;
				titleX2 = 340;
				titleY2 = 310;
				titleLocation = 0;
				state = STATE.TITLE;
				inputWait = 10;
			}*/
		}//end in game choices
		
		/*****************************************
		 * Special actions for the Title Menu
		 *****************************************/
		if(state == STATE.TITLE && inputWait < 0){
		
			//For when no initial choice has been made
			if(option == OPTION.NONE){
				//S or down arrow(Change selection)
				if(keyS && titleLocation < 1) {
					titleX -= 105;
					titleY += 100;
					titleLocation++;
					inputWait = 5;
				}
				//W or up arrow(Chnage selection
				if(keyW && titleLocation > 0){
					titleX += 105;
					titleY -= 100;
					titleLocation--;
					inputWait = 5;
				}
				//Enter key(Make a choice)
				if(keyEnter) {
					if(titleLocation == 0){
						option = OPTION.NEWGAME;
						titleLocation = 0;
						inputWait = 10;
						keyEnter = false;
					}
					if(titleLocation == 1){
						option = OPTION.LOADGAME;
						titleLocation = 0;
						inputWait = 5;
						keyEnter = false;
					}
				}
			}//end option none
			
			//After choosing an option
			if(option == OPTION.NEWGAME || option == OPTION.LOADGAME){
				//Backspace(Exit choice)
				if(keyBack && !title.isGetName()){
					if(option == OPTION.NEWGAME) titleLocation = 0;
					if(option == OPTION.LOADGAME) titleLocation = 1;
					inputWait = 5;
					titleX2 = 340;
					titleY2 = 310;
					option = OPTION.NONE;
				}
				//S or down arrow(Change selection)
				if(keyS && titleLocation < 2 && !title.isGetName()) {
					titleLocation++;
					titleY2 += 160;
					inputWait = 7;
				}
				//W or up arrow(Change selection)
				if(keyW && titleLocation > 0 && !title.isGetName()) {
					titleLocation--;
					titleY2 -= 160;
					inputWait = 7;
				}
				//Enter key(Make a choice)
				if(keyEnter && !title.isGetName()) {
					if(option == OPTION.NEWGAME) {
						if(title.files() != null){ //Make sure the location of a new game is greater than previous ones(Not overwriting)
							if(title.files().length - 1 < titleLocation) {
								title.enter();
								titleX2 += 40;
								inputWait = 5;
							}
						}
						if(title.files() == null) { //Final check if there are no files made yet, to make the file somewhere
							title.enter();
							titleX2 += 40;
							inputWait = 5;
						}
					}
					//Load the currently selected file
					if(option == OPTION.LOADGAME) {
						currentFile = title.enter();
						if(currentFile != "") { //File is empty
							loadGame();
							inputWait = 5;
							option = OPTION.NONE;
							state = STATE.GAME;
							setGameState(STATE.GAME);
						}
					}
				}//end enter key
				
				//The following is for when a new file needs to be created - Typesetting
				if(title.isGetName() == true) {
					title.setFileName(currentChar);
					currentChar = '\0'; //null
					//Back space(Delete last character)
					if(keyBack) {
						title.deleteChar();
						inputWait = 5;
					}
					//Back space(exit name entry if name has no characters)
					if(keyBack && title.getFileName().length() == 0) {
						title.setGetName(false);
						titleX2 -= 40;
						inputWait = 5;
					}
					//Enter key(Write the file using the currently typed name and save it)
					if(keyEnter && title.getFileName().length() > 0) {
						save.newFile(title.getFileName());
						title.setGetName(false);
						currentFile = title.getFileName();
						state = STATE.GAME;
						option = OPTION.NONE;
						setGameState(STATE.GAME);
					}
				}//end get name
			}//end new/load option
		}//end title state
		
		//special actions for the help class
		if(state == STATE.HELP && inputWait < 0){
			if(keyBack){
				state = STATE.GAME;
				inLocation = 0;
				sectionLoc = 0;
				inY = 90;
				inputWait = 8;
			}
		}
		
		/******************************************
		 * Special actions for In Game Menu
		 ******************************************/
		if(state == STATE.INGAMEMENU && inputWait < 0) {
			//I(Close inventory)
			if(keyInventory) {
				state = STATE.GAME;
				option = OPTION.NONE;
				inLocation = 0;
				inY = 90;
				inputWait = 8;
			}
			//No option is chosen yet
			if(option == OPTION.NONE){ 
				if(wait == 0) wasSaving = false;
				//W or up arrow(Move selection)
				if(keyW) {
					if(inLocation > 0) {
						inY -= 108;
						inLocation--;
						inputWait = 10;
					}
				}
				//S or down arrow(move selection)
				if(keyS) {
					if(inLocation < 4) {
						inY += 108;
						inLocation++;
						inputWait = 10;
					}
				}
				//Enter key(Make a choice)
				if(keyEnter) {
					if(inLocation == 0){
						option = OPTION.ITEMS;
						inputWait = 5;
					}
					if(inLocation == 1){
						option = OPTION.EQUIPMENT;
						inputWait = 5;
					}
					if(inLocation == 2){
						option = OPTION.MAGIC;
						inputWait = 5;
					}
					if(inLocation == 3){
						option = OPTION.STATUS;
						inputWait = 5;
					}
					if(inLocation == 4){
						option = OPTION.SAVE;
						inputWait = 20;
					}
					keyEnter = false;
				}
			}
			
		
			
			//Set actions for specific choices in the menu
			//Items
			if(option == OPTION.ITEMS) {
				//W or up arrow(move selection)
				if(keyW){
					if(sectionLoc == 0) inMenu.loadOldItems();
					if(sectionLoc - 1 != -1) sectionLoc--;
					inputWait = 8;
				}
				//S or down arrow(move selection)
				if(keyS) {
					if(sectionLoc == 3) inMenu.loadNextItems();
					if(inMenu.getTotalItems() > sectionLoc + 1 && sectionLoc < 3) sectionLoc++;
					inputWait = 8;
				}
				//Enter key(Make a choice)
				if(keyEnter){
					if(confirmUse) {
						inMenu.useItem(); //then use item
						confirmUse = false;
						keyEnter = false;
					}
					if(inMenu.checkCount() > 0 && keyEnter) confirmUse = true;
					inputWait = 10;
				}
				//Back space(Go back on your last choice)
				if(keyBack) confirmUse = false;
			}
			
			//Equipment
			if(option == OPTION.EQUIPMENT) {
				//W or up arrow(move selection)
				if(keyW){
					if(sectionLoc == 0) inMenu.loadOldItems();
					if(sectionLoc - 1 != -1) sectionLoc--;
					inputWait = 8;
				}
				//S or down arrow(move selection)
				if(keyS) {
					if(sectionLoc == 3) inMenu.loadNextEquipment();
					if(inMenu.getTotalEquipment() > sectionLoc + 1 && sectionLoc < 3) sectionLoc++;
					inputWait = 8;
				}
			}
			
			//Saving
			if(option == OPTION.SAVE){
				//Key enter(Save the file)
				if(keyEnter){
					save.saveState(currentFile, data());
					inputWait = 20;
					wait = 200;
					waitOn = true;
					wasSaving = true;
					option = OPTION.NONE;
				}
			}
			
			//Backspace(if a choice has been made, this backs out of it)
			if(keyBack && option != OPTION.NONE) {
				option = OPTION.NONE;
				inMenu.setItemLoc(0);
				sectionLoc = 0;
				inputWait = 8;
				keyBack = false;
			}
			//Backspace(if a choice has not been made, this closes the inventory)
			if(keyBack && option == OPTION.NONE) {
				state = STATE.GAME;
				option = OPTION.NONE;
				inLocation = 0;
				sectionLoc = 0;
				inY = 90;
				inputWait = 8;
			}
		}
		inputWait--;
	}
	
	
	/**
	 * Inherited method
	 * @param keyCode
	 * 
	 * Set keys for a new game action here using a switch statement
	 * dont forget gameKeyUp
	 */
	void gameKeyDown(int keyCode) {
		switch(keyCode) {
	        case KeyEvent.VK_LEFT:
	            keyLeft = true;
	            break;
	        case KeyEvent.VK_A:
	        	keyA = true;
	        	break;
	        case KeyEvent.VK_RIGHT:
	            keyRight = true;
	            break;
	        case KeyEvent.VK_D:
	        	keyD = true;
	        	break;
	        case KeyEvent.VK_UP:
	            keyUp = true;
	            break;
	        case KeyEvent.VK_W:
	        	keyW = true;
	        	break;
	        case KeyEvent.VK_DOWN:
	            keyDown = true;
	            break;
	        case KeyEvent.VK_S:
	        	keyS = true;
	        	break;
	        case KeyEvent.VK_Q:
	        	keyInventory = true;
	        	break;
	        case KeyEvent.VK_E:
	        	keyAction = true;
	        	inputWait = 20;
	        	break;
	        case KeyEvent.VK_ENTER:
	        	keyEnter = true;
	        	break;
	        case KeyEvent.VK_BACK_SPACE:
	        	keyBack = true;
	        	break;
	        case KeyEvent.VK_SPACE:
	        	keySpace = true;
	        	break;
	        case KeyEvent.VK_SHIFT:
	        	keyShift = true;
	        	break;
	        case KeyEvent.VK_H:
	        	keyHelp = true;
	        	break;
	        case KeyEvent.VK_ESCAPE:
		       	 keyEsc = true;
		       	 break;
	        
        }
	}

	/**
	 * Inherited method
	 * @param keyCode
	 * 
	 * Set keys for a new game action here using a switch statement
	 * Dont forget gameKeyDown
	 */
	void gameKeyUp(int keyCode) {
		switch(keyCode) {
        case KeyEvent.VK_LEFT:
            keyLeft = false;
            break;
        case KeyEvent.VK_A:
        	keyA = false;
        	break;
        case KeyEvent.VK_RIGHT:
            keyRight = false;
            break;
        case KeyEvent.VK_D:
        	keyD = false;
        	break;
        case KeyEvent.VK_UP:
            keyUp = false;
            break;
        case KeyEvent.VK_W:
        	keyW = false;
        	break;
        case KeyEvent.VK_DOWN:
            keyDown = false;
            break;
        case KeyEvent.VK_S:
        	keyS = false;
        	break;
        case KeyEvent.VK_Q:
	    	keyInventory = false;
	    	break;
	    case KeyEvent.VK_E:
	    	keyAction = false;
	    	break;
	    case KeyEvent.VK_ENTER:
	    	keyEnter = false;
	    	break;
	    case KeyEvent.VK_BACK_SPACE:
	    	keyBack = false;
	    	break;
	    case KeyEvent.VK_SPACE:
	    	keySpace = false;
	    	break;
	    case KeyEvent.VK_SHIFT:
	    	keyShift = false;
	    	break;
	    case KeyEvent.VK_H:
	    	keyHelp = false;
	    	break;
	    case KeyEvent.VK_ESCAPE:
	       	 keyEsc = false;
	       	 break;
		}
	}

	/**
	 * Inherited method
	 * Currently unused
	 */
	void gameMouseDown() {	
	}

	/**
	 * Inherited method
	 * Currently if the game is running and the sword is out, the player attacks with it
	 */
	void gameMouseUp() {
		if(getMouseButtons(1) == true && playerMob.isTakenOut()) {
			playerMob.attack();
		}
	}

	/**
	 * Inherited Method
	 * Currently unused
	 */
	void gameMouseMove() {
	}
	 
	 //From the title screen, load a game file by having the super class get the data,
	 // then handling where the pieces of the data will be assigned here.
	/**
	 * Inherited Method
	 * 
	 * The title screen calls this method when a currently existing file is chosen
	 * Add new saved game details here as well as in the 'Data.java' class
	 * 
	 * Currently only the player x and y location and the current map is saved
	 */
	 void loadGame() {
		 if(currentFile != "") {
			 System.out.println("Loading...");
			 loadData(currentFile);
			 tiles().clear();
			 sprites().clear();
			 for(int i = 0; i < mapBase.maps.length; i++){
				 if(mapBase.getMap(i) == null) continue;
				 if(data().getMapName() == mapBase.getMap(i).mapName()) currentMap = mapBase.getMap(i);
				 if(data().getOverlayName() == mapBase.getMap(i).mapName()) currentOverlay = mapBase.getMap(i);
			 }
			 playerX = data().getPlayerX();
			 playerY = data().getPlayerY();
			 sprites().add(playerMob);
			 for(int i = 0; i < currentMap.getWidth() * currentMap.getHeight(); i++){
					addTile(currentMap.accessTile(i));
					addTile(currentOverlay.accessTile(i));
					if(currentMap.accessTile(i).hasMob()) sprites().add(currentMap.accessTile(i).mob());
					if(currentOverlay.accessTile(i).hasMob()) sprites().add(currentOverlay.accessTile(i).mob());
			}//end for
			System.out.println("Load Successful");
		 } //end file is not empty check
	 } //end load method
} //end class