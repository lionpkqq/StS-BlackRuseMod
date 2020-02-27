package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class AmplifyDamagePower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(AmplifyDamagePower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public AmplifyDamagePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 10;
		this.type = AbstractPower.PowerType.DEBUFF;
		updateDescription();
		this.region48 = powerAltas.findRegion("blight48");
		this.region128 = powerAltas.findRegion("blight128");
	}

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		this.type = AbstractPower.PowerType.DEBUFF;
	}
	
	@Override
	public float atDamageReceive(float damage, DamageInfo.DamageType type) {
		if (type == DamageInfo.DamageType.NORMAL && (this.owner != null)) {
			return damage + this.amount;
		}
		return damage;
	}
}