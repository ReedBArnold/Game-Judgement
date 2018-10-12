

/****************************************************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: July 5, 2015
 * 
 * Title: Map Database
 * Description: A data handling class used for large projects. This class contains all of the spritesheets,
 * tiles, events, items, mobs and map creations since they all interlock together.
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 ****************************************************************************************************/
//Package
package axohEngine2.project;

//Imports
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import axohEngine2.entities.Mob;
import axohEngine2.entities.SpriteSheet;
import axohEngine2.map.Event;
import axohEngine2.map.Map;
import axohEngine2.map.Tile;

public class MapDatabase {
	
	//SpriteSheets
	SpriteSheet misc;
	SpriteSheet largeObjects;
	SpriteSheet largeObjects2;
	SpriteSheet largeObjects3;
	SpriteSheet largeObjects4;
	SpriteSheet environment32;
	SpriteSheet extras2;
	SpriteSheet mainCharacter;
	boolean bool;
	
	//Maps
	Map island1, island1o, lw1, lw1o,lw2,lw2o,lw3o,lw4o,lw5o,lw6,lw6o,lw7o,lw8o,lw9o,lw10o,lw10,houses,houses1o,houses2o,houses3o,houses4o,houses5o,houses6o,
	ice1,ice1o,ice2,ice2o,igloo1,igloo1o,igloo2o,icesandroad,icesandroado,sand1,sand1o,sand2,sand2o,city,cityO;
	
	//Tiles - Names are defined in the constructor for better identification
	Tile d, g, f, b, r, e, ro, h, hf, c, w1, w2, fl,t, s, sn, hw, dc, si,c1,c2, ts, py, br,ca,bl,bc,sb,s1,s2,s3,s4,so,dr,ig,bp,bs,n1,n2,n3,n4,cn,pe,ib,ss;
	//Events
	
	//warp events
	Event warp1,warp2,warp3,warp4,warp5,warp6,warp7,warp8,warp9,warp10,warp11,warp12,warp13,warp14,
	warp15,warp16,warp17,warp18,warp19,warp20,warp21,warp22,warp23,warp24,warp25,warp26,warp27,warp28,
	warp29,warp30,warp31,warp32,warp33,warp34,warp35,warp36,warp37,warp38,warp39,warp40,warp41,warp42,
	warp43,warp44,warp45,warp46,warp47,warp48,warp49,warp50;
	
	//potion events
	Event getPotion, getMpotion;
	
	//diaogue events
	Event dialogue0, dialogue1, dialogue2, dialogue3, dialogue4, dialogue5, dialogue6, dialogue7, dialogue8, dialogue9, dialogue10, dialogue11, dialogue12, dialogue13, dialogue14,
	dialogue15, dialogue16, dialogue17, dialogue18, dialogue19, dialogue20, dialogue21, dialogue22, dialogue23, dialogue24, dialogue25, dialogue26, dialogue27, dialogue28, dialogue29,
	dialogue30, dialogue31, dialogue32, dialogue33, dialogue34, dialogue35, dialogue36, dialogue37, dialogue38,dialogue39, dialogue40, dialogue41, dialogue42, dialogue43, dialogue44, dialogue45,
	dialogue46, dialogue47, dialogue48, dialogue49, dialogue50;
	
	//puzzle events
	Event puzzle1, puzzle2,puzzle3,puzzle4,puzzle5,puzzle6,puzzle7,puzzle8,puzzle9,puzzle10;
	
	//Items
	Item potion;
	Item mpotion;
	
	//NPC's and Monsters
	Mob npc, npc1, npc2, npc3, npc4, npc5, npc6, npc7;
	
	//Array of maps
	public Map[] maps;
	
	/****************************************************************
	 * Constructor
	 * Instantiate all variables for the game
	 * 
	 * @param frame - JFrame Window for the map to be displayed on
	 * @param g2d - Graphics2D object needed to display images
	 * @param scale - Number to be multiplied by each image for correct on screen display
	 * @throws IOException 
	 *******************************************************************/
	public MapDatabase(JFrame frame, Graphics2D g2d, int scale) throws IOException {
		//Currently a maximum of 200 maps possible(Can be changed if needed)
		maps = new Map[200];
		
		//Set up spriteSheets
		misc = new SpriteSheet("/textures/environments/environments5.png", 16, 16, 16, scale);
		largeObjects = new SpriteSheet("/textures/environments/4x4s.png", 4, 4, 64, scale);
		environment32 = new SpriteSheet("/textures/environments/32SizeEnvironment.png", 8, 8, 32,scale);
		extras2 = new SpriteSheet("/textures/environments/extras2.png", 16, 16, 16, scale);
		mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, scale);
		largeObjects2 = new SpriteSheet("/textures/environments/4x4buildings.png", 4, 4, 64, scale);
		largeObjects3 = new SpriteSheet("/textures/environments/4x4s.png", 2, 2, 128, scale);
		largeObjects4 = new SpriteSheet("/textures/environments/4x4s2.png", 2, 2, 128, scale);
		//Set up tile blueprints and if they are animating
		d = new Tile(frame, g2d, "door", environment32, 0);
		g = new Tile(frame, g2d, "grass", misc, 17);
		bc = new Tile(frame, g2d, "bricks", misc, 12, true);
		r = new Tile(frame, g2d, "walkWay", misc, 18);
		e = new Tile(frame, g2d, "empty", misc, 48);
		ro = new Tile(frame, g2d, "rock", misc, 2);
		fl = new Tile(frame, g2d, "flower", misc, 1);
		w1 = new Tile(frame, g2d, "water1", misc, 16,true);
		w2 = new Tile(frame, g2d, "water2", misc, 9,true);
		h = new Tile(frame, g2d, "house", largeObjects, 0, true);
		hf = new Tile(frame, g2d, "floor", misc, 6);
		c = new Tile(frame, g2d, "chest", extras2, 0, true);
		t = new Tile (frame, g2d, "tree", largeObjects, 1, true);
		sn = new Tile(frame, g2d, "snow", misc, 19);
		ss = new Tile(frame, g2d, "snowsand", misc, 29);
		s = new Tile(frame, g2d, "sand", misc, 5);
		hw = new Tile(frame,g2d, "housewall", misc , 12, true);
		dc = new Tile(frame, g2d, "dock", misc, 11);
		b = new Tile(frame, g2d, "boat",largeObjects, 6, true);
		ts = new Tile (frame, g2d, "snowyTree", largeObjects, 3, true);
		py = new Tile (frame, g2d, "pyrimid", largeObjects, 2, true);
		si = new Tile(frame, g2d, "sign", misc, 23, true);
		br = new Tile(frame, g2d, "brige", misc, 7);
		ca = new Tile(frame, g2d, "cave",largeObjects, 11 ,true);
		bl = new Tile(frame, g2d, "blacktile", misc, 24);
		sb = new Tile(frame, g2d, "snowbricks", misc, 12, true);
		s1 = new Tile(frame, g2d, "s1", misc, 21, true);
		so = new Tile(frame, g2d, "switchopen", misc, 22, true);
		s2 = new Tile(frame, g2d, "s2", misc, 21, true);
		s3 = new Tile(frame, g2d, "s3", misc, 21, true);
		s4 = new Tile(frame, g2d, "s4", misc, 21, true);
		dr = new Tile(frame, g2d, "door2", environment32, 0, true);
		n1 = new Tile(frame,g2d,"n1",extras2,3,true);
		n2 = new Tile(frame,g2d,"n2",extras2,3,true);
		n3 = new Tile(frame,g2d,"n3",extras2,3,true);
		n4 = new Tile(frame,g2d,"n4",extras2,3,true);
		cn = new Tile(frame,g2d,"canoooh",largeObjects,13,true);
		bp = new Tile(frame,g2d,"big pryamid",largeObjects4,0,true);
		bs = new Tile(frame, g2d, "sandbricks", misc, 28, true);
		pe = new Tile(frame,g2d,"penguin",largeObjects,8);
		ig = new Tile(frame,g2d,"icecave",largeObjects,9);
		ib = new Tile(frame,g2d,"igloobig",largeObjects3,3,true);
		c1 = new Tile(frame,g2d,"cactus1",largeObjects2,1);
		c2 = new Tile(frame,g2d,"cactus2",largeObjects2,2);
		//Non-Map Events
		potion = new Item(frame, g2d, extras2, 2, "Potion", false);
		potion.setHealItem(25, false, "");
		mpotion = new Item(frame, g2d, extras2, 2, "Mega Potion", false);
		potion.setHealItem(50, false, "");
		
		//Item events
		getPotion = new Event("getPotion", TYPE.ITEM);
		getPotion.setItem(potion);
		getMpotion = new Event("getMpotion", TYPE.ITEM);
		getMpotion.setItem(mpotion);
		
		//Map Making		
		Tile[] island1Tiles = new Tile[1600];
		Tile[] island1oTiles = new Tile[1600];
		Tile[] cityTiles = new Tile[1600];
		Tile[] cityOTiles = new Tile[1600];
		//Lost woods maps
		Tile[] lw1Tiles = new Tile[350];
		Tile[] lw1oTiles = new Tile[350];
		Tile[] lw2Tiles = new Tile[350];
		Tile[] lw2oTiles = new Tile[350];
		Tile[] lw6Tiles = new Tile[375];		
		Tile[] lw6oTiles = new Tile[375];
		Tile[] lw3oTiles = new Tile[350];
		Tile[] lw4oTiles = new Tile[350];
		Tile[] lw5oTiles = new Tile[350];
		Tile[] lw7oTiles = new Tile[350];
		Tile[] lw8oTiles = new Tile[350];
		Tile[] lw9oTiles = new Tile[350];
		Tile[] lw10oTiles = new Tile[350];
		Tile[] lw10Tiles = new Tile[350];
		//houses maps
		Tile[] houseTiles = new Tile[100];
		Tile[] house1oTiles = new Tile[100];
		Tile[] house2oTiles = new Tile[100];
		Tile[] house3oTiles = new Tile[100];
		Tile[] house4oTiles = new Tile[100];
		Tile[] house5oTiles = new Tile[100];
		Tile[] house6oTiles = new Tile[100];
		Tile[] ice1oTiles = new Tile[350];
		Tile[] ice1Tiles = new Tile[350];
		Tile[] ice2Tiles = new Tile[1600];
		Tile[] ice2oTiles = new Tile[1600];
		Tile[] igloo1Tiles = new Tile[350];
		Tile[] igloo1oTiles = new Tile[350];
		Tile[] igloo2oTiles = new Tile[350];
		Tile[] icesandroadTiles = new Tile[400];
		Tile[] icesandroadoTiles = new Tile[400];
		Tile[] sand1Tiles = new Tile[1600];
		Tile[] sand1oTiles = new Tile[1600];
		Tile[] sand2Tiles = new Tile[350];
		Tile[] sand2oTiles = new Tile[350];
		
		mapFromFile("maps/Final Game Maps/island1.txt/",island1Tiles,1600);
		mapFromFile("maps/Final Game Maps/island1o.txt/",island1oTiles,1600);
		mapFromFile("maps/Final Game Maps/city.txt/",cityTiles,1600);
		mapFromFile("maps/Final Game Maps/cityO.txt/",cityOTiles,1600);
		mapFromFile("maps/Final Game Maps/lw1.txt/",lw1Tiles,350);
		mapFromFile("maps/Final Game Maps/lw1o.txt/",lw1oTiles,350);
		mapFromFile("maps/Final Game Maps/lw2.txt/",lw2Tiles,350);
		mapFromFile("maps/Final Game Maps/lw2o.txt/",lw2oTiles,350);
		mapFromFile("maps/Final Game Maps/lw3o.txt/",lw3oTiles,350);
		mapFromFile("maps/Final Game Maps/lw4o.txt/",lw4oTiles,350);
		mapFromFile("maps/Final Game Maps/lw5o.txt/",lw5oTiles,350);
		mapFromFile("maps/Final Game Maps/lw7o.txt/",lw7oTiles,350);
		mapFromFile("maps/Final Game Maps/lw8o.txt/",lw8oTiles,350);
		mapFromFile("maps/Final Game Maps/lw9o.txt/",lw9oTiles,350);
		mapFromFile("maps/Final Game Maps/lw10o.txt/",lw10oTiles,350);
		mapFromFile("maps/Final Game Maps/lw6.txt/",lw6Tiles,375);
		mapFromFile("maps/Final Game Maps/lw6o.txt/",lw6oTiles,375);
		mapFromFile("maps/Final Game Maps/lw10.txt/",lw10Tiles,350);
		mapFromFile("maps/Final Game Maps/houses.txt/",houseTiles,100);
		mapFromFile("maps/Final Game Maps/houses1o.txt/",house1oTiles,100);
		mapFromFile("maps/Final Game Maps/houses2o.txt/",house2oTiles,100);
		mapFromFile("maps/Final Game Maps/houses3o.txt/",house3oTiles,100);
		mapFromFile("maps/Final Game Maps/houses4o.txt/",house4oTiles,100);
		mapFromFile("maps/Final Game Maps/houses5o.txt/",house5oTiles,100);
		mapFromFile("maps/Final Game Maps/houses6o.txt/",house6oTiles,100);
		mapFromFile("maps/Final Game Maps/ice1.txt/",ice1Tiles,350);
		mapFromFile("maps/Final Game Maps/ice1o.txt/",ice1oTiles,350);
		mapFromFile("maps/Final Game Maps/ice2.txt/",ice2Tiles,1600);
		mapFromFile("maps/Final Game Maps/ice2o.txt/",ice2oTiles,1600);
		mapFromFile("maps/Final Game Maps/igloo1.txt/",igloo1Tiles,350);
		mapFromFile("maps/Final Game Maps/igloo1o.txt/",igloo1oTiles,350);
		mapFromFile("maps/Final Game Maps/igloo2o.txt/",igloo2oTiles,350);
		mapFromFile("maps/Final Game Maps/icesandroad.txt/",icesandroadTiles,400);
		mapFromFile("maps/Final Game Maps/icesandroado.txt/",icesandroadoTiles,400);
		mapFromFile("maps/Final Game Maps/sand1.txt/",sand1Tiles,1600);
		mapFromFile("maps/Final Game Maps/sand1o.txt/",sand1oTiles,1600);
		mapFromFile("maps/Final Game Maps/sand2.txt/",sand2Tiles,350);
		mapFromFile("maps/Final Game Maps/sand2o.txt/",sand2oTiles,350);
		//Put the maps together and add them to the array of possible maps
		island1 = new Map(frame, g2d, island1Tiles, 40, 40, "island1");
		maps[0] = island1;
		island1o = new Map(frame, g2d, island1oTiles, 40, 40, "island1o");
		maps[1] = island1o;
		lw1 = new Map(frame, g2d, lw1Tiles, 25, 14, "lw1");
		maps[2] = lw1;
		lw1o = new Map(frame, g2d, lw1oTiles, 25, 14, "lw1o");
		maps[3] = lw1o;
		lw2 = new Map(frame, g2d, lw2Tiles, 25, 14, "lw2");
		maps[4] = lw2;
		lw2o = new Map(frame, g2d, lw2oTiles, 25, 14, "lw2o");
		maps[5] = lw2o;
		lw6o = new Map(frame, g2d, lw6oTiles, 25, 15, "lw6o");
		lw6  = new Map(frame, g2d, lw6Tiles, 25, 15, "lw6");
		lw3o = new Map(frame, g2d, lw3oTiles, 25, 14, "lw3o");
		lw4o = new Map(frame, g2d, lw4oTiles, 25, 14, "lw4o");
		lw5o = new Map(frame, g2d, lw5oTiles, 25, 14, "lw5o");
		lw7o = new Map(frame, g2d, lw7oTiles, 25, 14, "lw7o");
		lw8o = new Map(frame, g2d, lw8oTiles, 25, 14, "lw8o");
		lw9o = new Map(frame, g2d, lw9oTiles, 25, 14, "lw9o");
		lw10o= new Map(frame, g2d, lw10oTiles, 25, 14, "lw10o");
		lw10= new Map(frame, g2d, lw10Tiles, 25, 14, "lw10");
		maps[6] = lw3o;
		maps[7] = lw4o;
		maps[8] = lw5o;
		maps[9] = lw6;
		maps[10] = lw7o;
		maps[11] = lw8o;
		maps[12] = lw9o;
		maps[13] = lw10o;
		maps[14] = lw6o;
		maps[15] = lw10;
		houses = new Map(frame, g2d, houseTiles, 10, 10, "houses");
		maps[16] = houses;
		houses1o = new Map(frame, g2d, house1oTiles, 10, 10, "houses1o");
		maps[17] = houses1o;
		houses2o = new Map(frame, g2d, house2oTiles, 10, 10, "houses2o");
		maps[18] = houses2o;
		houses3o = new Map(frame, g2d, house3oTiles, 10, 10, "houses3o");
		maps[19] = houses3o;
		houses4o = new Map(frame, g2d, house4oTiles, 10, 10, "houses4o");
		maps[20] = houses4o;
		houses5o = new Map(frame, g2d, house5oTiles, 10, 10, "houses5o");
		maps[21] = houses5o;
		houses6o = new Map(frame, g2d, house6oTiles, 10, 10, "houses6o");
		maps[22] = houses6o;
		ice1 = new Map(frame, g2d, ice1Tiles, 25, 14, "ice1");
		maps[23] = ice1;
		ice1o = new Map(frame, g2d, ice1oTiles, 25, 14, "ice1o");
		maps[24] = ice1o;
		ice2 = new Map(frame, g2d, ice2Tiles, 40, 40, "ice2");
		maps[25] = ice2;
		ice2o = new Map(frame, g2d, ice2oTiles, 40, 40, "ice2o");
		maps[26] = ice2o;
		igloo1= new Map(frame, g2d, igloo1Tiles, 25, 14, "igloo1");
		maps[27] = igloo1;
		igloo1o= new Map(frame, g2d, igloo1oTiles, 25, 14, "igloo1o");
		maps[28] = igloo1o;
		igloo2o= new Map(frame, g2d, igloo2oTiles, 25, 14, "igloo2o");
		maps[29] = igloo2o;
		icesandroad= new Map(frame, g2d, icesandroadTiles, 10, 40, "icesandroad");
		maps[30] = icesandroad;
		icesandroado= new Map(frame, g2d, icesandroadoTiles, 10, 40, "icesandroado");
		maps[31] = icesandroado;
		sand1= new Map(frame, g2d, sand1Tiles, 40, 40, "sand1");
		maps[32] = sand1;
		sand1o= new Map(frame, g2d, sand1oTiles, 40, 40, "sand1o");
		maps[33] = sand1o;
		sand2= new Map(frame, g2d, sand2Tiles, 25, 14, "sand2");
		maps[34] = sand2;
		sand2o= new Map(frame, g2d, sand2oTiles, 25, 14, "sand2o");
		maps[35] = sand2o;
		city = new Map(frame, g2d, cityTiles, 40, 40, "city");
		maps[36] = city;
		cityO = new Map(frame, g2d, cityOTiles, 40, 40, "cityO");
		maps[37] = cityO;
		//Warping events
		warp1 = new Event("lostwoods start", TYPE.WARP);
		warp1.setWarp("lw1", "lw1o", 26, -245, 0, 0);
		warp2 = new Event("lostwoods to island", TYPE.WARP);//warp from lost woods - island
		warp2.setWarp("island1", "island1o",-528, -1, -950, -458);
		warp3 = new Event("lostwoods 1-2", TYPE.WARP);//warp from room one to room two in lost woods
		warp3.setWarp("lw2", "lw2o", -575, -35, 0,0);//RIGHT
		warp4 = new Event("lostwoods 1-3", TYPE.WARP);//warp from 1 - 3 in lost woods
		warp4.setWarp("lw2", "lw3o", 26, -245, 0,0);//BOTTOM
		warp22 = new Event("lostwoods 2-1", TYPE.WARP);//warp from 2 - 1 in lost woods
		warp22.setWarp("lw1", "lw1o", 654, -23, 0,0);//LEFT
		warp23 = new Event("lostwoods 3-1", TYPE.WARP);//warp from 3 - 1 in lost woods
		warp23.setWarp("lw1", "lw1o", 30, 385, 0,0);//TOP
		warp6 = new Event("lostwoods 3-4", TYPE.WARP);//warp from 3 - 4 in lost woods
		warp6.setWarp("lw2", "lw4o", -575, -35, 0,0);
		warp7 = new Event("lostwoods 3-3", TYPE.WARP);//warp from 3 - 3 in lost woods
		warp7.setWarp("lw2", "lw3o", 26, -245, 0,0);
		warp8 = new Event("lostwoods 3-2", TYPE.WARP);//warp from 3 - 2 in lost woods
		warp8.setWarp("lw2", "lw2o", 654, -23, 0,0);
		warp9 = new Event("lostwoods 4-5", TYPE.WARP);//warp from 4 - 5 in lost woods
		warp9.setWarp("lw2", "lw5o", 26, -245, 0,0);	
		warp10 = new Event("lostwoods 4-3", TYPE.WARP);//warp from 4 - 3 in lost woods
		warp10.setWarp("lw2", "lw3o", 654, -23, 0,0);
		warp11 = new Event("lostwoods 5-SS", TYPE.WARP);//warp from 5 - Secret room(6) in lost woods
		warp11.setWarp("lw6", "lw6o", 26, -245, 0,0);
		warp12 = new Event("lostwoods 5-4", TYPE.WARP);//warp from 5 - 4 in lost woods
		warp12.setWarp("lw2", "lw4o", 30, 385, 0,0);
		warp13 = new Event("lostwoods 5-7", TYPE.WARP);//warp from 5 - 7 in lost woods
		warp13.setWarp("lw2", "lw7o", 654, -23, 0,0);
		warp14 = new Event("lostwoods 6-3", TYPE.WARP);//warp from 6 - 3 in lost woods
		warp14.setWarp("lw2", "lw3o", 30, 385, 0,0);
		warp15 = new Event("lostwoods 6-5", TYPE.WARP);//warp from 6 - 5 in lost woods
		warp15.setWarp("lw2", "lw5o", 654, -23, 0,0);
		warp16 = new Event("lostwoods 7-3", TYPE.WARP);//warp from 7 - 3 in lost woods
		warp16.setWarp("lw2", "lw3o", 30, 385, 0,0);
		warp17 = new Event("lostwoods 7-8", TYPE.WARP);//warp from 7 - 8 in lost woods
		warp17.setWarp("lw2", "lw8o", 26, -245, 0,0);
		warp18 = new Event("lostwoods 7-5", TYPE.WARP);//warp from 7 - 5 in lost woods
		warp18.setWarp("lw2", "lw5o", -575, -35, 0,0);
		warp19 = new Event("lostwoods 8-4", TYPE.WARP);//warp from 8 - 4 in lost woods
		warp19.setWarp("lw2", "lw4o", -575, -35, 0,0);
		warp20 = new Event("lostwoods 8-6", TYPE.WARP);//warp from 8 - 6 in lost woods
		warp20.setWarp("lw2", "lw6o", 26, -245, 0,0);
		warp21 = new Event("lostwoods 8-9", TYPE.WARP);//warp from 8 - 9 in lost woods
		warp21.setWarp("lw2", "lw9o", 26, -53, 0,0);
		warp22 = new Event("lostwoods 9-10", TYPE.WARP);//warp from 9 - 10 in lost woods
		warp22.setWarp("lw10", "lw10o", 26, -53, 0,0);
		warp25 = new Event("toHouse1", TYPE.WARP);
		warp25.setWarp("houses", "houses1o", 66, -100, 480, 150);
		warp26 = new Event("houses1 to island", TYPE.WARP);
		warp26.setWarp("island1", "island1o",2, -7, -289, -288);
		warp27 = new Event("houses2 to island", TYPE.WARP);
		warp27.setWarp("island1", "island1o",2, -7, -864, -288);
		warp28 = new Event("toHouse2", TYPE.WARP);
		warp28.setWarp("houses", "houses2o", 66, -100, 480, 150);
		warp29 = new Event("toHouse3", TYPE.WARP);
		warp29.setWarp("houses", "houses3o", 66, -100, 480, 150);
		warp30 = new Event("houses3 to island", TYPE.WARP);
		warp30.setWarp("island1", "island1o", -172, -7, -954, -288);		
		warp31 = new Event("houses4 to island", TYPE.WARP);
		warp31.setWarp("island1", "island1o",1, -7, -298, -938);
		warp32 = new Event("houses5 to island", TYPE.WARP);
		warp32.setWarp("island1", "island1o", -162, -36, -958, -899);
		warp33 = new Event("houses6 to island", TYPE.WARP);
		warp33.setWarp("island1", "island1o",-2, -200, -671, -1659);		
		warp34 = new Event("toHouse4", TYPE.WARP);
		warp34.setWarp("houses", "houses4o", 66, -100, 480, 150);
		warp35 = new Event("toHouse5", TYPE.WARP);
		warp35.setWarp("houses", "houses5o", 66, -100, 480, 150);
		warp36 = new Event("toHouse6", TYPE.WARP);
		warp36.setWarp("houses", "houses6o", 66, -100, 480, 150);
		warp37 = new Event("toIce1", TYPE.WARP);
		warp37.setWarp("ice1", "ice1o", 26, 407, 0, 0);		
		warp38 = new Event("lostwoods 10-9", TYPE.WARP);//warp from 8 - 9 in lost woods
		warp38.setWarp("lw2", "lw9o", 26, 181, 0,0);
		warp39 = new Event("ice1 to ice2", TYPE.WARP);//warp from 8 - 9 in lost woods
		warp39.setWarp("ice2", "ice2o", 0, 298, -481,-13);
		warp40 = new Event("tolw10", TYPE.WARP);
		warp40.setWarp("lw10", "lw10o", 26, 407, 0, 0);
		warp41 = new Event("ice2 to ice1", TYPE.WARP);
		warp41.setWarp("ice1", "ice1o", 26, 400, 0,0);
		warp42 = new Event("ice2 to igloo1", TYPE.WARP);
		warp42.setWarp("igloo1", "igloo1o", 26, 400, 0,0);		
		warp43 = new Event("igloo2 to icesandroad", TYPE.WARP);
		warp43.setWarp("icesandroad", "icesandroado", 45, -248, 500, -1651);	
		warp44 = new Event("icesandroad to igloo2", TYPE.WARP);
		warp44.setWarp("igloo1", "igloo2o", 26, 400, 0,0);
	 	warp45 = new Event("icesandroad to sand", TYPE.WARP);            
		warp45.setWarp("sand1", "sand1o", 5, -266, -486, -1651);
		warp46 = new Event("from sand to iceroad", TYPE.WARP);            
		warp46.setWarp("icesandroad", "icesandroado", 43, 350, 500, 0);
		warp47 = new Event("sand1 to sand2", TYPE.WARP);            
		warp47.setWarp("sand2", "sand2o", 26, -245, 0, 0);
		warp48 = new Event("sand2 to sand1", TYPE.WARP);            
		warp48.setWarp("sand1", "sand1o", -1, 0, -544, -337);
		warp49 = new Event("igloo1 to ice2", TYPE.WARP);
		warp49.setWarp("ice2", "ice2o", 0, -46, -611,-922);
		
		
		houses6o.accessTile(94).addEvent(warp33);
		houses6o.accessTile(95).addEvent(warp33);
		houses5o.accessTile(94).addEvent(warp32);
		houses5o.accessTile(95).addEvent(warp32);
		houses4o.accessTile(94).addEvent(warp31);
		houses4o.accessTile(95).addEvent(warp31);
		houses3o.accessTile(94).addEvent(warp30);
		houses3o.accessTile(95).addEvent(warp30);
		houses2o.accessTile(94).addEvent(warp27);
		houses2o.accessTile(95).addEvent(warp27);
		houses1o.accessTile(94).addEvent(warp26);
		houses1o.accessTile(95).addEvent(warp26);
		
		island1o.accessTile(897).addEvent(warp34);
		island1o.accessTile(910).addEvent(warp35);
		island1o.accessTile(1384).addEvent(warp36);
		island1o.accessTile(510).addEvent(warp29);
		island1o.accessTile(506).addEvent(warp28);
		island1o.accessTile(497).addEvent(warp25);
		island1o.accessTile(599).addEvent(warp43);//replace with 1
		island1o.accessTile(639).addEvent(warp43);//replace with 1

		
		lw1o.accessTile(12).addEvent(warp4);//top
		lw1o.accessTile(13).addEvent(warp4);//top
		lw1o.accessTile(224).addEvent(warp1);//right
		lw1o.accessTile(249).addEvent(warp1);//right
		lw1o.accessTile(200).addEvent(warp3);//left
		lw1o.accessTile(225).addEvent(warp3);//left
		lw1o.accessTile(337).addEvent(warp2);//bottom
		lw1o.accessTile(338).addEvent(warp2);//bottom
		
		lw2o.accessTile(12).addEvent(warp1);//top
		lw2o.accessTile(13).addEvent(warp1);//top
		lw2o.accessTile(224).addEvent(warp22);//right
		lw2o.accessTile(249).addEvent(warp22);//right
		lw2o.accessTile(200).addEvent(warp1);//left
		lw2o.accessTile(225).addEvent(warp1);//left
		lw2o.accessTile(337).addEvent(warp1);//bottom
		lw2o.accessTile(338).addEvent(warp1);//bottom
		
		lw3o.accessTile(12).addEvent(warp7);//top
		lw3o.accessTile(13).addEvent(warp7);//top
		lw3o.accessTile(224).addEvent(warp8);//right
		lw3o.accessTile(249).addEvent(warp8);//right
		lw3o.accessTile(337).addEvent(warp23);//left
		lw3o.accessTile(338).addEvent(warp23);//left
		lw3o.accessTile(200).addEvent(warp6);//bottom
		lw3o.accessTile(225).addEvent(warp6);//bottom
		
		lw4o.accessTile(12).addEvent(warp9);//top
		lw4o.accessTile(13).addEvent(warp9);//top
		lw4o.accessTile(224).addEvent(warp10);//right
		lw4o.accessTile(249).addEvent(warp10);//right
		lw4o.accessTile(200).addEvent(warp1);//left
		lw4o.accessTile(225).addEvent(warp1);//left
		lw4o.accessTile(337).addEvent(warp1);//bottom
		lw4o.accessTile(338).addEvent(warp1);//bottom
		
		lw5o.accessTile(12).addEvent(warp1);//top
		lw5o.accessTile(13).addEvent(warp1);//top
		lw5o.accessTile(224).addEvent(warp13);//right
		lw5o.accessTile(249).addEvent(warp13);//right
		lw5o.accessTile(200).addEvent(warp11);//left
		lw5o.accessTile(225).addEvent(warp11);//left
		lw5o.accessTile(337).addEvent(warp12);//bottom
		lw5o.accessTile(338).addEvent(warp12);//bottom
		
		lw6o.accessTile(12).addEvent(warp1);//top
		lw6o.accessTile(13).addEvent(warp1);//top
		lw6o.accessTile(224).addEvent(warp15);//right
		lw6o.accessTile(249).addEvent(warp15);//right
		lw6o.accessTile(200).addEvent(warp1);//left
		lw6o.accessTile(225).addEvent(warp1);//left
		lw6o.accessTile(337).addEvent(warp14);//bottom
		lw6o.accessTile(338).addEvent(warp14);//bottom
				
		lw7o.accessTile(12).addEvent(warp17);//top
		lw7o.accessTile(13).addEvent(warp17);//top
		lw7o.accessTile(224).addEvent(warp1);//right
		lw7o.accessTile(249).addEvent(warp1);//right
		lw7o.accessTile(200).addEvent(warp18);//left
		lw7o.accessTile(225).addEvent(warp18);//left
		lw7o.accessTile(337).addEvent(warp16);//bottom
		lw7o.accessTile(338).addEvent(warp16);//bottom
		
		lw8o.accessTile(12).addEvent(warp20);//top
		lw8o.accessTile(13).addEvent(warp20);//top
		lw8o.accessTile(224).addEvent(warp21);//right
		lw8o.accessTile(249).addEvent(warp21);//right
		lw8o.accessTile(200).addEvent(warp19);//left
		lw8o.accessTile(225).addEvent(warp19);//left
		lw8o.accessTile(337).addEvent(warp1);//bottom
		lw8o.accessTile(338).addEvent(warp1);//bottom
		
		lw9o.accessTile(111).addEvent(warp22);
		lw9o.accessTile(112).addEvent(warp22);
		lw10o.accessTile(88).addEvent(warp37);//Warp to new cave for level 2
		lw10o.accessTile(87).addEvent(warp37);
		lw10o.accessTile(337).addEvent(warp38);
		lw10o.accessTile(338).addEvent(warp38);
		
		ice1o.accessTile(88).addEvent(warp40);
		ice1o.accessTile(87).addEvent(warp40);
		ice1o.accessTile(337).addEvent(warp39);
		ice1o.accessTile(338).addEvent(warp39);
		ice2o.accessTile(21).addEvent(warp41);
		ice2o.accessTile(20).addEvent(warp41);
		ice2o.accessTile(902).addEvent(warp42);
		ice2o.accessTile(903).addEvent(warp42);
		igloo2o.accessTile(12).addEvent(warp43);
		igloo2o.accessTile(13).addEvent(warp43);
		igloo1o.accessTile(12).addEvent(warp49);
		igloo1o.accessTile(13).addEvent(warp49);
		
		icesandroado.accessTile(395).addEvent(warp44);
		icesandroado.accessTile(394).addEvent(warp44);
		icesandroado.accessTile(4).addEvent(warp45);
		icesandroado.accessTile(5).addEvent(warp45);
		
		sand1o.accessTile(1580).addEvent(warp46);
		sand1o.accessTile(1581).addEvent(warp46);
		sand1o.accessTile(501).addEvent(warp47);
		sand1o.accessTile(502).addEvent(warp47);
		sand2o.accessTile(338).addEvent(warp48);
		sand2o.accessTile(338).addEvent(warp48);
		
		//**********DIALOGUE EVENT HANDLING**********************
		dialogue0 = new Event("dialogue0", TYPE.DIALOGUE);
		dialogue1 = new Event("dialogue1", TYPE.DIALOGUE);
		dialogue2 = new Event("dialogue2", TYPE.DIALOGUE);
		dialogue3 = new Event("dialogue3", TYPE.DIALOGUE);
		dialogue4 = new Event("dialogue4", TYPE.DIALOGUE);
		dialogue5 = new Event("dialogue5", TYPE.DIALOGUE);
		dialogue6 = new Event("dialogue6", TYPE.DIALOGUE);
		dialogue7 = new Event("dialogue7", TYPE.DIALOGUE);
		dialogue8 = new Event("dialogue8", TYPE.DIALOGUE);
		dialogue9 = new Event("dialogue9", TYPE.DIALOGUE);
		dialogue10 = new Event("dialogue10", TYPE.DIALOGUE);
		dialogue11 = new Event("dialogue11", TYPE.DIALOGUE);
		dialogue12 = new Event("dialogue12", TYPE.DIALOGUE);
		dialogue13 = new Event("dialogue13", TYPE.DIALOGUE);
		dialogue14 = new Event("dialogue14", TYPE.DIALOGUE);
		dialogue15 = new Event("dialogue15", TYPE.DIALOGUE);
		dialogue16 = new Event("dialogue16", TYPE.DIALOGUE);
		dialogue17 = new Event("dialogue17", TYPE.DIALOGUE);
		dialogue18 = new Event("dialogue18", TYPE.DIALOGUE);
		dialogue19 = new Event("dialogue19", TYPE.DIALOGUE);
		dialogue20 = new Event("dialogue20", TYPE.DIALOGUE);
		dialogue21 = new Event("dialogue21", TYPE.DIALOGUE);
		dialogue22 = new Event("dialogue22", TYPE.DIALOGUE);
		dialogue23 = new Event("dialogue23", TYPE.DIALOGUE);
		dialogue24 = new Event("dialogue24", TYPE.DIALOGUE);
		dialogue25 = new Event("dialogue25", TYPE.DIALOGUE);
		dialogue26 = new Event("dialogue26", TYPE.DIALOGUE);
		dialogue27 = new Event("dialogue27", TYPE.DIALOGUE);
		dialogue28 = new Event("dialogue28", TYPE.DIALOGUE);
		dialogue29 = new Event("dialogue29", TYPE.DIALOGUE);
		dialogue30 = new Event("dialogue30", TYPE.DIALOGUE);
		dialogue31 = new Event("dialogue31", TYPE.DIALOGUE);
		dialogue32 = new Event("dialogue32", TYPE.DIALOGUE);
		dialogue33 = new Event("dialogue33", TYPE.DIALOGUE);
		dialogue34 = new Event("dialogue34", TYPE.DIALOGUE);
		dialogue35 = new Event("dialogue35", TYPE.DIALOGUE);
		dialogue36 = new Event("dialogue36", TYPE.DIALOGUE);
		dialogue37 = new Event("dialogue37", TYPE.DIALOGUE);
		dialogue38 = new Event("dialogue38", TYPE.DIALOGUE);
		dialogue39 = new Event("dialogue39", TYPE.DIALOGUE);
		dialogue40 = new Event("dialogue40", TYPE.DIALOGUE);
		dialogue41 = new Event("dialogue41", TYPE.DIALOGUE);
		dialogue42 = new Event("dialogue42", TYPE.DIALOGUE);
		dialogue43 = new Event("dialogue43", TYPE.DIALOGUE);
		dialogue44 = new Event("dialogue44", TYPE.DIALOGUE);
		dialogue45 = new Event("dialogue45", TYPE.DIALOGUE);
		dialogue46 = new Event("dialogue46", TYPE.DIALOGUE);
		dialogue47 = new Event("dialogue47", TYPE.DIALOGUE);
		dialogue48 = new Event("dialogue48", TYPE.DIALOGUE);
		dialogue49 = new Event("dialogue49", TYPE.DIALOGUE);
		dialogue50 = new Event("dialogue50", TYPE.DIALOGUE);

		//*************PUZZLE EVENT HANDLING******************
		puzzle1 = new Event("puzzle1", TYPE.PUZZLE);
		igloo1o.accessTile(79).addEvent(puzzle1);
		puzzle2 = new Event("puzzle2", TYPE.PUZZLE);
		igloo1o.accessTile(94).addEvent(puzzle2);
		puzzle3 = new Event("puzzle3", TYPE.PUZZLE);
		igloo1o.accessTile(254).addEvent(puzzle3);
		puzzle4 = new Event("puzzle4", TYPE.PUZZLE);
		igloo1o.accessTile(269).addEvent(puzzle4);
		puzzle5 = new Event("puzzle5", TYPE.DOOR);
		puzzle5.setWarp("igloo1", "igloo2o", 26, -245,0,0);
		igloo1o.accessTile(312).addEvent(puzzle5);


		puzzle6 = new Event("puzzle6", TYPE.PUZZLE);
		sand2o.accessTile(61).addEvent(puzzle6);
		puzzle7 = new Event("puzzle7", TYPE.PUZZLE);
		sand2o.accessTile(62).addEvent(puzzle7);
		puzzle8 = new Event("puzzle8", TYPE.PUZZLE);
		sand2o.accessTile(63).addEvent(puzzle8);
		puzzle9 = new Event("puzzle9", TYPE.PUZZLE);
		sand2o.accessTile(64).addEvent(puzzle9);
		puzzle10 = new Event("puzzle10", TYPE.DOOR);
		puzzle10.setWarp("city", "cityO", 482, 101, -5, -13);
		sand2o.accessTile(35).addEvent(puzzle10);
		
		
		island1o.accessTile(403).addEvent(dialogue0);
		island1o.accessTile(404).addEvent(dialogue0);
		island1o.accessTile(563).addEvent(dialogue1);
		island1o.accessTile(570).addEvent(dialogue2);
		island1o.accessTile(638).addEvent(dialogue8);
		lw1o.accessTile(188).addEvent(dialogue9);
		lw1o.accessTile(189).addEvent(dialogue9);
		lw3o.accessTile(313).addEvent(dialogue14);
		lw3o.accessTile(314).addEvent(dialogue14);
		lw9o.accessTile(313).addEvent(dialogue11);
		lw9o.accessTile(314).addEvent(dialogue11);
		houses1o.accessTile(82).addEvent(dialogue7);
		houses2o.accessTile(82).addEvent(dialogue3);
		houses3o.accessTile(82).addEvent(dialogue4);
		houses4o.accessTile(82).addEvent(dialogue5);
		houses5o.accessTile(82).addEvent(dialogue6);
		houses6o.accessTile(82).addEvent(dialogue16);
		lw6o.accessTile(313).addEvent(dialogue10);
		lw9o.accessTile(111).addEvent(dialogue9);
		ice2o.accessTile(336).addEvent(dialogue13);
		ice2o.accessTile(589).addEvent(dialogue17);
		ice2o.accessTile(983).addEvent(dialogue18);
		ice2o.accessTile(889).addEvent(dialogue20);
		ice2o.accessTile(1096).addEvent(dialogue19);
		ice2o.accessTile(1231).addEvent(dialogue21);
		icesandroado.accessTile(165).addEvent(dialogue22);
		sand2o.accessTile(85).addEvent(dialogue23);
		cityO.accessTile(655).addEvent(dialogue24);
		cityO.accessTile(555).addEvent(dialogue24);
		
		//lw10o.accessTile(161).addEvent(getMpotion);
		//island1o.accessTile(63).addEvent(getPotion);
		//Set up Monsters and NPCs
		npc = new Mob(frame, g2d, mainCharacter, 40, TYPE.CHASE, "npc", false);
		npc.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc.setHealth(60);
		
		npc1 = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false);
		npc1.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc1.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc1.setHealth(60);
		
		npc2 = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false);
		npc2.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc2.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc2.setHealth(60);
		
		npc3 = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false);
		npc3.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc3.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc3.setHealth(60);
		
		npc4 = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false);
		npc4.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc4.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc4.setHealth(60);
		
		npc5 = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false);
		npc5.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc5.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc5.setHealth(60);
		
		npc6 = new Mob(frame, g2d, mainCharacter, 40, TYPE.RANDOMPATH, "npc", false);
		npc6.setMultBounds(6, 50, 92, 37, 88, 62, 92, 62, 96);
		npc6.setMoveAnim(32, 48, 40, 56, 3, 8);
		npc6.setHealth(60);
		
		
		
		
		//Add the mobs to their tile home
		island1o.accessTile(570).addMob(npc);
		houses1o.accessTile(55).addMob(npc);
		houses2o.accessTile(55).addMob(npc1);
		houses3o.accessTile(55).addMob(npc2);
		houses4o.accessTile(55).addMob(npc3);
		houses5o.accessTile(55).addMob(npc4);
		houses6o.accessTile(55).addMob(npc5);
		
	}
	
	/************************************************************
	 * Get a map back  based on its index in the array of maps
	 * 
	 * @param index - Position in the maps array
	 * @return - Map
	 *************************************************************/
	public Map getMap(int index) {
		return maps[index];
	}
public void mapFromFile(String filename, Tile[] map,int mapSize)
{
	try{
		File currentMap = new File(filename);
		Scanner fileInput = new Scanner(currentMap);
		
		for(int cur = 0;cur < mapSize;cur++){
		String nextTile = fileInput.next();
		 switch(nextTile) {
		 case "dc": map[cur]=dc;break;
		 case "bc": map[cur]=bc;break;
		 case "g": map[cur]=g;break;
		 case "f": map[cur]=f;break;
		 case "ro": map[cur]=ro;break;
		 case "fl": map[cur]=fl;break;
		 case "w1": map[cur]=w1;break;
		 case "w2": map[cur]=w2;break;		 
		 case "t": map[cur]=t;break;
		 case "sn": map[cur]=sn;break;
         case "r": map[cur]=r;break;
         case "b": map[cur]=b;break;
         case "hf": map[cur]=hf;break;
		 case "h": map[cur]=h;break;
         case "hw": map[cur]=hw;break;
         case "d": map[cur]=d;break;
         case "e": map[cur]=e;break;
         case "c": map[cur]=c;break;
         case "s": map[cur]=s;break;
         case "ts": map[cur]=ts;break;
         case "py": map[cur]=py;break;
         case "si": map[cur]=si;break;
         case "br": map[cur]=br;break;
         case "bl": map[cur]=bl;break;
         case "ca": map[cur]=ca;break;
         case "sb": map[cur]=sb;break;
         case "s1": map[cur]=s1;break;
         case "s2": map[cur]=s2;break;
         case "s3": map[cur]=s3;break;
         case "s4": map[cur]=s4;break;
         case "so": map[cur]=so;break;
         case "dr": map[cur]=dr;break;
         case "n1": map[cur]=n1;break;
         case "n2": map[cur]=n2;break;
         case "n3": map[cur]=n3;break;
         case "n4": map[cur]=n4;break;
         case "bs": map[cur]=bs;break;
         case "bp": map[cur]=bp;break;
         case "cn": map[cur]=cn;break;
         case "pe": map[cur]=pe;break;
         case "ig": map[cur]=ig;break;
         case "ib": map[cur]=ib;break;
         case "ss": map[cur]=ss;break;
         case "c1": map[cur]=c1;break;
         case "c2": map[cur]=c2;break;
         default: map[cur]=e;break;
		 }
		}
		fileInput.close();
	}
catch(FileNotFoundException e){
	System.out.println(e);
	System.exit(1);
		}	
	}
}

