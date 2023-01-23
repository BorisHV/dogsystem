
package dogsystem;

/**
 * @author Boris Holmgren boho8503
 */
public class User {
    private final String name;
    private Dog[] ownedDogs;

    public User(String name) {
        this.name = name;
        ownedDogs = new Dog[0];
    }
    /**
     * Lägger till en ny hund.
     * @param dogToAdd
     */
    public void setDog(Dog dogToAdd) {
        Dog[] targetArray = new Dog[ownedDogs.length+1];
        for (int i = 0; i < ownedDogs.length; i++) {
            targetArray[i] = ownedDogs[i];
        }
        targetArray[targetArray.length-1] = dogToAdd;
        ownedDogs = targetArray;
    }
    public String getName() {
        return name;
    }
    /**
     * Makes a copy of the Dog array and returns it.
     * @return
     */
    public Dog[] getOwnedDogs() {
        Dog[] targetArray = new Dog[ownedDogs.length];
        for (int i = 0; i < ownedDogs.length; i++) {
            targetArray[i] = ownedDogs[i];
        }
        return targetArray;
    }
    public String toString() {
        String dogList = "[";
        for (int i = 0; i< ownedDogs.length; i++) {
            dogList += ownedDogs[i].getName();
            if (i< ownedDogs.length-1) {
                dogList+=", ";
            }
        }
        dogList += "]";
        return name + " " + dogList;
    }
    public void removeDog(Dog dogToRemove) {
        Dog[] targetArray = new Dog[ownedDogs.length-1];
        int j = 0;
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] != dogToRemove) {
                targetArray[j] = ownedDogs[i];
                j++;
            }
        }
        ownedDogs = targetArray;
    }
}
