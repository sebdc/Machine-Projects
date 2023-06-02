package source.framework.resources;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Resources {

    /**************************************** 
                 Title Screen
    ****************************************/
    public static final ArrayList<ImageIcon> TITLE_SCREEN = new ArrayList<>();

    public static final byte TITLE_BG = 0;
    public static final byte TITLE_MENU = 1;
    public static final byte TITLE_START = 2;
    public static final byte TITLE_EXIT = 3;

    /**************************************** 
                    Game Menu
    ****************************************/
    public static final ArrayList<ImageIcon> GAME_MENU = new ArrayList<>();

    public static final byte VISIT_FARM = 0;
    public static final byte VISIT_STORE = 1;
    public static final byte VISIT_HOUSE = 2;
    public static final byte VISIT_EXIT = 3;

    /**************************************** 
                    Farm
    ****************************************/
    public static final ArrayList<ImageIcon> FARM = new ArrayList<>();

    public static final byte TILE_WORD = 0;
    public static final byte FARMER_ICON = 1;
    public static final byte LEVEL_ICON = 1;
    public static final byte COIN_ICON = 3;
    public static final byte FARM_BG = 4;


    /**************************************** 
              Tiles, Tools, & Crop
    ****************************************/
    public static final ArrayList<ImageIcon> TILE = new ArrayList<>();
    public static final ArrayList<ImageIcon> SEEDS = new ArrayList<>();
    public static final ArrayList<ImageIcon> CROPS = new ArrayList<>();
    public static final ArrayList<ImageIcon> TOOLS = new ArrayList<>();

    /// Tile Textures    
    public static final byte ROCK = 0;
    public static final byte UNPLOWED_TILE = 1;
    public static final byte PLOWED_TILE = 2;

    /// Seed Textures
    public static final byte UNF_UNW_SEED = 0;
    public static final byte UNF_WAT_SEED = 1;
    public static final byte FER_UNW_SEED = 2;
    public static final byte FER_WAT_SEED = 3;
    public static final byte UNF_UNW_CROP = 4;
    public static final byte UNF_WAT_CROP = 5;
    public static final byte FER_UNW_CROP = 6;
    public static final byte FER_WAT_CROP = 7;

    /// Farm Crop Textures
    public static final byte TURNIP_CROP = 0;
    public static final byte CARROT_CROP = 1;
    public static final byte POTATO_CROP = 2;
    public static final byte ROSE_CROP = 3;
    public static final byte TULIP_CROP = 4;
    public static final byte SUNFLOWER_CROP = 5;
    public static final byte MANGO_CROP = 6;
    public static final byte APPLE_CROP = 7;
    public static final byte WITHERED_CROP = 8;

    /// Tool Textures
    public static final byte PLOW = 0;
    public static final byte PLANT = 1;
    public static final byte WATERINGCAN = 2;
    public static final byte FERTILIZER = 3;
    public static final byte HARVEST = 4;
    public static final byte CROP = 5;
    public static final byte SHOVEL = 6;
    public static final byte PICKAXE = 7;
    public static final byte PLANT_SEED = 8;

    /**************************************** 
                Store & Crop Menu
    ****************************************/
    public static final ArrayList<ImageIcon> STORE_MENU = new ArrayList<>();
    public static final ArrayList<ImageIcon> CROP_MENU = new ArrayList<>();

    /// Store Textures
    public static final byte TRACTOR_ICON = 0;
    public static final byte TRACTOR_ICON_2 = 1;
    public static final byte TER_ICON = 2;
    public static final byte CLOUD = 3;
    public static final byte ROCK_PIXEL = 4;
    public static final byte SIGN = 5;
    public static final byte TREE_PIXEL = 6;
    public static final byte BUY = 7;
    public static final byte BUYBTN = 8;

    /// Store Crop Textures
    public static final byte TURNIP = 0;
    public static final byte CARROT = 1;
    public static final byte POTATO = 2;
    public static final byte ROSE = 3;
    public static final byte TULIP = 4;
    public static final byte SUNFLOWER = 5;
    public static final byte MANGO = 6;
    public static final byte APPLE = 7;

    /// Background
    public static final byte GRASS_BG = 0;
    public static final byte BRICK_BG = 1;

    public static final byte CROP_INVENTORY = 8;

    /**************************************** 
                    Next Day
    ****************************************/
     public static final ArrayList<ImageIcon> NEXTDAY = new ArrayList<>();

     /// Next Day Textures
     public static final byte SLEEP_BUTTON = 0;
     public static final byte NEXT_DAY = 1;
     public static final byte LOSE = 2;
    

    /**************************************** 
                      Font
    ****************************************/
    public static final ArrayList<Font> FONT = new ArrayList<>();
    public static final byte PIXEL_EMULATOR = 0;

    /// ArrayLists

  
    public static ImageIcon resizeImage( ImageIcon imageIcon, int width, int height ) {
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance( width, height, java.awt.Image.SCALE_DEFAULT );
        return new ImageIcon( newImage ); 
    }
}
