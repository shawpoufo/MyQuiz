package Models;

import java.io.Serializable;
import java.util.Map;

public class QuizQuestion implements Serializable {
    private String question;
    private String imageName;
    private String correctAnswer;
    private Map<String, Object> options;

    public QuizQuestion() {
        imageName = "quizzimage";
    }

    public QuizQuestion(String question, String imageName, String correctAnswer) {
        this.question = question;
        this.imageName = imageName;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean verify(String res) {
        return correctAnswer.equals(res);
    }

    public String getImageName() {
        return imageName;
    }

    public void setOptions(Map<String, Object> mapList) {
        options = mapList;
    }

    public Map<String, Object> getOptions() {
        return options;
    }
}
