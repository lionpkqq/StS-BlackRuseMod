package blackrusemod.relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class KneeBrace extends CustomRelic {
	public static final String ID = "BlackRuseMod:KneeBrace";
	public static final int BLOCK_AMT = 3;
	
	public KneeBrace() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.KNEE_BRACE_RELIC), ImageMaster.loadImage(BlackRuseMod.KNEE_BRACE_RELIC_OUTLINE), RelicTier.UNCOMMON, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new KneeBrace();
	}
}