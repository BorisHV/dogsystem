
package dogsystem;

/**
 * @author Boris Holmgren boho8503
 */
public class Dog {
    private static final double DACHSHUND_TAILLENGTH = 3.7;
    private static final double DIVISOR = 10.0;
    private final String name;
    private final String breed;
    private int age;
    private final int weight;
    private User owner;


    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public String getName() {
        return name;
    }
    public boolean isOwned() {
        return owner != null;
    }
    public double getTailLength() {

        if (breed.equalsIgnoreCase ("tax")|| breed.equalsIgnoreCase ("dachshund"))
        {
            return DACHSHUND_TAILLENGTH;
        }
        return age * (weight/DIVISOR);
    }
    @Override
    public String toString() {
        double tailLength = getTailLength();
        String returnString = "* "+ name +" (" + breed + ", " + age + " years, " + weight + " kilo, "
                + String.format("%.2f", tailLength) + " cm tail";
        if (owner == null) {
            returnString += ")";
        }else {
            returnString += ", owned by " + owner.getName() + ")";
        }
        return returnString;
    }
    public User getOwner() {
        return owner;
    }
    public String getBreed() {
        return breed;
    }
    public void increaseAge() {
        age++;
    }
    public int getAge() {
        return age;
    }
    public int getWeight() {
        return weight;
    }
}


