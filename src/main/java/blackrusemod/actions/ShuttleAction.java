package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShuttleAction extends AbstractGameAction {
	public ShuttleAction()
	{
		this.duration = 0.3F;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
	}

	public void update()
	{
		if (this.duration == 0.3F) {
			AbstractDungeon.player.applyStartOfTurnPowers();
			AbstractDungeon.player.applyStartOfTurnRelics();
            AbstractDungeon.player.applyStartOfTurnPostDrawPowers();
            AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
		}
		tickDuration();
	}
}