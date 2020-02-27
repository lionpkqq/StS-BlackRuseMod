package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BacklashAction;

public class SpecialFormula extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(SpecialFormula.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("special_formula.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int HEAL = 5;
	
	public SpecialFormula() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = HEAL;
		this.exhaust = true;
		this.tags.add(AbstractCard.CardTags.HEALING);
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainEnergyAction(1));
		addToBot(new HealAction(p, p, this.magicNumber));
		addToBot(new BacklashAction(1));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SpecialFormula();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(3);
		}
	}
}