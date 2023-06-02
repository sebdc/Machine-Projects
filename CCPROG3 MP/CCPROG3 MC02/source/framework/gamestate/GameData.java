package source.framework.gamestate;
import source.game.farm.*;
import source.game.farmer.*;
import source.game.store.*;
import source.game.time.*;

public class GameData {
    
    private FarmLot farm;
    private Farmer farmer;
    private Store store;
    private Time time;

    public GameData() {
        farmer = new Farmer( "Dei" );
        farmer.setFarmerTools( new FarmerTools(farmer) );

        farm = new FarmLot( 5, 10 );
        farm.generateRocks( 3 );

        store = new Store();
        time = new Time( 1 );
    }

    /*|*******************************************************
     
                        Getters and Setters

    *********************************************************/ 
    public FarmLot getFarm() {
        return farm;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public Store getStore() {
        return store;
    }

    public Time getTime() {
        return time;
    }
}