package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;

public class TemporalMisd extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TemporalMisd.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("temporal_misd.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int MISD = 1;

	public TemporalMisd() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isEthereal = true;
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = MISD;
		this.tags.add(Enums.TEMP);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			addToBot(new ApplyPowerAction(mo, p, new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
			addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber));
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new TemporalMisd();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}