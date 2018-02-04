package sudoku.arthur.com.sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class choix_grille extends Activity {

    Activity lecontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_grille);

        lecontext = this;
        lecontext.setTitle("Choix grille");

        final TextView nivselect = (TextView) findViewById(R.id.nivselect);

        Bundle objetbunble = this.getIntent().getExtras();
        String niveau = objetbunble.getString("niveau");
        nivselect.setText("Niveau: " + niveau);

        final ArrayList<vGrille> items = new ArrayList<vGrille>();

        for(int i = 1;i<=100;i++){

            vGrille v = new vGrille(i,Double.parseDouble(niveau));
            items.add(v);

        }

        MyAdapter adapter = new MyAdapter(this,items);

        ListView listView = (ListView) findViewById(R.id.choixgrille);
        listView.setAdapter(adapter);

        ListView choixListe = (ListView) findViewById(R.id.choixgrille);

        choixListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                System.out.println("Position: " + position + " Id: "  +id);
                AlertDialog alertDialog = new AlertDialog.Builder(choix_grille.this).create();
                alertDialog.setTitle("Information");
                alertDialog.setMessage(""+(position+1) + " - " + items.get(position).done + "%");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continuer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent defineIntent = new Intent(lecontext, Sudoku.class);
                                Bundle objetbunble = new Bundle();
                                objetbunble.putString("position",""+position);
                                defineIntent.putExtras(objetbunble);
                                lecontext.startActivity(defineIntent);

                            }
                        });
                alertDialog.show();
            }
        });


    }

}





