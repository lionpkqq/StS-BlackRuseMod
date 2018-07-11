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
import blackrusemod.powers.EnbodimentPower;

public class Enbodiment extends CustomCard {
	public static final String ID = "Enbodiment";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public Enbodiment() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.ENBODIMENT), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnbodimentPower(p, 1), 1));
	}

	public AbstractCard makeCopy() {
		return new Enbodiment();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}