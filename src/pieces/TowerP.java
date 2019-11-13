package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.enums.TypePiece;

public class TowerP extends Piece {
	private ImageIcon towerImage;
	
	public TowerP(boolean isWhite, Position position) {
		super(TypePiece.TOWER, position,isWhite);
		this.createImage();
		
	}
	//Método polimórfico para pegar a imagem
	@Override
	public ImageIcon getImageIcon() {
		return this.towerImage;
	}
	
	//Método polimorfico para instanciar a imagem ao objeto;
	@Override
	public void createImage() {
		if(this.isWhite()==true) {
			this.towerImage = new ImageIcon("/home/kokubum/Eclipse/ChessProject/src/PieceImages/TowerPieceWhite.png");
		}
		else {
			this.towerImage = new ImageIcon("/home/kokubum/Eclipse/ChessProject/src/PieceImages/TowerPieceBlack.png");
		}
				
	}
	
	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		/*É necessario a criacao de uma posição auxiliar para não modificar a posição atual da peça ja que a torre tem 
		varias possiveis movimentações*/
		Position aux = new Position(this.getPosition().getX(),this.getPosition().getY());
		
		//Determinando se a peça pode ir pra baixo
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
		
		return moves;
	}

	@Override
	public boolean isPossible(Position position) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		Position position = new Position(1,4);
		TowerP piece = new TowerP(true,position);
		piece.setMoves(piece.possibleMoves());

		for(Position aux:piece.getMoves()) {
			System.out.println("X-> "+aux.getX()+" Y-> "+aux.getY());
		}
	}
	

}
