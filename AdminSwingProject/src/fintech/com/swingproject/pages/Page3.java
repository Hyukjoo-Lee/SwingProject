package fintech.com.swingproject.pages;

import javax.swing.*;
import java.awt.*;

public class Page3 extends JPanel {
	
	// 생성자
	public Page3(CardLayout cardLayout, JPanel mainPanel) {
		setLayout(new BorderLayout());
		
		JLabel label = new JLabel("세번째 페이지", JLabel.CENTER);
		JButton button = new JButton("페이지 3로 이동");
		
		button.addActionListener(e -> cardLayout.show(mainPanel, "페이지3"));
		
		add(label, BorderLayout.CENTER);
		add(label, BorderLayout.SOUTH);
	}
}