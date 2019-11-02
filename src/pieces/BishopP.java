package pieces;

public class BishopP extends Piece {
	
	private boolean isWhite;
	
	public BishopP(boolean isWhite, int pos_x, int pos_y, int deslocamento_x, int deslocamento_y) {
		super(Type.BISHOP, pos_x, pos_y, deslocamento_x, deslocamento_y);
		this.isWhite = isWhite;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
