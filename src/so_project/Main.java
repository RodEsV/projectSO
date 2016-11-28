package so_project;

import java.util.Scanner;

public class Main {
	
public static void main(String[] args) throws InterruptedException {
		
		
		Scanner sn = new Scanner(System.in);
		
		System.out.println("Niños de Molokai");
		Integer kidsMolokai = Integer.parseInt(sn.nextLine());
		System.out.println("Adultos de Molokai");
		Integer adulstMolokai = Integer.parseInt(sn.nextLine());
		System.out.println("Niños de Oahu");
		Integer kidsOahu = Integer.parseInt(sn.nextLine());
		System.out.println("Adultos de Oahu");
		Integer adultsOahu = Integer.parseInt(sn.nextLine());
		System.out.println("Nombre de la isla inicial donde estará el bote");
		String inputIsland = sn.nextLine().toLowerCase();
		
		Boat boat =  new Boat(kidsMolokai,adulstMolokai,kidsOahu, adultsOahu, inputIsland);
				
		boat.start();
		
		boat.join();
		
	}


}
