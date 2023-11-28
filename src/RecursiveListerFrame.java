import javax.swing.*;
import java.io.File;
import java.awt.*;

public class RecursiveListerFrame extends JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JPanel buttonPnl;

    JLabel titleLbl;

    JScrollPane scroller;
    JTextArea displayTextArea;
    JButton quitBtn;
    JButton startBtn;

    public RecursiveListerFrame()
    {
        setTitle("Recursive FileLister, Josh Gormly");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((screenWidth / 4), 3*(screenHeight / 4));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        add(mainPnl);
        createTitlePnl();
        createDisplayPnl();
        createButtonPnl();

        setVisible(true);
    }
    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Recursive FileLister, Josh Gormly");
        titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }
    private void createDisplayPnl()
    {
        displayPnl = new JPanel();
        displayTextArea = new JTextArea(35,70);
        scroller = new JScrollPane(displayTextArea);
        displayTextArea.setEditable(false);

        displayPnl.add(scroller);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
    }
    private void createButtonPnl()
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1,2));

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        startBtn = new JButton("Start");
        startBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        buttonPnl.add(startBtn);
        buttonPnl.add(quitBtn);
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        quitBtn.addActionListener(e ->
        {
            JOptionPane quitPane = new JOptionPane();



                int decision = JOptionPane.showConfirmDialog(quitPane, "Are you wanitng to quit?", "Quit", JOptionPane.YES_NO_OPTION);
                if (decision == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
                else
                {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
        });
        startBtn.addActionListener(e -> start());
    }
    private void start()
    {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        chooser.setDialogTitle("Choose a directory you want to use: ");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File directory = chooser.getSelectedFile();
            displayTextArea.setText("Directory: " + directory + "\n\n");
            displayTextArea.append("Sub-directories, Files from the sub-directories, and Files from the main directory are shown." + "\n\n\n");

            listNames(directory);
        }
        else
        {
            displayTextArea.append("File is not found, please try again!");
        }
    }
    private void listNames(File f)
    {
        File Names[] = f.listFiles();

        if (Names != null)
        {
            for (File n : Names)
            {
                displayTextArea.append(n + "\n\n");
                if (n.isDirectory())
                {
                    listNames(n);
                }
            }
        }
    }
}