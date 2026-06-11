public class Question {
    String QuestionString;
    String answerChoices[];
    int greenScores[];

    public Question(String q, String choice[], int score[]){
        this.QuestionString= q;
        this.answerChoices= choice;
        this.greenScores=score;
    }
}
