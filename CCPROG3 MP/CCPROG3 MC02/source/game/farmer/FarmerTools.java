package source.game.farmer;
import source.game.farm.*;
import source.game.tool.*;

/*******************************************************************
 *     
 *  The Farmer Tools serves as a tool inventory for the Farmer. It
 *  has all the in-game tools, ready to be used by the Farmer.
 * 
*******************************************************************/
public class FarmerTools {

    /// Class Attributes
    private Plow plow;
    private Fertilizer fertilizer;
    private WateringCan wateringCan;
    private Shovel shovel;
    private Pickaxe pickaxe;
    private int cropIndex;
    
    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public FarmerTools( Farmer farmer ) {
        this.plow = new Plow( farmer );
        this.fertilizer = new Fertilizer( farmer );
        this.wateringCan = new WateringCan( farmer );
        this.shovel = new Shovel( farmer );
        this.pickaxe = new Pickaxe( farmer );
        this.cropIndex = 0;
    }

    /*|*******************************************************
     
                         Behaviour Methods

    *********************************************************/
    /**
     *   ` Use the Plow to plow a FarmTile. The tile becomes a plowed tile, 
     *   allowing the Farmer to plant a Crop on the tile. Using the Plow costs
     *   "coins" and yields "exp" to the Farmer. 
     *
     *    @param tile             the designated FarmTile
    */
    public void usePlow( FarmTile tile ){
        plow.plowTile( tile );
    }

    /**
     *   ` Use the WateringCan to water a FarmTile. The tile becomes watered, 
     *   increasing the Crop's "waterCount" attribute at the end of the day.
     *   Using the WateringCan costs "coins" and yields "exp" to the Farmer.
     *
     *    @param tile             the designated FarmTile
     */
    public void useWateringCan( FarmTile tile ) {
        wateringCan.waterTile( tile );
    }

    /**
     *   ` Use the Fertilzer to fertilize a Crop. The Crop becomes fertilized,
     *   increasing the Crop's "fertlizeCount" attribute at the end of the day.
     *   Using the Fertilizer costs "coins" and yields "exp" to the Farmer.
     *
     *   @param tile             the designated FarmTile
     */
    public void useFertilizer( FarmTile tile ) {
        fertilizer.fertilizeTile( tile );
    }

    /**
     *   ` Use the Shovel to remove a Crop from a FarmTile. The tile reverts to 
     *   being unplowed. Additionally, the water and fertilizer status of the tile 
     *   is removed. Using the Shovel costs "coins" and yields "exp" to the Farmer.
     *
     *   @param tile             the designated FarmTile
     */
    public void useShovel( FarmTile tile ) {
        shovel.removeCrop( tile );
    }

    /**
     *   ` Use the Plow to plow a FarmTile. The tile becomes a plowed tile, 
     *   allowing the Farmer to plant a Crop on the tile. Using the Plow costs
     *   "coins" and yields "exp" to the Farmer. 
     *
     *   @param tile             the designated FarmTile
     */
    public void usePickaxe( FarmTile tile ){
        pickaxe.mineRock( tile );
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public Plow getPlow() {
        return plow; 
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public WateringCan getWateringCan() {
        return wateringCan;
    }

    public Shovel getShovel() {
        return shovel;
    }

    public Pickaxe getPickaxe() {
        return pickaxe;
    }

    public int getCropIndex() {
        return cropIndex;
    }

    public void setCropIndex( int cropIndex ) {
        this.cropIndex = cropIndex;
    }
}