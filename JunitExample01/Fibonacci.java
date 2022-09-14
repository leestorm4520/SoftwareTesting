package edu.utsa.example.junit;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	private List<Integer> fib_in;
	public Fibonacci(){
		this.fib_in = new ArrayList<Integer>();
	}
	public void extend(int length){
		if(length > this.fib_in.size()){
			if(length == 1){
				this.fib_in.add(0);
			}else if(length == 2 && this.fib_in.size() == 0){
				this.fib_in.add(0);this.fib_in.add(1);
			}else if(length == 2 && this.fib_in.size() == 1){
				this.fib_in.add(1);
			}else{
				if(this.fib_in.size() == 0){
					this.fib_in.add(0); this.fib_in.add(1);
				}else if(this.fib_in.size() == 1){
					this.fib_in.add(1);
				}
				int original = this.fib_in.size();
				for(int i = original; i<length; i++){
					this.fib_in.add(this.fib_in.get(i-2) + this.fib_in.get(i-1));
				}
			}			
		}
	}
	public int get(int index){
		return this.fib_in.get(index); //bug
	}
	public int getLength(){
		return this.fib_in.size();
	}
	public int[] getRange(int start, int end){
		int[] results = new int[end-start];
		for(int i = start; i<end; i++){
			results[i - start] = this.fib_in.get(i); 
		}
		return results;
	}
}
