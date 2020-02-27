package blackrusemod.cards;


import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DenyAction;

public class Replace extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Replace.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("replace.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public Replace() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DenyAction(1, false));
		addToBot(new DiscardPileToTopOfDeckAction(p));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Replace();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}