package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DenyAction;

public class Deny extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Deny.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("deny.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int DENIED = 3;

	public Deny() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DENIED;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DenyAction(this.magicNumber, true));
	}

	public AbstractCard makeCopy() {
		return new Deny();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.exhaust = false;
		}
	}
}