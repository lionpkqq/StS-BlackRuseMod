package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.AbstractServantCard;

public class SilverBladesPower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(SilverBladesPower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();

	public SilverBladesPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("silver_blades48");
		this.region128 = powerAltas.findRegion("silver_blades128");
	}

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		if(DESCRIPTIONS.length > 2) {
			this.description = this.description + this.amount + DESCRIPTIONS[2];
		}
	}

	@Override
	public float atDamageGive(float dmg, DamageInfo.DamageType type, AbstractCard c) {
		if(c.hasTag(AbstractServantCard.Enums.SILVER_BLADES)) dmg += this.amount;
		return dmg;
	}
	
	@Override
	public void atStartOfTurn() {
		flash();
		addToBot(new ApplyPowerAction(this.owner, this.owner, new KnivesPower(this.owner, this.amount), this.amount));
	}
}