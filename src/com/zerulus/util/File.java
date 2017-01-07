package com.zerulus.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File {
	
	private static String file = "res/stats.txt";

	public static void write(String input) {
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter buff = new BufferedWriter(writer);
			
			buff.write(input);
			buff.newLine();
			
			buff.flush();
			buff.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String read() {
		String lines = "null";
		try {
			FileReader read = new FileReader(file);
			BufferedReader buff = new BufferedReader(read);
			lines = buff.readLine();
			buff.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lines;
	}
	
	
}
