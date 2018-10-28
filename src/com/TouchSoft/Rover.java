package com.TouchSoft;

public class Rover {

    private int velocity = 1;
    private int currentLocation = 0;
    private int type;
    public int endLocation;
    public String instruction = "";
    public String path = "0";

    Rover(int endLocation) {
        this.endLocation = endLocation;
    }

    public void setupRover(int type) {
        if (type > 0) {
            this.type = 1;
        } else {
            this.type = 0;
        }
        launchRover();
    }

    private void launchRover() {
        while (currentLocation != endLocation) move();
    }

    private int getMoves() {

        int counter = 0;
        int secondNum = 1;
        int distance = distance(currentLocation) + 1;
        while (secondNum <= distance) {
            secondNum *= 2;
            counter++;
        }
        int firstNum = secondNum / 2;
        if ((secondNum - distance) >= (distance - firstNum)) {
            return counter - 1;
        } else {
            return counter;
        }
    }

    private void move() {
        doStep(getMoves());
        if (currentLocation != endLocation) rotateOrResetSpeed();
    }

    private void doStep(int moves) {
        for (int i = 0; i < moves; i++) {
            currentLocation += velocity;
            speedUp();
            addToInstruction("A");
            addToPath();
        }
    }

    private void rotate() {
        if (velocity > 0) {
            velocity = -1;
        } else {
            velocity = 1;
        }
        addToInstruction("R");
        addToPath();
    }

    private void speedUp() {
        velocity *= 2;
    }

    private void rotateOrResetSpeed() {

        if (currentLocation < endLocation && velocity > 0) {
            rotate();
            doStep(getSteps());
            rotate();
        } else if (currentLocation > endLocation && velocity < 0) {
            rotate();
            doStep(getSteps());
            rotate();
        } else {
            rotate();
        }
    }

    private int distance(int location) {
        int distance = endLocation - location;
        return Math.abs(distance);
    }

    private void addToInstruction(String instruction) {
        this.instruction += instruction;
    }

    private void addToPath() {
        path += "->" + currentLocation;
    }

    private int getSteps() {
        if (type > 0) {
            int pos = currentLocation;
            int distance = distance(pos);
            int velocity = 1;
            int step = 1;
            int count = 0;
            while (true) {
                if (velocity != distance && velocity < distance) {
                    velocity *= 2;
                    velocity++;
                } else if (velocity > distance && count < 50) {
                    distance += step;
                    step *= 2;
                    count++;
                } else if (velocity == distance) {
                    return count;
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }
}
