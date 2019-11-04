package pieces;

import game.enums.TypePiece;

public class Piece {
	private TypePiece typePiece;
	private int pos_x, pos_y; //Posição da peça no tabuleiro (coordenadas x e y)
	private int deslocamento_x, deslocamento_y;//Forma de deslocamento da peça
	
	public Piece(TypePiece typePiece, int pos_x, int pos_y, int deslocamento_x, int deslocamento_y) {
		
		this.typePiece = typePiece;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.deslocamento_x = deslocamento_x;
		this.deslocamento_y = deslocamento_y;
	}

	//Getters e Setters da classe Piece	

	public TypePiece getTypePiece() {
		return typePiece;
	}

	public void setTypePiece(TypePiece typePiece) {
		this.typePiece = typePiece;
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public int getDeslocamento_x() {
		return deslocamento_x;
	}

	public void setDeslocamento_x(int deslocamento_x) {
		this.deslocamento_x = deslocamento_x;
	}

	public int getDeslocamento_y() {
		return deslocamento_y;
	}

	public void setDeslocamento_y(int deslocamento_y) {
		this.deslocamento_y = deslocamento_y;
	}
	
	

	
}
