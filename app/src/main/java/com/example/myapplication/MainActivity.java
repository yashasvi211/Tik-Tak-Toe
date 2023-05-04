package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int player = 1;
    boolean isWinner = false;
    int imageClicked = -1;
    int[][] winningStates = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    public void load(View view) {
        ImageView v = (ImageView) view;
        int tag = Integer.parseInt(v.getTag().toString());

        if (isWinner == false && gameState[tag] == -1) {
            if (player == 1) {
                v.setImageResource(R.drawable.download);
                gameState[tag] = player;
                Toast.makeText(this, "Player " + player + " has played at position " + tag, Toast.LENGTH_SHORT).show();
                player = 0;
            } else {
                v.setImageResource(R.drawable.images);
                gameState[tag] = player;
                Toast.makeText(this, "Player " + player + " has played at position " + tag, Toast.LENGTH_SHORT).show();
                player = 1;
            }

            for (int i = 0; i < winningStates.length; i++) {
                if (gameState[winningStates[i][0]] == gameState[winningStates[i][1]]
                        && gameState[winningStates[i][1]] == gameState[winningStates[i][2]]
                        && gameState[winningStates[i][0]] >= 0) {
                    Toast.makeText(this, "Player " + (player == 0 ? 1 : 0) + " has won the game!", Toast.LENGTH_SHORT).show();
                    isWinner = true;
                }
            }
        }
    }

    public void reset(View view) {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        int total_image = gridLayout.getChildCount();
        for (int i = 0; i < total_image; i++) {
            ImageView v = (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner = false;
        player = 1;
        imageClicked = -1;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = -1;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
