package blackrusemod.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
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

public class Exchange extends CustomCard {
	public static final String ID = "Exchange";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public Exchange() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.EXCHANGE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KnivesPower(p, p.hand.group.size()), p.hand.group.size()));
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 10, true));
	}

	public AbstractCard makeCopy() {
		return new Exchange();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}