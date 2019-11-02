package game;

public class Player {
	private String nickName;
	private boolean whitePlayer;
	
	public Player(String nickName, boolean whitePlayer) {
		this.nickName = nickName;
		this.whitePlayer = whitePlayer;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(boolean whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

}
