package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T07_ThreadGame {

	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Play p = new Play();
		p.start();
		
		Time t = new Time();
		t.start();
		
	}
}

enum Hand {
	가위, 바위, 보
}

class Play extends Thread {
	
	@Override
	public void run() {
		
		String player = null;
		
		do {			
			player = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요.");			
		} while (!player.equals("가위") && !player.equals("바위") && !player.equals("보"));

		
		T07_ThreadGame.inputCheck = true;
		
		String[] hand = {"가위", "바위", "보"};
		int random = (int)(Math.random() * 3);
		String computer = hand[random];
		
		System.out.println("컴퓨터 : " + computer);
		System.out.println("당신 : " + player);
		
		if(player.equals(computer)) {
			System.out.println("결과 : 비겼습니다");
		} else if ((computer.equals("가위") && player.equals("바위")) ||
				   (computer.equals("바위") && player.equals("보")) ||
				   (computer.equals("보") && player.equals("가위"))) {
			System.out.println("결과 : 당신이 이겼습니다.");			
		} else {
			System.out.println("결과 : 당신이 졌습니다.");
		}
	}
}

class Time extends Thread {
	
	@Override
	public void run() {
		
		for(int i = 5; i >= 1; i--) {
			
			
			if(T07_ThreadGame.inputCheck == true) {
				return;
			}

			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("5초가 경과하였습니다. ");
		System.exit(0);
	}
}

