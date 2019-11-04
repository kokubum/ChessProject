package pieces;

import game.enums.TypePiece;

public class TowerP extends Piece {
	private boolean isWhite;
	
	public TowerP(boolean isWhite, Position position) {
		super(TypePiece.TOWER, position);
		this.isWhite = isWhite;
	}

	//Getters e Setters da classe ToweP	
	
	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
