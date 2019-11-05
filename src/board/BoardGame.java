package board;

import pieces.*;

public class BoardGame{
	private Piece[][] boardMatrix;
	
	public BoardGame() {
		this.boardMatrix = new Piece[8][8];
		
		//Instanciação das peças no tabuleiro
		this.pieceInTheBoard();
	}
	
	//Encapsulamento -> Metodo para instanciar as peças
	
	public void pieceInTheBoard() {
		//Instanciando peões no tabuleiro
		for(int i=0;i<8;i++) {
			this.getBoardMatrix()[1][i] = new PawnP(true,new Position(1,i));
			this.getBoardMatrix()[6][i] = new PawnP(false,new Position(6,i));
		}
		
		//Instanciando Reis
		this.getBoardMatrix()[0][4] = new KingP(true,new Position(0,4));
		this.getBoardMatrix()[7][4] = new KingP(false,new Position(7,4));
		
		//Instanciando Rainhas
		this.getBoardMatrix()[0][3] = new QueenP(true,new Position(0,3));
		this.getBoardMatrix()[7][3] = new QueenP(false,new Position(7,3));
		
		//Instanciando Bispos
		this.getBoardMatrix()[0][2] = new BishopP(true,new Position(0,2));
		this.getBoardMatrix()[0][5] = new BishopP(true,new Position(0,5));
		this.getBoardMatrix()[7][2] = new BishopP(false,new Position(7,2));
		this.getBoardMatrix()[7][5] = new BishopP(false,new Position(7,5));
		
		//Instanciando Cavalos
		this.getBoardMatrix()[0][1] = new HorseP(true,new Position(0,1));
		this.getBoardMatrix()[0][6] = new HorseP(true,new Position(0,6));
		this.getBoardMatrix()[7][1] = new HorseP(false,new Position(7,1));
		this.getBoardMatrix()[7][6] = new HorseP(false,new Position(7,6));

		//Instanciando Torres
		this.getBoardMatrix()[0][0] = new TowerP(true, new Position(0,0));
		this.getBoardMatrix()[0][7] = new TowerP(true, new Position(0,7));
		this.getBoardMatrix()[7][0] = new TowerP(false, new Position(7,0));
		this.getBoardMatrix()[7][7] = new TowerP(false, new Position(7,7));

	}

	public Piece[][] getBoardMatrix() {
		return boardMatrix;
	}

}
