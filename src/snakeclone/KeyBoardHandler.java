/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Handles keyboard events
 * @author Manning
 */
public class KeyBoardHandler extends KeyAdapter{
	//variables for relaying info to game loop
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
	public void KeyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
	}
}
