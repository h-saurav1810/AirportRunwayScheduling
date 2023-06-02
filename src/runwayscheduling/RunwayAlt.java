/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runwayscheduling;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Date; 
import java.util.Stack;

public class RunwayAlt {
    
    private int delayedcause = 0;
    private int delayed = 0;
    private int emergency = 0;
    private Queue<FlightDetails> arrivalAirline = new LinkedList();
    private Queue<FlightDetails> departureAirline = new LinkedList();
    private Stack<FlightDetails> runway2arrivalAirline = new Stack();
    private Stack<FlightDetails> runway2departureAirline = new Stack();
    ClockTimer runningTime;
    private int arrival = 1; // empty
    FlightDetails arrivingflight;
    FlightDetails departingflight;
    // FlightDetails emergencyArrivalflight;
    // FlightDetails emergencyDepartingflight;
    
    public RunwayAlt(Queue<FlightDetails> arriveQ, Queue<FlightDetails> departureQ, Stack<FlightDetails> emergencyArrival, Stack<FlightDetails> emergencyDeparture, ClockTimer clock)
    {
        arrivalAirline = arriveQ;
        departureAirline = departureQ;
        runway2arrivalAirline = emergencyArrival;
        runway2departureAirline = emergencyDeparture;
        runningTime = clock;
    }
    
    
    public void arrive (String flightId) throws InterruptedException
    {
        // arrivingflight.setExpectedArrival();
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////");
        if (delayed == 1)
        {
            System.out.println("DELAYED ARRIVAL.");
            System.out.println("Airline " + flightId + " is landing now.");
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
                    System.out.println("Airline " + flightId + " is landing now.");
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
        System.out.println(flightId + " has landed. \t\t" + "Arrived at : " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds + "\t\t Time Delay : " + arrivingflight.getArrivalDelay());
        System.out.println("Arrived at Gate "  + arrivingflight.getGate());
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////\n");
        arrival--;
        delayed = 0;
    }
    
    public void depart(String flightId) throws InterruptedException
    {
        System.out.println("*********************************************************************************************");
        if (delayed == 1)
        {
            System.out.println("DELAYED DEPARTURE.");
            System.out.println("Airline " + flightId + " is taking off from the Runway now.");
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
                    System.out.println("Airline " + flightId + " is taking off from the Runway now.");
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
        System.out.println(flightId + " has Departed. \t\t" + "Departed at : " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds + "\t\t Time Delay : " + departingflight.getDepartureDelay());
        System.out.println("*********************************************************************************************\n");
        arrival++;
        delayed = 0;
    }
    
    public synchronized void runwayArrive() throws InterruptedException
    {
        while (arrival == 0)
        {
            arrivingflight = arrivalAirline.peek();
            System.out.println("\t" + arrivingflight.getairlineName() + " Airline " + arrivingflight.getId() +  " is Requesting a Landing on Runway 18");
            System.out.println("Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            arrivingflight.setExpectedArrival(runningTime.total);
            delayedcause = arrivingflight.getExpectedArrival();
            System.out.println("\n\t -----------------------------------------------------------------");
            System.out.println("\t Runway is Not Clear for Landing, Airline in position for Takeoff.");
            System.out.println("\t -----------------------------------------------------------------\n");
            runningTime.suspend();
            System.out.println("\n Allow Arrival at Emergency Runway?");
            emergency = (int) (0 + Math.random()*((1 - 0) + 1));
            if (emergency == 0)
            {
                // System.out.println(emergency);
                System.out.println("Airline is Not Clear for Landing on the Emergency Runway. Airline will land on Runway 18 as soon as it is cleared.\n");
            }
            else
            {
                // System.out.println(emergency);
                System.out.println("Airline is Clear for Landing on the Emergency Runway. Airline can direct itself to Emergency Runway for landing.\n");
                runway2arrivalAirline.push(arrivalAirline.remove()); 
            }
            runningTime.resume();
            emergency = 0;
            wait();
            delayed = 1;
        }
        arrivingflight = arrivalAirline.remove();
        System.out.println("\t" + arrivingflight.getairlineName() + " Airline " + arrivingflight.getId() +  " is Requesting a Landing on Runway 18");
        System.out.println("Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
        if (arrivingflight.getExpectedArrival() == 0)
        {
             arrivingflight.setExpectedArrival(runningTime.total);
        }
        //arrivingflight.setExpectedArrival(runningTime.total);
        System.out.println("Expected Time of Arrival :  " + arrivingflight.getExpectedArrival()/3600 + ":" + (arrivingflight.getExpectedArrival()%3600)/60  + ":" + arrivingflight.getExpectedArrival()%60);
        arrive(arrivingflight.getId());
        departureAirline.add(arrivingflight);
        notify();
    }
    
    public synchronized void runwayDepart() throws InterruptedException
    {
        while (arrival == 1)
        {
            departingflight = departureAirline.peek();
            System.out.println("\t" + departingflight.getairlineName() + " Airline " + departingflight.getId() +  " is Requesting a Departure from Runway 36");
            System.out.println("Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
            departingflight.setExpectedDeparture(runningTime.total);
            delayedcause = departingflight.getExpectedDeparture();
            // delayedcause = runningTime.total;
            System.out.println("\n\t --------------------------------------------------------------------");
            System.out.println("\t Runway is Not Clear for TakeOff, Airline is in position for Landing.");
            System.out.println("\t --------------------------------------------------------------------\n");
            runningTime.suspend();
            System.out.println("\n Allow Departure at Emergency Runway?");
            emergency = (int) (0 + Math.random()*((1 - 0) + 1));
            if (emergency == 0)
            {
                // System.out.println(emergency);
                System.out.println("Airline is Not Clear for Takeoff on the Emergency Runway. Airline will takeoff on Runway 18 as soon as it is cleared.\n");
            }
            else
            {
                // System.out.println(emergency);
                System.out.println("Airline is Clear for Takeoff on the Emergency Runway. Airline can move to the Emergency Runway for Takeoff.\n");
                runway2departureAirline.push(departureAirline.remove()); 
            }
            runningTime.resume();
            emergency = 0;
            wait();
            delayed = 1;
        }
        departingflight = departureAirline.remove();
        System.out.println("\t" + departingflight.getairlineName() + " Airline " + departingflight.getId() +  " is Requesting a Departure from Runway 36");
        System.out.println("\tDeparting To : " + departingflight.getAway());
        System.out.println("Request sent at " + runningTime.hours + ":" + runningTime.mins + ":" + runningTime.seconds);
        if (departingflight.getExpectedDeparture() == 0)
        {
            departingflight.setExpectedDeparture(runningTime.total);
        }
        //departingflight.setExpectedDeparture(runningTime.total);
        System.out.println("Expected Time of Departure :  " + departingflight.getExpectedDeparture()/3600 + ":" + (departingflight.getExpectedDeparture()%3600)/60  + ":" + departingflight.getExpectedDeparture()%60);
        depart(departingflight.getId());
        arrivalAirline.add(departingflight);
        notify();
    }

}
