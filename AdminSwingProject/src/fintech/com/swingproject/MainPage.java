package fintech.com.swingproject;

import javax.swing.*;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.pages.SignUpPage;
import fintech.com.swingproject.pages.Page2;
import fintech.com.swingproject.pages.Page3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 초기세팅: 메인페이지 + 페이지1,2,3 + 회원가입페이지 + JDBC 세팅
 */
public class MainPage {

	public static void main(String[] args) {
		
		// 디비 설정
		DBCon dbObj = new DBCon();
		
		JFrame frame = new JFrame("어드민 시스템");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);

		// 레이아웃
		CardLayout cardLayout = new CardLayout();
		JPanel mainPanel = new JPanel(cardLayout);

		// 초기 페이지 세팅
		SignUpPage signUpPage = new SignUpPage(cardLayout, mainPanel);
		Page2 page2 = new Page2(cardLayout, mainPanel);
		Page3 page3 = new Page3(cardLayout, mainPanel);

		mainPanel.add(signUpPage, "회원가입");
		mainPanel.add(page2, "페이지2");
		mainPanel.add(page3, "페이지3");
		
		
		// 메뉴 바
		JMenuBar menuBar = new JMenuBar();

		// 회원가입
		JMenu menu1 = new JMenu("회원가입");
		JMenuItem menuItem1 = new JMenuItem("회원가입 페이지 이동");
		menuItem1.addActionListener(e -> cardLayout.show(mainPanel, "회원가입"));
		menu1.add(menuItem1);
		
		

		// 메뉴2
		JMenu menu2 = new JMenu("메뉴 2");
		JMenuItem menuItem2 = new JMenuItem("페이지2 이동");
		menuItem2.addActionListener(e -> cardLayout.show(mainPanel, "페이지2"));
		menu2.add(menuItem2);

		// 메뉴 3
		JMenu menu3 = new JMenu("메뉴 3");
		JMenuItem menuItem3 = new JMenuItem("페이지3 이동");
		menuItem3.addActionListener(e -> cardLayout.show(mainPanel, "페이지3"));
		menu3.add(menuItem3);

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);

		frame.setJMenuBar(menuBar);
		frame.add(mainPanel);

		frame.setVisible(true);
	}
}
