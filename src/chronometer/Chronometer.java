package chronometer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.enums.GameLevel;

public class Chronometer extends JPanel implements ActionListener {
	
	//Atributos da classe Chronometer
	private int minutes;
	private int seconds;
	private int miliseconds;
	
	private JLabel labelTimer;
	private JLabel titleTimer;
	
	private Timer time;
	
	private GameLevel level;
	//Atributos que irão guardar o limite de minutos e segundos dependendo do nível do jogo passado
	private int levelMinutes;
	private int levelSeconds;
	
	public Chronometer(GameLevel level) {
		
		//Inicializando os atributos
		this.minutes = 0;
		this.seconds = 0;
		this.miliseconds = 0;
		this.level = level; //Level of the Chess Game
		this.levelMinutes = this.setTimeLimitMinutes(level);
		this.levelSeconds = this.setTimeLimitSeconds(level);
		
		//Inicializando a tela do cronômetro java Swing
		
		
		this.setSize(400,100);
		this.setLocation(850,650);
		
		time = new Timer(1,this); //Registrando o time como um "ouvinte" a cada 1 milisegundo
		
		labelTimer = new JLabel("00 : 00 : 000"); //Inicializando o label
		labelTimer.setFont(new Font("Arial",Font.BOLD,30));;
		titleTimer = new JLabel("TIME TO PLAY");
		titleTimer.setFont(new Font("Arial",Font.BOLD,40));
		
		//Adicionando os componentes ao JFrame do cronômetro
		this.add(titleTimer);
		this.add(labelTimer);
		
		
	}
	
	//Implementando o método da interface do ActionListener
	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() instanceof Timer) {
			this.setMiliseconds(this.getMiliseconds()+1);
			
			if(this.getMiliseconds()==1000) {
				this.setMiliseconds(0);
				this.setSeconds(this.getSeconds()+1);
				
				if(this.getSeconds()==this.levelSeconds) {
					this.setSeconds(0);
					if(this.levelSeconds==60) {
						this.setMinutes(this.getMinutes()+1);
					}					
					if(this.getMinutes()==this.levelMinutes) {
						this.setMinutes(0);
					}
				}
				
			}
			
			if(this.getMiliseconds()<10) {		
				if(this.getSeconds()<10) {
					this.getLabelTimer().setText("0"+this.getMinutes()+" : 0"+this.getSeconds()+" : 00"+this.getMiliseconds());
				}
				else {
					this.getLabelTimer().setText("0"+this.getMinutes()+" : "+this.getSeconds()+" : 00"+this.getMiliseconds());
				}
			}
			else if(this.getMiliseconds()<100) {
				if(this.getSeconds()<10) {
					this.getLabelTimer().setText("0"+this.getMinutes()+" : 0"+this.getSeconds()+" : 0"+this.getMiliseconds());
				}
				else {
					this.getLabelTimer().setText("0"+this.getMinutes()+" : "+this.getSeconds()+" : 0"+this.getMiliseconds());
				}
			}
			else {
				if(this.getSeconds()<10) {
					this.getLabelTimer().setText("0"+this.getMinutes()+" : 0"+this.getSeconds()+" : "+this.getMiliseconds());
				}
				else {
					this.getLabelTimer().setText("0"+this.getMinutes()+" : "+this.getSeconds()+" : "+this.getMiliseconds());
				}
			}
		}
		
		
	}
	
	//Método para definir o limite de tempo (minutos) a depender do nivel escolhido pelo usuário
	public int setTimeLimitMinutes(GameLevel level) {
		if(level.equals(GameLevel.BEGINNER)) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//Método para definir o limite de tempo (segundos) a depender do nivel escolhido pelo usuário
	public int setTimeLimitSeconds(GameLevel level) {
		if(level.equals(GameLevel.BEGINNER)) {
			return 60;
		}
		else if(level.equals(GameLevel.INTERMEDIATE)) {
			return 30;
		}
		else {
			return 15;
		}
	}
	
	//Programa Principal
	public static void main(String[] args) {
		Chronometer chronometer = new Chronometer(GameLevel.ADVANCED);
		chronometer.setVisible(true);
	}

	//Getters e Setters-------------------------------------------------------------------------------
	
	public int getLevelMinutes() {
		return levelMinutes;
	}

	public void setLevelMinutes(int levelMinutes) {
		this.levelMinutes = levelMinutes;
	}

	public int getLevelSeconds() {
		return levelSeconds;
	}

	public void setLevelSeconds(int levelSeconds) {
		this.levelSeconds = levelSeconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMiliseconds() {
		return miliseconds;
	}

	public void setMiliseconds(int miliseconds) {
		this.miliseconds = miliseconds;
	}

	public Timer getTime() {
		return time;
	}

	public void setTime(Timer time) {
		this.time = time;
	}

	public JLabel getLabelTimer() {
		return labelTimer;
	}


	public GameLevel getLevel() {
		return level;
	}

	public void setLevel(GameLevel level) {
		this.level = level;
	}
	
	

}
