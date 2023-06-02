/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runwayscheduling;

import java.util.Random;

/**
 *
 * @author LENOVO
 */
public class EmergencyDeparture extends Thread{
    
    private final static Random generator = new Random();
    private final EmergencyRunway emergencyRunway;
    
    public EmergencyDeparture(EmergencyRunway path)
    {
        emergencyRunway = path;
        // emergencyRunway = emergency;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                emergencyRunway.runwayDepart();
            }
            catch(InterruptedException e)
            {e.printStackTrace();}
        }
    }
    
}
