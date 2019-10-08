package sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    
    public static Font font1 = new Font("SansSerif", Font.BOLD, 22);
    public static Font font2 = new Font("SansSerif", Font.PLAIN, 22);
    public static JTextField[][] jtf = new JTextField[9][9];

    public GUI(){
        super("Sudoku");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel 0: Entire application
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel0 = new JPanel(new GridLayout(1,1));
        EmptyBorder border1 = new EmptyBorder(6, 6, 6, 6);
        panel0.setBorder(border1);

        JPanel contPanel = new JPanel(new GridBagLayout());

        //Grid panel
        JPanel gridPanel = new JPanel(new GridLayout(3,3));
        square(gridPanel);
        c.gridx = 0;
        c.gridy = 0;
        contPanel.add(gridPanel, c);

        //Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(border1);

        JLabel label1 = new JLabel("Sudoku", SwingConstants.CENTER);
        Font font1 = new Font("SansSerif", Font.BOLD, 22);
        label1.setFont(font1);
        label1.setBorder(new EmptyBorder(0, 0, 117, 0));
        c.gridx = 2;
        c.gridy = 1;
        buttonPanel.add(label1, c);

        JRadioButton r1 = new JRadioButton("Easy");
        r1.setSelected(true);
        JRadioButton r2 = new JRadioButton("Average");
        JRadioButton r3 = new JRadioButton("Hard");
        ButtonGroup rGroup = new ButtonGroup();
        rGroup.add(r1);rGroup.add(r2);rGroup.add(r3);
        c.gridx = 1;
        c.gridy = 2;
        buttonPanel.add(r1, c);
        c.gridx = 2;
        c.gridy = 2;
        buttonPanel.add(r2, c);
        c.gridx = 3;
        c.gridy = 2;
        buttonPanel.add(r3, c);



        JButton button1 = new JButton("Check");
        JButton button2 = new JButton("New Game");
        JButton button3 = new JButton("Exit");
        button1.setPreferredSize(new Dimension(85,22));
        button2.setPreferredSize(new Dimension(85,22));
        button3.setPreferredSize(new Dimension(85,22));
        c.gridx = 1;
        c.gridy = 3;
        buttonPanel.add(button1, c);
        c.gridx = 2;
        c.gridy = 3;
        buttonPanel.add(button2, c);
        c.gridx = 3;
        c.gridy = 3;
        buttonPanel.add(button3, c);

        button1.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //String test = jtf[0][0].getText();
                check();
            }
        });
        
        button2.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                newGame();
            }
        });

        JLabel consLabel = new JLabel("Last played:      ");
        EmptyBorder border2 = new EmptyBorder(15, 0, 0, 0);
        consLabel.setBorder(border2);
        c.gridx = 1;
        c.gridy = 4;
        buttonPanel.add(consLabel,c);

        JTextArea console = new JTextArea();
        console.setEditable(false);
        JScrollPane consoleScroll = new JScrollPane(console);
        consoleScroll.setPreferredSize(new Dimension(250,100));
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 3;
        buttonPanel.add(consoleScroll, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 0;
        contPanel.add(buttonPanel, c);



        panel0.add(contPanel);

        add(panel0);
        pack();
        setLocationRelativeTo(null);
    }

    public void square(JPanel panel){


        ArrayList<JPanel> panArr = new ArrayList<JPanel>();
        for (int i = 0; i<9; i++){
            panArr.add(new JPanel(new GridLayout(3,3)));
            panArr.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
        }

        /*ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();
        for (int j = 0; j<9; j++){
            fieldArr.add(new JTextField(Integer.toString(j)));
        }*/
        
        Sudoku sudo = new Sudoku();
        //JTextField[][] jtf = new JTextField[9][9];
        /*for (int i=0; i<9; i++){
            for (int y=0; y<9; y++){
                jttf.add(new JTextField());
            }
        }*/
        
     
        for(int x = 0; x<panArr.size(); x++){
            for (int y = 0; y<9; y++) {
                //JTextField jtf = new JTextField();
                jtf[x][y] = new JTextField();
                jtf[x][y].setFont(font1);
                jtf[x][y].setColumns(2);
                jtf[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                
                if (sudo.game[x][y]==0){
                    jtf[x][y].setText("");
                    jtf[x][y].setFont(font2);
                }else{
                    jtf[x][y].setText(Integer.toString(sudo.game[x][y]));
                    jtf[x][y].setEditable(false);
                    jtf[x][y].setBackground(Color.WHITE);
                }
                
                panArr.get(x).add(jtf[x][y]);
            }
            panel.add(panArr.get(x));
        }
        
        /*for(int x=0; x<panArr.size(); x++){
            for(int y=0; y<jtf.size(); y++){
                jtf.get(y).setFont(font1);
                jtf.get(y).setColumns(2);
                jtf.get(y).setHorizontalAlignment(SwingConstants.CENTER);
                jtf.get(y).setText(Integer.toString(sudo.game[x][y]));
            }
        }*/
                /*textfield.setFont(font1);
                textfield.setColumns(2);
                textfield.setHorizontalAlignment(SwingConstants.CENTER);
                panArr.get(x).add(textfield);*/

    }
    
    public static void check(){
        int[][] grid = new int[9][9];
        for (int x=0; x<9; x++){
            for (int y=0; y<9; y++){
                if("".equals(jtf[x][y].getText())){
                    grid[x][y] = 0;
                } else {
                    grid[x][y] = Integer.parseInt(jtf[x][y].getText());
                }
            }
        }
        
        for (int x=0; x<9; x++){
            for (int y=0; y<9; y++){
                if (jtf[x][y].isEditable()){
                    if (checker.isValid(grid, x, y)){
                        if (!"".equals(jtf[x][y].getText())){
                            jtf[x][y].setForeground(Color.GREEN);
                        }
                    } else {
                        jtf[x][y].setForeground(Color.RED);
                    }
                }
            }
        }
        
    }
    
    public static void newGame(){
        Sudoku sudo = new Sudoku();
            for (int x=0; x<9; x++){
                for (int y=0; y<9; y++){
                    if (sudo.game[x][y]==0){
                        jtf[x][y].setText("");
                        jtf[x][y].setFont(font2);
                        jtf[x][y].setEditable(true);
                        jtf[x][y].setForeground(Color.BLACK);
                    }else{
                        jtf[x][y].setText(Integer.toString(sudo.game[x][y]));
                        jtf[x][y].setEditable(false);
                        jtf[x][y].setFont(font1);
                        jtf[x][y].setBackground(Color.WHITE);
                        jtf[x][y].setForeground(Color.BLACK);
                    }
                }
            }
        
    }
    
}