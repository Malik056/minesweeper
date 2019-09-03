/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperlayoutmanager;

import java.awt.Color;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Taha
 */
public class GameWonMenu extends javax.swing.JFrame {

    /**
     * Creates new form GameWonMenu
     */
    
    public static Game game;
    
    public GameWonMenu() {
        initComponents();
        
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setForeground(Color.WHITE);
   
        exit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.gray, Color.lightGray), BorderFactory.createLineBorder(Color.gray,1)));
        exit.setBackground(Color.lightGray);
        
        play_again.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.gray, Color.lightGray), BorderFactory.createLineBorder(Color.gray,1)));
        play_again.setBackground(Color.lightGray);

        
        setBounds(game.getX()+game.getWidth()/2 - this.getWidth()/2, game.getY()+game.getHeight()/2 - getHeight()/2, getWidth(), getHeight());
        setResizable(false);
        
        
        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        java.util.Date date = new java.util.Date();
//        String todaysDate = (dateFormat.format(date)); //2016/11/16 12:08:43
//        
//        game = null;
//        int nofgames = 0;
//          String myDriver = "org.gjt.mm.mysql.Driver";
//          String myUrl = "jdbc:derby://localhost:1527/minesweeper";
//        
//        // SQL statement for creating a new table
//        String sql = "CREATE TABLE IF NOT EXISTS Statistics (\n"
//                + "	id INT PRIMARY KEY,"
//                + "	date STRING NOT NULL,"
//                + "	Score INT"
//                + ")";
//        
//        try {
//            Connection conn = DriverManager.getConnection(myUrl);
//            java.sql.Statement stmt = conn.createStatement();
//            // create a new table
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        
//        File file = new File("games.txt");
//       
//        PrintWriter writer = null;
//            try {
//                writer = new PrintWriter("games.txt", "UTF-8");
//            } catch (FileNotFoundException ex1) {
//                Logger.getLogger(GameWonMenu.class.getName()).log(Level.SEVERE, null, ex1);
//            } catch (UnsupportedEncodingException ex1) {
//                Logger.getLogger(GameWonMenu.class.getName()).log(Level.SEVERE, null, ex1);
//            }    
//        
//        
//        String line = "";
//        
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            
//            try {
//                line = br.readLine();
//                
//                int n = Integer.valueOf(line);
//                n++;
//                nofgames = n;
//                writer.println(n);
//                
//            } catch (IOException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            } catch (FileNotFoundException ex) {
//            
//            }
//            if(writer != null){
//                writer.println(0);
//                writer.close();
//            }
//
//        
//        
//        
//        
//        String id = "" + 0;
//        
//        
//        
//        String getData = "SELECT id, date, score FROM Statistics ORDER BY SCORE[DESC]";
//        try
//        {
//          // create our mysql database connection
//          Class.forName(myDriver);
//          Connection conn = DriverManager.getConnection(myUrl, "root", "");
//
//          // our SQL SELECT query. 
//          // if you only need a few columns, specify them by name instead of using "*"
//          String query = getData;
//
//          // create the java statement
//          java.sql.Statement st = conn.createStatement();
//
//          // execute the query, and get a java resultset
//          ResultSet rs = st.executeQuery(query);
//          
//          
//            if(rs == null){
//
//                id = "0";
//                this.games_won.setText("0");
//
//            }
//            else
//            {
//                rs.last();
//                int last_id = rs.getInt("id");
//                id = "" + last_id+1;
//                this.games_won.setText(String.valueOf(rs.getFetchSize()));
//                
//                rs.first();
//                int highScore = rs.getInt("Score");
//                
//                if(Integer.valueOf(game.time.getText()) < highScore)
//                {
//                    this.highScore_notify.setVisible(true);
//                    this.bestTime.setText(game.time.getText());
//                }
//                
//                
//            }
//
//            this.date.setText(todaysDate);
//                
//          st.close();
//        }
//        catch (Exception e)
//        {
//          System.err.println("Got an exception! ");
//          System.err.println(e.getMessage());
//        }
//
//        
//        
//
//        String score = game.time.getText();
//        
//        String updateTable = "INSERT INTO STATISTICS VALUES( " + id +" ," + date + "," + score +  " )";
//
//        try {
//            Connection conn = DriverManager.getConnection(myUrl);
//            java.sql.Statement stmt = conn.createStatement();
//            // create a new table
//            stmt.execute(updateTable);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        
//        this.games_played.setText(String.valueOf(nofgames));
//        
//        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        highScore_notify = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        bestTime = new javax.swing.JLabel();
        games_played = new javax.swing.JLabel();
        games_won = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        percentage = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        play_again = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jLabel1.setText("Congratulations, you won the game!");

        highScore_notify.setText("You have the fastest time for this difficulty level!");

        jLabel3.setText("Time:");

        jLabel4.setText("Best time:");

        jLabel5.setText("Games played:");

        jLabel6.setText("Games won:");

        jLabel7.setText("Date:");

        jLabel8.setText("Percentage:");

        jLabel9.setText("Legendary Developer's Production");

        time.setText("jLabel10");

        bestTime.setText("jLabel10");

        games_played.setText("jLabel10");

        games_won.setText("jLabel10");

        date.setText("jLabel10");

        percentage.setText("jLabel10");

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        play_again.setText("Play again");
        play_again.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_againActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(play_again, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bestTime))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(games_played))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(time))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(games_won)))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(date))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(percentage)))
                                .addGap(0, 9, Short.MAX_VALUE)))))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addComponent(highScore_notify))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(highScore_notify)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(time))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bestTime))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(games_played))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(games_won)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(date))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(percentage))))
                .addGap(25, 25, 25)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(play_again, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed

        game.dispose();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void play_againActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play_againActionPerformed
        // TODO add your handling code here:
        
        
        game.dispose();
        Game newGame = new Game();
        newGame.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_play_againActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(GameWonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWonMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bestTime;
    private javax.swing.JLabel date;
    private javax.swing.JButton exit;
    private javax.swing.JLabel games_played;
    private javax.swing.JLabel games_won;
    private javax.swing.JLabel highScore_notify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel percentage;
    private javax.swing.JButton play_again;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
