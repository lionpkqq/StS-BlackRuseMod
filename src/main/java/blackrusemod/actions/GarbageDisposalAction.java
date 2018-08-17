package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.ElegancePower;

public class GarbageDisposalAction extends AbstractGameAction {
	private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private float startingDuration;
	private AbstractPlayer p;
	public static int numExhausted;

	public GarbageDisposalAction(AbstractPlayer p, int numCards) {
		this.amount = numCards;
		this.p = p;
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.EXHAUST;
		this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}

	public void update() {
		if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
			if (this.p.hand.size() == 0) {
				this.isDone = true;
				return;
			}

			numExhausted = this.amount;
			AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true);
			tickDuration();
			return;
		}
		
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				this.p.hand.moveToExhaustPile(c);
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ElegancePower(p, 1), 1));
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}