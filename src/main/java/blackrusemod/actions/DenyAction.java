package blackrusemod.actions;
 
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.FrozenEye;

public class DenyAction extends AbstractGameAction {
	private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString("ReprogramAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private float startingDuration;
	private boolean anyNumber;

	public DenyAction(int numCards, boolean anyNumber) {
		this.amount = numCards;
		this.actionType = ActionType.DISCARD;
		this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
		this.anyNumber = anyNumber;
	}

	@Override
	public void update() {
		CardGroup tmpGroup;
		if (this.duration == this.startingDuration) {
			if (AbstractDungeon.player.drawPile.isEmpty()) {
				this.isDone = true;
				return;
			}
			tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			for (int i = 0; i < AbstractDungeon.player.drawPile.size(); i++) {
				tmpGroup.addToTop(
						(AbstractCard)AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
			}
			// Show the draw pile sorted if you don't have Frozen Eye
			if(!AbstractDungeon.player.hasRelic(FrozenEye.ID)) tmpGroup.sortByRarityPlusStatusCardType(true);
			
			if (!this.anyNumber) AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, TEXT[0], false, false, this.anyNumber, false);
			else AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, this.anyNumber, TEXT[0]);
		}
		else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
				AbstractDungeon.player.drawPile.moveToDiscardPile(c);
				c.triggerOnManualDiscard();
				GameActionManager.incrementDiscard(false);
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
		}
		tickDuration();
	}
}