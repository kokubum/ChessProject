package game;

import board.BoardGame;
import chronometer.Chronometer;

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
		++gameNumber; //A cada jogo criado seu numero irá ser modificado
		this.level = level;
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