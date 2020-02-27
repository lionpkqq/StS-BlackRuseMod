package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.TheWorldPower;

public class TheWorld extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TheWorld.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("the_world.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;
	private static final int DRAW = 3;

	public TheWorld() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(p, this.magicNumber));
		addToBot(new ApplyPowerAction(p, p, new TheWorldPower(p, -1), -1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new TheWorld();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}