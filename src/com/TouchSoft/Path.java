package com.TouchSoft;

import java.util.Scanner;

public class Path {

    private Scanner scanner = new Scanner(System.in);
    private int endLocation;

    public void start() {
        System.out.print("Type end location (have to be >1) : ");
        while (true) {
            endLocation = scanner.nextInt();
            if (endLocation > 1) {
                break;
            } else {
                System.out.print("Error: value should be higher than one: ");
            }
        }
        showOutput(getOptimalPath());
    }

    private Rover getOptimalPath() {
        Rover roverA = new Rover(endLocation);
        Rover roverB = new Rover(endLocation);
        roverA.setupRover(0);
        roverB.setupRover(1);
        return roverA.getInstructionLength() > roverB.getInstructionLength() ? roverB : roverA;
    }

    private void showOutput(Rover rover) {
        System.out.println(String.format("Input: %s \nAnswer: %s\nOptimal instruction is: %s\nRover's path: %s",
                endLocation, rover.getInstructionLength(), rover.getInstruction(), rover.getPath()));
    }
}
