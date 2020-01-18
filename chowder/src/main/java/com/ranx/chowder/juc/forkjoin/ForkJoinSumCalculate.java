package com.ranx.chowder.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Description: Fork/Join
 * @author ranx
 * 2019-8-23上午11:14:07
 */
public class ForkJoinSumCalculate extends RecursiveTask<Long>{

	private static final long serialVerssionUID = 1L;
	
	private long start;
	private long end;
	
	//拆分临界值
	private static final long THURSHOLD = 10000L;
	
	
	public ForkJoinSumCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}


	@Override
	protected Long compute() {
		long length = end - start;
		if (length <= THURSHOLD) {
			long sum = 0L;
			for (long i=start; i<=end; i++) {
				sum += i;
			}
			System.out.println(sum);
			return sum;
		} else {
			long middle = (start + end) / 2;
			ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
			left.fork(); //进行拆分，同时压入线程队列
			
			ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
			right.fork();
			
			return left.join() + right.join();
		}
	}

}
