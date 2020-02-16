package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.powers.SatellitePower;
import blackrusemod.powers.SuppressingFirePower;
import blackrusemod.vfx.SatelliteDaggerEffect;

public class SatelliteAction extends AbstractGameAction {
	private DamageInfo info;
	public SatelliteAction(AbstractPlayer p, AbstractCreature target, DamageInfo info)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.source = p;
		this.target = target;
		this.info = info;
	}

	public void update()
	{
		if (this.source.hasPower(SatellitePower.POWER_ID)) {
			if (this.source.getPower(SatellitePower.POWER_ID).amount > 0) {
				if ((this.target != null) && !(this.target.isDying) && !(this.target.halfDead) && (this.target.currentHealth > 0)) {
					// this.target.damageFlash = true;
					// this.target.damageFlashFrames = 4;
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, 
							AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
					this.info.applyPowers(this.info.owner, this.target);
					this.target.damage(this.info);
					AbstractDungeon.actionManager.addToTop(new WaitAction(0.06F));
					if ((this.target != null) && (this.target.hb != null)) {
						AbstractDungeon.actionManager.addToTop(new VFXAction(new SatelliteDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
					}
					this.source.getPower(SatellitePower.POWER_ID).reducePower(1);
					this.source.getPower(SatellitePower.POWER_ID).updateDescription();
					
					if (this.source.hasPower(SuppressingFirePower.POWER_ID)) {
						AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
						this.source.addBlock(this.source.getPower(SuppressingFirePower.POWER_ID).amount);
					}
				}
			}
			if (this.source.getPower(SatellitePower.POWER_ID).amount == 0) 
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.source, this.source, SatellitePower.POWER_ID));
		}
		this.isDone = true;
	}
}