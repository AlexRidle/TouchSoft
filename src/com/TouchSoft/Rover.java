package com.TouchSoft;

public class Rover {

    private int velocity = 1;
    private int currentLocation = 0;
    private int type;
    private int endLocation;
    private String instruction = "";
    private String path = "0";

    Rover(int endLocation) {
        this.endLocation = endLocation;
    }

    public int getInstructionLength() {
        return instruction.length();
    }

    public String getInstruction() {
        return instruction;
    }

    public String getPath() {
        return path;
    }

    public void setupRover(int type) {
        if (type > 0) {
            this.type = 1;
        } else {
            this.type = 0;
        }
        while (currentLocation != endLocation) move();
    }

    private void move() {
        doStep(getMoves());
        if (currentLocation != endLocation) rotateOrResetSpeed();
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

    private void doStep(int moves) {
        for (int i = 0; i < moves; i++) {
            currentLocation += velocity;
            speedUp();
            addToInstruction("A");
            addToPath();
        }
    }

    private void rotateOrResetSpeed() {

        if (currentLocation < endLocation && velocity > 0) {
            rotate();
            doStep(getBackwardSteps());
            rotate();
        } else if (currentLocation > endLocation && velocity < 0) {
            rotate();
            doStep(getBackwardSteps());
            rotate();
        } else {
            rotate();
        }
    }

    private int getBackwardSteps() {
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
}
