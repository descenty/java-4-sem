package ru.mirea.practice12;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;

@Component
public class FileHasher {
    @Value("${inputFile}")
    private String inputFile;

    @Value("${outputFile}")
    private String outputFile;

    private File inputFileObj;
    private File outputFileObj;

    @PostConstruct
    public void initialize() throws Exception {
        inputFileObj = new File(inputFile);
        outputFileObj = new File(outputFile);

        if (!inputFileObj.exists()) {
            try (FileOutputStream outputStream = new FileOutputStream(outputFileObj)) {
                outputStream.write("null".getBytes());
            }
            return;
        }

        try (InputStream inputStream = new FileInputStream(inputFileObj)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = digest.digest();
            String hashString = bytesToHex(hashBytes);

            try (FileOutputStream outputStream = new FileOutputStream(outputFileObj)) {
                outputStream.write(hashString.getBytes());
            }
        }
    }

    @PreDestroy
    public void cleanup() {
        if (inputFileObj.exists()) {
            inputFileObj.delete();
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
