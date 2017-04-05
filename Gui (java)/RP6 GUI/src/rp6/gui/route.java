
package rp6.gui;

import static rp6.gui.handmatig.snd;
import static rp6.gui.handmatig.teller;

/**
 *
 * @author Jop Veth
 */
public class route extends javax.swing.JFrame {
    
    public static String output = "";
    public int cb;
    public String stopAf;

    public route() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jCheckBox5 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        label3 = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox8 = new javax.swing.JCheckBox();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jCheckBox9 = new javax.swing.JCheckBox();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jCheckBox10 = new javax.swing.JCheckBox();
        jTextField10 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jCheckBox11 = new javax.swing.JCheckBox();
        jTextField11 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox<>();
        jCheckBox12 = new javax.swing.JCheckBox();
        jTextField12 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jCheckBox13 = new javax.swing.JCheckBox();
        jTextField13 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jCheckBox14 = new javax.swing.JCheckBox();
        jTextField14 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jTextField15 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        jTextField5.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel6.setText("meter naar");

        jComboBox5.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voren", "Achteren", "Links", "Rechts", " " }));

        jCheckBox5.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox5.setText("Commando 1:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Route programmeren");
        setResizable(false);

        jPanel1.setBackground(java.awt.SystemColor.textHighlight);

        label3.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setText("Route programmeren");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setText("naar");

        jComboBox1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox1.setText("Commando 1:");

        jButton1.setBackground(new java.awt.Color(0, 255, 0));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton1.setText("S T A R T");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton2.setText("S T O P");

        jCheckBox8.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox8.setText("Commando 2:");

        jTextField8.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel9.setText("naar");

        jComboBox10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox9.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox9.setText("Commando 3:");

        jTextField9.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel10.setText("naar");

        jComboBox12.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox10.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox10.setText("Commando 4:");

        jTextField10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel11.setText("naar");

        jComboBox14.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox11.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox11.setText("Commando 5:");

        jTextField11.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel12.setText("naar");

        jComboBox16.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox12.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox12.setText("Commando 6:");

        jTextField12.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel13.setText("naar");

        jComboBox18.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox13.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox13.setText("Commando 7:");
        jCheckBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox13ActionPerformed(evt);
            }
        });

        jTextField13.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel14.setText("naar");

        jComboBox20.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jCheckBox14.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jCheckBox14.setText("Commando 8:");

        jTextField14.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel15.setText("naar");

        jComboBox22.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voor", "Achter", "Links", "Rechts" }));

        jTextField15.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel16.setText("cm stoppen voor object na laatste commando.");

        jLabel1.setText("cm");

        jLabel3.setText("cm");

        jLabel4.setText("cm");

        jLabel5.setText("cm");

        jLabel7.setText("cm");

        jLabel20.setText("cm");

        jLabel21.setText("cm");

        jLabel22.setText("cm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox14)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox18, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox12)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel10))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel11))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel12))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel13))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel9)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox14, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox16, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox20, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox13)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox22, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox10)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox11)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox12)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox13)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox14)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String stringtoint(int input)
    {
        String route="";
        int x=input/128;
        int y=input%128;
        route+=x+y;
        return route;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String route="";
        //checkbox 1
        if(jCheckBox1.isSelected()){
            if(jComboBox1.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox1.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox1.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox1.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
            route+= stringtoint(Integer.parseInt(jTextField1.getText()))+cb;
        }
        
        //checkbox 2
        if(jCheckBox8.isSelected()){
            if(jComboBox10.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox10.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox10.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox10.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
            route+= stringtoint(Integer.parseInt(jTextField8.getText()))+cb;
        }
        
        //checkbox 3
        if(jCheckBox9.isSelected()){
            if(jComboBox12.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox12.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox12.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox12.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
             route+= stringtoint(Integer.parseInt(jTextField9.getText()))+cb;
        }
        
        //checkbox 4
        if(jCheckBox10.isSelected()){
            if(jComboBox14.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox14.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox14.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox14.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
             route+= stringtoint(Integer.parseInt(jTextField10.getText()))+cb;
        }
        
        //checkbox 5
        if(jCheckBox11.isSelected()){
            if(jComboBox16.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox16.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox16.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox16.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
             route+= stringtoint(Integer.parseInt(jTextField11.getText()))+cb;
        }
                
        //checkbox 6
        if(jCheckBox12.isSelected()){
            if(jComboBox20.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox20.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox20.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox20.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
            output = output + jTextField12.getText() + cb;
        }
        
        //checkbox 7
        if(jCheckBox13.isSelected()){
            if(jComboBox22.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox22.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox22.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox22.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
             route+= stringtoint(Integer.parseInt(jTextField13.getText()))+cb;
        }
        
        //checkbox 8
        if(jCheckBox14.isSelected()){
            if(jComboBox18.getSelectedItem().toString().equals("Voor")){
                cb = 0;
            }else if(jComboBox18.getSelectedItem().toString().equals("Achter")){
                cb = 2;
            }else if(jComboBox18.getSelectedItem().toString().equals("Links")){
                cb = 1;
            }else if(jComboBox18.getSelectedItem().toString().equals("Rechts")){
                cb = 3;
            }
             route+= stringtoint(Integer.parseInt(jTextField14.getText()))+cb;
        }
        
        output = route;
        
        String txt = jTextField15.getText();

        if(txt == null){
            stopAf = "000";
        }else{
            stopAf = txt;
        }
        
        String end = "00";
        
        output = output + end + stopAf;
        
        System.out.println(output);
        
        int int_out = Integer.parseInt(output);

        conv(int_out);   
    }//GEN-LAST:event_jButton1ActionPerformed

     public int conv(int x){
        
        char add = (char) x;
        snd += add;
        
        teller++; 
        
        if(teller >= 1){   
            SendAsync.main(snd);
            teller = 0;
            snd = "";
            System.out.println("Commando verstuurd!" + snd);
        } 
        
        return 0;
    }
    
    private void jCheckBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox13ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new route().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private java.awt.Label label3;
    // End of variables declaration//GEN-END:variables
}
