package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.enums.TypePiece;

public abstract class Piece {
	private boolean isWhite;	
	private TypePiece typePiece;
	private Position position; //Posição da peça no tabuleiro (coordenadas x e y)
	private ArrayList<Position> moves; //ArrayList com todas as possiveis movimentações da peça
	private ImageIcon pieceImage;
	
	
	public Piece(TypePiece typePiece, Position position,boolean isWhite) {
		
		this.moves = new ArrayList<Position>();
		this.typePiece = typePiece;
		this.position = position;
		this.isWhite = isWhite;
	}
	
	//Método para pegar a imagem referente a peça
	public abstract ImageIcon getImageIcon();
	//Método que define qual imagem vai ser associada a peça
	public abstract void createImage();
	//Método que define os movimentos possiveis da peça
	public abstract ArrayList<Position> possibleMoves();
	
	
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

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	
}
