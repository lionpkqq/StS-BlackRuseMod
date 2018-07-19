package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class StoneMask extends CustomRelic {
	private static final String ID = "StoneMask";
	private static final int HEAL_AMT = 1;
	
	public StoneMask() {
		super(ID, BlackRuseMod.getStoneMaskTexture(), RelicTier.BOSS, LandingSound.MAGICAL);
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action)
	{
		if (card.type == com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK) {
			flash();
			AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, HEAL_AMT));
			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		}
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new StoneMask();
	}
}