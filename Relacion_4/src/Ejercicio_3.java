import java.util.Arrays;

public class Ejercicio_3 {

        public static void imprimirMatriz2(int[][] matriz) {
            for (int x=0; x < matriz.length; x++) {
                System.out.print("|");
                for (int y=0; y < matriz[x].length; y++) {
                    System.out.print (matriz[x][y]);
                    if (y!=matriz[x].length-1) System.out.print("\t");
                }
                System.out.println("|");
            }
        }

        public static void main (String[] args) {
            int est[][] = {{0,1,3,5,8,10,10},{2,3,4,6,7,8,9},{0,2,5,7,8,9,10}};
            int H = 6;
            System.out.println("Solucion: ");
            imprimirMatriz2(horasPorAsignatura(est, H));
        }



        public static int[][] horasPorAsignatura(int[][] est, int H){
            int[][] A = new int [est.length][H+1];
            int[][] D = new int [est.length][H+1];

            for(int j = 0; j <= H; j++) { //se rellena la nota para una asignatura
                A[0][j] = est[0][j];
                D[0][j] = j;
            }

            for(int i = 1; i < est.length; i++) {
                for(int j = 0; j <= H; j++) {
                    A[i][j] = est[i][0] + A[i-1][j]; //asignacion trivial
                    D[i][j]=0;
                    for(int k = 0; k <= j; k++) {    //se rellena la nota para mas de 2 asignaturas
                        int aux = est[i][k] + A[i-1][j-k];
                        if(aux > A[i][j]) {
                            A[i][j] = aux;
                            D[i][j]=k;
                        }
                    }
                }
            }

            int[] obj = new int [est.length];
            int i1 = est.length-1;
            int j = H;


            while(i1 >= 0) {
                System.out.print("Estudiar asignatura "+i1+":");
                obj[i1] = D[i1][j];
                System.out.println(obj[i1]+" horas");
                j = j - D[i1][j];
                i1--;
            }

            System.out.println("Matriz auxiliar:");
            imprimirMatriz2(D);
            System.out.println("Matriz A:");

            return A;
        }
    }

