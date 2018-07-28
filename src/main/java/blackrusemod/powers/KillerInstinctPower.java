package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;

public class KillerInstinctPower extends AbstractPower {
	public static final String POWER_ID = "KillerInstinctPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private int COUNTER_ATTACK_DAMAGE;
	private DamageInfo p_info;

	public KillerInstinctPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getKillerInstinctPowerTexture();
		this.COUNTER_ATTACK_DAMAGE = 5;
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (AbstractDungeon.player.hasPower("SilverBladesPower")) this.COUNTER_ATTACK_DAMAGE = 4 + AbstractDungeon.player.getPower("SilverBladesPower").amount;
		this.p_info = new DamageInfo(AbstractDungeon.player, this.COUNTER_ATTACK_DAMAGE, DamageInfo.DamageType.NORMAL);
		if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != this.owner))
		{
			for (int i = 0; i < this.amount; i++) {
				flash();
				p_info.applyPowers(AbstractDungeon.player, info.owner);
				AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(AbstractDungeon.player, info.owner, p_info, false, "Weakened"));
			}
		}
		return damageAmount;
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}