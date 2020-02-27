package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionAction;
import blackrusemod.powers.DeadlinePower;

public class Deadline extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Deadline.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("deadline.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int AD = 2;

	public Deadline() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = AD;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VisionAction(p, m, new DeadlinePower(m, this.magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Deadline();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}