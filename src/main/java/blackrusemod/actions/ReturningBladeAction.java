package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import basemod.BaseMod;

public class ReturningBladeAction extends AbstractGameAction {
	private AbstractCard itself;
	private int where;
	private int damage;
	public ReturningBladeAction(AbstractCreature target, int damage, AbstractCard itself)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.target = target;
		this.damage = damage;
		this.itself = itself;
	}

	public void update()
	{
		AbstractDungeon.actionManager.addToTop(new TemporalDamageAction(this.damage));
		AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.3F));
		AbstractDungeon.actionManager.addToTop(new SFXAction("ATTACK_HEAVY"));
		
		for (AbstractCard c: AbstractDungeon.player.discardPile.group) if (c == this.itself) this.where = 0;
		for (AbstractCard c: AbstractDungeon.player.drawPile.group) if (c == this.itself) this.where = 1;
		for (AbstractCard c: AbstractDungeon.player.exhaustPile.group) if (c == this.itself) this.where = 2;
		for (AbstractCard c: AbstractDungeon.player.hand.group) if (c == this.itself) this.where = 3;
		if (this.where == 0) {
			if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
				AbstractDungeon.player.createHandIsFullDialog();
			} else {
				AbstractDungeon.player.discardPile.removeCard(this.itself);
				AbstractDungeon.player.hand.addToTop(this.itself);
			}
		}
		else if (this.where == 1) {
			if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
				AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
				AbstractDungeon.player.createHandIsFullDialog();
			} else {
				AbstractDungeon.player.drawPile.removeCard(this.itself);
				AbstractDungeon.player.hand.addToTop(this.itself);
			}
		}
		else if (this.where == 2) {
			if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
				AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
				AbstractDungeon.player.createHandIsFullDialog();
			} else {
				AbstractDungeon.player.exhaustPile.removeCard(this.itself);
				this.itself.unfadeOut();
				AbstractDungeon.player.hand.addToTop(this.itself);
			}
		}
		AbstractDungeon.player.hand.refreshHandLayout();
		AbstractDungeon.player.hand.applyPowers();
		AbstractDungeon.player.hand.glowCheck();
		this.isDone = true;
	}
}