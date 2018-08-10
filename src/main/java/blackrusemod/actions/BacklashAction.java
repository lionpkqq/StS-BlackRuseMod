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
	
	public void update() {
		if ((this.duration == 0.3F) && (this.target != null)) {
			for (int i = 0; i < this.amount; i++) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, new WeakPower(this.target, 1, false), 1));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, new VulnerablePower(this.target, 1, false), 1));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, new FrailPower(this.target, 1, false), 1));
			}
		}
		tickDuration();
	}
}