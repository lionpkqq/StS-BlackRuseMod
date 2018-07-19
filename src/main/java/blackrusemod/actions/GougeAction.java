package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.AmplifyDamagePower;

public class GougeAction extends AbstractGameAction
{
	public GougeAction(AbstractCreature source, AbstractCreature target)
	{
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DEBUFF;
		this.target = target;
		this.source = source;
	}

	public void update()
	{
		if (this.duration == Settings.ACTION_DUR_XFAST) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new AmplifyDamagePower(this.target, 1), 1));
			if (AbstractDungeon.player.hasRelic("PaperSwan")) 
				if (AbstractDungeon.cardRandomRng.randomBoolean())
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, AbstractDungeon.player, new AmplifyDamagePower(this.target, 1), 1));
		}

		tickDuration();
	}
}