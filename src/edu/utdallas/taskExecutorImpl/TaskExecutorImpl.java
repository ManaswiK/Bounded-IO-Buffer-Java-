package edu.utdallas.taskExecutorImpl;



import edu.utdallas.Queue.BlockingQueue;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor {
	BlockingQueue blockingFIFO = new BlockingQueue();
	
	ExecutorThread[] threadPool;
	int threadPoolCount;
	

	public TaskExecutorImpl(int threadPoolCount) {
		
		threadPool = new ExecutorThread[threadPoolCount];
		this.threadPoolCount = threadPoolCount;
		ThreadStarter();
	}


	@Override
	public void addTask(Task task) {
		try {
			blockingFIFO.enqueue(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ThreadStarter() {
		for (int i = 0; i < threadPoolCount; i++) {
			
			threadPool[i] = new ExecutorThread(blockingFIFO);
		}
		for (int i = 0; i < threadPoolCount; i++) {
			
			threadPool[i].start();
		}
	}

}
