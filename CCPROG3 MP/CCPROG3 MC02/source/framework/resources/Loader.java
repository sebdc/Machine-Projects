package source.framework.resources;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.File;

public class Loader {
	
    private static void loadTitleScreen() {
        String[] TitleScreen = {
            "resource/textures/titlescreen/TitleScreen.png",
            "resource/textures/titlescreen/TitleMenu.png",
            "resource/textures/titlescreen/StartButton.png",
            "resource/textures/titlescreen/QuitButton.png",
        };
    
        Resources.TITLE_SCREEN.add( Resources.TITLE_BG, new ImageIcon( TitleScreen[0] ) );
        Resources.TITLE_SCREEN.add( Resources.TITLE_MENU, new ImageIcon( TitleScreen[1] ) );
        Resources.TITLE_SCREEN.add( Resources.TITLE_START, new ImageIcon( TitleScreen[2] ) );
        Resources.TITLE_SCREEN.add( Resources.TITLE_EXIT, new ImageIcon( TitleScreen[3] ) );
    }

    private static void loadGameMenu() {
        String[] GameMenu = {
            "resource/textures/gamemenu/visitfarm.png",
            "resource/textures/gamemenu/visitstore.png",
            "resource/textures/gamemenu/visithouse.png",
            "resource/textures/gamemenu/visitexit.png"
        };
    
        Resources.GAME_MENU.add( Resources.VISIT_FARM, new ImageIcon( GameMenu[0]) );
        Resources.GAME_MENU.add( Resources.VISIT_STORE, new ImageIcon( GameMenu[1]) );
        Resources.GAME_MENU.add( Resources.VISIT_HOUSE, new ImageIcon( GameMenu[2]) );
        Resources.GAME_MENU.add( Resources.VISIT_EXIT, new ImageIcon( GameMenu[3]) );

    }

    // Textures used for FarmerDetails and TileDetails
    private static void loadFarmDetails() { 
        String[] FarmTextures = {
            "resource/textures/farm/details/Tile.png",
            "resource/textures/farm/details/Farmer.png",
            "resource/textures/farm/details/Level.png",
            "resource/textures/farm/details/Coins.png",
            "resource/textures/farm/details/Background.png"
        };

        Resources.FARM.add( Resources.TILE_WORD,       new ImageIcon( FarmTextures[0] ) );
        Resources.FARM.add( Resources.FARMER_ICON,     new ImageIcon( FarmTextures[1] ) );
        Resources.FARM.add( Resources.LEVEL_ICON,      new ImageIcon( FarmTextures[2] ) );
        Resources.FARM.add( Resources.COIN_ICON,       new ImageIcon( FarmTextures[3] ) );
        Resources.FARM.add( Resources.FARM_BG,         new ImageIcon( FarmTextures[4] ) );

    }

    private static void loadTileTextures() {
        String[] TileTextures = {
            "resource/textures/farm/tile/Rock.png",
            "resource/textures/farm/tile/UnplowedTile.png",
            "resource/textures/farm/tile/PlowedTile.png",
        };

		Resources.TILE.add( Resources.ROCK, new ImageIcon( TileTextures[0] ) );
		Resources.TILE.add( Resources.UNPLOWED_TILE, new ImageIcon( TileTextures[1] ) );
        Resources.TILE.add( Resources.PLOWED_TILE, new ImageIcon( TileTextures[2] ) );
    }

    private static void loadSeedTextures() {
        String[] SeedTextures = {
            "resource/textures/farm/crops/seed/UnfertilizedUnwateredSeeds.png",
            "resource/textures/farm/crops/seed/UnfertilizedWateredSeeds.png",
            "resource/textures/farm/crops/seed/FertilizedUnwateredSeeds.png",
            "resource/textures/farm/crops/seed/FertilizedWateredSeeds.png",

            "resource/textures/farm/crops/growing/UnfertilizedUnwateredCrop.gif",
            "resource/textures/farm/crops/growing/UnfertilizedWateredCrop.gif",
            "resource/textures/farm/crops/growing/FertilizedUnwateredCrop.gif",
            "resource/textures/farm/crops/growing/FertilizedWateredCrop.gif",
        };

		Resources.SEEDS.add( Resources.UNF_UNW_SEED, new ImageIcon( SeedTextures[0] ) );
		Resources.SEEDS.add( Resources.UNF_WAT_SEED, new ImageIcon( SeedTextures[1] ) );
        Resources.SEEDS.add( Resources.FER_UNW_SEED, new ImageIcon( SeedTextures[2] ) );
        Resources.SEEDS.add( Resources.FER_WAT_SEED, new ImageIcon( SeedTextures[3] ) );

        Resources.SEEDS.add( Resources.UNF_UNW_CROP, new ImageIcon( SeedTextures[4] ) );
		Resources.SEEDS.add( Resources.UNF_WAT_CROP, new ImageIcon( SeedTextures[5] ) );
        Resources.SEEDS.add( Resources.FER_UNW_CROP, new ImageIcon( SeedTextures[6] ) );
        Resources.SEEDS.add( Resources.FER_WAT_CROP, new ImageIcon( SeedTextures[7] ) );
    }

    private static void loadCropTextures() {
        String[] CropTexture = {
            "resource/textures/farm/crops/mature/Turnip.gif",
            "resource/textures/farm/crops/mature/Carrot.gif",
            "resource/textures/farm/crops/mature/Potato.gif",
            "resource/textures/farm/crops/mature/Rose.gif",
            "resource/textures/farm/crops/mature/Tulip.gif",
            "resource/textures/farm/crops/mature/Sunflower.gif",
            "resource/textures/farm/crops/mature/Mango.gif",
            "resource/textures/farm/crops/mature/Apple.gif",
            "resource/textures/farm/crops/mature/Withered.png",
        };
    
        Resources.CROPS.add( Resources.TURNIP_CROP, new ImageIcon( CropTexture[0] ) );
        Resources.CROPS.add( Resources.CARROT_CROP, new ImageIcon( CropTexture[1] ) );
        Resources.CROPS.add( Resources.POTATO_CROP, new ImageIcon( CropTexture[2] ) );
        Resources.CROPS.add( Resources.ROSE_CROP, new ImageIcon( CropTexture[3] ) );
        Resources.CROPS.add( Resources.TULIP_CROP, new ImageIcon( CropTexture[4] ) );
        Resources.CROPS.add( Resources.SUNFLOWER_CROP, new ImageIcon( CropTexture[5] ) );
        Resources.CROPS.add( Resources.MANGO_CROP, new ImageIcon( CropTexture[6] ) );
        Resources.CROPS.add( Resources.APPLE_CROP, new ImageIcon( CropTexture[7] ) );
        Resources.CROPS.add( Resources.WITHERED_CROP, new ImageIcon( CropTexture[8] ) );
    }

    private static void loadToolTextures() {
        String[] ToolTextures = {
            "resource/textures/farm/tools/Plow.png",
            "resource/textures/farm/tools/PlantSeed.png",
            "resource/textures/farm/tools/WateringCan.png",
            "resource/textures/farm/tools/Fertilizer.png",
            "resource/textures/farm/tools/Harvest.png",
            "resource/textures/farm/tools/SeedMenu.png",
            "resource/textures/farm/tools/Shovel.png",
            "resource/textures/farm/tools/Pickaxe.png"
        };

        Resources.TOOLS.add( Resources.PLOW,             new ImageIcon( ToolTextures[0] ) );
        Resources.TOOLS.add( Resources.PLANT,            new ImageIcon( ToolTextures[1] ) );
        Resources.TOOLS.add( Resources.WATERINGCAN,      new ImageIcon( ToolTextures[2] ) );
        Resources.TOOLS.add( Resources.FERTILIZER,       new ImageIcon( ToolTextures[3] ) );
        Resources.TOOLS.add( Resources.HARVEST,          new ImageIcon( ToolTextures[4] ) );
        Resources.TOOLS.add( Resources.CROP,             new ImageIcon( ToolTextures[5] ) );
        Resources.TOOLS.add( Resources.SHOVEL,           new ImageIcon( ToolTextures[6] ) );
        Resources.TOOLS.add( Resources.PICKAXE,          new ImageIcon( ToolTextures[7] ) );
        
    }

    private static void loadStoreMenu() {

        String[] StoreTextures = {
            "resource/textures/store/tractor.png",
            "resource/textures/store/tractor2.png",
            "resource/textures/store/ter.png",
            "resource/textures/store/cloud.gif",
            "resource/textures/store/rock.png",
            "resource/textures/store/sign.png",
            "resource/textures/store/tree.png",
            "resource/textures/store/buy.png",
            "resource/textures/store/buybutton.png"
        };
    
        Resources.STORE_MENU.add( Resources.TRACTOR_ICON,    new ImageIcon( StoreTextures[0] ) );
        Resources.STORE_MENU.add( Resources.TRACTOR_ICON_2,  new ImageIcon( StoreTextures[1] ) );
        Resources.STORE_MENU.add( Resources.TER_ICON,        new ImageIcon( StoreTextures[2] ) );
        Resources.STORE_MENU.add( Resources.CLOUD,           new ImageIcon( StoreTextures[3] ) );
        Resources.STORE_MENU.add( Resources.ROCK_PIXEL,      new ImageIcon( StoreTextures[4] ) );
        Resources.STORE_MENU.add( Resources.SIGN,            new ImageIcon( StoreTextures[5] ) );
        Resources.STORE_MENU.add( Resources.TREE_PIXEL,      new ImageIcon( StoreTextures[6] ) );
        Resources.STORE_MENU.add( Resources.BUY,             new ImageIcon( StoreTextures[7] ) );
        Resources.STORE_MENU.add( Resources.BUYBTN,          new ImageIcon( StoreTextures[8] ) );

    }

    private static void loadCropMenu() {
        String[] CropMenu  = {
            "resource/textures/store/crops/Turnip.png",
            "resource/textures/store/crops/Carrot.png",
            "resource/textures/store/crops/Potato.png",
            "resource/textures/store/crops/Rose.png",
            "resource/textures/store/crops/Tulip.png",
            "resource/textures/store/crops/Sunflower.png",
            "resource/textures/store/crops/Mango.png",
            "resource/textures/store/crops/Apple.png",
            "resource/textures/store/crops/Background.gif",
        };

        Resources.CROP_MENU.add( Resources.TURNIP, new ImageIcon( CropMenu[0]) );
        Resources.CROP_MENU.add( Resources.CARROT, new ImageIcon( CropMenu[1]) );
        Resources.CROP_MENU.add( Resources.POTATO, new ImageIcon( CropMenu[2]) );
        Resources.CROP_MENU.add( Resources.ROSE, new ImageIcon( CropMenu[3]) );
        Resources.CROP_MENU.add( Resources.TULIP, new ImageIcon( CropMenu[4]) );
        Resources.CROP_MENU.add( Resources.SUNFLOWER, new ImageIcon( CropMenu[5]) );
        Resources.CROP_MENU.add( Resources.MANGO, new ImageIcon( CropMenu[6]) );
        Resources.CROP_MENU.add( Resources.APPLE, new ImageIcon( CropMenu[7]) );
        Resources.CROP_MENU.add( Resources.CROP_INVENTORY,new ImageIcon( CropMenu[8] ) );

    }

    private static void loadNextDay() {

        String NextDayTextures[] = {
           "resource/textures/nextday/sleep.gif",
            "resource/textures/nextday/nextday.gif",
            "resource/textures/nextday/lose.png"
        };

        Resources.NEXTDAY.add( Resources.SLEEP_BUTTON,          new ImageIcon( NextDayTextures[0] ) );
        Resources.NEXTDAY.add( Resources.NEXT_DAY,              new ImageIcon( NextDayTextures[1] ) );
        Resources.NEXTDAY.add( Resources.LOSE,                  new ImageIcon( NextDayTextures[2] ) );
    }

    private static final String[] font = {
        "resource/textures/font/PixelEmulator-xq08.ttf"
    };

	public static void load() {
        
        loadTitleScreen();
        loadGameMenu();
        loadFarmDetails();
        loadTileTextures();
        loadSeedTextures();
        loadCropTextures();
        loadToolTextures();
        loadStoreMenu();
        loadCropMenu();
        loadNextDay();

        Resources.FONT.add( Resources.PIXEL_EMULATOR,    customFont( font[0]) );
	}

    private static Font customFont( String fontPath ){
        Font customFont = new Font( "Monospaced", Font.TRUETYPE_FONT, 10 );

        // load a custom font in your project folder
        try {
            customFont = Font.createFont( Font.TRUETYPE_FONT, new File(fontPath) ).deriveFont(20f);	
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont( Font.createFont( Font.TRUETYPE_FONT, new File(fontPath) ) );
            return customFont;			
        }
        catch(IOException | FontFormatException e){
            
        }
        return customFont; 
    }
}