package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.enums.TypePiece;

public class KingP extends Piece {
	private ImageIcon kingImage;
	
	public KingP(boolean isWhite, Position position) {
		super(TypePiece.KING, position,isWhite);
		this.createImage();
	}
	
	//Método polimórfico para pegar a imagem
	@Override
	public ImageIcon getImageIcon() {
		return this.kingImage;
	}
	
	//Método polimorfico para instanciar a imagem ao objeto;
	@Override
	public void createImage() {
		if(this.isWhite()==true) {
			this.kingImage = new ImageIcon(getClass().getClassLoader().getResource("PieceImages/KingPieceWhite.png"));
		}
		else {
			this.kingImage = new ImageIcon(getClass().getClassLoader().getResource("PieceImages/KingPieceBlack.png"));
		}
			
	}
	
	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		//Booleanos para poder definir quando a peça vai poder ir para algumas das diagonais (DS = direita superior ...)
		boolean diagonalDS = false;
		boolean diagonalES = false;
		boolean diagonalDI = false;
		boolean diagonalEI = false;
		
		//Determinando se a peça pode ir para baixo
		if(this.getPosition().getX()+1<=7) {
			moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()));
			diagonalDS = true;
			diagonalES = true;
		}
		//Determinando se a peça pode ir para cima
		if(this.getPosition().getX()-1>=0) {
			moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()));
			diagonalDI = true;
			diagonalEI = true;
		}
		//Determinando se a peça pode ir para a direita
		if(this.getPosition().getY()+1<=7) {
			moves.add(new Position(this.getPosition().getX(),this.getPosition().getY()+1));
			//Testa se pode mover para a diagonal direita superior
			if(diagonalDS == true) {
				moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()+1));
			}
			//Testa se pode mover para a diagonal direita inferior
			if(diagonalDI ==true) {
				moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()+1));
			}
		}
		//Determinando se a peça pode ir para esquerda
		if(this.getPosition().getY()-1>=0) {
			moves.add(new Position(this.getPosition().getX(),this.getPosition().getY()-1));
			//Testa se pode mover para a diagonal esquerda superior
			if(diagonalES == true) {
				moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()-1));
			}
			//Testa se pode mover para a diagonal esquerda inferior
			if(diagonalEI == true) {
				moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()-1));
			}
			
		}
		
		return moves;
		
	}
	




}
