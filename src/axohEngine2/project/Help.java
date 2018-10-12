package axohEngine2.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFrame;

import axohEngine2.entities.AnimatedSprite;
import axohEngine2.entities.ImageEntity;

public class Help {

	
	private String[] files;
	private File existingFiles;
	private int location;
	private String _fileName = "";
	//private boolean getName = false;
	
	//_mainImage - The initial background image of the title screen
	//_secondary - The image which appear after choosing load game
	//titleArrow - The AnimatedSprite which indicates which option the user is currently hovering over
	//_option - A choice the player might make like load or newGame or delete
	private ImageEntity _mainImage;
	private ImageEntity _secondary;
	private AnimatedSprite _titleArrow;
	private OPTION _option;
	
	//Fonts to be used to display text, variouse ones for various uses
	private Font _simple;
	private Font _bold;
	private Font _bigBold;
	
	//SCREENWIDTH, SCREENHEIGHT - width and height of the game JFrame window in pixels
	private int SCREENWIDTH;
	private int SCREENHEIGHT;
	
	public Help() {}
	
	public Help(ImageEntity mainImage, ImageEntity secondary,AnimatedSprite titleArrow, int screenWidth, int screenHeight, Font simple, Font bold, Font bigBold) {
		existingFiles = new File("C:/gamedata/saves/");
		_mainImage = mainImage;
		_secondary = secondary;
		_titleArrow = titleArrow;
		SCREENWIDTH = screenWidth;
		SCREENHEIGHT = screenHeight;
		_simple = simple;
		_bold = bold;
		_bigBold = bigBold;
		_option = OPTION.NONE;
	}
	
	public void update(OPTION option, int location) {
		_option = option;
		files = existingFiles.list();
		this.location = location;
	}
	
	
	public void render(JFrame frame, Graphics2D g2d, int titleX, int titleY, int titleX2, int titleY2) {
		g2d.setColor(Color.BLUE);
	
		g2d.drawString("HELP SCREEN", 375, 150);
		g2d.drawString("WASD to move", 300, 250);
	//	g2d.drawString("S to move South",300, 350);
		//g2d.drawString("A to move West", 300, 450);
		//g2d.drawString("D to move East", 300, 550);
		//g2d.drawString("Q to open the main menu", 300, 650);
		g2d.drawString("E to open doors/chests",300, 350);
		g2d.drawString("Your goal is to find the", 300, 450);
		//g2d.drawString("Legendary PC and ", 300, 550);
		//g2d.drawString("play tank wars!", 300, 650);
	}

	void drawString(Graphics2D g2d, String text, int x, int y) {
       for (String line : text.split("\n"))
           g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
	}
}
