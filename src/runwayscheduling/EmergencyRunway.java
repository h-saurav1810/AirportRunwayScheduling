/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runwayscheduling;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author LENOVO
 */
public class EmergencyRunway {
    
    private int delayedcause = 0;
    private int delayed = 0;
    private Queue<FlightDetails> arrivalAirline = new LinkedList();
    private Queue<FlightDetails> departureAirline = new LinkedList();
    private Queue<FlightDetails> runway1arrivalAirline = new LinkedList();
    private Queue<FlightDetails> runway1departureAirline = new LinkedList();
    private Stack<FlightDetails> emergencyarrivalAirline = new Stack();
    private Stack<FlightDetails> emergencydepartureAirline = new Stack();
    ClockTimer runningTime;
    private int arrival = 1; // empty
    FlightDetails arrivingflight;
    FlightDetails departingflight;
    
    public EmergencyRunway(Queue<FlightDetails> arriveQ, Queue<FlightDetails> departureQ, Queue<FlightDetails> runway1arriveQ, Queue<FlightDetails> runway1departQ, Stack<FlightDetails> emergencyArrival, Stack<FlightDetails> emergencyDeparture, ClockTimer clock)
    {
        arrivalAirline = arriveQ;
        departureAirline = departureQ;
        runway1arrivalAirline = runway1arriveQ;
        runway1departureAirline = runway1departQ;
        emergencyarrivalAirline = emergencyArrival;
        emergencydepartureAirline = emergencyDeparture;
        runningTime = clock;
    }
    
    
    public void arrive (String flightId) throws InterruptedException
    {
        // arrivingflight.setExpectedArrival();
        System.out.println("\t\t\t\t\t\t\t\t\t\t /////////////////////////////////////////////////////////////////////////////////////////////");
        if (delayed == 1)
        {
            System.out.println("\t\t\t\t\t\t\t\t\t\t DELAYED ARRIVAL.");
            System.out.println("\t\t\t\t\t\t\t\t\t\t Airline " + flightId + " is landing now.");
        }
        for(int time = 1; time <= arrivingflight.getArrivalTime(); time++)
        {
            try{
                /*
                if (arrivingflight.getArrivalRequestTime() == runningTime.total)
                {
                    System.out.println("\t Airline " + arrivingflight.getId() +  " is Requesting a Landing on Runway 1");
                    System.out.println("Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds + "\t Arriving in 60 minutes.");
                    System.out.println("Expected Time of Arrival :  " + arrivingflight.getExpectedArrival()/3600 + ":" + (arrivingflight.getExpectedArrival()%3600)/60  + ":" + arrivingflight.getExpectedArrival()%60);
                }
                */
                if (arrivingflight.getLandingTime() == runningTime.total)
                {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t Airline " + flightId + " is landing now.");
                }
                
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }                      
        }
        if (delayed == 1)
        {
            arrivingflight.setArrivalDelay(runningTime.total, delayedcause);
            //delayedcause = 0;
        }
        else
        {
            arrivingflight.setArrivalDelay(runningTime.total);
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t " + flightId + " has landed. \t\t" + "Arrived at : " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds + "\t\t Time Delay : " + arrivingflight.getArrivalDelay());
        System.out.println("\t\t\t\t\t\t\t\t\t\t Arrived at Gate "  + arrivingflight.getGate());
        System.out.println("\t\t\t\t\t\t\t\t\t\t /////////////////////////////////////////////////////////////////////////////////////////////\n");
        arrival--;
        delayed = 0;
    }
    
    public void depart(String flightId) throws InterruptedException
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t *********************************************************************************************");
        if (delayed == 1)
        {
            System.out.println("\t\t\t\t\t\t\t\t\t\t DELAYED DEPARTURE.");
            System.out.println("\t\t\t\t\t\t\t\t\t\t Airline " + flightId + " is taking off from the Runway now.");
        }
        for(int time = 1; time <= departingflight.getDepartingTime(); time++)
        {
            try{
                /*
                if (departingflight.getDepartRequestTime() == runningTime.total)
                {
                    // System.out.println("\t Airline " + departingflight.getId() +  " is Requesting a Departure from Runway 1");
                    // System.out.println("Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds + "\t Departing in 30 minutes.");
                    System.out.println("Expected Time of Departure :  " + departingflight.getExpectedDeparture()/3600 + ":" + (departingflight.getExpectedDeparture()%3600)/60  + ":" + departingflight.getExpectedDeparture()%60);
                }
                */
                if (departingflight.getTakeoffTime() == runningTime.total)
                {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t Airline " + flightId + " is taking off from the Runway now.");
                }
                
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }                      
        }
        if (delayed == 1)
        {
            departingflight.setDepartureDelay(runningTime.total, delayedcause);
            // delayedcause = 0;
        }
        else
        {
            departingflight.setDepartureDelay(runningTime.total);
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t " + flightId + " has Departed. \t\t" + "Departed at : " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds + "\t\t Time Delay : " + departingflight.getDepartureDelay());
        System.out.println("\t\t\t\t\t\t\t\t\t\t*********************************************************************************************\n");
        arrival++;
        delayed = 0;
    }
    
    public synchronized void runwayArrive() throws InterruptedException
    {
        while (arrival == 0)
        {
            if (emergencyarrivalAirline.isEmpty())
            {
                arrivingflight = arrivalAirline.peek();
            }
            else
            {
                arrivingflight = emergencyarrivalAirline.peek();
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t " + arrivingflight.getairlineName() + " " + arrivingflight.getId() +  " is Requesting a Landing on Runway 42");
            System.out.println("\t\t\t\t\t\t\t\t\t\t Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            arrivingflight.setExpectedArrival(runningTime.total);
            delayedcause = arrivingflight.getExpectedArrival();
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t  -----------------------------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t  Runway is Not Clear for Landing, Airline in position for Takeoff.");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t  -----------------------------------------------------------------\n");
            wait();
            delayed = 1;
        }
        if (emergencyarrivalAirline.isEmpty())
        {
            arrivingflight = arrivalAirline.remove();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t " + arrivingflight.getairlineName() + " " + arrivingflight.getId() +  " is Requesting a Landing on Runway 42");
            System.out.println("\t\t\t\t\t\t\t\t\t\t Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            if (arrivingflight.getExpectedArrival() == 0)
            {
                arrivingflight.setExpectedArrival(runningTime.total);
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t Expected Time of Arrival :  " + arrivingflight.getExpectedArrival()/3600 + ":" + (arrivingflight.getExpectedArrival()%3600)/60  + ":" + arrivingflight.getExpectedArrival()%60);
            arrive(arrivingflight.getId());
            departureAirline.add(arrivingflight);
        }
        else
        {
            arrivingflight = emergencyarrivalAirline.pop();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t " + arrivingflight.getairlineName() + " " + arrivingflight.getId() +  " is Requesting a Landing on Runway 42");
            System.out.println("\t\t\t\t\t\t\t\t\t\t Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            if (arrivingflight.getExpectedArrival() == 0)
            {
                arrivingflight.setExpectedArrival(runningTime.total);
            }
            //arrivingflight.setExpectedArrival(runningTime.total);
            System.out.println("\t\t\t\t\t\t\t\t\t\t Expected Time of Arrival :  " + arrivingflight.getExpectedArrival()/3600 + ":" + (arrivingflight.getExpectedArrival()%3600)/60  + ":" + arrivingflight.getExpectedArrival()%60);
            arrive(arrivingflight.getId());
            runway1departureAirline.add(arrivingflight);
        }
        notify();
    }
    
    public synchronized void runwayDepart() throws InterruptedException
    {
        while (arrival == 1)
        {
            if (emergencydepartureAirline.isEmpty())
            {
                departingflight = departureAirline.peek();
            }
            else
            {
                departingflight = emergencydepartureAirline.peek();
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t " + departingflight.getairlineName() + " " + departingflight.getId() +  " is Requesting a Departure from Runway 96");
            System.out.println("\t\t\t\t\t\t\t\t\t\t Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            departingflight.setExpectedDeparture(runningTime.total);
            delayedcause = departingflight.getExpectedDeparture();
            // delayedcause = runningTime.total;
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t  --------------------------------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t  Runway is Not Clear for TakeOff, Airline is in position for Landing.");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t  --------------------------------------------------------------------\n");
            wait();
            delayed = 1;
        }
        if (emergencydepartureAirline.isEmpty())
        {
            departingflight = departureAirline.remove();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t " + departingflight.getairlineName() + " " + departingflight.getId() +  " is Requesting a Departure from Runway 96");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t Departing To : " + departingflight.getAway());
            System.out.println("\t\t\t\t\t\t\t\t\t\t Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            if (departingflight.getExpectedDeparture() == 0)
            {
                departingflight.setExpectedDeparture(runningTime.total);
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t Expected Time of Departure :  " + departingflight.getExpectedDeparture()/3600 + ":" + (departingflight.getExpectedDeparture()%3600)/60  + ":" + departingflight.getExpectedDeparture()%60);
            depart(departingflight.getId());
            arrivalAirline.add(departingflight);
        }
        else
        {
            departingflight = emergencydepartureAirline.pop();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t " + departingflight.getairlineName() + " " + departingflight.getId() +  " is Requesting a Departure from Runway 96");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t Departing To : " + departingflight.getAway());
            System.out.println("\t\t\t\t\t\t\t\t\t\t Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            if (departingflight.getExpectedDeparture() == 0)
            {
                departingflight.setExpectedDeparture(runningTime.total);
            }
            //departingflight.setExpectedDeparture(runningTime.total);
            System.out.println("\t\t\t\t\t\t\t\t\t\t Expected Time of Departure :  " + departingflight.getExpectedDeparture()/3600 + ":" + (departingflight.getExpectedDeparture()%3600)/60  + ":" + departingflight.getExpectedDeparture()%60);
            depart(departingflight.getId());
            runway1arrivalAirline.add(departingflight);
        }
        notify();
    }
    
}
