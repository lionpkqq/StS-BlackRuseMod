package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.FalseFlawlessFormPower;

public class Defy extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Defy.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("defy.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 8;
	private static final int UPGRADE_PLUS_BLOCK = 2;

	public Defy() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseBlock = BLOCK_AMT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		addToBot(new ApplyPowerAction(p, p, new FalseFlawlessFormPower(p, 1), 1));
	}

	public AbstractCard makeCopy() {
		return new Defy();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.selfRetain = true;
		}
	}
}