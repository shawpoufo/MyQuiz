package Models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class QuizQuestion implements Serializable {
    private final String text;
    private final String imageName;
    private final int response;
    private Map<Integer,String> choices;

    public QuizQuestion(String text, String imageName, int response){
        this.text = text;
        this.imageName = imageName;
        this.response = response;
    }

    public String getText() { return  text;}
    public boolean verify(int res){
        return response == res;
    }
    public String getImageName() {
        return imageName;
    }
    public void setChoices(Map<Integer,String> mapList){
        choices = mapList;
    }
    public Map<Integer,String> getChoices(){
        return choices;
    }
}
