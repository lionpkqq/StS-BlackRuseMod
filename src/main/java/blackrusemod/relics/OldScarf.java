package blackrusemod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class OldScarf extends CustomRelic {
	private static final String ID = "OldScarf";
	
	public OldScarf() {
		super(ID, BlackRuseMod.getOldScarfTexture(), RelicTier.RARE, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new OldScarf();
	}
}