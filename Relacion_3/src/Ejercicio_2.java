public class Ejercicio_2 {


    public static int mediana(int v[],int w[],int infV,int infW,int supV,int supW){

        int centroV=((supV+infV)/2);
        int centroW=((supW+infW)/2);

        if(v.length==1){
            return Math.max(v[0],w[0]);
        }

        if(supV-infV>1) {

            if (v[centroV] < w[centroW]) {
                return mediana(v, w, centroV, infW, supV, centroW);
            } else if (w[centroW] < v[centroV]) {
                return mediana(v, w, infV, centroW, centroV, supW);
            }
        }
        return Math.min(v[supV],w[supW]);
    }

    // 7,9,8
    public static void main(String[] args) {
        int[] V, W, X;

        // Datos de ejemplo
        V = new int[]{1,3,5,7,8,9};
        W = new int[]{4,6,7,8,9,10};
        System.out.print(mediana(V,W,0,0,V.length-1,W.length-1));
    }
}
