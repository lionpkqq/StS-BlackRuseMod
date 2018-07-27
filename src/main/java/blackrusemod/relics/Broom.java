package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class Broom extends CustomRelic {
	private static final String ID = "Broom";
	private static final int COUNT = 10;
	
	public Broom() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.BROOM_RELIC), ImageMaster.loadImage(BlackRuseMod.BROOM_RELIC_OUTLINE), RelicTier.COMMON, LandingSound.FLAT);
		this.counter = 0;
	}
	
	public void onManualDiscard()
	 {
		this.counter += 1;
		if (this.counter >= COUNT) {
			flash();
			AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			this.counter -= COUNT;
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
		}
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new Broom();
	}
}