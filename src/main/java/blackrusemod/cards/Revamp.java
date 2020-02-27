package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.RevampAction;

public class Revamp extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Revamp.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("revamp.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int DRAW = 1;
	
	public Revamp() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.exhaust = true;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (AbstractDungeon.player.discardPile.size() > 0) {
			addToBot(new RevampAction(this.magicNumber));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Revamp();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.exhaust = false;
		}
	}
}