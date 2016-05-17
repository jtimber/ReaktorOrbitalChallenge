# Reaktor Orbital Challenge

This is my very unpolished solution for the Reaktor Orbital Challenge.  It includes one of the ugliest recursive methods I have ever written, and
it does not implement a shortest path algorithm.  Instead, it just tries to find **a** path.

## Basis
The logic behind the solution finds a core angle where a line drawn from the satellite would make a right angle with the surface of the earth.
A satellite can "see" everything for every core angle less than this, but only things above a certain altitude for angles larger than this.

Next, I calculate the core angle between two coordinates using the haversine formula and compare the two angles in order to determine
if this is a viable communication path.
