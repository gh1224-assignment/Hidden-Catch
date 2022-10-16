import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class TeamProject extends JFrame {
    private Container cp = getContentPane();
    private Task[] tasks = {
            new Task1(this),
            new Task2(this),
            new Task3(this),
            new Task4(this),
            new Task5(this)};
    private String[] taskExp = getExplanations();

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

    private String[] getExplanations() {
        String[] taskExpPath = {
                "Explanation/task1Exp.txt",
                "Explanation/task2Exp.txt",
                "Explanation/task3Exp.txt",
                "Explanation/task4Exp.txt",
                "Explanation/task5Exp.txt"
        };

        String[] taskExp = new String[taskExpPath.length];
        for (int i = 0; i < taskExpPath.length; i++) {
            try (InputStream is = ResourceUtil.getStream(taskExpPath[i])) {
                byte[] data = new byte[is.available()];
                is.read(data);
                taskExp[i] = new String(data, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return taskExp;
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
        ActionListener menuListener = e -> {
            removeScreen();

            Object obj = e.getSource();
            if (obj == menuItem[5][1])
                System.exit(0);
            for (int k = 0; k < menuTitle.length - 1; k++) {
                if (obj == menuItem[k][0]) {
                    showExplanation(k);
                    break;
                }
                if (obj == menuItem[k][1]) {
                    tasks[k].doTask();
                    break;
                }
            }
            setVisible(true);
        };

        for (int i = 0; i < menuTitle.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == menuTitle.length - 1)
                    menuItem[i][j] = new JMenuItem(menuItemTitle[j + 2]);
                else
                    menuItem[i][j] = new JMenuItem(menuItemTitle[j]);
                menu[i].add(menuItem[i][j]);
                menuItem[i][j].addActionListener(menuListener);
            }
        }
    }

    private void removeScreen() {
        cp.setBackground(null);
        cp.removeAll();
        cp.repaint();
    }

    private void showExplanation(int idx) {
        setSize(700, 400);
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        cp.add(new JLabel(taskExp[idx]));
    }
}