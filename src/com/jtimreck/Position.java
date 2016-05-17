package com.jtimreck;

/**
 * Created by timreckj on 5/14/2016.
 */
public class Position {
    private static final double EARTH_RADIUS = 6371;
    private String name;
    private double latitude;
    private double longitude;
    private double altitude;

    public Position(String name, double latitude, double longitude, double altitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public String getName() {
        return name;
    }

    public boolean canSee(Position pos2) {
        if (name.equals(pos2.name)) {
            return false; // yourself is not something you can see
        }
        if (pos2.getAltitude() > this.altitude) {
            return pos2.canSee(this);
        }

        double height = altitude;

        double maxLineOfSite = Math.sqrt(Math.pow((EARTH_RADIUS + height), 2) - Math.pow(EARTH_RADIUS, 2));

        double coreRatio = maxLineOfSite/(EARTH_RADIUS + height);

        double coreAngle = Math.toDegrees(Math.asin(coreRatio));

        // if the difference in degrees between two satellites is greater than the core angle, they might not be viewable
        //we have gone from height to 0 in n degrees.  Because it is a perfect circle, it will go back up to that height.
        // Well, it will be close anyway. like laying a giant platter on the earth

        double distancePerDegree = altitude / coreAngle;
        //apply this to any degrees over core angle

        double coreAngleToPos2 = calculateCoreAngle(pos2);

        if (coreAngleToPos2 > (coreAngle * 2)) {
            return false;
        } else if (coreAngleToPos2 > coreAngle) {
            // check the altitude
            double minHeight = (coreAngleToPos2 - coreAngle) * distancePerDegree;
            if (pos2.altitude > minHeight) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public double calculateCoreAngle(Position pos2) {
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(pos2.latitude);

        double deltaLat = Math.toRadians(pos2.latitude - latitude);
        double deltaLon = Math.toRadians(pos2.longitude - longitude);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        // this should be the central angle between the two points, which is what we want
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double cInDeg = Math.toDegrees(c);

        return cInDeg;
    }
}
