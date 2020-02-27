package blackrusemod.cards;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class BottledTime extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(BottledTime.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("bottled_time.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int DRAW = 2;
	
	public BottledTime() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.exhaust = true;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(p, magicNumber));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new BottledTime();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.isInnate = true;
		}
	}
}