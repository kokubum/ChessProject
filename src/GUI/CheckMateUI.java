package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Game;
import game.enums.GameLevel;


public class CheckMateUI extends JFrame {
	
	private ImageIcon background;
	private JLabel labelImage;
	private JButton rematch;
	private JButton newGame;
	private JLabel winner;
	
	private ButtonHandler handler;
	
	private GameUI gameUI;
	
	
	public CheckMateUI(GameUI gameUI) {
		this.background = new ImageIcon("src/GUIimages/checkMate.jpg");
		this.setTitle("CHECKMATE");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.gameUI = gameUI;
		this.handler = new ButtonHandler();
		
		
		this.labelImage = new JLabel();
		this.labelImage.setIcon(this.background);
		
		this.rematch = new JButton("Rematch");
		this.rematch.setSize(150, 50);
		this.rematch.setLocation(270, 100);
		this.rematch.setBorder(BorderFactory.createRaisedBevelBorder());
		this.rematch.setBackground(Color.LIGHT_GRAY);
		this.rematch.addActionListener(this.handler);
		
		this.newGame = new JButton("New Game");
		this.newGame.setSize(150, 50);
		this.newGame.setLocation(270, 30);
		this.newGame.setBorder(BorderFactory.createRaisedBevelBorder());
		this.newGame.setBackground(Color.LIGHT_GRAY);
		this.newGame.addActionListener(this.handler);
		
		this.winner = new JLabel("WINNER: " + this.winnerName(gameUI.getGame()));
		this.winner.setSize(150,50);
		this.winner.setLocation(280, 170);
		this.winner.setFont(new Font("Arial",Font.BOLD,15));
		
		
		this.getContentPane().add(this.winner);
		this.getContentPane().add(this.newGame);
		this.getContentPane().add(this.rematch);
		this.getContentPane().add(labelImage);
		
		
	}
	private class ButtonHandler implements ActionListener{
		private String nickName1 = CheckMateUI.this.gameUI.getGame().getPlayer1().getNickName();
		private String nickName2 = CheckMateUI.this.gameUI.getGame().getPlayer2().getNickName();
		private boolean isWhitePlayer1= CheckMateUI.this.gameUI.getGame().getPlayer1().isWhitePlayer();
		private boolean isWhitePlayer2 = CheckMateUI.this.gameUI.getGame().getPlayer2().isWhitePlayer();
		private GameLevel level = CheckMateUI.this.gameUI.getGame().getLevel();
		
		@Override
		public void actionPerformed(ActionEvent event) {
			CheckMateUI.this.dispose();
			CheckMateUI.this.gameUI.dispose();
			if(event.getSource() == CheckMateUI.this.rematch) {
				
				CheckMateUI.this.gameUI = new GameUI(nickName1,nickName2,isWhitePlayer1,isWhitePlayer2,level);
				CheckMateUI.this.gameUI.setVisible(true);
				
			}
			else {
				InitialScreenUI.main(null);
				
			}
			
		}
	
		
	}
	//Método para pegar o nome do ganhador
	public String winnerName(Game game) {
		if(game.getPlayer1().isWinner() == true) {
			return game.getPlayer1().getNickName();
		}
		else {
			return game.getPlayer2().getNickName();
		}
	}
	
	
	
	
}