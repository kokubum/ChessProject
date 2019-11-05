package board;

import pieces.*;

public class BoardGame{
	private Piece[][] boardMatrix;
	
	public BoardGame() {
		this.boardMatrix = new Piece[8][8];
		this.initialBoard();
		this.pieceInTheBoard();
	}
	
	//Encapsulamento -> Metodo para instanciar as 32 peças
	public void pieceInTheBoard() {
		//Instanciando os peões no tabuleiro
		for(int i=0;i<8;i++) {
			//Peões Brancos
			this.getBoardMatrix()[1][i] = new PawnP(true,new Position(1,i));
			//Peões Pretos
			this.getBoardMatrix()[6][i] = new PawnP(false,new Position(6,i));
		}
		//Intanciando as torres
		for(int i=0;i<8;i+=7) {
			//Torres Brancas
			this.getBoardMatrix()[0][i] = new TowerP(true,new Position(0,i));
			//Torres Pretas
			this.getBoardMatrix()[7][i] = new TowerP(false,new Position(7,i));
		}
		//Intanciando os cavalos
		for(int i=1;i<7;i+=5) {
			//Cavalos Brancos
			this.getBoardMatrix()[0][i] = new HorseP(true,new Position(0,i));
			//Cavalos Pretos
			this.getBoardMatrix()[7][i] = new HorseP(false,new Position(7,i));
		}
		//Instanciando os bispos
		for(int i=2;i<6;i+=3) {
			//Bispos Brancos
			this.getBoardMatrix()[0][i] = new BishopP(true,new Position(0,i));
			//Bispos Pretos
			this.getBoardMatrix()[7][i] = new BishopP(false,new Position(7,i));
		}
		//Instanciando o Rei e a Rainha
		this.getBoardMatrix()[0][4] = new KingP(true,new Position(0,4));
		this.getBoardMatrix()[0][3] = new QueenP(true,new Position(0,3));
		this.getBoardMatrix()[7][4] = new KingP(false,new Position(7,4));
		this.getBoardMatrix()[7][3] = new QueenP(false,new Position(7,3));
		
	}
	
	//Método para inicializar o tabuleiro como null em todas suas posiçoes
	/*obs: Por padrão o java ja inicia a matriz como sendo referências nulas (para strings ou objetos), mas para melhor entendimento foi criado 
	 * esse método*/
	public void initialBoard() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.getBoardMatrix()[i][j]=null;
			}
		}
	}

	public Piece[][] getBoardMatrix() {
		return boardMatrix;
	}

}
