import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberSorter
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        String filePath = scanner.nextLine();
        List<Integer> numbers = readNumbersFromFile(filePath);
        if (numbers == null)
        {
            System.out.println("Error reading.");
            return;
        }
        Counter counter = new Counter();
        Thread evenThread = new Thread(() -> counter.writeEvenNumbers(numbers, "even_numbers.txt"));
        Thread oddThread = new Thread(() -> counter.writeOddNumbers(numbers, "odd_numbers.txt"));
        evenThread.start();
        oddThread.start();
        try
        {
            evenThread.join();
            oddThread.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Number of even numbers: " + counter.getEvenCount());
        System.out.println("Number of odd numbers: " + counter.getOddCount());
    }
    private static List<Integer> readNumbersFromFile(String filePath)
    {
        List<Integer> numbers = new ArrayList<>();
        try
        {
            Files.lines(Paths.get(filePath)).forEach(line ->
            {
                try
                {
                    numbers.add(Integer.parseInt(line.trim()));
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Incorrect format: " + line);
                }
            });
        }
        catch (IOException e)
        {
            System.out.println("Cant read file: " + e.getMessage());
            return null;
        }
        return numbers;
    }
    static class Counter
    {
        private int evenCount = 0;
        private int oddCount = 0;
        public synchronized void incrementEvenCount()
        {
            evenCount++;
        }
        public synchronized void incrementOddCount()
        {
            oddCount++;
        }
        public int getEvenCount()
        {
            return evenCount;
        }
        public int getOddCount()
        {
            return oddCount;
        }
        public void writeEvenNumbers(List<Integer> numbers, String fileName)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
            {
                for (int number : numbers)
                {
                    if (number % 2 == 0)
                    {
                        writer.write(number + "\n");
                        incrementEvenCount();
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("Error writing: " + e.getMessage());
            }
        }
        public void writeOddNumbers(List<Integer> numbers, String fileName)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
            {
                for (int number : numbers)
                {
                    if (number % 2 != 0)
                    {
                        writer.write(number + "\n");
                        incrementOddCount();
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("Error writing: " + e.getMessage());
            }
        }
    }
}
