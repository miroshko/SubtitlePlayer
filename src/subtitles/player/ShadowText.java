package subtitles.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.io.IOException;
import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShadowText extends JLabel {

  public ShadowText() {
  }

  public void paint(Graphics g) {
    int x = 50;
    int y = 50;

    Graphics2D g1 = (Graphics2D) g;

    String text = this.getText();
    // @todo: html should be taken into account in fact
    text = text.replaceAll("\\<.*?>"," ");
    if (text.length() == 0) {
        return;
    }
    TextLayout textLayout = new TextLayout(text, this.getFont(), g1.getFontRenderContext());
    int strokeWidth = 2;
    Color strokeColor = new Color(50,50,50);
    for(double i = -Math.PI; i < Math.PI; i+= Math.PI / 8) {
        g1.setPaint(strokeColor);
        textLayout.draw(
                        g1,
                        (float)x + (float)strokeWidth * (float)Math.sin(i),
                        (float)y + (float)strokeWidth * (float)Math.cos(i)
                       );
    }
    
    g1.setPaint(this.getForeground());
    textLayout.draw(g1, x, y);
  }

  public static void main(String[] args) throws IOException {
    JFrame f = new JFrame();
    f.add(new ShadowText());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(450, 150);

    f.setVisible(true);

  }
}