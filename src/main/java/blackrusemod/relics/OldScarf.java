package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.OldScarfAction;

public class OldScarf extends CustomRelic {
	public static final String ID = "BlackRuseMod:OldScarf";
	
	public OldScarf() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.OLD_SCARF_RELIC), ImageMaster.loadImage(BlackRuseMod.OLD_SCARF_RELIC_OUTLINE), RelicTier.RARE, LandingSound.FLAT);
	}
	
	@Override
	public void atBattleStart() {
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new OldScarfAction());
		this.grayscale = true;
	}
	
	@Override
	public void onVictory() {
		this.grayscale = false;
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new OldScarf();
	}
}