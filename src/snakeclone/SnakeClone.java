/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import snakeclone.entity.EnemySnakeEntity;
import snakeclone.entity.Entity;
import snakeclone.entity.FoodEntity;
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
	private SnakeEntity ownSnake;
	private EnemySnakeEntity enemySnake;
	private EntityManagement entities;
	private KeyBoardHandler keyBoard = new KeyBoardHandler().get();
	public int foodLeft = 0;
	private String message = "";
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
			@Override
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
	
	
	public final void initEntities() {
		System.out.println("Starting Entity Init");
		ownSnake = new SnakeEntity(this, "snakeclone/Res/BlueSnake.gif", 400, 300);
		entities.addLivingEntity(ownSnake);
		foodLeft = 0;
	}
	
	public final void loose() {
		paused = true;
		message = "You loose. Try Again? Press N";
		ArrayList snakeTail = ownSnake.getSnakeTail();
		for(int i=0; snakeTail.size() > 0;){
			entities.addDeadEntity((Entity) snakeTail.get(i));
			snakeTail.remove(i);
		}
	}
	
	public final void pause() {
		message = "Paused. Press P";
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
			
			if (paused) {
				g.setColor(Color.WHITE);
				g.drawString(message, 300, 300);
			}
			
			//Entity Collision BruteForce
			for (int p=0;p<entities.getEntitySize();p++) {
				for (int s=p+1;s<entities.getEntitySize();s++) {
					Entity me = (Entity) entities.getEntity(p);
					Entity him = (Entity) entities.getEntity(s);
					
					if (me.collidesWith(him)) {
						me.collidedWith(him);
						him.collidedWith(me);
					}
				}
			}
			
			//Removes Entities destroyed in collisions
			entities.destroyDeadEntities();
			
			//Makes sure snake is in bounds and destroys it if it isn't and
			//displays the loose message
			if (ownSnake.checkPostion()){
				this.loose();
			}
			
			//Generates a new food if there is none left
			if(foodLeft <= 0) {
				FoodEntity food = new FoodEntity(this, "snakeclone/Res/Food.gif");
				foodLeft++;
				entities.addLivingEntity(food);
				System.out.println("Food Created");
			}
			
			//poll the controls
			if(keyBoard.isLeftPressed()) {
				ownSnake.turnLeft();
				System.out.println("Turning left");
				keyBoard.setLeftPressed(false);
			}
			if(keyBoard.isRightPressed()) {
				ownSnake.turnRight();
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
			try { Thread.sleep(lastLoopTime + 100 - System.currentTimeMillis()); } catch (Exception e) {}
			
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
