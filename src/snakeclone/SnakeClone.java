/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.lwjgl.Sys;

/**
 *
 * @author Manning
 */
public class SnakeClone extends Canvas {
	private BufferStrategy strategy;
	private boolean gameIsRunning;
	private long lastLoopTime = System.currentTimeMillis();
	
	public SnakeClone() {
		JFrame container = new JFrame("Snake by Neil");
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 6000));
		panel.setLayout(null);
		
		setBounds(0,0,800,600);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}
	
	public void gameLoop() {
		//gameloop
		while(gameIsRunning) {
			//work out how long its been since the last update
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			//clear and draw
			Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, 800, 600);
			//destroy object and flip buffer
			graphics.dispose();
			strategy.show();
			try { Thread.sleep(10); } catch (Exception e) {}
			
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SnakeClone g = new SnakeClone();
		g.gameLoop();
	}
}
