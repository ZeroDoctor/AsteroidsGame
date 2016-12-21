package com.zerulus.entity;

import java.awt.image.BufferedImage;

public class Player 
{
	private BufferedImage[] flyingUp = {Sprite.getSprite(0, 2), Sprite.getSprite(1,  2), Sprite.getSprite(2, 2)};
	private BufferedImage[] staying = {Sprite.getSprite(0, 0)};
	private BufferedImage[] startingUp = {Sprite.getSprite(1, 0), Sprite.getSprite(2, 0), Sprite.getSprite(0, 1), Sprite.getSprite(1, 1), Sprite.getSprite(2, 1)}
	
	private Animation flyUp = new Animation(flyingUp, 10);
	private Animation stay = new Animation(staying, 10);
	private Animation startUP = new Animation(startingUp, 10);
	
	
}

/* STILL NEEDS
 This is the actual animation
private Animation animation = standing;

In your update or tick method you will have:

animation.update();

In your render or draw method you will have:

g.drawImage(animation.getSprite(), x, y, null);

Lets say you have implemented MouseListener

public void mousePressed(MouseEvent e) {
    animation = walkLeft;
    animation.start();
}

public void mouseReleased(MouseEvent e) {
    animation.stop();
    animation.reset();
    animation = standing;
}
 */
*/