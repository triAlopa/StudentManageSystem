package util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Code {
    public static int count=0;
    public static String randomCode() {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            arrayList.add((char) ('a' + i));
            arrayList.add((char) ('A' + i));
        }
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int tempIndex = r.nextInt(arrayList.size());
            sb.append(arrayList.get(tempIndex));
        }
        int randomNumber = r.nextInt(10);
        sb.append(randomNumber);
        char[] chs = sb.toString().toCharArray();
        int tempIndex = r.nextInt(chs.length);
        char temp = chs[tempIndex];
        chs[tempIndex] = chs[chs.length - 1];
        chs[chs.length - 1] = temp;
        count++;
        if(count==10){
            ImageIcon emoji = new ImageIcon("src/img/emoji.png");
            JOptionPane.showMessageDialog(null,"没完没了了是吧?"," ",JOptionPane.ERROR_MESSAGE,emoji);
        }
        return new String(chs);
    }
}
