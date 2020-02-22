package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
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
	
	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		this.damage = this.ATTACK;
		if (info.type != DamageType.THORNS && info.type != DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
			flash();
			if (this.owner.hasPower(SilverBladesPower.POWER_ID)) this.damage += this.owner.getPower(SilverBladesPower.POWER_ID).amount;
			addToBot(new SatelliteAction(AbstractDungeon.player, info.owner, new DamageInfo(this.owner, this.damage, DamageType.NORMAL)));
		}
		return damageAmount;
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		this.damage = this.ATTACK;
		if (card.type == CardType.ATTACK) {
			flash();
			if (this.owner.hasPower(SilverBladesPower.POWER_ID)) this.damage += this.owner.getPower(SilverBladesPower.POWER_ID).amount;
			if (card.target != CardTarget.ALL_ENEMY) {
				addToBot(new WaitAction(0.1F));
				addToBot(new WaitAction(0.1F));
				addToBot(new SatelliteAction(AbstractDungeon.player, action.target, new DamageInfo(this.owner, this.damage, DamageType.NORMAL)));
			}
			else 
				addToBot(new SatelliteAction(AbstractDungeon.player, AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(this.owner, this.damage, DamageType.NORMAL)));
		}
	}
	
	@Override
	public void onAfterUseCard(AbstractCard card, UseCardAction action) {
		updateDescription();
	}

	@Override
	public void updateDescription() {
		this.damage = this.ATTACK;
		if (this.owner.hasPower(SilverBladesPower.POWER_ID)) this.damage += this.owner.getPower(SilverBladesPower.POWER_ID).amount;
		this.description = DESCRIPTIONS[0] + this.damage + DESCRIPTIONS[1];
	}
}