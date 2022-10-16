import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TeamProject extends JFrame {
    Container cp = getContentPane();
    Task[] tasks = {
            new Task1(this, cp),
            new Task2(this, cp),
            new Task3(this, cp),
            new Task4(this, cp),
            new Task5(this, cp)};
    String[] taskExp = getExplanations();

    public static void main(String[] args) {
        new TeamProject();
    }

    TeamProject() {
        setTitle("20171336_노도아_20190084_김가현_기말과제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setMenu();
        setVisible(true);
    }

    private void setMenu() {
        JMenuBar mb = new JMenuBar();
        String[] menuTitle = {"과제1", "과제2", "과제3", "과제4", "과제5", "기타"};
        JMenu[] menu = new JMenu[menuTitle.length];
        for (int i = 0; i < menuTitle.length; i++) {
            menu[i] = new JMenu(menuTitle[i]);
            mb.add(menu[i]);
        }
        setJMenuBar(mb);

        JMenuItem[][] menuItem = new JMenuItem[menuTitle.length][2];
        String[] menuItemTitle = {"과제설명", "과제실행", "화면 지우기", "종료"};
        for (int i = 0; i < menuTitle.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == menuTitle.length - 1)
                    menuItem[i][j] = new JMenuItem(menuItemTitle[j + 2]);
                else
                    menuItem[i][j] = new JMenuItem(menuItemTitle[j]);
                menu[i].add(menuItem[i][j]);

                menuItem[i][j].addActionListener(e -> {
                    removeScreen();

                    Object obj = e.getSource();
                    if (obj == menuItem[5][1])
                        System.exit(0);
                    for (int k = 0; k < menuTitle.length - 1; k++) {
                        if (obj == menuItem[k][0]) {
                            setSize(700, 400);
                            cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
                            cp.add(new JLabel(taskExp[k]));
                            break;
                        }
                        if (obj == menuItem[k][1]) {
                            tasks[k].doTask();
                            break;
                        }
                    }
                    setVisible(true);
                });
            }
        }
    }

    private String[] getExplanations() {
        String[] taskExpPath = {
                "Explanation/task1Exp.txt",
                "Explanation/task2Exp.txt",
                "Explanation/task3Exp.txt",
                "Explanation/task4Exp.txt",
                "Explanation/task5Exp.txt"
        };
        InputStream in = null;
        String[] taskExp = new String[taskExpPath.length];

        for (int i = 0; i < 5; i++) {
            try {
                in = getClass().getClassLoader().getResourceAsStream(taskExpPath[i]);
                byte[] data = new byte[in.available()];
                in.read(data);
                taskExp[i] = new String(data, "utf-8").replaceFirst("﻿", "");
            } catch (Exception e) {
                System.out.println("[ERROR] 에러가 발생했습니다. >> " + taskExpPath[i]);
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                        in = null;
                    } catch (IOException e) {
                        System.out.println("[ERROR] 파일 닫기 실패");
                    }
                }
            }
        }

        return taskExp;
    }

    private void removeScreen() {
        cp.setBackground(null);
        cp.removeAll();
        cp.repaint();
    }
}