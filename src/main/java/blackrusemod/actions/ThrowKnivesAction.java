package blackrusemod.actions;

import java.util.function.Consumer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.powers.KnivesPower;

public class ThrowKnivesAction extends AbstractGameAction {
	private DamageInfo info;
	private Consumer<AbstractCreature> action;
	private boolean golden;

	/**
	 * @param p The player (usually AbstractDungeon.player)
	 * @param target The target (usually a monster, picks at random if null)
	 * @param info Damage info for the knives
	 * @param amount Amount of knives to throw
	 * @param action Function to use for each time a knife hits
	 * @param golden Killing Doll visuals
	 */
	public ThrowKnivesAction(AbstractPlayer p, AbstractCreature target, DamageInfo info, int amount, Consumer<AbstractCreature> action, boolean golden) {
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = ActionType.DAMAGE;
		this.source = p;
		this.target = target;
		this.info = info;
		this.amount = amount;
		this.action = action;
		this.golden = golden;
	}

	@Override
	public void update() {
		AbstractPower knives = this.source.getPower(KnivesPower.POWER_ID);
		if (knives != null && knives.amount > 0) {
			for(;this.amount > 0;this.amount--) {
				addToTop(new ThrowKnifeAction(this.source, this.target, this.info, this.action, this.golden));
			}
		}
		this.isDone = true;
	}
}