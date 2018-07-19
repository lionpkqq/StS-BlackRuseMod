package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.KnivesPower;

public class Rewind extends CustomCard {
	public static final String ID = "Rewind";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int BLOCK_AMT = 3;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private int consumed;
	
	public Rewind() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.REWIND), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK_AMT;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		if (p.hasPower("KnivesPower") && p.hasPower("ConsumedKnivesPower")) {
			consumed = p.getPower("ConsumedKnivesPower").amount;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KnivesPower(p, -consumed), -consumed));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "ConsumedKnivesPower"));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Rewind();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}