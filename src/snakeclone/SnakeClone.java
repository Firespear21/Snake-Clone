/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import snakeclone.entity.EnemySnakeEntity;
import snakeclone.entity.Entity;
import snakeclone.entity.SnakeEntity;

/**
 *
 * @author Manning
 */
public class SnakeClone extends Canvas {
	//Rendering variables
	private BufferStrategy strategy;
	private boolean gameIsRunning = true;
	private boolean paused = false;
	SnakeEntity ownSnake;
	EnemySnakeEntity enemySnake;
	EntityManagement entities;
	KeyBoardHandler keyBoard = new KeyBoardHandler().get();
	//Object for entities to reside in
	
	public SnakeClone() {

		JFrame container = new JFrame("Snake Wars");

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);

		setBounds(0,0,800,600);
		panel.add(this);

		setIgnoreRepaint(true);

		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setFocusable(true);
		requestFocus();
		addKeyListener(keyBoard);

		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		entities = new EntityManagement().get();
		initEntities();
	}
	
	public void initEntities() {
		System.out.println("Starting Entity Init");
		ownSnake = new SnakeEntity(this, "snakeclone/Res/BlueSnake.gif", 375, 550);
		entities.addLivingEntity(ownSnake);
	}
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		System.out.println("Starting game looop");
		//gameloop
		while(gameIsRunning) {
			//work out how long its been since the last update
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			//clear and draw
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
			//poll the controls
			
			if(keyBoard.isLeftPressed()) {
				SnakeEntity snake = (SnakeEntity) entities.getEntity(0);
				snake.turnLeft();
				System.out.println("Turning left");
				keyBoard.setLeftPressed(false);
			}
			
			if(keyBoard.isRightPressed()) {
				SnakeEntity snake = (SnakeEntity) entities.getEntity(0);
				snake.turnRight();
				System.out.println("Turning Right");
				keyBoard.setRightPressed(false);
			}
			//Move and draw the entities
			if (!paused) {
				for (int i=0;i<entities.getEntitySize();i++) {
					Entity entity = entities.getEntity(i);
					entity.move(delta);
				}
			}
			
			for (int i=0;i<entities.getEntitySize();i++) {
				Entity entity = entities.getEntity(i);
				entity.draw(g);
			}
			
			//destroy object and flip buffer
			g.dispose();
			strategy.show();
			
			
			//allows each framebuffer and logic loop to occur no more than
			//100 times a second
			try { Thread.sleep(lastLoopTime + 10 - System.currentTimeMillis()); } catch (Exception e) {}
			
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String argv[]) {
		SnakeClone g = new SnakeClone();
		g.gameLoop();
	}
}
