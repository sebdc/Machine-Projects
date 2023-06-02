package source.game.tool;
import source.game.farm.*;
import source.game.farmer.*;

/*******************************************************************
 *     
 *  The WateringCan class is used to water a Crop. Using the 
 *  WateringCan costs coins and yields exp to the Farmer.
 * 
*******************************************************************/
public class WateringCan {

    /// Class Attributes
    private double exp;
    private int cost;
    private Farmer farmer;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public WateringCan( Farmer farmer ) {
        this.exp = 0.5;
        this.cost = 0;
        this.farmer = farmer;
    }

    public WateringCan( double exp, int cost ) {
        this.exp = exp;
        this.cost = cost;
    }

    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` Use the WateringCan to water a FarmTile. The tile becomes watered, 
     *   increasing the Crop's "waterCount" attribute at the end of the day.
     *   Using the WateringCan costs 'coins' and yields 'exp' to the Farmer.
     * 
     *    1. This method is used in the FarmerTools method useWateringCan()
     * 
     *    2. The WateringCan can only be used if: 
     *      a. the farmer has enough 'coins' to water the tile
     *      b. the FarmTile is occupied by a growing crop ; isCropPlanted == true
     *      c. the Crop is not withered ; isWithered == false
     *      d. the FarmTile is not yet watered ; isWatered == false
     * 
     *    3. Updates the FarmTile Boolean attribute "isWatered" to 'true'
     * 
     *    4. Using a FarmerTool yields exp ; adds 'exp' to Farmer
     * 
     *    5. Using a FarmerTool costs coins ; substract 'coins' from Farmer
     *
     *    @param tile             the designated FarmTile
     */
    public void waterTile( FarmTile tile ) {     
        if( !tile.isWatered() && tile.isCropPlanted() && !tile.getCrop().isWithered() && farmer.getCoins() >= cost ) {
            tile.setWatered(true);
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