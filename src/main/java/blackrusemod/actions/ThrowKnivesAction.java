package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.powers.AmplifyDamagePower;
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
		if (this.source.hasPower("KnivesPower")) {
			if (this.source.getPower("KnivesPower").amount > 0) {
				if ((this.target != null) && !(this.target.isDying) && !(this.target.halfDead) && (this.target.currentHealth > 0)) {
					this.target.damageFlash = true;
					this.target.damageFlashFrames = 4;
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
					this.info.applyPowers(this.info.owner, this.target);
					this.target.damage(this.info);
					AbstractDungeon.actionManager.addToTop(new WaitAction(0.06F));
					if ((this.target != null) && (this.target.hb != null)) {
						AbstractDungeon.actionManager.addToTop(new VFXAction(new ServantDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
					}
					
					this.source.getPower("KnivesPower").reducePower(1);
					this.source.getPower("KnivesPower").updateDescription();
					
					if (this.source.hasPower("SurpressingFirePower")) {
						AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
						this.source.addBlock(this.source.getPower("SurpressingFirePower").amount);
					}
					if (this.debuff != null && this.debuff == "Weakened")  
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new WeakPower(this.target, 1, false), 1));
					if (this.debuff != null && this.debuff == "Vulnerable")  
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new VulnerablePower(this.target, 1, false), 1));
					if (this.debuff != null && this.debuff == "Amplify Damage")  {
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new AmplifyDamagePower(this.target, 1), 1));
						if (AbstractDungeon.player.hasRelic("PaperSwan")) 
							if (AbstractDungeon.cardRandomRng.randomBoolean())
								AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, AbstractDungeon.player, new AmplifyDamagePower(this.target, 1), 1));
					}
				}
			}
		}
		this.isDone = true;
	}
}