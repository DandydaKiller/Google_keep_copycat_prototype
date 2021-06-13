import java.util.*;
public class Security {
	
	public static String UrutanKeyPoly (String plaintext, String key) {
        int panjangKey = plaintext.length();
        String output = "";
        int counter = 0;
        for (int i = 0; i < panjangKey ; i++) {
            if (counter > key.length() - 1) {
                counter = 0;
            }
            output = output + (key.charAt(counter));
            counter++;
       }
        return output;
    }
    
    
     public static String EnkripsiPoly(String plaintext, String key) {
      String output = "";
        for (int i = 0; i < plaintext.length(); i++) {
            if(plaintext.charAt(i) > 64 && plaintext.charAt(i) < 91){
                int ubah = (((plaintext.charAt(i)-64)+(key.charAt(i)-64)))%26;
                ubah = ubah + 64;
                output = output + (char)(ubah);
            }else if (plaintext.charAt(i) > 96 && plaintext.charAt(i) < 123){
                int ubah = ((plaintext.charAt(i)-96)+(key.charAt(i)-96))%26;
                ubah = ubah + 96;
                output = output + (char)(ubah);
            }else if (plaintext.charAt(i) == ' '){
                output = output + '@';
            }
        }
      return output;
    }
    
    
    public static String EncryptRT(String key, String text) {
        int[] arrange = arrangeKey(key);
        
        int lenkey = arrange.length;
        int lentext = text.length();

        int row = (int) Math.ceil((double) lentext / lenkey);
        //System.out.println(row);
        //System.out.println(Arrays.toString(arrange));

        char[][] grid = new char[row][lenkey];
        int z = 0;
        //System.out.println("Array Enkripsi");
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (z >= lentext) {
                    grid[x][y] = '#';
                } else {
                    if (text.charAt(z) == '@') {
                        grid[x][y] = '@';
                    }else{
                        grid[x][y] = text.charAt(z);
                    }
                }
                z++;
                //System.out.print(" " + grid[x][y] + " ");
            }
            //System.out.println();
        }
        String enc = "";
        for (int x = 0; x < lenkey; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (x == arrange[y]) {
                    for (int a = 0; a < row; a++) {
                        enc = enc + grid[a][y];
                    }
                }
            }
        }
        return enc;
    }
    
     public static String DecryptRT(String key, String text) {
        int[] arrange = arrangeKey(key);
        int lenkey = arrange.length;
        int lentext = text.length();

        int row = (int) Math.ceil((double) lentext / lenkey);

        String regex = "(?<=\\G.{" + row + "})";
        String[] get = text.split(regex);

        char[][] grid = new char[row][lenkey];
        //System.out.println("Array Dekripsi");
        for (int x = 0; x < lenkey; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (arrange[x] == y) {
                    for (int z = 0; z < row; z++) {
                        if (get[arrange[y]].charAt(z) == '#') {
                            grid[z][y] = ' ';
                        }else{
                            grid[z][y] = get[arrange[y]].charAt(z);
                            //System.out.print(" " + grid[z][y] + " ");
                        }
                    }
                }
            }
            //System.out.println();
        }

        String dec = "";
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenkey; y++) {
                    dec = dec + grid[x][y];
            }
        }

        return dec;
    }
    
    public static int[] arrangeKey(String key) {
        
        String[] keys = key.split("");
        Arrays.sort(keys);
        int[] num = new int[key.length()];
        for (int x = 0; x < keys.length; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (keys[x].equals(key.charAt(y) + "")) {
                    num[y] = x;
                    break;
                }
            }
        }

        return num;
    }
     
    public static String DeskripsiPoly(String ciphertext, String key){
      String output = "";
        for (int i = 0; i < ciphertext.length() && i < key.length(); i++) {
            if (ciphertext.charAt(i) > 64 && ciphertext.charAt(i) < 91) {
               int ubah = (((ciphertext.charAt(i)-64) - (key.charAt(i)-64)))%26;
               ubah = ubah + 64;
               output = output + (char) (ubah); 
            }else if (ciphertext.charAt(i) > 96 && ciphertext.charAt(i) < 123){
               int ubah = ((ciphertext.charAt(i)-96) - (key.charAt(i)-96)+26)%26;
               ubah = ubah + 96;
               output = output + (char) (ubah); 
            }else if (ciphertext.charAt(i)=='@'){
               
               output = output + ' ';
            }
            
        }
      return output;
    }
    
}
