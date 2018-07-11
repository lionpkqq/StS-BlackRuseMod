package blackrusemod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class AmplifyDamagePower extends AbstractPower {
	public static final String POWER_ID = "AmplifyDamagePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public AmplifyDamagePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.type = AbstractPower.PowerType.DEBUFF;
		this.priority = 10;
		updateDescription();
		this.img = BlackRuseMod.getAmplifyDamagePowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void updateDescription()
	{
		this.type = AbstractPower.PowerType.DEBUFF;
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public float atDamageReceive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL && (this.owner != null))
		{
			return damage + this.amount;
		}
		return damage;
	}
}