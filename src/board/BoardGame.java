package board;

import pieces.PawnP;
import pieces.Piece;
import pieces.Position;

public class BoardGame{
	private Piece[][] boardMatrix;
	
	public BoardGame() {
		this.boardMatrix = new Piece[8][8];
		
		//Instanciação das peças brancas (16 peças):
		
		//Instanciação das peças pretas (16 peças):
		//...
	}
	
	//Encapsulamento -> Metodo para instanciar as peças
	
	public void pieceInTheBoard() {
		for(int i=0;i<8;i++) {
			this.getBoardMatrix()[1][i] = new PawnP(true,new Position(1,i));
		}
	}

	public Piece[][] getBoardMatrix() {
		return boardMatrix;
	}

}
