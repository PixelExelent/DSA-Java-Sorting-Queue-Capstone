import java.util.ArrayList;
import java.util.Scanner;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> ticketNumbers = new ArrayList<>();
        ArrayList<String> requesterNames = new ArrayList<>();
        ArrayList<String> issueDescriptions = new ArrayList<>();

        Queue<Integer> ticketQueue = new LinkedList<>();
        ArrayList<Integer> processedTicketNumbers = new ArrayList<>();

        int nextTicketNumber = 1;
        int choice = 0;

        while (choice != 9) {
            System.out.println("\n=== IT Help Desk Ticketing System ===");
            System.out.println("1. Add a ticket");
            System.out.println("2. Display all tickets");
            System.out.println("3. Search for a ticket");
            System.out.println("4. Show the next ticket");
            System.out.println("5. Process the next ticket");
            System.out.println("6. Display the waiting queue");
            System.out.println("7. Sort records by requester name (Bubble Sort)");
            System.out.println("8. Sort records by issue description (Selection Sort)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Please enter a number from 1 to 9.");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter requester name: ");
                    String name = input.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Name cannot be blank. Ticket was not added.");
                        break;
                    }

                    System.out.print("Describe the issue: ");
                    String issue = input.nextLine().trim();

                    if (issue.isEmpty()) {
                        System.out.println("Issue cannot be blank. Ticket was not added.");
                        break;
                    }

                    ticketNumbers.add(nextTicketNumber);
                    requesterNames.add(name);
                    issueDescriptions.add(issue);

                    System.out.println("Ticket #" + nextTicketNumber + " was added.");
                    ticketQueue.add(nextTicketNumber);
                    nextTicketNumber++;
                    break;

                case 2:
                    System.out.println("\n--- Current Tickets Waiting ---");

                    if (ticketQueue.isEmpty()) {
                        System.out.println("No tickets are waiting.");
                    } else {
                        for (int waitingNumber : ticketQueue) {
                            for (int i = 0; i < ticketNumbers.size(); i++) {
                                if (ticketNumbers.get(i) == waitingNumber) {
                                    System.out.println("Ticket #" + waitingNumber);
                                    System.out.println("Requester: " + requesterNames.get(i));
                                    System.out.println("Issue: " + issueDescriptions.get(i));
                                    System.out.println();
                                    break;
                                }
                            }
                        }
                    }

                    System.out.println("--- Processed Tickets ---");

                    if (processedTicketNumbers.isEmpty()) {
                        System.out.println("No tickets have been processed.");
                    } else {
                        for (int processedNumber : processedTicketNumbers) {
                            for (int i = 0; i < ticketNumbers.size(); i++) {
                                if (ticketNumbers.get(i) == processedNumber) {
                                    System.out.println("Ticket #" + processedNumber);
                                    System.out.println("Requester: " + requesterNames.get(i));
                                    System.out.println("Issue: " + issueDescriptions.get(i));
                                    System.out.println();
                                    break;
                                }
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter the ticket number to search for: ");

                    if (input.hasNextInt()) {
                        int searchNumber = input.nextInt();
                        input.nextLine();

                        boolean found = false;

                        for (int i = 0; i < ticketNumbers.size(); i++) {
                            if (ticketNumbers.get(i) == searchNumber) {
                                System.out.println("\nTicket found:");
                                System.out.println("Ticket #" + ticketNumbers.get(i));
                                System.out.println("Requester: " + requesterNames.get(i));
                                System.out.println("Issue: " + issueDescriptions.get(i));
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("No ticket with that number was found.");
                        }
                    } else {
                        System.out.println("Please enter a whole number.");
                        input.nextLine();
                    }
                    break;

                case 4:
                    if (ticketQueue.isEmpty()) {
                        System.out.println("There are no tickets waiting.");
                    } else {
                        System.out.println("Next ticket to process: #" + ticketQueue.peek());
                    }
                    break;

                case 5:
                    if (ticketQueue.isEmpty()) {
                        System.out.println("There are no tickets waiting.");
                    } else {
                        int processedNumber = ticketQueue.remove();
                        processedTicketNumbers.add(processedNumber);
                        System.out.println("Ticket #" + processedNumber + " moved to processed tickets.");
                    }
                    break;

                case 6:
                    if (ticketQueue.isEmpty()) {
                        System.out.println("The waiting queue is empty.");
                    } else {
                        System.out.println("\n--- Waiting Queue (first to last) ---");

                        for (int ticketNumber : ticketQueue) {
                            System.out.println("Ticket #" + ticketNumber);
                        }
                    }
                    break;

                case 7: {
                    if (ticketNumbers.isEmpty()) {
                        System.out.println("There are no tickets to sort.");
                        break;
                    }

                    System.out.println("\n--- Tickets Before Bubble Sort ---");
                    for (int i = 0; i < ticketNumbers.size(); i++) {
                        System.out.println("Ticket #" + ticketNumbers.get(i) + " - " + requesterNames.get(i));
                    }

                    // Compare neighboring names and swap them if they are out of order.
                    for (int pass = 0; pass < requesterNames.size() - 1; pass++) {
                        for (int i = 0; i < requesterNames.size() - 1 - pass; i++) {
                            if (requesterNames.get(i).compareToIgnoreCase(requesterNames.get(i + 1)) > 0) {
                                // Swap the names.
                                String tempName = requesterNames.get(i);
                                requesterNames.set(i, requesterNames.get(i + 1));
                                requesterNames.set(i + 1, tempName);

                                // Swap the matching ticket numbers.
                                int tempNumber = ticketNumbers.get(i);
                                ticketNumbers.set(i, ticketNumbers.get(i + 1));
                                ticketNumbers.set(i + 1, tempNumber);

                                // Swap the matching issue descriptions.
                                String tempIssue = issueDescriptions.get(i);
                                issueDescriptions.set(i, issueDescriptions.get(i + 1));
                                issueDescriptions.set(i + 1, tempIssue);
                            }
                        }
                    }

                    System.out.println("\n--- Tickets After Bubble Sort (by requester name) ---");
                    for (int i = 0; i < ticketNumbers.size(); i++) {
                        System.out.println("Ticket #" + ticketNumbers.get(i) + " - " + requesterNames.get(i));
                    }
                    break;
                }
                case 8: {
                    if (ticketNumbers.isEmpty()) {
                        System.out.println("There are no tickets to sort.");
                        break;
                    }

                    System.out.println("\n--- Tickets Before Selection Sort ---");
                    for (int i = 0; i < ticketNumbers.size(); i++) {
                        System.out.println("Ticket #" + ticketNumbers.get(i) + " - " + issueDescriptions.get(i));
                    }

                    // Find the alphabetically earliest issue in the remaining unsorted part.
                    for (int start = 0; start < issueDescriptions.size() - 1; start++) {
                        int smallestIndex = start;

                        for (int i = start + 1; i < issueDescriptions.size(); i++) {
                            if (issueDescriptions.get(i)
                                    .compareToIgnoreCase(issueDescriptions.get(smallestIndex)) < 0) {
                                smallestIndex = i;
                            }
                        }

                        // Put that issue in its correct position, swapping all ticket details.
                        if (smallestIndex != start) {
                            String tempIssue = issueDescriptions.get(start);
                            issueDescriptions.set(start, issueDescriptions.get(smallestIndex));
                            issueDescriptions.set(smallestIndex, tempIssue);

                            String tempName = requesterNames.get(start);
                            requesterNames.set(start, requesterNames.get(smallestIndex));
                            requesterNames.set(smallestIndex, tempName);

                            int tempNumber = ticketNumbers.get(start);
                            ticketNumbers.set(start, ticketNumbers.get(smallestIndex));
                            ticketNumbers.set(smallestIndex, tempNumber);
                        }
                    }

                    System.out.println("\n--- Tickets After Selection Sort (by issue) ---");
                    for (int i = 0; i < ticketNumbers.size(); i++) {
                        System.out.println("Ticket #" + ticketNumbers.get(i) + " - " + issueDescriptions.get(i));
                    }
                    break;
                }
                case 9:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Please choose a number from 1 to 9.");
            }
        }
        input.close();
    }
}