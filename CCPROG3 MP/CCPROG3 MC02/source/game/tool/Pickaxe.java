package source.game.tool;
import source.game.farm.*;
import source.game.farmer.*;

/*******************************************************************
 *     
 *  The Pickaxe class is used to remove rocks obstructing a FarmTile.
 *  Using the Pickaxe costs coins and yields exp to the Farmer. 
 * 
*******************************************************************/

public class Pickaxe {
    
    /// Class Attributes
    private double exp;
    private int cost;
    private Farmer farmer;
    
    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Pickaxe( Farmer farmer ) {
        this.exp = 15.0;
        this.cost = 50;
        this.farmer = farmer;
    }

    public Pickaxe( double exp, int cost, Farmer farmer ) {
        this.exp = exp;
        this.cost = cost;
        this.farmer = farmer;
    }
    
    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` Use the Plow to plow a FarmTile. The tile becomes a plowed tile, 
     *   allowing the Farmer to plant a Crop on the tile. Using the Plow costs
     *   'coins' and yields 'exp' to the Farmer. 
     *    
     *    1. This method is used in the FarmerTools method usePickaxe()
     *  
     *    2. The Pickaxe can only be used if :
     *      a. the Farmer has enough 'coins' to mine the rock
     *      b. the FarmTile is obstructed by a rock ; isObstructed == true
     * 
     *    3. Updates the FarmTile Boolean attribute "isObstructed" to 'false'
     * 
     *    4. Using a FarmerTool yields exp ; adds 'exp' to Farmer
     * 
     *    5. Using a FarmerTool costs coins ; substract 'coins' from Farmer
     *
     *   @param tile             the designated FarmTile
     */
    public void mineRock( FarmTile tile ) {
        if( tile.isObstructed() && farmer.getCoins() >= cost )  {
            tile.setObstructed(false);
            farmer.setCoins( farmer.getCoins() - cost );
            farmer.setExp( farmer.getExp() + exp );
        }
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public int getCost() {
        return this.cost;
    }
}