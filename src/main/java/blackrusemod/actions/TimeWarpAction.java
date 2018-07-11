package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class TimeWarpAction extends AbstractGameAction {
	private DamageInfo info;
	private AbstractCard copy;

	public TimeWarpAction(AbstractCreature target, DamageInfo info, AbstractCard c) {
		this.info = info;
		setValues(target, info);
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = Settings.ACTION_DUR_FASTER;
		this.copy = c;
	}

	public void update() {
		if ((this.duration == Settings.ACTION_DUR_FASTER) && (this.target != null)) {
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.POISON));

			this.target.damage(this.info);

			if ((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) {
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(copy.makeStatEquivalentCopy(), false));
			}

			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}