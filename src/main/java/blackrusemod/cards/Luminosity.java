package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ElegancePower;

public class Luminosity extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Luminosity.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("luminosity.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int ELEGANCE = 1;

	public Luminosity() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = ELEGANCE;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
		addToBot(new ApplyPowerAction(p, p, new ElegancePower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Luminosity();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}