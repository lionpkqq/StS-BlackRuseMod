package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ReduceDebuffsAction;

public class Reset extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Reset.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("reset.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int PROTECTION = 1;
	
	public Reset() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = PROTECTION;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ReduceDebuffsAction(p, this.magicNumber));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Reset();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}