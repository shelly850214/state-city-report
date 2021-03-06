import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Created by Shelly on 2017/3/10.
 */
public class Driver {
    public static void main(String[] args) throws Exception {
        //local constants
        final int QUIT = 0;
        final int READ_FILE = 1;
        final int MOD_STATE = 2;
        final int MOD_CITY = 3;
        final int REMOVE_STATE = 4;
        final int REMOVE_CITY = 5;
        final int SORT_STATES = 6;
        final int SORT_CITIES = 7;
        final int PRODUCE_REPORT = 8;

        //local variables
        int choice;
        int numStates = 0;
        stateInfo[] states = new stateInfo[50];

        /********************************************/
        choice = menu();
        while (choice != QUIT) {
            if (choice == READ_FILE) {
                numStates = readFile(numStates,states);
            } else if (choice == MOD_STATE) {
                modState(numStates,states);
            } else if (choice == MOD_CITY) {
                modCity(numStates,states);
            } else if (choice == REMOVE_STATE){
                numStates = rmvState(numStates,states);
            } else if (choice == REMOVE_CITY) {
                rmvCity(numStates,states);
            } else if (choice == SORT_STATES) {
                sortState(numStates,states);
            } else if (choice == SORT_CITIES) {
                sortCity(numStates,states);
            } else if (choice == PRODUCE_REPORT) {
                proReport(numStates,states);
            } else {
                System.out.println("\t\t\tYour choice is invalid. Please enter again.");
            }
            choice = menu();
        }
        System.out.println("\t\tThe program is closed. Have a nice day.");
    }

    public static int menu(){
        int choice;
        System.out.println("\n\n\t\tHello, menu is below, please input your choice:");
        System.out.println("\t\t1. READ A FILE.");
        System.out.println("\t\t2. MODIFY STATE INFORMATION.");
        System.out.println("\t\t3. MODIFY CITY INFORMATION.");
        System.out.println("\t\t4. REMOVE STATE.");
        System.out.println("\t\t5. REMOVE CITY.");
        System.out.println("\t\t6. SORT STATES.");
        System.out.println("\t\t7. SORT CITIES.");
        System.out.println("\t\t8. PRODUCE REPORTS.");
        System.out.print("\t\t0. QUIT.\n\t\t");
        choice = Keyboard.readInt();

        return choice;
    }

    public static int readFile(int numStates, stateInfo states[]) throws IOException {
        final String QUIT = "-1";

        BufferedReader br;
        String fileName;
        String line;
        String cityName;
        int pop;
        StringTokenizer token;

        System.out.print("\t\tEnter the file's name (or -1 to quit): ");
        fileName = Keyboard.readString();

        try {
            while (!(fileName.equals(QUIT))) {
                br = new BufferedReader(new FileReader(fileName));
                if (new File(fileName).exists()) {
                    line = br.readLine();
                    while (line != null) {
                        token = new StringTokenizer(line, "\t");
                        if (token.countTokens() == 1) {
                            numStates++;
                            states[numStates-1] = new stateInfo(token.nextToken());
                        } else if (token.countTokens() == 2) {
                            cityName = token.nextToken();
                            try {
                                pop = Integer.parseInt(token.nextToken());
                                states[numStates-1].addCities(cityName, pop);
                            } catch (Exception e) {
                                System.out.println("\t\tPopulation has to be numeric.\n");
                            }
                        }
                        line = br.readLine();
                    }
                    System.out.println("\t\tReading file is success.\n");
                } else {
                    System.out.println("\t\tThe file is not found.\n");
                }
                System.out.print("\t\tEnter the file's name (or -1 to quit): ");
                fileName = Keyboard.readString();
            }
        } catch (Exception e) {
            System.out.println("\t\tThe file is not found.\n");
        }
        return numStates;
    }

    public static void listStates(int numState, stateInfo states[]){
        System.out.println("\n\n\t\tThe list of States:");
        for(int i = 0; i < numState; i++){
            System.out.println("\t\t" + (i+1) + ". " + states[i].getStateName() + ", " + states[i].getStatePop());
        }
        System.out.println("\n");
    }

    public static void modState(int numState, stateInfo states[]){
        final int QUIT = -1;

        int choice;
        String newName;
        if (numState > 0 ) {
            listStates(numState, states);
            System.out.print("\t\tEnter the choice of the State you want to modify (or -1 to QUIT): ");
            choice = Keyboard.readInt();
            while (choice != QUIT) {
                if (0 < choice && choice <= numState) {
                    System.out.print("\t\tEnter the name will be changed to: ");
                    newName = Keyboard.readString();
                    if (newName != null) {
                        states[choice - 1].setStateName(newName);
                        System.out.println("\t\tChanging name is success.\n");
                    } else {
                        System.out.println("\t\tThe name is invalid.");
                    }
                } else {
                    System.out.println("\t\tThe choice is invalid.");
                }
                listStates(numState, states);
                System.out.print("\t\tEnter the choice of the State you want to modify (or -1 to QUIT): ");
                choice = Keyboard.readInt();
            }
        } else {
            System.out.println ("\t\tThere's no States in the file.");
        }
    }

    public static void modCity(int numState, stateInfo states[]){
        final int QUIT = -1;

        int choice;
        int cityChoice;
        String cityName;
        int cityPop;
        String newName;
        int newPop = 0;
        boolean result;

        if (numState > 0 ) {
            listStates(numState, states);
            System.out.print("\t\tEnter the choice of the State you want to modify (or -1 to QUIT): ");
            choice = Keyboard.readInt();
            while (choice != QUIT) {
                if (choice <= numState && 0 < choice) {
                    System.out.print(states[choice - 1].toString());
                    System.out.print("\n\t\tEnter the choice of the City you want to modify (or -1 to QUIT): ");
                    cityChoice = Keyboard.readInt();
                    while (cityChoice != QUIT) {
                        if (cityChoice <= states[choice - 1].getNumCities() && 0 < cityChoice) {
                            cityName = states[choice - 1].getCity(cityChoice).getCityName();
                            cityPop = states[choice - 1].getCity(cityChoice).getCityPop();

                            System.out.print("\n\t\tEnter the name of the City will be changed to: ");
                            newName = Keyboard.readString();
                            if (newName != null) {
                                cityName = newName;
                                System.out.print("\n\t\tEnter the population of the City will be changed to: ");
                                newPop = Keyboard.readInt();
                                if (newPop >= 0) {
                                    cityPop = newPop;
                                    result = states[choice - 1].modifyCity(cityName, cityPop, cityChoice - 1);
                                    if (result == true) {
                                        System.out.println("\t\tChanging name is success.\n");
                                    } else {
                                        System.out.println("\t\tChanging name failed.\n");
                                    }
                                } else {
                                    System.out.println("\t\tThe population is invalid.\n");
                                }
                            } else {
                                System.out.println("\t\tThe name is invalid.\n");
                            }
                        } else {
                            System.out.println("\t\tThe modification is invalid.\n");
                        }
                        System.out.print(states[choice - 1].toString());
                        System.out.print("\t\tEnter the choice of the City you want to modify (or -1 to QUIT): ");
                        cityChoice = Keyboard.readInt();
                    }
                } else {
                    System.out.println("\t\tThe choice is invalid.\n");
                }
                listStates(numState, states);
                System.out.print("\t\tEnter the choice of the State you want to modify (or -1 to QUIT): ");
                choice = Keyboard.readInt();
            }
        } else {
            System.out.println("\t\tThere's no States in the file.\n");
        }
    }

    public static int rmvState(int numState, stateInfo states[]){
        final int QUIT = -1;

        int choice;
        if (numState > 0 ) {
            listStates(numState, states);
            System.out.print("\t\tEnter the choice of the State you want to remove(or -1 to QUIT): ");
            choice = Keyboard.readInt();
            while (choice != QUIT) {
                if (choice <= numState && choice > 0) {
                    states[choice - 1] = null;
                    for (int i = choice; i < numState; i++) {
                        states[i - 1] = states[i];
                    }
                    numState--;
                    System.out.println("\t\tThe State is removed.");
                } else {
                    System.out.println("\t\tThe choice is invalid.");
                }
                listStates(numState, states);
                System.out.print("\t\tEnter the choice of the State you want to remove(or -1 to QUIT): ");
                choice = Keyboard.readInt();
            }
        } else {
            System.out.println("\t\tThere's no States in the file.");
        }
        return numState;
    }

    public static void rmvCity(int numState, stateInfo states[]){
        final int QUIT = -1;

        int choice;
        int cityChoice;

        if (numState > 0 ) {
            listStates(numState, states);
            System.out.print("\t\tEnter the choice of the State where the city at that you want to remove(or -1 to QUIT): ");
            choice = Keyboard.readInt();
            while (choice != QUIT) {
                if (choice <= numState && choice > 0) {
                    System.out.print(states[choice - 1].toString());
                    System.out.print("\t\tEnter the choice of the City you want to remove (or -1 to QUIT): ");
                    cityChoice = Keyboard.readInt();
                    while (cityChoice != QUIT) {
                        if (cityChoice <= states[choice - 1].getNumCities() && 0 < cityChoice) {
                            states[choice - 1].removeCities(cityChoice);
                            System.out.println("\t\tCity is removed.");
                        } else {
                            System.out.println("\t\tThe choice is invalid.");
                        }
                        System.out.print(states[choice - 1].toString());
                        System.out.print("\t\tEnter the choice of the City you want to remove (or -1 to QUIT): ");
                        cityChoice = Keyboard.readInt();
                    }
                } else {
                    System.out.println("\t\tThe choice is invalid.");
                }
                listStates(numState, states);
                System.out.print("\t\tEnter the choice of the State where the city at that you want to remove(or -1 to QUIT): ");
                choice = Keyboard.readInt();
            }
        } else {
            System.out.println("\t\tThere's no States in the file.");
        }
    }

    public static void sortState(int numState, stateInfo states[]) {
        int choice;
        if (numState > 0 ) {
            System.out.println("\t\t1. SORT BY NAME.");
            System.out.println("\t\t2. SORT BY POPULATION.");
            System.out.print("\t\tWhat to sort by: ");
            choice = Keyboard.readInt();
            stateInfo temp[] = new stateInfo[numState];
            for (int i = 0; i < numState; i++){

                temp [i] = states[i];
            }
            if (numState > 0) {
                if (choice == 1) {
                    System.out.print("\n\t\tBefore sorting:");
                    listStates(numState, states);
                    Arrays.sort(temp, Comparator.comparing(state -> state.stateName));
                    for (int i = 0; i < numState; i++) {
                        states [i] = temp[i];
                    }
                    System.out.println("\t\tSorting method is success.");
                    System.out.print("\n\t\tAfter sorting:");
                    listStates(numState, states);
                } else if (choice == 2) {
                    System.out.print("\n\t\tBefore sorting:");
                    listStates(numState, states);
                    Arrays.sort(temp, Comparator.comparing(state -> state.statePop));
                    for (int i = 0; i < numState; i++) {
                        states [i] = temp[i];
                    }
                    System.out.print("\n\t\tAfter sorting:");
                    listStates(numState, states);
                } else {
                    System.out.println("\t\tInvalid choice.");
                }
            } else {
                System.out.println("\t\tThere's no States in the file.");
            }
        } else {
            System.out.println("\t\tThere's no States in the file.");
        }
    }

    public static void sortCity(int numState, stateInfo states[]) {
        final int QUIT = -1;

        int choice;
        int stateChoice;

        if (numState > 0 ) {
            listStates(numState, states);
            System.out.print("\t\tEnter the choice of the State of the cities that you want to sort by (or -1 to QUIT): ");
            stateChoice = Keyboard.readInt();

            while (stateChoice != QUIT) {
                if (stateChoice < numState && 0 < stateChoice) {
                    System.out.println("\t\tSORT BY (or -1 to QUIT): ");
                    System.out.println("\t\t1. SORT BY NAME.");
                    System.out.println("\t\t2. SORT BY POPULATION.");
                    choice = Keyboard.readInt();
                    states[stateChoice-1].sortCities(choice);
                } else {
                    System.out.println("\t\tInvalid choice.");
                }
                listStates(numState, states);
                System.out.print("\t\tEnter the choice of the State of the cities that you want to sort by (or -1 to QUIT): ");
                stateChoice = Keyboard.readInt();
            }
        } else {
            System.out.println("\t\tThere's no States in the file.");
        }
    }

    public static void proReport(int numState, stateInfo states[]) throws IOException {
        final String QUIT = "-1";

        String fileName;
        BufferedWriter bw;
        if (numState > 0 ) {

            System.out.print("\t\tEnter the file's name (or -1 to quit): ");
            fileName = Keyboard.readString();
            bw = new BufferedWriter(new FileWriter(fileName));
            if (fileName != QUIT) {
                for (int i = 0; i < numState; i++) {
                    bw.write("\r\n\t\t" + states[i].getStateName() + "\t\t" + states[i].getStatePop() + "\r\n");
                    bw.write(states[i].toString());
                }
                bw.close();
                System.out.println("\t\tThe final report file is produced.");
            }
        } else {
            System.out.println("\t\tThere's no States in the file.");
        }
    }
}
