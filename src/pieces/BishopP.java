package pieces;

import game.enums.TypePiece;

public class BishopP extends Piece {
	
	private boolean isWhite;
	
	public BishopP(boolean isWhite, Position position) {
		super(TypePiece.BISHOP,position);
		this.isWhite = isWhite;
	}
	
	//Getters e Setters da classe BishopP
	
	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
