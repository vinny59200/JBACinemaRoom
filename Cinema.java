package cinema;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        //printSeatsMap();
        //calculateProfits();
        //calculateTicketPrice();
        showAndProcessMenu();
    }

    private static void showAndProcessMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsByRow = scanner.nextInt();
        Map<String, String> bookedSeat = new HashMap<>();
        int income = 0;
        int ticketsCount = 0;
        boolean redoBuy = false;
        menu:
        for (; ; ) {
            int menuIndex = 0;
            if (!redoBuy) {
                System.out.println("1. Show the seats");
                System.out.println("2. Buy a ticket");
                System.out.println("3. Statistics");
                System.out.println("0. Exit");
                menuIndex = scanner.nextInt();
            }

            switch (redoBuy ? 2 : menuIndex) {
                case 1:
                    printSeatsMap(rows, seatsByRow, bookedSeat);
                    break;
                case 2: {   //buy a ticket
                    System.out.println("Enter a row number:");
                    String selectedRow = scanner.next();
                    System.out.println("Enter a seat number in that row:");
                    String selectedSeat = scanner.next();
                    if (Integer.parseInt(selectedRow) > rows
                            || Integer.parseInt(selectedRow) < 0
                            || Integer.parseInt(selectedSeat) > seatsByRow
                            || Integer.parseInt(selectedSeat) < 0) {
                        System.out.println("Wrong input");
                        redoBuy = true;
                        continue menu;
                    }
                    if (bookedSeat.containsKey(selectedRow) && bookedSeat.get(selectedRow).equals(selectedSeat)) {
                        System.out.println("That ticket has already been purchased");
                        redoBuy = true;
                        continue menu;
                    } else {
                        bookedSeat.put(selectedRow, selectedSeat);
                        int price = calculateTicketPrice(rows, seatsByRow, Integer.parseInt(selectedRow));
                        System.out.println(String.format("Ticket price: $%s", price));
                        income += price;
                        ticketsCount++;
                        redoBuy = false;
                    }
                }
                ;
                break;
                case 3: {
                    System.out.println("Number of purchased tickets:" + ticketsCount);
                    System.out.println(String.format("Percentage:%.2f", ((float)ticketsCount  / ((float)rows * (float)seatsByRow))*100 )+"%");
                    System.out.println("Current income: $" + income);
                    System.out.println("Total income: $" + calculateTotalIncome(rows, seatsByRow));
                }
                ;
                break;
                case 0:
                    break menu;
            }
        }
    }

    private static int calculateTotalIncome(int rows, int seatsByRow) {
        int totalIncome = 0;
        if (rows * seatsByRow < 60) {
             totalIncome += 10 * rows * seatsByRow;
        } else {
             totalIncome += (rows / 2 * 10 + (rows - rows / 2) * 8)* seatsByRow;
        }
        return totalIncome;
    }

    private static void calculateTicketPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsByRow = scanner.nextInt();
        printSeatsMap(rows, seatsByRow);
        System.out.println("Enter a row number:");
        int rowId = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatId = scanner.nextInt();
        if (rows * seatsByRow < 60) {
            System.out.println("Ticket price:");
            System.out.println("$10");
        } else {
            System.out.println("Ticket price:");
            if (rowId <= rows / 2) {
                System.out.println("$10");
            } else {
                System.out.println("$8");
            }
        }
        printSeatsMap(rows, seatsByRow, rowId, seatId);
    }

    private static int calculateTicketPrice(int rows, int seatsByRow, int rowId) {
        if (rows * seatsByRow < 60) {
            return 10;
        } else {
            if (rowId <= rows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    static void calculateProfits() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsByRow = scanner.nextInt();

        if (rows * seatsByRow < 60) {
            System.out.println("Total income:");
            System.out.println("$" + (rows * seatsByRow * 10));
        } else {
            System.out.println("Total income:");
            System.out.println("$" + +(rows / 2 * seatsByRow * 10 + (rows - rows / 2) * seatsByRow * 8));
        }
    }

    private static void printSeatsMap() {
        for (int i = 0; i <= 7; i++) {
            String row = "";
            for (int j = 0; j <= 8; j++) {
                if (i == 0 && j == 0) {
                    System.out.println("Cinema:");
                    row += " ";
                } else if (i == 0 && j != 0) {
                    row += " " + j;
                } else if (i != 0 && j == 0) {
                    row += i;
                } else if (i != 0 && j != 0) {
                    row += " S";
                }
            }
            System.out.println(row);
        }
    }

    private static void printSeatsMap(int rows, int seatsByRow) {
        for (int i = 0; i <= rows; i++) {
            String row = "";
            for (int j = 0; j <= seatsByRow; j++) {
                if (i == 0 && j == 0) {
                    System.out.println("Cinema:");
                    row += " ";
                } else if (i == 0 && j != 0) {
                    row += " " + j;
                } else if (i != 0 && j == 0) {
                    row += i;
                } else if (i != 0 && j != 0) {
                    row += " S";
                }
            }
            System.out.println(row);
        }
    }

    private static void printSeatsMap(int rows, int seatsByRow, int rowId, int seatId) {
        for (int i = 0; i <= rows; i++) {
            String row = "";
            for (int j = 0; j <= seatsByRow; j++) {
                if (i == rowId && j == seatId) {
                    row += " B";
                } else if (i == 0 && j == 0) {
                    System.out.println("Cinema:");
                    row += " ";
                } else if (i == 0 && j != 0) {
                    row += " " + j;
                } else if (i != 0 && j == 0) {
                    row += i;
                } else if (i != 0 && j != 0) {
                    row += " S";
                }
            }
            System.out.println(row);
        }
    }

    private static void printSeatsMap(int rows, int seatsByRow, Map<String, String> bookedSeat) {
        for (int i = 0; i <= rows; i++) {
            String row = "";
            for (int j = 0; j <= seatsByRow; j++) {
                if (bookedSeat.containsKey("" + i) && bookedSeat.get("" + i).equals("" + j)) {
                    row += " B";
                } else if (i == 0 && j == 0) {
                    System.out.println("Cinema:");
                    row += " ";
                } else if (i == 0 && j != 0) {
                    row += " " + j;
                } else if (i != 0 && j == 0) {
                    row += i;
                } else if (i != 0 && j != 0) {
                    row += " S";
                }
            }
            System.out.println(row);
        }
    }
}