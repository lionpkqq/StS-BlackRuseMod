package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.RealityMarbleAction;

public class RealityMarblePower extends AbstractPower {
	public static final String POWER_ID = "BlackRuseMod:RealityMarblePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();

	public RealityMarblePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("reality_marble48");
		this.region128 = powerAltas.findRegion("reality_marble128");
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (isPlayer && !AbstractDungeon.player.hand.isEmpty()) {
			flash();
			addToBot(new RealityMarbleAction(this.owner, this.amount));
		}
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}
}