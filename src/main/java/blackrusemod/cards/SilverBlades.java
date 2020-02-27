package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.SilverBladesPower;

public class SilverBlades extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(SilverBlades.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("silver_blades.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int DAMAGE_UPGRADE = 2;

	public SilverBlades() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DAMAGE_UPGRADE;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new SilverBladesPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new SilverBlades();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}