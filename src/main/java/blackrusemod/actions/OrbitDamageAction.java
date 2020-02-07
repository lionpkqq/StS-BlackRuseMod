package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.SatellitePower;

public class OrbitDamageAction extends AbstractGameAction {
	public int[] damage;
	public OrbitDamageAction(int[] damage)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.attackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
		this.damage = damage;
	}

	public void update()
	{
		if (AbstractDungeon.player.hasPower(SatellitePower.POWER_ID))
			for (int i = 0; i < AbstractDungeon.player.getPower(SatellitePower.POWER_ID).amount; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, 
						this.damage, DamageType.NORMAL, this.attackEffect, true));
			}
				
		this.isDone = true;
	}
}