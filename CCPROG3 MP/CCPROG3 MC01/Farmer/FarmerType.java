package Farmer;

/*******************************************************************
 *     
 *  The FarmerType refers to the rank of the farmer. Each rank 
 *  should offer different benefits to the Farmer when it comes
 *  to farming-related chores. 
 * 
*******************************************************************/

public class FarmerType {
    
    /// Class Attributes
    private String farmerType;
    private int levelReq;
    private int registerFee;
    private int bonusEarningPerProduce;
    private int seedCostReduction;
    private int waterBonusInc;
    private int fertilizerBonusInc;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public FarmerType( String farmerType, int levelReq, int registerFee, int bonusEarningPerProduce,
                       int seedCostReduction, int waterBonusInc, int fertilizerBonusInc ) {
        this.farmerType = farmerType;
        this.levelReq = levelReq;
        this.registerFee = registerFee;
        this.bonusEarningPerProduce = bonusEarningPerProduce;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusInc = waterBonusInc;
        this.fertilizerBonusInc = fertilizerBonusInc;
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/
    public String getFarmerType() {
        return farmerType;
    }

    public void setFarmerType(String farmerType) {
        this.farmerType = farmerType;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

    public int getRegisterFee() {
        return registerFee;
    }

    public void setRegisterFee(int registerFee) {
        this.registerFee = registerFee;
    }

    public int getBonusEarningPerProduce() {
        return bonusEarningPerProduce;
    }

    public void setBonusEarningPerProduce(int bonusEarningPerProduce) {
        this.bonusEarningPerProduce = bonusEarningPerProduce;
    }

    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    public void setSeedCostReduction(int seedCostReduction) {
        this.seedCostReduction = seedCostReduction;
    }

    public int getWaterBonusInc() {
        return waterBonusInc;
    }

    public void setWaterBonusInc(int waterBonusInc) {
        this.waterBonusInc = waterBonusInc;
    }

    public int getFertilizerBonusInc() {
        return fertilizerBonusInc;
    }

    public void setFertilizerBonusInc(int fertilizerBonusInc) {
        this.fertilizerBonusInc = fertilizerBonusInc;
    }
}
