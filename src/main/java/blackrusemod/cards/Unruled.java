package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;

public class Unruled extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Unruled.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("unruled.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int UPGRADE_PLUS_DMG = 2;

	public Unruled() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = UPGRADE_PLUS_DMG;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower(WeakPower.POWER_ID) || p.hasPower(VulnerablePower.POWER_ID) || p.hasPower(FrailPower.POWER_ID)) {
			addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_HEAVY));
		} else {
			addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		int tmpdmg = this.baseDamage;
		if (AbstractDungeon.player.hasPower(WeakPower.POWER_ID)) this.baseDamage += AbstractDungeon.player.getPower(WeakPower.POWER_ID).amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower(VulnerablePower.POWER_ID)) this.baseDamage += AbstractDungeon.player.getPower(VulnerablePower.POWER_ID).amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower(FrailPower.POWER_ID)) this.baseDamage += AbstractDungeon.player.getPower(FrailPower.POWER_ID).amount*this.magicNumber;
		super.calculateCardDamage(mo);
		this.baseDamage = tmpdmg;
		this.isDamageModified = this.damage != this.baseDamage;
	}

	@Override
	public void applyPowers() {
		int tmpdmg = this.baseDamage;
		if (AbstractDungeon.player.hasPower(WeakPower.POWER_ID)) this.baseDamage += AbstractDungeon.player.getPower(WeakPower.POWER_ID).amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower(VulnerablePower.POWER_ID)) this.baseDamage += AbstractDungeon.player.getPower(VulnerablePower.POWER_ID).amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower(FrailPower.POWER_ID)) this.baseDamage += AbstractDungeon.player.getPower(FrailPower.POWER_ID).amount*this.magicNumber;
		super.applyPowers();
		this.baseDamage = tmpdmg;
		this.isDamageModified = this.damage != this.baseDamage;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Unruled();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(2);
			upgradeMagicNumber(1);
		}
	}
}