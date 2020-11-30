package withInvokeAny;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int matriz[][] = new int[5][5];
        int n_buscado = (int)((Math.random()*19)+1);
        List<Task> tasks = new ArrayList<>();

        crearMatriz(matriz);
        mostrarMatriz(matriz, n_buscado);

        for (int i = 0; i < matriz.length; i++){
            tasks.add(new Task(i, matriz[i], n_buscado));
        }

        try {
            Position resultado = fixedThreadPool.invokeAny(tasks);
            System.out.println("\nEl primer valor "+ n_buscado+" encontrado se encuentra en la fila "+resultado.getRow()+" y en la columna "+resultado.getColum()+".");

        } catch (InterruptedException ignore){
        }catch ( ExecutionException e) {
            System.out.print("\nNo ha sido encontrado el valor "+n_buscado+" en la matriz.\n");
        }finally {
            fixedThreadPool.shutdown();
        }
    }

    private static void crearMatriz(int[][] matriz) {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
             matriz[i][j] = (int)((Math.random()*19)+1);
            }
        }
    }

    private static void mostrarMatriz(int[][] matriz, int n_buscado) {
        System.out.println("Valor buscado: "+ n_buscado+"\n");
        for(int i = 0; i < 5; i++){
            System.out.print("Fila "+(i+1)+"  ->  ");
            for(int j = 0; j < 5; j++){
                if(matriz[i][j] < 10){
                    System.out.print(matriz[i][j]+"   ");
                }else{
                    System.out.print(matriz[i][j]+"  ");
                }
            }
            System.out.println();
        }
    }
}
