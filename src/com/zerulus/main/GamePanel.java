package com.zerulus.main;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running;

	private Graphics2D g;
	private BufferedImage img;

	public static final int SIZE = 2;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static int frameCount;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();

		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void init() {
		running = true;
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();

	}

	public void run() {

		init();

        final double GAME_HERTZ = 30.0;
        final double TBU = 1000000000 / GAME_HERTZ;

        final int MUBR = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS;

        frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while(running) {

            double now = System.nanoTime();
            int updateCount = 0;

            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                lastUpdateTime += TBU;
                updateCount++;
            }

            if(now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }
            input();
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                Thread.yield();

                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                //FYI on some OS's this can cause pretty bad stuttering.
                //Scroll down and have a look at different peoples' solutions to this.
                try {Thread.sleep(1);} catch(Exception e) { System.out.print("Error in Thread-1");}

                now = System.nanoTime();
            }
        }
	}

	public void update() {
		
	}

	public void input() {
		
	}

	public void render() {
		if(g != null) {
			g.setColor(Color.BLACK);
			g.fillRect(0,0,WIDTH,HEIGHT);
		} else {
			System.out.println("g is null");
		}

	}

	public void draw() {
		Graphics g2 = (Graphics) this.getGraphics();
		g2.drawImage(img, 0, 0, null);
		g2.dispose();
	}
}

