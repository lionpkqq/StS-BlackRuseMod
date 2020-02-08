package blackrusemod.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.powers.AmplifyDamagePower;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SuppressingFirePower;
import blackrusemod.vfx.KDEffect;
import blackrusemod.vfx.ServantDaggerEffect;

public class ThrowKnivesAction extends AbstractGameAction {
	private DamageInfo info;
	private String debuff;
	public ThrowKnivesAction(AbstractPlayer p, AbstractCreature target, DamageInfo info, String debuff)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.source = p;
		this.target = target;
		this.info = info;
		this.debuff = debuff;
	}

	public void update()
	{
		if (this.source.hasPower(KnivesPower.POWER_ID)) {
			if (this.source.getPower(KnivesPower.POWER_ID).amount > 0) {
				if (this.debuff != null && (this.debuff == "Draw" || this.debuff == "Golden")) 
					this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
				if ((this.target != null) && !(this.target.isDying) && !(this.target.halfDead) && (this.target.currentHealth > 0)) {
					// this.target.damageFlash = true;
					// this.target.damageFlashFrames = 4;
					if (this.debuff == null || this.debuff != "Golden") 
						AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, 
								AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
					else CardCrawlGame.sound.play("ATTACK_FAST");
					this.info.applyPowers(this.info.owner, this.target);
					this.target.damage(this.info);
					AbstractDungeon.actionManager.addToTop(new WaitAction(0.06F));
					if ((this.target != null) && (this.target.hb != null)) {
						if (this.debuff != null && this.debuff == "Golden") 
							AbstractDungeon.actionManager.addToTop(new VFXAction(new KDEffect(
									MathUtils.random(1200.0F, 2000.0F) * Settings.scale, com.megacrit.cardcrawl.dungeons.AbstractDungeon.floorY + 
									MathUtils.random(-100.0F, 500.0F) * Settings.scale)));
						else
							AbstractDungeon.actionManager.addToTop(new VFXAction(new ServantDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
					}
					
					this.source.getPower(KnivesPower.POWER_ID).reducePower(1);
					this.source.getPower(KnivesPower.POWER_ID).updateDescription();
					
					if (this.source.hasPower(SuppressingFirePower.POWER_ID)) {
						AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
						this.source.addBlock(this.source.getPower(SuppressingFirePower.POWER_ID).amount);
					}
					if (this.debuff != null && this.debuff == "Draw") 
						AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.source, 1));
					if (this.debuff != null && this.debuff == "Weakened")  
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new WeakPower(this.target, 1, false), 1));
					if (this.debuff != null && this.debuff == "Vulnerable")  
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new VulnerablePower(this.target, 1, false), 1));
					if (this.debuff != null && this.debuff == "Amplify Damage")  {
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new AmplifyDamagePower(this.target, 1), 1));
					}
				}
			}
			if (this.source.getPower(KnivesPower.POWER_ID).amount == 0) 
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.source, this.source, KnivesPower.POWER_ID));
		}
		this.isDone = true;
	}
}