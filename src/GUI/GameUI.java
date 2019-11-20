package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import game.Game;
import game.Player;
import game.enums.GameLevel;
import game.interf.GameUInterface;
import pieces.PawnP;
import pieces.Piece;
import pieces.Position;

public class GameUI extends JFrame implements GameUInterface {

	private ButtonHandler handler;//Atributo para atuar como actionListener para os botões
	private JButton startTheGame; //Botão que da inicio ao cronometro e ao jogo
	private JLabel playerTurn; //Label que vai dizer de quem é a vez
	private JLabel numberOfGame;//Label para indicar qual a numeraçao do jogo
	private JTextArea movesMade; //Text Area que irá imprimir cada movimento feito
	private JTextArea checkArea; //Text area pra indicar check e checkmate
	private JScrollPane scrollTextArea; //Scroll para o JTextArea acima
	private JScrollPane scrollCheckArea; // Scroll para a área de check
	private Timer timerBase;  //Método criado aqui para poder adicionar o action listener ao Timer daqui, e assim ter acesso ao cronometro em funcionamento
	private BoardUI boardUI;//Tabuleiro de botões
	private Game game;//Base de jogo
	
	private CheckMateUI checkMateScreen; //Tela de checkMate
	

	//Construtor para abrir a tela principal do jogo
	public GameUI(String nickName1, String nickName2, boolean isWhite1, boolean isWhite2, GameLevel level) {
		this.game = new Game(nickName1,nickName2,isWhite1,isWhite2,level);
		
		this.setTitle("ChessProject - Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setLayout(null);
	
		
		this.setSize(1300,835);
		this.setLocationRelativeTo(null);
		boardUI = new BoardUI(this.game.getBoardGame());
		this.handler = new ButtonHandler();
		this.addActionListener(this.handler);
		
		this.startTheGame = new JButton("START");
		this.startTheGame.setFont(new Font("Arial",Font.BOLD,20));
		this.startTheGame.setBackground(new Color(133,94,66));
		this.startTheGame.setBorder(BorderFactory.createRaisedBevelBorder());
		this.startTheGame.setSize(200, 70);
		this.startTheGame.setLocation(950,550);
		this.startTheGame.addActionListener(this.handler);
		
		this.playerTurn = new JLabel("PLAYER: "+this.game.getPlayerTurn().getNickName());
		this.playerTurn.setSize(200,70);
		this.playerTurn.setLocation(975,740);
		this.playerTurn.setFont(new Font("Arial",Font.BOLD,17));
		
		this.numberOfGame = new JLabel("Game of Number: "+Game.getGameNumber());
		this.numberOfGame.setSize(200, 70);
		this.numberOfGame.setLocation(955,600);
		this.numberOfGame.setFont(new Font("Arial",Font.BOLD,17));
		
		this.movesMade = new JTextArea();
		this.movesMade.setBackground(Color.white);
		this.movesMade.setBorder(BorderFactory.createLoweredBevelBorder());
		this.movesMade.setText("----------------MOVES-----------------\n");
		this.movesMade.setFont(new Font("Arial",Font.PLAIN,19));
		this.movesMade.setEditable(false);
		
		this.scrollTextArea = new JScrollPane(this.movesMade, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollTextArea.setSize(300, 350);
		this.scrollTextArea.setLocation(900, 170);
		
		this.checkArea = new JTextArea();
		this.checkArea.setBackground(Color.white);
		this.checkArea.setBorder(BorderFactory.createLoweredBevelBorder());
		this.checkArea.setEditable(false);
		this.checkArea.setText("-------------CHECK AREA--------------\n");
		this.checkArea.setFont(new Font("Arial",Font.PLAIN,19));
		
		this.scrollCheckArea = new JScrollPane(this.checkArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollCheckArea.setSize(300,110);
		this.scrollCheckArea.setLocation(900, 30);
		
		this.timerBase = new Timer(1,this.handler);
		this.getContentPane().add(this.numberOfGame);
		this.getContentPane().add(this.scrollCheckArea);
		this.getContentPane().add(this.scrollTextArea);
		this.getContentPane().add(this.startTheGame);
		this.getContentPane().add(this.game.getChronometer());
		this.getContentPane().add(this.playerTurn);
		this.getContentPane().add(this.boardUI);

		
	
	}
	
	private class ButtonHandler implements ActionListener{
		//Atributo utilizado para saber se o usuario vai clicar ainda em sua peça que quer mover, ou seja, ainda não apareceu o caminho em verde
		private boolean firstClick = true;
		
		private boolean falseClick;
		private boolean start = false;
		private Piece piece=null;
		
		@Override
		public void actionPerformed(ActionEvent event) {
			falseClick = false;//Atributo criado para auxiliar o click em butões que não queremos utilizar
			if(start == true) {
				if(firstClick == true) {
					//Sendo o primeiro clique, eu testo qual botão do tabuleiro recebeu o evento (clique)
					for(int i=0;i<8 && (firstClick == true && falseClick == false);i++) {
						for(int j=0;j<8 && (firstClick == true && falseClick == false);j++) {
							//Checo o botão e posição do botão que ta recebendo o evento
							if(event.getSource() == GameUI.this.boardUI.getBoard()[i][j]) {
								//Se a posição que foi clicada, no tabuleiro base, fizer referencia a uma peça, entao...
								if(GameUI.this.game.getBoardGame().getBoardMatrix()[i][j]!=null) {
									//Testamos se a peça clicado tem a mesma cor que o jogador da vez
									if(isSameColor(GameUI.this.game,i,j)) {
										//Se tiver, eu guardo a peça para ser usada posteriormente
										piece = GameUI.this.game.getBoardGame().getBoardMatrix()[i][j];
										//Chamo o método para aparecer o caminho em verde
										GameUI.this.showColorMoves(i, j);
										//Seto o first click para falso
										firstClick = false;
									}
									else {
										//Se o jogador da vez clicar em uma peça de uma cor diferente, entao é um falso click
										falseClick = true;
									}
								
								}
								else {
									//Se o jogador clicar em uma posição vazia, tambem é um falso click
									falseClick = true;
								}
							
							}
						}
					}
				}
				else {
					//Usuario ja clicou e ja tem um caminho possivel para usar
					for(int i=0;i<8 && (firstClick == false && falseClick == false);i++) {
						for(int j=0;j<8 && (firstClick == false && falseClick == false);j++) {
							//Checamos então qual posicao ele clicou dessa vez
							if(event.getSource() == GameUI.this.boardUI.getBoard()[i][j]) {	
								//Independente de onde ele clicou, o tabuleiro volta a sua cor normal
								GameUI.this.boardUI.setColorBoard();
								//Se for uma posição válida, ou for uma posição invalida(sem peça)
								if(GameUI.this.changeTheIcons(i,j,piece)==true || GameUI.this.game.getBoardGame().getBoardMatrix()[i][j]==null) {
									firstClick = true;
									//FirstClick volta a ser true, pois ele tera que escolher a peça novamente, e tambem para sair do for
								
								}
								//Se ele clicou em outra peça do seu proprio time com intuito de não mover a anterior e sim a peça agora escolhida, entao...
								else if(isSameColor(GameUI.this.game,i,j)){
									//Recebo essa nova peça como uma nova referencia
									piece = GameUI.this.game.getBoardGame().getBoardMatrix()[i][j];
									//Mostro o novo caminho possivel
									GameUI.this.showColorMoves(i, j);
									//Seto falseClick como true para sair do for
									falseClick = true;
								}
							
							}
						}
					}
				
				}
				//A cada milisegundo eu testo se o jlabel do cronometro atingiu seu limite
				//Não posso só setar para zero e mudar o jogador, pois quando o cronometro é setado para zero
				//em outra situação, o jogador acaba nao mudando, e dando problemas
				if(event.getSource() instanceof Timer) {
					if(GameUI.this.game.getLevel() == GameLevel.ADVANCED) {
						if(GameUI.this.game.getChronometer().getLabelTimer().getText().equals("00 : 14 : 999")) {
							GameUI.this.changeTurn(GameUI.this.game.getPlayerTurn());
						}
					}
					else {
						if(GameUI.this.game.getLevel() == GameLevel.INTERMEDIATE) {
							if(GameUI.this.game.getChronometer().getLabelTimer().getText().equals("00 : 29 : 999")) {
								GameUI.this.changeTurn(GameUI.this.game.getPlayerTurn());
							}
						}
						else {
							if(GameUI.this.game.getChronometer().getLabelTimer().getText().equals("00 : 59 : 999")) {
								GameUI.this.changeTurn(GameUI.this.game.getPlayerTurn());
							}
						}
					}
				}
				if(event.getSource() == GameUI.this.startTheGame) {
					GameUI.this.game.setGameOver(true);
					GameUI.this.winnerByAbandonment();
					GameUI.this.gameOver();
				}
			
			}
			else {
				if(event.getSource() == GameUI.this.startTheGame){				
					GameUI.this.startTheGame.setText("GIVE UP");
					this.start = true;
					GameUI.this.game.getChronometer().getTime().start();
					GameUI.this.timerBase.start();			
				}
			}
		}
		
		
	}
	
	//Método para determinar o ganhador quando houver desisitência
	public void winnerByAbandonment() {
		this.changeTurn(this.game.getPlayerTurn());
		this.game.getPlayerTurn().setWinner(true);
	}
	
	//Método para checar se houve check mate e abre a tela
	public void gameOver() {
		if(this.game.isGameOver() == true) {
			this.game.getChronometer().getTime().stop();
			this.checkMateScreen = new CheckMateUI(this);
			this.checkMateScreen.setVisible(true);
		}
	}

	
	//Método para reiniciar o cronometro
	
	public void restartChronometer() {
		this.game.getChronometer().setMiliseconds(0);
		this.game.getChronometer().setSeconds(0);
		this.game.getChronometer().setMinutes(0);
		this.game.getChronometer().getLabelTimer().setText("00 : 00 : 000");
	}
	//Checa se o jogador que clicou é do mesmo time da peça que ele clicou
	/*Esse método foi criado pois, mesmo sabendo que os movimentos iria retornar como null quando jogadores de uma cor tentarem clicar
	 * em peças de outra cor, eu precisava diferenciar o null desse acontecimento para o null de quando o jogador não clicar em uma
	 * peça, pra poder analisar de uma forma em cada casa, e poder sair do for no momento correto
	 */
	@Override
	public boolean isSameColor(Game game,int row,int colum) {
		if(game.getPlayerTurn().isWhitePlayer()== game.getBoardGame().getBoardMatrix()[row][colum].isWhite()) {
			return true;
		}
		return false;
	}
	
	//Método que recebe o as possiveis movimentações e entao muda a cor dos botões que as representam
	@Override
	public void showColorMoves(int row,int colum) {
		Piece piece = this.game.getBoardGame().getBoardMatrix()[row][colum];
		ArrayList<Position> moves=null;
		//Se a peça for instancia de um peão, é preciso fazer um casting para usar o overload do método showPossibleMoves()
		if(piece instanceof PawnP) {
			PawnP pawn = (PawnP)piece;
			moves = this.game.showPossibleMoves(this.game.getPlayerTurn(),pawn);
		}
		else {
			moves = this.game.showPossibleMoves(this.game.getPlayerTurn(), piece);	
		}
		 
		Position position = null;
		for(int i=0;i<moves.size();i++) {
			position = moves.get(i);
			//Estabelece no tabuleiro, uma cor verde claro para as possiveis movimentações existentes
			this.boardUI.getBoard()[position.getX()][position.getY()].setBackground(new Color(144,238,144));
		}
	}
	//Método para adicionar um movimento na JTextArea
	public void writeMovement(Position before,Position after,Piece piece) {
		String color;
		if(piece.isWhite() == true) {
			color = "White";
		}
		else {
			color = "Black";
		}
		this.movesMade.setText(this.movesMade.getText()+
													"\n" +
													this.realPosition(before)+
													" -> " +
													this.realPosition(after)+
													" ("+color+" "+piece.getTypePiece() + ")");
	}
	
	
	//Método para transformar uma posição da matriz do tabuleiro, em uma posição do xadrez
	public String realPosition(Position position) {
		//Para cada coluna associa uma letra (a=0,b=1,c=2...)
		char colum = (char)(position.getY()+'a');
		//Transformo esse char para String
		String pos = String.valueOf(colum);
		//Pego a linha correspondente, se for 0 = 8, 1 = 7 ...
		//Concateno com a string anterior
		pos = pos.concat(String.valueOf(8-position.getX()));
		
		return pos;
	}
	
	//Método para escrever se o rei esta em check
	public void writeCheck(Piece piece) {
		String color;
		if(piece.isWhite() == true) {
			color = "Black";
		}
		else {
			color = "White";
		}
		if(this.game.getKingInCheck() == true) { 
			this.checkArea.setText(this.checkArea.getText() + color + " King in Check\n");
			this.game.setKingInCheck(false);
		}
	}
	
	//Método para mudar o jogador toda vez que uma peça é movida
	//Recebe como parâmetro o ultimo jogador a realizar o movimento
	@Override
	public void changeTurn(Player player) {
		if(this.game.getPlayer1().equals(player)) {
			this.game.setPlayerTurn(this.game.getPlayer2());
		}
		else {
			this.game.setPlayerTurn(this.game.getPlayer1());
		}
		this.playerTurn.setText("PLAYER: "+ this.game.getPlayerTurn().getNickName());
	}
	
	
	//Método para movimentar uma peça no tabuleiro de botões
	@Override
	public boolean changeTheIcons(int row,int colum,Piece piece) {
		//Guarda a posição que ele quer se mover
		Position afterPos = new Position(row,colum);
		//Guardo a posiçao em que a peça primeiramente se encontra
		Position beforePos = new Position(piece.getPosition().getX(),piece.getPosition().getY());
		//Se for possivel mover a peça referenciada então...
		if(this.game.movePiece(afterPos, piece, this.game.getPlayerTurn()) == true) {
			//Se a peça que for se mover for um peão, e a posição que ele vai é uma das pontas, entao sua posição agora instancia uma rainha
			if(piece instanceof PawnP && afterPos.getX()%7==0) {
				PawnP pawn = (PawnP)piece;
				this.game.turnQueen(pawn);
				//Faço a peça fazer referencia a nova instancia criada em sua posição que agora será uma new QueenP()
				piece = this.game.getBoardGame().getBoardMatrix()[afterPos.getX()][afterPos.getY()];
			}
			//Salvo a imagem referente a peça que iremos mover (que caso entre no if anterior, então será uma nova instânia de rainha)
			ImageIcon resizeImage = this.boardUI.resizeImage(piece.getImageIcon());
			//Atribuimos a imagem salvo ao novo botao que contem a posição para onde o usuário quis movimentar
			this.boardUI.getBoard()[afterPos.getX()][afterPos.getY()].setIcon(resizeImage);
			//Seto a imagem do botão que iria se mover como null, para sumir a imagem da peça
			this.boardUI.getBoard()[beforePos.getX()][beforePos.getY()].setIcon(null);
			
			this.changeTurn(this.game.getPlayerTurn());
			this.writeMovement(beforePos, afterPos,piece);
			this.writeCheck(piece);
			this.restartChronometer();
			this.gameOver();
			
			return true;
		}
		
		return false;
	}
	
	
	
	

	//Adicionando ActionListener a cada um dos botões
	public void addActionListener(ButtonHandler handler) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.boardUI.getBoard()[i][j].addActionListener(handler);
			}
		}
	}
	
	//Getter do game para o checkMateScreen
	public Game getGame() {
		return this.game;
	}

	

}
