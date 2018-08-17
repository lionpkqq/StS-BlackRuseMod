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
import blackrusemod.actions.TimeEmbeddedAction;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.ProtectionPower;

public class TimeEmbedded extends CustomCard {
	public static final String ID = "TimeEmbedded";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int PROTECTION_AMT = 5;
	private static final int UPGRADE_PROTECTION_BLOCK = 3;

	public TimeEmbedded() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TIME_EMBEDDED), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ProtectionPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new TimeEmbeddedAction(p));
	}

	public AbstractCard makeCopy() {
		return new TimeEmbedded();
	}
	
	public void applyPowers() {
		this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
		if (!this.canUpgrade()) upgradeMagicNumber(UPGRADE_PROTECTION_BLOCK);
		if (AbstractDungeon.player.hasPower("ElegancePower")) {
			upgradeMagicNumber(AbstractDungeon.player.getPower("ElegancePower").amount);
			this.isMagicNumberModified = true;
		}
		super.applyPowers();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PROTECTION_BLOCK);
		}
	}
}