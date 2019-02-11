package levelsegintrospiral.tools;

import levelsegintrospiral.spiralworld.sprite.Character;

public class SpriteInfo {
	public static final String TEXATLAS_FILENAME = "sprite/walksprites v4.pack";

	private static Character abc(String a, float b) {
		return new Character(a, b);
	}

	public static final Character[] TEMPLATES = new Character[] {
			abc("smb1_mario", 1f/15f),
			abc("smb1_luigi", 1f/15f),
			abc("samus", 1f/15f),
			abc("kid_icarus", 1f/15f),
			abc("link", 1f/15f),
			abc("mega_man", 7f/60f),
			abc("simon", 11f/60f),
			abc("bill_rizer", 2f/15f),
			abc("lance_bean", 2f/15f),
			abc("ryu_hyabusa", 1f/15f),
			abc("rushnattack_soldier", 7f/60f),
			abc("rygar", 7f/60f),
			abc("batman", 1f/10f),
			abc("scrooge_mcduck", 3f/20f),
			abc("smb2_mario", 1f/12f),
			abc("smb2_luigi", 1f/12f),	//dbl check this
			abc("smb2_peach", 1f/12f),	//dbl check this
			abc("smb2_toad", 1f/12f),	//dbl check this
			abc("smb3_mario", 1f/15f),
			abc("smb3_luigi", 1f/15f),
			abc("tmnt1_leo", 1f/7f),
			abc("tmnt1_don", 1f/7f),
			abc("tmnt1_mike", 1f/7f),
			abc("tmnt1_ralph", 1f/7f),
			abc("sophiaIII", 1f/20f),
			abc("arthur", 1f/15f),
			abc("mark", 2f/15f),
			abc("milon", 3f/20f),
			abc("popo", 1f/30f),
			abc("nana", 1f/30f)
		};
}
