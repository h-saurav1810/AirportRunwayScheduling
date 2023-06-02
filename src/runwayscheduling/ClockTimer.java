/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runwayscheduling;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ClockTimer extends Thread{

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");  
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date(); 
        int hours = 0;
        int mins = 0; 
        int seconds = 0;
        int total = 60*60*7;
        int temp = 0;
    
    public ClockTimer()
    {
        
    }
    
    @Override
    public void run()
    {
        try
            {
                // java.util.Date date = new java.util.Date();  
                while(true)
                {
                    if (total == 86400)
                    {
                        total = 0;
                    }
                    total += (5*60);
                    hours = (total / 3600);
                    mins = (total % 3600) / 60;
                    seconds = (total % 60);
                    //System.out.println(hours + ":" + mins + ":" + seconds);
                    Thread.sleep(1000);
                }
                
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
    }
    
}
