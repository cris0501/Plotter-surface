import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.util.Vector;
import java.util.Stack;
import java.lang.Math;

public class Plano extends JPanel{
	private int esc=1;
	private int[] obs = new int[3];
	private Vector<Vector<Stack<String>>> res = new Vector<Vector<Stack<String>>>();
	public Plano(int[] obs) {
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setSize(500, 500);
		this.obs = obs;
	}

	@Override 
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		//g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2); // x
		//g.drawLine(this.getWidth()/2, 0,this.getWidth()/2 , this.getHeight()); // y
		g.setColor(Color.red);
		g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)+(int)(Math.sin(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)-((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.cos(Math.toRadians(obs[1]))) )) ); // -x
		g.setColor(Color.green);
		g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)-(int)(Math.cos(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)-((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.sin(Math.toRadians(obs[1]))) )) ); // -y
		g.setColor(Color.blue);
		g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, (this.getHeight()/2)+(int)(Math.cos(Math.toRadians(obs[2]))*this.getHeight()/2) ); // -z
		
		g.setColor(Color.red);
		g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)-(int)(Math.sin(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)+((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.cos(Math.toRadians(obs[1]))) )) ); // +x
		g.setColor(Color.green);
		g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)+(int)(Math.cos(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)+((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.sin(Math.toRadians(obs[1]))) )) ); // +y
		g.setColor(Color.blue);
		g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, (this.getHeight()/2)-(int)(Math.cos(Math.toRadians(obs[2]))*this.getHeight()/2) ); // +z

		if (!res.isEmpty()){
			int[] aux = new int[3];
			for (int x=0; x<=20; x++){
				for (int y=0; y<=20; y++){
					while ( !res.elementAt(x).elementAt(y).isEmpty() ){
						double aux1 = Double.parseDouble(res.elementAt(x).elementAt(y).pop());
						aux = transform (x-10,y-10,(int)aux1);
						// g.drawLine(aux[0], aux[1], aux[0], aux[1]+(this.getHeight()/2)-aux[2] ); // x1,y1 x2,y2
						g.fillOval((int)(aux[0]-2.5), (int)(aux[1]+(this.getHeight()/2)-aux[2]-2.5), 5, 5); // x1,y1 x2,y2
					}
				}
			}
		}
	}

	public void graficar (Graphics g, Vector<Vector<Stack<String>>> res){
		// int[] aux = new int[3];
		// //aux = transform(-10, 5, 1); //x,y,z
		// //g.drawLine(aux[0], aux[1], aux[0], aux[1]+(this.getHeight()/2)-aux[2] ); // x1,y1 x2,y2

		// g.setColor(Color.red);
		// g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)+(int)(Math.sin(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)-((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.cos(Math.toRadians(obs[1]))) )) ); // -x
		// g.setColor(Color.green);
		// g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)-(int)(Math.cos(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)-((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.sin(Math.toRadians(obs[1]))) )) ); // -y
		// g.setColor(Color.blue);
		// g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, (this.getHeight()/2)+(int)(Math.cos(Math.toRadians(obs[2]))*this.getHeight()/2) ); // -z
		
		// g.setColor(Color.red);
		// g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)-(int)(Math.sin(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)+((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.cos(Math.toRadians(obs[1]))) )) ); // +x
		// g.setColor(Color.green);
		// g.drawLine(this.getWidth()/2, this.getHeight()/2, (this.getWidth()/2)+(int)(Math.cos(Math.toRadians(obs[1]))*this.getWidth()/2), (this.getHeight()/2)+((int)(Math.sin(Math.toRadians(obs[2]))*(this.getHeight()/2)*(Math.sin(Math.toRadians(obs[1]))) )) ); // +y
		// g.setColor(Color.blue);
		// g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, (this.getHeight()/2)-(int)(Math.cos(Math.toRadians(obs[2]))*this.getHeight()/2) ); // +z

		// for (int x=-5; x<=5; x++){
		// 	for (int y=-5; y<=5; y++){
		// 		while ( !res.elementAt(x+5).elementAt(y+5).isEmpty() ){
		// 			double aux1 = Double.parseDouble(res.elementAt(x+5).elementAt(y+5).pop());
		// 			aux = transform (x,y,(int)aux1);
		// 			// g.drawLine(aux[0], aux[1], aux[0], aux[1]+(this.getHeight()/2)-aux[2] ); // x1,y1 x2,y2
		// 			g.fillOval((int)(aux[0]-2.5), (int)(aux[1]+(this.getHeight()/2)-aux[2]-2.5), 5, 5); // x1,y1 x2,y2
		// 		}
		// 	}
		// }
	}

	private int[] transform (int x, int y){
		int[] t = new int[2];
		t[0] = (this.getWidth()/2)+(int)(Math.cos(Math.toRadians(obs[1]))*((-1)*x*15)); // -x
		t[1] = (this.getHeight()/2)-((int)(Math.sin(Math.toRadians(obs[2]))*(y*15)*(Math.cos(Math.toRadians(obs[1]))) ));
		return t;
	}

	private int[] transform (int x, int y, int z){
		int[] t = new int[3];
		t[0] = (this.getWidth()/2)+(int)(Math.cos(Math.toRadians(obs[1]))*((-1)*x*15)); // -x
		t[1] = (this.getHeight()/2)-((int)(Math.sin(Math.toRadians(obs[2]))*(y*15)*(Math.cos(Math.toRadians(obs[1]))) ));
		t[2] = (this.getHeight()/2)+(int)(Math.cos(Math.toRadians(obs[2]))*z*15);
		return t;
	}

	// Coordenadas
	protected double coord_x (double x){
		double real_x = x+this.getWidth()/2;
		return real_x;
	}
	protected double coord_y (double y){
		double real_y = -y+this.getHeight()/2;
		return (real_y);
	}

	public void setobs (int[] obs){
		this.obs = obs;
	}

	public void setFun (Vector<Vector<Stack<String>>> res){
		this.res = res;
	}
}
