package framework;

import commands.CommandBase;
import commands.driving.ButteryflyDrive;
import commands.driving.CheesyDrive;
import commands.driving.DriveSelect;
import commands.driving.GyroReset;
import commands.driving.HoloDrive;
import commands.pneumatics.RunCompressor;

/**
 *
 * @author matthew
 */
public class Init {

    public static CheesyDrive cheesydrive = new CheesyDrive();
    public static HoloDrive holodrive = new HoloDrive();
    public static ButteryflyDrive butteryflydrive = new ButteryflyDrive();
    public static DriveSelect driveselect = new DriveSelect();
    public static RunCompressor runcompressor = new RunCompressor();
    public static GyroReset gryoreset = new GyroReset();

    public static void init() {
        CommandBase.init();
        
        Dashboard.intializeDashboard();
    }

    public static void periodic() {
    }
}
