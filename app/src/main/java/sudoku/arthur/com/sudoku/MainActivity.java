package sudoku.arthur.com.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity{

    Activity lecontext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lecontext = this;

        Button btnNiv1 = (Button) findViewById(R.id.niveau1);
        Button btnNiv2 = (Button) findViewById(R.id.niveau2);


        btnNiv1.setOnClickListener(new Button.OnClickListener(){ public void onClick(View v) {
            Intent defineIntent = new Intent(lecontext, choix_grille.class);
            Bundle objetbunble = new Bundle();
            objetbunble.putString("niveau","1");
            defineIntent.putExtras(objetbunble);
            lecontext.startActivity(defineIntent);} });

        btnNiv2.setOnClickListener(new Button.OnClickListener(){ public void onClick(View v) {
            Intent defineIntent = new Intent(lecontext, choix_grille.class);
            Bundle objetbunble = new Bundle();
            objetbunble.putString("niveau","2");
            defineIntent.putExtras(objetbunble);
            lecontext.startActivity(defineIntent);} });

    }
}
