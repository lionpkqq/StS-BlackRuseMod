package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.SatelliteAction;

public class SatellitePower extends AbstractPower {
	public static final String POWER_ID = "SatellitePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private float actual_damage;
	private int damage;
	
	public SatellitePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("satellite48");
		this.region128 = powerAltas.findRegion("satellite128");
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		updateDescription();
	}
	
	public float atDamageFinalReceive(float damage, DamageInfo.DamageType type)
	{
		return calculateDamageTakenAmount(damage, type);
	}

	private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
		if ((type != DamageInfo.DamageType.HP_LOSS) && (type != DamageInfo.DamageType.THORNS)) {
			actual_damage = damage - 5.0F;
			if (actual_damage < 0) actual_damage = 0;
			return actual_damage;
		}
		return damage;
	}
	
	public int onAttacked(DamageInfo info, int damageAmount)
	{
		Boolean willLive = Boolean.valueOf(calculateDamageTakenAmount(damageAmount, info.type) < this.owner.currentHealth);
		if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS) && 
				(willLive.booleanValue())) {
			flash();
			AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
		}
		return damageAmount;
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		this.damage = 5;
		if (card.type == AbstractCard.CardType.ATTACK) {
			flash();
			if (this.owner.hasPower("SilverBladesPower")) this.damage += this.owner.getPower("SilverBladesPower").amount;
			if (card.target != AbstractCard.CardTarget.ALL_ENEMY) {
				AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
				AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
				AbstractDungeon.actionManager.addToBottom(new SatelliteAction(AbstractDungeon.player, action.target, 
						new DamageInfo(this.owner, this.damage, DamageType.NORMAL)));
			}
			else 
				AbstractDungeon.actionManager.addToBottom(new SatelliteAction(AbstractDungeon.player, AbstractDungeon.getMonsters().getRandomMonster(true), 
						new DamageInfo(this.owner, this.damage, DamageType.NORMAL)));
		}
	}
	
	public void onAfterUseCard(AbstractCard card, UseCardAction action) {
		updateDescription();
	}

	public void updateDescription()
	{
		this.damage = 5;
		if (this.owner.hasPower("SilverBladesPower")) this.damage += this.owner.getPower("SilverBladesPower").amount;
		this.description = DESCRIPTIONS[0] + this.damage + DESCRIPTIONS[1];
	}
}