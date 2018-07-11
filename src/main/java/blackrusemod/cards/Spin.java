package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class Spin extends CustomCard {
	public static final String ID = "Spin";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int BLOCK_AMT = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;

	public Spin() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SPIN), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK_AMT;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	}

	public AbstractCard makeCopy() {
		return new Spin();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}