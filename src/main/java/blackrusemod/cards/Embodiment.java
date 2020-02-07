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
import blackrusemod.powers.EmbodimentPower;
import blackrusemod.powers.UpgradedEmbodimentPower;

public class Embodiment extends CustomCard {
	public static final String ID = "BlackRuseMod:Embodiment";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;

	public Embodiment() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.EMBODIMENT), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!this.upgraded) AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EmbodimentPower(p, 1), 1));
		else AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UpgradedEmbodimentPower(p, 1), 1));
	}

	public AbstractCard makeCopy() {
		return new Embodiment();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
		}
	}
}