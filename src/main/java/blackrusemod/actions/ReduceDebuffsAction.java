package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.ProtectionPower;

public class ReduceDebuffsAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {

	public ReduceDebuffsAction(AbstractCreature target, int protection) {
		this.target = target;
		this.amount = protection;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = 0.3F;
	}

	public void update() {
		if ((this.duration == 0.3F) && (this.target != null)) {
			if (this.target.hasPower("Weakened")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
						new ProtectionPower(this.target, Math.min(999*this.amount, this.target.getPower("Weakened").amount*this.amount)), 
						Math.min(999*this.amount, this.target.getPower("Weakened").amount*this.amount)));
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.target, "Weakened"));
			}
			if (this.target.hasPower("Vulnerable")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
						new ProtectionPower(this.target, Math.min(999*this.amount, this.target.getPower("Vulnerable").amount*this.amount)), 
						Math.min(999*this.amount, this.target.getPower("Vulnerable").amount*this.amount)));
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.target, "Vulnerable"));
			}
			if (this.target.hasPower("Frail")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
						new ProtectionPower(this.target, Math.min(999*this.amount, this.target.getPower("Frail").amount*this.amount)), 
						Math.min(999*this.amount, this.target.getPower("Frail").amount*this.amount)));
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.target, "Frail"));
			}
		}
		tickDuration();
	}
}