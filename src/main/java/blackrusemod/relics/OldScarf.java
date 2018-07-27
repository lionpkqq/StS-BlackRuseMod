package blackrusemod.relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class OldScarf extends CustomRelic {
	private static final String ID = "OldScarf";
	
	public OldScarf() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.OLD_SCARF_RELIC), ImageMaster.loadImage(BlackRuseMod.OLD_SCARF_RELIC_OUTLINE), RelicTier.RARE, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new OldScarf();
	}
}