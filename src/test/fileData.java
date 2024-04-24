package test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.math.BigInteger;

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
		BigInteger hashCode = BigInteger.valueOf(mainMenu.getAccountHash(test));
		BigInteger lineNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
	    String writtenData = String.valueOf(lineNumber);
	    
	    // Writing
	    fileData.writeData(lineNumber, writtenData);
//	    long endWriteTime = System.currentTimeMillis();

	    // Reading
	    String readData = fileData.readData(lineNumber);

	    assertEquals(writtenData, readData);
	}

	 @Test
	    void testWriteAndReadData() throws IOException {
	        String data = "This is a test record";
	        int recordNumber1 = 1;
			BigInteger hashCode = BigInteger.valueOf(recordNumber1);
			BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
	        fileData.writeData(recordNumber, data);
	        String readData = fileData.readData(recordNumber);

	        assertEquals(data, readData.trim());
	    }

	    @Test
	    void testUpdateData() throws IOException {
	        String initialData = "Initial data";
	        String updatedData = "Updated data";
	        //int recordNumber = 2;

			BigInteger hashCode = BigInteger.valueOf(2);
			BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
	        
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

			BigInteger hashCode = BigInteger.valueOf(2);
			BigInteger recordNumber = hashCode.and(BigInteger.valueOf(Long.MAX_VALUE));
	        
	        fileData.writeData(BigInteger.valueOf(1), record1Data);
	        fileData.writeData(BigInteger.valueOf(2), record2Data);
	        fileData.writeData(BigInteger.valueOf(3), record3Data);

	        fileData.writeData(BigInteger.valueOf(4), updatedRecord2Data);

	        assertEquals(record1Data, fileData.readData(BigInteger.valueOf(1)).trim());
	        assertEquals(updatedRecord2Data, fileData.readData(BigInteger.valueOf(2)).trim());
	        assertEquals(record3Data, fileData.readData(BigInteger.valueOf(3)).trim());
	    }

	    @Test
	    void testReadDataFromEmptyRecord() throws IOException {
		
	        String readData = fileData.readData(BigInteger.valueOf(5));
	        assertEquals("", readData.trim());
	    }

	    @Test
	    void testWriteDataExceedingRecordSize() throws IOException {
	        String longData = "This is a very long data that exceeds the record size";

	        
	        fileData.writeData(BigInteger.valueOf(4), longData);
	        String readData = fileData.readData(BigInteger.valueOf(4));

	        assertEquals(longData.substring(0, RECORD_SIZE), readData.trim());
	    }
}
