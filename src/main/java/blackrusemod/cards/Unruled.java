package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class Unruled extends CustomCard {
	public static final String ID = "BlackRuseMod:Unruled";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int UPGRADE_PLUS_DMG = 2;
	private boolean debuffed;

	public Unruled() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.UNRULED), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = UPGRADE_PLUS_DMG;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("Weakened") || p.hasPower("Vulnerable") || p.hasPower("Weakened")) debuffed = true;
		else debuffed = false;
		if (debuffed) AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
		else AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}
	
	public void applyPowers()
	{
		this.baseDamage = ATTACK_DMG;
		if (AbstractDungeon.player.hasPower("Weakened")) this.baseDamage += AbstractDungeon.player.getPower("Weakened").amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower("Vulnerable")) this.baseDamage += AbstractDungeon.player.getPower("Vulnerable").amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower("Frail")) this.baseDamage += AbstractDungeon.player.getPower("Frail").amount*this.magicNumber;
		super.applyPowers();
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	public AbstractCard makeCopy() {
		return new Unruled();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(2);
			upgradeMagicNumber(1);
		}
	}
}