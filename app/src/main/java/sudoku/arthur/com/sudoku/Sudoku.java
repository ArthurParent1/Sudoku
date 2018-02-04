package sudoku.arthur.com.sudoku;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sudoku extends Activity {

    Activity lecontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        lecontext = this;
        lecontext.setTitle("Sudoku");

        Bundle objetbunble = this.getIntent().getExtras();
        String niveau = objetbunble.getString("position");






    }
}
