package subtitles.player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Translucent extends JPanel implements ActionListener {

    private static final int W = 300;
    private static final int H = 100;
    private static final Font font =
        new Font("Serif", Font.PLAIN, 48);
    private static final SimpleDateFormat df =
        new SimpleDateFormat("HH:mm:ss");
    private final Date now = new Date();
    private final Timer timer = new Timer(1000, this);
    private BufferedImage time;
    private Graphics2D timeG;

    public Translucent() {
        super(true);
        this.setPreferredSize(new Dimension(W, H));
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = this.getWidth();
        int h = this.getHeight();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, w, h);
        g2d.setComposite(AlphaComposite.Src);
        g2d.setPaint(g2d.getBackground());
        g2d.fillRect(0, 0, w, h);
        g2d.setComposite(AlphaComposite.SrcOver);
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(new Color(0f, 0f, 0f, 0.3f));
        f.setUndecorated(true);
        f.add(new Translucent());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        now.setTime(System.currentTimeMillis());
        this.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                create();
            }
        });
    }
}