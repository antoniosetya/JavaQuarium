// File		    : Piranha.java
// Author	    : 13516122 - Manasye
// Created on   : 15 April 2018
// Updated by   :

public class Piranha extends Fish {
    private static final int piranhaSpeed = 40;
    private static final int guppyPrice = 100;
    private int degOfMovement;

    // Constructor
    public Piranha() {
        super(0, 0, piranhaSpeed);
        this.degOfMovement = 180;
    }

    public Piranha(double x, double y) {
        super(x, y, piranhaSpeed);
        this.degOfMovement = 180;
    }

    // Getter
    public int getDegOfMovement() {
        return degOfMovement;
    }

    // Setter
    public void setDegOfMovement(int degOfMovement) {
        this.degOfMovement = degOfMovement;
    }

//    // Method for piranha eating guppy
//    public Coin eatGuppy(Guppy g) {
//        g.eaten();
//        setFishFull(true);
//        setTimeBeforeHungry(15);
//        setTimeBeforeDying(20);
//        // Create new coin
//        Coin c1 = new Coin(this.getX(), this.getY(), guppyPrice * (g.getGrowthStage() + 1));
//        return c1;
//    }
}
