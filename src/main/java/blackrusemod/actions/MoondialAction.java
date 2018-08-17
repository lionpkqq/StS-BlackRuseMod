package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.cards.TemporalDefense;
import blackrusemod.cards.TemporalEssence;
import blackrusemod.cards.TemporalMisd;
import blackrusemod.cards.TemporalSlicing;

public class MoondialAction extends AbstractGameAction {
	public MoondialAction (int amount) {
		this.amount = amount;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.target = AbstractDungeon.player;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
	}
	
	public void update() {
		if (this.target != null) {
			AbstractCard c;
			for (int i = 0; i < this.amount; i++) {
				int randomNum = AbstractDungeon.miscRng.random(9);
				if (randomNum == 0) c = new TemporalSlicing().makeCopy();
				else if (randomNum == 1) c = new TemporalMisd().makeCopy();
				else if (randomNum == 2) c = new TemporalDefense().makeCopy();
				else if (randomNum == 3) c = new TemporalSlicing().makeCopy();
				else if (randomNum == 4) c = new TemporalMisd().makeCopy();
				else if (randomNum == 5) c = new TemporalDefense().makeCopy();
				else if (randomNum == 6) c = new TemporalSlicing().makeCopy();
				else if (randomNum == 7) c = new TemporalMisd().makeCopy();
				else if (randomNum == 8) c = new TemporalDefense().makeCopy();
				else c = new TemporalEssence().makeCopy();
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
			}
		}
		this.isDone = true;
	}
}