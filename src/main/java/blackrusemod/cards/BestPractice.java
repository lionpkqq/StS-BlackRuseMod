package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ProperPracticePower;

public class BestPractice extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(BestPractice.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("best_practice.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public BestPractice() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ProperPracticePower(p, 1), 1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new BestPractice();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}