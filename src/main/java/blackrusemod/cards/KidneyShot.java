package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;

public class KidneyShot extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(KidneyShot.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("kidney_shot.png");
	private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 4;
	private static final int THROW = 2;

	public KidneyShot() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = THROW;
		this.tags.add(Enums.SILVER_BLADES);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ThrowKnivesAction(p, m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber, (t) -> {
			addToTop(new ApplyPowerAction(t, p, new WeakPower(t, 1, false), 1));
		}, false));
	}

	@Override
	public AbstractCard makeCopy() {
		return new KidneyShot();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}