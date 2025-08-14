package view.visual_pattern;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class QueryC<C extends Component> extends JPanel {
	
	private String question;
	private C answer;

	public QueryC(String question, C answer, LayoutManager layout) {
		this.question = question;
		this.answer = answer;
		this.setLayout(layout);
		initialize();
	}

	public QueryC(String question, C answer) {
		this.question = question;
		this.answer = answer;
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
