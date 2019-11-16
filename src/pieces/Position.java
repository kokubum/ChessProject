package pieces;
//Classe para definir a posição de uma peça
public class Position {
	private int x;
	private int y;
	
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	//Faço uma sobescrita do método da classe object para poder fazer uma comparação das posição baseada em valores e não em instância
	@Override
	public boolean equals(Object object) {
		if(object instanceof Position) {
			Position position = (Position)object;
			if(position.getX()==this.getX() && position.getY()==this.getY()) {
				return true;
			}
		}
		return false;
	}
	
	//Getters e Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
