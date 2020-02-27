package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.FloweringNightPower;

public class FloweringNight extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(FloweringNight.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("flowering_night.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;
	private static final int LIMIT = 6;
	
	public FloweringNight() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = LIMIT;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new FloweringNightPower(p, 1), 1));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FloweringNight();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}