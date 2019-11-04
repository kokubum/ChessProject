package chronometer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import game.enums.GameLevel;

public class Chronometer extends JFrame implements ActionListener {
	
	//Atributos da classe Chronometer
	private int minutes;
	private int seconds;
	private int miliseconds;
	private JButton startStopButton;
	private JButton restartButton;
	private JLabel labelTimer;
	private Timer time;
	private GameLevel level;
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
		this.setTitle("TIME TO PLAY");
		this.setResizable(false);
		this.setSize(300,80);
		this.setLocationRelativeTo(null); //Faz com que a tela seja inicializada no centro da tela
		this.getContentPane().setLayout(new FlowLayout()); 
		
		startStopButton = new JButton("Start"); //startStopButton.setText("Start") = mesma função
		startStopButton.addActionListener(this); //Registrando o botao da classe como "ouvinte"
		
		restartButton = new JButton("Restart");//restartButton.setText("Restart") = mesma função
		restartButton.addActionListener(this);//Registrando o botão como um "ouvinte"
		
		time = new Timer(1,this); //Registrando o time como um "ouvinte" a cada 1 milisegundo
		
		labelTimer = new JLabel("00 : 00 : 000"); //Inicializando o label
		
		//Adicionando os componentes ao JFrame do cronômetro
		this.getContentPane().add(labelTimer);
		this.getContentPane().add(startStopButton);
		this.getContentPane().add(restartButton);
		
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
		else if(event.getSource() == this.getStartStopButton()) {
			
			if(this.getStartStopButton().getText().equals("Start")) {
				this.getTime().start();
				this.getStartStopButton().setText("Stop");
			}
			else {
				this.getTime().stop();;
				this.getStartStopButton().setText("Start");
			}
		}
		else {
			this.setMiliseconds(0);
			this.setSeconds(0);
			this.setMinutes(0);
			this.getLabelTimer().setText("00 : 00 : 000");
		}
		
		
	}
	
	//Método para definir o limite de tempo (minutos) a depender do nivel escolhido pelo usuário
	public int setTimeLimitMinutes(GameLevel level) {
		if(level.equals(GameLevel.BEGINNER)) {
			return 2;
		}
		else if(level.equals(GameLevel.INTERMEDIATE)) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//Método para definir o limite de tempo (segundos) a depender do nivel escolhido pelo usuário
	public int setTimeLimitSeconds(GameLevel level) {
		if(level.equals(GameLevel.BEGINNER) || level.equals(GameLevel.INTERMEDIATE)) {
			return 60;
		}
		else {
			return 30;
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

	public JButton getStartStopButton() {
		return startStopButton;
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
