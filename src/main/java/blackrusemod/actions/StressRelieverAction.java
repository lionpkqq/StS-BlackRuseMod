package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class StressRelieverAction extends AbstractGameAction {
	private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString("ReprogramAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private float startingDuration;

	public StressRelieverAction(int numCards) {
		this.amount = numCards;
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}

	public void update() {
		CardGroup tmpGroup;
		if (this.duration == this.startingDuration) {
			if (AbstractDungeon.player.drawPile.isEmpty()) {
				this.isDone = true;
				return;
			}
			tmpGroup = new CardGroup(com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED);
			for (int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); i++) {
				tmpGroup.addToTop(
						(AbstractCard)AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
			}
			AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, TEXT[0]);
		}
		else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
				AbstractDungeon.player.drawPile.moveToDiscardPile(c);
				c.triggerOnManualDiscard();
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
		}
		tickDuration();
	}
}