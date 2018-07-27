package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DuplicationAction extends AbstractGameAction {
	private float startingDuration;
	private AbstractPlayer p;
	public static int numExhausted;

	public DuplicationAction(AbstractPlayer p, int numCards) {
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
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT)
				AbstractDungeon.handCardSelectScreen.open("¸´ÖÆ", this.amount, true, true);
			else
				AbstractDungeon.handCardSelectScreen.open("duplicate", this.amount, true, true);
			tickDuration();
			return;
		}
		
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
				AbstractCard d = c.makeStatEquivalentCopy();
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(d, false));
				d.setCostForTurn(-9);
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}