package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.OddMushroom;

import blackrusemod.BlackRuseMod;

public class FlawlessFormPower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(FlawlessFormPower.class.getSimpleName());
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
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type) {
		if (type == DamageType.NORMAL) {
			if (this.owner.hasPower(WeakPower.POWER_ID)) return damage / 0.75F;
		}
		return damage;
	}
	
	@Override
	public float modifyBlock(float blockAmount) {
		if (this.owner.hasPower(FrailPower.POWER_ID)) return blockAmount / 0.75F;
		return blockAmount;
	}
	
	@Override
	public float atDamageReceive(float damage, DamageInfo.DamageType type) {
		if (type == DamageType.NORMAL) {
			if (this.owner.hasPower(VulnerablePower.POWER_ID)) {
				if (AbstractDungeon.player.hasRelic(OddMushroom.ID)) {
					return damage / 1.25F;
				}
				return damage / 1.5F;
			}
		}
		return damage;
	}
}