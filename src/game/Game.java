package game;

import board.BoardGame;
import chronometer.Chronometer;
import pieces.*;

import java.util.ArrayList;

public class Game {
	
	private Player player1, player2;
	private BoardGame boardGame; //Tabuleiro de xadrez
	private static int gameNumber = 0; //Numero do jogo que será iniciado (static = caracterista da classe jogo, antes de ser instânciada)
	private int level; //Nivel de dificuldade do jogo | Intimamente relacionado ao funcionamento do cronômetro
	private Chronometer chronometer;
	
	//Construtor do Game vai instanciar todos os seus objetos devido a agregação forte relacionada a ele
	public Game(String nickPlayer1, String nickPlayer2, boolean whitePlayer1, boolean whitePlayer2,int level) {
		this.player1 = new Player(nickPlayer1, whitePlayer1);
		this.player2 = new Player(nickPlayer2, whitePlayer2);
		this.boardGame = new BoardGame();
		++gameNumber; //A cada jogo criado seu número irá ser modificado
		this.level = level;
	}
	
	/*Método em que é recebido um jogador, uma peça e o tabuleiro em que o jogo está acontecendo para realizar a movimentação
	das peças, este metodo será chamado quando o jogador clicar em cima da peça do seu próprio time e apartir
	dai as possiveis movimentações serão mostradas*/
	
	public ArrayList<Position> showPossiblesMoves(Player player, Piece piece){
		ArrayList<Position>moves;
		moves = null;
		
		//verificando se o jogador é do time branco e se ele clicou em uma peça do time branco
		if(player.isWhitePlayer()) {
			if(piece.isWhite()){
				moves = piece.getMoves(); //Método polimorfico, logo não precisa de instance of, nem casting
			}
			else {
				//A peça é do time preto e o jogador é do time branco
			}
		}
		//Verificando se o jogador é do time preto e se ele clicou em uma peça do time preto
		else {
			if(!player.isWhitePlayer()) {
				if(!piece.isWhite()) {
					moves = piece.getMoves(); //Método polimorfico, logo não precisa de instance of, nem casting
				}
				else {
					//A peça é do time preto e a peça é do time branco
				}
			}
		}
		//retorna as possiveis movimentações de uma peça para a interface grafica mostrar na tela
		return moves;
	}
	
	//Método que é chamado quando um peão atravessa o tabuleiro ou pode ser chamado depois de realizado uma movimentação
	public void turnQueen(PawnP pawn) {
		//Tratanto para os peões do time branco e se ele atravessou o mapa
		if(pawn.isWhite() && (pawn.getPosition().getX() == 7)) {
			this.getBoardGame().getBoardMatrix()[7][pawn.getPosition().getY()] = new QueenP(true, pawn.getPosition());
		}
		//Tratando para os peões do time preto e se ele atravessou o mapa
		else if(!pawn.isWhite() && (pawn.getPosition().getX() == 0)){
			this.getBoardGame().getBoardMatrix()[0][pawn.getPosition().getY()] = new QueenP(false, pawn.getPosition());
		}
		
	}
	
	//Getter e Setters da classe Game

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public static int getGameNumber() {
		return gameNumber;
	}

	public static void setGameNumber(int gameNumber) {
		Game.gameNumber = gameNumber;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Chronometer getChronometer() {
		return chronometer;
	}

	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}
	
	
}
