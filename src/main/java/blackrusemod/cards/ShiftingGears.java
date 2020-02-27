package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ElegancePower;

public class ShiftingGears extends AbstractShiftCard {
	public static final String ID = BlackRuseMod.makeID(ShiftingGears.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("shifting_gears.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int DRAW = 3;

	public ShiftingGears() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
	}
	
	@Override
	public void triggerShift() {
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ElegancePower(AbstractDungeon.player, 1), 1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new ShiftingGears();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}