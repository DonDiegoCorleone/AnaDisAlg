import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdenarLista_Merge {

    public static void mezclar(int []v,int ini,int med,int fin){

        int [] aux = new int[fin-ini+1];
        int i=ini;
        int j=med+1;
        int k=0; //contador de auxiliar
        while(i<=med && j<=fin) {
            if (v[i] > v[j]) {
                aux[k]=v[j];
                j++;
            } else {
                aux[k]=v[i];
                i++;
            }
            k++;
        }
        while(i<=med){
            aux[k]=v[i];
            i++;
            k++;
        }
        while(j<=fin){
            aux[k]=v[j];
            j++;
            k++;
        }

        k=0;
        System.out.print("\n");

        for (int f=ini; f<=fin; f++){
            v[f] = aux[k];k++;
            System.out.print(v[f]);
        }
    }


        public static void Ordenar_Merge(int v[],int ini,int fin){

            if(ini<fin) { //si la lista tiene solo un elemento ya está ordenada

                Ordenar_Merge(v,ini,((fin+ini)/2)); // parte izquierda
                Ordenar_Merge(v,((fin+ini)/2)+1,fin); // parte derecha
                int med=(ini+fin)/2;
                mezclar(v,ini,med,fin); // ordena
            }
        }

        public static void main(String arg[]) {

            int v[]={3,2,7,1,5};
            Ordenar_Merge(v,0,4);
        }

}
