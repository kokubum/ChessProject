package pieces;

import java.util.ArrayList;

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

	@Override
	public ArrayList<Position> possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPossible(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

}
