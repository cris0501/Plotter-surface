import java.util.Stack;
import java.util.Vector;

public class Pila {
	/*
	 * res  (Vector de filas)
		├─ res.elementAt(0) (Vector) ──> [ Stack<String>, Stack<String>, Stack<String> ]
		├─ res.elementAt(1) (Vector) ──> [ Stack<String>, Stack<String>, Stack<String> ]
		└─ res.elementAt(2) (Vector) ──> [ Stack<String>, Stack<String>, Stack<String> ]
	 */
	private Vector<Vector<Stack<String>>> res = new Vector<Vector<Stack<String>>>(10); // Vector de vectores de pilas
	private Stack<String> ePila = new Stack<String>(); // Pila entrada
	private Stack<String> tmp = new Stack<String>(); // Pila temporal de intercambio
	private Stack<String> sPila = new Stack<String>(); // Pila salida
	private String aux = "";

	public Pila(String fun) {
		// Entrada de datos
		String[] expr = limpiar(fun).split(" ");

		// Añadir la array a la Pila de entrada (E)
		for (int i = expr.length - 1; i >= 0; i--) {
			ePila.push(expr[i]);
		}

		try {
			// Algoritmo Infijo a Postfijo
			while (!ePila.isEmpty()) {
				switch (jerarquia(ePila.peek())){
					case 1:
						tmp.push(ePila.pop());
						break;
					case 2:
						while(!tmp.peek().equals("(")) {
							sPila.push(tmp.pop());
						}
						tmp.pop();
						ePila.pop();
						break;
					case 3:
					case 4:
						while(!tmp.isEmpty() && jerarquia(tmp.peek()) >= jerarquia(ePila.peek())) {
							sPila.push(tmp.pop());
						}
						tmp.push(ePila.pop());
						break;
					default:
						sPila.push(ePila.pop());
				}
			}
		}catch(Exception ex){
			System.out.println("Error en la expresión algebraica");
			System.err.println(ex);
		}

		// Preparar el vector res
		// -5 - 5 * -5 - 5 = 11*11 = 121
		// 21*21 = 442
		// 110 * 110
		for (int i=0; i<=20; i++){
			res.add(new Vector<Stack<String>>()); // Pilas de resultados
			for (int j=0; j<=20; j++){
				res.elementAt(i).add(new Stack<String>()); // Pilas de resultados
			}
		}

		sPila = invertir(sPila);
		String operadores = "+-*/%";
		while (!sPila.isEmpty()) {
			if (operadores.contains(sPila.peek())) {
				aux = sPila.pop();
				int i = 0;
				int j = 0;
				double x = -5;
				double y = -5;

				while (i<= 20){
					while (j<= 20){
						res.elementAt(i).elementAt(j).push( evaluacion( aux, res.elementAt(i).elementAt(j).pop(), res.elementAt(i).elementAt(j).pop(), x, y )+"" );
						y += 0.5;
						j += 1;
					}
					x += 0.5;
					i += 1;
					j = 0;
					y = -5;
				}
				// for (int x=-5; x<=5; x++){ // x
				// 	for (int y=-5; y<=5; y++){ // y
				// 		res.elementAt(x+5).elementAt(y+5).push( evaluacion( aux, res.elementAt(x+5).elementAt(y+5).pop(), res.elementAt(x+5).elementAt(y+5).pop(), x, y )+"" );
				// 	}
				// }
			}else {
				aux = sPila.pop();
				for (int i=0; i<=20; i++){
					for (int j=0; j<=20; j++){
						res.elementAt(i).elementAt(j).push(aux);
					}
				}
			}
		}
	}

	private String limpiar(String s) {
		s = s.replaceAll("\\s", ""); // Elimina espacios en blanco
		s = s.replaceAll("P", "3.14"); // Elimina espacios en blanco
		s = s.replaceAll("e", "2.71"); // Elimina espacios en blanco
		s = "(" + s + ")";
		String simbols = "+-*/()";
		String str = "";

		for (int i = 0; i < s.length(); i++) {
			if (simbols.contains("" + s.charAt(i))) {
				str += " " + s.charAt(i) + " ";
			}else str += s.charAt(i);
		}
		return str.replaceAll("\\s+", " ").trim();
	}

	// Jerarquia de los operadores
	private int jerarquia(String op) {
		int prf = 0;
		if (op.equals("^")) prf = 5;
		if (op.equals("*") || op.equals("/")) prf = 4;
		if (op.equals("+") || op.equals("-")) prf = 3;
		if (op.equals(")")) prf = 2;
		if (op.equals("(")) prf = 1;
		return prf;
	}

	private double evaluacion (String op, String n2, String n1, double val1, double val2){
		double num1=0 , num2=0;
		if (n1.equals("x")){
			num1 = val1;
		}
		if (n2.equals("x")){
			num2 = val1;
		}
		if (n1.equals("y")){
			num1 = val2;
		}
		if (n2.equals("y")){
			num2 = val2;
		}
		if (!n1.equals("x") && !n1.equals("y")) {
			num1 = Double.parseDouble(n1);
		}
		if (!n2.equals("x") && !n2.equals("y")) {
			num2 = Double.parseDouble(n2);
		}

		if (op.equals("+")) return (num1 + num2);
		if (op.equals("-")) return (num1 - num2);
		if (op.equals("*")) return (num1 * num2);
		if (op.equals("/")) return (num1 / num2);
		if (op.equals("%")) return (num1 % num2);
		return 0;
	}

	public void mostrarPila(){
		for (int i=-5; i<=5; i++){
			for (int j=-5; j<=5; j++){
				while ( !res.elementAt(i+5).elementAt(j+5).isEmpty() ){
					System.out.print ( res.elementAt(i+5).elementAt(j+5).pop() );
				}
				System.out.print ("\n");
			}
			System.out.print ("\n");
		}
	}

	private Stack<String> invertir (Stack<String> a){
		Stack<String> b = new Stack<String>(); // Pila auxiliar inversa
		while (!a.isEmpty()){
			b.push(a.pop());
		}
		return b;
	}

	public Vector<Vector<Stack<String>>> getRes(){
		return res;
	}
}
