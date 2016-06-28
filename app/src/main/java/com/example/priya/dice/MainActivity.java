package com.example.priya.dice;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int userGameScore=0;
    int compGameScore=0;
    int userTurnScore=0;
    int compTurnScore=0;
    ImageView diceImage;
    TextView userScore,totalUserScore,compScore,totalCompScore;
    Button roll,hold;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diceImage=(ImageView)findViewById(R.id.imageView);
        userScore=(TextView)findViewById(R.id.userScore);
        compScore=(TextView)findViewById(R.id.compScore);
        totalUserScore=(TextView)findViewById(R.id.totalUserScore);
        totalCompScore=(TextView)findViewById(R.id.totalCompScore);
        roll=(Button)findViewById(R.id.roll);
        hold=(Button)findViewById(R.id.hold);
    }

    public void onRoll(View view){
        int random=new Random().nextInt(6)+1;
        switch (random){
            case 1:
                diceImage.setImageResource(R.drawable.dice1);
                userTurnScore=0;
                userScore.setText(String.valueOf(userTurnScore));
                computerTurn();
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice2);
                userTurnScore+=2;
                userScore.setText(String.valueOf(userTurnScore));
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice3);
                userTurnScore+=3;
                userScore.setText(String.valueOf(userTurnScore));
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice4);
                userTurnScore+=4;
                userScore.setText(String.valueOf(userTurnScore));
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice5);
                userTurnScore+=5;
                userScore.setText(String.valueOf(userTurnScore));
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice6);
                userTurnScore+=6;
                userScore.setText(String.valueOf(userTurnScore));
        }
    }

    public void onHold(View view){
        userGameScore+=userTurnScore;
        totalUserScore.setText(String.valueOf(userGameScore));
        userTurnScore=0;
        userScore.setText(String.valueOf(userTurnScore));
        if(userGameScore>=100){
            builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("WINNER");
            builder.setMessage("You win");
            builder.setCancelable(true);
            builder.show();
            onReset(null);
        }
        computerTurn();
    }

    public void onReset(View view){
        userTurnScore=0;
        userScore.setText(String.valueOf(userTurnScore));
        compTurnScore=0;
        compScore.setText(String.valueOf(compTurnScore));
        userGameScore=0;
        totalUserScore.setText(String.valueOf(userGameScore));
        compGameScore=0;
        totalCompScore.setText(String.valueOf(compGameScore));
    }

    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this,500);
        }
    };
    public void computerTurn(){
        compTurnScore=0;
        roll.setEnabled(false);
        hold.setEnabled(false);
        Toast.makeText(this,"Computer's turn",Toast.LENGTH_SHORT).show();
        loop:while(compTurnScore<20) {
            int random = new Random().nextInt(6) + 1;
            switch (random) {
                case 1:
                    //image.setImageResource(R.drawable.dice1);
                    //compTurnScore=0;
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    compScore.setText(String.valueOf(compTurnScore));
                    Toast.makeText(this,"Computer hit zero.",Toast.LENGTH_SHORT).show();
                    break loop;

                case 2:
                    //image.setImageResource(R.drawable.dice2);
                    compTurnScore += 2;
                    compScore.setText(String.valueOf(compTurnScore));
                    break;
                case 3:
                    //image.setImageResource(R.drawable.dice3);
                    compTurnScore += 3;
                    compScore.setText(String.valueOf(compTurnScore));
                    break;
                case 4:
                    //image.setImageResource(R.drawable.dice4);
                    compTurnScore += 4;
                    compScore.setText(String.valueOf(compTurnScore));
                    break;
                case 5:
                    //image.setImageResource(R.drawable.dice5);
                    compTurnScore += 5;
                    compScore.setText(String.valueOf(compTurnScore));
                    break;
                case 6:
                    //image.setImageResource(R.drawable.dice6);
                    compTurnScore += 6;
                    compScore.setText(String.valueOf(compTurnScore));
            }
            handler.postDelayed(runnable,0);
        }

        roll.setEnabled(true);
        hold.setEnabled(true);
        if(compTurnScore>=20) {
            compGameScore += compTurnScore;
            totalCompScore.setText(String.valueOf(compGameScore));
        }

        //compTurnScore=0;
        compScore.setText(String.valueOf(compTurnScore));
        if(compGameScore>=100){
            builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("WINNER");
            builder.setMessage("Computer wins the game.");
            builder.setCancelable(true);
            builder.show();
            onReset(null);
        }
    }
}
