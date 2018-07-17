package com.tanishqmittal.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     int activePlayer = 0;
    // 0 is cross and 1 is circle

    int winner = 2;
    // 0 is cross and 1 is circle 2 means draw

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // 2 means un-played state.

    int gameOver = 0;
    // 1 means game over

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void playAgain (View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        for(int i =0; i< gameState.length ;i++) {
            gameState[i] = 2;
        }
        winner = 2;

        gameOver = 0;
        activePlayer = 0;
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
    public void getPeg (View view) {

        System.out.println(view.getTag().toString());
        int position = Integer.parseInt(view.getTag().toString());


        if (gameState[position] == 2&&gameOver==0) {

            ImageView peg = (ImageView) view;
            peg.setTranslationY(-1000f);

            if (activePlayer == 0) {
                peg.setImageResource(R.drawable.cross);
                gameState[position] = 0;
                activePlayer = 1;
            } else {
                peg.setImageResource(R.drawable.circle);
                gameState[position] = 1;
                activePlayer = 0;
            }
            peg.animate().translationYBy(1000f).rotation(360f).setDuration(500);



            // checking if player has won or not

            for (int[] winningPosition : winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] !=2)
                {
                    winner = gameState[winningPosition[0]];
                    gameOver = 1;
                    System.out.println("winner is " + winner);
                    break;
                }
                else {
                    gameOver = 1;
                    for(int i=0;i< gameState.length; i++ ) {
                        if(gameState[i]==2) {
                            gameOver = 0;
                        }

                    }
                }

            }
            if (gameOver == 1) {
                System.out.println("yes this area is working");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                linearLayout.setVisibility(View.VISIBLE);
                if(winner==0) {

                    TextView winnerMessage = (TextView) findViewById(R.id.textView2);
                    winnerMessage.setText("Cross has won !");
                }
                else if(winner==1){
                    TextView winnerMessage = (TextView) findViewById(R.id.textView2);
                    winnerMessage.setText("Circle has won !");
                }
                else {
                    TextView winnerMessage = (TextView) findViewById(R.id.textView2);
                    winnerMessage.setText("Its a Draw!");

                }

            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
