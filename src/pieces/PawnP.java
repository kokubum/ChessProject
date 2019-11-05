package pieces;

import java.util.ArrayList;

import game.enums.TypePiece;

public class PawnP extends Piece {
	private boolean isWhite;
	
	public PawnP(boolean isWhite, Position position) {
		super(TypePiece.PAWN, position);
		this.isWhite = isWhite;
	}
	
	/*Movimentação para frente de um peão
		public PawnP moveFoward() {
			Position newPosition;
			
			//O peão é do time branco
			if(this.isWhite) {
				newPosition = super.getPosition();
				if(newPosition.getX() < 7) {
					newPosition.setX(newPosition.getX()+1);
					super.setPosition(newPosition);
				}
				else {
					//Não pode ser movimentado, a peça está na borda do tabuleiro
				}
			}
			//O peão é do time preto
			else {
				newPosition = super.getPosition();
				if(newPosition.getX() > 0) {
					newPosition.setX(newPosition.getX()-1);
					super.setPosition(newPosition);
				}
				else {
					//Não pode ser movimentado, a peça está na borda do tabuleiro
				}
			}
			return this;
		}
	*/
	//Getters e Setters da classe PawnP

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
