package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SatellitePower;
import blackrusemod.powers.SuppressingFirePower;

public class ConvertAction extends AbstractGameAction {
	private int stack;
	public ConvertAction (int amount) {
		this.amount = amount;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.target = AbstractDungeon.player;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
	}
	
	public void update() {
		if (this.target != null) {
			if (this.target.hasPower(KnivesPower.POWER_ID)) {
				this.stack = Math.min(this.amount, this.target.getPower(KnivesPower.POWER_ID).amount);
				AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.target, KnivesPower.POWER_ID, this.stack));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.target, new SatellitePower(this.target, this.stack), this.stack));
			}
			if (this.source.hasPower(SuppressingFirePower.POWER_ID)) {
				AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
				this.source.addBlock(this.source.getPower(SuppressingFirePower.POWER_ID).amount);
			}
		}
		this.isDone = true;
	}
}