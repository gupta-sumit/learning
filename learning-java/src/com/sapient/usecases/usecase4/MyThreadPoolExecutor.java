package com.sapient.usecases.usecase4;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyThreadPoolExecutor implements ExecutorService, Executor{

	private BlockingQueue<Runnable> jobsQueue;
	
	private Set<Worker> workerThreads;
	
	private int maxPoolSize;
	
	private int corePoolSize;
	
	
	public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> queue) {
		// TODO Auto-generated constructor stub
		this.corePoolSize = corePoolSize;
		this.maxPoolSize = maxPoolSize;
		this.jobsQueue = queue;
		workerThreads = new HashSet<MyThreadPoolExecutor.Worker>();
	}
	
	@Override
	public void execute(Runnable command) {
		synchronized (this) {
			if(workerThreads.size() <= corePoolSize) {
				// create new thread
				Worker worker = new Worker(workerThreads.size()+1,command);
				workerThreads.add(worker);
				worker.start();
			} else {
				System.out.println("Current Pool Size : " + workerThreads.size() + " Putting message in queue " + command.toString());
				boolean offered = jobsQueue.offer(command);
				if(!offered) {
					if(workerThreads.size() <= maxPoolSize ) {
						Worker worker = new Worker(workerThreads.size()+1,null);
						workerThreads.add(worker);
						worker.start();
					}
					throw new RuntimeException("Rejected " + command.toString());
				}
			}
			
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(
			Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
			long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	private class Worker extends Thread {

		private volatile Runnable task;
		
		public Worker(int index, Runnable task) {
			super("worker-thread " + index);
			this.task = task;
		}
		
		@Override
		public void run() {
			try {
				while(null != task || (task = getTask()) != null) {
					System.out.println(Thread.currentThread() + " Executing Task " + task.toString());
					task.run();
					task = null;
				}
			} catch(InterruptedException e ) {
				e.printStackTrace();
			}
			workerThreads.remove(this);
		}
		
	}
	
	private Runnable getTask() throws InterruptedException {
		return jobsQueue.take();
	}
	
	
}
