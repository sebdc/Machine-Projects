package Farm;
import java.util.Random;
import java.lang.Math;

/*******************************************************************
 *     
 *  The FarmLot class refers to the farm where the Farmer would be
 *  able to plant, grow, and harvest a variety of crops. All of the
 *  crop-related actions happens in the FarmTile 2D Array attribute.
 * 
*******************************************************************/
public class FarmLot {
    
    /// Class Attributes
    private int width;
    private int length;
    private FarmTile[][] tiles;

    /*********************************************************
     
                        Constructor Methods

    *********************************************************/
    public FarmLot( int width, int length ) {
        // - declare FarmTiles[][]
        FarmTile[][] tiles = new FarmTile[length][width];   

        // - initialize FarmTiles[][]
        for( int i = 0; i < length; i++ ) {
            for( int j = 0; j < width; j++ ) {
                tiles[i][j] = new FarmTile();
            }         
        }

        // - initialize class attributes
        this.width = width;
        this.length = length;
        this.tiles = tiles;
    }

    /*********************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` Randomly generate rocks in all the FarmTiles. In FarmTile, rocks
     *   refer to the Boolean attribute "isObstructed" attribute of the tile. 
     *   If it is set to true, then it means that there is a rock obstructing
     *   the tile. 
     * 
     *      1. The rocks are generated using the Random class. 
     *      2. The percentage of rocks are determined by the parameter "rocks"
     *        - if rocks = 0, then there would be no rocks on the farm
     *        - if rocks = 1, then there would be an average of 10% rocks on the farm
     *        - if rocks = 2, then there would be an average of 20% rocks on the farm
     *        - ...
     *        - if rocks = 10, then there would be rocks on all the tiles of the farm
     *
     *    @param rock             the percentage of rocks to be generated
    */
    public void generateRocks( int rocks ) {
        Random random = new Random();
        // - outer loop iterates through the the column
        for( int curCol = 0; curCol < length; curCol++ ) {
            // - inner loop iterates through the row
            for( int curRow = 0; curRow < width; curRow++ ) {
                if( Math.abs((random.nextInt() % 10)) < rocks ) {
                    tiles[curCol][curRow].setObstructed(true);
                }
            }
        }
    }

    /**
     *   ` Check all the FarmTiles for a growing crop. If there is at least
     *   one growing Crop, then return true. Else, return false. This method
     *   is used to check for one of the losing conditions of the game, i.e.,
     *   if the Farmer has no coins AND has no growing crops.
     *
     *    @return true              there is at least one growing Crop on the farm
     *    @return false             there is no growing crop on the farm 
    */
    public Boolean isThereAGrowingCrop() {
        // - outer loop iterates through the the column
        for( int curCol = 0; curCol < length; curCol++ ) {
            // - inner loop iterates through the row
            for( int curRow = 0; curRow < width; curRow++ ) {
                if( tiles[curCol][curRow].isCropPlanted() && !tiles[curCol][curRow].getCrop().isWithered() ) {
                    return true;
                }
            }
        }
        return false;
    }

    /*********************************************************
     
                        Getters and Setters

    *********************************************************/
    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public FarmTile getTile( int indexOne, int indexTwo ) {
        return tiles[indexOne][indexTwo];
    } 
}