public class Question 
{
    String question;
    String[] options;
    int[] scores;

    public Question(String question, String[] options, int[] scores) {
        this.question = question;
        this.options = options;
        this.scores = scores;
    }
}