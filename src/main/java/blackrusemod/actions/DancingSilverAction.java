package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.SatellitePower;

public class DancingSilverAction extends AbstractGameAction {
	public DancingSilverAction(int times) {
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.BLOCK;
		this.target = AbstractDungeon.player;
		this.source = AbstractDungeon.player;
		this.amount = times;
	}

	@Override
	public void update() {
		if (this.target != null && this.target.hasPower(SatellitePower.POWER_ID))
			addToBot(new GainBlockAction(this.target, this.target, this.amount*this.target.getPower(SatellitePower.POWER_ID).amount));
		this.isDone = true;
	}
}