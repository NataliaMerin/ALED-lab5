package es.upm.dit.aled.lab5;

import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 
 * @author rgarciacarmona
 */

public class AreaQueue extends Area {

	protected Queue<Patient> waitQueue;
	
	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		waitQueue = new LinkedList<>();
		// TODO* Auto-generated constructor stub
	}

	@Override
	public synchronized void enter(Patient p){
		try { 
			waiting++;
			waitQueue.add(p);
			while((this.numPatients>=this.capacity) || (p!= waitQueue.peek())){
				wait();
			}
			waitQueue.remove(p);
			numPatients++;
			waiting--;
		}catch(InterruptedException e) {
			System.out.println("Hay un error en la ejecución del bucle while, método enter");
		}
	}
	// TODO*
}
