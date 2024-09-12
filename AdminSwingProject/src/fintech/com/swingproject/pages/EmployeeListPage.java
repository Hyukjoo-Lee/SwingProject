package fintech.com.swingproject.pages;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeListPage extends JPanel {

	public EmployeeListPage(CardLayout cardLayout, JPanel mainPanel) {
		// 프로젝트 통일성을 위해 구조 수정 (JFrame 구조를 JPanel로 바꾸고, CardLayout과 mainPanel을 사용하여 페이지 전환이
		// 가능하게 만듦)
//		JFrame frame = new JFrame("직원 리스트");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(800, 600);

		// 패널 생성
		setLayout(new BorderLayout());

		// 버튼 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));

		JButton addButton = new JButton("추가");
		JButton deleteButton = new JButton("삭제");
		JButton editButton = new JButton("편집");
		JButton cancelButton = new JButton("취소");

		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(editButton);
		buttonPanel.add(cancelButton);

		// 테이블 생성 (이건 배웠는지 모르겠음)
		String[] columnNames = { "이름", "소속", "직급", "아이디", "이메일", "전화번호", "상태" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);

		// 검색 필드
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));

		// 레이아웃 설정
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);
		add(searchField, BorderLayout.NORTH);
	}
}
