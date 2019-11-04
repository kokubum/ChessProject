package pieces;

import game.enums.TypePiece;

public class PawnP extends Piece {
	private boolean isWhite;
	
	public PawnP(boolean isWhite, Position position) {
		super(TypePiece.PAWN, position);
		this.isWhite = isWhite;
	}
	
	//Getters e Setters da classe PawnP

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
