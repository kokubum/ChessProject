package pieces;

import game.enums.TypePiece;

public class HorseP extends Piece {
	private boolean isWhite;
	
	public HorseP(boolean isWhite, Position position) {
		super(TypePiece.HORSE, position);
		this.isWhite = isWhite;
	}

	//Getters e Setters da classe HorseP
	
	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
