package sudoku.arthur.com.sudoku;

/**
 * Created by arthur on 01/02/2018.
 */

public class vGrille {

    public int num;
    public double level;
    public int done;


    public vGrille(int num, double level) {
        this.num = num;
        this.level = level;

        done = (int)(Math.random()*100);

    }
}
