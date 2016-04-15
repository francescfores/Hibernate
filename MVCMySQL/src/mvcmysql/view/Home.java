/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mvcmysql.controller.HomeController;
import mvcmysql.model.Post;
import mvcmysql.model.Usuari;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mvcmysql.model.Grup;

/**
 *
 * @author francesc
 */
public class Home extends javax.swing.JFrame {

    public static String post = "post";
    public static String uploadPhoto = "photo";
    public int id;
    //Posts
    public static String nomPost = "";
    public static String comentaris = "";
    public static String dataPost;
    public static String fotoPost;
    
    String ruta;
    final JFileChooser f = new JFileChooser();
    
    //public static final String insertarPost = "InsertarPost";
    public static final String borrarPost = "BorrarPost";
    public static final String modificarPost = "ModificarPost";

    public static final String addUser = "Agregar usuari";
    public static final Usuari user1 = null;
    public static Usuari user2 = null;

    //Grup
    public static final String insertarGrup = "InsertarGrup";
    public static final String addGroup = "Agregar grup";
    public static ArrayList<Grup> addedGroup = new ArrayList();
    //Users 
    public static ArrayList<Usuari> addedusers = new ArrayList();


    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.setVisible(true);
        jButton1.setText(uploadPhoto);
        jButton2.setText(post);
        jButton3.setText(insertarGrup);
        jButton4.setText(addUser);
        jButton5.setText(addGroup);
    }

    public void carregaPost(ArrayList<Post> arrayList) {
        carregaPosts(new JPanel(), arrayList);
    }
    public void carregaUsuaris(ArrayList<Usuari> arrayList) {
        /*javax.swing.JPanel panel = new javax.swing.JPanel(new GridLayout(arrayList.size(), 1, 0, 0));
         //panel.setLayout(new FlowLayout(FlowLayout.LEFT));
         panel.setBackground(new java.awt.Color(188, 204, 255));

         for (int i = 0; i < arrayList.size();) {
         Usuari usuari = arrayList.get(i);
         panel.add((Component) jPanelUsuaris(usuari, arrayList.size()));
         i = i + 1;
         };
         jScrollPane2.setViewportView(panel);*/

        DefaultListModel listModel = new DefaultListModel<>();

        JList countryList = new JList<>(listModel);
        for (int i = 0; i < arrayList.size();) {
            Usuari usuari = arrayList.get(i);
            listModel.addElement(usuari);
            i = i + 1;
        };

        countryList.setCellRenderer(new UserRenderer());
        //String selected = countryList.getSelectedValue().toString();
        jScrollPane2.setViewportView(countryList);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                addedusers.clear();
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.print("  Selections: ");
                        }
                        System.out.print(selections[i] + "/" + selectionValues[i] + " ");
                        addedusers.add((Usuari) selectionValues[i]);
                    }

                }
            }
        };
        countryList.addListSelectionListener(listSelectionListener);
    }
    public void loadContacts(Usuari usauri) {

       DefaultListModel listModel = new DefaultListModel<>();

        JList countryList = new JList<>(listModel);
        
        for (int i = 0; i < usauri.getAddUser().size();) {
            //Usuari usuari = usauri.getAddUser().get(i);
            listModel.addElement(usauri.getAddUser().get(i));
            i = i + 1;
        };

        countryList.setCellRenderer(new UserRenderer());
        //String selected = countryList.getSelectedValue().toString();
        jScrollPane3.setViewportView(countryList);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                addedusers.clear();
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.print("  Selections: ");
                        }
                        System.out.print(selections[i] + "/" + selectionValues[i] + " ");
                        addedusers.add((Usuari) selectionValues[i]);
                    }

                }
            }
        };
        countryList.addListSelectionListener(listSelectionListener);
    }
    public javax.swing.JButton passarBotoPost() {
        return jButton2;
    }
    public javax.swing.JButton CreateGroup() {
        return jButton3;
    }
    public javax.swing.JButton passarBotoUploadPhoto() {
        return jButton1;
    }
    public void carregaUsusari(Usuari usuari) {
        jLabel2.setText(usuari.get2_nom() + " " + usuari.get3_cognoms());
        jLabel3.setText(usuari.get4_dataNaixement());

        jLabel4.setText(Integer.toString(usuari.get5_edat()));
        jLabel5.setText(usuari.get6_treball());

        BufferedImage img = null;
        try {
            //FileOutputStream fos = new FileOutputStream("images\\output.jpg");  //windows
            ImageIcon imageIcon = new ImageIcon(usuari.get7_foto());
            jLabel1.setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void carregaPosts(JPanel panele, ArrayList<Post> arrayList) {
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel(new GridLayout(arrayList.size(), 1, 0, 0));
        jPanel4.setBackground(new java.awt.Color(188, 204, 255));

        for (int i = 0; i < arrayList.size();) {
            Post post = arrayList.get(i);
            jPanel4.add((Component) Panellnou(new JPanel(), post));
            i = i + 1;
        };

        jScrollPane1.setViewportView(jPanel4);

    }
    public Object Panellnou(JPanel panele, Post post) {
        JPanel jPanel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("title"));
        jPanel7.setBackground(new java.awt.Color(188, 204, 255));
        
        JLabel jbl1 = new JLabel();
        BufferedImage img = null;
        try {
            //FileOutputStream fos = new FileOutputStream("images\\output.jpg");  //windows
            ImageIcon imageIcon = new ImageIcon(post.get5_foto());
            jbl1.setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        jPanel7.add((Component) jbl1);

        JPanel jPanel6 = new JPanel(new GridLayout(2, 2, 80, 15));
        jPanel6.setBackground(new java.awt.Color(188, 204, 255));

        JLabel jLabel5 = new JLabel(post.getUsuari().get2_nom());
        JLabel jLabel6 = new JLabel(post.get4_data());
        JPanel jPanel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel jLabel7 = new JLabel("<html><body style=\"margin-left:20px; \"><div style=\"width: 1;word-wrap: break-word; \">"+ post.get3_comentari() +"</div></body></html>");

        jPanel6.add(jLabel5);
        jPanel6.add(jLabel6);
        jPanel6.add(jLabel7);
        jPanel7.add(jPanel6);

        return jPanel7;
    }
    
    public Object imatge(String url) {
        //Imatge de l'habitacio
        JLabel jLabel4 = new JLabel();
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource(url))); // NOI18N       
        return jLabel4;
        
        
    }
    public Object PanellImatge(JPanel panele, Post post) {
        JPanel jPanel7 = new JPanel();
        jPanel7.setBackground(new java.awt.Color(188, 204, 255));
        jPanel7.add((Component) imatge("/mvcmysql/img/post.png"));

        JPanel jPanel6 = new JPanel(new GridLayout(2, 2, 80, 15));
        jPanel6.setBackground(new java.awt.Color(188, 204, 255));

        JLabel jLabel5 = new JLabel(post.getUsuari().get2_nom());
        JLabel jLabel6 = new JLabel(post.get4_data());
        //"<html><body style=\"\">jheejejejejjejejejejeeeeeeee con <br> varias <br>jheejejejejjejejejejeeeeeeee </body></html>"
        JLabel jLabel7 = new JLabel(post.get3_comentari());

        jPanel6.add(jLabel5);
        jPanel6.add(jLabel6);
        jPanel6.add(jLabel7);
        jPanel7.add(jPanel6);

        return jPanel7;
    }
    public Object PanellText(JPanel panele) {
        JPanel jPanel6 = new JPanel(new GridLayout(2, 2));
        jPanel6.setBackground(new java.awt.Color(188, 204, 255));
        JLabel jLabel5 = new JLabel("Francesc Fores Campos ");
        JLabel jLabel6 = new JLabel("Data 13/4/2016");
        JLabel jLabel7 = new JLabel("ejejejjeejejje :");
        /*JLabel jLabel8 = new JLabel("sestrelles");
         JLabel jLabel9 = new JLabel("Direccio :");
         JLabel jLabel10 = new JLabel("carre");
         JLabel jLabel11 = new JLabel("Valoracio :");
         JLabel jLabel12 = new JLabel("valor");
         JLabel jLabel13 = new JLabel("Ofertes :");
         JLabel jLabel14 = new JLabel("ofertes");
         JLabel jLabel15 = new JLabel("Pais :");
         JLabel jLabel16 = new JLabel("pais");*/

        jPanel6.add(jLabel5);
        jPanel6.add(jLabel6);
        jPanel6.add(jLabel7);
        /*jPanel6.add(jLabel8);
         jPanel6.add(jLabel9);
         jPanel6.add(jLabel10);
         jPanel6.add(jLabel11);
         jPanel6.add(jLabel12);
         jPanel6.add(jLabel13);
         jPanel6.add(jLabel14);
         jPanel6.add(jLabel15);
         jPanel6.add(jLabel16);*/
        return jPanel6;
    }
    public Object PanellText2(JPanel panele) {
        JPanel jPanel6 = new JPanel(new GridLayout(7, 1, 0, 0));
        jPanel6.setBackground(new java.awt.Color(188, 204, 255));

        return jPanel6;
    }
    public String data() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }
    public javax.swing.JButton passarBotoAddUser() {
        return jButton4;
    }
    public javax.swing.JButton passarBotoAddGroup() {
        return jButton5;
    }
    public void carregaGrups(ArrayList<Grup> arrayList) {


        DefaultListModel listModel = new DefaultListModel<>();

        JList countryList = new JList<>(listModel);
        for (int i = 0; i < arrayList.size();) {
            Grup grup = arrayList.get(i);
            listModel.addElement(grup);
            i = i + 1;
        };

        countryList.setCellRenderer(new GroupRenderer());
        //String selected = countryList.getSelectedValue().toString();
        jScrollPane5.setViewportView(countryList);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                addedGroup.clear();
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.print("  Selections: ");
                        }
                        System.out.print(selections[i] + "/" + selectionValues[i] + " ");
                        addedGroup.add((Grup) selectionValues[i]);
                    }

                }
            }
        };
        countryList.addListSelectionListener(listSelectionListener);
        
    }
    public void loadGroups(Usuari usuari) {
        

        DefaultListModel listModel = new DefaultListModel<>();

        JList countryList = new JList<>(listModel);
        for (int i = 0; i < usuari.getGrups().size();) {
            //Usuari usuari = usauri.getAddUser().get(i);
            listModel.addElement(usuari.getGrups().get(i));
            i = i + 1;
        };

        countryList.setCellRenderer(new GroupRenderer());
        //String selected = countryList.getSelectedValue().toString();
        jScrollPane6.setViewportView(countryList);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                addedGroup.clear();
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.print("  Selections: ");
                        }
                        System.out.print(selections[i] + "/" + selectionValues[i] + " ");
                        addedGroup.add((Grup) selectionValues[i]);
                    }

                }
            }
        };
        countryList.addListSelectionListener(listSelectionListener);
        
    }
    public Object jPanelGrups(final Grup grup, int size) {
        JPanel panel = new JPanel(new GridLayout(1, 1, 0, 0));
        //panel.setBorder(javax.swing.BorderFactory.createTitledBorder("title"));
        panel.setBackground(new java.awt.Color(188, 204, 255));

        JLabel jLabel1 = new JLabel("Nom : " + grup.get2_nom());
        JLabel jLabel2 = new JLabel("Nom : " + grup.get2_nom());
        JButton jbtn1 = new JButton("add");
        panel.add(jLabel1);
        panel.add(jLabel2);
        panel.add(jbtn1);
        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 239, 241));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mvcmysql/img/User-48.png"))); // NOI18N

        jLabel2.setText("nom");

        jLabel3.setText("edat");

        jLabel4.setText("data");

        jLabel5.setText("treball");

        jPanel5.setBackground(new java.awt.Color(236, 239, 241));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea1FocusLost(evt);
            }
        });
        jScrollPane4.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setForeground(new java.awt.Color(254, 254, 254));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mvcmysql/img/Google Images-48.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setForeground(new java.awt.Color(254, 254, 254));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mvcmysql/img/Upload to Cloud-48.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))))
        );

        jLabel6.setText("Nom");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton3.setText("Crea grup");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Agregar usuari");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Agregar grup");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Usuaris");

        jLabel9.setText("Grups");

        jLabel10.setText("Agrega Usuaris");

        jLabel11.setText("Agrega grup");

        jLabel12.setText("Perfil");

        jLabel13.setText("Introduiex un post");

        jLabel7.setText("Nom :");

        jLabel14.setText("Edat :");

        jLabel15.setText("Data :");

        jLabel16.setText("Treball :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(jLabel9)
                    .addComponent(jSeparator2))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(441, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1349, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16)))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(703, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if (f.showSaveDialog(null) == f.APPROVE_OPTION) {

            if (f.getSelectedFile().isFile()) {
                fotoPost = f.getSelectedFile().getAbsolutePath();                
            } 
        } else {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


    }//GEN-LAST:event_jButton2ActionPerformed
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {

    }
    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        Usuari usuari = HomeController.carregaUsuari();
        id = (int) usuari.get1_usuari_id();
        nomPost = jTextField1.getText().trim();

        dataPost = data();
       
    }//GEN-LAST:event_jTextField1FocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextArea1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusLost
        // TODO add your handling code here:
        comentaris = jTextArea1.getText().trim();
    }//GEN-LAST:event_jTextArea1FocusLost

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
