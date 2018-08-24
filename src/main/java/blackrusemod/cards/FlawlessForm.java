package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.FlawlessFormPower;
import blackrusemod.powers.ProtectionPower;

public class FlawlessForm extends CustomCard {
	public static final String ID = "FlawlessForm";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 3;
	private static final int PROTECTION_AMT = 12;

	public FlawlessForm() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.FLAWLESS_FORM), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
		CardTags.addTags(this, BaseModTags.FORM);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlawlessFormPower(p, -1), -1));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ProtectionPower(p, this.magicNumber), this.magicNumber));
	}
	
	public void triggerOnEndOfTurnForPlayingCard() {
		if (!this.canUpgrade()) 
			this.retain = true;
	}

	public AbstractCard makeCopy() {
		return new FlawlessForm();
	}
	
	public void applyPowers() {
		this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
		if (!this.canUpgrade()) upgradeMagicNumber(4);
		if (AbstractDungeon.player.hasPower("ElegancePower")) {
			upgradeMagicNumber(AbstractDungeon.player.getPower("ElegancePower").amount);
			this.isMagicNumberModified = true;
		}
		super.applyPowers();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
			this.retain = true;
			upgradeMagicNumber(4);
		}
	}
}