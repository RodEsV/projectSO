package so_project;

public class Person extends Thread {
	
	Island island;
	String type;
	Boolean finish;

	public Person(Island island, String type) {
		this.island = island;
		this.type = type;
		this.finish = new Boolean(false);
	}

	public synchronized void move(){
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
	}
	
	public synchronized void run() {

		while(!finish){
			try {
				Thread.sleep((long)Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			move();
		}
			
	}

	public synchronized Integer checkForKids(Island island) {
		return island.countKids();
	}
	
	
}
