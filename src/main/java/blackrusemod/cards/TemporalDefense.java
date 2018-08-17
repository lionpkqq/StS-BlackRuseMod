package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.ProtectionPower;

public class TemporalDefense extends CustomCard {
	public static final String ID = "TemporalDefense";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int BLOCK_AMT = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int PROTECTION_AMT = 4;
	private static final int UPGRADE_PROTECTION_BLOCK = 2;

	public TemporalDefense() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_DEFENSE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.isEthereal = true;
		this.exhaust = true;
		this.baseBlock = BLOCK_AMT;
		this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new ProtectionPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new TemporalDefense();
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
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPGRADE_PROTECTION_BLOCK);
		}
	}
}