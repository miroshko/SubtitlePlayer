/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package subtitles.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *
 * @author miroshko
 */
public class BasicHtmlTextLayoutDrawer {
    private TextLayout textLayout;
    private Color strokeColor;
    private Color textColor;
    private int strokeWidth;
    private FontRenderContext fontRenderContext;
    private Font font;
    private String text;

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    
    public BasicHtmlTextLayoutDrawer(String text, Font font, FontRenderContext fontRenderContext ) {
        this.text = text;
        this.font = font;
        this.fontRenderContext = fontRenderContext;
    }
        
    public void draw(Graphics2D g1, int x, int y) {
        String[] lines = text.split("((<br.*?>)|\n)");
        int lineSpacing = 3;
        for(String line: lines) {
            // strip html tags as long as they are not processed
            line = line.replaceAll("<.*?>", "");
            TextLayout tl = drawText(line, g1, x, y);
            if (tl != null) {
                y += tl.getBounds().getHeight() + lineSpacing;            
            }
        }
    }
    
    private TextLayout drawText(String text, Graphics2D g1, int x, int y) {
        if (text.length() == 0) {
            return null;
        }
        textLayout = new TextLayout(text, font, fontRenderContext);
        
        for(double i = -Math.PI; i < Math.PI; i+= Math.PI / 8) {
            g1.setPaint(getStrokeColor());
            textLayout.draw(
                            g1,
                            (float)x + (float)getStrokeWidth() * (float)Math.sin(i),
                            (float)y + (float)getStrokeWidth() * (float)Math.cos(i)
                           );
        }
        g1.setPaint(this.getTextColor());
        textLayout.draw(g1, x, y);
        return textLayout;
    }
}
