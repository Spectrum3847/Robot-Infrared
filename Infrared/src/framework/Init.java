package framework;

import commands.CommandBase;
import commands.blocking.LauncherBlock;
import commands.collection.SippingBirdAutoCatch;
import commands.collection.SippingBirdCollect;
import commands.collection.SippingBirdEject;
import commands.collection.SippingBirdKiss;
import commands.driving.ButteryflyDrive;
import commands.driving.CheesyDrive;
import commands.driving.DriveSelect;
import commands.driving.HoloDrive;
import commands.launching.LauncherLaunch;
import commands.launching.LauncherCatch;
import commands.launching.LauncherLaunchForward;
import commands.launching.LauncherLaunchLine;
import commands.launching.LauncherToss;
import commands.light.FlashLightOn;
import commands.pneumatics.RunCompressor;

/**
 *
 * @author matthew
 */
public class Init {

    public static DriveSelect driveselect = new DriveSelect();
    public static CheesyDrive cheesydrive = new CheesyDrive();
    public static HoloDrive holodrive = new HoloDrive();
    public static ButteryflyDrive butteryflydrive = new ButteryflyDrive();
    
    public static FlashLightOn flashlighton = new FlashLightOn();
    
    public static RunCompressor runcompressor = new RunCompressor();
    
    public static SippingBirdCollect sippingbirdcollect = new SippingBirdCollect();
    public static SippingBirdEject sippingbirdeject = new SippingBirdEject();
    public static SippingBirdAutoCatch sippingbirdcatch = new SippingBirdAutoCatch();
    public static SippingBirdKiss sippingbirdkiss = new SippingBirdKiss();
    
    public static LauncherLaunch lauch = new LauncherLaunch(Dashboard.LAUNCHER_PULSE_A);
    public static LauncherLaunch lauch_alt = new LauncherLaunch(Dashboard.LAUNCHER_PULSE_B);
    public static LauncherLaunchLine lauch_line = new LauncherLaunchLine();
    public static LauncherCatch launchercatch = new LauncherCatch();
    public static LauncherBlock laucherblock = new LauncherBlock();
    public static LauncherToss lauchertoss = new LauncherToss();
    public static LauncherLaunchForward lauch_forward = new LauncherLaunchForward(Dashboard.LAUNCHER_PULSE_A);

    public static void init() {
        CommandBase.init();

        Dashboard.intializeDashboard();
    }

    public static void periodic() {
    }
}
