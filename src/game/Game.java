package game;

import board.BoardGame;
import chronometer.Chronometer;
import game.enums.GameLevel;
import game.enums.TypePiece;
import pieces.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
	
	private Player player1, player2;
	private Player playerTurn; //Jogador que vai jogar
	private BoardGame boardGame; //Tabuleiro de xadrez
	private static int gameNumber = 0; //Numero do jogo que será iniciado (static = caracterista da classe jogo, antes de ser instânciada)
	private GameLevel level; //Nivel de dificuldade do jogo | Intimamente relacionado ao funcionamento do cronômetro
	private Chronometer chronometer;
	
	//Construtor do Game vai instanciar todos os seus objetos devido a agregação forte relacionada a ele
	public Game(String nickPlayer1, String nickPlayer2, boolean whitePlayer1, boolean whitePlayer2, GameLevel level) {
		this.player1 = new Player(nickPlayer1, whitePlayer1);
		this.player2 = new Player(nickPlayer2, whitePlayer2);
		this.boardGame = new BoardGame();
		++gameNumber; //A cada jogo criado seu número irá ser modificado
		this.level = level;
		this.myTurn();
	}
	
	//Método para definir de quem vai começar o jogo
	public void myTurn() {
	
		if(this.player1.isWhitePlayer()==true) {
			this.setPlayerTurn(player1);
		}
		else {
			this.setPlayerTurn(player2);
		}
		
	}
	
	//Método para mudar o jogador toda vez que uma peça é movida
	//Recebe como parâmetro o ultimo jogador a realizar o movimento
	public void changeTurn(Player player) {
		if(this.player1.equals(player)) {
			this.setPlayerTurn(player2);
		}
		else {
			this.setPlayerTurn(player1);
		}
	}
	
	//Método para movimentar uma peça
	public boolean movePiece(Position position,Piece piece,Player player) {
		PawnP pawn = null;
		//Crio uma arrayList auxiliar para receber as movimentaçoes possiveis, devido ao Overload no metodo showPossibleMoves()
		ArrayList<Position> auxiliarMoves=null;
		//Crio uma variável booleana para dizer se o jogador escolheu um local válido
		boolean isPossible=false;
		//Se a peça for um peão preciso fazer um casting e passar o metodo para o arrayList auxiliar com seu metodo correspondente
		if(piece instanceof PawnP) {
			pawn = (PawnP)piece;
			auxiliarMoves = this.showPossibleMoves(player, pawn);
		}
		else {
			auxiliarMoves = this.showPossibleMoves(player,piece);
		}
		
		for(Position aux:auxiliarMoves) {
			//Se a posição escolhida estiver na lista de possiveis movimentações, entao isPossible = true
			if(position.getX()==aux.getX() && position.getY()==aux.getY()) {
				isPossible=true;
				
			}
		}	
		if(isPossible) {
			if(pawn!=null) {
				pawn.setFirstMove(false);
			}
			//Verifico se a posição escolhida resulta em um check mate
			if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()]!=null) {
				if(isCheckMate(position)) {
					player.setWinner(true);
					//É preciso criar algum metodo para finalizar o jogo, assim não precisaremos ficar checando todo momento
				}
			}
			//Se isPossible == true, entao a posição do tabuleiro ira referenciar a peça
			this.boardGame.getBoardMatrix()[position.getX()][position.getY()]=piece;
			//A posiçao anterior da peça ira referenciar null, indicando que não há mais uma peça ali
			this.boardGame.getBoardMatrix()[piece.getPosition().getX()][piece.getPosition().getY()]=null;
			//Logo, para finalizar a movimentação setamos a posição da peça para a posição que ela ira se mover	
			piece.setPosition(position);
			this.changeTurn(player);
			
		}
		else {
			//Usuario clicou em um lugar que não é possivel mover
			
		}
		return isPossible;
	}
	
	
	/*
	 Método para remover das movimentações possiveis as posições que possuirem peças da mesma cor que a peça que quer se mover, e quando
	 alguma peça interromper o caminho
	*/
	public void removeMoves(ArrayList<Position> moves,Piece piece) {
		Position aux = new Position(piece.getPosition().getX(),piece.getPosition().getY());
		Position position=null;
		//Testamos se a piece passada por parâmetro é uma instância da Torre ou Rainha
		//Essa parte do método estamos tratando as movimentações horizontais ou verticais
		if(piece instanceof TowerP || piece instanceof QueenP) {
			//Percorro a lista de possiveis movimentos com um iterador
			for(Iterator<Position>it=moves.iterator();it.hasNext();) {
				position=it.next();
				//Se em um desses movimentos tiver uma peça, sabemos entao que essa peça esta bloquendo o resto dos movimento
				if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()]!=null) {
					//É criado outro iterador para poder pegar as posições bloqueadas e então remove-las do arraylist
					Iterator<Position>itAux=it;
					//Se a peça estiver bloquendo o movimento vertical
					if(position.getY()==aux.getY()) {
						//Vertical -> Subindo
						if(position.getX()>aux.getX()) {
							//Removo todas as possiveis movimentações depois da peça encontrada
							while(position.getX()<7 && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
						//Vertical-> Descendo
						else if(position.getX()<aux.getX()) {
							//Removo todas as possiveis movimentações depois da peça encontrada
							while(position.getX()>0 && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
					}
					//Se a peça estiver bloquenado o movimento horizontal
					else if(position.getX()==aux.getX()) {
						//Horizontal -> Para direita
						if(position.getY()>aux.getY()) {
							//Removo todas as possiveis movimentações depois da peça encontrada
							while(position.getY()<7 && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
						//Horizontal -> Para esquerda
						else if(position.getY()<aux.getY()) {
							//Removo todas as possiveis movimentações depois da peça encontrada
							while(position.getY()>0 && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
					}
					
				}		
			}
		}
		//Testamos se a piece é uma instancia de Rainha ou Bispo
		if(piece instanceof QueenP || piece instanceof BishopP) {
			//Crio um iterador para poder passar pelo arraylist de possiveis movimentos
			for(Iterator<Position>it = moves.iterator();it.hasNext();) {
				position=it.next();
				//Se em uma dessas posições tiver uma peça
				if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()]!=null) {
					//Crio um iterador auxiliar para poder acessar as movimentações que essa peça encontrada bloqueia
					Iterator<Position>itAux=it;
					//Vou removendo as possiveis posições da diagonal inferior direita logo após a peça que está bloqueando a movimentação
					if(position.getX()>aux.getX()) {
						if(position.getY()>aux.getY()) {
							while((position.getX()<7 && position.getY()<7) && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
						//Vou removendo as possiveis posições da diagonal inferior esquerda logo após a peça que está bloqueando a movimentação
						else if(position.getY()<aux.getY()) {
							while((position.getX()<7 && position.getY()>0) && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
					}
					//Vou removendo as possiveis posições da diagonal superior direita logo após a peça que está bloqueando a movimentação
					else if(position.getX()<aux.getX()) {
						if(position.getY()>aux.getY()) {
							while((position.getX()>0 && position.getY()<7) && itAux.hasNext()) {
								position=itAux.next();
								itAux.remove();
							}
						}
						//Vou removendo as possiveis posições da diagonal superior esquerda logo após a peça que está bloqueando a movimentação
						else if(position.getY()<aux.getY()) {
							while((position.getX()>0 && position.getY()>0) && itAux.hasNext() ) {
								position=itAux.next();
								itAux.remove();
							}
						}
					}
				}
			}
		}
		//Testo se a peça passada como parâmetro é um peão
		else if(piece instanceof PawnP) {
			//Faço um castingo da peça para poder usar a funcao .isFirstMove() da classe PawnP
			PawnP pawn = (PawnP)piece;
			//Testo se é a primeiro movimentação do peão
			if(pawn.isFirstMove()==true) {
				for(Iterator<Position>it=moves.iterator();it.hasNext();) {
					position=it.next();
					//Se for, é analisado se há uma peça logo em frente ao peão e removo a possivel posiçao logo depois dela
					if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()]!=null) {
						if((position.getX()==aux.getX()+1 && position.getY()==aux.getY() || (position.getX()==aux.getX()-1 && position.getY()==aux.getY()))){
							if(it.hasNext()) {
								it.next();
								it.remove();
							}
						}
					}
				}
			}
		}
		/*Ultima parte onde tratamos de remover as possiveis movimentações onde ja se tem uma peça sendo essa da mesma cor da peça que quer se
		 mover*/
		for(Iterator<Position>it=moves.iterator();it.hasNext();) {
			position=it.next();
			if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()]!=null) {
				if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()].isWhite()==piece.isWhite()) {
					it.remove();
				}
			}
		}
			
			
		
	}
	/*Método em que é recebido um jogador, uma peça e o tabuleiro em que o jogo está acontecendo para realizar a movimentação
	das peças, este metodo será chamado quando o jogador clicar em cima da peça do seu próprio time e apartir
	dai as possiveis movimentações serão mostradas*/
	
	public ArrayList<Position> showPossibleMoves(Player player, Piece piece){
		ArrayList<Position>moves;
		moves = null;	
		//verificando se o jogador é do time branco e se ele clicou em uma peça do time branco
		if(player.isWhitePlayer()) {
			if(piece.isWhite()){
				moves = piece.possibleMoves(); //Método polimorfico, logo não precisa de instance of, nem casting
			}
			else {
				//A peça é do time preto e o jogador é do time branco
			}
		}
		//Verificando se o jogador é do time preto e se ele clicou em uma peça do time preto
		else {
			if(!player.isWhitePlayer()) {
				if(!piece.isWhite()){
					moves = piece.possibleMoves(); //Método polimorfico, logo não precisa de instance of, nem casting
				}
				else {
					//A peça é do time branco e o jogador é do time preto
				}
			}
		}
		
		this.removeMoves(moves, piece);
		//retorna as possiveis movimentações de uma peça para a interface grafica mostrar na tela
		return moves;
	}
	
	//Implementando a sobrecarga do método showPossibleMoves() para o peão
	public ArrayList<Position> showPossibleMoves(Player player,PawnP pawn){
		ArrayList<Position>moves;
		moves = null;
		Position pos = new Position(pawn.getPosition().getX(),pawn.getPosition().getY());//Usado apenas para diminuir tamanho das chamadas
		

		//Verificando se o jogador é do time branco e ele clicou em um peão tambem branco
		if(player.isWhitePlayer()) {
			if(pawn.isWhite()) {
				moves = pawn.possibleMoves();//Metódo presente em pawnP
				//Além da movimentação normal, o peão tambem pode ter outras movimentações específicas
				//1- Movimentação En Passant
				if(pos.getX()<=5) {
					//Teste se duas casas a frente do peão tem uma peça que seja a instancia de um peão tambem
					if(this.boardGame.getBoardMatrix()[pos.getX()+2][pos.getY()]!=null){
						//Checo tambem se entre os dois não há uma peça, se houver, não tem como fazer essa jogada
						if(this.boardGame.getBoardMatrix()[pos.getX()+1][pos.getY()]==null) {
							if(this.boardGame.getBoardMatrix()[pos.getX()+2][pos.getY()].getTypePiece() == TypePiece.PAWN) {
								moves.add(new Position(pos.getX()+2,pos.getY()));
							}
						}
					}
				}
				//Se o peão estiver na borda esquerda
				if(pos.getY()==0) {
					//Se tiver uma peça na sua diagonal direita inferior
					if(this.boardGame.getBoardMatrix()[pos.getX()+1][1]!=null) {
						moves.add(new Position(pos.getX()+1,1));
						
					}
				}
				//Se o peão estiver na borda direita
				else if (pos.getY()==7) {
					//Se tiver uma peça em sua diagonal esquerda inferior
					if(this.boardGame.getBoardMatrix()[pos.getX()+1][6]!=null) {
						moves.add(new Position(pos.getX()+1,6));
						
					}
				}
				//Se o peão estiver pelo centro
				else {
					//Se em sua diagonal esquerda inferior tiver uma peça
					if(this.boardGame.getBoardMatrix()[pos.getX()+1][pos.getY()-1]!=null) {
						moves.add(new Position(pos.getX()+1,pos.getY()-1));
					}
					//Se em sua diagonal direita inferior tiver uma peça
					else if (this.boardGame.getBoardMatrix()[pos.getX()+1][pos.getY()+1]!=null) {
						moves.add(new Position(pos.getX()+1,pos.getY()+1));
					}
				}
			}
			else {
				//Jogador do time branco e peão do time preto
			}
		}
		//Jogador do time preto e peça tambem do time preto
		else if(!player.isWhitePlayer()) {
			if(!pawn.isWhite()) {
				moves = pawn.possibleMoves();
				//En Passant
				if(pos.getX()>=2) {
					if(this.boardGame.getBoardMatrix()[pos.getX()-2][pos.getY()]!=null) {
						if(this.boardGame.getBoardMatrix()[pos.getX()-1][pos.getY()]==null) {
							if(this.boardGame.getBoardMatrix()[pos.getX()-2][pos.getY()].getTypePiece()==TypePiece.PAWN) {
								moves.add(new Position(pos.getX()-2,pos.getY()));
							}
						}
					}
				}
				//Se o peão estiver na borda esquerda
				if(pos.getY()==0) {
					//Se tiver uma peça na sua diagonal direita superior
					if(this.boardGame.getBoardMatrix()[pos.getX()-1][1]!=null) {
						moves.add(new Position(pos.getX()-1,1));
						
					}
				}
				//Se o peão estiver na borda direita
				else if (pos.getY()==7) {
					//Se tiver uma peça em sua diagonal esquerda superior
					if(this.boardGame.getBoardMatrix()[pos.getX()-1][6]!=null) {
						moves.add(new Position(pos.getX()-1,6));		
					}
				}
				//Se o peão estiver pelo centro
				else {
					//Se em sua diagonal esquerda superior tiver uma peça
					if(this.boardGame.getBoardMatrix()[pos.getX()-1][pos.getY()-1]!=null) {
						moves.add(new Position(pos.getX()-1,pos.getY()-1));
					}
					//Se em sua diagonal direita inferior tiver uma peça
					else if (this.boardGame.getBoardMatrix()[pos.getX()-1][pos.getY()+1]!=null) {
						moves.add(new Position(pos.getX()-1,pos.getY()+1));
					}
				}
				
				
			}
			else {
				//Jogador do time preto e peça do time branco
			}
		}
		
		this.removeMoves(moves, pawn);
		return moves;
		
	}
	
	//Método que é chamado quando um peão atravessa o tabuleiro ou pode ser chamado depois de realizado uma movimentação
	public void turnQueen(PawnP pawn) {
		//Tratanto para os peões do time branco e se ele atravessou o mapa
		if(pawn.isWhite() && (pawn.getPosition().getX() == 7)) {
			this.boardGame.getBoardMatrix()[7][pawn.getPosition().getY()] = new QueenP(true, pawn.getPosition());
		}
		//Tratando para os peões do time preto e se ele atravessou o mapa
		else if(!pawn.isWhite() && (pawn.getPosition().getX() == 0)){
			this.boardGame.getBoardMatrix()[0][pawn.getPosition().getY()] = new QueenP(false, pawn.getPosition());
		}
		
	}
	
	//Este método recebe uma posição que é onde a peça irá e se tiver um rei nesta posição é dado check mate
	public boolean isCheckMate(Position position) {
		if(this.boardGame.getBoardMatrix()[position.getX()][position.getY()].getTypePiece() == TypePiece.KING) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Método que verifica se o jogo acabou atraves do atributo winner de Player
	public Player gameEnd() {
		if(this.player1.isWinner()) {
			return this.player1;
		}
		if(this.player2.isWinner()){
			return this.player2;
		}
		else {
			//nenhum jogador ainda deu check mate
			return null;
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game("Erick","Alberto",true,false,GameLevel.BEGINNER);
		Piece piece = game.boardGame.getBoardMatrix()[1][2];
		System.out.println(piece.getTypePiece());
		System.out.println(game.showPossibleMoves(game.getPlayer1(), piece));
		for(Position aux:game.showPossibleMoves(game.player1, piece)) {
			System.out.println("X-> "+aux.getX()+" Y-> "+aux.getY());
		}
	}
	
	//Getter e Setters da classe Game

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public static int getGameNumber() {
		return gameNumber;
	}

	public static void setGameNumber(int gameNumber) {
		Game.gameNumber = gameNumber;
	}

	public GameLevel getLevel() {
		return level;
	}

	public void setLevel(GameLevel level) {
		this.level = level;
	}

	public Chronometer getChronometer() {
		return chronometer;
	}

	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	
}
