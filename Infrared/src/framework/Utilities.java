package framework;

/*
 * @author JAG, matthew
 */
public class Utilities {

    public static double abs(double a) {
        return a < 0 ? -a : a;
    }

    public static double deadBand(double input, double dead) {
        double output = 0;
        if (abs(input) > abs(dead)) // If input is past deadband
            output = input;
            
        return output;
    }

    public static double haloDeadBand(double input, double otherInput, double smallDead, double largeDead) {

        if (deadBand(otherInput, smallDead) == 0) {  //if the other input is in the samll deadband
            return deadBand(input, smallDead);  //t
        } else {                     //other input is out of its deadband use the large deadband
            return deadBand(input, largeDead);
        }
    }

    public static double sign(double in) {
        return in / -in;
    }
}
