package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class EnergyManipulationPower extends AbstractPower {
	public static final String POWER_ID = "EnergyManipulationPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public EnergyManipulationPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.canGoNegative = true;
		this.region48 = powerAltas.findRegion("energy_manipulation48");
		this.region128 = powerAltas.findRegion("energy_manipulation128");
	}
	
	public void stackPower(int stackAmount) {
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "EnergyManipulationPower"));
		}
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		if (this.amount > 0) {
			this.type = AbstractPower.PowerType.BUFF;
		} else {
			this.type = AbstractPower.PowerType.DEBUFF;
		}
	}
	
	public void atStartOfTurnPostDraw() {
		//flash();
		if (this.amount > 0) AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
		else AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(-this.amount));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "EnergyManipulationPower"));
	}
}