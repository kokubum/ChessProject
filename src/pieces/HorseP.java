package pieces;

import game.enums.TypePiece;

public class HorseP extends Piece {
	private boolean isWhite;
	
	public HorseP(boolean isWhite, int pos_x, int pos_y, int deslocamento_x, int deslocamento_y) {
		super(TypePiece.HORSE, pos_x, pos_y, deslocamento_x, deslocamento_y);
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
