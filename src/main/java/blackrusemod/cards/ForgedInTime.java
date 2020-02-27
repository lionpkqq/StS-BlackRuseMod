package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DisposalAction;

public class ForgedInTime extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(ForgedInTime.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("forged_in_time.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int PROTECTION = 5;
	
	public ForgedInTime() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.exhaust = true;
		this.protection = this.baseProtection = PROTECTION;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DisposalAction(p, this.protection));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ForgedInTime();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
		}
	}
}