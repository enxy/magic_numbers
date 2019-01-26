import java.util.*;

public class MagicNumbers {
    private Map<String,int[]> signatures;
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

    public static void main(String[] args) {
        MagicNumbers mg = new MagicNumbers();
        System.out.println(mg);
    }
}