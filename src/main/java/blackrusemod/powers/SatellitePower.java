package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	public static final String POWER_ID = "BlackRuseMod:SatellitePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private int damage;
	private final int ATTACK = 4;
	
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
	
	public int onAttacked(DamageInfo info, int damageAmount) {
		this.damage = this.ATTACK;
		if ((info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != null) && (info.owner != this.owner))
		{
			flash();
			if (this.owner.hasPower(SilverBladesPower.POWER_ID)) this.damage += this.owner.getPower(SilverBladesPower.POWER_ID).amount;
			AbstractDungeon.actionManager.addToBottom(new SatelliteAction(AbstractDungeon.player, info.owner, 
					new DamageInfo(this.owner, this.damage, DamageType.NORMAL)));
		}
		return damageAmount;
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		this.damage = this.ATTACK;
		if (card.type == AbstractCard.CardType.ATTACK) {
			flash();
			if (this.owner.hasPower(SilverBladesPower.POWER_ID)) this.damage += this.owner.getPower(SilverBladesPower.POWER_ID).amount;
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
		this.damage = this.ATTACK;
		if (this.owner.hasPower(SilverBladesPower.POWER_ID)) this.damage += this.owner.getPower(SilverBladesPower.POWER_ID).amount;
		this.description = DESCRIPTIONS[0] + this.damage + DESCRIPTIONS[1];
	}
}