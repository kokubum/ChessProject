package pieces;

import java.util.ArrayList;

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
