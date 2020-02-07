package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class FlawlessFormPower extends AbstractPower {
	public static final String POWER_ID = "BlackRuseMod:FlawlessFormPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public FlawlessFormPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			updateDescription();
			this.region48 = powerAltas.findRegion("flawless_form48");
			this.region128 = powerAltas.findRegion("flawless_form128");
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	public float atDamageGive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL) {
			if (this.owner.hasPower("Weakened")) return damage / 0.75F;
		}
		return damage;
	}
	
	public float modifyBlock(float blockAmount)
	{
		if (this.owner.hasPower("Frail")) return blockAmount / 0.75F;
		return blockAmount;
	}
	
	public float atDamageReceive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL)
		{
			if (this.owner.hasPower("Vulnerable")) {
				if (AbstractDungeon.player.hasRelic("Odd Mushroom")) {
					return damage / 1.25F;
				}
				return damage / 1.5F;
			}
		}
		return damage;
	}
}