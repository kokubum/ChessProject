package pieces;

import game.enums.TypePiece;

public class KingP extends Piece {
	private boolean isWhite;
	
	public KingP(boolean isWhite, Position position) {
		super(TypePiece.KING, position);
		this.isWhite = isWhite;
	}

	//Getters e Setters da classe KingP
	
	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
