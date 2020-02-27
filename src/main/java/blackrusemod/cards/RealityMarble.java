package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.RealityMarblePower;

public class RealityMarble extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(RealityMarble.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("reality_marble.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int RETAIN = 1;

	public RealityMarble() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isInnate = true;
		this.magicNumber = this.baseMagicNumber = RETAIN;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new RealityMarblePower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new RealityMarble();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
			upgradeMagicNumber(1);
		}
	}
}