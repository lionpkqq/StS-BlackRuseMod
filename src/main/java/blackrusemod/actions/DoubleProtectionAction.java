package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import blackrusemod.powers.ProtectionPower;

public class DoubleProtectionAction extends AbstractGameAction {
	public DoubleProtectionAction(AbstractCreature target) {
		this.duration = 0.3F;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.target = target;
		this.source = target;
	}

	@Override
	public void update() {
		if ((this.duration == 0.3F) && (this.target != null) && (this.target.hasPower(ProtectionPower.POWER_ID))) {
			addToTop(new ApplyPowerAction(this.target, this.source, new ProtectionPower(this.source, this.target.getPower(ProtectionPower.POWER_ID).amount), this.target.getPower(ProtectionPower.POWER_ID).amount));
		}
		tickDuration();
	}
}