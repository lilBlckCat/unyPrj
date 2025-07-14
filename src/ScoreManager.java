import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";

    public void saveScore(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + ":" + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTopScores() {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            // اینجا میگه اگه فایلی هم نبود اکیه
        }

        // وظیفه ایشون مرتب سازیه
        scores.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split(":")[1]);
            int scoreB = Integer.parseInt(b.split(":")[1]);
            return Integer.compare(scoreB, scoreA); // از بیشترین به کمترین
        });

        return scores.subList(0, Math.min(5, scores.size())); // فقط 5 رکورد اول
    }
}
