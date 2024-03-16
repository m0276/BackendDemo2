package demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DailyFortuneBO {
    private final List<String> dailyFortunes = List.of(
            "최상",
            "중상",
            "중간",
            "중하",
            "최하"
    );

    public String getDailyFortune(String name) {
        Random random = new Random();
        int randomIndex = random.nextInt(dailyFortunes.size());
        return name + " 님의 오늘 운세는 " + dailyFortunes.get(randomIndex) + "입니다.";
    }
}
