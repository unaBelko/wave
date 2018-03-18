package com.example.armin.wave;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Una on 29.10.2017..
 */

public class StringsPositions {
    private static List<String> positions;
    private static StringsPositions pos;
    private StringsPositions(){
        positions = new ArrayList<>();
        positions.add("Defender");
        positions.add("Midfielder");
        positions.add("Striker");
        positions.add("Winger");
    }
    public static String getPosition(int id){
        pos = new StringsPositions();
        return positions.get(id-1);
    }
}
