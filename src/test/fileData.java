package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class fileData {

//	@Test
//	void writeTester() throws IOException {
//		Menu mainMenu = new Menu();
//
//		// 1. Setup Objects
//		BankAccount johnAccount = mainMenu.createAccount("John1");
//		String filePath = "./file.txt";
//		//File file = new File("./file.txt");
//		
//		//mainMenu.writeData(file, johnAccount);
//		
//		int lineNumber = mainMenu.getAccountHash(johnAccount);;
//		
//        
//
//		List<String> lines;
//		
//		try {
//		    lines = Files.readAllLines(Paths.get(filePath));
//		} catch (IOException e) {
//		    System.out.println("Error reading the file: " + e.getMessage());
//		    return;
//		}
//		
//		if (lineNumber >= 0 && lineNumber < lines.size()) {
//		    if (lines.get(lineNumber).isEmpty()) {
//		        lines.set(lineNumber, johnAccount.toString());
//		    } else {
//		        System.out.println("The specified line is not empty.");
////		        lines.set(lineNumber, lines.get(lineNumber) + "\n" + johnAccount.toString());
//		    }
//		} else if (lineNumber >= lines.size()) {
//		    // Add empty lines until we reach the desired line number
//		    while (lines.size() <= lineNumber) {
//		        lines.add("");
//		    }
//		    lines.set(lineNumber, johnAccount.toString());
//		} else {
//		    System.out.println("Invalid line number!");
//		    return;
//		}
//		
//
//		// Write the updated list of strings back to the file
//		try {
//		    Files.write(Paths.get(filePath), lines);
//		    System.out.println("File updated successfully!");
//		} catch (IOException e) {
//		    System.out.println("Error writing to the file: " + e.getMessage());
//		}
//	

	@Test
	void readTested() throws IOException {
		Menu mainMenu = new Menu();
		
		BankAccount kkAccount = mainMenu.createAccount("John1");
		
		// verify that this user is in the file list
		String filePath = "./file.txt";
		int lineNumber = mainMenu.getAccountHash(kkAccount);
		System.out.println(lineNumber);
		//go read that line
		BufferedReader br = new BufferedReader(new FileReader(filePath));
	    for (int i =1; i < lineNumber; i ++) {
	    	String L = br.readLine();
	    	System.out.println(L);
	    }
		
		// GOAL bankapp.BankAccount@33065d67
		String line = br.readLine();
		System.out.println("GOT IT: " + lineNumber + " : " + line);
		
    	String L = br.readLine();
    	System.out.println(L);
		
	}


}
