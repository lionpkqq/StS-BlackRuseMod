package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;

public class MoonPhasePower extends AbstractPower {
	public static final String POWER_ID = "BlackRuseMod:MoonPhasePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private int BLOCK = 0;
	
	public MoonPhasePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("moon_phase48");
		this.region128 = powerAltas.findRegion("moon_phase128");
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		this.BLOCK = 0;
		if (this.owner.hasPower(WeakPower.POWER_ID)) this.BLOCK += this.owner.getPower(WeakPower.POWER_ID).amount*this.amount;
		if (this.owner.hasPower(VulnerablePower.POWER_ID)) this.BLOCK += this.owner.getPower(VulnerablePower.POWER_ID).amount*this.amount;
		if (this.owner.hasPower(FrailPower.POWER_ID)) this.BLOCK += this.owner.getPower(FrailPower.POWER_ID).amount*this.amount;
		if (!(this.BLOCK == 0)) {
			this.flash();
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.BLOCK));
		}
	}

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}