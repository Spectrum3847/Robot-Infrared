package driver;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.visa.VisaException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author matthew no it's me trey boyer
 */
public class IMU {
    private final SerialPort ser;
    private float offset = 0;
    private float yaw = 0;
    private float angle = 0;
    private IMUThread task;
    private boolean alive = false;
    private Thread thread;

    public class IMUThread implements Runnable {
        IMU imu;
        
        public IMUThread(IMU i)
        {
            imu = i;
        }
        public void run()
        {
            while(alive)
            {
                imu.update();
                System.out.println("Angle: " + imu.angle);
                Thread.yield();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println("Error Thread: " + ex.getMessage());
                }
            }
        }
    }
    
    public IMU(int baudrate) throws VisaException
    {
        ser = new SerialPort(baudrate);
        ser.reset();
        ser.enableTermination('\n');
        ser.setWriteBufferMode(SerialPort.WriteBufferMode.kFlushOnAccess);
        ser.readFlush();
    }
    
    public IMU() throws VisaException
    {
        this(115200);
    }
    
    public float init()
    {
        try {
            ser.reset();
            ser.enableTermination('\n');
            ser.flush();
            ser.readFlush();
        } catch (VisaException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        this.zero();
        alive = true;
        task = new IMUThread(this);
        thread = new Thread(task);
        thread.start();
        return offset;
    }
    
    public String getData()
    {
        String in = "0.0\n";
        try {
            in = ser.readString(10);
            ser.readFlush();
        } catch (VisaException ex) {
            try {
                ser.reset();
            } catch (VisaException ex1) {}
            System.out.println(in + " Error: " + ex.getMessage());
            System.out.print("Trey was here huehuehuehuelel");
        }
        return in.trim();
    }
    
    public float zero()
    {
        String first_in;
        first_in = this.getData();
        try {
            offset = Float.parseFloat(first_in);
        } catch(NumberFormatException ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return offset;
    }
    
    public float getYaw()
    {
        String in;
        in = getData();
        try {
            yaw = Float.parseFloat(in);
        } catch(NumberFormatException ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return yaw + offset;
    }
    
    public float getAngle()
    {
        return angle;
    }
    
    public void update()
    {
        getYaw();
        angle = yaw + offset + 180.0f;
    }
    public void end()
    {
        alive = false;
        try {
        ser.flush(); }catch(VisaException ex){}
        angle = 0.0f;
        yaw = 0.0f;
        offset = 0.0f;
    }
}