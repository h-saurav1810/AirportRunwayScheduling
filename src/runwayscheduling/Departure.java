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
public class Departure extends Thread{
    
    private final static Random generator = new Random();
    private final RunwayAlt flightRunway;
    // private final EmergencyRunway emergencyRunway;
    
    public Departure(RunwayAlt path)
    {
        flightRunway = path;
        // emergencyRunway = emergency;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                flightRunway.runwayDepart();
                // emergencyRunway.runwayDepart();
            }
            catch(InterruptedException e)
            {e.printStackTrace();}
        }
    }
    
}
