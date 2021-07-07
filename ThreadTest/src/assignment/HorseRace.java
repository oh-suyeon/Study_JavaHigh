package assignment;

import java.util.Arrays;

public class HorseRace {

	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
				new Horse("01번 말"),
				new Horse("02번 말"),
				new Horse("03번 말"),
				new Horse("04번 말"),
				new Horse("05번 말"),
				new Horse("06번 말"),
				new Horse("07번 말"),
				new Horse("08번 말"),
				new Horse("09번 말"),
				new Horse("10번 말")
		};
		
		PrintHorses ph = new PrintHorses(horses);
		
		for(Horse h : horses) {
			h.start();
		}
		ph.start();
		
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ph.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n경기 끝\n");
		
		Arrays.sort(horses);
		
		System.out.println("경기 결과");
		for(Horse h : horses) {
			System.out.println(h);
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	
	public static int currentRank = 0;
	
	private String hName;
	private int rank;
	private int location;

	public Horse(String hName) {
		this.hName = hName;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return  rank + "등 : " + hName;
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(rank, h.getRank());
	}
	
	@Override
	public void run() {
		for(int i = 1; i <=50; i++) {
			location = i;
			try {
				Thread.sleep((int) (Math.random() * 400));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		rank = ++Horse.currentRank;
	}	
}

class PrintHorses extends Thread {
	
	private Horse[] horses;

	public PrintHorses(Horse[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			if(Horse.currentRank == horses.length) {
				break;
			}
			for(int i = 1; i <=10; i++) {
				System.out.println();
			}
			for(int i = 0; i < horses.length; i++) {
				System.out.println(horses[i].gethName() + " : ");
				for(int j = 1; j <= 50; j++) {
					if(horses[i].getLocation() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}























