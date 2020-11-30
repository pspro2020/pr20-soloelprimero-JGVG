package withInvokeAny;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Position> {
    int matriz[];
    int row, num;

    public Task(int row, int matriz[], int num){
        this.row = row;
        this.matriz = matriz;
        this.num = num;
    }

    @Override
    public Position call() {
        Position position = null;

        try {
            TimeUnit.MILLISECONDS.sleep((long)(Math.random()*999)+500);
            for(int column = 0; column < matriz.length; column++){
                if(matriz[column] == num){
                    position = new Position(row+1, column+1);
                }
            }
        } catch (InterruptedException ignored) {}

        if(position == null){
            throw new RuntimeException();
        }else{
            return position;
        }

    }
}
