package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.enums.TypePiece;

public class PawnP extends Piece {
	private boolean firstMove;
	private ImageIcon pawnImage;
	
	public PawnP(boolean isWhite, Position position) {
		super(TypePiece.PAWN, position,isWhite);
		this.firstMove = true;
		this.createImage(); //Método para criar a imagem relacionada a peça, instanciando o caminho da imagem .png
	}
	
	//Método polimórfico para pegar a imagem
	@Override
	public ImageIcon getImageIcon() {
		return this.pawnImage;
	}

	//Método polimorfico para instanciar a imagem ao objeto;
	@Override
	public void createImage() {
		if(this.isWhite()==true) {
			// Do jeito que esta imagem está sendo pegada não irá funcionar na execução dentro do eclipse mas foi feita para aparecer no .jar
			this.pawnImage = new ImageIcon(getClass().getClassLoader().getResource("PieceImages/PawnPieceWhite.png"));
		}
		else {
			// Do jeito que esta imagem está sendo pegada não irá funcionar na execução dentro do eclipse mas foi feita para aparecer no .jar
			this.pawnImage = new ImageIcon(getClass().getClassLoader().getResource("PieceImages/PawnPieceBlack.png"));
		}
		
	}
	
	
	/* Coloquei no array dos movimentos possiveis as movimentaçoes em diagonais do peão pois não da pra passar aqui o tabuleiro do jogo,
	 * logo achei melhor a gente checar a presenca de uma peça em outro metodo aqui ou no BoardGame (que so será usado pro peão), e ai
	 * essa movimentação, se escolhida, so sera processada se o isPossible for true ou outro método, ou seja, quando houver uma peça na posição. 
	 */
	
	@Override
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position>moves = new ArrayList<Position>();
		//Tratando os peões brancos
		if(this.isWhite()==true) {
			//Peões centrais que tem dois movimentos diagonais possiveis que poderão ou não serem processados
			if(this.firstMove == true) {
				moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()));
				moves.add(new Position(this.getPosition().getX()+2,this.getPosition().getY()));
			}
			//Tratando os peões que ja se moveram
			else{
				moves.add(new Position(this.getPosition().getX()+1,this.getPosition().getY()));		
			}
		}
		//Tratando os peões pretos
		else {
			//Tratando os peões que ainda não se moveram ainda
			if(this.firstMove == true) {
				moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()));
				moves.add(new Position(this.getPosition().getX()-2,this.getPosition().getY()));
			}
			//Tratando os peões que ja se moveram
			else {
				moves.add(new Position(this.getPosition().getX()-1,this.getPosition().getY()));
			}
			
		}
		
		return moves;
	}


	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	

}
