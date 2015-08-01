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

    BasicHtmlTextLayoutDrawer textLayout;
    textLayout = new BasicHtmlTextLayoutDrawer(text, this.getFont(), g1.getFontRenderContext());
    textLayout.setTextColor(Color.white);
    textLayout.setStrokeColor(Color.black);
    textLayout.setStrokeWidth(2);
    
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