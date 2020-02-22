package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import blackrusemod.BlackRuseMod;

public class FloweringNightPower extends TwoAmountPower {
	public static final String POWER_ID = "BlackRuseMod:FloweringNightPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	public static final int LIMIT = 6;
	
	public FloweringNightPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.amount2 = LIMIT;
			updateDescription();
			this.region48 = powerAltas.findRegion("flowering_night48");
			this.region128 = powerAltas.findRegion("flowering_night128");
	}
	
	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2]);
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		for (int i = 0; i < this.amount; i++) {
			if (this.amount2 <= 0) break;
			addToBot(new DrawCardAction(this.owner, 1));
			this.amount2--;
			this.updateDescription();
		}
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		this.amount2 = LIMIT;
		this.updateDescription();
	}
}