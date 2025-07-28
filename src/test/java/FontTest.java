import java.awt.*;
public class FontTest {
    public static void main(String...arguments) {
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Font fonts[] = gEnv.getAllFonts();
        for(java.awt.Font font : fonts)System.out.println(font);
    }
}

