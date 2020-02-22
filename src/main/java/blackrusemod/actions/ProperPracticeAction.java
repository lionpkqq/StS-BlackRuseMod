package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class ProperPracticeAction extends AbstractGameAction {
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private float startingDuration;
	private AbstractPlayer p;

	public ProperPracticeAction(AbstractPlayer p, int numCards) {
		this.amount = numCards;
		this.p = p;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.startingDuration = Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}

	@Override
	public void update() {
		if (this.duration == Settings.ACTION_DUR_FAST) {
			if (this.p.hand.size() == 0) {
				this.isDone = true;
				return;
			}

			AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true, false, true);
			tickDuration();
			return;
		}
		
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				if (c.canUpgrade()) {
					c.upgrade();
					c.initializeDescription();
					c.superFlash();
				}
				this.p.hand.addToTop(c);
			}
			this.p.hand.refreshHandLayout();
			this.p.hand.glowCheck();
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}