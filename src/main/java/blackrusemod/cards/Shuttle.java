package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ShuttleAction;

public class Shuttle extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Shuttle.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("shuttle.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public Shuttle() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ShuttleAction());
	}

	@Override
	public AbstractCard makeCopy() {
		return new Shuttle();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}