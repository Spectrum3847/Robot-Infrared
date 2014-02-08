package framework;

import commands.CommandBase;
import commands.collection.SippingBirdCollect;
import commands.collection.SippingBirdEject;
import commands.driving.ButteryflyDrive;
import commands.driving.CheesyDrive;
import commands.driving.DriveSelect;
import commands.driving.HoloDrive;
import commands.launching.LauncherDashboardFWD;
import commands.launching.LauncherFWD;
import commands.launching.LauncherStall;
import commands.launching.PIDLauncherDashboardFWD;
import commands.launching.SippingBirdLaunchReady;
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
    public static SippingBirdCollect sippingbirdcollect = new SippingBirdCollect();
    public static SippingBirdEject sippingbirdeject = new SippingBirdEject();
    public static SippingBirdLaunchReady launchready = new SippingBirdLaunchReady();
    public static LauncherFWD launcherfwd = new LauncherFWD();
    public static LauncherDashboardFWD launcherdashboardfwd = new LauncherDashboardFWD();
    public static PIDLauncherDashboardFWD pidlauncherdashboardfwd = new PIDLauncherDashboardFWD();
    public static LauncherStall launcherstall = new LauncherStall();
    
    public static void init() {
        CommandBase.init();

        Dashboard.intializeDashboard();
    }

    public static void periodic() {
    }
}
