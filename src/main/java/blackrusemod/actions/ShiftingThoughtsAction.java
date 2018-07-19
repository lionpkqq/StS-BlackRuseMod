package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShiftingThoughtsAction extends AbstractGameAction
{
	private int discardTimes;

	public ShiftingThoughtsAction(int times)
	{
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
		this.source = AbstractDungeon.player;
		this.discardTimes = times;
	}
	
	public void update()
	{
		if (this.duration == Settings.ACTION_DUR_FAST) {
			if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
				this.isDone = true;
				return;
			}

			if (AbstractDungeon.player.drawPile.isEmpty()) {
				AbstractDungeon.actionManager.addToTop(new ShiftingThoughtsAction(this.discardTimes));
				AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
				this.isDone = true;
				return;
			}

			for (int i = 0; i < this.discardTimes; i++) {
				if (!AbstractDungeon.player.drawPile.isEmpty()) {
					AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
					AbstractDungeon.player.drawPile.moveToDiscardPile(card);
					card.triggerOnManualDiscard();
					GameActionManager.incrementDiscard(false);
				}
			}
			this.isDone = true;
		}
	}
}