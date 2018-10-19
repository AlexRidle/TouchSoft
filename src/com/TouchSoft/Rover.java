package com.TouchSoft;

import java.util.Scanner;

public class Rover {

    private Scanner scanner = new Scanner(System.in);

    private int moves;
    private int velocity = 1;
    private int currentLocation = 0;
    private int endLocation;
    private boolean reverse = false;
    private String instruction = "";
    private String path = "0";

    public void setupRover() {
        System.out.print("Type end location (have to be >1) : ");
        while (true) {
            endLocation = scanner.nextInt();
            if (endLocation > 1) {
                break;
            } else {
                System.out.print("Error: value should be higher than one: ");
            }
        }
        launchRover();
    }

    private void launchRover() {
        while (currentLocation != endLocation) move();
        showOutput();
    }

    private void numberOfMoves() {

        int counter = 0;
        int secondNum = 1;
        int distance = distance() + 1;
        moves = 0;
        while (secondNum <= distance) {
            secondNum *= 2;
            counter++;
        }
        int firstNum = secondNum / 2;
        if ((secondNum - distance) >= (distance - firstNum)) {
            moves = counter - 1;
        } else {
            moves = counter;
        }
    }

    private void move() {
        numberOfMoves();
        for (int i = 0; i < moves; i++) {
            currentLocation += velocity;
            speedUp();
            addToInstruction("A");
            addToPath();
        }
        if (currentLocation != endLocation) rotateOrResetSpeed();
    }

    private void rotate() {
        if (velocity > 0) {
            reverse = true;
            velocity = -1;
        } else {
            reverse = false;
            velocity = 1;
        }
        addToInstruction("R");
    }

    private void speedUp() {
        velocity *= 2;
    }

    private void rotateOrResetSpeed() {
        if (currentLocation < endLocation && velocity > 0) {
            rotate();
            rotate();
            addToPath();
        } else if (currentLocation > endLocation && velocity < 0) {
            rotate();
            rotate();
            addToPath();
        } else {
            rotate();
            addToPath();
        }
    }

    private int distance() {
        int distance = endLocation - currentLocation;
        return Math.abs(distance);
    }

    private void addToInstruction(String instruction) {
        this.instruction += instruction;
    }

    private void addToPath() {
        path += "->" + currentLocation;
    }

    private void showOutput(){
        System.out.println(String.format("Input: %s \nAnswer: %s\nOptimal instruction is: %s\nRover's path: %s", endLocation, instruction.length(), instruction, path));
    }
}
