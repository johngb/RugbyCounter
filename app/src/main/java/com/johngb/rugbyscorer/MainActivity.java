package com.johngb.rugbyscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.enabled;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private TextView scoreViewTeamA;
    private Button btnTeamAConversion;

    private int scoreTeamB = 0;
    private TextView scoreViewTeamB;
    private Button btnTeamBConversion;

    private String lastScore = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the scoreViews so that we don't have to do ao findViewBy often.
        scoreViewTeamA = (TextView) findViewById(R.id.team_a_score);
        btnTeamAConversion = (Button) findViewById(R.id.btn_team_a_conversion);

        scoreViewTeamB = (TextView) findViewById(R.id.team_b_score);
        btnTeamBConversion = (Button) findViewById(R.id.btn_team_b_conversion);

        updateConversionButtonStates();
    }

    public void onSubmitScore(View v) {

        switch (v.getId()) {

            case R.id.btn_team_a_try:
                scoreTeamA += 5;
                lastScore = "team_a_try";
                break;

            case R.id.btn_team_a_conversion:
                scoreTeamA += 2;
                lastScore = "team_a_conversion";
                break;

            case R.id.btn_team_a_penalty:
                scoreTeamA += 3;
                lastScore = "team_a_penalty";
                break;

            case R.id.btn_team_b_try:
                scoreTeamB += 5;
                lastScore = "team_b_try";
                break;

            case R.id.btn_team_b_conversion:
                scoreTeamB += 2;
                lastScore = "team_b_conversion";
                break;

            case R.id.btn_team_b_penalty:
                scoreTeamB += 3;
                lastScore = "team_b_penalty";
                break;

            case R.id.btn_reset_scores:
                scoreTeamA = scoreTeamB = 0;
                lastScore = "";
                break;
        }

        // Update both team score displays.
        scoreViewTeamA.setText(String.valueOf(scoreTeamA));
        scoreViewTeamB.setText(String.valueOf(scoreTeamB));

        // Update the conversion button states.
        updateConversionButtonStates();
    }

    /**
     * Update the states of both teams' conversion buttons, as they should only be active after a
     * try has been scored.
     */
    public void updateConversionButtonStates() {

        switch (lastScore) {

            // If the last score was team A scoring a try, then team A's conversion button should be
            // active, and B's inactive.
            case "team_a_try":
                btnTeamAConversion.setEnabled(true);
                btnTeamAConversion.setVisibility(View.VISIBLE);

                btnTeamBConversion.setEnabled(false);
                btnTeamBConversion.setVisibility(View.INVISIBLE);

                break;

            // Else if the last score was team A scoring a try, then team A's conversion button should
            // be active, and B's inactive.
            case "team_b_try":
                btnTeamBConversion.setEnabled(true);
                btnTeamBConversion.setVisibility(View.VISIBLE);

                btnTeamAConversion.setEnabled(false);
                btnTeamAConversion.setVisibility(View.INVISIBLE);

                break;

            // Else, both should be inactive.
            default:
                btnTeamBConversion.setEnabled(false);
                btnTeamBConversion.setVisibility(View.INVISIBLE);

                btnTeamAConversion.setEnabled(false);
                btnTeamAConversion.setVisibility(View.INVISIBLE);
        }
    }
}

