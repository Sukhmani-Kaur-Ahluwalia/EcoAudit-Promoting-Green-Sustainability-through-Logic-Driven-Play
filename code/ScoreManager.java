public class ScoreManager{
    int totalScore=0;
    int qanswered=0;
    public void Add(int points){
        totalScore+=points;
        qanswered++;
        System.out.println("SCORE : "+totalScore);
    }
        public int getTotalScore(){
            return totalScore;
        }
        public void resetScore(){
            totalScore=0;
            qanswered=0;
        }  
}