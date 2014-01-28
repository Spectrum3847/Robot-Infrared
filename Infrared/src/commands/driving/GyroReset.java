/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commands.driving;

import commands.CommandBase;

/**
 *
 * @author matthew
 */
public class GyroReset extends CommandBase {

    protected void initialize() {
    }

    protected void execute() {
        drivebase.getGyro().reset();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
