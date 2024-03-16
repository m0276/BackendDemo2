package demo;

import demo.model.Poll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PollMapper {
    void insertPoll(@Param("poll") Poll poll);

    List<Poll> selectPolls();

    Poll selectPoll(@Param("id") Long id);
}
