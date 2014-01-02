package driver;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author matthew
 */
public class MagEncoder extends Encoder {
    private DigitalInput pwm;
    private Timer count = new Timer();
    private double pulsewidth = 0;
    private boolean enabled = false;
    
    private class MagEncoderChecker extends Thread {
        double before = 0, after = 0, buf = 0, tmp = 0;
        final double samples = 32;
        
        public synchronized void run()
        {
            while(enabled)
            {
                buf = 0;
                for(int i = 0; i < samples; i++)
                {
                    while(!pwm.get());
                    before = Timer.getUsClock();
                    while(pwm.get());
                    tmp = Timer.getUsClock() - before;
                    buf += tmp<4098?tmp:900;
                }
                pulsewidth = buf/samples;
            }
        }
    }
    
    private MagEncoderChecker checker = new MagEncoderChecker();
    
    public MagEncoder(DigitalSource a, DigitalSource b, DigitalInput c)
    {
        super(a, b);
        pwm = c;
    }
    
    public MagEncoder(int a, int b, int c)
    {
        super(a, b);
        pwm = new DigitalInput(c);
    }
    
    public void start()
    {
        enabled = true;
        checker.start();
    }
    
    public void stop()
    {
        enabled = false;
        count.stop();
    }
    
    public double getPulseWidth()
    {
        return pulsewidth;
    }
    
    public double getAngle()
    {
        return (pulsewidth*360)/(3100-235)-1;
    }
}