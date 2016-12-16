package com.zerulus.main;

import javax.swing.*;


public class Game extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Game() {
		setTitle("New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(new GamePanel());
		pack();
		setVisible(true);
	}
}
