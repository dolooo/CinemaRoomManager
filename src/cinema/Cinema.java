package cinema;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static int purchasedTickets = 0;
    static double percentageOfPurchasedTickets = 0.0;
    static int currentIncome = 0;
    static int totalIncome = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();
        int[][] prices = new int[rows][columns];
        char[][] seats = new char[rows][columns];
        calculateTotalIncome(rows, columns, prices);


        for (char[] chars : seats) {
            Arrays.fill(chars, 'S');
        }

        boolean isRunning = true;

        while(isRunning) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();

            switch (option) {
                case (1):
                    showSeats(columns, seats);
                    break;
                case (2):
                    int[] chosenSeat = chooseSeat(rows, columns, seats);
                    buyTicket(rows, columns, prices, seats, chosenSeat);
                    break;
                case (3):
                    showStatistics();
                    break;
                case (0):
                    isRunning = false;
                    break;
            }
        }
    }

    private static void showStatistics() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentageOfPurchasedTickets);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    private static void calculateTotalIncome(int rows, int columns, int[][] prices) {
        int sizeOfCinema = rows * columns;

        if (sizeOfCinema <= 60) {
            for (int[] seatsAndPrice : prices) {
                Arrays.fill(seatsAndPrice, 10);
                totalIncome += 10;
            }
        } else {
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < prices[i].length; j++) {
                    if (i < ((prices.length - 1) / 2)) {
                        prices[i][j] = 10;
                        totalIncome += 10;
                    } else  {
                        prices[i][j] = 8;
                        totalIncome += 8;
                    }
                }
            }
        }
    }
    private static int[] chooseSeat(int rows, int columns, char[][] seats) {
        int[] chosenSeat = new int[2];
        boolean done = false;
        while(!done) {
            System.out.println("Enter a row number: ");
            chosenSeat[0] = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            chosenSeat[1] = scanner.nextInt();

            if (chosenSeat[0] > rows || chosenSeat[1] > columns) {
                System.out.println("Wrong input!");
            } else if(seats[chosenSeat[0]-1][chosenSeat[1]-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                done = true;
            }
        }
        return chosenSeat;
    }

    private static void buyTicket(int rows, int columns, int[][] prices, char[][] seats, int[] chosenSeat) {
        int sizeOfCinema = rows * columns;
        if (sizeOfCinema <= 60) {
            for (int[] seatsAndPrice : prices) {
                Arrays.fill(seatsAndPrice, 10);
            }
        } else {
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < prices[i].length; j++) {
                    if (i < ((prices.length - 1) / 2)) {
                        prices[i][j] = 10;
                    } else  {
                        prices[i][j] = 8;
                    }
                }
            }
        }
        System.out.println("Ticket price: $" + prices[chosenSeat[0]-1][chosenSeat[1]-1]);
        purchasedTickets++;
        percentageOfPurchasedTickets = (Math.round(((double)purchasedTickets / (double)sizeOfCinema) * 10000.0) / 100.0);
        currentIncome += prices[chosenSeat[0]-1][chosenSeat[1]-1];

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if ((i + 1) == chosenSeat[0] && (j + 1) == chosenSeat[1]) {
                    seats[i][j] = 'B';
                }
            }
        }
    }

    private static void showSeats(int columns, char[][] seats) {
        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i = 0; i < columns; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();
        }
    }
}