package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.cards.TemporalDefense;
import blackrusemod.cards.TemporalEssence;
import blackrusemod.cards.TemporalMisd;
import blackrusemod.cards.TemporalSlicing;

public class MakeRandomTemporalCardAction extends AbstractGameAction {
	public CardGroup group;
	
	public MakeRandomTemporalCardAction(CardGroup group, int amount) {
		this.group = group;
		this.amount = amount;
	}

	@Override
	public void update() {
		AbstractCard c;
		for (int i = 0; i < this.amount; i++) {
			// 30% Slicing, Misdirect, Defend
			// 10% Essence
			int randomNum = AbstractDungeon.miscRng.random(9);
			if (randomNum >= 0 && randomNum <= 2) c = new TemporalSlicing();
			else if (randomNum >= 3 && randomNum <= 5) c = new TemporalMisd();
			else if (randomNum >= 6 && randomNum <= 8) c = new TemporalDefense();
			else c = new TemporalEssence();
			if(this.group == AbstractDungeon.player.hand) {
				addToTop(new MakeTempCardInHandAction(c));
			} else if(this.group == AbstractDungeon.player.drawPile) {
				addToTop(new MakeTempCardInDrawPileAction(c, 1, true, false));
			} else if(this.group == AbstractDungeon.player.discardPile) {
				addToTop(new MakeTempCardInDiscardAction(c, 1));
			}
		}
		this.isDone = true;
	}
}