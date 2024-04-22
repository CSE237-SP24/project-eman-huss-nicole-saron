package test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.FileData;
import bankapp.Menu;

class fileData {
    private static final String FILE_PATH = "./file.txt";
    private static final int RECORD_SIZE = 50; //number of Bytes perline

    private FileData fileData;
    
    @BeforeEach
    void setUp() {
        fileData = new FileData(FILE_PATH, RECORD_SIZE);
    }
    
	@Test
	void writeThenReadIt() throws IOException {
	    Menu mainMenu = new Menu();

	    BankAccount test = mainMenu.createAccount("John");
	    int lineNumber = mainMenu.getAccountHash(test);
	    String writtenData = String.valueOf(lineNumber);
	    
	    // Writing
	    fileData.writeData(lineNumber, writtenData);
	    long endWriteTime = System.currentTimeMillis();

	    // Reading
	    String readData = fileData.readData(lineNumber);

	    assertEquals(writtenData, readData);
	}

	 @Test
	    void testWriteAndReadData() throws IOException {
	        String data = "This is a test record";
	        int recordNumber = 1;

	        fileData.writeData(recordNumber, data);
	        String readData = fileData.readData(recordNumber);

	        assertEquals(data, readData.trim());
	    }

	    @Test
	    void testUpdateData() throws IOException {
	        String initialData = "Initial data";
	        String updatedData = "Updated data";
	        int recordNumber = 2;

	        fileData.writeData(recordNumber, initialData);
	        String readData = fileData.readData(recordNumber);
	        assertEquals(initialData, readData.trim());

	        fileData.writeData(recordNumber, updatedData);
	        readData = fileData.readData(recordNumber);
	        assertEquals(updatedData, readData.trim());
	    }

	    @Test
	    void testUpdateDataDoesNotAffectOtherRecords() throws IOException {
	        String record1Data = "Record 1";
	        String record2Data = "Record 2";
	        String record3Data = "Record 3";
	        String updatedRecord2Data = "Updated Record 2";

	        fileData.writeData(1, record1Data);
	        fileData.writeData(2, record2Data);
	        fileData.writeData(3, record3Data);

	        fileData.writeData(2, updatedRecord2Data);

	        assertEquals(record1Data, fileData.readData(1).trim());
	        assertEquals(updatedRecord2Data, fileData.readData(2).trim());
	        assertEquals(record3Data, fileData.readData(3).trim());
	    }

	    @Test
	    void testReadDataFromEmptyRecord() throws IOException {
	        int emptyRecordNumber = 5;
	        String readData = fileData.readData(emptyRecordNumber);
	        assertEquals("", readData.trim());
	    }

	    @Test
	    void testWriteDataExceedingRecordSize() throws IOException {
	        String longData = "This is a very long data that exceeds the record size";
	        int recordNumber = 4;

	        fileData.writeData(recordNumber, longData);
	        String readData = fileData.readData(recordNumber);

	        assertEquals(longData.substring(0, RECORD_SIZE), readData.trim());
	    }
}
