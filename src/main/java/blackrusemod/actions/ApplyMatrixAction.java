package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.MatrixPower;

public class ApplyMatrixAction extends AbstractGameAction {
	public ApplyMatrixAction()
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.BLOCK;
		this.target = AbstractDungeon.player;
		this.source = AbstractDungeon.player;
	}

	public void update()
	{
		if ((this.target != null) && (this.target.hasPower("SatellitePower")))
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, 
					new MatrixPower(this.target, this.target.getPower("SatellitePower").amount), this.target.getPower("SatellitePower").amount));
		this.isDone = true;
	}
}