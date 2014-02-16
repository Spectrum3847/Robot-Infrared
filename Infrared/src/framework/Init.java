package framework;

import com.sun.squawk.microedition.io.FileConnection;
import commands.CommandBase;
import commands.collection.SippingBirdCatch;
import commands.collection.SippingBirdCollect;
import commands.collection.SippingBirdEject;
import commands.driving.ButteryflyDrive;
import commands.driving.CheesyDrive;
import commands.driving.DriveSelect;
import commands.driving.HoloDrive;
import commands.launching.LauncherDashboardFWD;
import commands.launching.LauncherFWD;
import commands.launching.LauncherManual;
import commands.launching.LauncherZero;
import commands.launching.LauncherDashboardFWDPID;
import commands.launching.LauncherReady;
import commands.pneumatics.RunCompressor;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 *
 * @author matthew
 */
public class Init {

    public static DriveSelect driveselect = new DriveSelect();
    public static CheesyDrive cheesydrive = new CheesyDrive();
    public static HoloDrive holodrive = new HoloDrive();
    public static ButteryflyDrive butteryflydrive = new ButteryflyDrive();
    
    public static RunCompressor runcompressor = new RunCompressor();
    
    public static SippingBirdCollect sippingbirdcollect = new SippingBirdCollect();
    public static SippingBirdEject sippingbirdeject = new SippingBirdEject();
    public static SippingBirdCatch sippingbirdcatch = new SippingBirdCatch();
    
    public static LauncherReady launcherready = new LauncherReady();
    public static LauncherManual launchermanual = new LauncherManual();
    public static LauncherDashboardFWD launcherdashboardfwd = new LauncherDashboardFWD();
    public static LauncherDashboardFWDPID launcherdashboardfwdpid = new LauncherDashboardFWDPID();
    public static LauncherZero launcherzero = new LauncherZero();
    
    public static DataOutputStream theFile;

    public static void init() {
        CommandBase.init();

        Dashboard.intializeDashboard();
        FileConnection fc;

        try {
            fc = (FileConnection) Connector.open("file:///output.txt", Connector.WRITE);
            fc.create();
            theFile = fc.openDataOutputStream();
        } catch (IOException e) {}
    }

    public static void periodic() {
    }
}
