package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BacklashAction;

public class MysterySword extends CustomRelic {
	public static final String ID = "BlackRuseMod:MysterySword";
	public boolean activated = true;
	
	public MysterySword() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.MYSTERY_SWORD_RELIC), ImageMaster.loadImage(BlackRuseMod.MYSTERY_SWORD_RELIC_OUTLINE), RelicTier.BOSS, LandingSound.CLINK);
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type == AbstractCard.CardType.SKILL  && (this.activated)) {
			this.activated = false;
			flash();
			stopPulse();
			addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			addToBot(new BacklashAction(1));
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public void onEquip() {
		AbstractDungeon.player.energy.energyMaster += 1;
	}

	@Override
	public void onUnequip() {
		AbstractDungeon.player.energy.energyMaster -= 1;
	}
	
	@Override
	public void atTurnStart() {
		beginPulse();
		this.pulse = true;
		this.activated = true;
	}

	@Override
	public boolean checkTrigger() {
		return this.activated;
	}
	
	@Override
	public void onVictory() {
		stopPulse();
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new MysterySword();
	}
}