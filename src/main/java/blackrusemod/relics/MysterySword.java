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
	private static final String ID = "MysterySword";
	private boolean activated = true;
	
	public MysterySword() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.MYSTERY_SWORD_RELIC), ImageMaster.loadImage(BlackRuseMod.MYSTERY_SWORD_RELIC_OUTLINE), RelicTier.BOSS, LandingSound.CLINK);
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type == AbstractCard.CardType.SKILL  && (this.activated)) {
			this.activated = false;
			flash();
			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			stopPulse();
			AbstractDungeon.actionManager.addToBottom(new BacklashAction(1));
		}
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public void onEquip()
	{
		AbstractDungeon.player.energy.energyMaster += 1;
	}

	public void onUnequip()
	{
		AbstractDungeon.player.energy.energyMaster -= 1;
	}
	
	public void atTurnStart()
	{
		beginPulse();
		this.pulse = true;
		this.activated = true;
	}

	public boolean checkTrigger()
	{
		return this.activated;
	}
	
	public AbstractRelic makeCopy() {
		return new MysterySword();
	}
}