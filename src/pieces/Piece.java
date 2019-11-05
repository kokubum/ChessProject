package pieces;

import java.util.ArrayList;

import game.enums.TypePiece;

public abstract class Piece {
	private TypePiece typePiece;
	private Position position; //Posição da peça no tabuleiro (coordenadas x e y)
	private ArrayList<Position> moves; //ArrayList com todas as possiveis movimentações da peça
	
	
	public Piece(TypePiece typePiece, Position position) {
		
		this.moves = new ArrayList<Position>();
		this.typePiece = typePiece;
		this.position = position;
	}
	
	//Método que define os movimentos possiveis da peça
	public abstract ArrayList<Position> possibleMoves();
	//Método para checar se determinada posição escolhida pode ser uma possibilidade
	public abstract boolean isPossible(Position position);
	
	//Getters e Setters da classe Piece	

	public TypePiece getTypePiece() {
		return typePiece;
	}

	public void setTypePiece(TypePiece typePiece) {
		this.typePiece = typePiece;
	}


	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public ArrayList<Position> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Position> moves) {
		this.moves = moves;
	}
	
}
