package comp3350.flashy.logic;

//This might need to be changed before Due date

public class Flashcard implements FlashcardInterface, LogicInterface{
    private String answer;
    private String question;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Mutators
     */

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuesion(String newQuestion) {
        this.question = newQuestion;
    }

}