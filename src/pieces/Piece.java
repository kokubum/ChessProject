package pieces;

import game.enums.TypePiece;

public abstract class Piece {
	private TypePiece typePiece;
	private Position position; //Posição da peça no tabuleiro (coordenadas x e y)
	
	public Piece(TypePiece typePiece, Position position) {
		
		this.typePiece = typePiece;
		this.setPosition(position);
	}

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
}
