package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;

public class Moonlight extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Moonlight.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("moonlight.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private static final int THROW = 2;
	
	public Moonlight() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = THROW;
		this.tags.add(Enums.SILVER_BLADES);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ThrowKnivesAction(p, m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber, (t) -> {
			addToTop(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1));
		}, false));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Moonlight();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}