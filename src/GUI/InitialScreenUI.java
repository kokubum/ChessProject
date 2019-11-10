package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import game.enums.GameLevel;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InitialScreenUI {

	private JFrame frmChessproject;
	private JTextField textFieldPlayer1;
	private JTextField textFieldPlayer2;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialScreenUI window = new InitialScreenUI();
					window.frmChessproject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public InitialScreenUI() {
		initialize();
	}

	
	private void initialize() {
		frmChessproject = new JFrame();
		frmChessproject.setTitle("ChessProject");
		frmChessproject.setBounds(100, 0, 1200, 700);
		frmChessproject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChessproject.getContentPane().setLayout(null);
		
		JLabel lblChess = new JLabel("Chess");
		lblChess.setBounds(570, 34, 66, 15);
		frmChessproject.getContentPane().add(lblChess);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setBounds(127, 117, 66, 15);
		frmChessproject.getContentPane().add(lblPlayer);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(77, 158, 66, 15);
		frmChessproject.getContentPane().add(lblName);
		
		textFieldPlayer1 = new JTextField();
		textFieldPlayer1.setBounds(146, 156, 124, 19);
		frmChessproject.getContentPane().add(textFieldPlayer1);
		textFieldPlayer1.setColumns(10);
		
		JRadioButton rdbtnWhiteTeamPlayer1 = new JRadioButton("White team");
		
		rdbtnWhiteTeamPlayer1.setBounds(108, 206, 144, 23);
		frmChessproject.getContentPane().add(rdbtnWhiteTeamPlayer1);
		
		JLabel lblPlayer_1 = new JLabel("Player2");
		lblPlayer_1.setBounds(924, 117, 66, 15);
		frmChessproject.getContentPane().add(lblPlayer_1);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(880, 158, 66, 15);
		frmChessproject.getContentPane().add(lblName_1);
		
		textFieldPlayer2 = new JTextField();
		textFieldPlayer2.setBounds(942, 156, 124, 19);
		frmChessproject.getContentPane().add(textFieldPlayer2);
		textFieldPlayer2.setColumns(10);
		
		JRadioButton rdbtnWhiteTeamPlayer2 = new JRadioButton("White team");
		rdbtnWhiteTeamPlayer2.setBounds(922, 206, 144, 23);
		frmChessproject.getContentPane().add(rdbtnWhiteTeamPlayer2);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStart.setBounds(509, 546, 114, 25);
		frmChessproject.getContentPane().add(btnStart);
		
		JLabel lblDificulty = new JLabel("Dificulty");
		lblDificulty.setBounds(526, 305, 66, 15);
		frmChessproject.getContentPane().add(lblDificulty);
		
		JRadioButton rdbtnBeginner = new JRadioButton("Beginner");
		rdbtnBeginner.setBounds(227, 405, 144, 23);
		frmChessproject.getContentPane().add(rdbtnBeginner);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Intermediate");
		rdbtnIntermediate.setBounds(492, 405, 144, 23);
		frmChessproject.getContentPane().add(rdbtnIntermediate);
		
		JRadioButton rdbtnAdvanced = new JRadioButton("Advanced");
		rdbtnAdvanced.setBounds(762, 405, 144, 23);
		frmChessproject.getContentPane().add(rdbtnAdvanced);
		
		rdbtnWhiteTeamPlayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnWhiteTeamPlayer2.setSelected(false);
			}
		});
		
		rdbtnWhiteTeamPlayer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnWhiteTeamPlayer1.setSelected(false);
			}
		});
		
		rdbtnBeginner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnIntermediate.setSelected(false);
				rdbtnAdvanced.setSelected(false);
			}
		});
		
		rdbtnIntermediate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnBeginner.setSelected(false);
				rdbtnAdvanced.setSelected(false);
			}
		});
		
		rdbtnAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnBeginner.setSelected(false);
				rdbtnIntermediate.setSelected(false);
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Começar jogo
				String nickName1, nickName2;
				boolean isWhite1, isWhite2;
				//Caso o usuario n escolha a difuldade, a mesma será fácil
				GameLevel level = GameLevel.BEGINNER;
				
				//Pegando nome dos jogadores nos FieldText
				nickName1 = textFieldPlayer1.getName();
				nickName2 = textFieldPlayer2.getName();
				
				//Pegando valores boolean dos RadioButton
				isWhite1 = rdbtnWhiteTeamPlayer1.isSelected();
				isWhite2 = rdbtnWhiteTeamPlayer2.isSelected();
				
				if(rdbtnBeginner.isSelected()) {
					level = GameLevel.BEGINNER;
				}
				else if(rdbtnIntermediate.isSelected()) {
					level = GameLevel.INTERMEDIATE;
				}
				else if(rdbtnAdvanced.isSelected()) {
					level = GameLevel.ADVANCED;
				}
				
				GameUI gameWindow = new GameUI(nickName1, nickName2, isWhite1, isWhite2, level);
				gameWindow.setVisible(true);
				frmChessproject.dispose();
			}
		});
	}
}
