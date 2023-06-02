package source.game.tool;
import source.game.farm.*;
import source.game.farmer.*;

/*******************************************************************
 *     
 *  The Shovel class is used to remove a Crop, whether withered or
 *  not, from a FarmTile. Using the Shovel costs coins regardless 
 *  of the Crop condition and yields exp to the user if the Crop is 
 *  withered crop. 
 * 
*******************************************************************/
public class Shovel {
    
    /// Class Attributes
    private double exp;
    private int cost;
    private Farmer farmer;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Shovel( Farmer farmer ) {
        this.exp = 2.0;
        this.cost = 7;
        this.farmer = farmer;
    }

    public Shovel( double exp, int cost ) {
        this.exp = exp;
        this.cost = cost;
    }

    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` Use the Shovel to remove a crop from a FarmTile. The tile reverts to 
     *   being unplowed. Additionally, the water and fertilizer status of the tile 
     *   is removed. Using the Shovel costs 'coins' and yields 'exp' to the Farmer.
     * 
     *    1. This method is used in the FarmerTools method useShovel()
     * 
     *    2. The Shovel can only be used if :
     *      a. the Farmer has enough 'coins' to remove the crop
     *      b. the FarmTile is occupied by a growing crop ; isCropPlanted == true
     * 
     *    3. Removes the FarmTile "Crop" attribute
     * 
     *    4. Updates the following FarmTile Boolean attributes to 'false' :
     *      - "isCropPlanted", "isPlowed", "isFertilized", "isWatered"
     * 
     *    5. Using a FarmerTool yields exp ; adds 'exp' to Farmer
     * 
     *    6. Using a FarmerTool costs coins ; substract 'coins' from Farmer
     *
     *   @param tile             the designated FarmTile
     */
    public void removeCrop( FarmTile tile ) {     
        if( tile.isCropPlanted() && farmer.getCoins() >= cost ) {
            // - yields the Farmer exp if removing withered crops
            if( tile.getCrop().isWithered() )         
                farmer.setExp( farmer.getExp() + exp );
            farmer.setCoins( farmer.getCoins() - cost );

            // - update the FarmerTile attributes
            tile.removeCrop();              
            tile.setCropPlanted(false);
            tile.setPlowed(false);
            tile.setFertilized(false);
            tile.setWatered(false);
        }
   }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public int getCost() {
        return this.cost;
    }
}
