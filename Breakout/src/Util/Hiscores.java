package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hiscores {
	private int hiscoresLimit = 10;
	private ArrayList<Score> scores = new ArrayList<Score>();
	
	public Hiscores() throws FileNotFoundException {
		readHiscores();
	}
	
	
	public ArrayList<Score> getScores(){
		return this.scores;
	}
	
	public void readHiscores() throws FileNotFoundException {
		File file = new File("./Hiscores.txt");
		Scanner s = new Scanner(file);
		while(s.hasNext()) {
			String name = s.next();
			double score = s.nextDouble();
			this.scores.add(new Score(name, score));
			System.out.println(name+" "+score);
		}
	}
	
	public void writeHiscores() throws IOException {
		File file = new File("./Hiscores.txt");
		BufferedWriter out = new BufferedWriter(new FileWriter(file));

		for (int i = 0; i < scores.size(); i++) {
			String name = scores.get(i).name;
			double score = scores.get(i).score;
			out.write(name+" "+score);
			out.newLine();
		}	
	}
	
	public void submit(String name, double score) throws IOException {
		for(int i = 0; i > this.scores.size(); i++) {
			if(this.scores.get(i).score < score) {
				this.scores.add(new Score(name, score));
				if(this.scores.size()>10) {
					this.scores.remove(this.scores.size()-1);
				}
				writeHiscores();
			}
		}
	}
	
	public class Score{
		public Score(String name, double score) {
			this.score = score;
			this.name = name;
		}
		public double score;
		public String name;
	}
}
