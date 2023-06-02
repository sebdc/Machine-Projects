package source.game.farm;

/*******************************************************************
 *     
 *  The FarmTile class refers to a single planting plot or tile on 
 *  a farm. It can either be obstructed by a rock, be plowed, be
 *  watered, be fertilized, or have a single crop planted on it.
 * 
*******************************************************************/

public class FarmTile {

    /// Class Attributes
    private boolean isPlowed;
    private boolean isCropPlanted;
    private boolean isWatered;
    private boolean isFertilized;
    private boolean isObstructed;
    private Crop crop;

    /*********************************************************
     
                        Constructor Methods

    *********************************************************/
    public FarmTile() {           
        this.isObstructed = false;      
        this.isCropPlanted = false;
        this.isFertilized = false;
        this.isPlowed = false;
        this.isWatered = false;
        crop = new Crop();
    }

    /*********************************************************
     
                        Getters and Setters

    *********************************************************/
    public boolean isPlowed() {
        return isPlowed;
    }
    
    public void setPlowed( boolean isPlowed ) {
        this.isPlowed = isPlowed;
    }

    public boolean isCropPlanted() {
        return isCropPlanted;
    }

    public void setCropPlanted( boolean isCropPlanted ) {
        this.isCropPlanted = isCropPlanted;
    }

    public boolean isWatered() {
        return isWatered;
    }

     public void setWatered( boolean isWatered ) {
        this.isWatered = isWatered;
    }

    public boolean isFertilized() {
        return isFertilized;
    }

    public void setFertilized( boolean isFertilized ) {
        this.isFertilized = isFertilized;
    }
    
    public boolean isObstructed() {
        return isObstructed;
    }

    public void setObstructed( boolean isObstructed ) {
        this.isObstructed = isObstructed;
    }

    public Crop getCrop() {
        return this.crop;
    }

    public void setCrop( Crop crop ) {
        this.crop = crop;
    }

    public void removeCrop() {
        this.crop = new Crop();
    }
}