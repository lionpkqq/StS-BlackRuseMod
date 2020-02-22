package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.BlackRuseMod;

public class WasteNotPower extends AbstractPower {
	public static final String POWER_ID = "BlackRuseMod:WasteNotPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private int DRAW;

	public WasteNotPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("waste_not48");
		this.region128 = powerAltas.findRegion("waste_not128");
	}

	@Override
	public void atEndOfTurn(boolean isPlayer)
	{
		flash();
		this.DRAW = this.amount * EnergyPanel.totalCount;
		if (this.DRAW != 0)
			addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawCardNextTurnPower(AbstractDungeon.player, this.DRAW), this.DRAW));
		addToBot(new LoseEnergyAction(EnergyPanel.totalCount));
	}

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}