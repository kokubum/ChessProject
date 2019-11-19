package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import game.Game;
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
		frmChessproject.setLocationRelativeTo(null);
		frmChessproject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChessproject.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1200, 672);
		frmChessproject.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnAdvanced = new JRadioButton("Advanced");
		rdbtnAdvanced.setFont(new Font("Arial",Font.BOLD,15));
		rdbtnAdvanced.setBackground(Color.WHITE);
		rdbtnAdvanced.setBounds(796, 500, 120, 23);
		panel.add(rdbtnAdvanced);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Intermediate");
		rdbtnIntermediate.setFont(new Font("Arial",Font.BOLD,15));
		rdbtnIntermediate.setBackground(Color.WHITE);
		rdbtnIntermediate.setBounds(558, 500, 150, 23);
		panel.add(rdbtnIntermediate);
		
		JRadioButton rdbtnBeginner = new JRadioButton("Beginner");
		rdbtnBeginner.setFont(new Font("Arial",Font.BOLD,15));
		rdbtnBeginner.setBackground(Color.WHITE);
		rdbtnBeginner.setBounds(340, 500, 110, 23);
		panel.add(rdbtnBeginner);
		
		JLabel lblDificulty = new JLabel("");
		lblDificulty.setIcon(new ImageIcon(InitialScreenUI.class.getResource("/GUIimages/Dificulty.jpg")));
		lblDificulty.setBounds(500, 265, 300, 260);
		panel.add(lblDificulty);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(576, 588, 114, 25);
		panel.add(btnStart);
		
		JRadioButton rdbtnWhiteTeamPlayer2 = new JRadioButton("White Team");
		rdbtnWhiteTeamPlayer2.setFont(new Font("Arial",Font.BOLD,17));
		rdbtnWhiteTeamPlayer2.setBackground(Color.WHITE);
		rdbtnWhiteTeamPlayer2.setBounds(889, 314, 150, 23);
		panel.add(rdbtnWhiteTeamPlayer2);
		
		textFieldPlayer2 = new JTextField();
		textFieldPlayer2.setBounds(940, 273, 124, 19);
		panel.add(textFieldPlayer2);
		textFieldPlayer2.setColumns(10);
		
		JLabel lblName_1 = new JLabel("NAME:");
		lblName_1.setFont(new Font("Arial",Font.BOLD,15));
		lblName_1.setBounds(846, 275, 75, 15);
		panel.add(lblName_1);
		
		JLabel lblPlayer_1 = new JLabel("");
		lblPlayer_1.setIcon(new ImageIcon(InitialScreenUI.class.getResource("/GUIimages/player2.png")));
		lblPlayer_1.setBounds(889, 195, 193, 63);
		panel.add(lblPlayer_1);
		
		JRadioButton rdbtnWhiteTeamPlayer1 = new JRadioButton("White Team");
		rdbtnWhiteTeamPlayer1.setFont(new Font("Arial",Font.BOLD,17));
		rdbtnWhiteTeamPlayer1.setBackground(Color.WHITE);
		rdbtnWhiteTeamPlayer1.setBounds(116, 314, 150, 23);
		panel.add(rdbtnWhiteTeamPlayer1);
		
		textFieldPlayer1 = new JTextField();
		textFieldPlayer1.setBounds(168, 273, 124, 19);
		panel.add(textFieldPlayer1);
		textFieldPlayer1.setColumns(10);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Arial",Font.BOLD,15));
		lblName.setBounds(84, 275, 75, 15);
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
				//Começar jogo
				String nickName1, nickName2;
				boolean isWhite1, isWhite2;
				boolean open = false;
				GameLevel level = null;
				
				//Pegando nome dos jogadores nos FieldText
				nickName1 = textFieldPlayer1.getText();
				nickName2 = textFieldPlayer2.getText();
				
				//Pegando valores boolean dos RadioButton
				isWhite1 = rdbtnWhiteTeamPlayer1.isSelected();
				isWhite2 = rdbtnWhiteTeamPlayer2.isSelected();
				
				if(!nickName1.equals("") && !nickName2.equals("")) {
					if(isWhite1 == true || isWhite2 == true) {
						if(rdbtnBeginner.isSelected()) {
							level = GameLevel.BEGINNER;
							open = true;
						}
						else if(rdbtnIntermediate.isSelected()) {
							level = GameLevel.INTERMEDIATE;
							open = true;
						}
						else if(rdbtnAdvanced.isSelected()) {
							level = GameLevel.ADVANCED;
							open = true;
						}
					}
				}
			
				if(open == true) {
					Game.setGameNumber(0);
					GameUI gameWindow = new GameUI(nickName1, nickName2, isWhite1, isWhite2, level);
					gameWindow.setVisible(true);
					frmChessproject.dispose();
				}
				else {
					JOptionPane.showMessageDialog(InitialScreenUI.this.frmChessproject,"Missing Information!!","ERROR 404",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		rdbtnBeginner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnBeginner.setBackground(Color.GREEN);
				rdbtnIntermediate.setSelected(false);
				rdbtnIntermediate.setBackground(Color.WHITE);
				rdbtnAdvanced.setSelected(false);
				rdbtnAdvanced.setBackground(Color.WHITE);
			}
		});
		
		rdbtnIntermediate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnIntermediate.setBackground(Color.YELLOW);
				rdbtnBeginner.setSelected(false);
				rdbtnBeginner.setBackground(Color.WHITE);
				rdbtnAdvanced.setSelected(false);
				rdbtnAdvanced.setBackground(Color.WHITE);
			}
		});
		
		rdbtnAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAdvanced.setBackground(Color.RED);
				rdbtnBeginner.setSelected(false);
				rdbtnBeginner.setBackground(Color.WHITE);
				rdbtnIntermediate.setSelected(false);
				rdbtnIntermediate.setBackground(Color.WHITE);
			}
		});
	}
}
