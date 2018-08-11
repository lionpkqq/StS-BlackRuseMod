package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
		if (card.type == AbstractCard.CardType.ATTACK  && (this.activated)) {
			this.activated = false;
			flash();
			AbstractMonster m = null;
			if (action.target != null) {
				m = (AbstractMonster)action.target;
			}
			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			AbstractCard tmp = card.makeStatEquivalentCopy();
			tmp.current_x = card.current_x;
			tmp.current_y = card.current_y;
			tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
			tmp.target_y = (Settings.HEIGHT / 2.0F);
			tmp.freeToPlayOnce = true;
			tmp.applyPowers();
			tmp.purgeOnUse = true;
			AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m));
			stopPulse();
			AbstractDungeon.actionManager.addToBottom(new BacklashAction(1));
		}
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
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