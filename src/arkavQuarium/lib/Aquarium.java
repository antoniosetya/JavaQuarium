package arkavquarium.lib;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * The type Aquarium.
 */
public class Aquarium {
    /**
     * Variable for width.
     */
    private int width;
    /**
     * Variable for height.
     */
    private int height;
    /**
     * Constant for initial coin.
     */
    private static final long INITIAL_COIN = 150;
    /**
     * Constant for initial width.
     */
    private static final int INITIAL_WIDTH = 640;
    /**
     * Constant for initial height.
     */
    private static final int INITIAL_HEIGHT = 480;
    /**
     * Variable for number of coin.
     */
    private long numOfCoins = INITIAL_COIN;
    /**
     * Variable for list of piranha.
     */
    private List<Piranha> piranhas = new List<Piranha>();
    /**
     * Variable for list of guppy.
     */
    private List<Guppy> guppies = new List<Guppy>();
    /**
     * Variable for list of fish food.
     */
    private List<FishFood> fishFoods = new List<FishFood>();
    /**
     * Variable for list of coin.
     */
    private List<Coin> coins = new List<Coin>();
    /**
     * Variable for snail.
     */
    private Siput snail;

    /**
     * Instantiates a new Aquarium.
     */
    public Aquarium() {
        width = INITIAL_WIDTH;
        height = INITIAL_HEIGHT;
    }

    /**
     * Instantiates a new Aquarium.
     *
     * @param w the desired width
     * @param h the desired height
     */
    public Aquarium(final int w, final int h) {
        this.width = w;
        this.height = h;
    }


    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Gets number of coins.
     *
     * @return the num of coins
     */
    public long getNumOfCoins() {
        return numOfCoins;
    }

    /**
     * Gets piranhas.
     *
     * @return the piranhas list
     */
    public List<Piranha> getPiranhas() {
        return piranhas;
    }

    /**
     * Gets guppies.
     *
     * @return the guppies list
     */
    public List<Guppy> getGuppies() {
        return guppies;
    }

    /**
     * Gets fish foods.
     *
     * @return the fish foods list
     */
    public List<FishFood> getFishFoods() {
        return fishFoods;
    }

    /**
     * Gets coins.
     *
     * @return the coins list
     */
    public List<Coin> getCoins() {
        return coins;
    }

    /**
     * Gets snail.
     *
     * @return the snail
     */
    public Siput getSnail() {
        return snail;
    }

    /**
     * Sets width.
     *
     * @param w the desired width
     */
    public void setWidth(final int w) {
        this.width = w;
    }

    /**
     * Sets height.
     *
     * @param h the desired height
     */
    public void setHeight(final int h) {
        this.height = h;
    }

    /**
     * Sets num of coins.
     *
     * @param coin the desired coin
     */
    public void setNumOfCoins(final long coin) {
        this.numOfCoins = coin;
    }

    /**
     * Sets snail.
     *
     * @param s the desired snail
     */
    public void setSnail(final Siput s) {
        snail = s;
    }

    /**
     * Initialize the aquarium with one Guppy and one Snail.
     */
    public void initialize() {
        Random rng = new Random();
        guppies.append(new Guppy(rng.nextInt(this.getWidth() + 1),
                rng.nextInt(this.getHeight() + 1)));
        snail = new Siput(rng.nextInt(this.getWidth() + 1),
                this.getHeight());
    }

    /**
     * Create new object with a random position.
     *
     * @param obj the object code (G,F,P)
     */
    public void createNewObject(final char obj) {
        Random rng = new Random();
        double x = rng.nextInt(this.getWidth() + 1);
        double y = rng.nextInt(this.getHeight() + 1);
        createNewObject(obj, x, y);
    }

    /**
     * Create new object.
     *
     * @param obj the object code (G,F,P)
     * @param x   the desired x position
     * @param y   the desired y position
     */
    public void createNewObject(final char obj,
                                final double x, final double y) {
        if (obj == 'F') {
            fishFoods.append(new FishFood(x, y));
        } else if (obj == 'G') {
            guppies.append(new Guppy(x, y));
        } else if (obj == 'P') {
            piranhas.append(new Piranha(x, y));
        }
    }

    /**
     * Draw aquarium.
     *
     * @param g      the Graphics context the Aquarium will be drawn to
     * @param offset the top offset
     * @param io     the ImageObserver this Aquarium will be drawn to
     */
    public void drawAquarium(final Graphics g,
                             final int offset, final JPanel io) {
        int i;
        Image temp;
        // Invoke draw on guppies
        for (i = 0; i < guppies.getSize(); i++) {
            if (guppies.get(i) != null) {
                temp = guppies.get(i).draw();
                g.drawImage(temp,
                        (int) (guppies.get(i).getX()
                                - (temp.getWidth(io) / 2)),
                                (int) (guppies.get(i).getY()
                                        - (temp.getHeight(io) / 2)
                                        + offset), io);
            }
        }
        // Invoke draw on piranhas
        for (i = 0; i < piranhas.getSize(); i++) {
            if (piranhas.get(i) != null) {
                temp = piranhas.get(i).draw();
                g.drawImage(temp,
                        (int) (piranhas.get(i).getX()
                                - (temp.getWidth(io) / 2)),
                                (int) (piranhas.get(i).getY()
                                        - (temp.getHeight(io) / 2)
                                        + offset), io);
            }
        }
        // Invoke draw on fishFoods
        for (i = 0; i < fishFoods.getSize(); i++) {
            if (fishFoods.get(i) != null) {
                temp = fishFoods.get(i).draw();
                g.drawImage(temp,
                        (int) (fishFoods.get(i).getX()
                                - (temp.getWidth(io) / 2)),
                                (int) (fishFoods.get(i).getY()
                                        - (temp.getHeight(io) / 2)
                                        + offset), io);
            }
        }
        // Invoke draw on coins
        for (i = 0; i < coins.getSize(); i++) {
            if (coins.get(i) != null) {
                temp = coins.get(i).draw();
                g.drawImage(temp,
                        (int) (coins.get(i).getX()
                                - (temp.getWidth(io) / 2)),
                                (int) (coins.get(i).getY()
                                        - (temp.getHeight(io) / 2)
                                        + offset), io);
            }
        }
        // Invoke snail's draw
        temp = snail.draw();
        g.drawImage(temp,
                (int) (snail.getX() - (temp.getWidth(io) / 2)),
                (int) (snail.getY() - (temp.getWidth(io) / 2) + offset),
                io);
    }

    /**
     * Calcuate distance between 2 coordinate.
     *
     * @param x1 the first absis
     * @param y1 the first ordinat
     * @param x2 the second absis
     * @param y2 the second ordinat
     *
     * @return euclidean distance between that coordinate
     */
    private static double euclidean(final double x1, final double y1,
                                    final double x2, final double y2) {
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    /**
     * Keep on aquarium.
     *
     * @param ao the AqObject that needs to be kept on aquarium
     */
    public void keepOnAquarium(final AqObject ao) {
        if (ao.getX() < 0) {
            ao.setX(0);
        } else if (ao.getX() > width) {
            ao.setX(width);
        }
        if (ao.getY() < 0) {
            ao.setY(0);
        } else if (ao.getY() > height) {
            ao.setY(height);
        }
    }

    /**
     * Clean the list.
     *
     * @param lao list of AqObject that want to be cleaned
     */
    private void cleanList(final List<? extends AqObject> lao) {
        int i = 0;
        while ((i < lao.getSize()) && (lao.get(i) != null)) {
            if (!lao.get(i).getIsAlive()) {
                lao.removeAt(i);
            } else {
                i++;
            }
        }
    }

    /**
     * Time has passed.
     *
     * @param sec the amount of time that has passed
     */
    public void timeHasPassed(final double sec) {
        int i, j, optLoc;
        double shortest, temp;
        Coin droppedCoin;
        // Invoke timeHasPassed for Guppy
        for (i = 0; i < guppies.getSize(); i++) {
            if (guppies.get(i) != null) {
                if (!guppies.get(i).isFishFull() && !fishFoods.isEmpty()) {
                    j = 0;
                    optLoc = 0;
                    shortest = euclidean(guppies.get(i).getX(),
                            guppies.get(i).getY(),
                            fishFoods.get(j).getX(), fishFoods.get(j).getY());
                    for (j = 1; j < fishFoods.getSize(); j++) {
                        temp = euclidean(guppies.get(i).getX(),
                                guppies.get(i).getY(),
                                fishFoods.get(j).getX(),
                                fishFoods.get(j).getY());
                        if (shortest > temp) {
                            shortest = temp;
                            optLoc = j;
                        }
                    }
                    guppies.get(i).moveTowards(fishFoods.get(optLoc));
                }
                guppies.get(i).timeHasPassed(sec);
                keepOnAquarium(guppies.get(i));
                // Special for Guppy : dropping coins every fixed interval
                droppedCoin = guppies.get(i).countdownCoin(sec);
                if (droppedCoin != null) {
                    coins.append(droppedCoin);
                }
            } else {
                break;
            }
        }
        // Invoke timeHasPassed for piranhas
        for (i = 0; i < piranhas.getSize(); i++) {
            if (piranhas.get(i) != null) {
                // Determines if there's nearby Guppy and Piranha is hungry
                if (!piranhas.get(i).isFishFull() && !guppies.isEmpty()) {
                    j = 0;
                    optLoc = 0;
                    shortest = euclidean(piranhas.get(i).getX(),
                            piranhas.get(i).getY(),
                            guppies.get(j).getX(), guppies.get(j).getY());
                    for (j = 1; j < fishFoods.getSize(); j++) {
                        temp = euclidean(piranhas.get(i).getX(),
                                piranhas.get(i).getY(),
                                guppies.get(j).getX(), guppies.get(j).getY());
                        if (shortest > temp) {
                            shortest = temp;
                            optLoc = j;
                        }
                    }
                    piranhas.get(i).moveTowards(guppies.get(optLoc));
                }
                piranhas.get(i).timeHasPassed(sec);
                keepOnAquarium(piranhas.get(i));
            } else {
                break;
            }
        }
        // Invoke timeHasPassed for fishFoods
        for (i = 0; i < fishFoods.getSize(); i++) {
            if (fishFoods.get(i) != null) {
                fishFoods.get(i).timeHasPassed(sec);
                if (fishFoods.get(i).getY() >= height) {
                    fishFoods.get(i).setIsAlive(false);
                }
            } else {
                break;
            }
        }
        // Invoke timeHasPassed for coins
        for (i = 0; i < coins.getSize(); i++) {
            if (coins.get(i) != null) {
                coins.get(i).timeHasPassed(sec);
                keepOnAquarium(coins.get(i));
            } else {
                break;
            }
        }
        // Check whether there's Coin to go to
        if (!coins.isEmpty()) {
            i = 0;
            shortest = euclidean(snail.getX(), snail.getY(),
                    coins.get(i).getX(), coins.get(i).getY());
            optLoc = 0;
            for (j = 1; j < fishFoods.getSize(); j++) {
                temp = euclidean(snail.getX(), snail.getY(),
                        coins.get(i).getX(), coins.get(i).getY());
                if (shortest > temp) {
                    shortest = temp;
                    optLoc = j;
                }
            }
            snail.moveTowards(coins.get(optLoc));
        }
        // Invoke timeHasPassed for snail
        snail.timeHasPassed(sec);
        keepOnAquarium(snail);
        // For Guppy and FishFood
        for (i = 0; i < guppies.getSize(); i++) {
            if (!guppies.get(i).isFishFull()) {
                // Loop FishFood to see whether a collision has happened
                for (j = 0; j < fishFoods.getSize(); j++) {
                    if (fishFoods.get(j).getIsAlive()
                            && guppies.get(i).getHitBox().intersects(
                                    fishFoods.get(j).getHitBox())) {
                        guppies.get(i).eat();
                        fishFoods.get(j).eaten();
                        break;
                    }
                }
            }
        }

        for (i = 0; i < piranhas.getSize(); i++) {
            if (!piranhas.get(i).isFishFull()) {
                // Loop FishFood to see whether a collision has happened
                for (j = 0; j < guppies.getSize(); j++) {
                    if (guppies.get(j).getIsAlive() && piranhas.get(i)
                            .getHitBox().intersects(
                                    guppies.get(j).getHitBox())) {
                        droppedCoin = piranhas.get(i).eatGuppy(guppies.get(j));
                        coins.append(droppedCoin);
                        break;
                    }
                }
            }
        }

        if (!coins.isEmpty()) {
            for (i = 0; i < coins.getSize(); i++) {
                if (coins.get(i).getIsAlive() && snail.getHitBox()
                        .intersects(coins.get(i).getHitBox())) {
                    numOfCoins += coins.get(i).getValue();
                    coins.get(i).collected();
                    break;
                }
            }
        }

        /* --- "GARBAGE CLEANER" -- */
        cleanList(guppies);
        cleanList(piranhas);
        cleanList(fishFoods);
        cleanList(coins);
    }

    /**
     * Collect a coin.
     *
     * @param x the absis
     * @param y the ordinat
     * @return the boolean whether a coin is collected or not
     */
    public boolean collectCoin(final int x, final int y) {
        boolean found = false;
        int i = 0;
        while ((i < coins.getSize()) && !found) {
            if (coins.get(i).getHitBox().contains(x, y)
                    && coins.get(i).getIsAlive()) {
                numOfCoins += coins.get(i).getValue();
                coins.get(i).collected();
                found = true;
            } else {
                i++;
            }
        }
        return found;
    }
}
