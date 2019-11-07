package pieces;

import java.util.ArrayList;

import game.enums.TypePiece;

public class HorseP extends Piece {
	
	public HorseP(boolean isWhite, Position position) {
		super(TypePiece.HORSE, position,isWhite);
	}
	

	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		//Determinando se a peça pode se mover (em formato L) para cima
		if(this.getPosition().getX()+2<=7) {
			if(this.getPosition().getY()+1<=7) {
				moves.add(new Position(this.getPosition().getX()+2,this.getPosition().getY()+1));
			}
			if(this.getPosition().getY()-1>=0) {
				moves.add(new Position(this.getPosition().getX()+2,this.getPosition().getY()-1));
			}
		}
		
		//Determinando se a peça pode se mover (em formato L) para baixo
		if(this.getPosition().getX()-2>=0) {
			if(this.getPosition().getY()+1<=7) {
				moves.add(new Position(this.getPosition().getX()-2,this.getPosition().getY()+1));
			}
			if(this.getPosition().getY()-1>=0) {
				moves.add(new Position(this.getPosition().getX()-2,this.getPosition().getY()-1));
			}
		}
		
		//Determinando se a peça pode se mover (em formato L) para direita
		if(this.getPosition().getY()+2<=7) {
			if(this.getPosition().getX()+1<=7) {
				moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()+2));
			}
			if(this.getPosition().getX()-1>=0) {
				moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()+2));
			}
		}
		
		//Determinando se peça pode se mover (em formato L) para esquerda
		if(this.getPosition().getY()-2>=0) {
			if(this.getPosition().getX()+1<=7) {
				moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()-2));
			}
			if(this.getPosition().getX()-1>=0) {
				moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()-2));
			}
		}
		
		return moves;
		
	}

	@Override
	public boolean isPossible(Position position) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		Position position = new Position(2,4);
		HorseP piece = new HorseP(true,position);
		piece.setMoves(piece.possibleMoves());

		for(Position aux:piece.getMoves()) {
			System.out.println("X-> "+aux.getX()+" Y-> "+aux.getY());
		}
	}


}
