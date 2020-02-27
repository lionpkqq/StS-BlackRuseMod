package blackrusemod.actions;

import java.util.function.Consumer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SuppressingFirePower;
import blackrusemod.vfx.KDEffect;
import blackrusemod.vfx.ServantDaggerEffect;

public class ThrowKnifeAction extends AbstractGameAction {
	private DamageInfo info;
	private Consumer<AbstractCreature> action;
	private boolean golden;

	/**
	 * @param p The player (usually AbstractDungeon.player)
	 * @param target The target (usually a monster, picks at random if null)
	 * @param info Damage info for the knife
	 * @param action Function to use for each time a knife hits
	 * @param golden Killing Doll visuals
	 */
	public ThrowKnifeAction(AbstractCreature p, AbstractCreature target, DamageInfo info, Consumer<AbstractCreature> action, boolean golden) {
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = ActionType.DAMAGE;
		this.source = p;
		this.target = target;
		this.info = info;
		this.action = action;
		this.golden = golden;
	}

	@Override
	public void update() {
		AbstractPower knives = this.source.getPower(KnivesPower.POWER_ID);
		if (knives != null && knives.amount > 0) {
			if (this.target == null) {
				this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
				if(this.target == null) {
					this.isDone = true;
					return;
				}
			}
			if (!this.target.isDeadOrEscaped()){
				if (this.golden) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
				} else {
					CardCrawlGame.sound.play("ATTACK_FAST");
				}

				if (this.action != null) this.action.accept(this.target);

				//this.info.applyPowers(this.info.owner, this.target);
				//this.target.damage(this.info);
				this.target.damage(this.info);
				//addToTop(new WaitAction(0.06F));
				if (this.golden) {
					addToTop(new VFXAction(new KDEffect(
							MathUtils.random(1200.0F, 2000.0F) * Settings.scale, AbstractDungeon.floorY + 
							MathUtils.random(-100.0F, 500.0F) * Settings.scale)));
				} else {
					addToTop(new VFXAction(new ServantDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
				}
				
				knives.reducePower(1);
				knives.updateDescription();
				
				if (this.source.hasPower(SuppressingFirePower.POWER_ID)) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AttackEffect.SHIELD));
					this.source.addBlock(this.source.getPower(SuppressingFirePower.POWER_ID).amount);
				}
			}
			if (knives.amount == 0) {
				addToBot(new RemoveSpecificPowerAction(this.source, this.source, knives));
			}
		}
		this.isDone = true;
	}
}