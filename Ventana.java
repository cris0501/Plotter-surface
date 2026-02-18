import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ventana extends JFrame implements ActionListener, KeyListener{
	JButton btn_graf = new JButton("Graficar");
	JButton btn_clear = new JButton("Actualizar ejes");
	JTextField input_fun = new JTextField();
	JButton up = new JButton("\u2191");
	JButton down = new JButton("\u2193");
	JButton left = new JButton("\u2190");
	JButton right = new JButton("\u2192");
	JPanel south = new JPanel();
	JPanel west = new JPanel();
	JPanel east = new JPanel();

	int obs[] = {1, 90, 45}; // p, 0, r
	Plano plano = new Plano(obs);

	public Ventana(){
		setTitle("Funciones");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 600);
		setLayout(new BorderLayout());
		initComponents();
		events();
	}

	private void initComponents (){
		south.setLayout(new GridLayout(3,3));
		south.setSize(700, 100);
		south.add(btn_clear);

		west.setLayout(new GridLayout(5,1));
		west.setSize(100, 500);
		west.add(btn_graf);
		west.add(up);
		west.add(down);
		west.add(left);
		west.add(right);

		east.setLayout(new GridLayout(5,1));
		east.add(new JLabel("Introduce la funcion"));
		east.setSize(100, 500);
		east.add(input_fun);

		add(new JLabel("Grafica funciones en determinado intervalo con escala"), BorderLayout.NORTH);
		add(plano, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
	}

	private void events (){
		plano.setFocusable(true);
		plano.addKeyListener(this);
		up.addActionListener(this);
		down.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
		btn_graf.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Pila p = new Pila(input_fun.getText());
		plano.setFun (p.getRes());
		
		if(e.getSource() == btn_graf){
			plano.setobs (obs);
		}

		else if (e.getSource() == up){
			obs[2] = obs[2]+2;
		}

		else if (e.getSource() == down){
			obs[2] = obs[2]-2;
		}

		else if (e.getSource() == left){
			obs[1] = obs[1]-2;
		}

		else if (e.getSource() == right){
			obs[1] = obs[1]+2;
		}

		else if(e.getSource() == btn_clear){
			plano.setobs (obs);
			repaint();
		}
		if (obs[2] >= 90) obs[2]=90;
		if (obs[2] <= 0) obs[2]= 0;
		if (obs[1] >= 90) obs[1]=90;
		if (obs[1] <= 0) obs[1]=0;
		plano.setobs (obs);
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Pila p = new Pila(input_fun.getText());
		plano.setFun (p.getRes());
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("Up")){
			obs[2] = obs[2]+2;
		}
		else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")){
			obs[2] = obs[2]-2;
		}
		else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")){
			obs[1] = obs[1]+2;
		}
		else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left")){
			obs[1] = obs[1]-2;
		}
		if (obs[2] >= 90) obs[2]=90;
		if (obs[2] <= 0) obs[2]= 0;
		if (obs[1] >= 90) obs[1]=90;
		if (obs[1] <= 0) obs[1]=0;
		plano.setobs (obs);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
