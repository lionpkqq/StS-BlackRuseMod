package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.vfx.BounceEffect;

public class BounceAction extends AbstractGameAction {
	private DamageInfo info;
	private int times;
	private int count;
	private int baseDamage;
	
	public BounceAction(AbstractCreature source, AbstractCreature target, int baseDamage, int times) {
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.source = source;
		this.target = target;
		this.times = times;
		this.baseDamage = baseDamage;
		this.info = new DamageInfo(AbstractDungeon.player, baseDamage, DamageType.NORMAL);
	}

	@Override
	public void update() {
		// Count the number of monsters in the room (only the ones that are alive)
		this.count = 0;
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) 
			if ((mo != null) && (!mo.isDeadOrEscaped())) 
				count++;
		
		// Animation
		if ((this.target != null) && (this.target.hb != null)) {
			// this.target.damageFlash = true;
			// this.target.damageFlashFrames = 4;
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, 
					AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
		
		// Damage phase
		this.info.applyPowers(this.info.owner, this.target);
		this.target.damage(this.info);
		this.times--;
		
		// Recursion
		if (this.count > 1 && this.times > 0) {
			this.source = this.target;
			this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
			while (this.source == this.target)
				this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new BounceEffect(this.source.hb.cX, 
					this.source.hb.cY, this.target.hb.cX, this.target.hb.cY)));
			addToBot(new WaitAction(0.1F));
			addToBot(new WaitAction(0.1F));
			addToBot(new WaitAction(0.1F));
			addToBot(new BounceAction(this.source, this.target, this.baseDamage, this.times));
		}
		this.isDone = true;
	}
}