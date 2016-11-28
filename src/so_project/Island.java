package so_project;

import java.util.ArrayList;

public class Island {
	
	// entidad isla tiene un conjunto de personas y un bote
	
	private ArrayList<Person> people; 
	private Boat boat;
	String name;
	
	public Island(String name, Boat boat){
		this.people = new ArrayList<>();
		this.boat = boat;
		this.name = name;
	}
	
	// al inicializar la isla arrancan todos los hilos en la isla
	
	public void init(){
		for (Person person : people) {
			person.start();
		}
	}
	
	public synchronized ArrayList<Person> getPopulation(){
		return this.people;
	}
	
	public synchronized Boat getBoat(){
		return this.boat;
	}
	
	public synchronized void addPeople(ArrayList<Person> people, Island current){
		System.out.println(this.name);
		for (Person person : people) {
			person.island = current;
			this.people.add(person);
		}
	}
	
	public synchronized Integer countKids(){
		Integer kids = new Integer(0);
		for (int i = 0; i < this.getPopulation().size(); i++) {
			if (this.getPopulation().get(i).type.equals("kid")) {
				kids += new Integer(1);
			}
		}
		return kids;
	}
	
	public synchronized void deletePeople( ArrayList<Person> people){
		for (Person person : people) {
			System.out.println(this.people.remove(person));
		}
	}
	
	
	public synchronized void setBoat(Boat boat){
		this.boat = boat;
	}
	
	/*
	public synchronized void changeStatusBoat(Boat boat){
		if (this.boat != null) {
			this.boat = null;
		} else
			this.boat = boat;
	}
	*/
}
