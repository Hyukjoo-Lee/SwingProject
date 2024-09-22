package fintech.com.swingproject.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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

	public EmployeeListPage(CardLayout cardLayout, JPanel mainPanel) {
		// 프로젝트 통일성을 위해 구조 수정 (JFrame 구조를 JPanel로 바꾸고, CardLayout과 mainPanel을 사용하여 페이지
		// 전환이
		// 가능하게 만듦)
//		JFrame frame = new JFrame("직원 리스트");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(800, 600);

		// 패널 생성
		setLayout(new BorderLayout());

		// 버튼 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 4));

		JButton addButton = new JButton("추가");
		JButton deleteButton = new JButton("삭제");
		JButton editButton = new JButton("편집");
		JButton findButton = new JButton("검색");

		// 버튼 크기 조정
		Dimension buttonSize = new Dimension(50, 40);
		addButton.setPreferredSize(buttonSize);
		deleteButton.setPreferredSize(buttonSize);
		editButton.setPreferredSize(buttonSize);
		findButton.setPreferredSize(buttonSize);

		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(editButton);
		buttonPanel.add(findButton);

		// 테이블 생성 (이건 배웠는지 모르겠음)
		String[] columnNames = { "이름", "소속", "직급", "아이디", "이메일", "전화번호", "상태" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);

		// 검색 필드
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));

		// 레이아웃 설정
		add(searchField, BorderLayout.NORTH); // 검색 필드를 상단에 배치
		add(scrollPane, BorderLayout.CENTER); // 테이블을 중앙에 배치
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널을 하단에 배치

		// 텍스트에 입력한 다음 검색 버튼 클릭시
		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchField.getText().trim();

				DBCon dbCon = new DBCon();
				ResultSet rs = null;

				try {
					// 검색 실행
					rs = dbCon.searchUser(searchText);
					ArrayList<User> list = new ArrayList<User>(); //user 클래스 타입의 객체만 저장 하도록 제한한다

					// 검색된 데이터가 있으면 테이블에 추가
					while (rs.next()) {
						User user = new User(
								rs.getString("USERNAME")
								);

						
						// 데이블에 행 추가
						list.add(user);

					}

					if (list.size() == 0) {
						JOptionPane.showMessageDialog(null, "저장된 회원이 없습니다");

					}else {
						for(User user : list) {
							model.addRow(new Object[] {
								user.getUsername()
								
							});
						}
					}
					System.out.println("정보가 있습니다");

				} catch (SQLException ex) {
					System.out.println("찾을 수 없습니다");
					ex.printStackTrace();

				} finally {
					dbCon.close(rs, null);
				}

			}
		});

	}

}
