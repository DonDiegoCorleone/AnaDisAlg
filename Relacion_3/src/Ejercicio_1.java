public class Ejercicio_1 {


    public static int encontrarI(int v[],int inf,int sup){
        int solucion=-1;

        int centro=((inf+sup)/2);

        if(inf<=sup) {
            if (v[centro] > centro) {
                solucion = encontrarI(v, inf, centro - 1);
            } else if (v[centro] < centro) {
                solucion = encontrarI(v, centro + 1, sup);
            } else if (centro == v[centro]) {
                solucion = centro;
            }
        }

        return solucion;
    }

    public static void main(String[] args) {
        int v[]={1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //true

        System.out.print(encontrarI(v,0,v.length-1));
    }
}
