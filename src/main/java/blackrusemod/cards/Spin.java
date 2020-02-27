package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BacklashAction;

public class Spin extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Spin.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("spin.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 13;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int WEAK = 1;

	public Spin() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = WEAK;
		this.isMultiDamage = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SFXAction("ATTACK_HEAVY"));
		addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
		addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageType, AttackEffect.NONE, true));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber));
		}
		addToBot(new BacklashAction(1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Spin();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(1);
		}
	}
}