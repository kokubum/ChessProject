package pieces;

import game.enums.TypePiece;

public class TowerP extends Piece {
	private boolean isWhite;
	
	public TowerP(boolean isWhite, int pos_x, int pos_y, int deslocamento_x, int deslocamento_y) {
		super(TypePiece.TOWER, pos_x, pos_y, deslocamento_x, deslocamento_y);
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
