package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.AdvanceAction;
import blackrusemod.patches.AbstractCardEnum;

public class Advance extends CustomCard {
	public static final String ID = "Advance";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = -1;

	public Advance() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.ADVANCE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);

	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		if (!this.canUpgrade()) this.energyOnUse += 1;
		if (p.hasRelic("Chemical X")) p.getRelic("Chemical X").flash();
		if (AbstractDungeon.player.hasRelic("Chemical X")) this.energyOnUse += 2;
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 10, true));
		AbstractDungeon.actionManager.addToBottom(new AdvanceAction(this.energyOnUse));
		if (!this.canUpgrade()) this.energyOnUse -= 1;
		if (AbstractDungeon.player.hasRelic("Chemical X")) this.energyOnUse -= 2;
		AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
	}

	public AbstractCard makeCopy() {
		return new Advance();
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
		}
	}
}