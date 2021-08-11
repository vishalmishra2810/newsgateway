package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class newsQuiz extends AppCompatActivity {

    private TextView questionTV, questionNumberTV;
    private Button option1Btn,option2Btn,option3Btn,option4Btn;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0, questionAttempted = 1, currentpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_news_quiz);

        questionTV = findViewById (R.id.idTVQuestion);
        questionNumberTV = findViewById (R.id.idTVQuestionAttempted);
        option1Btn = findViewById (R.id.idBtnOptional1);
        option2Btn = findViewById (R.id.idBtnOptional2);
        option3Btn = findViewById (R.id.idBtnOptional3);
        option4Btn = findViewById (R.id.idBtnOptional4);
        quizModalArrayList = new ArrayList<> ();
        random = new Random ();
        getQuizQuestion(quizModalArrayList);
        currentpos = random.nextInt (quizModalArrayList.size ());
        setDataToViews(currentpos);
        option1Btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get (currentpos).getAnswer ().trim ().toLowerCase ().equals (option1Btn.getText ().toString ().trim ().toLowerCase ())){

                currentScore++;
            }
                questionAttempted++;
                currentpos = random.nextInt (quizModalArrayList.size ());
                setDataToViews (currentpos);
            }
        });
        option2Btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get (currentpos).getAnswer ().trim ().toLowerCase ().equals (option2Btn.getText ().toString ().trim ().toLowerCase ())){

                    currentScore++;
                }
                questionAttempted++;
                currentpos = random.nextInt (quizModalArrayList.size ());
                setDataToViews (currentpos);
            }
        });

        option3Btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get (currentpos).getAnswer ().trim ().toLowerCase ().equals (option3Btn.getText ().toString ().trim ().toLowerCase ())){

                    currentScore++;
                }
                questionAttempted++;
                currentpos = random.nextInt (quizModalArrayList.size ());
                setDataToViews (currentpos);
            }
        });


        option4Btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get (currentpos).getAnswer ().trim ().toLowerCase ().equals (option4Btn.getText ().toString ().trim ().toLowerCase ())){

                    currentScore++;
                }
                questionAttempted++;
                currentpos = random.nextInt (quizModalArrayList.size ());
                setDataToViews (currentpos);
            }
        });
    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog (newsQuiz.this);
        View bottomSheetView = LayoutInflater.from (getApplicationContext ()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById (R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById (R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById (R.id.idBtnRestart);
        scoreTV.setText ("Your Score is \n"+currentScore + "/10");
        restartQuizBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                currentpos = random.nextInt (quizModalArrayList.size ());
                setDataToViews (currentpos);
                questionAttempted = 1;
                currentScore = 0;
                bottomSheetDialog.dismiss ();;
            }
        });

        bottomSheetDialog.setCancelable (false);
        bottomSheetDialog.setContentView (bottomSheetView);
        bottomSheetDialog.show ();

    }

    private void setDataToViews(int currentpos){
        questionNumberTV.setText ("Questions Attempted : "+questionAttempted + "/10");
        if(questionAttempted == 10){
            showBottomSheet ();
        }else{

            questionTV.setText (quizModalArrayList.get (currentpos).getQuestion ());
            option1Btn.setText (quizModalArrayList.get (currentpos).getOption1 ());
            option2Btn.setText (quizModalArrayList.get (currentpos).getOption2 ());
            option3Btn.setText (quizModalArrayList.get (currentpos).getOption3 ());
            option4Btn.setText (quizModalArrayList.get (currentpos).getOption4 ());

        }

    }

    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {

        quizModalArrayList.add(new QuizModal ("India's Current Prime Minister ?","Narendra Modi","Manmohan Singh","Indira Gandhi","Rajiv Gandhi","Narendra Modi"));
        quizModalArrayList.add(new QuizModal ("Who is the First World Test Championship WINNER?","India","New Zealand","Australia","South Africa","New Zealand"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));
        quizModalArrayList.add(new QuizModal ("Who is First T-20 World-cup WINNER?","India","New Zealand","Australia","Pakistan","India"));


    }
}