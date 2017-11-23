package feps;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExemploCardLayout implements ItemListener {
    JPanel cartoes; //é neste panel que utilizaremos CardLayout
    final static String BUTTONPANEL = "Cartao com JButtons";
    final static String TEXTPANEL = "Cartao com JTextField";
    final static String CHECKPANEL = "Cartao com JCheckBox";
   
    public void adicionarComponentesAoPanel(Container pane) {
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL, CHECKPANEL };
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
       
        //Cria os "cartoes".
        // 1. botões
        JPanel cartaoComBotoes = new JPanel();
        cartaoComBotoes.add(new JButton("Botao n#1"));
        cartaoComBotoes.add(new JButton("Botao n#2"));
        cartaoComBotoes.add(new JButton("Botao n#3"));
        // 2. texto
        JPanel cartaoComTexto = new JPanel();
        cartaoComTexto.add(new JTextField("Campo de texto de exemplo", 20));


        // 3. checkbox
        JCheckBox chinButton;
        JCheckBox glassesButton;
        JCheckBox hairButton;
        JCheckBox teethButton;
        chinButton = new JCheckBox("Chin");
        chinButton.setMnemonic(KeyEvent.VK_C);
        chinButton.setSelected(false);

        glassesButton = new JCheckBox("Glasses");
        glassesButton.setMnemonic(KeyEvent.VK_G);
        glassesButton.setSelected(false);

        hairButton = new JCheckBox("Hair");
        hairButton.setMnemonic(KeyEvent.VK_H);
        hairButton.setSelected(false);

        teethButton = new JCheckBox("Teeth");
        teethButton.setMnemonic(KeyEvent.VK_T);
        teethButton.setSelected(true);
       
        JPanel cartaoComCheckbox = new JPanel();
        cartaoComCheckbox.add(chinButton);
        cartaoComCheckbox.add(glassesButton);
        cartaoComCheckbox.add(hairButton);
        cartaoComCheckbox.add(teethButton);
       
        //Cpanel que conterá os cartoes
        cartoes = new JPanel(new CardLayout());
        cartoes.add(cartaoComBotoes, BUTTONPANEL);
        cartoes.add(cartaoComTexto, TEXTPANEL);
        cartoes.add(cartaoComCheckbox, CHECKPANEL);
       
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        // note que os cartões estão utilizando BORDERLAYOUT       
        pane.add(cartoes, BorderLayout.CENTER);
       
    }
   
    // trocar entre os cartões
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cartoes.getLayout());
        cl.show(cartoes, (String)evt.getItem());
    }
   
     /**
     * Criate GUI e mostra
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Exemplo com gerenciador CardLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        ExemploCardLayout demo = new ExemploCardLayout();
        demo.adicionarComponentesAoPanel(frame.getContentPane());
       
        //mostra o frame
        frame.pack();
        frame.setVisible(true);
    }
   
    public static void main(String[] args) {     
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
