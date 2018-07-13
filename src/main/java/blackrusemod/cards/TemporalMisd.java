package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.AmplifyDamagePower;

public class TemporalMisd extends CustomCard {
	public static final String ID = "TemporalMisd";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = -2;
	private static final int MISD = 1;

	public TemporalMisd() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_MISD), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.isEthereal = true;
		this.magicNumber = this.baseMagicNumber = MISD;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		return false;
	}
	
	public void triggerWhenDrawn() {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
		}
	}

	public AbstractCard makeCopy() {
		return new TemporalMisd();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}