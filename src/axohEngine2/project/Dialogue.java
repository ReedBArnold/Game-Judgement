package axohEngine2.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import axohEngine2.entities.AnimatedSprite;
import axohEngine2.entities.ImageEntity;

public class Dialogue {
	
	private OPTION _option;
	
	//Current file variables
	private String[] files;
	private File currFile;
	private String _fileName = "";
	
	//Possible images and choice options -- Futureke
	private ImageEntity image1;
	private ImageEntity _secondary;
	private AnimatedSprite _dialogueArrow;
	
	//Text fonts
	private Font _normal;
	private Font _bold;
	private Font _italics;
	
	//Colors
	Color boxB = new Color(0, 0, 0, 150);
	Color boxW = new Color(255, 255, 255, 100);
	
	//Box variables
	private int SCREENWIDTH;
	private int SCREENHEIGHT;
	private int boxHeightW;
	private int boxHeightB;
	private int location;
	
	//Arrow Variables
	private int _arrowY;
	private int _arrowX;
	
	//Text location variables
	private int msg1Y;
	private int msg2Y;
	private int msg3Y;
	private int msg4Y;
	
	//Test variables
	String text1 = "";
	String text2 = "";
	String text3 = "";
	String text4 = "";
	
	private int _eventNum = 0;
	
	/**
	 * Constructor for the dialogue box class
	 * 
	 * @param choiceArrow - animated sprite arrow (change font)
	 * @param screenWidth - int
	 * @param screenHeight - int
	 * @param simple - font
	 * @param bold - font
	 * @param italics - font
	 */
	public Dialogue(AnimatedSprite dialogueArrow, int screenWidth, int screenHeight, Font simple, Font bold, Font italics) {
		currFile = new File("C:/gamedata/saves/");
		_dialogueArrow = dialogueArrow;
		SCREENWIDTH = screenWidth;
		SCREENHEIGHT = screenHeight;
		boxHeightW = SCREENHEIGHT/4;
		boxHeightB = boxHeightW - 20;
		_normal = simple;
		_bold = bold;
		_italics = italics;
		_option = OPTION.NONE;
		
		msg1Y = SCREENHEIGHT - boxHeightB + 10;
		msg2Y = SCREENHEIGHT - boxHeightB*3/4 + 12;
		msg3Y = SCREENHEIGHT - boxHeightB*2/4 + 14;
		msg4Y = SCREENHEIGHT - boxHeightB*1/4 + 15;
	}
	
	public void setDialogue(String s1, String s2, String s3, String s4){
		text1 = s1;
		text2 = s2;
		text3 = s3;
		text4 = s4;
	}
	
	// Update method for game files
	public void update(OPTION option, int location) {
		_option = option;
		files = currFile.list();
		this.location = location;
	}
	
	
	// Method to render graphics
	public void render(JFrame frame, Graphics2D g2d, int arrowX, int arrowY) {	
		_arrowX = arrowX;
		_arrowY = arrowY;
		g2d.setColor(boxW);
		g2d.fillRoundRect(240, SCREENHEIGHT*3/4 - 10, SCREENWIDTH - 430, boxHeightW, 20, 20);
		g2d.setColor(boxB);
		g2d.fillRoundRect(250, (SCREENHEIGHT*3/4), SCREENWIDTH - 450, boxHeightB, 20, 20);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 22));
		g2d.drawString(text1, 300, msg1Y);
		g2d.setFont(new Font("Arial", Font.PLAIN, 22));
		g2d.drawString(text2, 500, msg2Y);
		g2d.drawString(text3, 500, msg3Y);
		g2d.drawString(text4, 500, msg4Y);
		
		g2d.drawImage(_dialogueArrow.getImage(), _arrowX, _arrowY, _dialogueArrow.getSpriteSize(), _dialogueArrow.getSpriteSize(), frame);
	}
	
	
	// Method to determine which event has just occurred
	public boolean eventCheck(){
		return false;
	}
	
	// Method to set Text based on the name of the dialogue
	public void setText(int eventNum){
		_eventNum = eventNum;
		switch(_eventNum){
		case 0 : text1 = "*My boat was destroyed during a storm! What do I do?*"; text2 = "Explore the island for parts?"; text3 = "Talk to the locals for advive?"; text4 = "Talk to yourself in the third person?"; break; //first intro dialogue opens on game render
		case 1 : text1 = "*How did I get here again?*"; text2 = "Oh look theres my boat!"; text3 = ""; text4 = ""; break;	//boat dock dialogue
		case 2 : text1 = "Samuel: I here of a magical woods where you may find what you're looking for."; text2 = "'Where is that?'"; text3 = "'Are there any boat parts there?'"; text4 = ""; break; //first npc interaction
		case 3 : text1 = "David: The woods you say? I once made it to a beautiful field there but thats not the end you seek."; text2 = "I remeber East"; text3 = "Then north!"; text4 = "or was it east...?"; break; //house1
		case 4 : text1 = "Craig: WOODS!? I know nothing of the subject!"; text2 = "......"; text3 = "You should leave at once."; text4 = "There's the door."; break;  //house2
		case 5 : text1 = "Mike: Yes I know of the end of the woods, it leads to a magic world where you might find help or tanks?"; text2 ="First find the cave. Next take the stream East!"; text3 = "Although no one has ever made it there and back in years.";  text4 = "Maybe they just dont want to?"; break;   //house3
		case 6 : text1 = "Nick: I don't think thats a good idea to go in there...but if you must."; text2 = "First head north."; text3 = "Then west"; text4 = "After that south or maybe north I cant remember exactly."; break; //house 4
		case 7 : text1 = "Jamie: Lotta dudes on this island...gets you wondering."; text2 = "'No Judgement man'"; text3 = "'Good vibes bro! See you around!'"; text4 = "'Pen island?'"; break; //jammin house5
		case 8 : text1 = "*I should go back and talk to everyone before I go.*"; text2 = "Just in case."; text3 = ""; text4 = ""; break; //lost woods before entrance
		case 9 : text1 = "                                 ==========|Deep Woods Entrance|=========="; text2 = "Professional explorers [ONLY]. Great dangers ahead!"; text3 = "'Signs are for wimps!'"; text4 = "'Tiny Rick'"; break; //lost woods sign
		case 10 : text1 = "*This is a strange area in a cave.*"; text2 =""; text3 = "This must be the field that old man was talkinga about!"; text4 = "Neat. I'm out."; break; //secret room dialogue
		case 11 : text1 = "*A CAVE AT LAST!*"; text2 = ""; text3 = "This better bring me closer to getting home or I swear to Hoff!"; text4 = ""; break; //in the caves
		case 12 : text1 = "*I hate the snow! This just took me farther away from the end.*"; text2 = "Do I go back?"; text3 = "carry on?"; text4 = "HEY look a penguin! Been a good day."; break; //in the ice zone
		case 13 : text1 = "Marvin: You lost stranger?"; text2 = "....am I tripping?"; text3 = "Can you help me get out mysterious talking penguin?"; text4 = "Please?"; break; //penguin1
		case 14 : text1 = "*What did those folks from town say again.*"; text2 = "North, west, west?"; text3 = "I think it was South for sure though."; text4 ="Maybe I should back and talk to them again."; break; //lw3o dialogue
		case 16 : text1 = "Dan: Just in time to celebrate my 84th year living on this island!"; text2 = "I mean 49th!...."; text3 = ".........."; text4 = "....I think?"; break; //number clue
		case 17 : text1 = "Mark: Hi there my names mark! Searching for a way out?"; text2 = "There's a room in the big cave. Duh man."; text3 = "My mom doesnt let me near that place."; text4 = "She said never touch the top right lever....but I did."; break; //Penguin 2
		case 18 : text1 = "Mom: Dont go near that cave! Dont let Mark near there either."; text2 = "'What's in there?'"; text3 = "A bunch of levers it makes no sense."; text4 = "'Do they get me out of this strange place?'"; break; //Penguin 3
		case 19 : text1 = "Danielle: I'm the only other girl penguin here, kinda sucks."; text2 = "If you run into Jermaine will you say 'Hi' for me?"; text3 = "We're madly in love but he's avoiding me lately it's strange."; text4 = "Real strange..."; break; //Penguin 4
		case 20 : text1 = "Jermaine: Yeah Danielle's crazy stay away from here man. What you need?"; text2 = "'What are these levers?'"; text3 = "I heard they lead to a way out of this place."; text4 = "'Why is it snowing actually?'"; break; //Penguin 5
		case 21 : text1 = "Rick: I'm a tiny penguin! Hit those levers to get out of this frozen wasteland."; text2 = "I'm not sure"; text3 = ""; text4 = ""; break; //Penguin 6
		case 22 : text1 = "*That's strange now it's a desert on this island.*"; text2 = "Am I dreaming this whole thing?"; text3 = "Am I in a video game?"; text4 = "Will I finally get out of this place?"; break; // Entrance to sand
		case 23 : text1 = "*What are those magic floating numbers doing?*"; text2 = "Oh no I was supposed to remember numbers too?"; text3 = "The guy from a beach house said some numbers..."; text4 = "...was it the bottom or middle house guy?"; break; // Pyramid entrance
		case 24 : text1 = "*Now where am!?*"; text2 = "Dont you dare tell me its the end of the game?"; text3 = "I spent my time playing this?"; text4 = "Ohhhhh the sequel will be out in 2042 so stay tuned!"; break; //City end
		case 25 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 26 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 27 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 28 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 29 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 30 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 31 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 32 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 33 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 34 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 35 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 36 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 37 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 38 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 39 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 40 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 41 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 42 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 43 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 44 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 45 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 46 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 47 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 48 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 49 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		case 50 : text1 = ""; text2 = ""; text3 = ""; text4 = ""; break;
		}

	}
	
	void drawString(Graphics2D g2d, String text, int x, int y) {
       for (String line : text.split("\n"))
           g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
	}
}
