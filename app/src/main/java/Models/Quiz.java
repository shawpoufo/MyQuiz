package Models;


import java.io.Serializable;
import java.util.List;


public class Quiz implements Serializable {
    private final String name;
    private final List<QuizQuestion> quizQuestions;

    public Quiz(String name ,List<QuizQuestion> quizQuestions){
        this.name = name;
        this.quizQuestions = quizQuestions;
    }

    public String getName(){
        return name;
    }
    public List<QuizQuestion> getQuizQuestions(){
        return  quizQuestions;
    }
}
