package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ConvertAction;
import blackrusemod.actions.DancingSilverAction;

public class DancingSilver extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(DancingSilver.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("dancing_silver.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int KNIVES = 4;
	
	public DancingSilver() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = KNIVES;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ConvertAction(this.magicNumber));
		addToBot(new DancingSilverAction(2));
	}

	@Override
	public AbstractCard makeCopy() {
		return new DancingSilver();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
		}
	}
}