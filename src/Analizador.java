import java.util.Arrays;

public class Analizador {
	
	private static long v1[];
	private static long v2[];
	/* 
	 * NOTA IMPORTANTE
	 * 
	 * Esta clase se proporciona solamente para ilustrar el formato de
	 * salida que deberia tener la solucion a este ejericio.
	 * Esta clase debe modificarse completamente para cumplir 
	 * mínimamente los requisitos de esta practica.
	 * Notese que ni siquiera esta completa......
	 */
	
	public static String masCercano(double ratio) {
			if (ratio < 1.5) {                      // aprox 1.0
				return "1";	
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "LOG";	
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "N";
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "NLOG";
			} else if (3 <= ratio && ratio < 6.0) { // aprox 4.0
				return "N2";
			} else if (6 <= ratio && ratio < 10.0) { // aprox 8.0
				return "N3";
			} else if (6 <= ratio && ratio < 10.0) { // aprox 8.0
				return "2N";
			} else { 								 // otras
				return "NF";
			}
	}
	
	public static void main(String arg[]) {
		int n1 = 100000;
		int n2 = 200000;
		
		v1=new long[20];
		v2=new long[20];
	
		int tn1=0;
		Temporizador t = new Temporizador();
		while(tn1<20) {
			
			t.iniciar();
			algo.f(n1);
			t.parar();

			v1[tn1]=t.tiempoPasado();
			tn1++;
			
			t.reiniciar();
		}
		
		int tn2=0;
		while(tn2<20) {
			
			t.iniciar();
			algo.f(n2);
			t.parar();

			v2[tn2]=t.tiempoPasado();
			tn2++;
			
			t.reiniciar();
		}
		
	
		//double ratio = (double)t2/t1;
		System.out.println("T1: "+Arrays.toString(v1)+"\nT2: "+Arrays.toString(v2));
		//System.out.println(masCercano(ratio));
	}
}
