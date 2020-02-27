package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ApplyMatrixAction;
import blackrusemod.actions.ConvertAction;

public class SilverMatrix extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(SilverMatrix.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("silver_matrix.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int SATELLITE = 2;

	public SilverMatrix() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = SATELLITE;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ConvertAction(this.magicNumber));
		addToBot(new ApplyMatrixAction());
	}

	@Override
	public AbstractCard makeCopy() {
		return new SilverMatrix();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}