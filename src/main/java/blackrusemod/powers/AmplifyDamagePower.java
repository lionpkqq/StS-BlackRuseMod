package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.relics.PaperSwan;

public class AmplifyDamagePower extends AbstractPower {
	public static final String POWER_ID = "BlackRuseMod:AmplifyDamagePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public AmplifyDamagePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 10;
		this.type = AbstractPower.PowerType.DEBUFF;
		updateDescription();
		this.region48 = powerAltas.findRegion("blight48");
		this.region128 = powerAltas.findRegion("blight128");
	}
	
	/*public void onInitialApplication() {
		if (AbstractDungeon.player.hasRelic(PaperSwan.ID)) {
			if (AbstractDungeon.cardRandomRng.randomBoolean()) {
				AbstractDungeon.player.getRelic(PaperSwan.ID).flash();
				this.amount += 1;
			}
		}
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		if (AbstractDungeon.player.hasRelic(PaperSwan.ID)) {
			if (AbstractDungeon.cardRandomRng.randomBoolean()) {
				AbstractDungeon.player.getRelic(PaperSwan.ID).flash();
				this.amount += 1;
			}
		}
	}*/

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		this.type = AbstractPower.PowerType.DEBUFF;
	}
	
	public float atDamageReceive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL && (this.owner != null))
		{
			return damage + this.amount;
		}
		return damage;
	}
}