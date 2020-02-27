package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BacklashAction;
import blackrusemod.actions.DuplicationAction;

public class Duplication extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Duplication.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("duplication.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int DUP = 1;

	public Duplication() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DUP;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DuplicationAction(p, this.magicNumber));
		addToBot(new BacklashAction(1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Duplication();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			upgradeMagicNumber(1);
		}
	}
}