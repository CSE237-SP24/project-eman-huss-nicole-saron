package bankapp;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class FileData {
    private final String filePath;
    private final int recordSize;

    public FileData(String filePath, int recordSize) {
        this.filePath = filePath;
        this.recordSize = recordSize;
    }

    public void writeData(int recordNumber, String data) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            long offset = calculateOffset(recordNumber);
            file.seek(offset);
            byte[] recordData = new byte[recordSize];
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            System.arraycopy(dataBytes, 0, recordData, 0, Math.min(dataBytes.length, recordSize));
            file.write(recordData);
        }
    }

    public String readData(int recordNumber) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            long offset = calculateOffset(recordNumber);
            file.seek(offset);
            byte[] recordData = new byte[recordSize];
            file.readFully(recordData);
            return new String(recordData, StandardCharsets.UTF_8).trim();
        }
    }

    private long calculateOffset(int recordNumber) {
        return (long) (recordNumber - 1) * recordSize;
    }
}
