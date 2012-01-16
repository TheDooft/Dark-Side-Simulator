package dss;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CombatLog {
	private static CombatLog log;
	private FileWriter fstream;
	private BufferedWriter out;

	private CombatLog() {
	}

	public static CombatLog getInstance() {
		if (null == log) {
			log = new CombatLog();
		}
		return log;
	}

	public void init(){
		try {
			fstream = new FileWriter("log.txt");
			out = new BufferedWriter(fstream);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void write(String str) {
		try {
			out.write(str);
		} catch (Exception e) {
			System.err.println("[write]Error writing \"" + str + "\": " + e.getMessage());
		}
	}

	public void writeln(String str) {
		try {
			out.write(str);
			out.newLine();
		} catch (Exception e) {
			System.err.println("[writeln]Error writing \"" + str + "\": " + e.getMessage());
		}
	}

	public void close() {
		try {
			out.close();
		} catch (Exception e) {
			System.err.println("[close]Error while closing: " + e.getMessage());
		}
	}
}
