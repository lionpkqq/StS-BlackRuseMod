package blackrusemod.relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class PaperSwan extends CustomRelic {
	private static final String ID = "PaperSwan";
	
	public PaperSwan() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.PAPER_SWAN_RELIC), ImageMaster.loadImage(BlackRuseMod.PAPER_SWAN_RELIC_OUTLINE), RelicTier.UNCOMMON, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new PaperSwan();
	}
}