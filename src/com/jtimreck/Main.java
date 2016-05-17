package com.jtimreck;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {


        BufferedReader reader = new BufferedReader(new FileReader("generate.txt"));

        ArrayList<Position> satelliteList = new ArrayList<Position>();
        Route route = null;

        String line = reader.readLine();
        while (line != null) {
            String[] tokens = line.split(",");
            if (tokens[0].startsWith("#SEED")) {
                System.out.println(line);
            } else if (tokens[0].equalsIgnoreCase("ROUTE")) {
                Position start = new Position("Start", Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), 0);
                Position end = new Position("End", Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), 0);
                route = new Route(start, end);

            } else {
                Position pos = new Position(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));

                satelliteList.add(pos);
            }

            line = reader.readLine();
        }

        RouteCalculator calculator = new RouteCalculator(satelliteList);
        System.out.println(calculator.findHops(route));

    }
}
