package blackrusemod.actions;

import java.util.function.Predicate;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class ReturningBladeAction extends AbstractGameAction {
	private AbstractCard itself;
	private int damage;

	public ReturningBladeAction(AbstractCreature target, int damage, AbstractCard itself) {
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.target = target;
		this.damage = damage;
		this.itself = itself;
	}

	@Override
	public void update() {
		Predicate<AbstractCard> predicate = (c) -> { return c == this.itself; };
		addToTop(new FetchAction(AbstractDungeon.player.discardPile, predicate));
		addToTop(new FetchAction(AbstractDungeon.player.drawPile, predicate));
		addToTop(new FetchAction(AbstractDungeon.player.exhaustPile, predicate));
		addToTop(new TemporalDamageAction(this.damage));
		addToTop(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.3F));
		addToTop(new SFXAction("ATTACK_HEAVY"));
		this.isDone = true;
	}
}