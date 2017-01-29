package com.johngb.rugbyscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.enabled;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    String lastScore = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateConversionButtonStates();
    }

    /**
     * Team A's try button was pressed.
     *
     * @param v
     */
    public void tryTeamA(View v) {
        scoreTeamA += 5;
        lastScore = "team_a_try";
        processScores();
    }

    /**
     * Team A's conversion button was pressed.
     *
     * @param v
     */
    public void conversionTeamA(View v) {
        scoreTeamA += 2;
        lastScore = "team_a_conversion";
        processScores();
    }

    /**
     * Team A's penalty button was pressed.
     *
     * @param v
     */
    public void penaltyTeamA(View v) {
        scoreTeamA += 3;
        lastScore = "team_a_penalty";
        processScores();
    }

    /**
     * Team B's try button was pressed.
     *
     * @param v
     */
    public void tryTeamB(View v) {
        scoreTeamB += 5;
        lastScore = "team_b_try";
        processScores();
    }

    /**
     * Team B's conversion button was pressed.
     *
     * @param v
     */
    public void conversionTeamB(View v) {
            scoreTeamB += 2;
        lastScore = "team_b_conversion";
        processScores();
        }

    /**
     * Team B's penalty button was pressed.
     *
     * @param v
     */
    public void penaltyTeamB(View v) {
        scoreTeamB += 3;
        lastScore = "team_b_penalty";
        processScores();
    }

    /**
     * Scores reset button was pressed.
     *
     * @param v
     */
    public void resetScores(View v) {
        scoreTeamA = scoreTeamB = 0;
        lastScore = "none";
        processScores();
    }

    /**
     * Update the states of both teams' conversion buttons, as they should only be active after a
     * try has been scored.
     */
    public void updateConversionButtonStates() {
        // If the last score was team A scoring a try, then team A's conversion button should be
        // active, and B's inactive.
        if (lastScore == "team_a_try") {
            Button btnA = (Button) findViewById(R.id.btn_team_a_conversion);
            btnA.setEnabled(true);
            btnA.setVisibility(View.VISIBLE);

            Button btnB = (Button) findViewById(R.id.btn_team_b_conversion);
            btnB.setEnabled(false);
            btnB.setVisibility(View.INVISIBLE);
        }
        // Else if the last score was team A scoring a try, then team A's conversion button should
        // be active, and B's inactive.
        else if (lastScore == "team_b_try") {
            Button btnB = (Button) findViewById(R.id.btn_team_b_conversion);
            btnB.setEnabled(true);
            btnB.setVisibility(View.VISIBLE);

            Button btnA = (Button) findViewById(R.id.btn_team_a_conversion);
            btnA.setEnabled(false);
            btnA.setVisibility(View.INVISIBLE);
        }

        // Else, both should be inactive.
        else {
            Button btnB = (Button) findViewById(R.id.btn_team_b_conversion);
            btnB.setEnabled(false);
            btnB.setVisibility(View.INVISIBLE);

            Button btnA = (Button) findViewById(R.id.btn_team_a_conversion);
            btnA.setEnabled(false);
            btnA.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Process the score displays for both teams. Calling this function simply makes sure that the
     * displayed values match the stored values.
     */
    public void processScores(){
        // Update both team score displays.
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(scoreTeamA));
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(scoreTeamB));

        // Update the conversion button states.
        updateConversionButtonStates();
    }

    /**
     * Displays the given score for Team A.
     */
    public void updateTeamAScoreDisplay() {

        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Displays the given score for Team B.
     */
    public void updateTeamBScoreDisplay() {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));
    }
}

