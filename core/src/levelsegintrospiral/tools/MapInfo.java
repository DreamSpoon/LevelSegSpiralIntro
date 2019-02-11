package levelsegintrospiral.tools;

public class MapInfo {
	// tile size (in pixels)
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;

	// maximum height of slice (in tiles)
	public static final int MAX_SLICE_HEIGHT_MAIN = 9;

	private static final String DIR = "map/";
	public static final String[] FILENAMES = new String[] {
			DIR+"SMB1_1-1.1.tmx",
			DIR+"SMB1_1-1.2.tmx",
			DIR+"Metroid1.1.tmx",
			DIR+"Metroid1.2.tmx",
			DIR+"KidIcarus1-1.1.tmx",
			DIR+"KidIcarus1-1.2.tmx",
			DIR+"Zelda2_1.1.tmx",
			DIR+"Zelda2_1.2.tmx",
			DIR+"MegaMan2_1.1.tmx",
			DIR+"MegaMan2_1.2.tmx",
			DIR+"Castlevania2_1.tmx",
			DIR+"Contra1_1-1.1.tmx",
			DIR+"Contra1_1-1.2.tmx",
			DIR+"NinjaGaiden1.1.tmx",
			DIR+"NinjaGaiden1.2.tmx",
			DIR+"RushNAttack1.1.tmx",
			DIR+"RushNAttack1.2.tmx",
			DIR+"Rygar1.1.tmx",
			DIR+"Rygar1.2.tmx",
			DIR+"Batman1.1.tmx",
			DIR+"Batman1.2.tmx",
			DIR+"BlasterMaster1.1.tmx",
			DIR+"BlasterMaster1.2.tmx",
			DIR+"DuckTales1.1.tmx",
			DIR+"DuckTales1.2.tmx",
			DIR+"SMB2_1-1.1.tmx",
			DIR+"SMB2_1-1.2.tmx",
			DIR+"SMB3_1-1.1.tmx",
			DIR+"SMB3_1-1.2.tmx",
			DIR+"TMNT1_1.1.tmx",
			DIR+"TMNT1_1.2.tmx",
			DIR+"GhostsNGoblins1.1.tmx",
			DIR+"GhostsNGoblins1.2.tmx",
			DIR+"IceClimber1.1.tmx",
			DIR+"IceClimber1.2.tmx",
			DIR+"MilonsSC1.1.tmx",
			DIR+"MilonsSC1.2.tmx",
			DIR+"MonsterParty1.1.tmx",
			DIR+"MonsterParty1.2.tmx"
		};

	public static final int MAX_SLICE_HEIGHT_CHARS = 22;
	public static final String[] THUMB_FILENAMES = new String[] {
			DIR+"charactersv3.tmx"
		};
}
