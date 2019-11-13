package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.enums.TypePiece;

public class QueenP extends Piece{
	private ImageIcon queenImage;
	
	public QueenP(boolean isWhite, Position position) {
		super(TypePiece.QUEEN, position,isWhite);
		this.createImage();
	}
	//Método polimórfico para pegar a imagem
	@Override
	public ImageIcon getImageIcon() {
		return this.queenImage;
	}
	
	//Método polimorfico para instanciar a imagem ao objeto;
	@Override
	public void createImage() {
		if(this.isWhite()==true) {
			this.queenImage = new ImageIcon("/home/kokubum/Eclipse/ChessProject/src/PieceImages/QueenPieceWhite.png");
		}
		else {
			this.queenImage = new ImageIcon("/home/kokubum/Eclipse/ChessProject/src/PieceImages/QueenPieceBlack.png");
		}
			
	}
	
	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		/*É necessario a criacao de uma posição auxiliar para não modificar a posição atual da peça ja que a Rainha tem 
		varias possiveis movimentações*/
		Position aux = new Position(this.getPosition().getX(),this.getPosition().getY());
		
		//Determinando se a peça pode ir para baixo
		while(aux.getX()+1<=7) {
			aux.setX(aux.getX()+1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
		
		//Reinicializado a váriável
		aux.setX(this.getPosition().getX());
		//Determinando se a peça pode ir para cima
		while(aux.getX()-1>=0) {
			aux.setX(aux.getX()-1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
		
		//Reinicializado a váriável
		aux.setX(this.getPosition().getX());
		
		//Determinando se a peça pode ir para a direita
		while(aux.getY()+1<=7) {
			aux.setY(aux.getY()+1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
		
		//Reinicializando a váriavel
		aux.setY(this.getPosition().getY());
		
		//Determinando se a peça pode ir para esquerda
		while(aux.getY()-1>=0) {
			aux.setY(aux.getY()-1);
			moves.add(new Position(aux.getX(),aux.getY()));
		}
		
		//Reinicializando a váriavel
		aux.setY(this.getPosition().getY());
		
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
		Position position = new Position(0,3);
		QueenP piece = new QueenP(true,position);
		piece.setMoves(piece.possibleMoves());

		for(Position aux:piece.getMoves()) {
			System.out.println("X-> "+aux.getX()+" Y-> "+aux.getY());
		}
	}

	
	

}
