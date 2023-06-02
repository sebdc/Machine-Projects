package Time;
import Farm.*;

/*******************************************************************
 *     
 *  The Time class refers to the current in-game day. At the moment,
 *  it is exclusively made for its method nextDay(), which allows the
 *  Farmer to advance to the next day and trigger updates to all the
 *  FarmTiles and Crops located inside the FarmLot.
 * 
*******************************************************************/
public class Time {
    
    /// Class Attributes
    private int day;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Time( int day ) {
        this.day = day;
    }
 
    public Time() {
        this.day = 0;
    }

    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` Advances to the next day and trigger updates to all the FarmTiles and Crops 
     *   inside the FarmLot. The updates depends on the status of the tiles and the crops : 
     * 
     *    1. If the tile is occupied by a growing crop and if the tile is watered 
     *    and/or fertilized, then increase the Crop's "waterCount" and "fertilizeCount" 
     *    attribute by one (1). Additionally, the Crop's "harvestTime" is decreased
     *    by one. 
     * 
     *    2. Additionally, the Crop's "harvestTime" is decreased by one (1). If the
     *    Crop's new "harvestTime" is equal to zero (0), then the Crop matures and
     *    becomes a harvestable crop (isHarvestable = true). If the Crop's new 
     *    "harvestTime" is less than zero, then the Crop dies and becomes a withered 
     *    crop (isWithered = true).
     * 
     *    3. Regardless of the tile's status, the water and fertilizer status of all
     *    the tiles are removed (isWatered = false, isFertilized = false). 
     * 
     *    4. Increase the day count by one (1).
     *
     *    @param farm             added as a parameter to check all the FarmTiles
     *    @param day              the current in-game day
    */
    public void nextDay( FarmLot farm ) {
        // - initialize loop counters
        int maxCol = farm.getLength();
        int maxRow = farm.getWidth();

        // - declare temporary FarmTile and Crop variables
        FarmTile tile;
        Crop crop;

        // - declare Boolean variables
        Boolean isTileWatered, isTileFertilized;
        Boolean isWaterNeedsMet, isFertilizerNeedsMet;
        Boolean isHarvestTimeMet, isCropWithered;

        /// loop through all the FarmTile in FarmLot
        for( int curCol = 0; curCol < maxCol; curCol++ ) {
            for( int curRow = 0; curRow < maxRow; curRow++ ) {
                // - initialize tile and crop
                tile = farm.getTile(curCol,curRow);
                crop = tile.getCrop();

                if( tile.isCropPlanted() ) {
                    // - initialize Boolean variables for the water and fertilize condition
                    isTileWatered = tile.isWatered();
                    isTileFertilized = tile.isFertilized();

                    // - update Crop's water and/or fertilize count if the FarmTile is watered and/or fertilized
                    if( isTileWatered ) 
                        crop.setWaterCount( crop.getWaterCount() + 1 );
                    if( isTileFertilized ) 
                        crop.setFertilizeCount( crop.getFertilizeCount() + 1 );

                    // - update Crop's remaining 'harvestTime'
                    crop.setHarvestTime( crop.getHarvestTime() - 1 );

                    // - initialize Boolean variables for the harvest crop condition
                    isWaterNeedsMet = crop.getWaterCount() >= crop.getWaterNeeds();
                    isFertilizerNeedsMet = crop.getFertilizeCount() >= crop.getFertilizeNeeds();
                    isHarvestTimeMet = crop.getHarvestTime() == 0;
                    isCropWithered = crop.getHarvestTime() < 0;

                    // - check if Crop is harvestable 
                    if( isHarvestTimeMet && isWaterNeedsMet && isFertilizerNeedsMet && !isCropWithered ) 
                        crop.setHarvestable( true );

                    // - check if Crop is withered
                    else if( isCropWithered ) {
                        crop.setHarvestable(false);    
                        crop.setWithered(true);
                    }
                }
                // - reset the Tile boolean 'isWatered' and 'isFertilized' back to false
                tile.setWatered(false);
                tile.setFertilized(false);
            }
        }
        day = day + 1;
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}