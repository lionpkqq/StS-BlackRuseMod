package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FollowUpAction extends AbstractGameAction
{
	public FollowUpAction(AbstractCreature target)
	{
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.BLOCK;
		this.target = target;
	}

	public void update()
	{
		if ((this.duration == Settings.ACTION_DUR_XFAST) && 
				(this.target != null) && (this.target.hasPower("AmplifyDamagePower"))) {
			AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(AbstractDungeon.player, 1));
			AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
		}

		tickDuration();
	}
}