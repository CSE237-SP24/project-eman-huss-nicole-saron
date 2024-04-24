package bankapp;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class FileData {
	private final String filePath;
	private final int recordSize;

	public FileData(String filePath, int recordSize) {
		this.filePath = filePath;
		this.recordSize = recordSize;
	}

	public void writeData(BigInteger recordNumber, String data) throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
			BigInteger offset = calculateOffset(recordNumber);

			file.seek(offset.longValue());


			byte[] recordData = new byte[recordSize];
			byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
			System.arraycopy(dataBytes, 0, recordData, 0, Math.min(dataBytes.length, recordSize));
			file.write(recordData);
		}
	}

	public String readData(BigInteger recordNumber) throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
			BigInteger offset = calculateOffset(recordNumber);
			System.out.println("offset: " + offset.longValue());

			file.seek(offset.longValue());

			byte[] recordData = new byte[recordSize];
			try {
				file.readFully(recordData);
				return new String(recordData, StandardCharsets.UTF_8).trim();
			} catch (EOFException e) {
				return "";
			}
		}
	}

	private BigInteger calculateOffset(BigInteger recordNumber) {
		BigInteger offset = recordNumber.subtract(BigInteger.ONE).multiply(BigInteger.valueOf(recordSize));
		return offset;
	}

}
