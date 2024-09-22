package fintech.com.swingproject.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.model.User;

public class EmployeeListPage extends JPanel {

	private static DefaultTableModel model;

	//삭제관련 추가
	private DBCon dbCon; // DB 연결 객체
	
	public EmployeeListPage(CardLayout cardLayout, JPanel mainPanel) {
		// 프로젝트 통일성을 위해 구조 수정 (JFrame 구조를 JPanel로 바꾸고, CardLayout과 mainPanel을 사용하여 페이지
		// 전환이
		// 가능하게 만듦)
		// JFrame frame = new JFrame("직원 리스트");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(800, 600);

		//삭제관련 추가
		this.dbCon = new DBCon(); // DB 객체 초기화
	    setLayout(new BorderLayout());
		
		// 패널 생성
		setLayout(new BorderLayout());

		// 버튼 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));

		JButton addButton = new JButton("추가");
		JButton deleteButton = new JButton("삭제");
		JButton editButton = new JButton("편집");
		JButton cancelButton = new JButton("취소");

		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(editButton);
		buttonPanel.add(cancelButton);

		// 테이블 생성 (이건 배웠는지 모르겠음)
		String[] columnNames = { "이름", "비밀번호", "소속", "직급", "이메일", "전화번호", "상태" };
		model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);

		// 검색 필드
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));

		// 레이아웃 설정
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);
		add(searchField, BorderLayout.NORTH);

		// "추가" 버튼 클릭 이벤트
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "추가");
				// new EmployeeAdd(); // 새로운 직원 추가 창 열기
			}
		});
		 
		// "삭제" 버튼 클릭 이벤트
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String name = (String) model.getValueAt(selectedRow, 0); // 선택된 직원의 이름
                    dbCon.deleteUser(name); // DB에서 삭제
                    model.removeRow(selectedRow); // 테이블에서 행 제거
                } else {
                    JOptionPane.showMessageDialog(EmployeeListPage.this, "삭제할 직원을 선택하세요.");
                }
            }
        });
		
		
		
		
		setVisible(true);

	}

	public static void addEmployee(String name, String password, String department, String position, String email,
			String phone, String status) {
		model.addRow(new Object[] { name, password ,department, position, email, phone, status });

		DBCon dbCon = new DBCon();
		// username, department, position, email, phone, status
		dbCon.insertUser(new User(name, password, department, position, email, phone, status));

	}
}
