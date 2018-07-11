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
import blackrusemod.powers.StarChefPower;

public class StarChef extends CustomCard {
	public static final String ID = "StarChef";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;

	public StarChef() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.STAR_CHEF), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StarChefPower(p, 1), 1));
	}

	public AbstractCard makeCopy() {
		return new StarChef();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
			this.isInnate = true;
		}
	}
}