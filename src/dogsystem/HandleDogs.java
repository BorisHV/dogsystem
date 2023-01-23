
package dogsystem;

/**
 * @author Boris Holmgren boho8503
 */
import java.util.*;

public class HandleDogs {

    private final Scanner
            input = new Scanner(System.in);

    private final Random rnd = new Random();
    private final ArrayList<Dog> dogs = new ArrayList<>();
    private final DogSorter sorter = new DogSorter();
    private final ArrayList<User> users = new ArrayList<>();
    private boolean terminateProgram;
    private final ArrayList<Auction> auctions = new ArrayList<>();
    private int nextAuctionNumber = 1;

    public static void main(String[] args) {
        HandleDogs program = new HandleDogs();
        program.run();
    }
    private void startUp() {
        System.out.println("Hej och välkommen till hundprogrammet!");
        printMenu();
    }
    private void printMenu() {
        System.out.println("Write shorthand rnd for register new dog eg and press enter.\nFollowing options are available: ");
        System.out.println("register new dog (rnd)");
        System.out.println("register new user (rnu)");
        System.out.println("give dog (gd)");
        System.out.println("start auction (sa)");
        System.out.println("increase age (ia)");
        System.out.println("list dogs (ld)");
        System.out.println("list users (lu)");
        System.out.println("list auctions (la)");
        System.out.println("list bids (lb)");
        System.out.println("close auction (ca)");
        System.out.println("make bid (mb)");
        System.out.println("remove dog (rd)");
        System.out.println("remove user (ru)");
        System.out.println("exit (e)");
    }
    private void closeDown() {
        System.out.println("Hej då!");
        input.close();
    }
    public void run() {
        startUp();
        runCommandLoop();
        closeDown();
    }
    private void runCommandLoop() {
        String command;
        do {
            command = readCommand();
            handleCommand(command);
        } while (!command.equals("exit") && !command.equals("e") && !terminateProgram);
    }
    private String readCommand() {
        System.out.print("?> ");
        String command = input.nextLine();
        return command;
    }
    private void addNewDog() {
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Name?>");
            name = input.nextLine().trim();
            if(name.isEmpty()) {
                System.out.println("Error: the name can't be empty");
            }
        }
        String breed = "";
        System.out.print("Breed?>");
        breed = input.nextLine().trim();
        if(breed.isEmpty()) {
            System.out.println("Error: the breed can't be empty");
            return;
        }
        System.out.print("Age?>");
        int age = Integer.parseInt(input.nextLine().trim());
        System.out.print("Weight?>");
        int weight = Integer.parseInt(input.nextLine().trim());
        Dog dogToAdd = new Dog(name, breed, age, weight);
        dogs.add(dogToAdd);
        System.out.println(dogToAdd + " added to the register");
    }
    private void addNewUser() {
        String nameOfUserToAdd = "";
        System.out.print("Name?>");
        nameOfUserToAdd = input.nextLine().trim();
        if(nameOfUserToAdd.isEmpty()) {
            System.out.println("Error: the name can't be empty");
            return;
        }
        User usertoadd = new User(nameOfUserToAdd);
        users.add(usertoadd);
        System.out.println(nameOfUserToAdd + " added to the register");
    }
    private void listUsers() {
        if (users.isEmpty()) {
            System.out.println("Error: no users in register");
        } else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).toString());
            }
        }
    }
    private void listDogs() {
        if  (dogs.isEmpty()) {
            System.out.println("Error: no dogs in register");
        } else {
            System.out.println("Smallest tail length to display?>");
            int smallestTailLength = Integer.parseInt(input.nextLine());
            sorter.sort(dogs);
            for (int i = 0; i < dogs.size(); i++) {
                Dog dog = dogs.get(i);
                if (dog.getTailLength() >= smallestTailLength) {
                    System.out.println (dog.toString());
                }
            }
        }
    }
    private void giveDog() {
        String nameOfDog = "";
        Dog selectedDog = null;
        System.out.print("Enter the name of the dog?>");
        nameOfDog = input.nextLine().trim();
        selectedDog = findDog(nameOfDog);
        if (nameOfDog.equalsIgnoreCase("exit") || nameOfDog.equalsIgnoreCase("e")) {
            terminateProgram = true;
            return;
        }
        if(nameOfDog.isEmpty()) {
            System.out.println("Error: the name can't be empty");
            return;
        }
        else if(selectedDog == null) {
            System.out.println("Error: no such dog");
            return;
        }
        else if(selectedDog.isOwned()) {
            System.out.println("Error: Dog is already owned");
            return;
        }
        User selectedUser = null;
        String nameOfOwner = "";
        System.out.print("Enter the name of the new owner?>");
        nameOfOwner = input.nextLine().trim();
        selectedUser = findUser(nameOfOwner);
        if(nameOfOwner.isEmpty()) {
            System.out.println("Error");
            return;
        }
        else if(selectedUser == null) {
            System.out.println("Error: no such user");
            return;
        }
        selectedDog.setOwner(selectedUser);
        selectedUser.setDog(selectedDog);
        System.out.println(nameOfOwner + " now owns " + nameOfDog);
    }
    /**
     * If dog exists return it else return null.
     * @param name
     * @return
     */
    private Dog findDog(String name) {
        Dog foundDog = null;
        for (int i = 0; i < dogs.size(); i++) {
            Dog dogAtPointer = dogs.get(i);
            if (dogAtPointer.getName().equalsIgnoreCase(name)) {
                foundDog = dogAtPointer;
            }
        }
        return foundDog;
    }
    private User findUser(String name) {
        User foundUser = null;
        for (int i = 0; i < users.size(); i++) {
            User userAtPointer = users.get(i);
            if (userAtPointer.getName().equalsIgnoreCase(name)) {
                foundUser = userAtPointer;
            }
        }
        return foundUser;
    }
    /**
     * Den här metoden används för att hitta en auktion med en viss hund i.
     * Finns ingen sån auktion så returnerar vi null.
     * Annars den auktionen som innehåller hunden.
     * @param dogWeAreLookingFor
     * @return
     */
    private Auction findAuctionWithDog(Dog dogWeAreLookingFor) {
        Auction foundAuction = null;
        for (int i = 0; i < auctions.size(); i++ ) {
            Auction auctionAtPointer = auctions.get(i);
            if (auctionAtPointer.getDogToBeAuctioned() == dogWeAreLookingFor) {
                foundAuction = auctionAtPointer;
            }
        }
        return foundAuction;
    }
    private void removeUser() {
        User selectedUser = null;
        String nameOfUserYouWantToRemove = "";
        System.out.print("Enter the name of the user?>");
        nameOfUserYouWantToRemove = input.nextLine().trim();
        selectedUser = findUser(nameOfUserYouWantToRemove);
        if(nameOfUserYouWantToRemove.isEmpty()) {
            System.out.println("Error");
            return;
        }
        else if(selectedUser == null) {
            System.out.println("Error: no such user");
            return;
        }
        Dog[] dogsToRemove = selectedUser.getOwnedDogs();
        for (int i = 0; i<dogsToRemove.length; i++) {
            dogs.remove(dogsToRemove[i]);
        }
        for (int i = 0; i < auctions.size(); i++) {
            auctions.get(i).removeBidsFromUser(selectedUser);
        }
        users.remove(selectedUser);
        System.out.println(nameOfUserYouWantToRemove + " is removed from the register");
    }
    private void increaseAge() {
        System.out.println("Enter the name of the dog?>");
        String dogNameUserIsLookingFor = input.nextLine();
        dogNameUserIsLookingFor = dogNameUserIsLookingFor.trim();
        boolean hasFoundDog = false;
        for (int i = 0; i < dogs.size(); i++) {
            Dog dogAtPointer = dogs.get(i);
            if (dogAtPointer.getName().equalsIgnoreCase(dogNameUserIsLookingFor)) {
                hasFoundDog = true;
                dogAtPointer.increaseAge();
                System.out.println(dogNameUserIsLookingFor + " is now one year older");
            }
        }
        if (!hasFoundDog) {
            System.out.println("Error: no such dog");
        }
    }
    private void startAuction() {
        System.out.println("Enter the name of the dog?>");
        String dogNameToBidOn = input.nextLine().trim();
        Dog selectedDog = findDog(dogNameToBidOn);
        if(dogNameToBidOn.isEmpty()) {
            System.out.println("Error: the name can't be empty");
            return;
        }
        else if(selectedDog == null) {
            System.out.println("Error: no such dog");
            return;
        }
        else if(selectedDog.isOwned()) {
            System.out.println("Error: Dog is already owned");
            return;

        }
        else if(findAuctionWithDog(selectedDog) != null) {
            System.out.println("Error: this dog is already up for auction");
            return;
        }
        Auction auctionToAdd = new Auction(selectedDog, nextAuctionNumber);
        auctions.add(auctionToAdd);
        nextAuctionNumber++;
        System.out.println(selectedDog.getName() + " has been put up for auction in auction #"+auctionToAdd.getAuctionNumber());
    }
    private void listAuctions() {
        boolean allAuctionsClosed = true;
        if (auctions.isEmpty()) {
            System.out.println("Error: no such auction");
            return;
        }
        for (int i = 0; i < auctions.size(); i++) {
            Auction auctionAtPointer = auctions.get(i);
            if (!auctionAtPointer.isAuctionClosed()) {
                System.out.println(auctionAtPointer.toString());
                allAuctionsClosed = false;
            }
        }
        if (allAuctionsClosed) {
            System.out.println("Error: no such auction");
        }
    }
    private void listBids() {
        System.out.println("Enter the name of the dog?>");
        String dogWeAreLookingFor = input.nextLine().trim();
        if (dogWeAreLookingFor.isEmpty()) {
            System.out.println("Error: name can´t be empty");
            return;
        }
        Dog selectedDog = findDog(dogWeAreLookingFor);
        if (selectedDog == null) {
            System.out.println("Error: no such dog");
            return;
        }
        Auction selectedAuction = findAuctionWithDog(selectedDog);
        if (selectedAuction == null) {
            System.out.println("Error: this dog is not up for auction");
            return;
        }
        if (!selectedAuction.hasBids()) {
            System.out.println("No bids registered for this auction");
            return;
        }
        selectedAuction.printBids();
    }
    private void closeAuction() {
        System.out.println("Enter the name of dog?>");
        String nameOfDog = input.nextLine().trim();
        if (nameOfDog.isEmpty()) {
            System.out.println("Error: no such dog");
            return;
        }
        Dog selectedDog = findDog(nameOfDog);
        if (selectedDog == null) {
            System.out.println("Error: no such dog");
            return;
        }
        Auction selectedAuction = findAuctionWithDog(selectedDog);
        if (selectedAuction == null) {
            System.out.println("Error: This dog is not up for auction");
            return;
        }
        if (selectedDog.isOwned()) {
            System.out.println("Error: this dog is not up for auction");
            return;
        }
        if (selectedAuction.hasBids()) {
            System.out.println("The auction is closed. The winning bid was " + selectedAuction.currentHighestBid()+ "kr and was made by "+selectedAuction.currentHighestBidder().getBidderName());
            selectedDog.setOwner(selectedAuction.currentHighestBidder().getBidder());
            selectedAuction.currentHighestBidder().getBidder().setDog(selectedDog);
        }
        else {
            System.out.println("The auction is closed. There were no bids for "+ nameOfDog);
        }
        selectedAuction.closeAuction();
    }
    private void removeDog() {
        System.out.println("Enter the name of the dog?>");
        String dogNameUserIsLookingFor = input.nextLine();
        dogNameUserIsLookingFor = dogNameUserIsLookingFor.trim();
        int hasFoundDogIndex = -1;
        for (int i = 0; i < dogs.size(); i++) {
            Dog dogAtPointer = dogs.get(i);
            if (dogAtPointer.getName().equalsIgnoreCase(dogNameUserIsLookingFor)) {
                hasFoundDogIndex = i;
            }
        }
        if (hasFoundDogIndex == -1) {
            System.out.println("Error: no such dog");
        }else {
            Dog dogToRemove = dogs.get(hasFoundDogIndex);
            User dogOwner = dogToRemove.getOwner();
            if (dogOwner != null) {
                dogOwner.removeDog(dogToRemove);
            }
            Auction selectedAuction = findAuctionWithDog(dogToRemove);
            if (selectedAuction != null) {
                auctions.remove(selectedAuction);
            }
            dogs.remove(hasFoundDogIndex);
            System.out.println(dogNameUserIsLookingFor + " is removed from the register");
        }
    }
    private void makeBid() {
        System.out.println("Enter the name of the user?>");
        String nameOfUser = input.nextLine().trim();
        if (nameOfUser.isEmpty()) {
            System.out.println("Error: name can´t be empty");
            return;
        }
        User selectedUser = findUser(nameOfUser);
        if (selectedUser == null) {
            System.out.println("Error: no such user");
            return;
        }
        System.out.println("Enter name of dog?>");
        String nameOfDog = input.nextLine().trim();
        if (nameOfDog.isEmpty()) {
            System.out.println("Error: name can't be empty");
            return;
        }
        Dog selectedDog = findDog(nameOfDog);
        if (selectedDog == null) {
            System.out.println("Error: no such dog");
            return;
        }
        Auction selectedAuction = findAuctionWithDog(selectedDog);
        if (selectedAuction == null) {
            System.out.println("Error: Dog is nor for sale");
            return;
        }
        if (selectedDog.isOwned()) {
            System.out.println("Error: this dog is not up for auction");
            return;
        }
        int highestBid = selectedAuction.currentHighestBid();
        int bid = -1;
        while (bid <= highestBid) {
            System.out.println("Amount to bid (min " + (highestBid+1)+")?>");
            bid = Integer.parseInt(input.nextLine().trim());
            if (bid <= highestBid) {
                System.out.println("Error: bid to low!");
            }
        }
        Bid bidMade = new Bid(bid, selectedUser);
        selectedAuction.addBid(bidMade);
        System.out.println("Bid made");
    }
    private void handleCommand(String command) {
        switch (command) {
            case "rnd":
            case "register new dog":
                addNewDog();
                break;
            case "rnu":
            case "register new user":
                addNewUser();
                break;
            case "gd":
            case "give dog":
                giveDog();
                break;
            case "ia":
            case "increase age":
                increaseAge();
                break;
            case "ld":
            case "list dogs":
                listDogs();
                break;
            case "lu":
            case "list users":
                listUsers();
                break;
            case "sa":
            case "start auction":
                startAuction();
                break;
            case "la":
            case "list auctions":
                listAuctions();
                break;
            case "ca":
            case "close auction":
                closeAuction();
                break;
            case "mb":
            case "make bid":
                makeBid();
                break;
            case "lb":
            case "list bids":
                listBids();
                break;
            case "ru":
            case "remove user":
                removeUser();
                break;
            case "rd":
            case "remove dog":
                removeDog();
                break;
            case "e":
            case "exit":
                break;
            default:
                System.out.println("Fel, inte ett kommando!");
        }
    }
}
