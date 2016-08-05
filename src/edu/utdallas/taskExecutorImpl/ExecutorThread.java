package edu.utdallas.taskExecutorImpl;


import edu.utdallas.Queue.BlockingQueue;
import edu.utdallas.taskExecutor.Task;

public class ExecutorThread extends Thread {
	BlockingQueue blockingFIFO;

	public ExecutorThread(BlockingQueue blockingFIFO)  {
		this.blockingFIFO = blockingFIFO;
		
	}

		@Override
	public void run() {
		Task task;
		while (true) {
			
				try {
					task = blockingFIFO.dequeue();
					if(task!=null){
					task.execute();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

	}

}
