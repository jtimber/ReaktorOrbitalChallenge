package com.jtimreck;

import javafx.geometry.Pos;

/**
 * Created by timreckj on 5/14/2016.
 */
public class Route {
    private Position start;
    private Position end;

    public Route(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
