package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class Hiscores {
	private int hiscoresLimit = 10;
	private ArrayList<Score> scores = new ArrayList<Score>();

	public Hiscores() throws NumberFormatException, IOException {
		readHiscores();
	}

	public ArrayList<Score> getScores() {
		return this.scores;
	}

	public void readHiscores() throws NumberFormatException, IOException {
		File f = new File("src/Hiscores.txt");
		ClassLoader.getSystemClassLoader();
		java.io.InputStream inputStream = ClassLoader.getSystemResourceAsStream("Hiscores.txt");
		InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader in = new BufferedReader(streamReader);

		for (String line; (line = in.readLine()) != null;) {
			// do something with the line
			String[] parts = line.split("\\s+");
			this.scores.add(new Score(parts[0], Double.parseDouble(parts[1])));
			System.out.println((parts[0]+" "+ Double.parseDouble(parts[1])));
		}
	}

	public void writeHiscores() throws IOException {
		File f = new File("Hiscores.txt");
		PrintWriter out = new PrintWriter(f);

		for (int i = 0; i < scores.size(); i++) {
			String name = scores.get(i).name;
			double score = scores.get(i).score;
			out.println(name + " " + score);
			System.out.println(name + " " + score);
		}

		out.close();

	}

	public void submit(String name, double score) throws IOException {
		for (int i = 0; i < this.scores.size(); i++) {
			if (this.scores.get(i).score < score) {
				this.scores.add(i, new Score(name, score));
				if (this.scores.size() > 10) {
					this.scores.remove(this.scores.size() - 1);
				}
				writeHiscores();
				System.out.println("submit");
				break;
			}
		}
	}

	public class Score {
		public Score(String name, double score) {
			this.score = score;
			this.name = name;
		}

		public double score;
		public String name;
	}
}
