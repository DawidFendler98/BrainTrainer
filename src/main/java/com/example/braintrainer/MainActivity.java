package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView scoreTextView;
    TextView resultTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestion=0;
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));

    }
    public void chooseAnswer(View view){
      if(  Integer.toString(locationOfCorrectAnswer).equals( view.getTag().toString())){
            resultTextView.setText("CORRECT!");
            score++;
      }else{
          resultTextView.setText("WRONG!");
      }
        numberOfQuestion++;
        scoreTextView.setText(score+"/"+numberOfQuestion);
        newQuestion();
    }
    public void playAgain(View view){
        score=0;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        newQuestion();
        scoreTextView.setText(score+"/"+numberOfQuestion);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void newQuestion(){
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        sumTextView.setText(a +"+"+ b);
        locationOfCorrectAnswer= random.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                int wrongAnswer=random.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer=random.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgainButton=findViewById(R.id.playAgainButton);
        startButton=findViewById(R.id.startButton);
        timerTextView=findViewById(R.id.timerTextView);
        sumTextView=findViewById(R.id.sumTextView);
        gameLayout=findViewById(R.id.gameLayout);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        scoreTextView=findViewById(R.id.scoreTextView);
        resultTextView=findViewById(R.id.resultTextView);
        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
