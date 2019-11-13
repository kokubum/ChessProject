package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.Game;
import game.enums.GameLevel;
import pieces.PawnP;
import pieces.Piece;
import pieces.Position;

public class GameUI extends JFrame {

	private ButtonHandler handler;//Atributo para atuar como actionListener para os botões
	private BoardUI boardUI;//Tabuleiro de botões
	private Game game;//Base de jogo
	

	//Construtor para abrir a tela principal do jogo
	public GameUI(String nickName1, String nickName2, boolean isWhite1, boolean isWhite2, GameLevel level) {
		this.game = new Game(nickName1,nickName2,isWhite1,isWhite2,level);
		this.setTitle("ChessProject - Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(1500,1000);
		boardUI = new BoardUI(this.game.getBoardGame());
		this.handler = new ButtonHandler();
		this.addActionListener(this.handler);
		
		this.getContentPane().add(boardUI);		
	
	}
	
	private class ButtonHandler implements ActionListener{
		//Atributo utilizado para saber se o usuario vai clicar ainda em sua peça que quer mover, ou seja, ainda não apareceu o caminho em verde
		private boolean firstClick = true;
		
		private boolean falseClick;
		private Piece piece=null;
		
		@Override
		public void actionPerformed(ActionEvent event) {
			falseClick = false;//Atributo criado para auxiliar o click em butões que não queremos utilizar
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
		}
	}
	//Checa se o jogador que clicou é do mesmo time da peça que ele clicou
	/*Esse método foi criado pois, mesmo sabendo que os movimentos iria retornar como null quando jogadores de uma cor tentarem clicar
	 * em peças de outra cor, eu precisava diferenciar o null desse acontecimento para o null de quando o jogador não clicar em uma
	 * peça, pra poder analisar de uma forma em cada casa, e poder sair do for no momento correto
	 */
	public boolean isSameColor(Game game,int row,int colum) {
		if(game.getPlayerTurn().isWhitePlayer()== game.getBoardGame().getBoardMatrix()[row][colum].isWhite()) {
			return true;
		}
		return false;
	}
	
	//Método que recebe o as possiveis movimentações e entao muda a cor dos botões que as representam
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
	
	
	
	//Método para movimentar uma peça no tabuleiro de botões
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
	
	public static void main(String[] args) {
		GameUI game = new GameUI("Erick","Alberto",true,false,GameLevel.BEGINNER);
		game.setVisible(true);
	}
	

}
