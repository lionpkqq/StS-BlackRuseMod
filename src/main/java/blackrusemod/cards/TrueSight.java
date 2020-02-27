package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.TrueSightPower;

public class TrueSight extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TrueSight.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("true_sight.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 2;

	public TrueSight() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new TrueSightPower(p, -1), -1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new TrueSight();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.selfRetain = true;
		}
	}
}