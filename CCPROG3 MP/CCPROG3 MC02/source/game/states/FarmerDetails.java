package source.game.states;
import source.game.farmer.*; 
import source.framework.resources.*;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

// REFERENCES
// https://www.fontspace.com/search?q=video%20game - FONTS
// https://www.youtube.com/watch?v=43duJsYmhxQ - Customize FONTS
// https://www.canva.com/design/DAFUEBcOq-A/6mAF6YglOiQjMdUA-wrxdA/view?utm_content=DAFUEBcOq-A&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton - ICONS USED
// https://teaching.csse.uwa.edu.au/units/CITS1001/colorinfo.html - Colors

public class FarmerDetails extends JPanel {
    
    /// Game Attributes
    private Farmer farmer;

    /// Farmer Details
    private JLabel farmerName;
    private JLabel farmerExp;
    private JLabel farmerLevel;
    private JLabel farmerCoins;
    private JPanel upperBorder;
    private JPanel lowerBorder;
    private JPanel rightBorder;
    private JPanel leftBorder;

    // DESIGNS
    private Font customFont;
    private JLabel farmerIcon;
    private JLabel levelIcon;
    private JLabel coinsIcon;

    public FarmerDetails( Farmer farmer ) {
        setLayout(null);
        setBackground( Color.BLACK ); //new Color(51,51,51)
        setBounds( 900, 0, 380, 360 );

        this.farmer = farmer;
        farmerName = new JLabel();
        farmerLevel = new JLabel();
        farmerExp = new JLabel();
        farmerCoins = new JLabel();
        farmerIcon = new JLabel();
        levelIcon = new JLabel();
        coinsIcon = new JLabel();
        upperBorder = new JPanel();
        lowerBorder = new JPanel();
        rightBorder = new JPanel();
        leftBorder = new JPanel();

        initFarmerDetails();
        updateFarmerDetails();
    }

    /*|*******************************************************
     
                        Behavior Methods

    *********************************************************/
    public void updateFarmerDetails() {
            
        farmerName.setText("Farmer "+farmer.getName());
        farmerName.setFont(customFont);
        farmerName.setBounds(130,115,200,20);       
        farmerName.setForeground(new Color(255,102,0));
       
        farmerLevel.setText("Level "+ farmer.getLevel());//+ " ( "+farmer.getExp()+" exp )"
        farmerLevel.setFont(customFont);
        farmerLevel.setBounds(153,175,200,20);
        farmerLevel.setForeground(new Color(255,102,0));

        farmerExp.setText("( "+farmer.getExp()+"exp )");
        farmerExp.setFont(customFont);
        farmerExp.setBounds(135,195,200,20);
        farmerExp.setForeground(new Color(204,0,0));

        farmerCoins.setText(farmer.getCoins()+" coins");  
        farmerCoins.setFont(customFont);
        farmerCoins.setBounds(130, 255, 200, 20);   
        farmerCoins.setForeground(new Color(255,102,0));   
    }
    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/
    public void initFarmerDetails(){
        upperBorder.setBackground(new Color (255,255,153)); // Light Yellow
        upperBorder.setBounds(0,0,500,10);
        leftBorder.setBackground(new Color (255,255,153));  // Light Yellow
        leftBorder.setBounds(0,0,5,500);
        rightBorder.setBounds(375,0,5,500);
        rightBorder.setBackground(new Color (255,255,153));
        lowerBorder.setBounds(0,355,500,5);
        lowerBorder.setBackground(new Color (255,255,153));

        farmerIcon.setIcon( Resources.FARM.get(1) );
        farmerIcon.setBounds(13,85,200,105);
        levelIcon.setIcon( Resources.FARM.get(2) );
        levelIcon.setBounds(13,150,200,105);
        coinsIcon.setIcon( Resources.FARM.get(3) );
        coinsIcon.setBounds(20,215,200,105);

        customFont = Resources.FONT.get(0);

        super.add(farmerIcon);
        super.add(farmerName);
        super.add(levelIcon);
        super.add(farmerLevel);
        super.add(farmerExp);
        super.add(farmerCoins);
        super.add(coinsIcon);
        super.add(upperBorder);
        super.add(leftBorder);
        super.add(lowerBorder);
        super.add(rightBorder);
        super.validate();
    }
}