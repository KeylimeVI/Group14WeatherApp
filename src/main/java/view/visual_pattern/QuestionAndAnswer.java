package view.visual_pattern;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionAndAnswer<C extends Component> extends JPanel {
	
	private String question;
	private C answer;

	public QuestionAndAnswer(String question, LayoutManager layout) {
		this.question = question;
		this.setLayout(layout);
		initialize();
	}

	public QuestionAndAnswer(String question) {
		this.question = question;
		initialize();
	}

	private void initialize() {
		JLabel label = new JLabel(question);
		add(label);
		add(answer);
	}

	public C getComponent() {
		return answer;
	}
}
