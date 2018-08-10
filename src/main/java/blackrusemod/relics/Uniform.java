package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;

public class Uniform extends CustomRelic {
	private static final String ID = "Uniform";
	private static final int KNIVES = 6;
	
	public Uniform() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.UNIFORM_RELIC), ImageMaster.loadImage(BlackRuseMod.UNIFORM_RELIC_OUTLINE), RelicTier.STARTER, LandingSound.MAGICAL);
	}

	public void atBattleStart() {
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new KnivesPower(AbstractDungeon.player, KNIVES)));
	}
	
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new Uniform();
	}	
}