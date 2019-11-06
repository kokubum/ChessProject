package pieces;

import java.util.ArrayList;

import game.enums.TypePiece;

public class BishopP extends Piece {
	
	private boolean isWhite;
	
	public BishopP(boolean isWhite, Position position) {
		super(TypePiece.BISHOP,position);
		this.isWhite = isWhite;
	}
	
	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		/*É necessario a criacao de uma posição auxiliar para não modificar a posição atual da peça ja que o Bispo tem 
		varias possiveis movimentações*/
		Position aux = new Position(this.getPosition().getX(),this.getPosition().getY());
		
		//Determinando se a peça pode ir para diagonal direita inferior
		while(aux.getX()+1<=7 && aux.getY()+1<=7) {
			aux.setX(aux.getX()+1);
			aux.setY(aux.getY()+1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
		//Reinicializando a váriavel
		aux.setY(this.getPosition().getY());
		aux.setX(this.getPosition().getX());
			
		//Determinando se a peça pode ir para diagonal esquerda inferior
		while(aux.getX()+1<=7 && aux.getY()-1>=0) {
			aux.setX(aux.getX()+1);
			aux.setY(aux.getY()-1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
				
		//Reinicializando a váriavel
		aux.setY(this.getPosition().getY());
		aux.setX(this.getPosition().getX());
				
		//Determinando se a peça pode ir para diagonal direita superior
		while(aux.getX()-1>=0 && aux.getY()+1<=7) {
			aux.setX(aux.getX()-1);
			aux.setY(aux.getY()+1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
				
		//Reinicializando a váriavel
		aux.setY(this.getPosition().getY());
		aux.setX(this.getPosition().getX());
				
		//Determinando se a peça pode ir para diagonal esquerda superior
		while(aux.getX()-1>=0 && aux.getY()-1>=0) {
			aux.setX(aux.getX()-1);
			aux.setY(aux.getY()-1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
				
				
		return moves;
	}

	@Override
	public boolean isPossible(Position position) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		Position position = new Position(1,4);
		BishopP piece = new BishopP(true,position);
		piece.setMoves(piece.possibleMoves());

		for(Position aux:piece.getMoves()) {
			System.out.println("X-> "+aux.getX()+" Y-> "+aux.getY());
		}
	}
	
	//Getters e Setters da classe BishopP
	
	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}


}
