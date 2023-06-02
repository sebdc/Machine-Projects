package Farm;
import java.util.Random;

/*******************************************************************
 *     
 *  The Crop class refers to the variety of plants the Farmer can 
 *  plant on the FarmTile. Each Crop has different attributes 
 *  regarding its characteristics.
 * 
*******************************************************************/

public class Crop {

    /// Crop Trait Attributes
    private String name;
    private String type;
    private boolean isWithered;
    private boolean isHarvestable;

    /// Crop Growth Attributes
    private int harvestTime;
    private int waterCount;
    private int waterNeeds;
    private int waterBonusLimit;
    private int fertilizeCount;
    private int fertilizeNeeds;
    private int fertilizeBonusLimit;

    /// Harvest Crop Attributes
    private int minProduce;
    private int maxProduce;
    private int seedCost;
    private int sellPrice;
    private double exp;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Crop() { 
        this.name = " "; 
        this.type = " "; 
    }

    public Crop( String name, String type, int harvestTime, int waterNeeds, int waterBonusLimit, 
                 int fertilizeNeeds, int fertilizeBonusLimit, int minProduce, int maxProduce, 
                 int seedCost, int sellPrice, double exp ) {
        // - initialize crop trait attributes
        this.name = name;
        this.type = type;
        isWithered = false;
        isHarvestable = false;

        // - initialize crop growth attributes
        this.harvestTime = harvestTime;
        waterCount = 0;
        this.waterNeeds = waterNeeds;
        this.waterBonusLimit = waterBonusLimit;
        fertilizeCount = 0;
        this.fertilizeNeeds = fertilizeNeeds;
        this.fertilizeBonusLimit = fertilizeBonusLimit;

        // - initialize harvest crop attributes
        this.minProduce = minProduce;
        this.maxProduce = maxProduce;
        this.seedCost = seedCost;
        this.sellPrice = sellPrice;
        this.exp = exp;
    }

    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/  
    /**
     *   ` Randomly generate the number of proudce based on the Crop's
     *   "maxProduce" and "minProduce" attribute. This method is used
     *   in the Farmer method harvestCrop().
     *
     *    @param tile               the designated FarmTile
     *    @return produce           the amount of crops produced
    */
    public int getRandProduce() {
        Random random = new Random();
        int produce = random.nextInt(maxProduce + 1 - minProduce) + minProduce;
        return produce;
    }

    /*|*******************************************************
     
               Getters and Setters : Crop Attributes

    *********************************************************/
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isWithered() {
        return isWithered;
    }

    public void setWithered( boolean isWithered ) {
        this.isWithered = isWithered;
    }

    public boolean isHarvestable() {
        return isHarvestable;
    }

    public void setHarvestable( boolean isHarvestable ) {
        this.isHarvestable = isHarvestable;
    }

    /*|*******************************************************
     
            Getters and Setters : Crop Growth Attributes

    *********************************************************/
    public int getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime( int harvestTime ) {
        this.harvestTime = harvestTime;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public void setWaterCount( int waterCount ) {
        this.waterCount = waterCount;
    }
    
    public int getWaterNeeds() {
        return waterNeeds;
    }

    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    public int getFertilizeCount() {
        return fertilizeCount;
    }

    public void setFertilizeCount( int fertilizeCount ) {
        this.fertilizeCount = fertilizeCount;
    }

    public int getFertilizeNeeds() {
        return fertilizeNeeds;
    }

    public int getFertilizeBonusLimit() {
        return fertilizeBonusLimit;
    }

    /*|*******************************************************
     
                Getters : Harvest Crop Attributes

    *********************************************************/
    public int getMinProduce() {
        return minProduce;
    }

    public int getMaxProduce() {
        return maxProduce;
    }

    public int getSeedCost() {
        return seedCost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public double getExp() {
        return exp;
    }
}