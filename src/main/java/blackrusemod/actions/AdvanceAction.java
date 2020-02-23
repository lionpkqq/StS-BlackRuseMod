package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;

public class AdvanceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
	private AbstractPlayer p;
	ArrayList<AbstractCard> zero_cost = new ArrayList<AbstractCard>();

	public AdvanceAction(int numCards) {
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, numCards);
		this.actionType = ActionType.CARD_MANIPULATION;
	}

	public void update() {
		if(this.amount <= 0 || AbstractDungeon.player.drawPile.isEmpty()) {
			this.isDone = true;
			return;
		}
		ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
		for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if(c.costForTurn == 0) {
				cards.add(c);
				if(cards.size() >= this.amount) break;
			}
		}
		for(AbstractCard c : cards) {
			if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
				AbstractDungeon.player.createHandIsFullDialog();
				break;
			}
			this.p.drawPile.moveToHand(c);
		}
		this.isDone = true;
	}
}