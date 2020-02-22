package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DisposalAction;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.ElegancePower;

public class ForgedInTime extends CustomCard {
	public static final String ID = "BlackRuseMod:ForgedInTime";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int PROTECTION = 5;
	
	public ForgedInTime() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.FORGED_IN_TIME), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = PROTECTION;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DisposalAction(p, this.magicNumber));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ForgedInTime();
	}
	
	@Override
	public void applyPowers() {
		this.magicNumber = this.baseMagicNumber = PROTECTION;
		if (!this.canUpgrade()) upgradeMagicNumber(2);
		if (AbstractDungeon.player.hasPower(ElegancePower.POWER_ID)) {
			upgradeMagicNumber(AbstractDungeon.player.getPower(ElegancePower.POWER_ID).amount);
			this.isMagicNumberModified = true;
		}
		super.applyPowers();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
		}
	}
}