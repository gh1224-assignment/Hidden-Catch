import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame {
    private static final String[] MENU_TITLE = {"과제1", "과제2", "과제3", "과제4", "과제5", "기타"};
    private static final String[] MENU_ITEM_TITLE = {"과제설명", "과제실행", "화면 지우기", "종료"};

    private JFrame jFrame = new JFrame();
    private Container cp = jFrame.getContentPane();

    private JMenuBar mb = new JMenuBar();
    private JMenu[] menu = new JMenu[MENU_TITLE.length];
    private JMenuItem[][] menuItem = new JMenuItem[MENU_TITLE.length][2];

    private Explanation explanation = new Explanation(jFrame);
    private Task[] tasks = {
            new Task1(jFrame),
            new Task2(jFrame),
            new Task3(jFrame),
            new Task4(jFrame),
            new Task5(jFrame)};

    private ActionListener menuListener = e -> {
        cp.setBackground(null);
        cp.removeAll();
        cp.repaint();

        Object obj = e.getSource();
        if (obj == menuItem[5][1])
            System.exit(0);
        for (int k = 0; k < MENU_TITLE.length - 1; k++) {
            if (obj == menuItem[k][0]) {
                explanation.show(k);
                break;
            }
            if (obj == menuItem[k][1]) {
                tasks[k].doTask();
                break;
            }
        }

        jFrame.setVisible(true);
    };

    public static void main(String[] args) {
        new MainFrame();
    }

    public MainFrame() {
        jFrame.setJMenuBar(mb);

        for (int i = 0; i < MENU_TITLE.length; i++) {
            menu[i] = new JMenu(MENU_TITLE[i]);
            mb.add(menu[i]);
        }

        for (int i = 0; i < MENU_TITLE.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == MENU_TITLE.length - 1)
                    menuItem[i][j] = new JMenuItem(MENU_ITEM_TITLE[j + 2]);
                else
                    menuItem[i][j] = new JMenuItem(MENU_ITEM_TITLE[j]);
                menu[i].add(menuItem[i][j]);
                menuItem[i][j].addActionListener(menuListener);
            }
        }

        jFrame.setTitle("20171336_노도아_20190084_김가현_기말과제");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
    }
}
