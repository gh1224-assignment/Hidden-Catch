import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Task5 extends Task {
    private static final int IMG_W = 800;
    private static final int IMG_H = 530;
    private static final int IMG_X = 280;
    private static final int IMG_Y = 70;
    private static final int ANSWER_POS[][][] = {
            {{653, 688, 76, 113}, {436, 468, 364, 383}, {749, 797, 258, 307}, {680, 713, 336, 369}, {491, 578, 452, 513}},
            {{632, 655, 83, 109}, {546, 562, 140, 156}, {532, 552, 378, 398}, {561, 579, 274, 294}, {600, 616, 183, 204}},
            {{409, 445, 148, 175}, {423, 443, 261, 289}, {480, 544, 457, 493}, {721, 745, 271, 297}, {654, 706, 36, 73}}
    };

    private List<JComponent> allComponents = new ArrayList<>();
    private JLabel intro = createImgLabel("HiddenCatch/intro.gif", 280, 50, 800, 405);
    private JButton start = createImgButton("HiddenCatch/start.png", 505, 500, 350, 83);
    private JLabel timebar = createImgLabel("HiddenCatch/timebar.gif", 280, 20, 300, 47);
    private JButton next = createImgButton("HiddenCatch/next.png", 1110, 233, 110, 184);
    private JLabel lose = createImgLabel("HiddenCatch/stop.png", 280, 35, 800, 397);
    private JButton retry = createImgButton("HiddenCatch/retry.png", 620, 430, 120, 137);
    private JLabel outro = createImgLabel("HiddenCatch/outro.gif", 355, 5, 650, 671);
    private JLabel[] img = new JLabel[3];
    private JLabel[] win = new JLabel[3];
    private JLabel[] heart = new JLabel[5];
    private JLabel[] circle = new JLabel[5];
    private JLabel[] circle2 = new JLabel[5];

    private GameState gameState = new GameState();
    private MouseAdapter answerChecker;

    public Task5(JFrame jFrame) {
        super(jFrame);

        for (int i = 0; i < 3; i++)
            win[i] = createImgLabel("HiddenCatch/win0" + i + ".png", 520, 246, 320, 158);
        for (int i = 0; i < 5; i++) {
            circle[i] = createImgLabel("HiddenCatch/circle.png", 0, 0, 50, 50);
            circle2[i] = createImgLabel("HiddenCatch/circle.png", 0, 0, 50, 50);
            heart[i] = createImgLabel("HiddenCatch/heart.png", i * 30 + 920, 40, 30, 28);
        }
        for (int i = 0; i < 3; i++)
            img[i] = createImgLabel("HiddenCatch/HiddenCatch0" + i + ".jpg", IMG_X, IMG_Y, IMG_W, IMG_H);

        start.addActionListener(e -> {
            gameState.reset();
            showNewGame();
        });
        next.addActionListener(e -> {
            gameState.next();
            if (gameState.isEnd()) {
                showOutro();
                return;
            }
            showNewGame();
        });
        retry.addActionListener(e -> showIntro());

        answerChecker = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (!isOnImg(x, y))
                    return;

                for (int i = 0; i < 5; i++) {
                    if (!isInBound(ANSWER_POS[gameState.getLevel()][i], x, y))
                        continue;
                    if (gameState.hasFound(i))
                        return;

                    gameState.correct(i);

                    int correctIdx = gameState.getCorrectNum() - 1;
                    circle[correctIdx].setLocation(x - 25, y - 25);
                    circle[correctIdx].setVisible(true);
                    circle2[correctIdx].setLocation(x - 25 - IMG_W / 2, y - 25);
                    circle2[correctIdx].setVisible(true);

                    if (gameState.isWin())
                        showWin();

                    return;
                }

                gameState.wrong();
                heart[5 - gameState.getWrongNum()].setVisible(false);
                if (gameState.isLose())
                    showLose();
            }
        };
    }

    private void showIntro() {
        clear();

        intro.setVisible(true);
        start.setVisible(true);
    }

    private void showNewGame() {
        clear();

        img[gameState.getLevel()].setVisible(true);
        timebar.setVisible(true);
        for (int i = 0; i < 5; i++)
            heart[i].setVisible(true);

        cp.addMouseListener(answerChecker);
    }

    private void showWin() {
        timebar.setVisible(false);

        win[gameState.getLevel()].setVisible(true);
        next.setVisible(true);

        cp.removeMouseListener(answerChecker);
    }

    private void showLose() {
        clear();

        lose.setVisible(true);
        retry.setVisible(true);
    }

    private void showOutro() {
        clear();

        outro.setVisible(true);
    }

    private void clear() {
        allComponents.stream().forEach(e -> e.setVisible(false));
        cp.removeMouseListener(answerChecker);
    }

    @Override
    int getFrameWidth() {
        return 1360;
    }

    @Override
    int getFrameHeight() {
        return 730;
    }

    @Override
    void addComponents() {
        allComponents.stream().forEach(cp::add);
        showIntro();
    }

    private JLabel createImgLabel(String path, int x, int y, int width, int height) {
        JLabel label = new JLabel(new ImageIcon(ResourceUtil.getUrl(path)));
        label.setBounds(x, y, width, height);
        allComponents.add(label);
        return label;
    }

    private JButton createImgButton(String path, int x, int y, int width, int height) {
        JButton button = new JButton(new ImageIcon(ResourceUtil.getUrl(path)));
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        allComponents.add(button);
        return button;
    }

    private static boolean isInBound(int[] p, int x, int y) {
        return p[0] + IMG_X <= x
                && x <= p[1] + IMG_X
                && p[2] + IMG_Y <= y
                && y <= p[3] + IMG_Y;
    }

    private static boolean isOnImg(int x, int y) {
        return IMG_X + IMG_W / 2 <= x
                && x <= IMG_X + IMG_W
                && IMG_Y <= y
                && y <= IMG_Y + IMG_H;
    }
}
