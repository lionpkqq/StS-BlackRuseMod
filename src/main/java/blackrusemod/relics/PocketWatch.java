package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class PocketWatch extends CustomRelic {
	public static final String ID = "BlackRuseMod:PocketWatch";
	
	public PocketWatch() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.POCKET_WATCH_RELIC), ImageMaster.loadImage(BlackRuseMod.POCKET_WATCH_RELIC_OUTLINE), RelicTier.RARE, LandingSound.MAGICAL);
	}
	
	public void onExhaust(AbstractCard card)
	{
		flash();
		AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new PocketWatch();
	}
}