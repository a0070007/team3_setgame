package iss.sa40.team3.model;

import java.util.Random;

public class Card implements Comparable<Card>{
     
    private int number;
    private int shading;
    private int color;
    private int shape;
    private float randomId;
    private static Random random = new Random();
    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the shading
     */
    public int getShading() {
        return shading;
    }

    /**
     * @param shading the shading to set
     */
    public void setShading(int shading) {
        this.shading = shading;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the shape
     */
    public int getShape() {
        return shape;
    }

    /**
     * @param shape the shape to set
     */
    public void setShape(int shape) {
        this.shape = shape;
    }

    public Card(int number, int shading, int color, int shape) {
        this.number = number;
        this.shading = shading;
        this.color = color;
        this.shape = shape;
        this.randomId = random.nextFloat();
    }

    public int compareTo(Card other){
        if(this.randomId == ((Card)other).randomId)
            return 0;
        if(this.randomId > ((Card)other).randomId)
            return 1;
        else
            return -1;
    }
    
    
    @Override
    public String toString() {
        return "Card{" + "number=" + number + ", shading=" + shading + ", color=" + color + ", shape=" + shape + '}';
    }   
}
