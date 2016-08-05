package edu.utdallas.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import edu.utdallas.taskExecutor.Task;

public class BlockingQueue {
	private List<Task> blockingFIFO;
	
	//The limit is used for the BlockingQueue size
	private int limit = 100;
	
	//Semaphores for enqueue and dequeue
	private Semaphore sem = new Semaphore(1);
	private Semaphore sem2 = new Semaphore(1);

	public BlockingQueue() {
		blockingFIFO = new LinkedList<Task>();
	}

      
	
	//Enqueue operation
	public void enqueue(Task task) throws InterruptedException {
		sem.acquire();
		while(blockingFIFO.size() == limit){
			sem.release();	
			
		}
		
		
		blockingFIFO.add(task);
		sem.release();
		return;
 	}
	

    //Dequeue Operation
	public Task dequeue() throws InterruptedException {
		sem2.acquire();
		Task task = null;
		while(blockingFIFO.isEmpty()) {
			sem2.release();
			return task;
			}
			task = blockingFIFO.remove(0);
		    sem2.release();
			return task;
		}
	}

