package framework;

import com.sun.squawk.microedition.io.FileConnection;
import commands.CommandBase;
import commands.blocking.LauncherBlock;
import commands.collection.SippingBirdAutoCatch;
import commands.collection.SippingBirdCollect;
import commands.collection.SippingBirdEject;
import commands.driving.ButteryflyDrive;
import commands.driving.CheesyDrive;
import commands.driving.DriveSelect;
import commands.driving.HoloDrive;
import commands.launching.LauncherReady;
//import commands.launching.LauncherManual;
//import commands.launching.LauncherZero;
//import commands.launching.LauncherDashboardFWDPID;
//import commands.launching.LauncherReady;
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
    public static SippingBirdAutoCatch sippingbirdcatch = new SippingBirdAutoCatch();
    
    //public static LauncherZero launcherzero = new LauncherZero();
    public static LauncherReady launcherready = new LauncherReady();
    //public static LauncherManual launchermanual = new LauncherManual();
    public static LauncherBlock laucherblock = new LauncherBlock();
    
    public static DataOutputStream theFile;

    public static void init() {
        CommandBase.init();

        Dashboard.intializeDashboard();
        FileConnection fc;

        try {
            //fc = (FileConnection) Connector.open("file:///output_" + Timer.getFPGATimestamp() + "_.txt", Connector.WRITE);
            fc = (FileConnection) Connector.open("file:///output.txt", Connector.WRITE);
            fc.create();
            theFile = fc.openDataOutputStream();
        } catch (IOException e) {}
    }

    public static void periodic() {
    }
}
