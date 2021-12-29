public class NReinas {

    int nR=4;
    boolean exito=false;


    public boolean reinas(int [] solucion,int etapa){
        if(etapa>nR)return false;
        exito=false;
        Solucion[etapa]=-1;
        while(solucion[etapa]!=nR || exito){
            solucion[etapa]=solucion[etapa]+1;
            if(esValido(solucion,etapa)){
                if (etapa!=nR){
                    exito=reinas(solucion,etapa+1);
                }else{
                    exito=true;
                }
            }
        }
        return exito;
    }
}
