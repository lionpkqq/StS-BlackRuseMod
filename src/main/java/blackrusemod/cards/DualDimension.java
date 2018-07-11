package blackrusemod.cards;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DoubleKnivesAction;
import blackrusemod.patches.AbstractCardEnum;

public class DualDimension extends CustomCard {
	public static final String ID = "DualDimension";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public DualDimension() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.DUAL_DIMENSION), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DoubleKnivesAction(p));
	}

	public AbstractCard makeCopy() {
		return new DualDimension();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}