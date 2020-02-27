package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.SolidifyAction;

public class Solidify extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Solidify.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("solidify.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;

	public Solidify() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SolidifyAction(p));
		if (this.upgraded) addToBot(new SolidifyAction(p));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Solidify();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}