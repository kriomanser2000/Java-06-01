import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordSearchApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter word for search: ");
        String word = scanner.nextLine();
        WordSearchTask searchTask = new WordSearchTask(filePath, word);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Boolean> result = executorService.submit(searchTask);
        try
        {
            if (result.get())
            {
                System.out.println("Word finded in file.");
            }
            else
            {
                System.out.println("Word not finded in file.");
            }
        }
        catch (InterruptedException | ExecutionException e)
        {
            System.out.println("Error searching: " + e.getMessage());
        }
        finally
        {
            executorService.shutdown();
        }
    }
}
