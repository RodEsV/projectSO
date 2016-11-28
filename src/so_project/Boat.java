package so_project;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Boat extends Thread{
	ArrayList<Person> boat ;
	Boolean full = false;
	Island molokai;
	Island oahu;
	Island currentIsland;
	BoatGrader bg;
	Boolean finish;
	
	
	
	public Boat(Integer molokaiKids, Integer molokaiAdults, Integer oahuKids, Integer oahuAdults, String initialIsland){
		this.finish = false;
		if(initialIsland.equals("molokai")){
			this.molokai = new Island("molokai",this);
			this.oahu = new Island("oahu", null);
			this.currentIsland = molokai;
			
		} else if( initialIsland.equals("oahu")){
			this.molokai = new Island("molokai",null);
			this.oahu = new Island("oahu",this);
			this.currentIsland = oahu;
		}
		
		
		this.boat = new ArrayList<>();
		this.bg = new BoatGrader(molokai, oahu);
		
		for( int i = 0; i < molokaiKids; i++ ){
			this.molokai.getPopulation().add(new Person(this.molokai, "kid"));
		}
		for( int i = 0; i < molokaiAdults; i++ ){
			this.molokai.getPopulation().add(new Person(this.molokai, "adult"));
		}
		for( int i = 0; i < oahuKids; i++ ){
			this.oahu.getPopulation().add(new Person(this.oahu, "kid"));
		}
		for( int i = 0; i < oahuKids; i++ ){
			this.oahu.getPopulation().add(new Person(this.oahu, "adult"));
		}
	}
	
	
	public void run(){
		
		this.molokai.init();
		this.oahu.init();
		
	}
	
	
	
	public synchronized Island aboard(Person person,  String to, Boolean goOne) throws InterruptedException{		
		if(this.currentIsland.equals(person.island)){
			if (person.type.equals("adult")) {
				if (this.boat.size() == 0) {
					this.boat.add(person);
					this.full = true;
				}
				
			} else if( this.boat.size() < 2 && this.boat.indexOf(person) == -1 ) { 
				this.boat.add(person);
				if (this.boat.size() == 2){
					this.full = true;
				}
			}
			if (goOne || this.full) {
				Thread.sleep(1000);
				System.out.println("islas iguales " + this.currentIsland.equals(person.island));
		
				
				this.currentIsland.mutex.acquire();
				
				this.currentIsland.deletePeople(boat, currentIsland);
				
				this.currentIsland.mutex.release();
		
				if (to.equals("molokai")) {
					this.bg.action(person, "going to molokai");
					this.currentIsland = molokai;
					
					this.currentIsland.mutex.acquire();
					
					this.oahu.setBoat(null);
					this.currentIsland.setBoat(this);
					this.currentIsland.addPeople(boat, currentIsland);
					
					this.currentIsland.mutex.release();
					
					this.bg.action(person, "Already in molokai");
				} else if( to.equals("oahu")){
					this.bg.action(person, "going to oahu");
					this.molokai.setBoat(null);
					this.currentIsland = oahu;
					
					this.currentIsland.mutex.acquire();
					
					this.currentIsland.setBoat(this);
					this.currentIsland.addPeople(boat, currentIsland);
					
					this.currentIsland.mutex.release();
					
					this.bg.action(person, "Already in oahu");
					
					
				}
				
				this.boat.clear();
				this.full = false;
				
				return currentIsland;
			}
			
			return null;
		}
		return null;
	}
}
