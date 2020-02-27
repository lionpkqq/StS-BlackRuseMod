package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class TemporalEssence extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TemporalEssence.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("temporal_essence.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int DRAW = 2;

	public TemporalEssence() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isEthereal = true;
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.tags.add(Enums.TEMP);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(p, this.magicNumber));
		addToBot(new GainEnergyAction(1));
		if (this.upgraded) addToBot(new GainEnergyAction(1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new TemporalEssence();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			upgradeMagicNumber(1);
		}
	}
}