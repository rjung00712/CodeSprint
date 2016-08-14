package com.google.engedu.codesprint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.engedu.puzzle8.R;

import java.util.HashMap;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int[] drawableArray = {R.drawable.cpp, R.drawable.csuf, R.drawable.mit, R.drawable.oregon, R.drawable.slo,
            R.drawable.ucla, R.drawable.ucsc, R.drawable.ucsd, R.drawable.usc};
    private Bitmap imageBitmap = null;
    private PuzzleBoardView boardView;
    private ImageView imageView;
    private String currentPhotoPath;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button exitButton;
    private Button hint;
    private TextView status;
    private HashMap<Integer, String> schools;

    private int currentImage;
    String currentHints;
    private final int MAX_GUESSES = 3;
    private int currentGuesses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This code programmatically adds the PuzzleBoardView to the UI.
        RelativeLayout container = (RelativeLayout) findViewById(R.id.puzzle_container);
        boardView = new PuzzleBoardView(this);
        // Some setup of the view.
        boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(boardView);
        init();

        button1 = (Button) findViewById(R.id.button1);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.oregon);
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.mit);

            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.csuf);
            }
        });
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.ucsd);
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.slo);
            }
        });
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.ucsc);
            }
        });
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.usc);
            }
        });
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.ucla);
            }
        });
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMove(R.drawable.cpp);
            }
        });

        exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        hint = (Button) findViewById(R.id.button1);
        status = (TextView) findViewById(R.id.textView);
        currentGuesses = 0;
        status.setText("Guesses Left: " + (MAX_GUESSES - currentGuesses));
        initHashMap();



        currentHints = schools.get(currentImage);
        Log.d("test", currentHints);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setText(currentHints);
            }
        });


    }


    public void initHashMap() {
        schools = new HashMap<>();
        schools.put(drawableArray[0], "farm, kellogg, oj");
        schools.put(drawableArray[1], "arboretum, elephant, drama");
        schools.put(drawableArray[2], "blackjack, open courseware, Massachusetts");
        schools.put(drawableArray[3], "Donald Duck, Eugene, Oregon");
        schools.put(drawableArray[4], "rivals, sucks, copycat");

        schools.put(drawableArray[5], "Kocian, LA, bear");
        schools.put(drawableArray[6], "linguistics, JNelson, sinkholes");

        schools.put(drawableArray[7], "Little Mermaid, Sea god, Sea World");

        schools.put(drawableArray[8], "spoiled, red, overpriced");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puzzle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        int randLogoIndex = (int)(Math.random() * drawableArray.length);
//        imageView.setImageResource(drawableArray[(int)(Math.random() * drawableArray.length)]);
//        imageView.buildDrawingCache();
//        imageBitmap = imageView.getDrawingCache();
        imageBitmap = BitmapFactory.decodeResource(getResources(), drawableArray[randLogoIndex]);
        Log.d("init", ""+imageBitmap.getWidth());
        boardView.initialize(imageBitmap);
        currentImage = drawableArray[randLogoIndex];
    }

    private void gameMove(int chosenImage) {
        currentGuesses++;
        status.setText("Guesses Left: " + (MAX_GUESSES - currentGuesses));
        if(currentImage == chosenImage || currentGuesses == MAX_GUESSES) {
            showAll(chosenImage);
        }

    }

    public void showAll(int chosenImage) {
        boardView.showAll();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
                button1.setText("Hints");
                currentGuesses = 0;
                status.setText("Guesses Left: " + (MAX_GUESSES - currentGuesses));
                currentHints = schools.get(currentImage);

            }
        }, 2000);
        button1.setText("WAIT!!!!!!!!!");
        if(currentImage != chosenImage) {
            Log.d("gameMove", "fired");
            status.setText("Ran out of guesses. You Lose!");
        }
        else
            status.setText("You Win!");
    }
}
