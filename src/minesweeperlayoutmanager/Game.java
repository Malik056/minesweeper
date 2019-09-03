/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperlayoutmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Taha
 */
public class Game extends javax.swing.JFrame {

    /**
     * Creates new form Game
     */
    
    boolean auto = false;
    boolean started = false;
    Vector<Integer> bombIndexes;
    int noOfColumns = 9;
    int noOfRows = 9;
    int buttonHeight = 60; //(this.getHeight()-100) % noOfRows ;
    int buttonWidth = 60;//(this.getWidth() - 200) % noOfColumns;
   
    JMenuBar mb;
    JMenu menu;
    JMenuItem menuItem;
    
    int panelWidth = buttonWidth * noOfColumns;
    int panelHeight = buttonHeight * noOfRows;
    int noOfBombs = 10;
    int frameWidth;
    int frameHeight;
    
    Color borderColor = Color.DARK_GRAY;
    Color buttonColor = Color.BLUE;
    
   
    JPanel mainPanel;
    JPanel top; 
    JPanel middle;
    JPanel bottom;
    ArrayList<JButton> buttons;
    
    JLabel bombLabel;
    JLabel clockLabel;
    JLabel bombCount;
    JLabel time;
    MyTimer score;
    
    Icon clockIcon;
    Icon flagIcon;
    Icon hiddenBombIcon;
    Icon bombIcon;
    
    protected Icon getIcon(String name)
    {
        
        ImageIcon imgIcon = new ImageIcon(getClass().getResource(name));
        Image img = imgIcon.getImage();  
        Image newimg = img.getScaledInstance( buttonWidth,buttonHeight, java.awt.Image.SCALE_SMOOTH ) ;  
        Icon icon;
        icon = new ImageIcon( newimg );
        return icon;
        
    }
    
    
    protected Border getMyBorder()
    {
            Border border = new Border() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                    
                Color oldColor = g.getColor();
                int i;

                for (i = 0; i < 4; i++) {
                    g.setColor(Color.black);
                    g.drawRect(x + i, y + i, width - i - i - 1, height - i - i - 1); //White Rectangle
                }
                for (i = 0; i < 2; i++) {
                    g.setColor(Color.black);
                    g.drawLine(x + i, y + i, (width - x) - (i * 2), y + i); //Top Outer Edge
                    g.drawLine(x + width, y + i, x + width, (height - y) - (i * 2));  //Left Outer Edge
                }
                for (i = 2; i < 4; i++) {
                    g.setColor(Color.gray);
                    g.drawLine(x + i, y + i, (width - x) - (i * 2), y + i); //Top Inner Edge
                    g.drawLine(x + width, y + width, x + width, (height - y) - (i * 2)); //Left Inner Edge

                }
                g.setColor(oldColor);
                }

                @Override
                public Insets getBorderInsets(Component c) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    return new Insets(4,4,4,4);
                }

                @Override
                public boolean isBorderOpaque() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    return true;
                }
            };
            return border;
    }
    
    protected void addToPanel()
    {
        for(int i = 0; i < noOfRows*noOfColumns;i++)
        {
            JButton button = new JButton();
            button.setText("");
            try {
                button.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("ds.ttf")).deriveFont(64f));
            } catch (FontFormatException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.LIGHT_GRAY, Color.lightGray));
            button.setBorder(BorderFactory.createLoweredBevelBorder());
            button.setBackground(buttonColor);
            button.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
            button.setMinimumSize(new Dimension(buttonWidth,buttonHeight));
            button.setMaximumSize(new Dimension(buttonWidth,buttonHeight));
            
//            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            
            button.removeMouseListener(button.getMouseListeners()[0]);
            button.addMouseListener(mouseListener);
//            buttons[i].setBounds(panel.getX() + ((i%(noOfColumns)) * buttonWidth), panel.getY() + (i/noOfColumns)*buttonHeight, buttonWidth, buttonHeight);
            buttons.add(button);
            middle.add(button);
        }
    }
    
    
    
    public int checkSurrounding(int index)
    {
        int bombCount = 0;

        int ind = index - 10;
        
        for(int j = 0; j < 3; ind += 9,j++)
        {
            for(int i = ind; i<ind+3; i++)
            {
                if(i != index)
                {
                    if(i>=0 && i<81)
                    {
                        if((index % 9 == 0 && i == ind) || (index % 9 == 8 && i == ind + 2))
                        {
                            
                        }
                        else
                        {
                            if(bombIndexes.contains(i))
                            {
                                bombCount++;
                            }
                        }
                    }
                }
            }
        }
        
        return bombCount;
    }
    
    public void clickSurrounding(int index)
    {
        
        int ind = index - 10;
        
        for(int j = 0; j < 3; ind += 9,j++)
        {
            for(int i = ind; i<ind+3; i++)
            {
                if(i != index)
                {
                    if(i>=0 && i<81)
                    {
                        if((index % 9 == 0 && i == ind) || (index % 9 == 8 && i == ind + 2))
                        {
                            
                        }
                        else if(buttons.get(i).isEnabled() && buttons.get(i).getIcon() != flagIcon) 
                        {
                            MouseEvent mEvent = new MouseEvent(buttons.get(i),InputEvent.BUTTON1_MASK,0,0,100,100,1,false);
//                            mEvent = new MouseEvent();
                            MouseListener mL;                    
                            //                            buttons.get(i).doClick();
                            mL = (buttons.get(i)).getMouseListeners()[0];
                            auto = true;
                            mL.mouseReleased(mEvent);
                        }
                    }
                }
            }
        }
    }
    
    public int checkSurroundingFlags(int index)
    {
        int flagCount = 0;
        
        int ind = index - 10;
        
        for(int j = 0; j < 3; ind += 9,j++)
        {
            for(int i = ind; i<ind+3; i++)
            {
                if(i != index)
                {
                    if(i>=0 && i<81)
                    {
                        if((index % 9 == 0 && i == ind) || (index % 9 == 8 && i == ind + 2))
                        {
                            
                        }
                        else
                        {
                            if(buttons.get(i).getIcon() != null)
                            {
                                
                                flagCount++;                            
                            
                            }
                            
                        }
                    }
                }
            }
        }
        
        return flagCount;
    }
    
    
    public boolean isGameComplete()
    {
        boolean complete = true;
        
        for(int i = 0; i < 81 && complete; i++)
        {
            
            if(buttons.get(i).isEnabled() && !bombIndexes.contains(i))
            {
                complete = false;
            }
            
        }
        
        return complete;
        
    }
    
    
    public void gameOver(String status)
    {
        
        score.cancel();
        

        BufferedReader br = null;
            JFrame frame = this;
            frame.setEnabled(false);
            
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//        myframe.setCursor(
//        Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            SwingUtilities.updateComponentTreeUI(frame);
//            frame.invalidate();
//            frame.validate();
//            frame.repaint();

//        myframe.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));


        middle.setEnabled(false);
        
        
        if(status.equalsIgnoreCase("You Win"))
        {
            GameWonMenu.game = this;
            GameWonMenu menu = new GameWonMenu();
            menu.setVisible(true);
        }   
        else
        {
            GameWonMenu.game = this;
            GameWonMenu menu = new GameWonMenu();            
            
            GameOverMenu.game = this;
            GameOverMenu menu1 = new GameOverMenu();            
            menu.setVisible(true);
            menu1.setVisible(true);
        }
//        String cs = jLabel3.getText();
//        int currentScore = Integer.valueOf(cs);
//        String hs = jLabel6.getText();
//        int highScore = Integer.valueOf(hs);
            
//            Menu menu = new Menu();
//            
//        
//                        
//        if(status.equalsIgnoreCase("You Win") && (currentScore < highScore || highScore == 0))
//        {
//            highScore = currentScore;
//         
//
//            try {
//                PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
//                writer.println(highScore);
//                writer.close();
//
//            } catch (IOException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            
//            status = "BestScore";
//            menu.setScore(String.valueOf(currentScore));
//            
//        }
//        else
//        {
//            menu.setScore("Your Score: " + String.valueOf(currentScore));
//        }
//            
//            menu.setLabel(status);
//            menu.setVisible(true);
////        this.dispose();
    }
    
    
    MouseListener mouseListener = new MouseAdapter() {
         
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int modifiers = mouseEvent.getModifiers();
                if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
                    
                    if(!started)
                    {
                        started = true;
                        score = new MyTimer(time);
                        if(!auto){
                        intializeBombs(buttons.indexOf((JButton)mouseEvent.getSource()));
                        }
                    }
                    
                    // Mask may not be set properly prior to Java 2
                    // See SwingUtilities.isLeftMouseButton() for workaround
//                    System.out.println("Left button pressed.");
                }
                if ((modifiers & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
//                    System.out.println("Middle button pressed.");
                    

                }
                if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
//                    System.out.println("Right button pressed.");
                }
                if(mouseEvent.getClickCount() == 2)
                {
                    final JButton jbutton = (JButton) mouseEvent.getSource();
                    
                    if(jbutton == null)
                    {
                        return;
                    }
                     if(!jbutton.isEnabled() && !jbutton.getText().equals("") && !jbutton.getText().equals("X") )
                        {
                            if(checkSurroundingFlags(buttons.indexOf(jbutton)) == Integer.valueOf(jbutton.getText()))
                            {
                                clickSurrounding(buttons.indexOf(jbutton));
                            }
                            else
                            {
                                final String text = jbutton.getText();
//                                jbutton.setEnabled(true);
                                jbutton.setText("X");
                                final Font old = jbutton.getFont();
//                                jbutton.setFont(new Font("Arial", Font.PLAIN, 14));
                                jbutton.setForeground(Color.RED);
                                
                                java.util.Timer timer= new java.util.Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                     
                                     jbutton.setFont(old);
                                     jbutton.setForeground(getColor(Integer.valueOf(text)));
                                     jbutton.setText(text);
                                // Your database code here
                                    }
                                }, 2000);

                            }
                        }
                }
            }
            
            public void autoClick(MouseEvent mouseEvent)
            {
                auto = false;
                JButton jbutton = (JButton)mouseEvent.getSource();                
//                if(jbutton.isEnabled()){

                jbutton.setEnabled(false);
//                jbutton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, new Color(192,192,192)));
                jbutton.setBorder(getMyBorder());

                if(jbutton == null)
                {
                    return;
                }
                click(jbutton);
            }

            
            public void click(JButton jbutton)
            {
                
                int index = buttons.indexOf(jbutton);

                        if(!bombIndexes.contains(index))
                        {
                            if(jbutton.getIcon() == null)
                            {

                                int value = checkSurrounding(index);

                                jbutton.setEnabled(false);
                                
//                                jbutton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, new Color(192,192,192)));
                                jbutton.setBorder(getMyBorder());
                                
                                
                                if(value != 0)
                                {
                                    buttons.get(index).setText(String.valueOf(value));
                                    buttons.get(index).setForeground(getColor(value));
                                    
                                }
                                else
                                {
                                    clickSurrounding(index);
                               
                                }
                                if(isGameComplete())
                                {
                                    
                                    bombLabel.setText("0");
                                    
                                    for(int i = 0; i < 10;i++)
                                    {
                                        if(buttons.get(bombIndexes.get(i)).getIcon() == null){
                                            
                                            buttons.get(bombIndexes.get(i)).setIcon(hiddenBombIcon);
                                            
                                        }
                                    }
                                    
                                    gameOver("You Win");
                                }
                       }

                        }
                        else
                        {

                            buttons.get(index).setEnabled(false);
                            buttons.get(index).setDisabledIcon(bombIcon);
                            buttons.get(index).setBorder(getMyBorder());

                            for(int i = 0; i < 81; i++)
                            {
                                buttons.get(i).removeAll();
                                buttons.get(i).removeMouseListener(mouseListener);
                                
                                if(i!= index && bombIndexes.contains(i))
                                {
                                    buttons.get(i).setBorder(getMyBorder());
                                    buttons.get(i).setEnabled(false);
                                    buttons.get(i).setIcon(hiddenBombIcon);
                                }

                            }
                            gameOver("You Lose");

                        }
            
            }
            
            public void mouseReleased(MouseEvent mouseEvent) {
                
                if(auto)
                {
                    autoClick(mouseEvent);
                    return;
                }
                
                JButton jbutton = (JButton)mouseEvent.getSource();
                
                    if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
    //                    System.out.println("Left button released.");


                        if(jbutton == null || !jbutton.isEnabled() || jbutton.getIcon() != null)
                        {
                            return;
                        }
                        
                        click(jbutton);
                        
                    }
                    if (SwingUtilities.isMiddleMouseButton(mouseEvent)) {
    //                    System.out.println("Middle button released.");

                        if(!jbutton.isEnabled())
                        {
                            if(checkSurroundingFlags(buttons.indexOf(jbutton)) == Integer.valueOf(jbutton.getText()))
                            {
                               clickSurrounding(buttons.indexOf(jbutton));
                            }
                            else
                            {
                            }
                        }


                    }
                    if (SwingUtilities.isRightMouseButton(mouseEvent)) {
    //                    System.out.println("Right button released.");
                        
                        if(!jbutton.isEnabled())
                        {
                            return;
                        }
                        int count = Integer.valueOf(bombCount.getText());
                        if(jbutton.getIcon() == null)
                        {
                            jbutton.setIcon(flagIcon);
                            count--;
                            
                        }
                        else
                        {
                            jbutton.setIcon(null);
                            count++;
                        }
                        bombCount.setText(String.valueOf(count));
                        jbutton.setEnabled(true);

                    }
//                }
            }
        };
    
    MouseListener mouseListener1 = new MouseAdapter(){
        @Override
        public void mouseReleased(MouseEvent e) {
//            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            
            Game game = new Game();
            game.setVisible(true);
            restart();
            
        }
    
        
    };
    
    public void restart()
    {
        this.dispose();
    }
     
     public Color getColor(int index)
     {
        switch (index) {
            case 1:
                return Color.BLACK;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.DARK_GRAY;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.ORANGE;
            default:
                return Color.ORANGE;
        }
     }
     
    
    protected void intializeBombs(int startIndex)
    {
        bombIndexes = new Vector<Integer>();
        
        Random rand = new Random();
        for(int i = 0; i < 10; i++)
        {
            int num = rand.nextInt(81);
            if(bombIndexes.contains(num) || (num >= startIndex - 10 && num <= startIndex + 10 ))
            {
                i--;
            }
            else
            {
                
            bombIndexes.add(num);
            }
        }
    }
    
    
    public void init(){
        
        
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
//        getContentPane().setLayout(new BorderLayout());
//        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

        clockLabel = new JLabel(bombIcon);
        clockLabel.setSize(new Dimension(buttonWidth,buttonHeight));
        
        mb = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        mb.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("New",
                                 KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Start A new Game");
        
        menuItem.addMouseListener(mouseListener1);
        
        JMenuItem menuItem1 = new JMenuItem("Statistics",
                                 KeyEvent.VK_T);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription(
                "Start A new Game");
        
        menu.add(menuItem);
        menu.add(menuItem1);
        
        
        
        this.setJMenuBar(mb);
        
        bombIcon = getIcon("bomb.png");
        flagIcon = getIcon("flag.png");
        hiddenBombIcon = getIcon("hiddenBomb.png");
        clockIcon = getIcon("clock.png");
        
        buttons = new ArrayList<JButton>();
        
        mainPanel = new JPanel();
        frameWidth = panelWidth + buttonWidth*4;
        frameHeight = panelHeight + buttonHeight*4;
//        getContentPane().setPreferredSize(new Dimension(frameWidth,frameHeight));
//        getContentPane().setMinimumSize(new Dimension(frameWidth,frameHeight));
//        getContentPane().setMaximumSize(new Dimension(frameWidth,frameHeight));

        this.setBounds(400,200,frameWidth,frameHeight);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
//        buttons = new JButton[noOfColumns*noOfRows];
        mainPanel.setBackground(borderColor);
        mainPanel.setMaximumSize(new Dimension(buttonWidth*(noOfColumns + 4),frameHeight));
//        mainPanel.setPreferredSize(new Dimension(buttonWidth*(noOfColumns + 4),frameHeight));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        
        
        top = new JPanel();
        top.setBackground(borderColor);
        top.setMaximumSize(new Dimension(buttonWidth*noOfColumns,(frameHeight - panelHeight) / 2));
        top.setPreferredSize(new Dimension(buttonWidth*noOfColumns,(frameHeight - panelHeight) / 2));
        top.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bottom = new JPanel();
        bottom.setBackground(borderColor);
        bottom.setMaximumSize(new Dimension(buttonWidth*noOfColumns,(frameHeight - panelHeight) / 2));
        bottom.setPreferredSize(new Dimension(buttonWidth*noOfColumns,(frameHeight - panelHeight) / 2));

        bottom.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        middle = new JPanel();
        middle.setPreferredSize(new Dimension(buttonWidth*noOfColumns,panelHeight));
//        middle.setMinimumSize(new Dimension(10,200));
        middle.setMaximumSize(new Dimension(buttonWidth*noOfColumns,panelHeight));
        middle.setBackground(Color.GRAY);
        middle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        middle.setLayout(new GridLayout(noOfRows,noOfColumns,-4,-4));

        addToPanel();
        
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));
        bottom.add(clockLabel);
        clockLabel.setBackground(Color.LIGHT_GRAY);
        
        Border border = BorderFactory.createLoweredBevelBorder();
        
        clockLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),border));
        clockLabel.setOpaque(true);
        clockLabel.setIcon(clockIcon);
        clockLabel.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        clockLabel.setMinimumSize(new Dimension(buttonWidth,buttonHeight));
        clockLabel.setMaximumSize(new Dimension(buttonWidth,buttonHeight));

        time = new JLabel("0",SwingConstants.CENTER);
        time.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),border));
        time.setBackground(buttonColor);
        time.setOpaque(true);
        try {
                time.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("ds.ttf")).deriveFont(64f));
            } catch (FontFormatException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        time.setForeground(Color.WHITE);
        time.setPreferredSize(new Dimension(buttonWidth * 2,buttonHeight));
        time.setMinimumSize(new Dimension(buttonWidth * 2,buttonHeight));
        time.setMaximumSize(new Dimension(buttonWidth * 2,buttonHeight));

        bottom.add(Box.createRigidArea(new Dimension(buttonWidth / 2,buttonHeight)));
        bottom.add(time);
        
        
        bottom.add(Box.createHorizontalGlue());
        
        bombLabel = new JLabel(bombIcon,SwingConstants.CENTER);
        bombLabel.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        bombLabel.setMaximumSize(new Dimension(buttonWidth,buttonHeight));
        bombLabel.setMinimumSize(new Dimension(buttonWidth,buttonHeight));
        bombLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),border));
        bombLabel.setBackground(Color.LIGHT_GRAY);
        bombLabel.setOpaque(true);
        bottom.add(bombLabel);
        bottom.add(Box.createRigidArea(new Dimension(buttonWidth/2,buttonHeight)));
        
        
        bombCount = new JLabel("" + noOfBombs, SwingConstants.CENTER);
        bombCount.setBorder(border);
        bombCount.setBackground(buttonColor);
        bombCount.setOpaque(true);
        try {
                bombCount.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("ds.ttf")).deriveFont(64f));
            } catch (FontFormatException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        bombCount.setForeground(Color.WHITE);
        bombCount.setPreferredSize(new Dimension(buttonWidth * 2,buttonHeight));
        bombCount.setMinimumSize(new Dimension(buttonWidth * 2,buttonHeight));
        bombCount.setMaximumSize(new Dimension(buttonWidth * 2,buttonHeight));
        
        bottom.add(bombCount);
        
        getContentPane().setBackground(Color.BLACK);
        mainPanel.add(top);
        mainPanel.add(middle);
        mainPanel.add(bottom);
        
        getContentPane().add(mainPanel,BorderLayout.CENTER);
        
//        getContentPane().add(buttons[0]);
        
        
    }
    
    public Game() {
        
        
        initComponents();
        this.setResizable(false);
        this.setTitle("Minesweeper");
        init();
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("hiddenBomb.png"));
        this.setIconImage(imgIcon.getImage());
        
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        MsAccess ms = new MsAccess();
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new Game()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
