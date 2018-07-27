package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.ProtectionPower;

public class ReduceDebuffsAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {

	public ReduceDebuffsAction(AbstractCreature target, int turns) {
		this.target = target;
		this.amount = turns;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = 0.8F;
	}

	public void update() {
		if ((this.duration == 0.8F) && (this.target != null)) {
			if (this.target.hasPower("Weakened")) {
				AbstractDungeon.actionManager.addToBottom(
						new ReducePowerAction(this.target, this.target, "Weakened", Math.min(this.amount, this.target.getPower("Weakened").amount)));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
						new ProtectionPower(this.target, Math.min(this.amount*2, this.target.getPower("Weakened").amount*2)), 
						Math.min(this.amount*2, this.target.getPower("Weakened").amount*2)));
				if (this.target.getPower("Weakened").amount == 0)  AbstractDungeon.actionManager.addToBottom(
						new RemoveSpecificPowerAction(this.target, this.target, "Weakened"));
			}
			if (this.target.hasPower("Vulnerable")) {
				AbstractDungeon.actionManager.addToBottom(
						new ReducePowerAction(this.target, this.target, "Vulnerable", Math.min(this.amount, this.target.getPower("Vulnerable").amount)));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
						new ProtectionPower(this.target, Math.min(this.amount*2, this.target.getPower("Vulnerable").amount*2)), 
						Math.min(this.amount*2, this.target.getPower("Vulnerable").amount*2)));
				if (this.target.getPower("Vulnerable").amount == 0)  AbstractDungeon.actionManager.addToBottom(
						new RemoveSpecificPowerAction(this.target, this.target, "Vulnerable"));
			}
			if (this.target.hasPower("Frail")) {
				AbstractDungeon.actionManager.addToBottom(
						new ReducePowerAction(this.target, this.target, "Frail", Math.min(this.amount, this.target.getPower("Frail").amount)));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
						new ProtectionPower(this.target, Math.min(this.amount*2, this.target.getPower("Frail").amount*2)), 
						Math.min(this.amount*2, this.target.getPower("Frail").amount*2)));
				if (this.target.getPower("Frail").amount == 0)  AbstractDungeon.actionManager.addToBottom(
						new RemoveSpecificPowerAction(this.target, this.target, "Frail"));
			}
		}
		tickDuration();
	}
}