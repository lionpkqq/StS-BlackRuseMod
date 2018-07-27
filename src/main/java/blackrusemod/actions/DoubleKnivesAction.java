package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.KnivesPower;

public class DoubleKnivesAction extends AbstractGameAction {
	public DoubleKnivesAction(AbstractCreature target)
	{
		this.duration = 0.5F;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.target = target;
		this.source = target;
	}

	public void update()
	{
		if ((this.duration == 0.5F) && (this.target != null) && (this.target.hasPower("KnivesPower"))) {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new KnivesPower(this.source, 
					this.target.getPower("KnivesPower").amount), this.target.getPower("KnivesPower").amount));
		}

		tickDuration();
	}
}