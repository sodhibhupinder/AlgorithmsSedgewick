package com.algo.problems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SherlockPairs {
	public static void main(String[] args)
	{
	    try(Scanner sc=new Scanner(System.in)){
	    int test=sc.nextInt();
	    while(test>0)
	    {
	    	BigInteger pairs=new BigInteger("0");
	        long num=sc.nextLong();
	        Map<Long,Long> arr=new HashMap<Long,Long>(1000001);
	        for(int i=0;i<num;i++)
	        {
	            long n=sc.nextLong();
	            Long value=arr.get(n);
	            if(value!=null)
	            arr.put(n, ++value);
	            else
	            arr.put(n, 1L); 	
	        }
	        for(Entry<Long, Long> entry:arr.entrySet())
	        {
	            if(entry.getValue()!=null && entry.getValue()>1L)
	            {
	            	BigInteger n = new BigInteger(Long.toString(entry.getValue()));
	            	BigInteger p = n.subtract(new BigInteger("1"));
	            	BigInteger result =  n.multiply(p);
	                pairs=pairs.add(result); 
	            }
	        }
	        System.out.println(pairs.toString());
	        test--;
	    }
	}catch (Exception e) {
		throw e;
	}
	}
}
