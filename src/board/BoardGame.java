package board;

import pieces.*;

public class BoardGame{
	private Piece[][] boardMatrix;
	
	public BoardGame() {
		this.boardMatrix = new Piece[8][8];
		this.pieceInTheBoard();
	}
	
	//Encapsulamento -> Metodo para instanciar as 32 peças
	public void pieceInTheBoard() {
		//Instanciando os peões no tabuleiro
		for(int i=0;i<8;i++) {
			//Peões Brancos
			this.getBoardMatrix()[1][i] = new PawnP(true,new Position(1,i));
			//Peões Pretos
			this.getBoardMatrix()[6][i] = new PawnP(false,new Position(6,i));
		}
		//Intanciando as torres
		for(int i=0;i<8;i+=7) {
			//Torres Brancas
			this.getBoardMatrix()[0][i] = new TowerP(true,new Position(0,i));
			//Torres Pretas
			this.getBoardMatrix()[7][i] = new TowerP(false,new Position(7,i));
		}
		//Intanciando os cavalos
		for(int i=1;i<7;i+=5) {
			//Cavalos Brancos
			this.getBoardMatrix()[0][i] = new TowerP(true,new Position(0,i));
			//Cavalos Pretos
			this.getBoardMatrix()[7][i] = new TowerP(false,new Position(0,i));
		}
		//Instanciando os bispos
		for(int i=2;i<6;i+=3) {
			//Bispos Brancos
			this.getBoardMatrix()[0][i] = new TowerP(true,new Position(0,i));
			//Bispos Pretos
			this.getBoardMatrix()[7][i] = new TowerP(true,new Position(0,i));
		}
		//Instanciando o Rei e a Rainha
		for(int i=0;i<8;i+=7) {
			this.getBoardMatrix()[i][3] = new KingP(true,new Position(i,3));
			this.getBoardMatrix()[i][4] = new QueenP(true,new Position(i,4));
		}
	}

	public Piece[][] getBoardMatrix() {
		return boardMatrix;
	}

}
