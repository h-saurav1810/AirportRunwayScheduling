/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runwayscheduling;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author LENOVO
 */
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.Stack;

public class RunwayScheduling {
    
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");  
    SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date(); 
       
    public static void main(String[] args) {
        // TODO code application logic here
       
       ClockTimer clock = new ClockTimer();
       clock.start();
       
       FlightDetails plane1 = new FlightDetails("EK876", "Emirates", "A3", "Bahrain");
       FlightDetails plane2 = new FlightDetails("EK579", "Emirates", "A2", "Dubai");
       FlightDetails plane3 = new FlightDetails("SA468", "Saudi Airlines", "A1", "Jeddah");
       FlightDetails plane4 = new FlightDetails("QA269", "Qatar Airways", "B7", "Qatar");
       FlightDetails plane5 = new FlightDetails("AA585", "Air Asia", "B4", "Jakarta");
       FlightDetails plane6 = new FlightDetails("BA327", "British Airways", "B4", "London");
       FlightDetails plane7 = new FlightDetails("QN114", "Qantas Airways", "B5", "Melbourne");
       FlightDetails plane8 = new FlightDetails("PJ001", "Private Jet", "C1", "Jakarta");
       FlightDetails plane9 = new FlightDetails("MC199", "Military Craft", "C2", "Wellington");
       FlightDetails plane10 = new FlightDetails("CG237", "Cargo Airline", "C2", "Bahrain");
       FlightDetails plane11 = new FlightDetails("PJ173", "Private Jet", "C1", "Singapore");
       
       
       Queue<FlightDetails> arrivalQueue = new LinkedList();
       Queue<FlightDetails> departureQueue = new LinkedList();
       Queue<FlightDetails> arrivalQueue2 = new LinkedList();
       Queue<FlightDetails> departureQueue2 = new LinkedList();
       Stack<FlightDetails> emergencyArrival = new Stack();
       Stack<FlightDetails> emergencyDeparture = new Stack();
       
       arrivalQueue.add(plane2);
       arrivalQueue.add(plane4);
       arrivalQueue.add(plane5);
       arrivalQueue.add(plane7);
       departureQueue.add(plane1);
       departureQueue.add(plane3);
       departureQueue.add(plane6);
       
       arrivalQueue2.add(plane8);
       arrivalQueue2.add(plane10);
       departureQueue2.add(plane9);
       departureQueue2.add(plane11);
       
       RunwayAlt runway1 = new RunwayAlt(arrivalQueue, departureQueue, emergencyArrival, emergencyDeparture, clock);
       EmergencyRunway runway2 = new EmergencyRunway(arrivalQueue2, departureQueue2, arrivalQueue, departureQueue, emergencyArrival, emergencyDeparture, clock);
       Arrival arrThread = new Arrival(runway1);
       Departure departThread = new Departure(runway1);
       EmergencyArrival emergencyarrThread = new EmergencyArrival(runway2);
       EmergencyDeparture emergencydepartThread = new EmergencyDeparture(runway2);
       arrThread.start();
       departThread.start();
       emergencyarrThread.start();
       emergencydepartThread.start();
       
    }
    
}
