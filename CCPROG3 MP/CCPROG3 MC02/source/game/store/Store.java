package source.game.store;
import source.game.farm.*;
import source.game.farmer.*;

/*******************************************************************
 *     
 *  The Store class allows the Farmer to buy seeds and upgrade their
 *  FarmerType if they meet the right condition. All the information
 *  about the different Crops and FarmerType are located inside this
 *  class.
 * 
*******************************************************************/
public class Store {

    /// Class Attributes
    private FarmerType[] type;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Store() {
        // - declare type[] 
        FarmerType[] type = new FarmerType[4];      

        // - initialize type[] 
        type[0] = new FarmerType( "Farmer", 0, 0, 0, 0, 0, 0 );  
        type[1] = new FarmerType( "Registered Farmer", 5, 200, 1, 1, 0, 0 );
        type[2] = new FarmerType( "Distinguished Farmer", 10, 300, 2, 2, 1, 0 );
        type[3] = new FarmerType( "Legendary Farmer", 15, 400, 4, 3, 2, 1 );
        
        // - initialize class attributes
        this.type = type;
    }   
    
    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/ 
    /**
     *   ` The farmer can register themselves for a fee, upgrading their FarmerType
     *   if they satisfy the following upgrade conditions for their current FarmerType :
     *   the level and the coin requirement.
     * 
     *    @param farmer             the farmer to be promoted
    */
    public void upgradeFarmerType( Farmer farmer ) {
        if( farmer.getType().getFarmerType().equals(type[2].getFarmerType()) && 
            farmer.getLevel() >= type[3].getLevelReq() &&
            farmer.getCoins() >= type[3].getRegisterFee() ) {
                farmer.setType( type[3] );
                farmer.setCoins( farmer.getCoins() - type[3].getRegisterFee() );
        } else if(
            farmer.getType().getFarmerType().equals(type[1].getFarmerType()) && 
            farmer.getLevel() >= type[2].getLevelReq() &&
            farmer.getCoins() >= type[2].getRegisterFee()) {
                farmer.setType( type[2] );
                farmer.setCoins( farmer.getCoins() - type[2].getRegisterFee() );
        } else if( 
            farmer.getType().getFarmerType().equals(type[0].getFarmerType()) && 
            farmer.getLevel() >= type[1].getLevelReq() &&
            farmer.getCoins() >= type[1].getRegisterFee() ) {
                farmer.setType( type[1] );
                farmer.setCoins( farmer.getCoins() - type[1].getRegisterFee() );
        }
    }

    /*|*******************************************************
     
                Getters : Harvest Crop Attributes

    *********************************************************/
    public FarmerType getFarmerType(int index ) {

        FarmerType farmerType;

        switch( index ) {
            case 1 : farmerType = type[1]; break;
            case 2 : farmerType = type[2]; break;
            case 3 : farmerType = type[3]; break;
            default : farmerType = type[0]; break;
        }

        return farmerType;
    }

    public FarmerType findNextFarmerType( String name ) {
        FarmerType farmerType; 

        switch( name ) {
            case "Farmer" : farmerType = type[1]; break; 
            case "Registered Farmer" : farmerType = type[2]; break;
            case "Distinguished Farmer" : farmerType = type[3]; break;
            case "Legendary Farmer" : farmerType = type[3]; break;
            default : farmerType = type[3]; break;
        }
        return farmerType;
    }

    /**
     *   ` Initialize a Crop with varying attributes and return it. The Crop
     *   object being returned depends on the index parameter.
     *
     *    @param index          an integer from 0 to the Crop ArrayList's size
     *    @return crop          the crop being bought from the store
    */
    public Crop buyCrop( int index ) {
        Crop crop = new Crop(); 

        switch( index ) {
            case 0 : 
                crop = new Crop( "Turnip", "Root Crop", 2, 1, 2, 0, 1, 1, 2, 5, 6, 5.0 ); break; 
            case 1 : 
                crop = new Crop( "Carrot", "Root Crop", 3, 1, 2, 0, 1, 1, 2, 10, 9, 7.5 ); break; 
            case 2 : 
                crop = new Crop( "Potato", "Root Crop", 5, 3, 4, 1,2, 1, 10, 20, 3, 12.5 ); break; 
            case 3 : 
                crop = new Crop( "Rose", "Flower", 1, 1, 2, 0, 1, 1, 1, 5, 5, 2.5 ); break; 
            case 4 : 
                crop = new Crop( "Tulip", "Flower", 2, 2, 3, 0, 1, 1, 1, 10, 9, 5.0 ); break; 
            case 5 : 
                crop = new Crop( "Sunflower", "Flower", 3, 2, 3, 1, 2, 1, 1, 20, 19, 7.5 ); break; 
            case 6 : 
                crop = new Crop( "Mango", "Fruit Tree", 10, 7, 7, 4, 4, 5, 15, 100, 8, 25.0 ); break;
            case 7 : 
                crop = new Crop( "Apple", "Fruit Tree", 10, 7, 7, 5, 5, 10, 15, 200, 5, 25.0 ); break; 
        }
        return crop; 
    }

    public Crop findCrop( String name ) {
        Crop crop = new Crop(); 

        switch( name ) {
            case "Turnip" : 
                crop = new Crop ("Turnip", "Root Crop", 2, 1, 2, 0, 1, 1, 2, 5, 6, 5.0 ); break; 
            case "Carrot" : 
                crop = new Crop( "Carrot", "Root Crop", 3, 1, 2, 0, 1, 1, 2, 10, 9, 7.5 ); break; 
            case "Potato" : 
                crop = new Crop( "Potato", "Root Crop", 5, 3, 4, 1,2, 1, 10, 20, 3, 12.5 ); break; 
            case "Rose" : 
                crop = new Crop( "Rose", "Flower", 1, 1, 2, 0, 1, 1, 1, 5, 5, 2.5 ); break; 
            case "Tulip" : 
                crop = new Crop( "Tulip", "Flower", 2, 2, 3, 0, 1, 1, 1, 10, 9, 5.0 ); break; 
            case "Sunflower" : 
                crop = new Crop( "Sunflower", "Flower", 3, 2, 3, 1, 2, 1, 1, 20, 19, 7.5 ); break; 
            case "Mango" : 
                crop = new Crop( "Mango", "Fruit Tree", 10, 7, 7, 4, 4, 5, 15, 100, 8, 25.0 ); break;
            case "Apple" : 
                crop = new Crop( "Apple", "Fruit Tree", 10, 7, 7, 5, 5, 10, 15, 200, 5, 25.0 ); break; 
        }
        return crop; 
    }


    
}