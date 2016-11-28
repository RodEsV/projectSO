package so_project;

import java.util.concurrent.Semaphore;

public class Person extends Thread {
	
	Island island;
	String type;
	Boolean finish;
	Semaphore sm = new Semaphore(1);
	
	
	public Person(Island island, String type) {
		this.island = island;
		this.type = type;
		this.finish = new Boolean(false);
		//this.sm = new Semaphore(100);
	}

	public synchronized void move() throws InterruptedException{

		
		//this.island.mutex.acquire();
		this.sm.acquire();
		if (this.island.getBoat() != null) {
			
			Integer aux = checkForKids(this.island);
			if (this.island.name.equals("oahu")) {
				if (this.type.equals("adult")) {
					if (aux < 2) {
						try {
					
							this.island.getBoat().aboard(this, "molokai", true);
					
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} else if( this.type.equals("kid")){
					try {
						if (aux == 1) {
					
							this.island.getBoat().aboard(this, "molokai", true);
					
						} else {
					
						
							if (this.island.getBoat() == null){
								System.out.println("bote nulo");
							}
							this.island.getBoat().aboard(this, "molokai", false);
							
							
					
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (this.island.name.equals("molokai")){
				if( this.type.equals("kid")){
					try {
						
						this.island.getBoat().aboard(this, "oahu", true);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if ( this.type.equals("adult")){
					if( aux == 0 ) {
						try {
							
							this.island.getBoat().aboard(this, "oahu", true);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		this.sm.release();
		
		//this.island.mutex.release();
	}
	
	
	public void run() {
		while(!finish){
			try {
				Thread.sleep(1000);
				this.move();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized Integer checkForKids(Island island) {
		return island.countKids();
	}
	
	
}
