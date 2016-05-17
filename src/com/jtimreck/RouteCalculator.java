package com.jtimreck;

import java.util.ArrayList;

/**
 * Created by timreckj on 5/14/2016.
 */
public class RouteCalculator {
    private ArrayList<Position> satellites;
    public RouteCalculator(ArrayList<Position> satellites) {
        this.satellites = satellites;
    }

    public String findHops(Route route) {

        Position start = route.getStart();

        ArrayList<String> routeSteps = new ArrayList<String>();
        ArrayList<String> deadEnds = new ArrayList<String>();
        ArrayList<String> calculatedRoute = chaseRoute(start, route.getEnd(), routeSteps, deadEnds);

        String routeString = "";
        for (int i = 0; i < calculatedRoute.size(); i++) {
            routeString += calculatedRoute.get(i) + ",";
        }

        return routeString;
    }

    public ArrayList<String> chaseRoute(Position current, Position destination, ArrayList<String> route, ArrayList<String> deadEnds) {
        if (current.canSee(destination)) {
            route.add(current.getName());
            route.add(destination.getName());
            return route;
        }

        int i  = 0;
        Position canSee = null;
        while (canSee == null && i < satellites.size()) {
            Position satPos = satellites.get(i);
            if (!route.contains(satPos.getName()) && !deadEnds.contains(satPos.getName()) && current.canSee(satPos)) {
                canSee = satPos;
            }
            i++;
        }

        if (canSee != null) {
            if (!route.contains(current.getName())) {
                route.add(current.getName());
            }
            return chaseRoute(canSee, destination, route, deadEnds);
        } else {
            deadEnds.add(current.getName());
            return chaseRoute(findPositionByName(route.get(route.size() - 1)), destination, route, deadEnds);
        }
    }

    public Position findPositionByName(String name) {
        int i = 0;
        Position found = null;

        while (found == null && i < satellites.size()) {
            Position c = satellites.get(i);
            if (name.equals(c.getName())) {
                found = c;
            }
            i++;
        }

        return found;
    }
}
