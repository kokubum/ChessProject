package pieces;

public class HorseP extends Piece {
	private boolean isWhite;
	
	public HorseP(boolean isWhite, int pos_x, int pos_y, int deslocamento_x, int deslocamento_y) {
		super(Type.HORSE, pos_x, pos_y, deslocamento_x, deslocamento_y);
		this.isWhite = isWhite;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
