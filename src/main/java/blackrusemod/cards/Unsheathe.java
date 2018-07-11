package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class Unsheathe extends CustomCard {
	public static final String ID = "Unsheathe";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 9;
	private static final int DEBUFFS = 2;

	public Unsheathe() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.UNSHEATHE), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		this.magicNumber = this.baseMagicNumber = DEBUFFS;
		this.baseDamage = ATTACK_DMG;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < 3; i ++)
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Unsheathe();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}