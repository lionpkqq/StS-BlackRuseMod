package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class ProtectionPower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(ProtectionPower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public ProtectionPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 2;
		updateDescription();
		this.region48 = powerAltas.findRegion("protection48");
		this.region128 = powerAltas.findRegion("protection128");
	}

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	@Override
	public int onLoseHp(int damageAmount) {
		flash();
		int actual_damage = damageAmount - this.amount;
		if (actual_damage < 0) {
			this.amount -= damageAmount;
			this.updateDescription();
			actual_damage = 0;
		}
		else 
			addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		return actual_damage;
	}
}