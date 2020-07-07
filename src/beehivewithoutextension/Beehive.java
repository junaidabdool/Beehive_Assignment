package beehivewithoutextension;

	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.Collection;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.List;
	import java.util.Random;
	import java.util.Stack;
	public class Beehive  {
		private static double numberOfWorkerBees=0;
		private static double simulationdays=0;
		private static int eggsLaid=0;
		private static int totalEggsLaid;
		private static int countDeath=0;
		private static double honey=3000;
		private static int countDay;
		private static int countBirths=0;
		private static int countLarva=0;
		private static int countPupa=0;
		private static int countDrones=0;
		private static int rose=0;
		private static int Hibiscus=0;
		private static int Frangipani=0;
		private static double nectar;
		private static ArrayList<Bee> BeeArray = new ArrayList<Bee>(1000);// The BeeArray
		private static ArrayList<Flowers> FlowersArray = new ArrayList<Flowers>(5); // The FlowersArray
		private static Stack<Bee> workersLaunchChamber = new Stack<Bee>();// The workersLaunchChamber (Stack)
		
	public static void readSimulationConfig() throws FileNotFoundException {// read the data from the config file.
		try(BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeeHiveWithOutExtension\\simconfig.txt"))) {
			    String str;
			    int i = 0;
			    double numbers[] = new double [3];
			    while ((str = in.readLine()) != null) {
			        String[] tokens = str.split(" ");// split the data where there is a blank space and store it in the Tokens Array.
			        numbers [i] = Double.parseDouble(tokens[1]); // The second part of the token is read and store in the number Array. As the second part of the Array that stored the values of simulation days , worker , honey 
			        i++; // increment the index of the number Array.
			    }
			    
		simulationdays = numbers[0];// store the simulationdays
		numberOfWorkerBees = numbers[1];//store the number of Workers from the file
		honey = numbers[2];//store the honey.
		System.out.println("Simulationdays \t\n"+simulationdays);
		System.out.println();
		System.out.println("initworker \t\n"+numberOfWorkerBees);
		System.out.println();
		System.out.println("Honey \t\n"+honey);
			 
			}
		catch (IOException e) {
			System.out.println(e.toString());
			}					  
		}
	public static void logDailyStatusToFile1() {// load only the header of the file called only once at the start of the simulation :)
		
		try {
			PrintWriter pw= new PrintWriter(new FileWriter("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeeHiveWithOutExtension\\simLog.csv",false));
			StringBuilder sb = new StringBuilder();
			
				sb.append("Day");
				sb.append(",");
				sb.append("eggsLaid");
				sb.append(",");
				sb.append("eggInHive");
				sb.append(",");
				sb.append("Larva");
				sb.append(",");
				sb.append("Pupa");
				sb.append(",");
				sb.append("Worker");
				sb.append(",");
				sb.append("Drone");
				sb.append(",");
				sb.append("Death");
				sb.append(",");
				sb.append("Birth");
				sb.append(",");
				sb.append("HoneyStock");
				sb.append(",");
				sb.append("Flower 1 Nectar");
				sb.append(",");
				sb.append("Flower 2 Nectar");
				sb.append(",");
				sb.append("Flower 3 Nectar");
				sb.append(",");
		
				sb.append("\r\n");
				pw.write(sb.toString());
				pw.close();
			
				
			
		}catch(Exception e){
	}
	}
	public static void logDailyStatusToFile() {// loads the values,  :)
		
		try {
			PrintWriter pw= new PrintWriter(new FileWriter("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeeHiveWithOutExtension\\simLog.csv",true));
			StringBuilder sb = new StringBuilder();
			
				sb.append(countDay);
				sb.append(",");
				sb.append(eggsLaid);
				sb.append(",");
				sb.append(totalEggsLaid);
				sb.append(",");
				sb.append(countLarva);
				sb.append(",");
				sb.append(countPupa);
				sb.append(",");
				sb.append(numberOfWorkerBees);
				sb.append(",");
				sb.append(countDrones);
				sb.append(",");
				sb.append(countDeath);
				sb.append(",");
				sb.append(countBirths);
				sb.append(",");
				sb.append(honey);
				sb.append(",");
				sb.append(rose);
				sb.append(",");
				sb.append(Hibiscus);
				sb.append(",");
				sb.append(Frangipani);
				sb.append(",");
			

			sb.append("\r\n");
			pw.write(sb.toString());
			pw.close();
			
				
			
		}catch(Exception e){
			
	}
	}

	public static void initBeesArray(double numberOfWorkerBees ) {// load the workers from the config file into the ArrayList
		for(int i=0;i<numberOfWorkerBees;i++) {
			Bee Bee1= new Bee("Worker",21,0,false,true);// set the Bee(type,age,nectarcollectionsorties,eaten,alive)
		    BeeArray.add(Bee1);
			}
			
		}
	public static int layDailyEggs() {//generate random numbers between 
		
		Random randomEggs = new Random();
	    final int MAX_EGGS = 50;
	    final int MIN_EGGS = 10;
	    
	    
	    int x = randomEggs.nextInt((MAX_EGGS - MIN_EGGS) + 1) + MIN_EGGS;
	    eggsLaid = x;
	    return x;
	    
	}
	public static void addEggToHive(int eggsLaid) {
		
			for(int i =0;i<eggsLaid;i++) {
				if(BeeArray.size()<1000) {// check the space in the BeeArray 
					Bee Bee2 = new Bee("Egg",0,0,false,true);
					BeeArray.add(Bee2);
				}
			
		}
		}

	public static void incrementAge() {// increment the Age for Each Bee
		for(Bee bee:BeeArray) {
			 int age =bee.getAge();
			 bee.incrementAge(age);// The attribute increment age is called which increase the age by 1.
		}
	}
	public static void metamorphose() {
		Random random = new Random();
		int age;
		String type ;
		for(int i=0;i<BeeArray.size();i++) {
			age = BeeArray.get(i).getAge();// get the age and type of each Bee
			type = BeeArray.get(i).getType();
			if(age>4 && type=="Egg") {// check the age and change their type accordingly
				BeeArray.get(i).setType("larva");
			}else if(age>10 && type=="larva") {
				BeeArray.get(i).setType("pupa");		
			}else if(age>20 && type=="pupa"){
				double probability = random.nextDouble();
				if(probability<=0.1) {
					BeeArray.get(i).setType("Drone");
				}else {
					BeeArray.get(i).setType("Worker");
				}
				
				}else if(type=="Worker" && age>=35) {
					funeral(i);// the funeral function is called 
				}else if (type=="Drone" && age>=54) {
					funeral(i);// the funeral function is called 
				}
		}
	}
	public static void feedingTime() {
		if(honey<2) {
			System.exit(0);// Terminate the simulation as less than 2 unit of honey is available and the queen won't be able to feed
		}
		honey=honey-2;// Feeding the queen 
		
			for(int i=0;i<BeeArray.size();i++) {
			String type = BeeArray.get(i).getType();
			if(honey>0) {
				if(type=="larva") {
					honey=honey-0.5;
					BeeArray.get(i).setEaten(true);
				}else if (type=="Worker" || type=="Drone") {
					honey=honey-1;
					BeeArray.get(i).setEaten(true);
				}
			}else {
				BeeArray.get(i).setEaten(false);//set all the Eaten attribute to false (No Honey available for the Worker,Drone and larva).
			}
			
		}
			
	}

	public static void undertakencheck() {// check if the attribute Eaten is False then the Funeral function is called 
		for(int i=0;i<BeeArray.size();i++) {
			String type = BeeArray.get(i).getType();
			Boolean isEaten = BeeArray.get(i).isEaten();
			if(type=="larva" && isEaten==false) {
				funeral(i);// pass the index of the bee to the funeral function
			}else if (type=="Worker" && isEaten==false) {
				funeral(i);// pass the index of the bee to the funeral function
			}else if(type=="Drone" && isEaten==false) {
				funeral(i);
			}
		}
	}
	public static void funeral(int i) {
		BeeArray.remove(i);// remove the bee in the Array
		countDeath++;// increment death
	}
	public static void counttypesofbee() {
		countBirths=0;
		countLarva=0;
		countPupa=0  ;  
		numberOfWorkerBees=0;
		countDrones=0;
		 for(int i=0;i<BeeArray.size();i++) {
			String type =BeeArray.get(i).getType();
			if(type=="larva") {
				countLarva++;
				countBirths++;
			}else if(type=="pupa"){
				countPupa++;
				countBirths++;
			}else if(type=="Worker") {
				numberOfWorkerBees++;
				
			}else if(type=="Drone") {
				countDrones++;
				
			}
		 }
	}
	public static void countflowers() { // get the value of the flower for each day
		rose = FlowersArray.get(0).getCurrentNectarAvailable();
		Hibiscus=FlowersArray.get(1).getCurrentNectarAvailable();
		Frangipani=FlowersArray.get(2).getCurrentNectarAvailable();
		
	}

	public static void printFlowerGarden() {
		System.out.println("Flower Roses nectar Stock :"+rose);
		System.out.println("Flower Hibiscus nectar Stock :"+Hibiscus);
		System.out.println("Flower Frangipani nectar Stock "+Frangipani);
		
	}

	public static void AllWorkerBeesGardenSorties() {
		for(int i=0;i<BeeArray.size();i++) {
			String type =BeeArray.get(i).getType();
			if(type=="Worker") {
				workersLaunchChamber.push(BeeArray.get(i));// push all the worker into the stack
				BeeArray.remove(i);// remove them from the BeeArray
			}
		}
		while(!workersLaunchChamber.isEmpty()){
			visitFlower(workersLaunchChamber.pop()); // Each Worker is sent to visitflower()
		}
		
	}
	public static void visitFlower(Bee bee) {
		Random random = new Random();

		 double probability = random.nextDouble();
		 int numofsorties = bee.getNectarCollectionSorties(); //get the number of times the bee has visited the flowers
			if (probability<0.3333) {
			int current = FlowersArray.get(0).getCurrentNectarAvailable();
			int collection = FlowersArray.get(0).getNectarCollection();
			
			if(current>=20) {
				current = current-collection; // minus the NectarCollection
				nectar = nectar + collection;
				FlowersArray.get(0).setCurrentNectarAvailable(current); // 
				numofsorties++;
				bee.setNectatCollectionSorties(numofsorties);
			}
				else if(numofsorties<=5){// if numofsorties less 5 than visitflower is called to allow the bee to visit another flower with available nectar.
				visitFlower(bee);
			}
			
			 }else if(probability>0.33334 && probability<=0.66667) {
				 	int current = FlowersArray.get(1).getCurrentNectarAvailable();
					int collection = FlowersArray.get(1).getNectarCollection();
					if(current>=10) {
						current = current-collection;
						nectar = nectar + collection;
						FlowersArray.get(1).setCurrentNectarAvailable(current);
						numofsorties++;
						bee.setNectatCollectionSorties(numofsorties);
					}
					else  if(numofsorties<=5){
						visitFlower(bee);
					}
			 }else if(probability>0.66667 && probability<=0.9999) {
				 	int current = FlowersArray.get(2).getCurrentNectarAvailable();
					int collection = FlowersArray.get(2).getNectarCollection();
				 if(current>=50) {
						current = current-collection;
						nectar = nectar + collection;
						FlowersArray.get(2).setCurrentNectarAvailable(current);
						numofsorties++;
						bee.setNectatCollectionSorties(numofsorties);
						
				 }
				 else if(numofsorties<=5) {
					 visitFlower(bee);
				 }
			 }
			 
			 	honey = (int) honey+(nectar/40);// convert the nectar into honey
				double nectarRemain=nectar%40;
				nectar=nectarRemain;
				
				BeeArray.add(bee);
			 
	}
	public static void emptyStomachofAllBees() {
		for(int i=0;i<BeeArray.size();i++) {
			BeeArray.get(i).setEaten(false);
			BeeArray.get(i).setNectatCollectionSorties(0);
		}
	}
	public static void resetFlowerArray() {
		for(int i=0;i<FlowersArray.size();i++) {
			String name ;
			name=FlowersArray.get(i).getFlowerName();
			if(name=="Rose") {
				FlowersArray.get(i).setNectarCollection(20);
				FlowersArray.get(i).setCurrentNectarAvailable(20000);
				FlowersArray.get(i).setDailyNectarProduction(20000);
			}else if(name=="Frangip") {
				FlowersArray.get(i).setNectarCollection(50);
				FlowersArray.get(i).setCurrentNectarAvailable(50000);
				FlowersArray.get(i).setDailyNectarProduction(50000);
			}else if(name=="Hibiscus") {
				FlowersArray.get(i).setNectarCollection(10);
				FlowersArray.get(i).setCurrentNectarAvailable(10000);
				FlowersArray.get(i).setDailyNectarProduction(10000);
			}
		}
	}
	public static void printBeeHiveStatus() {
		
		 totalEggsLaid=eggsLaid+totalEggsLaid;	
		 counttypesofbee();
		
		 //Print the BeehiveStatus
		System.out.println("************* This is Day " + countDay +" *****************");
		System.out.println("Queen laid "+ eggsLaid +" eggs! ");
		System.out.println("Beehive Status");
		System.out.println("Egg count: "+ totalEggsLaid);
		System.out.println("Larva Count: "+countLarva  );
		System.out.println("Pupa Count: " + countPupa);
		System.out.println("Worker Count: "+ numberOfWorkerBees);
		System.out.println("Drone Count: " + countDrones );
		System.out.println("Death Count: " + countDeath );
		System.out.println("Birth Count: "+ countBirths);
		System.out.println("Honey Stock: " + honey );
		printFlowerGarden();
	}
	public static void aDayPasses() {
		incrementAge();
		metamorphose();
		resetFlowerArray();
		for(int i =0;i<5;i++) {
			AllWorkerBeesGardenSorties();// call five times in a day.
		}
		emptyStomachofAllBees();
		feedingTime();
		undertakencheck();
	    try {
	    	Thread.sleep(1500);
	    }catch(InterruptedException e) {
	    		e.printStackTrace();
	    		}

		countDay++;
	}

		public static void main(String[] args) throws FileNotFoundException, Exception {
				Flowers Rose = new Flowers("Rose",20,20000,20000);
				Flowers Frangip = new Flowers("Frangip",50,50000,50000);
				Flowers Hibiscus = new Flowers("Hibiscus",10,10000,10000);
				FlowersArray.add(Rose);// add the flowers in the Array
				FlowersArray.add(Frangip);// 
				FlowersArray.add(Hibiscus);
				
				logDailyStatusToFile1(); // create the file simlog and load the header
							
				readSimulationConfig();
				initBeesArray(numberOfWorkerBees);
				for(int i=0;i<simulationdays;i++) {	
					
					addEggToHive(layDailyEggs());
					counttypesofbee();
					countflowers();
					aDayPasses();
					System.out.println();
					printBeeHiveStatus();
					System.out.println();
					logDailyStatusToFile();
		}  
				
		}
}
