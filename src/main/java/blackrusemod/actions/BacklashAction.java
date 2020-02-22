package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BacklashAction extends AbstractGameAction {
	public BacklashAction (int amount) {
		this.amount = amount;
		this.actionType = AbstractGameAction.ActionType.DEBUFF;
		this.target = AbstractDungeon.player;
		this.duration = 0.3F;
	}
	
	@Override
	public void update() {
		if (this.duration == 0.3F && this.target != null) {
			addToBot(new ApplyPowerAction(this.target, this.target, new WeakPower(this.target, this.amount, false), this.amount));
			addToBot(new ApplyPowerAction(this.target, this.target, new VulnerablePower(this.target, this.amount, false), this.amount));
			addToBot(new ApplyPowerAction(this.target, this.target, new FrailPower(this.target, this.amount, false), this.amount));
		}
		tickDuration();
	}
}