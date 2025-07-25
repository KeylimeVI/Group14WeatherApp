package view.visual_pattern;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionAndAnswer<C extends Component> {

	private JPanel panel;
	
	private String question;
	private C answer;

	public QuestionAndAnswer(String question, LayoutManager layout) {
		this.question = question;
		panel = new JPanel(layout);
		initialize();
	}

	public QuestionAndAnswer(String question) {
		this.question = question;
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		initialize();
	}

	private void initialize() {
		JLabel label = new JLabel(question);
		panel.add(label);
		panel.add(answer);
	}

	public C getComponent() {
		return answer;
	}
}
