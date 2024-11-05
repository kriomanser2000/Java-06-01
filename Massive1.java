import java.util.Scanner;

public class Massive1
{
    public static void main(String[] args) throws InterruptedException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter massive count: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Enter massive element:");
        for (int i = 0; i < n; i++)
        {
            array[i] = scanner.nextInt();
        }
        final int[] maxResult = new int[1];
        final int[] minResult = new int[1];
        Thread maxThread = new Thread(() ->
        {
            int max = array[0];
            for (int value : array)
            {
                if (value > max)
                {
                    max = value;
                }
            }
            maxResult[0] = max;
        });
        Thread minThread = new Thread(() ->
        {
            int min = array[0];
            for (int value : array)
            {
                if (value < min)
                {
                    min = value;
                }
            }
            minResult[0] = min;
        });
        maxThread.start();
        minThread.start();
        maxThread.join();
        minThread.join();
        System.out.println("Max: " + maxResult[0]);
        System.out.println("Min: " + minResult[0]);
    }
}
