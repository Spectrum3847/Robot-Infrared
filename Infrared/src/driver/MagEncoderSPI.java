package driver;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author matthew
 */
public class MagEncoderSPI {
    SPI spi;
    
    public MagEncoderSPI(int clk, int mosi, int miso, int cs)
    {
        spi = new SPI(new DigitalOutput(cs), false);
        SPI.initBus(clk, mosi, miso);
    }
    
    public long getRaw()
    {
        return spi.transfer(0xffffff, 24)&0x3ffff;
    }
    
    public long getPosition()
    {
        return (getRaw()&0xfff);
    }
    
    public double getAngle()
    {
        return (getPosition()/4096)*360.0;
    }
    
    public void free()
    {
        SPI.freeBus();
        spi.free();
    }
}