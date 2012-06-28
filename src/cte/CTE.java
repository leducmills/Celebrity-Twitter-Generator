package cte;

import java.io.File;

import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.DropdownList;
import processing.core.PApplet;
import processing.core.PFont;
import rita.RiMarkov;


public class CTE extends PApplet {

	//File dir = new File("data/celebs");
	//String[] files = dir.list();
	
	String[] files = {"AlecBaldwin", "aplusk" ,"BarackObama", "BillGates", "britneyspears", "ConanOBrien","CornelWest", "DAVID_LYNCH", "doctorow", "donttrythis", "Jesus", "justinbieber", "KariByron", "katyperry", "ladygaga", "lessig", "LilKim", "LilTunechi", "mikeroweworks", "NICKIMINAJ", "Oprah", "ParisHilton", "PerezHilton", "SarahPalinUSA", "snooki",  "SnoopDogg", "StephenAtHome", "SteveMartinToGo","TheEllenShow", "WilliamShatner","yokoono"};
	

	ControlP5 controlP5;
	DropdownList p1, p2;
	Button b1;

	String celeb = null;
	int ngram = 0;
	String generatedTweet = "Please select a celebrity and an NGram value";

	PFont fontA;

	public void setup() {
		size(1200, 500);

		fontA = loadFont("HelveticaNeue-Bold-64.vlw");
		textFont(fontA, 20);
		// Set the font and its size (in units of pixels)
		//textFont(fontA, 32);

		controlP5 = new ControlP5(this);
		p1 = controlP5.addDropdownList("Celebrites",100,100,100,120);
		p2 = controlP5.addDropdownList("NGram", 205,100,100,120);
		addItems();
		customize(p1);
		customize(p2);
		p1.setId(1);
		p2.setId(2);
		b1 = controlP5.addButton("Generate", 0, 310, 85, 100, 20);
		b1.setColorBackground(color(60));
		b1.setHeight(15);



	}

	public void draw() {
		background(255);
		fill(color(60));
		text(generatedTweet, 100, 300);
	}

	void customize(DropdownList ddl) {
		ddl.setBackgroundColor(color(190));
		ddl.setItemHeight(20);
		ddl.setBarHeight(15);
		//ddl.captionLabel().set("Celebrities");
		ddl.captionLabel().style().marginTop = 3;
		ddl.captionLabel().style().marginLeft = 3;
		ddl.valueLabel().style().marginTop = 3;
		ddl.setColorBackground(color(60));
		ddl.setColorActive(color(255,128));
	}

	public void addItems() {

		//System.out.println(files.length);

		for(int i = 0; i< files.length; i++) {
			p1.addItem(files[i], i);
			println(files[i]);
		}

		for(int i = 2; i< 7; i++) {
			p2.addItem("" + i, i);
		}


	}

	public void controlEvent(ControlEvent theEvent) {
		// PulldownMenu is if type ControlGroup.
		// A controlEvent will be triggered from within the ControlGroup.
		// therefore you need to check the originator of the Event with
		// if (theEvent.isGroup())
		// to avoid an error message from controlP5.

		if(theEvent.id() == 1) {
			celeb = files[(int) theEvent.group().value()];
			//println(celeb);
		}

		if(theEvent.id() == 2) {
			ngram = (int) theEvent.group().value();
			//println(ngram);
		}


		//println(theEvent.id());

		if (theEvent.isGroup()) {
			// check if the Event was triggered from a ControlGroup
			// println(theEvent.group().value()+" from "+theEvent.group());
			//println(theEvent.group().value());
		} else if(theEvent.isController()) {
			//println(theEvent.controller().value()+" from "+theEvent.controller());
		}
	}


	public void Generate() {

		//println("hi");


		if(celeb != null && ngram != 0) {

			RiMarkov markov = new RiMarkov(this, ngram);
			markov.loadFile("data/celebs/" + celeb);

			markov.setMinSentenceLength(8);
			markov.setMaxSentenceLength(15);
			markov.ignorePunctuation = true;

			String[] line = markov.generate(1);
			generatedTweet = line[0];
			//println(line[0]);
		}



	}




}


