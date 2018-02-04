package sudoku.arthur.com.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Dimension;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by arthur on 01/02/2018.
 */

public class Grille extends View implements View.OnTouchListener{

    LinkedList<rectangle> rectangles;
    rectangle rect[][];
    rectangle btn[];
    Grille grille = (Grille) findViewById(R.id.grille);
    String grilleNb = "008203500009670408346050702430010059967005001000496203280034067703500904004107020";
    int largeurEcran = 1440;
    int hauteurEcran = 2560;
    int tailleCarre;

   int remainPosX;
   int remainPosY;

   int remainBtnPosX;

   boolean generationGrille = false;


    public Grille(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectangles = new LinkedList<rectangle>();
        this.setOnTouchListener(this);


       /* int xmodif;
        int xmodifbtn;
        int ymodif;
        int index;*/

        remainPosY = 0;
        remainPosX = 0;
        remainBtnPosX = 0;

        rect = new rectangle[9][9];
        btn = new rectangle[9];

       /* for(int y=0;y<9;y++){   //Création de la grille

            for(int x=0;x<9;x++){

                xmodif = (159*x)+x;
                ymodif = (159*y)+y;
                index = (x*9) + y;

                rect[y][x] = new rectangle(xmodif,ymodif,159,""+grilleNb.charAt(index),false);

            }
        }

        for(int x=0;x<9;x++){   //Création des boutons

            xmodifbtn = (159*x)+x;

            btn[x] = new rectangle(xmodifbtn,1500,159,""+(x+1),false);

        }*/

    }

    @Override
    public void onDraw(Canvas canvas){

        largeurEcran = grille.getWidth();
        hauteurEcran = grille.getHeight();

        if(!generationGrille){
            genGrille();
        }


        System.out.println("draw" + grille.getWidth());

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Paint p = new Paint();
        p.setColor(Color.WHITE);

        canvas.drawRect(0,0,grille.getWidth(),grille.getHeight(),paint);

        for(int y=0;y<9;y++) {  //Affichage grille

            for (int x = 0; x < 9; x++) {

                rect[y][x].draw(canvas);

            }
        }

        for (int x = 0; x < 9; x++) {   //Affichage boutons

            btn[x].draw(canvas);

        }

    }

    public void genGrille(){

        generationGrille = true;

        int xmodif;
        int xmodifbtn;
        int ymodif;
        int index;

        tailleCarre = (largeurEcran-9)/9;

        System.out.println("TAILLE SECTION: " + tailleCarre);


        for(int y=0;y<9;y++){   //Création de la grille

            for(int x=0;x<9;x++){

                xmodif = (tailleCarre*x)+x;
                ymodif = (tailleCarre*y)+y;
                index = (x*9) + y;

                rect[y][x] = new rectangle(xmodif,ymodif,tailleCarre,""+grilleNb.charAt(index),false);

            }
        }

        for(int x=0;x<9;x++){   //Création des boutons

            xmodifbtn = (tailleCarre*x)+x;

            btn[x] = new rectangle(xmodifbtn,largeurEcran+200,tailleCarre,""+(x+1),false);

        }

    }


    public boolean enPrise(int xTest, int yTest, String valeurTest){    //Permet de vérifier la présence du même nombre en horizontal et vertical

        boolean flag_trouve = false;
        System.out.println("Recherche en: x: " + xTest + " y: " + yTest);
        for(int aX = 0; aX < 9; aX++){
            if(rect[yTest][aX].numero.contains(valeurTest)){
                flag_trouve = true;
            }
        }
        for(int aY = 0; aY < 9; aY++){
            if(rect[aY][xTest].numero.contains(valeurTest)){
                flag_trouve = true;
            }
        }
        return flag_trouve;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if(y<largeurEcran){

            int posX, posY;

            posX = x/tailleCarre;
            posY = y/tailleCarre;


            if(rect[posY][posX].numero.contains("0")){  //Vérification sur c'est une case modifiable
                rect[remainPosY][remainPosX].selected = false;
                rect[posY][posX].selected = true;
                remainPosX = posX;
                remainPosY = posY;
                System.out.println("PosXRemain: " +remainPosX + " PosYremain " + remainPosY);
                btn[remainBtnPosX].selected = false;
            }

        } else{

            int posX;
            posX = x/tailleCarre;

            btn[remainBtnPosX].selected = false;
            btn[posX].selected = true;
            remainBtnPosX = posX;

            //Inscription numéro
            if (!enPrise(remainPosX,remainPosY,btn[remainBtnPosX].numero)){ //Si le placement est bon
                rect[remainPosY][remainPosX].numero = btn[remainBtnPosX].numero;
                btn[remainBtnPosX].selected = false;
                rect[remainPosY][remainPosX].selected = false;

            }

        }

        this.invalidate();
        return true;
    }
}

class rectangle{

    int posX, posY,posXdec,posYdec, width, height;
    String numero;
    private Paint p, p2,p_selected, p_ok, p_ko;
    boolean selected = false;

    public rectangle(int positionX, int positionY, int Size, String inscription, boolean select){

        p = new Paint();
        p2 = new Paint();
        p_ok = new Paint();
        p_ko = new Paint();
        p_selected = new Paint();
        posX = positionX;
        posY = positionY;
        numero = inscription;
        width = Size;
        height = Size;
        selected = select;

    }

    public void draw(Canvas canvas){
        p.setColor(Color.WHITE);
        p_selected.setColor(Color.YELLOW);
        p_ok.setColor(Color.RED);
        p_ko.setColor(Color.GRAY);
        p2.setColor(Color.BLACK);
        p2.setTextSize(40);

        posXdec = posX + width;
        posYdec = posY+height;



        if(selected){   //Couleur selection
            canvas.drawRect(posX,posY,posXdec,posYdec,p_selected);
        }else{
            canvas.drawRect(posX,posY,posXdec,posYdec,p);
        }

        if (!numero.contains("0")){
            canvas.drawText(numero,posXdec-(width/2),posYdec-(width/2),p2);
        }
    }
}
