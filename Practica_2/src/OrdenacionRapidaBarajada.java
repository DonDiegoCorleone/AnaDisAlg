////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO:
// GRUPO:
////////////////////////////////////////////////////////////////////////////////////////////

public class OrdenacionRapidaBarajada extends OrdenacionRapida {

    // Implementación de QuickSort con reordenación aleatoria inicial (para comparar tiempos experimentalmente)
    public static <T extends Comparable<? super T>> void ordenar(T v[]) {
        barajar(v);
        ordRapidaRec(v, 0, v.length - 1);
    }

    // reordena aleatoriamente los datos de un vector
    private static <T> void barajar(T v[]) {
        for (int i = v.length - 1; i > 0; i--) {
            int indice = aleat.nextInt(i+1); //numero entre 0 y i ya que i+1 no se incluye
            intercambiar(v, i, indice);
        }
    }
	

}
