package pieces;

import game.enums.TypePiece;

public class QueenP extends Piece{
	private boolean isWhite;
	
	public QueenP(boolean isWhite, int pos_x, int pos_y, int deslocamento_x, int deslocamento_y) {
		super(TypePiece.QUEEN, pos_x, pos_y, deslocamento_x, deslocamento_y);
		this.isWhite = isWhite;
	}
	
	//Getters e Setters da classe QueenP

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
