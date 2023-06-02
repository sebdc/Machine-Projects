package Farmer;
import Farm.*;

/*******************************************************************
 *     
 *  The Farmer class refers to the game character of the user. The
 *  Farmer can interact with the FarmLot through the use of the 
 *  different FarmerTools. Additionally, the Farmer has the coins
 *  and exp attributes. These attributes increase and/or decrease 
 *  through different Farmer actions.
 * 
*******************************************************************/
public class Farmer {
    
    /// Class Attributes
    private String name;
    private int level;
    private double exp;
    private int coins;
    private FarmerType type;
    private FarmerTools tool;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Farmer( String name ) {
        this.name = name;
        this.level = 0;
        this.exp = 0;
        this.coins = 100;
        this.type = new FarmerType( "Farmer", 0, 0, 0, 0, 0, 0 );
    }

    public Farmer( String name, int level, int coins ) {
        this.name = name;
        this.level = level;
        this.exp = level * 100;
        if( coins < 100 ) 
            coins = 100;
        this.coins = coins;
        this.type = new FarmerType( "Farmer", 0, 0, 0, 0, 0, 0 );
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
        tool.usePlow( tile );
    }

    /**
     *   ` Use the WateringCan to water a FarmTile. The tile becomes watered, 
     *   increasing the Crop's "waterCount" attribute at the end of the day.
     *   Using the WateringCan costs "coins" and yields "exp" to the Farmer.
     *
     *    @param tile             the designated FarmTile
     */
    public void useWateringCan( FarmTile tile ){
        tool.useWateringCan( tile );
    }
    
    /**
     *   ` Use the Fertilzer to fertilize a Crop. The Crop becomes fertilized,
     *   increasing the Crop's "fertilizeCount" attribute at the end of the day.
     *   Using the Fertilizer costs "coins" and yields "exp" to the Farmer.
     *
     *   @param tile             the designated FarmTile
     */
    public void useFertilizer( FarmTile tile ){
        tool.useFertilizer( tile );
    }

    /**
     *   ` Use the Shovel to remove a Crop from a FarmTile. The tile reverts to 
     *   being unplowed. Additionally, the water and fertilizer status of the tile 
     *   is removed. Using the Shovel costs "coins" and yields "exp" to the Farmer.
     *
     *   @param tile             the designated FarmTile
     */
    public void useShovel( FarmTile tile ) {
        tool.useShovel( tile );
    }

    /**
     *   ` Use the Plow to plow a FarmTile. The tile becomes a plowed tile, 
     *   allowing the Farmer to plant a Crop on the tile. Using the Plow costs
     *   "coins" and yields "exp" to the Farmer. 
     *
     *   @param tile             the designated FarmTile
     */
    public void usePickaxe( FarmTile tile ) {
        tool.usePickaxe( tile );
    }

    /**
     *   ` Plants a crop on a designated FarmTile. The tile becomes occupied with
     *   a growing crop, allowing the Farmer to water and fertilize the Crop. Planting
     *   a crop costs "coins". Different Crop 'types' have different planting conditions
     *   that needs to be met.
     * 
     *    1. updates the Crop object in FarmTile
     * 
     *    2. updates the FarmTile Boolean attribute "isCropPlanted" to 'true'
     * 
     *    3. Crops can only be planted if they satisfy the following conditions :
     *      - the Farmer has enough "coins" to afford the seeds
     *      - Crop "type" attribute 'Root Crop' and 'Flower' can be planted on any plowed tiles
     *      - Crop "type" attribute 'Fruit Tree' can only be planted on plowed tiles surrounded by free tiles
     * 
     *    4. planting a crop costs coins ; substract "coins" from Farmer
     *    
     *   @param farm             the FarmLot containing the FarmTile
     *   @param crop             a Crop object from Register
     *   @param row              used to find the designated tile
     *   @param col              used to find the designated tile
     */
    public void plantCrop( FarmLot farm, Crop crop, int row, int col ) {
        // - initialize a new FarmTile variable and copy the designated tile
        FarmTile tile = farm.getTile( col, row );

        // - initialize Boolean variables for the plant crop condition
        Boolean isTilePlantable = !tile.isObstructed() && !tile.isCropPlanted() && tile.isPlowed();
        Boolean canFarmerAfford = coins >= crop.getSeedCost();
        Boolean isFruitTree = crop.getType().equals("Fruit Tree");

        // - if FarmTile and Farmer satisfies the condition AND if the Crop IS NOT a "Fruit Tree"
        if( isTilePlantable && canFarmerAfford && !isFruitTree ) {
                coins = coins - crop.getSeedCost();
                tile.setCrop( crop );
                tile.setCropPlanted(true);
        }
        // - if FarmTile and Farmer satisfies the condition AND if the Crop IS a "Fruit Tree"
        else if( isTilePlantable && canFarmerAfford && isFruitTree ) {

            // - initialize a variable that counts the number of occupied tile
            int occupiedTile = 0;

            // - count the number of occupied tiles around the designated tile
            for( int curRow = row-1; curRow <= row+1; curRow++ ) {
                for( int curCol = col-1; curCol <= col+1; curCol++ ) {
                    // - check if the index is not out of bounds and if the tile is occupied
                    if( ((curRow >= 0) && (curRow < farm.getWidth())) && ((curCol >= 0) && (curCol < farm.getLength())) && 
                        (farm.getTile(curCol,curRow).isCropPlanted() || farm.getTile(curCol,curRow).isObstructed()) ) {
                        occupiedTile = occupiedTile + 1;
                    }
                }
            }

            // - if the given FarmLot area satisfies the plant "Fruit Tree" condition
            if( occupiedTile == 0 ) {
                coins = coins - crop.getSeedCost();
                tile.setCrop(crop);
                tile.setCropPlanted(true);
            }
        }
    }

    /**
     *   ` Harvests a matured Crop from a FarmTile and return the number of produce.
     *   In doing so, the Crop gets removed and the tile reverts to its unplowed state.
     *   Additionally, harvesting a crop yields "exp" to the Farmer. It is intended to
     *   be used along with sellCrop().
     *
     *   @param tile             the designated FarmTile
     *   @return produce         returns the number of produces harvested
     *   @return -1              crop is not harvestable
     */
    public int harvestCrop( FarmTile tile ) {
        // - check if the Crop on FarmTile satisfies the harvest condition
        if( tile.isCropPlanted() && tile.getCrop().isHarvestable() ) {
            int produce = tile.getCrop().getRandProduce();         
            exp = exp + tile.getCrop().getExp();                      
            tile.removeCrop();                                     
            tile.setCropPlanted( false );
            tile.setPlowed(false);                         
            return produce;                                         
        }
        else 
            return -1;
    }

    /**
     *   ` Sells the Crop's produce from harvestCrops() for a certain amount of Farmer
     *   "coins." The amount of "coins" received is calculated according to the amount of
     *   produce, the Crop's base selling price, the Crop's water and fertilizer bonuses,
     *   and the Farmer's "FarmerType".
     *
     *   @param crop                         the Crop object being sold, get from Store
     *   @return finalHarvestPrice           the coins gained from selling the crops
     */
    public int sellCrop( Crop crop, int produce ) {
        // - initialize variables
        int harvestTotal = produce * (crop.getSellPrice() + type.getBonusEarningPerProduce());
        int waterCount = crop.getWaterCount();
        int fertilizeCount = crop.getFertilizeCount();

        // - check if FarmerType bonuses applies
        if( waterCount > crop.getWaterBonusLimit() + type.getWaterBonusInc() )
            waterCount = crop.getWaterBonusLimit() + type.getWaterBonusInc();
        if( fertilizeCount > crop.getFertilizeBonusLimit() + type.getFertilizerBonusInc() )
            fertilizeCount = crop.getFertilizeBonusLimit() + type.getFertilizerBonusInc();

        // - calculations for the final harvest price
        double waterBonus = harvestTotal * 0.2 * (waterCount - 1);
        double fertilizeBonus = harvestTotal * 0.5 * fertilizeCount;
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizeBonus;

        // - check if "Flower" bonuses applies
        if( crop.getType().equalsIgnoreCase("Flower") )
            finalHarvestPrice = finalHarvestPrice * 1.1;

        coins = coins + (int)finalHarvestPrice;
        return (int)finalHarvestPrice;
    }

    /**
     *   ` Updates the Farmer "level" based on the Farmer "exp"
     *    1. the following formula is used to compute for the Farmer "level"
     *      - level = exp / 100
     */
    public void updateLevel() {
        level = (int)(exp/100.0);
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel( int level ) {
        this.level = level;
    }

    public double getExp() {
        return exp;
    }

    public void setExp( double exp ) {
        this.exp = exp;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins( int coins ) {
        this.coins = coins;
    }

    public FarmerType getType() {
        return this.type;
    }

    public void setType( FarmerType type ) {
        this.type = type;
    }

    public FarmerTools getFarmerTools() {
        return tool;
    }

    public void setFarmerTools( FarmerTools tool ) {
        this.tool = tool;
    }
}
