package Tool;
import Farm.*;
import Farmer.*;

/*******************************************************************
 *     
 *  The Fertilizer class is used to fertilize the crop planted on 
 *  the FarmTile. Using the Fertilizer costs coins and yields exp
 *  to the Farmer. 
 * 
*******************************************************************/

public class Fertilizer {
    
    /// Class Attributes
    private double exp;
    private int cost;
    private Farmer farmer;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Fertilizer( Farmer farmer ) {
        this.exp = 4.0;
        this.cost = 10;
        this.farmer = farmer;
    }

    public Fertilizer(double exp, int cost, Farmer farmer ) {
        this.exp = exp;
        this.cost = cost;
        this.farmer = farmer;
    }

    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` Use the Fertilizer to fertilize a FarmTile. The tile becomes fertilized,
     *   increasing the Crop's "fertilizeCount" attribute at the end of the day.
     *   Using the Fertilizer costs 'coins' and yields 'exp' to the Farmer.
     * 
     *    1. This method is used in the FarmerTools method useFertilizer()
     * 
     *    2. The Fertlizer can only be used if : 
     *      a. the Farmer has enough 'coins' to fertilize the Crop
     *      b. the FarmTile is occupied by a growing Crop ; isCropPlanted == true
     *      c. the FarmTile is not yet fertilized ; isFertilized == false
     * 
     *    3. Updates the FarmTile Boolean attribute "isFertilized" to 'true'
     * 
     *    4. Using a FarmerTool yields exp ; adds 'exp' to Farmer
     * 
     *    5. Using a FarmerTool costs coins ; substract 'coins' from Farmer
     *
     *   @param tile             the designated FarmTile
     */
    public void fertilizeTile( FarmTile tile ) {     
        if( tile.isCropPlanted() && !tile.isFertilized() && farmer.getCoins() >= cost ) {
            tile.setFertilized(true);
            farmer.setCoins( farmer.getCoins() - cost );
            farmer.setExp( farmer.getExp() + exp );
        }
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public int getCost() {
        return cost;
    }
}