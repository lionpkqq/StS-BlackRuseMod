package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.AdvanceAction;

public class Advance extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Advance.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("advance.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int ZERO = 2;

	public Advance() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = ZERO;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new AdvanceAction(this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Advance();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.isInnate = true;
		}
	}
}