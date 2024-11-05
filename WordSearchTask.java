import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

class WordSearchTask implements Callable<Boolean>
{
    private String filePath;
    private String word;
    public WordSearchTask(String filePath, String word)
    {
        this.filePath = filePath;
        this.word = word;
    }
    @Override
    public Boolean call()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains(word))
                {
                    return true;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading: " + e.getMessage());
        }
        return false;
    }
}
