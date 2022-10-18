import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Explanation {
    private JFrame jFrame;
    private Container cp;

    private static final String[] TASK_EXP_PATH = {
            "Explanation/task1Exp.txt",
            "Explanation/task2Exp.txt",
            "Explanation/task3Exp.txt",
            "Explanation/task4Exp.txt",
            "Explanation/task5Exp.txt"
    };
    private String[] taskExp = new String[TASK_EXP_PATH.length];

    public Explanation(JFrame jFrame) {
        this.jFrame = jFrame;
        this.cp = jFrame.getContentPane();

        for (int i = 0; i < TASK_EXP_PATH.length; i++) {
            try (InputStream is = ResourceUtil.getStream(TASK_EXP_PATH[i])) {
                byte[] data = new byte[is.available()];
                is.read(data);
                taskExp[i] = new String(data, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show(int idx) {
        jFrame.setSize(700, 400);
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        cp.add(new JLabel(taskExp[idx]));
    }
}
