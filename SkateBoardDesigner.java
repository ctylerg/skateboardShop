//This is a Java GUI where a user can select different parts of a skateboard
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SkateBoardDesigner extends JFrame
{
  public static void main(String[] args)
  {
     SkateBoardDesigner sd = new SkateBoardDesigner();
  }
   private JLabel banner;                    // To display a banner
   private SkateboardPartsPanel partsPanel;  // To hold various list components
   private JPanel bannerPanel, buttonPanel;  // To hold the banner and buttons
   private JButton calcButton;               // To calculate total charges
   private JButton exitButton;               // To exit the application

   public SkateBoardDesigner()
   {
      // Display a title.
      setTitle("Skateboard Designer");

      //close on button click
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create the banner on a panel.
      bannerPanel = new JPanel();
      bannerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      banner = new JLabel("Design A Skateboard");
      banner.setFont(new Font("Rockwell", Font.BOLD, 24));
      bannerPanel.setBackground(new Color(127, 127, 209));
      bannerPanel.add(banner);

      // Create a parts panel.
      partsPanel = new SkateboardPartsPanel();

      // Build the button panel.
      buildButtonPanel();

      // Add the banner and panels to the content pane.
      add(bannerPanel, BorderLayout.NORTH);
      add(partsPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);

      // Pack and display the window.
      pack();
      setVisible(true);
   }


    //builds a panel with calculate  and exit buttons.
   private void buildButtonPanel()
   {
      // Create a button to calculate the charges.
      calcButton = new JButton("Total");
      //Hit alt T to total the charges
      calcButton.setMnemonic(KeyEvent.VK_T);
      //small hover popup
      calcButton.setToolTipText("Click here for a Total.");
      // Add an action listener to the button.
      calcButton.addActionListener(new CalcButtonListener());

      // Create a button to exit the application.
      exitButton = new JButton("Exit");
      //hit alt X to exit
      exitButton.setMnemonic(KeyEvent.VK_X);
      exitButton.setToolTipText("Click here to exit the program.");

      // Add an action listener to the button.
      exitButton.addActionListener(new ExitButtonListener());

      // Put the buttons in their own panel.
      buttonPanel = new JPanel();
      buttonPanel.add(calcButton);
      buttonPanel.add(exitButton);
   }

   private class CalcButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         double subTotal;
         double total;
         double taxes; //total $ of taxes
         double tax = 1.06; //tax
         // Get the total charges.
         subTotal = partsPanel.getDeckPrice() + partsPanel.getWheelPrice() +
                        partsPanel.getTruckPrice() + partsPanel.getMiscPrice();
         taxes = (subTotal * tax) - subTotal;
         total = (partsPanel.getDeckPrice() + partsPanel.getWheelPrice() +
                        partsPanel.getTruckPrice() + partsPanel.getMiscPrice()) * tax;
         // Display the message.
         JOptionPane.showMessageDialog(null, String.format(
                                       "Sub Total: $%,.2f\nTaxes (at 6.0%%): $%,.2f\nTotal Charges $%,.2f\n",
                                       subTotal, taxes,total));
      }
   }

   private class ExitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }


}
