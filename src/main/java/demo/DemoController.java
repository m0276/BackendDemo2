package demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.CreatePollResult;
import demo.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private DailyFortuneBO dailyFortuneBO;

    @Autowired
    private PollBO pollBO;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String createPollParamDefaultValue = null;

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @GetMapping("/getDailyFortune")
    public String getDailyFortune(@RequestParam(value = "name", defaultValue = "UNKNOWN") String name) {
        return dailyFortuneBO.getDailyFortune(name);
    }

    @GetMapping("/createPoll")
    public String createPoll(@RequestParam(value = "userId", required = false) String userId,
                             @RequestParam(value = "question", required = false) String question,
                             @RequestParam(value = "optionA", required = false) String optionA,
                             @RequestParam(value = "optionB", required = false) String optionB,
                             @RequestParam(value = "optionC", required = false) String optionC,
                             @RequestParam(value = "optionD", required = false) String optionD) {
        if (userId == null || question == null
                || optionA == null || optionB == null || optionC == null || optionD == null) {
            return objectToJson(CreatePollResult.failure("INVALID_PARAM"));
        }

        Poll poll = new Poll();
        poll.setUserId(userId);
        poll.setQuestion(question);
        poll.setOptionA(optionA);
        poll.setOptionB(optionB);
        poll.setOptionC(optionC);
        poll.setOptionD(optionD);

        pollBO.createPoll(poll);
        return objectToJson(CreatePollResult.success());
    }

    @GetMapping("/getRandomPoll")
    public String getRandomPoll() {
        Poll poll = pollBO.getRandomPoll();
        return objectToJson(poll);
    }

    @GetMapping("/getPoll")
    public String getPoll(Long id) {
        Poll poll = pollBO.getPoll(id);
        return objectToJson(poll);
    }

    @GetMapping("/listPolls")
    public String listPolls() {
        List<Poll> polls = pollBO.getPolls();
        if (polls == null) {
            return null;
        }
        return objectToJson(polls);
    }

    private String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
