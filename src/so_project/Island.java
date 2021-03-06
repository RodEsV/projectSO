package so_project;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Island {
	
	private ArrayList<Person> people; 
	private Boat boat;
	String name;
	Semaphore mutex;
	
	public Island(String name, Boat boat){
		this.people = new ArrayList<>();
		this.boat = boat;
		this.name = name;
		this.mutex = new Semaphore(1, true);
	}
	
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
	
	public synchronized void deletePeople( ArrayList<Person> people, Island current){
		for (Person person : people) {
			System.out.println("se removio a la persona " + current.people.remove(person));
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
