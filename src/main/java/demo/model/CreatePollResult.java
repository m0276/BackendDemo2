package demo.model;

public class CreatePollResult {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    private String result;
    private String failureReason;

    public static CreatePollResult success() {
        CreatePollResult createPollResult = new CreatePollResult();
        createPollResult.result = SUCCESS;
        return createPollResult;
    }

    public static CreatePollResult failure(String failureReason) {
        CreatePollResult createPollResult = new CreatePollResult();
        createPollResult.result = FAILURE;
        createPollResult.failureReason = failureReason;
        return createPollResult;
    }

    public String getResult() {
        return result;
    }

    public String getFailureReason() {
        return failureReason;
    }
}
