package dogsystem;

/**
 * @author Boris Holmgren boho8503
 */
import java.util.ArrayList;

public class Auction {
    static final int MAXPRINTOUTS = 3;
    private final Dog dogToBeAuctioned;
    private final int auctionNumber;
    private final ArrayList<Bid> bids;
    private boolean isClosed;

    public Auction  (Dog dogToBeAuctioned, int auctionNumber) {
        this.auctionNumber = auctionNumber;
        this.dogToBeAuctioned = dogToBeAuctioned;
        bids = new ArrayList<>();
        isClosed = false;
    }
    public int getAuctionNumber() {
        return auctionNumber;
    }
    public boolean isAuctionClosed() {
        return isClosed;
    }
    public void closeAuction() {
        isClosed = true;
    }
    public Dog getDogToBeAuctioned() {
        return dogToBeAuctioned;
    }
    public String toString() {
        if (bids.size()>MAXPRINTOUTS) {
            ArrayList<Bid> tempBids = new ArrayList<>();
            for (int i = 0; i<MAXPRINTOUTS; i++) {
                Bid bidAtPointer = bids.get(i);
                tempBids.add(bidAtPointer);
            }
            return "Auction #"+ auctionNumber +": "+ dogToBeAuctioned.getName()+". Top bids: "+tempBids.toString();
        }
        return "Auction #"+ auctionNumber +": "+ dogToBeAuctioned.getName()+". Top bids: "+bids.toString();
    }
    public boolean hasBids() {
        return !bids.isEmpty();
    }
    public int currentHighestBid() {
        int highest = 0;
        for (int i = 0; i < bids.size(); i++) {
            Bid bidAtPointer = bids.get(i);
            if	(bidAtPointer.getSumOfBid() > highest) {
                highest = bidAtPointer.getSumOfBid();
            }
        }
        return highest;
    }
    public void removeBidsFromUser(User userToRemove) {
        for (int i = bids.size()-1; i >= 0; i-- ) {
            if (bids.get(i).getBidderName().equalsIgnoreCase(userToRemove.getName())) {
                bids.remove(i);
            }
        }
    }
    public Bid currentHighestBidder() {
        int highest = 0;
        Bid highestBidder = null;
        for (int i = 0; i < bids.size(); i++) {
            Bid bidAtPointer = bids.get(i);
            if	(bidAtPointer.getSumOfBid() > highest) {
                highest = bidAtPointer.getSumOfBid();
                highestBidder = bidAtPointer;
            }
        }
        return highestBidder;
    }
    public void addBid(Bid bidToAdd) {
        for (int i = 0; i<bids.size(); i++ ) {
            Bid bidAtPointer = bids.get(i);
            if (bidAtPointer.getBidderName().equalsIgnoreCase(bidToAdd.getBidderName())) {
                bids.remove(i);
                bids.add(0, bidToAdd);
                return;
            }
        }
        bids.add(0, bidToAdd);
    }
    public void printBids() {
        System.out.println("Here are the bids for this auction");
        for (int i = 0; i < bids.size(); i++) {
            System.out.println(bids.get(i).toString());
        }
    }
}


