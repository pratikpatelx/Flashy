package Persistence;

public class Flashcard implements FlashcardInterface {
	private String cardName;
	private String answer;
	private String question;

	public Flashcard(String name, String question, String answer) {
		this.cardName = name;
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

	public void setQuestion(String newQuestion) {
		this.question = newQuestion;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String newName) {
		cardName = newName;
	}

}