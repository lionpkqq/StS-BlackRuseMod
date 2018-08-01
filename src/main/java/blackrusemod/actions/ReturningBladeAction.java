package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ReturningBladeAction extends AbstractGameAction {
	private DamageInfo info;
	private AbstractCard itself;
	private int where;
	public ReturningBladeAction(AbstractCreature target, int damage, AbstractCard itself)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.target = target;
		this.info = new DamageInfo(AbstractDungeon.player, damage, DamageType.NORMAL);
		this.itself = itself;
	}

	public void update()
	{
		AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		this.info.applyPowers(this.info.owner, this.target);
		this.target.damage(this.info);
		itself.upgrade();
		for (AbstractCard c: AbstractDungeon.player.discardPile.group) if (c == this.itself) this.where = 0;
		for (AbstractCard c: AbstractDungeon.player.drawPile.group) if (c == this.itself) this.where = 1;
		for (AbstractCard c: AbstractDungeon.player.exhaustPile.group) if (c == this.itself) this.where = 2;
		for (AbstractCard c: AbstractDungeon.player.hand.group) if (c == this.itself) this.where = 3;
		if (this.where == 0) {
			if (AbstractDungeon.player.hand.size() == 10) {
				AbstractDungeon.player.createHandIsFullDialog();
			} else {
				AbstractDungeon.player.discardPile.removeCard(this.itself);
				AbstractDungeon.player.hand.addToTop(this.itself);
			}
		}
		else if (this.where == 1) {
			if (AbstractDungeon.player.hand.size() == 10) {
				AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
				AbstractDungeon.player.createHandIsFullDialog();
			} else {
				AbstractDungeon.player.drawPile.removeCard(this.itself);
				AbstractDungeon.player.hand.addToTop(this.itself);
			}
		}
		else if (this.where == 2) {
			if (AbstractDungeon.player.hand.size() == 10) {
				AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
				AbstractDungeon.player.createHandIsFullDialog();
			} else {
				AbstractDungeon.player.exhaustPile.removeCard(this.itself);
				AbstractDungeon.player.hand.addToTop(this.itself);
			}
		}
		AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));
		itself.superFlash();
		AbstractDungeon.player.hand.refreshHandLayout();
		AbstractDungeon.player.hand.applyPowers();
		this.isDone = true;
	}
}