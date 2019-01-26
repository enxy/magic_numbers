import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class MagicNumbers {
    private Map<String,int[]> signatures;
    private static final int SIZE_OF_BUFFER = 4*1024;
    private static final int SIZE_OF_SIGNATURE = 8;
    private static final int[] jpg = {0xff, 0xd8, 0xff, 0xe0};
    private static final int[] gif = {0x47, 0x49, 0x46, 0x38, 0x39, 0x61};
    private static final int[] png ={0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
    private static final int[] txt = {0x46, 0x4F, 0x52, 0x4D, 0x46, 0x54, 0x58, 0x54, 0xEF, 0xBB, 0xBF, 0x50, 0x4B, 0x05, 0x06};
    private static final int[] pdf ={0x25, 0x50, 0x44, 0x2d};

    public MagicNumbers() {
        this.signatures = new HashMap<String,int[]>();
        signatures.put("jpg", jpg);
        signatures.put("gif", gif);
        signatures.put("txt", txt);
        signatures.put("png", png);
        signatures.put("pdf", pdf);
    }

    public Set getAvailableExt() {
        return signatures.keySet();
    }

    public String searchMagicNumber(String filePath){
        byte[] buffer = new byte[SIZE_OF_BUFFER];
        String fileType = "";

        try {
            InputStream fileStream = new FileInputStream(new File(filePath));
            int content = fileStream.read(buffer, 0, SIZE_OF_BUFFER);
            int a = content;
            for (int x = content; (x < SIZE_OF_SIGNATURE) && (content > 0); x += content) {
                content = fileStream.read(buffer, x, SIZE_OF_BUFFER - x);
            }
            for (Iterator<String> i = signatures.keySet().iterator(); i.hasNext();) {
                String key = i.next();
                if (findMatches(signatures.get(key), buffer, a)) {
                    fileType = key;
                    break;
                }
            }
            fileStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Please give relative file path to method. Exception occured: " + e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return fileType;
    }

    private static boolean findMatches(int[] signature, byte[] buffer, int size) {
        boolean exists = true;
        for (int i = 0; i < signature.length; i++) {
            if (signature[i] != (0x00ff & buffer[i])) {
                exists = false;
                break;
            }
        }
        return exists;
    }

    public static void main(String[] args) {
        MagicNumbers mg = new MagicNumbers();
        System.out.println(mg.searchMagicNumber("../po/README.txt"));
    }
}