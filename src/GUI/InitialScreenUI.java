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
import javax.swing.ImageIcon;
import java.awt.Color;

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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1200, 672);
		frmChessproject.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnAdvanced = new JRadioButton("Advanced");
		rdbtnAdvanced.setBackground(Color.WHITE);
		rdbtnAdvanced.setBounds(796, 500, 92, 23);
		panel.add(rdbtnAdvanced);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Intermediate");
		rdbtnIntermediate.setBackground(Color.WHITE);
		rdbtnIntermediate.setBounds(558, 500, 144, 23);
		panel.add(rdbtnIntermediate);
		
		JRadioButton rdbtnBeginner = new JRadioButton("Beginner");
		rdbtnBeginner.setBackground(Color.WHITE);
		rdbtnBeginner.setBounds(340, 500, 144, 23);
		panel.add(rdbtnBeginner);
		
		JLabel lblDificulty = new JLabel("Dificulty");
		lblDificulty.setBounds(584, 405, 66, 15);
		panel.add(lblDificulty);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(576, 588, 114, 25);
		panel.add(btnStart);
		
		JRadioButton rdbtnWhiteTeamPlayer2 = new JRadioButton("White team");
		rdbtnWhiteTeamPlayer2.setBackground(Color.WHITE);
		rdbtnWhiteTeamPlayer2.setBounds(889, 314, 144, 23);
		panel.add(rdbtnWhiteTeamPlayer2);
		
		textFieldPlayer2 = new JTextField();
		textFieldPlayer2.setBounds(940, 273, 124, 19);
		panel.add(textFieldPlayer2);
		textFieldPlayer2.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(846, 275, 66, 15);
		panel.add(lblName_1);
		
		JLabel lblPlayer_1 = new JLabel("");
		lblPlayer_1.setIcon(new ImageIcon(InitialScreenUI.class.getResource("/GUIimages/player2.png")));
		lblPlayer_1.setBounds(889, 195, 193, 63);
		panel.add(lblPlayer_1);
		
		JRadioButton rdbtnWhiteTeamPlayer1 = new JRadioButton("White team");
		rdbtnWhiteTeamPlayer1.setBackground(Color.WHITE);
		rdbtnWhiteTeamPlayer1.setBounds(116, 314, 144, 23);
		panel.add(rdbtnWhiteTeamPlayer1);
		
		textFieldPlayer1 = new JTextField();
		textFieldPlayer1.setBounds(168, 273, 124, 19);
		panel.add(textFieldPlayer1);
		textFieldPlayer1.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(84, 275, 66, 15);
		panel.add(lblName);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(InitialScreenUI.class.getResource("/GUIimages/ChessLogo.png")));
		label.setBounds(393, 22, 409, 140);
		panel.add(label);
		
		JLabel lblPlayer = new JLabel("");
		lblPlayer.setIcon(new ImageIcon(InitialScreenUI.class.getResource("/GUIimages/player1.png")));
		lblPlayer.setBounds(99, 217, 193, 41);
		panel.add(lblPlayer);
		
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
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	}
}
