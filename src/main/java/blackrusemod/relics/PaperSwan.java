package blackrusemod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class PaperSwan extends CustomRelic {
	private static final String ID = "PaperSwan";
	
	public PaperSwan() {
		super(ID, BlackRuseMod.getPaperSwanTexture(), RelicTier.UNCOMMON, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new PaperSwan();
	}
}