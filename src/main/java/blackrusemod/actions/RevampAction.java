package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.cards.Rearm;

public class RevampAction extends AbstractGameAction {
	private float startingDuration;
	private AbstractPlayer p;
	public static int numExhausted;
	private boolean canUpgrade;

	public RevampAction(AbstractPlayer p, int numCards, boolean canUpgrade) {
		this.amount = numCards;
		this.p = p;
		this.canUpgrade = canUpgrade;
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
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT)
				AbstractDungeon.handCardSelectScreen.open("·­ÐÂ", this.amount, true, true);
			else
				AbstractDungeon.handCardSelectScreen.open("revamp", this.amount, true, true);
			tickDuration();
			return;
		}
		
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				this.p.hand.moveToExhaustPile(c);
				AbstractCard d = new Rearm();
				if (!this.canUpgrade) d.upgrade();
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(d, false));
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}