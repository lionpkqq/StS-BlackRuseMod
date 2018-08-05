package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.ProtectionPower;

public class TimeEmbeddedAction extends AbstractGameAction {
	public TimeEmbeddedAction(AbstractCreature target)
	{
		this.duration = 0.25F;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.target = target;
		this.source = target;
	}

	public void update()
	{
		if ((this.duration == 0.25F) && (this.target != null) && (this.target.currentBlock > 0)) {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, 
					new ProtectionPower(this.source, this.target.currentBlock), this.target.currentBlock));
			this.target.loseBlock();
		}
		tickDuration();
	}
}