
package dogsystem;

/**
 * @author Boris Holmgren boho8503
 */
public class Bid {
    private int sumOfBid;
    private User bidder;
    public Bid (int sumOfBid, User bidder) {
        this.bidder = bidder;
        this.sumOfBid = sumOfBid;
    }
    public void setSumOfBid(int newBid) {
        sumOfBid = newBid;
    }
    public int getSumOfBid() {
        return sumOfBid;
    }
    public String toString() {
        return bidder.getName()+" "+ sumOfBid +" kr";
    }
    public User getBidder() {
        return bidder;
    }
    public String getBidderName() {
        return bidder.getName();
    }
}