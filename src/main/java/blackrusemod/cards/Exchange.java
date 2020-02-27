package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;

public class Exchange extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Exchange.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("exchange.png");
	private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int DISCARD = 2;
	
	public Exchange() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DISCARD;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DiscardAction(p, p, this.magicNumber, false));
		addToBot(new ApplyPowerAction(p, p, new KnivesPower(p, this.magicNumber), this.magicNumber));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Exchange();
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