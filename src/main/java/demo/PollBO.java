package demo;

import demo.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PollBO {
    @Autowired
    private PollMapper pollMapper;

    public void createPoll(Poll poll) {
        pollMapper.insertPoll(poll);
    }

    public Poll getRandomPoll() {
        List<Poll> polls = pollMapper.selectPolls();
        if (polls == null || polls.isEmpty()) {
            return null;
        }

        int randomIndex = new Random().nextInt(polls.size());
        return polls.get(randomIndex);
    }

    public Poll getPoll(Long id) {
        if (id == null) {
            return null;
        }
        return pollMapper.selectPoll(id);
    }

    public List<Poll> getPolls() {
        return pollMapper.selectPolls();
    }
}
