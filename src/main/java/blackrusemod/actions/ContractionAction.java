package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import blackrusemod.powers.ProtectionPower;

public class ContractionAction extends AbstractGameAction {
	public ContractionAction(AbstractCreature target) {
		this.duration = 0.25F;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.target = target;
		this.source = target;
	}

	@Override
	public void update() {
		if (this.duration == 0.25F && this.target != null && this.target.currentBlock > 0) {
			addToTop(new ApplyPowerAction(this.target, this.source, new ProtectionPower(this.source, this.target.currentBlock), this.target.currentBlock));
			this.target.loseBlock();
		}
		tickDuration();
	}
}