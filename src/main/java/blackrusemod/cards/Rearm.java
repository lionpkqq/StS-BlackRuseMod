package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;

public class Rearm extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Rearm.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("rearm.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int KNIVES = 5;
	private static final int DRAW = 2;
	
	public Rearm() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DRAW;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(p, this.magicNumber));
		addToBot(new ApplyPowerAction(p, p, new KnivesPower(p, KNIVES), KNIVES));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Rearm();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}