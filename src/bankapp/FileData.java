package bankapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileData {
	public static void main(String[] args) {
		File inFile = new File("files/file.txt");
		try {
			Scanner in = new Scanner (inFile);
			int temp = 1;
			int temp2 = 3;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found. do better");
			e.printStackTrace();
		}
//		writing to a file //
		try {
			PrintWriter out = new PrintWriter(inFile);
			BankAccount ba = new BankAccount("Jeff");
			ba.deposit(100);
			out.println(ba);
			out.close();			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
