/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programadornovato.javagui;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eugenio
 */
public class Formulario extends javax.swing.JFrame {
    DefaultTableModel modelo=new DefaultTableModel();
    Statement ejecutor=null;
    Connection con;
    String driver="com.mysql.cj.jdbc.Driver";
    String user="eugenio";
    String pass="123456";
    String url="jdbc:mysql://localhost:3306/empleados?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected void cargaTabla(){
        colorTabla c=new colorTabla();
        modelo.setRowCount(0);
        String datos[]=new String[4];
        String query="select * from empleados;";
        ResultSet rs;
        try {
            ejecutor=con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs=ejecutor.executeQuery(query);
            while(rs.next()==true){
                datos[0]=rs.getString("id");
                datos[1]=rs.getString("nombre");
                datos[2]=rs.getString("puesto");
                datos[3]=rs.getString("edad");
                modelo.addRow(datos);
            }
            tablaEmpleados.setModel(modelo);
            for (int i = 0; i < tablaEmpleados.getColumnCount(); i++) {
                tablaEmpleados.getColumnModel().getColumn(i).setCellRenderer(c);
            }
            
            
        } catch (Exception e) {
        }
    }
    protected void buscarTabla(String nombre,String puesto,String edad){
        modelo.setRowCount(0);
        String datos[]=new String[4];
        String where=" where 1=1 ";
        //Si el nombre no esta vacio
        if(nombre.isEmpty()==false){
            where=where+" and nombre='"+nombre+"' ";
        }
        //Si el puesto no esta vacio
        if(puesto.isEmpty()==false){
            where=where+" and puesto='"+puesto+"' ";
        }
        //Si la edad no esta vacio
        if(edad.isEmpty()==false){
            where=where+" and edad='"+edad+"' ";
        }
        String query="select * from empleados "+where+" ;";
        ResultSet rs;
        try {
            ejecutor=con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs=ejecutor.executeQuery(query);
            while(rs.next()==true){
                datos[0]=rs.getString("id");
                datos[1]=rs.getString("nombre");
                datos[2]=rs.getString("puesto");
                datos[3]=rs.getString("edad");
                modelo.addRow(datos);
            }
            tablaEmpleados.setModel(modelo);
            
        } catch (Exception e) {
        }
    }
    public void conectar(){
        con=null;
        try {
            Class.forName(driver);
            con=(Connection) DriverManager.getConnection(url,user,pass);
            if(con!=null){
                estadoCon.setText("Conexion exitosa");
            }
        } catch (Exception e) {
            estadoCon.setText("Conexion no exitosa "+e);
        }
    }
    /**
     * Creates new form Formulario
     */
    public Formulario() {
        initComponents();
        modelo.addColumn("id");
        modelo.addColumn("nombre");
        modelo.addColumn("puesto");
        modelo.addColumn("edad");
        conectar();
        cargaTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuEditar = new javax.swing.JMenuItem();
        menuBorrar = new javax.swing.JMenuItem();
        estadoCon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        porNombre = new javax.swing.JTextField();
        porPuesto = new javax.swing.JTextField();
        porEdad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPuesto = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        menuEditar.setText("Editar");
        menuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuEditar);

        menuBorrar.setText("Borrar");
        menuBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaEmpleados= new javax.swing.JTable(){
            public boolean isCellEditable(int row,int col){
                for(int i=0;i<tablaEmpleados.getRowCount();i++){
                    if(row==i){
                        return false;
                    }
                }
                return true;
            }
        };
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "id", "nombre", "puesto", "edad"
            }
        ));
        tablaEmpleados.setComponentPopupMenu(jPopupMenu1);
        tablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEmpleados);

        jLabel1.setText("Por nombre");

        jLabel2.setText("Por puesto");

        jLabel3.setText("Por edad");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre");

        jLabel5.setText("Puesto");

        jLabel6.setText("Edad");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(25, 25, 25)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(porNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(porPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(porEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscar))))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(estadoCon, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 129, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(estadoCon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(porNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarTabla(porNombre.getText(),porPuesto.getText(),porEdad.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosMouseClicked
        
    }//GEN-LAST:event_tablaEmpleadosMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        PreparedStatement preparar=null;
        String query="update empleados set "
                + "nombre='"+txtNombre.getText()+"', "
                + "puesto='"+txtPuesto.getText()+"', "
                + "edad='"+txtEdad.getText()+"' "
                + "where id='"+tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(),0)+"'; ";
        try {
            preparar=con.prepareStatement(query);
            preparar.executeUpdate();
            cargaTabla();
        } catch (Exception e) {
            System.out.println(e);
        }
        btnEditar.setEnabled(false);
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void menuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarActionPerformed
            int registro=tablaEmpleados.getSelectedRow();
            txtNombre.setText( tablaEmpleados.getValueAt(registro, 1).toString() );
            txtPuesto.setText( tablaEmpleados.getValueAt(registro, 2).toString() );
            txtEdad.setText( tablaEmpleados.getValueAt(registro, 3).toString() );
            btnEditar.setEnabled(true);
    }//GEN-LAST:event_menuEditarActionPerformed

    private void menuBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBorrarActionPerformed
        PreparedStatement pre=null;
        String query="delete from empleados "
                + "where id='"+tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 0)+"'; ";
        try {
            pre=con.prepareStatement(query);
            pre.executeUpdate();
            cargaTabla();
            txtNombre.setText("");
            txtPuesto.setText("");
            txtEdad.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_menuBorrarActionPerformed

    protected void agregar(){
        String mensajeError="";
        String query="";
        PreparedStatement preparar=null;
        if(txtNombre.getText().isEmpty()==true){
            mensajeError=mensajeError+" Nombre no puede esta vacio\n ";
        }
        if(txtPuesto.getText().isEmpty()==true){
            mensajeError=mensajeError+" Puesto no puede esta vacio\n ";
        }
        if(txtEdad.getText().isEmpty()==true){
            mensajeError=mensajeError+" Edad no puede esta vacio\n ";
        }
        
        if(mensajeError.isEmpty()==true){
            query="insert into empleados "
                    + "(   nombre,                   puesto,                   edad) values"
                    + "('"+txtNombre.getText()+"','"+txtPuesto.getText()+"','"+txtEdad.getText()+"') ";
            try {
                preparar=con.prepareStatement(query);
                preparar.executeUpdate();
                cargaTabla();
            } catch (Exception e) {
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, mensajeError);
        }
        
    }
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
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JLabel estadoCon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuBorrar;
    private javax.swing.JMenuItem menuEditar;
    private javax.swing.JTextField porEdad;
    private javax.swing.JTextField porNombre;
    private javax.swing.JTextField porPuesto;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPuesto;
    // End of variables declaration//GEN-END:variables
}
