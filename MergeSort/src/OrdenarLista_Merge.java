import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdenarLista_Merge {

    public static void mezclar(int v_izq[], int v_der[]){


        if(v_der.length==1 && v_izq.length==1){
            if(v_der[0]<v_izq[0]){
                System.out.println("["+v_der[0]+","+v_izq[0]+"]");
            }else {
                System.out.println("[" + v_izq[0] + "," + v_der[0] + "]");
            }
        }else{

            List<Integer> aux = new ArrayList<Integer>();

            int i=0;
            int j=0;

            while(i<v_izq.length && j<v_der.length) {

                if (i == v_izq.length || j == v_der.length) {
                    if (i == v_izq.length) {
                        while (j < v_der.length) {
                            aux.add(v_der[j]);
                            j++;
                        }
                    }
                    if (j == v_der.length) {
                        while (i < v_izq.length) {
                            aux.add(v_izq[i]);
                            i++;
                        }
                    }
                } else {

                    if (v_izq[i] > v_der[j]) {
                        aux.add(v_der[j]);
                        j++;
                    } else {
                        aux.add(v_izq[i]);
                        i++;
                    }
                }
            }
            System.out.println(aux.toString());
        }


    }


        public static void Ordenar_Merge(int v[]){

            int p=0;
            int u=v.length;
            if(v.length>=2) { //si la lista tiene solo un elemento ya está ordenada

                int v_izq[]=Arrays.copyOfRange(v,p,(int)Math.floor((u/2)));
                int v_der[]=Arrays.copyOfRange(v,(int)Math.floor(u/2),u);

                Ordenar_Merge(v_izq); // parte izquierda
                Ordenar_Merge(v_der); // parte derecha
                mezclar(v_izq,v_der);       // ordena

            }
        }

        public static void main(String arg[]) {
            int v[]={4,67,3,6,3,5,9,2,4,3,1,2};

            Ordenar_Merge(v);
        }

}
