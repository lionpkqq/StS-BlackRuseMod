package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class Entangle extends CustomCard {
	public static final String ID = "Entangle";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;

	public Entangle() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.ENTANGLE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("Weakened")) 
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, p.getPower("Weakened").amount, false), p.getPower("Weakened").amount));
		if (p.hasPower("Vulnerable")) 
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, p.getPower("Vulnerable").amount, false), p.getPower("Vulnerable").amount));
		if (p.hasPower("Frail")) 
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, p.getPower("Frail").amount, false), p.getPower("Frail").amount));
	}

	public AbstractCard makeCopy() {
		return new Entangle();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}