package blackrusemod.cards;


import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;
import blackrusemod.patches.AbstractCardEnum;

public class FanOfKnives extends CustomCard {
	public static final String ID = "FanOfKnives";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int KNIVES = 2;

	public FanOfKnives() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.FAN_OF_KNIVES), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.isMultiDamage = true;
		this.magicNumber = this.baseMagicNumber = KNIVES;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		for (int i = 0; i < this.energyOnUse*this.magicNumber; i++)
			AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(p, m, new DamageInfo(p, this.damage, this.damageTypeForTurn), true, null));
		AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
	}

	public AbstractCard makeCopy() {
		return new FanOfKnives();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}