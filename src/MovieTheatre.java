import java.util.ArrayList;
import java.util.Scanner;

public class MovieTheatre {

    static class Seat {
        private final int row;
        private final int col;
        private boolean isReserved;

        public Seat(int row, int col) {
            this.row = row;
            this.col = col;
            this.isReserved = false;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public boolean isReserved() {
            return isReserved;
        }

        public void reserve() {
            isReserved = true;
        }

        public void cancel() {
            isReserved = false;
        }

        @Override
        public String toString() {
            return isReserved ? "[X]" : "[ ]";
        }
    }

    private final ArrayList<ArrayList<Seat>> seats;

    public MovieTheatre(int rows, int cols) {
        seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Seat> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(new Seat(i + 1, j + 1));
            }
            seats.add(row);
        }
    }

    public void displaySeatingChart() {
        System.out.println("\nSeating Chart:");
        for (ArrayList<Seat> row : seats) {
            for (Seat seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    public void reserveSeat(int row, int col) {
        if (row < 1 || row > seats.size() || col < 1 || col > seats.get(0).size()) {
            System.out.println("Invalid seat number.");
            return;
        }

        Seat seat = seats.get(row - 1).get(col - 1);
        if (seat.isReserved()) {
            System.out.println("Seat (" + row + ", " + col + ") is already reserved.");
            suggestAvailableSeat();
        } else {
            seat.reserve();
            System.out.println("Seat (" + row + ", " + col + ") has been reserved.");
        }
    }

    public void cancelReservation(int row, int col) {
        if (row < 1 || row > seats.size() || col < 1 || col > seats.get(0).size()) {
            System.out.println("Invalid seat number.");
            return;
        }

        Seat seat = seats.get(row - 1).get(col - 1);
        if (!seat.isReserved()) {
            System.out.println("Seat (" + row + ", " + col + ") is not reserved.");
        } else {
            seat.cancel();
            System.out.println("Reservation for seat (" + row + ", " + col + ") has been cancelled.");
        }
    }

    private void suggestAvailableSeat() {
        for (ArrayList<Seat> row : seats) {
            for (Seat seat : row) {
                if (!seat.isReserved()) {
                    System.out.println("Suggestion: Seat (" + seat.getRow() + ", " + seat.getCol() + ") is available.");
                    return;
                }
            }
        }
        System.out.println("No available seats to suggest.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieTheatre Theatre = new MovieTheatre(5, 5); // Adjust the number of rows and columns as needed

        while (true) {
            System.out.println("\n1. View Seating Chart");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> Theatre.displaySeatingChart();
                case 2 -> {
                    System.out.print("Enter row number to reserve (1-5): ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column number to reserve (1-5): ");
                    int col = scanner.nextInt();
                    Theatre.reserveSeat(row, col);
                }
                case 3 -> {
                    System.out.print("Enter row number to cancel: ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column number to cancel: ");
                    int col = scanner.nextInt();
                    Theatre.cancelReservation(row, col);
                }
                case 4 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
