package bankapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileData {
	public static void main(String[] args) throws FileNotFoundException {
		File inFile = new File("files/file.txt");

		Scanner in = new Scanner(inFile);

		// TODO Auto-generated catch block
		System.out.println("file not found. do better");

//		writing to a file //
		try {
			PrintWriter out = new PrintWriter(inFile);
			BankAccount ba = new BankAccount("Jeff");
			ba.deposit(100);
			out.println(ba);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void writeAccountInfo() {
		
	}

}
