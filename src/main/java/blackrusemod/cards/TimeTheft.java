package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionAction;
import blackrusemod.powers.TimeTheftPower;

public class TimeTheft extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TimeTheft.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("time_theft.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int ENERGY = 2;

	public TimeTheft() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = ENERGY;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VisionAction(p, m, new TimeTheftPower(m, this.magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() {
		return new TimeTheft();
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