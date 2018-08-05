package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class SilverSoulPower extends AbstractPower {
	public static final String POWER_ID = "SilverSoulPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private int stack;
	
	public SilverSoulPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("silver_soul48");
		this.region128 = powerAltas.findRegion("silver_soul128");
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public void atStartOfTurn() {
		if (this.owner.hasPower("KnivesPower")) {
			flash();
			this.stack = Math.min(this.amount, this.owner.getPower("KnivesPower").amount);
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "KnivesPower", this.stack));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new SatellitePower(this.owner, this.stack), this.stack));
		}
	}
}