package rp6.gui;

import java.awt.event.KeyEvent;

/**
 *
 * @author Jop Veth
 * @author Chris Eijken
 */

public class handmatig extends javax.swing.JFrame {

    public int manual_output;           /* deze values worden doorgestuurd naar de RP6 */      
    
    public int v = 0;                   // var naar voor rijden
    public int a = 0;                   // var naar achter rijden
    public int stop;                    // var voor stoppen tussen indrukken pijltje naar voren en naar achteren
    public int lr = 0;                  // var voor stoppen tussen indrukken pijltje naar liks en naar rechhs
    
    public static int teller = 0;       // teller voor convert naar char functie
    public static String snd = "";      // send string voor convert naar char functie
    
    
    public int bc = 0;                  // button counter, zorgt ervoor dat bij het inhouden van een toets niet constant hetzelfde commando wordt verstuurd,.
    
    public handmatig() {
        initComponents();
    }
    
    //delay funcite om het voorkomen van het spammen van commands (van de toetsen) te voorkomen
    public void delay(){
        int countdown = 1;
        while (countdown < 10000){

            ++countdown;
            if(countdown == 10000 ){
                countdown = 10001;
                System.out.println(countdown);
            }
        }
    }
    
    //functie voor het converteren naar chars, zodat data kan worden verstuurd over de xbee
    public static int conv(int x){
        
        char add = (char ) x;
        snd += add;
        SendAsync.main(snd);
        snd = "";
        System.out.println("Commando verstuurd!");

        return 0;
    }
    
    
    
    //--------- BEGIN  auto generated code voor opbouw gui door NetBeans (java) --------- >
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label3 = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label4 = new java.awt.Label();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        label5 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Handmatige besturing");
        setResizable(false);

        jPanel1.setBackground(java.awt.SystemColor.textHighlight);

        label3.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setText("Handmatige besturing");

        jTextField1.setBackground(java.awt.SystemColor.textHighlight);
        jTextField1.setForeground(java.awt.SystemColor.activeCaption);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(java.awt.SystemColor.controlHighlight);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton1.setText("S T O P");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(java.awt.SystemColor.controlHighlight);
        jRadioButton2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Korte afstanden");
        jRadioButton2.setToolTipText("");
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });

        jRadioButton1.setBackground(java.awt.SystemColor.controlHighlight);
        jRadioButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jRadioButton1.setText("Lange afstanden");
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });

        jPanel3.setBackground(java.awt.SystemColor.controlHighlight);

        jPanel2.setBackground(java.awt.SystemColor.activeCaption);

        label4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setText("Instellingen");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(159, 159, 159))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(java.awt.SystemColor.controlHighlight);

        jPanel4.setBackground(java.awt.SystemColor.activeCaption);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        label5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(255, 255, 255));
        label5.setText("Besturing");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setText("Gebuirk de pijltjes-toetsen om de RP6 te besturen ");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setText("Gebruik de spatiebalk om een noodstop te maken");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setText("Gebruik enter om tussen de lange- de korteafstand-modus te switchen");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("ACHTER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("VOOR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("LINKS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("RECHTS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    //---------  EINDE  auto generated code voor opbouw gui door NetBeans (java) --------- >
    
    
    // in de funcite hieronder worden keypresses gesignaleerd en worden de buttons aangesproken die hiermee verbonden zijn in de GUI
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
            
        if(bc==0){
            /* Pijltje omhoog indrukken */
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton3.doClick();
            
        /* Pijltje omlaag indrukken */
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton2.doClick();

         /* Pijltje naar rechts indrukken */
        }else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){            
            jButton6.doClick();
        
         /* Pijltje naar links indrukken */
        }else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton5.doClick();
               
        
        /* Spatiebalk / NOODSTOP */
        }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            System.out.println("STOP");
            manual_output = 127; //127 stop code op de arduino
            conv(manual_output); //conv functie converteerd data en verstuurd het door naar de xbee verzend functie
            v = 0; 
  
         /* Enter / Wisselen tussen ingestelde afstand en de korte afstands modus */
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(jRadioButton1.isSelected()){
                jRadioButton2.setSelected(true);
                jRadioButton1.setSelected(false);
                System.out.println("Korte afstand modus");
                //korte afstand modus geselecteerd en radiobuttn in gui selected
            }else if(jRadioButton2.isSelected()){
                jRadioButton1.setSelected(true);
                jRadioButton2.setSelected(false);
                System.out.println("Lange afstand modus");
                //lange afstand modus geselecteerd en radiobuttn in gui selected
            }
        }} 
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        
        /* veranderen van de enabled radiobutton */
        if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
        }
        jTextField1.requestFocus(); //er moet worden gefocusd op dit textfield omdat deze de keypresses registreerd
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
        
        /* veranderen van de enabled radiobutton */
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
        }
        jTextField1.requestFocus(); 
    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        /* Stop knop / NOODSTOP */
        manual_output = 127;
        jTextField1.requestFocus();
        conv(manual_output);
    }//GEN-LAST:event_jButton1ActionPerformed

    // de functie hieronder herkent de keyreleases van de pijltjes toetsen
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        // pijltje omhoog loslatenn
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            if(jRadioButton2.isSelected()){
                manual_output = 127;
                System.out.println("STOP");
                conv(manual_output);
                v = 0;
            }
            
        // pijltje omlaag loslaten   
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            if(jRadioButton2.isSelected()){
                manual_output = 127;
                System.out.println("STOP");
                conv(manual_output);
                v = 0;
                a = 1;
            }
                
        // pijltje naar rechts loslaten
        }else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            if(jRadioButton2.isSelected() || jRadioButton1.isSelected()){
                if(v==1){ //als de rp6 al naar voren rijdt en dit pijltje wordt losgelaten moet hij weer rechtuit gaan rijden.
                    manual_output = 1;
                    System.out.println("Weer naar voor .....");
                }else{
                    manual_output = 127;
                    System.out.println("STOP");// als de robot niet naar voren rijdt stopt hij.
                }
            }else if(jRadioButton2.isSelected() && v==1){//als de rp6 al naar voren rijdt en dit pijltje wordt losgelaten moet hij weer rechtuit gaan rijden.
                manual_output = 1;
                System.out.println("Weer naar voor .....");
                conv(manual_output);
            }else if(jRadioButton1.isSelected() && a==1){//als de rp6 al naar achter rijdt en dit pijltje wordt losgelaten moet hij weer achteruit gaan rijden.
                a = 1;
                manual_output = 2;
                System.out.println("Weer naar achter .....");
            }
            conv(manual_output);
                     
        //pijtlje naar links loslaten    
        }else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            if(jRadioButton2.isSelected() || jRadioButton1.isSelected()){
                if(v==1){ //als de rp6 al naar voren rijdt en dit pijltje wordt losgelaten moet hij weer rechtuit gaan rijden.
                    manual_output = 1;
                    System.out.println("Weer naar voor .....");
                }else{ // als de robot niet naar voren rijdt stopt hij.
                    manual_output = 127;
                    System.out.println("STOP");
                }
            }else if(jRadioButton2.isSelected() && v==1){//als de rp6 al naar voren rijdt en dit pijltje wordt losgelaten moet hij weer rechtuit gaan rijden.
                manual_output = 1;
                System.out.println("Weer naar voor .....");
                conv(manual_output);
            }else if(jRadioButton1.isSelected() && a==1){//als de rp6 al naar achter rijdt en dit pijltje wordt losgelaten moet hij weer achteruit gaan rijden.
                a = 1;
                manual_output = 2;
                System.out.println("Weer naar achter .....");
            }
            conv(manual_output);
        
        }bc=0;  // herstel de buttoncounter
    }//GEN-LAST:event_jTextField1KeyReleased

    //pijltje omhoog KNOP
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        v = 1; //rp6 moet vooruit, dus vooruit var moet op 1
        a = 0;  // hij moet niet achteruit, dus var achteruit moet op 0
        if(stop==1){// als de robot naar achtijr rijdt, en er wordt op het pijltje naar voor gedrukt, stopt de robot eerst, en bij een volgende push rijdt hij pas naar voor.
            manual_output = 127;
            System.out.println("STOP");
            stop = 0;
        }else{// als de robot stilstaat begit hij met achteruit rijden
            manual_output = 1;
            // Bij lange afstand modus: DEZE MODUS WERKT NIET
            if(jRadioButton1.isSelected()){
                stop = 1;
                System.out.println("Vooruit rijden ..... ");
                bc++;// buttoncounter optellen , zodat commandos niet gespamt worden
            }
            /* Bij korte afstands modus: */
            else if(jRadioButton2.isSelected()){
                System.out.println("Vooruit rijden .....");
                bc++;// buttoncounter optellen , zodat commandos niet gespamt worden
            }
        }
        conv(manual_output);
    }//GEN-LAST:event_jButton3ActionPerformed

    // pijltje omlaag KNOP
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        v = 0; // hij moet niet vooruit, dus var vooruit moet op 0
        a = 1; //rp6 moet achteruit, dus achteruit var moet op 1
        if(stop==1){// als de robot naar voor rijdt, en er wordt op het pijltje naar achter gedrukt, stopt de robot eerst voor, en bij een volgende push wordt er pas naar achter gereden
            manual_output = 127;
            System.out.println("STOP");
            stop = 0;
        }else{ //naar achter rijden
            manual_output = 2;
            System.out.println("Achteruit rijden .....");   
            stop = 1;
        }
        bc++;// buttoncounter optellen , zodat commandos niet gespamt worden
        conv(manual_output);
    }//GEN-LAST:event_jButton2ActionPerformed

    // pijltje naar links KNOP
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(v==1){//naar voren en naar links rijden      
            manual_output = 5;
            System.out.println("Naar links + voor rijden .....");
        }else{ //om as draaien naar links
            manual_output = 4;
            System.out.println("Naar links rijden .....");
        }
        conv(manual_output);
        bc++;  // buttoncounter optellen , zodat commandos niet gespamt worden
    }//GEN-LAST:event_jButton5ActionPerformed

    // pijltje naar rechts KNOP
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(v==1){//naar voor en rechts rijden als de robot al naar voren rijdt
            manual_output = 9;
            System.out.println("Naar rechts + voor rijden .....");
        }else{ // naar voor rijden
            manual_output = 8;
            System.out.println("Naar rechts rijden .....");
        } 
        conv(manual_output);
        bc++;// buttoncounter optellen , zodat commandos niet gespamt worden
    }//GEN-LAST:event_jButton6ActionPerformed

    //main function
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
            java.util.logging.Logger.getLogger(handmatig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(handmatig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(handmatig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(handmatig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new handmatig().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    // End of variables declaration//GEN-END:variables
}
