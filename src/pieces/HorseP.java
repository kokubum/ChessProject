package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.enums.TypePiece;

public class HorseP extends Piece {
	
	private ImageIcon horseImage;
	
	public HorseP(boolean isWhite, Position position) {
		super(TypePiece.HORSE, position,isWhite);
		this.createImage();
	}
	
	//Método polimórfico para pegar a imagem
	@Override
	public ImageIcon getImageIcon() {
		return this.horseImage;
	}
	
	//Método polimorfico para instanciar a imagem ao objeto;
	@Override
	public void createImage() {
		if(this.isWhite()==true) {
			this.horseImage = new ImageIcon("src/PieceImages/HorsePieceWhite.png");
		}
		else {
			this.horseImage = new ImageIcon("src/PieceImages/HorsePieceBlack.png");
		}
			
	}

	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		//Determinando se a peça pode se mover (em formato L) para baixo
		if(this.getPosition().getX()+2<=7) {
			if(this.getPosition().getY()+1<=7) {
				moves.add(new Position(this.getPosition().getX()+2,this.getPosition().getY()+1));
			}
			if(this.getPosition().getY()-1>=0) {
				moves.add(new Position(this.getPosition().getX()+2,this.getPosition().getY()-1));
			}
		}
		
		//Determinando se a peça pode se mover (em formato L) para cimas
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

}
