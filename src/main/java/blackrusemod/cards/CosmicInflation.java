package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class CosmicInflation extends CustomCard {
	public static final String ID = "CosmicInflation";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 9;
	private static final int UPGRADE_PLUS_BLOCK = 3;

	public CosmicInflation() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.COSMIC_INFLATION), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK_AMT;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(makeStatEquivalentCopy(), 1, false, false));
	}

	public AbstractCard makeCopy() {
		return new CosmicInflation();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}