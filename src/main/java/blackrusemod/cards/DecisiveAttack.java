package blackrusemod.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
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
import blackrusemod.patches.AbstractCardEnum;

public class DecisiveAttack extends CustomCard {
	public static final String ID = "DecisiveAttack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = -1;

	public DecisiveAttack() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.DECISIVE_ATTACK), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = 0;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		if (!this.canUpgrade()) this.energyOnUse += 1;
		if (p.hasRelic("Chemical X")) p.getRelic("Chemical X").flash();
		if (AbstractDungeon.player.hasRelic("Chemical X")) this.energyOnUse += 2;
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 
				AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 10, true));
		if (!this.canUpgrade()) this.energyOnUse -= 1;
		if (AbstractDungeon.player.hasRelic("Chemical X")) this.energyOnUse -= 2;
		AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
	}

	public AbstractCard makeCopy() {
		return new DecisiveAttack();
	}
	
	public void applyPowers()
	{
		int TIMES = EnergyPanel.totalCount;
		if (!this.canUpgrade()) TIMES += 1;
		if (AbstractDungeon.player.hasRelic("Chemical X")) TIMES += 2;
		this.baseDamage = AbstractDungeon.player.hand.size() * TIMES;
		super.applyPowers();
		if (!this.canUpgrade()) this.rawDescription = UPGRADED_DESCRIPTION;
		else this.rawDescription = DESCRIPTION;
		this.rawDescription += EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}

	public void calculateCardDamage(AbstractMonster mo)
	{
		super.calculateCardDamage(mo);
		if (!this.canUpgrade()) this.rawDescription = UPGRADED_DESCRIPTION;
		else this.rawDescription = DESCRIPTION;
		this.rawDescription += EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
		}
	}
}