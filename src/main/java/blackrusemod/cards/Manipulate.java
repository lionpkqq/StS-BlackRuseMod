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
import blackrusemod.powers.ManipulatePower;
import blackrusemod.powers.UpgradedManipulatePower;

public class Manipulate extends CustomCard {
	public static final String ID = "Manipulate";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int TEMPORAL = 3;
	
	public Manipulate() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.MANIPULATE), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = TEMPORAL;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.canUpgrade()) AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ManipulatePower(p), -1));
		else AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UpgradedManipulatePower(p), -1));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Manipulate();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
		}
	}
}