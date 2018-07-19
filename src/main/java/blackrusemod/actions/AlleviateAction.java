package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AlleviateAction extends AbstractGameAction {
	private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private float startingDuration;
	private AbstractPlayer p;
	public static int numExhausted;
	private int block;

	public AlleviateAction(AbstractPlayer p, int numCards, int block) {
		this.amount = numCards;
		this.p = p;
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.EXHAUST;
		this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
		this.block = block;
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
				this.p.hand.moveToDiscardPile(c);
				c.triggerOnManualDiscard();
				GameActionManager.incrementDiscard(false);
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}