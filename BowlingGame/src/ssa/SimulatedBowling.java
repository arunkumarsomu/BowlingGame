package ssa;

import java.util.Random;


public class SimulatedBowling{

	private int bowlingScores[][][] = new int[3][10][3];
	static Random rnd = new Random();
	boolean spare[][] = new boolean[3][10];
	boolean strike[][] = new boolean[3][10];
	int totals[][]    = new int[3][10];
	int totalSeries =0;
	
	
	  
	
	int getPinsKnockedDown() {
		  int randomInt = rnd.nextInt(11); // zero to ten
		  return randomInt;
		}
	
	void initFlags(){
		for (int idx = 0; idx < 3; idx++)
		for (int idx2 = 0;idx2 < 10 ; idx2++){
			spare[idx][idx2] = false;
			strike[idx][idx2] = false;
			for (int idx3 = 0;idx3 < 3 ; idx3++)
			bowlingScores[idx][idx2][idx3] = 0 ;
			
		}
	}
	
	void bowl(){
		initFlags();
		
		for (int idx = 0; idx < 3; idx++){		
			for (int idx2 = 0;idx2 < 10 ; idx2++){
				bowlingScores[idx][idx2][0] = firstRoll(idx2);
				if (idx2 < 9){						
					if (bowlingScores[idx][idx2][0] < 10 ){
						bowlingScores[idx][idx2][1] = secondRoll(bowlingScores[idx][idx2][0]);
					    if (bowlingScores[idx][idx2][0] + bowlingScores[idx][idx2][1] == 10)
					    	spare[idx][idx2] = true;
					}
					else 
						strike[idx][idx2] = true;
				
				}
				else if (idx2 ==9){
					if (bowlingScores[idx][idx2][0] == 10){
						bowlingScores[idx][idx2][1] = secondRoll();
						bowlingScores[idx][idx2][2] = thirdRoll(bowlingScores[idx][idx2][1]);
					}
					else{
						bowlingScores[idx][idx2][1] = secondRoll(bowlingScores[idx][idx2][0]);
						if (bowlingScores[idx][idx2][0] + bowlingScores[idx][idx2][1] == 10){
						bowlingScores[idx][idx2][2] = thirdRoll(bowlingScores[idx][idx2][1]);
						}
						
					}
				}
			}
			
			
		}
		
		calcTotals();
		calcSpareTotals();
		calcStrikeTotals();
		calcFinalTotals();
		displayScores();
		
	}
	
	void calcSpareTotals(){
		for (int idx = 0; idx < 3; idx++){		
			for (int idx2 = 0;idx2 < 9 ; idx2++){
				
				if ( spare[idx][idx2] == true){
				totals[idx][idx2] = totals[idx][idx2] + bowlingScores[idx][idx2 + 1][0];
				
				}
				
			}
			
		}
		
	}
	
	void calcStrikeTotals(){
		for (int idx = 0; idx < 3; idx++){		
			for (int idx2 = 0;idx2 < 9 ; idx2++){
				
				if ( strike[idx][idx2] == true){
				totals[idx][idx2] = totals[idx][idx2] + bowlingScores[idx][idx2 + 1][0]+bowlingScores[idx][idx2 + 1][1];
				
				}
				
			}
			
		}
		
	}
	
	
	void calcTotals(){
		for (int idx = 0; idx < 3; idx++){		
			for (int idx2 = 0;idx2 < 10 ; idx2++){
				for (int idx3 = 0;idx3 < 3 ; idx3++)
				totals[idx][idx2] += bowlingScores[idx][idx2][idx3];
//				totals[idx][idx2] = bowlingScores[idx][idx2][0] + bowlingScores[idx][idx2][1]
			}
			
		}
		
	}
	
	void calcFinalTotals(){
		for (int idx = 0; idx < 3; idx++){		
			for (int idx2 = 1;idx2 < 10 ; idx2++){
				totals[idx][idx2] = totals[idx][idx2 - 1] + totals[idx][idx2];
				
			}
		
		}
		totalSeries = totals[0][9] + totals[1][9] + totals[2][9] ;
	}
	
	
	int firstRoll(int idx){
		int firstPin = getPinsKnockedDown();
		int secondPin =0,totalOfTwo=0;
		
		return firstPin;
	}
	
	
	int secondRoll(int firstPin){
		int secondPin = getPinsKnockedDown();
		if ( secondPin + firstPin >= 10 ){
			secondPin = 10 - firstPin;
		}
		return secondPin;
	}
	
	int secondRoll(){
		int secondPin = getPinsKnockedDown();
		return secondPin;
	}
	
	int thirdRoll(int secondPin){
		int thirdPin = getPinsKnockedDown();
		if (secondPin == 10)
			return thirdPin;
		else if ( secondPin + thirdPin >= 10 ){
			thirdPin = 10 - secondPin;
		}
		return thirdPin;
	}
	
	
	void displayScores(){
		int total =0;
		
		System.out.printf(" Frames ");
		for (int i=0;i<10;i++)
			System.out.printf("%8d ",i+1);
		
		System.out.printf("\t  Totals ");
		
//		System.out.printf(" Frames    1 	   2  	   3   	   4  	   5   	   6 	   7  	   8  	   9  	   10     Total");
		System.out.println(" ");
			for (int idx = 0; idx < 3;idx++){	
				System.out.println(" ");
				 System.out.printf(" Game %d",idx+1);
				for (int idx2 = 0;idx2 < 10 ; idx2++){
					if (idx2 < 9)
					System.out.printf("  %3d | %d",bowlingScores[idx][idx2][0],bowlingScores[idx][idx2][1]);
					if (idx2 ==9) System.out.printf("  %3d | %d | %d ",bowlingScores[idx][idx2][0],bowlingScores[idx][idx2][1],bowlingScores[idx][idx2][2]);
		//			total += bowlingScores[idx][idx2] ;
				}
				System.out.printf("  %7d",totals[idx][9]);
				System.out.println(" ");
				System.out.printf("           ------   ------   ------   ------   ------   ------   ------   ------   ------   ----------");
				
				System.out.println(" ");
				System.out.printf("             ");
				for (int i=0;i<10;i++)
				System.out.printf("%d       ", totals[idx][i]);
				
				totalSeries += total;
				total = 0;
				System.out.println(" ");
			}
			System.out.println(" ");
		System.out.printf(" Total Series %97d",totalSeries);	
	}

	

}