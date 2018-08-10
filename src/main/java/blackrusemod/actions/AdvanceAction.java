package blackrusemod.actions;
 
import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AdvanceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
	private AbstractPlayer p;
	ArrayList<AbstractCard> zero_cost = new ArrayList<AbstractCard>();

	public AdvanceAction(int numCards) {
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, numCards);
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
	}

	public void update() {
		if (this.amount <= 0) {
			this.isDone = true;
			return;
		}
		if (AbstractDungeon.player.drawPile.isEmpty()) {
			this.isDone = true;
				return;
		}
		if (AbstractDungeon.player.hand.size() == 10) {
			AbstractDungeon.player.createHandIsFullDialog();
			this.isDone = true;
			return;
		}
		for (AbstractCard c: AbstractDungeon.player.drawPile.group)
			if (c.costForTurn == 0)
				zero_cost.add(c);
		if (zero_cost.size() != 0) {
			AbstractCard c = zero_cost.get(0);
			this.p.hand.addToHand(c);
			c.lighten(false);
			this.p.drawPile.removeCard(c);
			this.p.hand.refreshHandLayout();
		}
		this.amount--;
		if (this.amount != 0) 
			AbstractDungeon.actionManager.addToTop(new AdvanceAction(this.amount));
		this.isDone = true;
	}
}