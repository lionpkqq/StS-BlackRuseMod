package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.MakeRandomTemporalCardAction;

public class Moondial extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Moondial.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("moondial.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int TEMPORAL = 2;

	public Moondial() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = TEMPORAL;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new MakeRandomTemporalCardAction(AbstractDungeon.player.hand, this.magicNumber));
		addToBot(new MakeRandomTemporalCardAction(AbstractDungeon.player.drawPile, this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Moondial();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}