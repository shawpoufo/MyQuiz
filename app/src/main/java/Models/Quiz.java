package Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Quiz implements Serializable {
    private String name;
    private List<QuizQuestion> quizQuestions;

    public Quiz() {
        quizQuestions = new ArrayList<>();
    }

    public Quiz(String name , List<QuizQuestion> quizQuestions){
        this.name = name;
        this.quizQuestions = quizQuestions;
    }

    public String getName() {
        return name;
    }

    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }
}

