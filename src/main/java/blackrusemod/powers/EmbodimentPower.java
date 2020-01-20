package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.TemporalDefense;
import blackrusemod.cards.TemporalEssence;
import blackrusemod.cards.TemporalMisd;
import blackrusemod.cards.TemporalSlicing;

public class EmbodimentPower extends AbstractPower {
	public static final String POWER_ID = "EmbodimentPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();

	public EmbodimentPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("embodiment48");
		this.region128 = powerAltas.findRegion("embodiment128");
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void atEndOfTurn(boolean isPlayer)
	{
		flash();
		AbstractCard c;
		for (int i = 0; i < this.amount; i++) {
			int randomNum = AbstractDungeon.miscRng.random(9);
			if (randomNum == 0) c = new TemporalSlicing().makeCopy();
			else if (randomNum == 1) c = new TemporalMisd().makeCopy();
			else if (randomNum == 2) c = new TemporalDefense().makeCopy();
			else if (randomNum == 3) c = new TemporalSlicing().makeCopy();
			else if (randomNum == 4) c = new TemporalMisd().makeCopy();
			else if (randomNum == 5) c = new TemporalDefense().makeCopy();
			else if (randomNum == 6) c = new TemporalSlicing().makeCopy();
			else if (randomNum == 7) c = new TemporalMisd().makeCopy();
			else if (randomNum == 8) c = new TemporalDefense().makeCopy();
			else c = new TemporalEssence().makeCopy();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, false));
		}
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}