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
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class NoEscape extends CustomCard {
	public static final String ID = "NoEscape";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 12;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int VUL = 2;

	public NoEscape() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.NO_ESCAPE), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = VUL;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
			AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		if (!(m.intent == AbstractMonster.Intent.ATTACK) && !(m.intent == AbstractMonster.Intent.ATTACK_BUFF) && !(m.intent == AbstractMonster.Intent.ATTACK_DEBUFF) && !(m.intent == AbstractMonster.Intent.ATTACK_DEFEND))    
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new NoEscape();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(1);
		}
	}
}