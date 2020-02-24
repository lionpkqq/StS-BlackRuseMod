package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;

public class AdvanceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
	private AbstractPlayer p;

	public AdvanceAction(int numCards) {
		this.p = AbstractDungeon.player;
		setValues(this.p, this.p, numCards);
		this.actionType = ActionType.CARD_MANIPULATION;
	}

	@Override
	public void update() {
		if(this.amount <= 0 || this.p.drawPile.isEmpty()) {
			this.isDone = true;
			return;
		}
		ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
		for(AbstractCard c : this.p.drawPile.group) {
			if(c.costForTurn == 0) {
				cards.add(c);
				if(cards.size() >= this.amount) break;
			}
		}
		for(AbstractCard c : cards) {
			if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {
				this.p.createHandIsFullDialog();
				break;
			}
			this.p.drawPile.moveToHand(c);
		}
		this.isDone = true;
	}
}