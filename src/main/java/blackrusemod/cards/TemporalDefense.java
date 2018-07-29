package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class TemporalDefense extends CustomCard {
	public static final String ID = "TemporalDefense";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = -2;
	private static final int BLOCK_AMT = 8;
	private static final int UPGRADE_PLUS_BLOCK = 4;
	private static final int WEAK = 1;

	public TemporalDefense() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_DEFENSE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.isEthereal = true;
		this.baseBlock = BLOCK_AMT;
		this.magicNumber = this.baseMagicNumber = WEAK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		return false;
	}
	
	public void triggerWhenDrawn() {
		this.applyPowers();
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
					new WeakPower(mo, this.magicNumber, false), this.magicNumber));
		}
	}
	public AbstractCard makeCopy() {
		return new TemporalDefense();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(1);
		}
	}
}