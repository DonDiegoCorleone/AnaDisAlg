////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO:DIEGO CENTENO LINARES
// GRUPO:2�E
////////////////////////////////////////////////////////////////////////////////////////////

public class OrdenacionRapida extends Ordenacion {

	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
		ordRapidaRec(v, 0, v.length-1);
	}

	// Debe ordenar ascendentemente los primeros @n elementos del vector @v con
	// una implementaci�n recursiva del m�todo de ordenaci�n r�pida.
	public static <T extends Comparable<? super T>> void ordRapidaRec(T v[], int izq, int der) {



		int inf=izq;
		int sup=der;

		if(inf<sup) { 	//como se va a partir el vector,
						//en el momento que la posici�n inf supere sup es que
						//se ha partido del todo el vector



			int s = partir(v,v[izq],inf,sup); //ignoramos T pivote
			//y mi funci�n partir devolvera
			//la nueva pos del pivote
			ordRapidaRec(v,inf,s-1);
			ordRapidaRec(v,s+1,sup);
		}

	}

	public static <T extends Comparable<? super T>> int partir(T v[], T pivote, int izq, int der) {


		int i=izq;
		int j=der;

		while(i<j) {
			while(v[j].compareTo(pivote)>0 && j>0)j--;
			while(v[i].compareTo(pivote)<=0 && i<v.length-1)i++;

			if(i<j)intercambiar(v,i,j);
		}
		if(pivote.compareTo(v[j])>0)intercambiar(v,izq,j);

		return j;
	}

	// Peque�os ejemplos para pruebas iniciales.
	public static void main (String args[]) {

		// Un vector de enteros
		Integer vEnt[] = {3,8,6,5,2};
		ordenar(vEnt);
		System.out.println(vectorAString(vEnt));

		// Un vector de caracteres
		Character vCar[] = {'d','c','v','b'};
		ordenar(vCar);
		System.out.println(vectorAString(vCar));

	}

}
