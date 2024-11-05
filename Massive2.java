import java.util.Scanner;

public class Massive2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть розмір масиву: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введіть елементи масиву:");
        for (int i = 0; i < size; i++)
        {
            array[i] = scanner.nextInt();
        }
        Result result = new Result();
        Thread sumThread = new Thread(new SumTask(array, result));
        Thread averageThread = new Thread(new AverageTask(array, result));
        sumThread.start();
        averageThread.start();
        try
        {
            sumThread.join();
            averageThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Massive sum: " + result.sum);
        System.out.println("Arithmetic mean of an array: " + result.average);
    }
}
class Result
{
    int sum;
    double average;
}
class SumTask implements Runnable
{
    private int[] array;
    private Result result;
    public SumTask(int[] array, Result result)
    {
        this.array = array;
        this.result = result;
    }
    @Override
    public void run()
    {
        int sum = 0;
        for (int num : array)
        {
            sum += num;
        }
        result.sum = sum;
    }
}
class AverageTask implements Runnable
{
    private int[] array;
    private Result result;
    public AverageTask(int[] array, Result result)
    {
        this.array = array;
        this.result = result;
    }
    @Override
    public void run()
    {
        int sum = 0;
        for (int num : array)
        {
            sum += num;
        }
        result.average = (double) sum / array.length;
    }
}
