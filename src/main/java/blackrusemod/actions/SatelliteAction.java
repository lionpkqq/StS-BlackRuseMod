package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.vfx.ServantDaggerEffect;

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
		if (this.source.hasPower("SatellitePower")) {
			if (this.source.getPower("SatellitePower").amount > 0) {
				if ((this.target != null) && !(this.target.isDying) && !(this.target.halfDead) && (this.target.currentHealth > 0)) {
					this.target.damageFlash = true;
					this.target.damageFlashFrames = 4;
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, 
							AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
					this.info.applyPowers(this.info.owner, this.target);
					this.target.damage(this.info);
					if ((this.target != null) && (this.target.hb != null)) {
						AbstractDungeon.actionManager.addToTop(new VFXAction(new ServantDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
					}
					
					AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.source, this.source, "SatellitePower", 1));
					
					if (this.source.hasPower("SurpressingFirePower")) {
						AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
						this.source.addBlock(this.source.getPower("SurpressingFirePower").amount);
					}
				}
			}
		}
		this.isDone = true;
	}
}