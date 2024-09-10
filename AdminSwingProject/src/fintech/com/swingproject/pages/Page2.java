package fintech.com.swingproject.pages;

import javax.swing.*;
import java.awt.*;

public class Page2 extends JPanel {
	
	// 생성자
	public Page2(CardLayout cardLayout, JPanel mainPanel) {
		setLayout(new BorderLayout());
		
		JLabel label = new JLabel("두번째 페이지", JLabel.CENTER);
		JButton button = new JButton("페이지 3으로 이동");
		
		button.addActionListener(e -> cardLayout.show(mainPanel, "페이지3"));
		
		add(label, BorderLayout.CENTER);
		add(label, BorderLayout.SOUTH);
	}

}
	