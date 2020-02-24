package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;
import blackrusemod.cards.Interfaces.KnivesCard;
import blackrusemod.patches.AbstractCardEnum;

public class FanOfKnives extends CustomCard implements KnivesCard {
	public static final String ID = "BlackRuseMod:FanOfKnives";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = -1;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_DMG = 3;

	public FanOfKnives() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.FAN_OF_KNIVES), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.isMultiDamage = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		if (p.hasRelic(ChemicalX.ID)) p.getRelic(ChemicalX.ID).flash();
		if (p.hasRelic(ChemicalX.ID)) this.energyOnUse += 2;
		for (int i = 0; i < this.energyOnUse; i++)
			for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) 
				addToBot(new ThrowKnivesAction(p, mo, new DamageInfo(p, this.damage, this.damageTypeForTurn), null));
		if (p.hasRelic(ChemicalX.ID)) this.energyOnUse -= 2;
		addToBot(new LoseEnergyAction(this.energyOnUse));
	}

	@Override
	public AbstractCard makeCopy() {
		return new FanOfKnives();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}