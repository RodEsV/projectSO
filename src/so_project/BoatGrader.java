package so_project;

public class BoatGrader {
	Island molokai;
	Island oahu;
	
	public BoatGrader(Island molokai, Island oahu){
		this.molokai = molokai;
		this.oahu = oahu;
	}
	
	public synchronized void action(Person person, String action){
		System.out.println("Action " + action + " by person " + person.type);
		System.out.println("Molokai " + this.molokai.getPopulation().size());
		System.out.println("Oahu " + this.oahu.getPopulation().size());
		System.out.println();
	}

}
