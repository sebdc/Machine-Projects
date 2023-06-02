package source.game.tool;
import source.game.farm.*;
import source.game.farmer.*;

/*******************************************************************
 *     
 *  The Plow class is used to plow a free FarmTile. Using the plow 
 *  can cost coins (but it's currently set to 0) and yields exp to
 *  the Farmer
 * 
*******************************************************************/
public class Plow {
    
    /// Class Attributes
    private double exp;
    private int cost;
    private Farmer farmer;
    
    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Plow( Farmer farmer ) {
        this.exp = 0.5;
        this.cost = 0;
        this.farmer = farmer;
    }

    public Plow( double exp, int cost, Farmer farmer ) {
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
     *   "coins" and yields "exp" to the Farmer. 
     *  
     *    1. This method is used in the FarmerTools method usePlow()
     * 
     *    2. The Plow can only be used if:
     *      a. the Farmer has enough "coins" to plow the FarmTile
     *      b. the FarmTile is not obstructed by a rock ; isObstructed == false
     *      c. the FarmTile is not yet plowed ; isPlowed == false
     * 
     *    3. Updates the FarmTile Boolean attribute "isPlowed" to 'true'
     * 
     *    4. Using a FarmerTool yields exp ; adds "exp" to Farmer
     * 
     *    5. Using a FarmerTool costs coins ; substract "coins" from Farmer
     * 
     *    @param tile             the designated FarmTile
    */
    public void plowTile( FarmTile tile) {
        if( !tile.isPlowed() && !tile.isObstructed() && farmer.getCoins() >= cost ) {
            tile.setPlowed(true);
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