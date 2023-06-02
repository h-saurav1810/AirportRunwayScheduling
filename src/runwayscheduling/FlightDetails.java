/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runwayscheduling;

import java.util.Random;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.Stack;

public class FlightDetails {
    
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");  
    SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date(); 
    
    // private static Stack<Integer> timestack = new Stack<Integer>();
    
    private String flightId;
    private String airlineName;
    private String gate; 
    private String away; 
    private int arrivalTime = 0;
    private int departingTime = 0;
    private int landingTime = 0;
    private int takeoffTime = 0;
    private int arrivalRequestTime = 0;
    private int departRequestTime = 0;
    private int expectedArrival;
    private int expectedDeparture;
    private int arrivalDelay = 0;
    private int departureDelay = 0;
    
    public FlightDetails(String id, String airline, String gateway, String awayairport)
    {
        this.flightId = id;
        this.airlineName = airline;
        this.gate = gateway;
        this.away = awayairport;
        arrivalTime = (int) (15 + Math.random()*((20 - 15) + 1));   
        departingTime = (int) (9 + Math.random()*((12 - 9) + 1));
    }
    
    public String getId()
    {
        return flightId;
    }
    
    public String getairlineName()
    {
        return airlineName;
    }
    
    public String getGate()
    {
        return gate;
    }
    
    public String getAway()
    {
        return away;
    }
    
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    
    public int getArrivalRequestTime()
    {
        return arrivalRequestTime;
    }
    
    public int getExpectedArrival()
    {
        return expectedArrival;
    }
    
    public int getDepartingTime()
    {
        return departingTime;
    }
    public int getDepartRequestTime()
    {
        return departRequestTime;
    }
    
    public int getExpectedDeparture()
    {
        return expectedDeparture;
    }
    
    public String getArrivalDelay()
    {
        if (arrivalDelay < 0)
        {
            arrivalDelay = 0;
            return "0:0:0";
        }
        return (arrivalDelay / 3600) + ":" + ((arrivalDelay % 3600) / 60)  + ":" + (arrivalDelay % 60) ;
    }
    
    public String getDepartureDelay()
    {
        if (departureDelay < 0)
        {
            departureDelay = 0;
            return "0:0:0";
        }
        return (departureDelay / 3600) + ":" + ((departureDelay % 3600) / 60)  + ":" + (departureDelay % 60) ;
    }
    
    public int getLandingTime()
    {
        return landingTime;
    }
    
    public int getTakeoffTime()
    {
        return takeoffTime;
    }
    
    public void setLandingTime()
    {
        landingTime = expectedArrival - 5 * 60;
    }
    
    public void setTakeoffTime()
    {
        takeoffTime = expectedDeparture - 5 * 60;
    }
    
    public void setExpectedArrival(int time)
    {
        expectedArrival = time + arrivalTime * 5 * 60;
        setArrivalRequestTime();
        setLandingTime();
    }
    
    public void setArrivalRequestTime()
    {
        arrivalRequestTime = expectedArrival - 60 * 60;
    }
    
    public void setArrivalDelay(int time)
    {
        arrivalDelay = (time - expectedArrival); 
    }
    
    public void setArrivalDelay(int time, int expected)
    {
        arrivalDelay = (time - expected); 
    }
    
    public void setExpectedDeparture(int time)
    {
        expectedDeparture = time + departingTime * 5 * 60;
        setDepartRequestTime();
        setTakeoffTime();
    }
    
    public void setDepartRequestTime()
    {
        departRequestTime = expectedDeparture - 30 * 60;
    }
    
    public void setDepartureDelay(int time)
    {
        departureDelay = (time - expectedDeparture); 
    }
    
    public void setDepartureDelay(int time, int expected)
    {
        departureDelay = (time - expected); 
    }
    /*
    public int recordInstantTime()
    {
        int hours = 0;
        int mins = 0; 
        int seconds = 0;
        int total = 0;
        java.util.Date date = new java.util.Date();  
        hours = date.getHours()*60*60;
        mins = date.getMinutes()*60;
        seconds = date.getSeconds();
        total = (hours + mins + seconds); 
        return total;
    }
    */
}
