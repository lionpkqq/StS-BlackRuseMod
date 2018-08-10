package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.cards.Deadline;
import blackrusemod.cards.Read;
import blackrusemod.cards.ReturningBlade;
import blackrusemod.cards.Snipe;
import blackrusemod.cards.TimeTheft;

public class OldScarfAction extends AbstractGameAction {
	private float startingDuration;
	private AbstractPlayer p;

	public OldScarfAction() {
		this.p = AbstractDungeon.player;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.startingDuration = Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}

	public void update() {
		if (this.duration == Settings.ACTION_DUR_FAST) {
			for (AbstractCard c : p.drawPile.group)
				if (c.canUpgrade() && isVision(c)) {
					c.upgrade();
					c.superFlash();
				}
		}
		tickDuration();
	}
	
	public boolean isVision(AbstractCard c) {
		if (c instanceof Read) return true;
		if (c instanceof Snipe) return true;
		if (c instanceof TimeTheft) return true;
		if (c instanceof Deadline) return true;
		if (c instanceof ReturningBlade) return true;
		return false;
	}
}