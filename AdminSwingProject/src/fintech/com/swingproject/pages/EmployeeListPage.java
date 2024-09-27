package fintech.com.swingproject.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.model.User;

public class EmployeeListPage extends JPanel {

	private static DefaultTableModel model;

	private DBCon dbCon; // DB 연결 객체

	public EmployeeListPage(CardLayout cardLayout, JPanel mainPanel) {
		
		// DB 객체 초기화
		this.dbCon = new DBCon(); 
		setLayout(new BorderLayout());

		// 패널 생성
		setLayout(new BorderLayout());

		// 버튼 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 4));

		JButton addButton = new JButton("추가");
		JButton deleteButton = new JButton("삭제");
		JButton editButton = new JButton("편집");
		JButton findButton = new JButton("조회");

		Dimension buttonSize = new Dimension(50, 40);
		addButton.setPreferredSize(buttonSize);
		deleteButton.setPreferredSize(buttonSize);
		editButton.setPreferredSize(buttonSize);
		findButton.setPreferredSize(buttonSize);

		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(editButton);
		buttonPanel.add(findButton);

		// 테이블 생성
		String[] columnNames = { "이름", "비밀번호", "소속", "직급", "이메일", "전화번호", "상태" };
		model = new DefaultTableModel(columnNames, 0) {
			@Override

			public Class getColumnClass(int columnIndex) {
				// 체크박스 열은 Boolean.class로 설정, 나머지는 String
				if (columnIndex == 6) {
					return Boolean.class;
				}
				return String.class;
			}
		};

		JTable table = new JTable(model);

		// 검색 필드
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));
		JScrollPane scrollPane = new JScrollPane(table);

		// 레이아웃 설정
		add(searchField, BorderLayout.NORTH); // 검색 필드를 상단에 배치
		add(scrollPane, BorderLayout.CENTER); // 테이블을 중앙에 배치
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널을 하단에 배치

		// "추가" 버튼 클릭 이벤트
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "추가");
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

		// "검색" 버튼 클릭 이벤트
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchField.getText().trim();
				try {
					ArrayList<User> list = dbCon.searchUser(searchText);

					// 검색된 데이터가 있으면 테이블에 추가
					if (list.size() == 0) {
						JOptionPane.showMessageDialog(null, "저장된 회원이 없습니다");
					} else {
						System.out.println("정보가 있습니다");
						for (User user : list) {
							model.addRow(new Object[] { user.getUsername(), user.getPassword(), user.getDepartment(),
									user.getPosition(), user.getEmail(), user.getPhone(), user.getStatus() });

						}
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		setVisible(true);

	}

	public static void addEmployee(String name, String password, String department, String position, String email,
			String phone, boolean status) {
		model.addRow(new Object[] { name, password, department, position, email, phone, status ? true : false });

		DBCon dbCon = new DBCon();
		dbCon.insertUser(new User(name, password, department, position, email, phone, status));

	}
}
