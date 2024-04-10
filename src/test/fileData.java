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

	@Test
	void writeTester() throws IOException {
		Menu mainMenu = new Menu();

		// 1. Setup Objects
		BankAccount johnAccount = mainMenu.createAccount("John1");
		String filePath = "./file.txt";
		//File file = new File("./file.txt");

		//mainMenu.writeData(file, johnAccount);
		
		int lineNumber = 3;
		
        
		List<String> lines = Files.readAllLines(Paths.get(filePath));

        // Modify the specific line
        if (lineNumber >= 0 && lineNumber < lines.size()) {
            lines.set(lineNumber, johnAccount.toString());
        } else {
            System.out.println("Invalid line number!");
            return;
        }

        // Write the updated list of strings back to the file
        Files.write(Paths.get(filePath), lines);
	
	
	}


}
