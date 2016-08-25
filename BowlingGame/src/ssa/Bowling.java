package ssa;

import java.util.Random;
import java.util.Date;

public class Bowling {

	

	private int bowlingScores[][] = new int[3][10];
	static int seed;
	static Random rnd = new Random();
	boolean spare[] = new boolean[10];
	boolean strike[] = new boolean[10];
//	boolean currSpare[] = new boolean[10];
//	boolean currSpare[] = new boolean[10];
	
	  
	
	int getPinsKnockedDown() {
		  int randomInt = rnd.nextInt(11); // zero to ten
		  return randomInt;
		}
	
	void initFlags(){
		for (int idx2 = 0;idx2 < 10 ; idx2++){
			spare[idx2] = false;
			strike[idx2] = false;
		}
	}
	
	void bowl(){
		initFlags();
		
		for (int idx = 0; idx < 3; idx++){		
			for (int idx2 = 0;idx2 < 10 ; idx2++){
				bowlingScores[idx][idx2] = firstRoll(idx2);
			}
		}
		
		displayScores();
		
	}
	
	int firstRoll(int idx){
		int firstPin = getPinsKnockedDown();
		int secondPin =0,totalOfTwo=0;
		if (firstPin < 10){
			secondPin =	secondRoll(firstPin);
		}
		else{
			strike[idx] = true;
		}
		totalOfTwo = firstPin + secondPin;
		if (totalOfTwo == 10 ){
			spare[idx] = true; 
		}
		return totalOfTwo;
	}
	
	int secondRoll(int firstPin){
		int secondPin = getPinsKnockedDown();
		if ( secondPin + firstPin >= 10 ){
			secondPin = 10 - firstPin;
		}
		return secondPin;
	}
	
	void displayScores(){
		int total =0, totalSeries =0;
			
		System.out.printf(" Frames   1     2    3    4    5    6    7    8    9    10     Total");
		System.out.println(" ");
			for (int idx = 0; idx < 3;idx++){		
				 System.out.printf(" Game %d",idx+1);
				for (int idx2 = 0;idx2 < 10 ; idx2++){
					System.out.printf("  %3d",bowlingScores[idx][idx2]);
					total += bowlingScores[idx][idx2] ;
				}
				System.out.printf("  %7d",total);
				totalSeries += total;
				total = 0;
				System.out.println(" ");
			}
		System.out.printf(" Total Series %52d",totalSeries);	
	}


}