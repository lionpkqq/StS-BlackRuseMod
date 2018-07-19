package blackrusemod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class KneeBrace extends CustomRelic {
	private static final String ID = "KneeBrace";
	private static final int BLOCK = 3;
	
	public KneeBrace() {
		super(ID, BlackRuseMod.getKneeBraceTexture(), RelicTier.UNCOMMON, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0] + BLOCK + this.DESCRIPTIONS[1];
	}
	
	public AbstractRelic makeCopy() {
		return new KneeBrace();
	}
}