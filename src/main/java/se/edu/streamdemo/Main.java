package se.edu.streamdemo;

import java.util.ArrayList;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

public class Main {

    public static void main(String[] args) {
        printWelcomeMessage();
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlineUsingStreams(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlineUsingStreams(tasksData));

    }

    private static void printWelcomeMessage() {
        System.out.println("Welcome to Task manager (using streams)");
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlineUsingStreams(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter((Task t) -> t instanceof Deadline)
                .count();
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Print data using iteration...");
        for (Task t : tasksData) {
            System.out.println(t);
        }
        System.out.println();
    }

    public static void printDataUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Print data using stream...");
        tasks.stream()
                .forEach(System.out::println);

    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Print deadline using iteration...");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlineUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Print deadline using stream...");
        tasks.parallelStream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }


}
