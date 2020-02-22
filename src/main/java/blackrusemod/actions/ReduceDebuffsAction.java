package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.powers.ProtectionPower;

public class ReduceDebuffsAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {

	public ReduceDebuffsAction(AbstractCreature target, int protection) {
		this.target = target;
		this.amount = protection;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = 0.3F;
	}

	@Override
	public void update() {
		if (this.duration == 0.3F && this.target != null) {
			int prot = 0;
			AbstractPower weak = this.target.getPower(WeakPower.POWER_ID);
			AbstractPower vuln = this.target.getPower(VulnerablePower.POWER_ID);
			AbstractPower frail = this.target.getPower(FrailPower.POWER_ID);
			if (weak != null) {
				prot += weak.amount;
				addToBot(new RemoveSpecificPowerAction(this.target, this.target, weak));
			}
			if (vuln != null) {
				prot += vuln.amount;
				addToBot(new RemoveSpecificPowerAction(this.target, this.target, vuln));
			}
			if (frail != null) {
				prot += frail.amount;
				addToBot(new RemoveSpecificPowerAction(this.target, this.target, frail));
			}
			if(prot > 0) {
				prot = Math.min(999, prot);
				addToBot(new ApplyPowerAction(this.target, this.target, new ProtectionPower(this.target, prot * this.amount), prot * this.amount));
			}
		}
		tickDuration();
	}
}