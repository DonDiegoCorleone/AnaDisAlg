////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO:DIEGO CENTENO LINARES
// GRUPO:2ºE
////////////////////////////////////////////////////////////////////////////////////////////

public class OrdenacionRapida extends Ordenacion {

	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
		ordRapidaRec(v, 0, v.length-1);
	}

	public static void intercambiar(int[] a,int p,int d) {

		int aux = a[p];
		a[p] = a[d];
		a[d] = aux;
	}



	// Debe ordenar ascendentemente los primeros @n elementos del vector @v con
	// una implementación recursiva del método de ordenación rápida.
	public static <T extends Comparable<? super T>> void ordRapidaRec(T v[], int izq, int der) {



		int inf=izq;
		int sup=der;

		if(inf<sup) { 	//como se va a partir el vector,
						//en el momento que la posición inf supere sup es que
						//se ha partido del todo el vector



			int s=partir(v,null,inf,sup); //ignoramos T pivote
			//y mi función partir devolvera
			//la nueva pos del pivote
			ordRapidaRec(v,inf,s-1);
			ordRapidaRec(v,s+1,sup);
		}

	}

	public static <T extends Comparable<? super T>> int partir(T v[], T pivote, int izq, int der) {
		int pivoteT=izq;
		int i=izq+1;
		int j=der;

		while(i<j) {
			while(v[j].compareTo(v[pivoteT])>0)j--;
			while(v[i].compareTo(v[pivoteT])<0)i++;

			if(i<j)intercambiar(v,i,j);
		}
		if(v[pivoteT].compareTo(v[j])>0)intercambiar(v,pivoteT,j);

		return j;
	}

	// Pequeños ejemplos para pruebas iniciales.
	public static void main (String args[]) {

		// Un vector de enteros
		Integer vEnt[] = {3,8,6,5,2,9,1,1,4};
		ordenar(vEnt);
		System.out.println(vectorAString(vEnt));

		// Un vector de caracteres
		Character vCar[] = {'d','c','v','b'};
		ordenar(vCar);
		System.out.println(vectorAString(vCar));

	}

}
