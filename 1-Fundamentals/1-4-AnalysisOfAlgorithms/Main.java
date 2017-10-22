import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.in;

class Prime {
	  void checkPrime(int... args){
	        String outPut = "";
	        for (int arg : args){
	            if (isPrime(arg)){
	                outPut += arg+" "; 
	            }
	        }
	        if (outPut != ""){
	            outPut = outPut.substring(0,outPut.length()-1);
	            System.out.print(outPut);
	        }
	        System.out.println();
	    }
	    
	    boolean isPrime(int num){
	        if (num < 2){
	            return false;
	        }
	        for (int i = 2; i < (num/2)+1; i++){
	            if (num%i==0){
	                return false;
	            }
	        }
	        return true;
	    }
    
}
public class Main {

	public static void main(String[] args) {
		try{
		InputStreamReader input = new InputStreamReader(in);
		BufferedReader br=new BufferedReader(input);
		int n1=Integer.parseInt(br.readLine());
		int n2=Integer.parseInt(br.readLine());
		int n3=Integer.parseInt(br.readLine());
		int n4=Integer.parseInt(br.readLine());
		int n5=Integer.parseInt(br.readLine());
		Prime ob=new Prime();
		ob.checkPrime(n1);
		ob.checkPrime(n1,n2);
		ob.checkPrime(n1,n2,n3);
		ob.checkPrime(n1,n2,n3,n4,n5);	
		Method[] methods=Prime.class.getDeclaredMethods();
		Set<String> set=new HashSet<>();
		boolean overload=false;
		for(int i=0;i<methods.length;i++)
		{
			if(set.contains(methods[i].getName()))
			{
				overload=true;
				break;
			}
			set.add(methods[i].getName());
			
		}
		if(overload)
		{
			throw new Exception("Overloading not allowed");
		}
			System.out.println("Testing");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}

