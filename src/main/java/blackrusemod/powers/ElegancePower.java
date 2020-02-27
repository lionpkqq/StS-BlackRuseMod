package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class ElegancePower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(ElegancePower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public ElegancePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 1;
		updateDescription();
		this.region48 = powerAltas.findRegion("elegance48");
		this.region128 = powerAltas.findRegion("elegance128");
	}

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	@Override
	public float modifyBlock(float blockAmount, AbstractCard c) {
		return blockAmount + this.amount;
    }
}