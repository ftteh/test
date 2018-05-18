import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

class Date {
    protected int day;
    protected String month;
    protected int year;

    public Date(int d, String m, int y) {
        day = d;
        month = m;
        year = y;
    }

    public void setDate(int d, String m, int y) {
        day = d;
        month = m;
        year = y;
    }

    public String getDate() {
        return day + " " + month + " " + year;
    }

}

class Member {
    protected String nickname;
    protected String pos;
    protected int age;
    protected Date birthday;
    protected Record healthRecord, medicalRecord;
    protected ArrayList<FitnessActivity> fitnessActivities = new ArrayList<FitnessActivity>();

    // protected ArrayList<shoppingItem> shoppingItemList=new ArrayList();

    public Member(String n, String p, int a, int d, String m, int y) {
        nickname = n;
        pos = p;
        age = a;
        birthday = new Date(d, m, y);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String s) {
        nickname = s;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String s) {
        pos = s;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int i) {
        age = i;
    }

    public String getBirthday() {
        return birthday.getDate();
    }

    public void setBirthday(int d, String m, int y) {
        birthday.setDate(d, m, y);
    }

    public Record getHealthRecord() {
        return healthRecord;
    }

    public Record getMedicalRecord() {
        return medicalRecord;
    }

    public void setHealthRecord(int rate, int bloodpressure, int bloodglucose, double h, double w, String d, String n,
            String l) {
        this.healthRecord = new HealthMeasurement(rate, bloodpressure, bloodglucose, h, w, d, n, l);
    }

    public void setMedicalRecord(String record, String sym, String doc, String future, String last, String contact,
            String d, String l, String n) {
        this.medicalRecord = new MedicalRecord(record, sym, doc, future, last, contact, d, l, n);
    }

    public void joinActivity(FitnessActivity a) {
        this.fitnessActivities.add(a);
    }

    public void showJoinedActivity() {
        for (FitnessActivity m : fitnessActivities)
            m.toString();
    }

    // public void buyItem(ShoppingItem i){
    // shoppingItemList.add(i);
    // }

    // public String getShoppingItem(){
    // for(ShoppingItem s:ShoppingItemList)
    // //s.toString();
    // }

    public String toString() {
        System.out.println("Nickname: " + getNickname());
        System.out.println("Pos: " + getPos());
        System.out.println("Age: " + getAge());
        System.out.println("Birthday: " + getBirthday());
        // getShoppingItem();
        return "\n";
    }

}

class ToDo {
    protected String todoName;
    protected String state;
    protected Date date;
    protected ArrayList<Member> memberInvolved = new ArrayList<Member>();

    public ToDo(String t, String s, int d, String m, int y) {
        todoName = t;
        state = s;
        date = new Date(d, m, y);
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String s) {
        todoName = s;
    }

    public String getDate() {
        return date.getDate();
    }

    public void setDate(int d, String m, int y) {
        date.setDate(d, m, y);
    }

    public void addMember(Member m) {
        memberInvolved.add(m);
    }

    public void removeMember(Member m) {
        memberInvolved.remove(m);
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String toString() {
        System.out.println("Todo: " + getTodoName());
        System.out.println("Date: " + getDate());
        System.out.println("State: " + getState());
        for (Member a : memberInvolved)
            System.out.println(a.getNickname());
        return "\n";
    }

}

abstract class Record {
    protected String dateRecorded;
    protected String nextUpdate;
    protected String lastModified;

    public Record(String date, String next, String lastMod) {
        dateRecorded = date;
        nextUpdate = next;
        lastModified = lastMod;
    }

    public abstract void setNextUpdate(String next);

    public abstract String getNextUpdate();

    public abstract void setLastModified(String lastMod);

    public abstract String getLastModified();
}

class HealthMeasurement extends Record {
    protected int heartrate;
    protected int bloodpressureLevel;
    protected int bloodglucoseLevel;
    protected double height, weight;

    public HealthMeasurement(int rate, int bloodpressure, int bloodglucose, double h, double w, String d, String n,
            String l) {
        super(d, n, l);
        heartrate = rate;
        bloodpressureLevel = bloodpressure;
        bloodglucoseLevel = bloodglucose;
        height = h;
        weight = w;
    }

    public void setHeartrate(int rate) {
        heartrate = rate;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public void setBloodpressureLevel(int bloodpressure) {
        bloodpressureLevel = bloodpressure;
    }

    public int getBloodpressureLevel() {
        return bloodpressureLevel;
    }

    public void setBloodglucoseLevel(int bloodglucose) {
        bloodglucoseLevel = bloodglucose;
    }

    public int getBloodglucoseLevel() {
        return bloodglucoseLevel;
    }

    public void setHeight(double h) {
        height = h;
    }

    public double getHeight() {
        return height;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public double getWeight() {
        return weight;
    }

    public String calculateBMI() {
        return String.format("%.2f", ((weight) * 10000 / (height * height)));
    }

    public void setNextUpdate(String next) {
        nextUpdate = next;
    }

    public String getNextUpdate() {
        return nextUpdate;
    }

    public void setLastModified(String lastMod) {
        lastModified = lastMod;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String toString() {

        return "====Health Measurement====" + "\n Next record update: " + getNextUpdate()
                + "\n Last record modification: " + getLastModified() + "\n Heart rate : " + heartrate
                + "\n Blood Pressure Level : " + bloodpressureLevel + "\n Blood Glucose Level : " + bloodglucoseLevel
                + "\n Weight : " + weight + "\n Height : " + height + "\n Body Mass Index (BMI) : " + calculateBMI();
    }
}

class MedicalRecord extends Record {
    protected String healthRecord;
    protected String symptom;
    protected String doctorName;
    protected String futureAppointment;
    protected String lastAppointment;
    protected String emergencyContact;

    public MedicalRecord(String record, String sym, String doc, String future, String last, String contact, String d,
            String l, String n) {
        super(d, l, n);
        healthRecord = record;
        symptom = sym;
        doctorName = doc;
        futureAppointment = future;
        lastAppointment = last;
        emergencyContact = contact;
    }

    public void setHealthRecord(String record) {
        healthRecord = record;
    }

    public String getHealthRecord() {
        return healthRecord;
    }

    public void setSymptom(String sym) {
        symptom = sym;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setDoctorName(String doc) {
        doctorName = doc;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setFutureAppointment(String future) {
        futureAppointment = future;
    }

    public String getFutureAppointment() {
        return futureAppointment;
    }

    public void setLastAppointment(String last) {
        lastAppointment = last;
    }

    public String getLastAppointment() {
        return lastAppointment;
    }

    public void setEmergencyContact(String contact) {
        emergencyContact = contact;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setNextUpdate(String next) {
        nextUpdate = next;
    }

    public String getNextUpdate() {
        return nextUpdate;
    }

    public void setLastModified(String lastMod) {
        lastModified = lastMod;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String toString() {

        return "====Medical Record====" + "\n Next record update: " + getNextUpdate() + "\n Last record modification: "
                + getLastModified() + "\n Health Record : " + healthRecord + "\n Symptom : " + symptom
                + "\n Doctor Name : " + doctorName + "\n Future Appointment " + futureAppointment
                + "\n Last Appointment : " + lastAppointment + "\n Emergency contact : " + emergencyContact;
    }

}

class FitnessActivity {
    private int numOfStepsPerDay;
    private int numOfWorkoutPerWeek;
    private int numOfCyclingPerWeek;
    private int numOfStairsClimbedPerDay;
    private String activityPlan;
    private ArrayList<Member> joinedMember = new ArrayList<Member>();

    public FitnessActivity(int step, int workout, int cycle, int stair, String activity) {
        numOfStepsPerDay = step;
        numOfWorkoutPerWeek = workout;
        numOfCyclingPerWeek = cycle;
        numOfStairsClimbedPerDay = stair;
        activityPlan = activity;
    }

    public void setNumOfStepsPerDay(int step) {
        numOfStepsPerDay = step;
    }

    public int getNumOfStepsPerDay() {
        return numOfStepsPerDay;
    }

    public void setNumOfWorkoutPerWeek(int workout) {
        numOfWorkoutPerWeek = workout;
    }

    public int getNumOfWorkoutPerWeek() {
        return numOfWorkoutPerWeek;
    }

    public void setNumOfCyclingPerWeek(int cycle) {
        numOfCyclingPerWeek = cycle;
    }

    public int getNumOfCyclingPerWeek() {
        return numOfCyclingPerWeek;
    }

    public void setNumOfStairsClimbedPerDay(int stair) {
        numOfStairsClimbedPerDay = stair;
    }

    public int getNumOfStairsClimbedPerDay() {
        return numOfStairsClimbedPerDay;
    }
    
    public void setactivityPlan(String activity) {
        activityPlan = activity;
    }

    public String getactivityPlan() {
        return activityPlan;
    }

    public void addMember(Member m){
        joinedMember.add(m);
    }

    public void showJoinedMember(){
        for(Member m:joinedMember)
            System.out.println(m.getNickname()+" ("+m.getPos()+")");
    }

    public String toString() {
        return "====Fitness Activity====" + "\n Number of steps Per Day : " + numOfStepsPerDay
                + "\n Number of Workout Per Week : " + numOfWorkoutPerWeek + "\n Number of Stairs Climbed per Day : "
                + numOfStairsClimbedPerDay + "\n Number of Cycling Per Week : " + numOfCyclingPerWeek
                + "\n Plan activity name : " + activityPlan;
    }
}

class RecentlyBuyer{ // this class show recently buyer who has buy an item
	private
		static int num =0;
		String [] buyer = new String[20];
		String [] itemName = new String[20];
	public RecentlyBuyer(){}
	public RecentlyBuyer(String memberName, String itemBuy)throws IOException{
		buyer[num] = memberName;
		itemName[num] = itemBuy;
		writeBuyer(buyer[num],itemName[num]);
		num++;

    }
    public void writeBuyer(String buyer, String item) throws IOException{
		String filename= "recentlyBuyer.txt";
		FileWriter fw = new FileWriter(filename,true);

		fw.write("\r\n");
		fw.write(buyer + "\t");
		fw.write(item + "\t");
    	fw.close();

	}


	public void recentlyBuyInterface() throws IOException{
		Scanner in = new Scanner(new FileReader("recentlyBuyer.txt"));
		int count=0;

		while(in.hasNext()){
			buyer[count] = in.next();
			itemName[count] = in.next();
			count++;
		}
			in.close();

		System.out.printf("%40s","Recently Buyer\n\n");
		for(int i=count; i>count-3; i--){ // set to only 3 recently buyer to show , not all buyer will be show
			System.out.printf("%20s%-10s","",buyer[i-1]);
			System.out.printf("%5s%-10s","",itemName[i-1]);
			System.out.print("\n");
		}
	}
}

class ShoppingItem {
	private String[] itemName = new String[20];
	String[] buyerName = new String[20];
	double[] unitCost = new double[20];
	int[] itemQuantity = new int[20];
	static int count = 0;
	RecentlyBuyer[] buyer = new RecentlyBuyer[10]; // this object is used to send buyer and item buy to RecentlyBuyer
													// class

	public ShoppingItem() {
	}

	public ShoppingItem(String i, String b, int q) {
		itemName[count] = i;
		buyerName[count] = b;
		unitCost[count] = q;
	}

	public String getItemName(int index) {
		return itemName[index];
	}

	public void setItemName(String n, int index) {
		itemName[index] = n;
	}

	public String getBuyerName(int index) {
		return buyerName[index];
	}

	public void setBuyerName(String b, int index) {
		buyerName[index] = b;
	}

	public double getUnitCost(int index) {
		return unitCost[index];
	}

	public void setUnitCost(double u, int index) {
		unitCost[index] = u;
	}

	public int getItemQuantity(int index) {
		return itemQuantity[index];
	}

	public void setItemQuantity(int q, int index) {
		itemQuantity[index] = q;
	}

	public void readInput() throws IOException {
		Scanner in = new Scanner(new FileReader("listToShop.txt"));
		count = 0;
		while (in.hasNext()) {
			itemName[count] = in.next();
			itemQuantity[count] = in.nextInt();
			unitCost[count] = in.nextDouble();
			count++;
		}
		in.close();
	}
	/* kat sini */

	public void addRecentlyBuyer(RecentlyBuyer b) {
		buyer[count] = b;
		count++;

	}

	public void myShoppingListInterface() { // Display all previous item to shop
		System.out.print("\n\n");
		System.out.print("Shopping List\n\n");
		try {
			readInput();
		} catch (IOException ex) {
			System.out.print("\nError in getting input!\n\n");
		}

		System.out.printf("%40s", "To Shop Item\n\n");
		System.out.printf("%20s", "No");
		System.out.printf("%-5s", ".");
		System.out.printf("%-10s", "Item");
		System.out.printf("%5s", "Quantity");
		System.out.printf("%5s", "");
		System.out.printf("%-5s", "Price");
		System.out.printf("%9s", "Total");

		System.out.print("\n");
		System.out.printf("%5s%20s", "", "___________________________________________________________________\n");
		for (int i = 0; i < count; i++) {
			System.out.printf("%20s", i + 1);
			System.out.printf("%-4s", ".");
			System.out.printf("%-15s", itemName[i]);
			System.out.printf("%-5s", itemQuantity[i]);
			System.out.printf("%6s", "RM");
			System.out.printf("%-5s", unitCost[i]);
			System.out.printf("%4s", "RM");
			System.out.printf("%.2f", (unitCost[i] * itemQuantity[i]));
			System.out.print("\n");
		}
		System.out.printf("%5s%20s", "", "___________________________________________________________________\n");
	}

	public void writeItem() throws IOException { // add new item into to shop item
		String type1;
		int quantity1;
		double price1;
		String tempEnter;
		String enterType;
		int enterQuantity;
		String enterPrice;

		String filename = "listToShop.txt"; // act as database for previous item to shop
		FileWriter fw = new FileWriter(filename, true);

		enterType = JOptionPane.showInputDialog("Enter item name"); // input data from user
		tempEnter = JOptionPane.showInputDialog("Enter item quantity");
		enterQuantity = Integer.parseInt(tempEnter);
		enterPrice = JOptionPane.showInputDialog("Enter item per unit price");

		fw.write("\r\n");
		fw.write(enterType + "\t");
		fw.write(enterQuantity + "\t");
		fw.write(enterPrice);
		fw.close();
	}

	public void addBuyer(RecentlyBuyer b) {
		buyer[count] = b;

	}

	public void removeItem() throws IOException { // remove item to shop ( referring to user has bought the item )
		System.out.print("\n\n");
		System.out.print("Shopping List\n\n");
		String tempRemove;
		int removeItem;

		try {
			readInput();
		} catch (IOException ex) {
			System.out.print("\nError in getting input!\n\n");
		}

		PrintWriter fw = new PrintWriter("listToShop.txt");
		tempRemove = JOptionPane.showInputDialog("Enter item number bought (e.g. : 1) ");
		removeItem = Integer.parseInt(tempRemove);

		buyer[count] = new RecentlyBuyer("anonymous", itemName[removeItem - 1]); // THIS PART - member name will be
																					// inherit from "member" class

		for (int i = 0; i < count; i++) {
			if ((i + 1) == removeItem) {
			} // will skip for "i" index - act as to delete item
			else {
				fw.print(itemName[i] + "\t");
				fw.print(itemQuantity[i] + "\t");
				fw.print(unitCost[i]);
				fw.print("\r\n");
			}
		}
		fw.close();
		JOptionPane.showMessageDialog(null, itemName[removeItem - 1] + " has been removed");
	}
}

class clearScreen { // Clear Screen
	public static void clrscr() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
		}
	}
}

class MealPlanner {
	private String[] day = new String[20];
	int[] option = new int[20];
	String[] breakfast = new String[20];
	String[] lunch = new String[20];
	String[] dinner = new String[20];
	int[] voters = new int[20];
	static int count = 0;

	public MealPlanner() {
	}

	public MealPlanner(String d, int o, String bre, String lun, String din, int v) {
		day[count] = d;
		option[count] = o;
		breakfast[count] = bre;
		lunch[count] = lun;
		dinner[count] = din;
		voters[count] = v;
	}

	public String getDay(int index) {
		return day[index];
	}

	public void setDay(String d, int index) {
		day[index] = d;
	}

	public String getBreakfast(int index) {
		return breakfast[index];
	}

	public void setBreakfast(String b, int index) {
		breakfast[index] = b;
	}

	public String getLunch(int index) {
		return lunch[index];
	}

	public void setLunch(String b, int index) {
		lunch[index] = b;
	}

	public String getDinner(int index) {
		return dinner[index];
	}

	public void setDinner(String d, int index) {
		dinner[index] = d;
	}

	public void readInput() throws IOException {
		count = 0;
		Scanner input = new Scanner(new FileReader("dayMeal.txt"));
		while (input.hasNext()) {
			day[count] = input.next();
			option[count] = input.nextInt();
			breakfast[count] = input.next();
			lunch[count] = input.next();
			dinner[count] = input.next();
			voters[count] = input.nextInt();
			count++;
		}
		input.close();
	}

	public void mealPlannerInterface() throws IOException {
		try {
			readInput();
		} catch (IOException ex) {
			System.out.print("\nError in getting input!\n\n");
		}

		System.out.print("\n\n");
		System.out.printf("%40s", "Meal Planner");
		for (int i = 0; i < count; i++) {
			System.out.print("\n\n");
			System.out.printf("%5s%20s", "=", "====================================================\n");
			System.out.printf("%5s%2s%-39s", "=", "", day[i]);
			System.out.printf("%-8s%3s", "Vote: " + voters[i], "=");
			System.out.print("\n");
			System.out.printf("%5s%2s%-7s%-3s%40s", "=", "", "Option ", option[i], "=");
			System.out.print("\n");
			System.out.printf("%5s%13s%-12s%-25s%2s", "=", "", "Breakfast: ", breakfast[i], "=");
			System.out.print("\n");
			System.out.printf("%5s%13s%-12s%-25s%2s", "=", "", "Lunch: ", lunch[i], "=");
			System.out.print("\n");
			System.out.printf("%5s%13s%-12s%-25s%2s", "=", "", "Dinner: ", dinner[i], "=");
			System.out.print("\n\n");
			// System.out.printf("%5s%20s","","____________________________________________________\n");
			System.out.printf("%5s%20s", "=", "====================================================");
		}
	}

	public void addMenu() throws IOException {
		String tempEnter;
		String dayEnter;
		Scanner in = new Scanner(System.in);
		mealPlannerInterface();

		try {
			readInput();
		} catch (IOException ex) {
			System.out.print("\nError in getting input!\n\n");
		}
		System.out.print("\nEnter Menu Day (e.g: Tuesday): ");

		dayEnter = in.next();
		for (int i = 0; i < 2; i++) {
			JOptionPane.showMessageDialog(null, "Menu for Option " + (i + 1));
			day[count] = dayEnter;
			System.out.print("\nOption " + (i + 1) + ": \n");
			option[count] = i + 1;
			System.out.print("Enter Breakfast dish: ");
			breakfast[count] = in.next();
			System.out.print("Enter Lunch dish: ");
			lunch[count] = in.next();
			System.out.print("Enter Dinner dish: ");
			dinner[count] = in.next();
			voters[count] = 0;
			count += 1;
		}

		PrintWriter fw = new PrintWriter("dayMeal.txt");
		for (int i = 0; i < count; i++) {
			fw.print(day[i] + "\t" + option[i] + "\t" + breakfast[i] + "\t" + lunch[i] + "\t" + dinner[i] + "\t"
					+ voters[i]);
			fw.print("\r\n");
		}
		fw.close();
	}

	public void addVoter() throws IOException {
		try {
			readInput();
		} catch (IOException ex) {
			System.out.print("\nError in getting input!\n\n");
		}

		String tempOptionDay;
		int optionDay;
		String dayEnter;

		dayEnter = JOptionPane.showInputDialog("Which day (e.g : Tuesday)");
		tempOptionDay = JOptionPane.showInputDialog("Which option (1/2)");
		optionDay = Integer.parseInt(tempOptionDay);

		for (int i = 0; i < count; i++) { // add voter to meal option
			if ((day[i].toUpperCase()).equals(dayEnter.toUpperCase())) {
				if (optionDay == option[i]) {
					voters[i] += 1;
				}
			}
		}

		PrintWriter fw = new PrintWriter("dayMeal.txt");
		for (int i = 0; i < count; i++) {
			fw.print(day[i] + "\t" + option[i] + "\t" + breakfast[i] + "\t" + lunch[i] + "\t" + dinner[i] + "\t"
					+ voters[i]);
			fw.print("\r\n");
		}

		fw.close(); // close for PrintWriter
	}
}



public class oop {
    public static void main(String[] a)throws IOException {
        // ToDo t = new ToDo("Working", "Good", 12, "dec", 2019);
        // Member dad = new Member("Pop", "father", 45, 12, "dec", 1990);
        // t.addMember(dad);
        // t.toString();
        // dad.setHealthRecord(12, 23, 24, 170, 88, "12 dec 2017", "12 12 2017", "12 12 2088");
        // System.out.println(dad.getHealthRecord().toString());
        // dad.setMedicalRecord("Good", "Peanut allergy", "Dr M", "23 12 1956", "1 1 2011", "999", "3 3 2003",
        //         "12 12 2012", "12 12 1991");
        // System.out.println(dad.getMedicalRecord().toString());
        // FitnessActivity f=new FitnessActivity(12, 22, 33, 55, "Be Healthy Plan");
        // f.addMember(dad);
        // f.showJoinedMember();

        clearScreen clear = new clearScreen();

		ShoppingItem s1 = new ShoppingItem();
		RecentlyBuyer b1 = new RecentlyBuyer();
		Scanner input = new Scanner(System.in);

		int ans = 0;
		int mainAns;
		int mainMenu;

		do {
			clear.clrscr();
			System.out.print("\n\n");
			System.out.printf("%35s", "MODULE");
			System.out.print("\n\n");
			System.out.printf("%50s", "Enter '1' to enter shopping items module");
			System.out.print("\n");
			System.out.printf("%50s", "Enter '2' to enter meal planner module");
			System.out.print("\n\n");
			System.out.printf("%18s", "Your option: ");
			mainAns = input.nextInt();

			if (mainAns == 1) { // go to Shopping module

				do {
					clear.clrscr();
					s1.myShoppingListInterface();
					System.out.print("\n\n");
					b1.recentlyBuyInterface();

					System.out.print("\n\n");
					System.out.printf("%20s%-20s", "", "Enter '1' to add new Item\n");
					System.out.printf("%20s%-20s", "", "Enter '2' to remove item's buy\n");
					System.out.printf("%20s%-20s", "", "Enter '0' to exit to main module\n");

					System.out.print("\n\n");
					System.out.print("Choose option: ");

					ans = input.nextInt();

					if (ans == 1) {
						s1.writeItem();
					}
					if (ans == 2) {
						s1.removeItem();
					}

					System.out.print("\n\n");
				} while (ans != 0);
			}

			if (mainAns == 2) { // go to MealPlanner module
				clear.clrscr();
				MealPlanner s2 = new MealPlanner();
				int account;

				System.out.print("\n");
				System.out.print("\tPlease choose account>\n");
				System.out.print("\t\t1-Admin 2-Member :");// Admin account can add menu, Member account can vote
				account = input.nextInt();

				do {
					clear.clrscr();
					s2.mealPlannerInterface();
					System.out.print("\n\n");
					if (account == 1) { // admin account can add new menu
						System.out.printf("%20s%-20s", "", "Enter '1' to add Menu\n");
						System.out.printf("%20s%-20s", "", "Enter '0' to exit to main module\n");
					} else { // member account can cote
						System.out.printf("%20s%-20s", "", "Enter '1' to vote for food\n");
						System.out.printf("%20s%-20s", "", "Enter '0' to exit to main module\n");
					}

					System.out.print("\n\n");
					System.out.print("Choose option: ");
					ans = input.nextInt();
					if (ans == 1 && account == 1) {
						s2.addMenu();
					} // admin account
					if (ans == 1 && account == 2) {
						s2.addVoter();
					} // member account

					System.out.print("\n\n");
				} while (ans != 0);// will return to same menu, if user enter '0', go to main menu
			}
			mainMenu = ans;
		} while (mainMenu == 0);// return to main menu if user enter '0'



    }
}