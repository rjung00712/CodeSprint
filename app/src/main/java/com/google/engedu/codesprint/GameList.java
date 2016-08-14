package com.google.engedu.codesprint;

import com.google.engedu.puzzle8.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Richard on 8/13/16.
 */
public class GameList {
    public HashMap<Integer, String> schools;
    private int[] drawableArray = {R.drawable.cpp, R.drawable.csuf, R.drawable.mit, R.drawable.oregon, R.drawable.slo,
            R.drawable.ucla, R.drawable.ucsc, R.drawable.ucsd, R.drawable.usc};

    GameList(){
        schools = new HashMap<>();
        add();
    }

    public void add() {

        schools.put(drawableArray[0], "farm kellogg oj");
        schools.put(drawableArray[1], "arboretum elephant drama");
        schools.put(drawableArray[2], "blackjack open courseware Massachusetts");
        schools.put(drawableArray[3], "Donald Duck Eugene Oregon");
        schools.put(drawableArray[4], "rivals sucks copycat");

        schools.put(drawableArray[5], "Kocian LA bear");
        schools.put(drawableArray[6], "linguistics JNelson sinkholes");

        schools.put(drawableArray[7], "Little Mermaid Sea god Sea World");

        schools.put(drawableArray[8], "spoiled red overpriced");






    }

    public HashMap<Integer, String> getHints() {
        return schools;
    }

//    public void load
}


//    private ArrayList<String> words;
//
//    public SimpleDictionary(InputStream wordListStream) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
//        words = new ArrayList<>();
//        String line = null;
//        while((line = in.readLine()) != null) {
//            String word = line.trim();
//            if (word.length() >= MIN_WORD_LENGTH)
//                words.add(line.trim());
//        }
//    }


