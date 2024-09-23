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
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.model.User;

public class EmployeeListPage extends JPanel {

	private static DefaultTableModel model =new DefaultTableModel();
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
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {//columnNames:테이블 헤더에 표시, 0:초기 행수
			//JTable에게 제공 메서드
			public Class getColumnClass(int columnIndex) { 
		        if (columnIndex == 6) { // "상태" 열은 Boolean 타입 (체크박스)으로 설정
		            return Boolean.class;
		        }
		        return String.class; // 나머지는 기본 String 타입
		    }
		};
		JTable table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		

		// 검색 필드
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));

		// 레이아웃 설정
		add(searchField, BorderLayout.NORTH); // 검색 필드를 상단에 배치
		add(scrollPane, BorderLayout.CENTER); // 테이블을 중앙에 배치
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널을 하단에 배치
		
		
		//추가 버튼 클릭 이벤트
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
                    DBCon dbCon = new DBCon();
                    dbCon.deleteUser(name); // DB에서 삭제
                    model.removeRow(selectedRow); // 테이블에서 행 제거
                } else {
                    JOptionPane.showMessageDialog(EmployeeListPage.this, "삭제할 직원을 선택하세요.");
                }
            }
        });

		
		

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
						boolean status = rs.getString("STATUS").equals("1"); // STATUS가 '1'이면 true, 아니면 false

						User user = new User(
								rs.getString("USERNAME"),
	                            rs.getString("PASSWORD"),
	                            rs.getString("DEPARTMENT"),
	                            rs.getString("POSITION"),
	                            rs.getString("EMAIL"),
	                            rs.getString("PHONE"),
	                            status // boolean으로 변환
										
								);

						// 데이블에 행 추가
						list.add(user);

					}

					if (list.size() == 0) {
						JOptionPane.showMessageDialog(null, "저장된 회원이 없습니다");

					}else {
						for(User user : list) {
							model.addRow(new Object[] {
								user.getUsername(),
								user.getPassword(),
								user.getDepartment(),
								user.getEmail(),
								user.getPhone(),
								user.getPosition(),
								user.getStatus()
								
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

	public static void addEmployee(String name, String password, String department, String position, String email,
			String phone, boolean status) {
	//	DefaultTableModel model = new DefaultTableModel();
		if(model != null) {
			model.addRow(new Object[] { name, password ,department, position, email, phone, status ? "1":"0" });
		}else {
			System.out.println("값이 없습니다");
		}
		

		DBCon dbCon = new DBCon();
		// username, department, position, email, phone, status
		dbCon.insertUser(new User(name, password, department, position, email, phone, status));
		
		
	}

}
