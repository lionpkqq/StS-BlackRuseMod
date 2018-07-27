package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RewindAction extends AbstractGameAction
{
	private AbstractPlayer p;

	public RewindAction(int amount) {
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}

	public void update()
	{
		if (this.p.hand.size() >= 10 || this.p.discardPile.size() == 0) {
			this.isDone = true; return;
		}
		AbstractCard card;

		if (this.duration == 0.5F) {
			int index = this.p.discardPile.group.size() - 1;
			card = (AbstractCard)this.p.discardPile.group.get(index);
			this.p.hand.addToHand(card);
			card.lighten(false);
			this.p.discardPile.removeCard(card);
			this.p.hand.refreshHandLayout();
			this.isDone = true;
			tickDuration();
			return;
		}

		tickDuration();
	}
}