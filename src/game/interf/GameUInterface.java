package game.interf;

import game.Player;
import pieces.Piece;
import pieces.Position;

public interface GameUInterface {
	
	//Contrato que define o funcionamento da interface do jogo, baseado nesses 5 métodos

	void changeTurn(Player player); //Método que modifica a vez do jogador
	boolean changeTheIcons(int row,int colum,Piece piece); //Metodo que movimenta uma peça
	void showColorMoves(int row,int colum); //Método que mostra as possiveis movimentações em uma cor ver
	void writeCheck(Piece piece); //Método que escreve quando uma peça põe o o rei do adversário em check
	void writeMovement(Position before,Position after,Piece piece);	//Método que escreve cada movimentação que foi dada durante o jogo
}
